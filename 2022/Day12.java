import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day12 {
    public static void doDay12() {
        try {
            File file = new File("input/input12.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            // Use a list of a list to mark the grid
            // keep track of the start and end
            // Given the rules of being only able to step up a slope 1 higher, and any lower
            // BFS to find the end. If we already seen a location,
            // only reconsider if the step to there is lower
            // Essentially this is djikstra's
            List<List<Integer>> grid = new ArrayList<>();
            List<Integer> start = new ArrayList<>();
            List<Integer> end = new ArrayList<>();
            String line;
            int i = 0;
            int j = 0;
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split("");
                List<Integer> row = new ArrayList<>();
                for (String s : splittedLine) {
                    if ("S".equals(s)) {
                        start = Arrays.asList(i, j);
                        row.add(0);
                    } else if ("E".equals(s)) {
                        end = Arrays.asList(i, j);
                        row.add(25);
                    } else {
                        row.add(s.charAt(0) - 'a');
                    }
                    j++;
                }
                i++;
                j = 0;
                grid.add(row);
            }
            // for (List<Integer> row : grid) {
            //     System.out.println(row);
            // }
            int m = grid.size();
            int n = grid.get(0).size();
            int[][] djik = new int[grid.size()][grid.get(0).size()];
            for (int[] row : djik) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            djik[start.get(0)][start.get(1)] = 1;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // i, j, steps
            pq.offer(new int[]{start.get(0), start.get(1), 0});

            int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                i = curr[0];
                j = curr[1];
                int steps = curr[2];
                if (i == end.get(0) && j == end.get(1)) {
                    System.out.println("Part 1: " + steps);
                    break;
                }
                int currElevation = grid.get(i).get(j);
                for (int[] dir : dirs) {
                    int nextI = i + dir[0];
                    int nextJ = j + dir[1];
                    if (nextI < 0 || nextJ < 0 || nextI >= m || nextJ >= n) {
                        continue;
                    }
                    int nextElevation = grid.get(nextI).get(nextJ);
                    if (nextElevation <= currElevation + 1 && steps + 1 < djik[nextI][nextJ]) {
                        djik[nextI][nextJ] = steps + 1;
                        pq.offer(new int[] {nextI, nextJ, steps + 1});
                    }
                }
            }
            // System.out.println("Part 1: " + (inspectionList.get(0) * inspectionList.get(1)));
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doDay12Part2() {
        try {
            File file = new File("input/input12.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            // Use a list of a list to mark the grid
            // keep track of the start and end
            // Given the rules of being only able to step up a slope 1 higher, and any lower
            // BFS to find the end. If we already seen a location,
            // only reconsider if the step to there is lower
            // Essentially this is djikstra's
            List<List<Integer>> grid = new ArrayList<>();
            List<List<Integer>> start = new ArrayList<>();
            List<Integer> end = new ArrayList<>();
            String line;
            int i = 0;
            int j = 0;
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split("");
                List<Integer> row = new ArrayList<>();
                for (String s : splittedLine) {
                    if ("S".equals(s) || s.equals("a")) {
                        start.add(Arrays.asList(i, j));
                        row.add(0);
                    } else if ("E".equals(s)) {
                        end = Arrays.asList(i, j);
                        row.add(25);
                    } else {
                        row.add(s.charAt(0) - 'a');
                    }
                    j++;
                }
                i++;
                j = 0;
                grid.add(row);
            }
            // for (List<Integer> row : grid) {
            //     System.out.println(row);
            // }
            int min = Integer.MAX_VALUE;
            for (List<Integer> s : start) {
                int m = grid.size();
                int n = grid.get(0).size();
                int[][] djik = new int[grid.size()][grid.get(0).size()];
                for (int[] row : djik) {
                    Arrays.fill(row, Integer.MAX_VALUE);
                }

                djik[s.get(0)][s.get(1)] = 1;
                PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // i, j, steps
                pq.offer(new int[]{s.get(0), s.get(1), 0});

                int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                while (!pq.isEmpty()) {
                    int[] curr = pq.poll();
                    i = curr[0];
                    j = curr[1];
                    int steps = curr[2];
                    if (i == end.get(0) && j == end.get(1)) {
                        min = Math.min(steps, min);
                        break;
                    }
                    int currElevation = grid.get(i).get(j);
                    for (int[] dir : dirs) {
                        int nextI = i + dir[0];
                        int nextJ = j + dir[1];
                        if (nextI < 0 || nextJ < 0 || nextI >= m || nextJ >= n) {
                            continue;
                        }
                        int nextElevation = grid.get(nextI).get(nextJ);
                        if (nextElevation <= currElevation + 1 && steps + 1 < djik[nextI][nextJ]) {
                            djik[nextI][nextJ] = steps + 1;
                            pq.offer(new int[] {nextI, nextJ, steps + 1});
                        }
                    }
                }
            }
            System.out.println("Part 2: " + min);
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doDay12();
        doDay12Part2();
    }
}
