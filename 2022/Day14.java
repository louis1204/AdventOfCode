import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day14 {
    public static void doDay14() {
        try {
            File file = new File("input/input14.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            Set<List<Integer>> rocks = new HashSet<>();
            Set<List<Integer>> restedSand = new HashSet<>();
            int floor = -1;
            while ((line = br.readLine()) != null) {
                String[] points = line.split("->");
                for (int i = 1; i < points.length; i++) {
                    String[] start = points[i - 1].split(",");
                    int startX = Integer.parseInt(start[0].strip());
                    int startY = Integer.parseInt(start[1].strip());

                    String[] end = points[i].split(",");
                    int endX = Integer.parseInt(end[0].strip());
                    int endY = Integer.parseInt(end[1].strip());

                    if (startX == endX) {
                        int minY = Math.min(startY, endY);
                        int maxY = Math.max(startY, endY);
                        while (minY <= maxY) {
                            rocks.add(Arrays.asList(startX, minY));
                            minY++;
                        }
                    } else {
                        int minX = Math.min(startX, endX);
                        int maxX = Math.max(startX, endX);
                        while (minX <= maxX) {
                            rocks.add(Arrays.asList(minX, startY));
                            minX++;
                        }
                    }

                    floor = Math.max(floor, Math.max(startY, endY));
                }
            }
            // After establishing the rocks and the floor, start at 500, 0 and simulate
            boolean reachedEnd = false;
            while (!reachedEnd) {
                reachedEnd = placeSand(500, 0, floor, rocks, restedSand);
            }
            System.out.println("Part 1: " + restedSand.size());
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean placeSand(int x, int y, int floor, Set<List<Integer>> rocks, Set<List<Integer>> restedSand) {
        if (y > floor) {
            return true;
        }
        List<Integer> bottom = Arrays.asList(x, y + 1);
        List<Integer> left = Arrays.asList(x - 1, y + 1);
        List<Integer> right = Arrays.asList(x + 1, y + 1);
        if (!rocks.contains(bottom) && !restedSand.contains(bottom)) {
            return placeSand(x, y + 1, floor, rocks, restedSand);
        } else if (!rocks.contains(left) && !restedSand.contains(left)) {
            return placeSand(x - 1, y + 1, floor, rocks, restedSand);
        } else if (!rocks.contains(right) && !restedSand.contains(right)) {
            return placeSand(x + 1, y + 1, floor, rocks, restedSand);
        }
        // Finally we can't move in any direction, so we rest
        restedSand.add(Arrays.asList(x, y));
        return false;
    }

    public static void doDay14Part2() {
        try {
            File file = new File("input/input14.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            Set<List<Integer>> rocks = new HashSet<>();
            Set<List<Integer>> restedSand = new HashSet<>();
            int floor = -1;
            while ((line = br.readLine()) != null) {
                String[] points = line.split("->");
                for (int i = 1; i < points.length; i++) {
                    String[] start = points[i - 1].split(",");
                    int startX = Integer.parseInt(start[0].strip());
                    int startY = Integer.parseInt(start[1].strip());

                    String[] end = points[i].split(",");
                    int endX = Integer.parseInt(end[0].strip());
                    int endY = Integer.parseInt(end[1].strip());

                    if (startX == endX) {
                        int minY = Math.min(startY, endY);
                        int maxY = Math.max(startY, endY);
                        while (minY <= maxY) {
                            rocks.add(Arrays.asList(startX, minY));
                            minY++;
                        }
                    } else {
                        int minX = Math.min(startX, endX);
                        int maxX = Math.max(startX, endX);
                        while (minX <= maxX) {
                            rocks.add(Arrays.asList(minX, startY));
                            minX++;
                        }
                    }

                    floor = Math.max(floor, Math.max(startY, endY));
                }
            }
            floor += 2;
            // After establishing the rocks and the floor, start at 500, 0 and simulate
            while (!restedSand.contains(Arrays.asList(500, 0))) {
                placeSandPart2(500, 0, floor, rocks, restedSand);
            }
            System.out.println("Part 2: " + restedSand.size());
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void placeSandPart2(int x, int y, int floor, Set<List<Integer>> rocks, Set<List<Integer>> restedSand) {
        if (y + 1 != floor) {
            List<Integer> bottom = Arrays.asList(x, y + 1);
            List<Integer> left = Arrays.asList(x - 1, y + 1);
            List<Integer> right = Arrays.asList(x + 1, y + 1);
            if (!rocks.contains(bottom) && !restedSand.contains(bottom)) {
                placeSandPart2(x, y + 1, floor, rocks, restedSand);
                return;
            } else if (!rocks.contains(left) && !restedSand.contains(left)) {
                placeSandPart2(x - 1, y + 1, floor, rocks, restedSand);
                return;
            } else if (!rocks.contains(right) && !restedSand.contains(right)) {
                placeSandPart2(x + 1, y + 1, floor, rocks, restedSand);
                return;
            }
        }
        // Finally we can't move in any direction, so we rest
        restedSand.add(Arrays.asList(x, y));
    }

    public static void main(String[] args) {
        doDay14();
        doDay14Part2();
    }
}
