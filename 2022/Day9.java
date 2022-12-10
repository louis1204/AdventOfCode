import java.io.*;
import java.util.*;

public class Day9 {
    public static void doDay9() {
        try {
            File file = new File("input9.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            // Only move T to where H was if there's a distance more than 1
            // This can be determined by checking the surrounding if T's location is in any of the surrounding areas
            // Otherwise leave T where it is
            String line;
            Set<List<Integer>> seenPos = new HashSet<>();
            List<Integer> tailLoc = Arrays.asList(0, 0);
            List<Integer> headLoc = Arrays.asList(0, 0);
            seenPos.add(Arrays.asList(0, 0));

            int[][] dirs = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
            
            HashMap<String, int[]> movements = new HashMap<>();
            movements.put("R", new int[] {0, 1});
            movements.put("L", new int[] {0, -1});
            movements.put("U", new int[] {-1, 0});
            movements.put("D", new int[] {1, 0});

            while ((line = br.readLine()) != null) {
                String[] instruction = line.split(" ");
                int[] movement = movements.get(instruction[0]);
                int amtToMove = Integer.parseInt(instruction[1]);

                for (int i = 0; i < amtToMove; i++) {
                    int prevX = headLoc.get(0);
                    int prevY = headLoc.get(1);
                    headLoc.set(0, headLoc.get(0) + movement[0]);
                    headLoc.set(1, headLoc.get(1) + movement[1]);
                    boolean isNearby = false;
                    for (int[] dir : dirs) {
                        List<Integer> posAroundHead = Arrays.asList(headLoc.get(0) + dir[0], headLoc.get(1) + dir[1]);
                        if (posAroundHead.equals(tailLoc)) {
                            isNearby = true;
                        }
                    }
                    if (!isNearby) {
                        tailLoc.set(0, prevX);
                        tailLoc.set(1, prevY);
                        seenPos.add(Arrays.asList(prevX, prevY));
                    }
                }
            }
            System.out.println("Part 1: " + seenPos.size());
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doDay9Part2() {
        try {
            File file = new File("input9.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            // The second can still move the same way
            // For the 3rd and others we need to check whether or not the previous was displaced
            // If it was, we move in the same direction
            String line;
            Set<List<Integer>> seenPos = new HashSet<>();
            // For the locations, use a hashmap, then we w
            HashMap<Integer, List<Integer>> locs = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                locs.put(i, Arrays.asList(0, 0));
            }
            seenPos.add(Arrays.asList(0, 0));

            int[][] dirs = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
            
            HashMap<String, int[]> movements = new HashMap<>();
            movements.put("R", new int[] {0, 1});
            movements.put("L", new int[] {0, -1});
            movements.put("U", new int[] {-1, 0});
            movements.put("D", new int[] {1, 0});

            while ((line = br.readLine()) != null) {
                String[] instruction = line.split(" ");
                int[] movement = movements.get(instruction[0]);
                int amtToMove = Integer.parseInt(instruction[1]);

                List<Integer> headLoc = locs.get(0);

                System.out.println("Instruction: " + Arrays.toString(instruction));
                for (int i = 0; i < amtToMove; i++) {
                    headLoc.set(0, headLoc.get(0) + movement[0]);
                    headLoc.set(1, headLoc.get(1) + movement[1]);
                    // After moving the head check where to move the others
                    for (int curr = 1; curr <= 9; curr++) {
                        List<Integer> prevLoc = locs.get(curr - 1);
                        List<Integer> currLoc = locs.get(curr);
                        List<Integer> delta = Arrays.asList(prevLoc.get(0) - currLoc.get(0), prevLoc.get(1) - currLoc.get(1));
                        if (Math.abs(delta.get(0)) <= 1 && Math.abs(delta.get(1)) <= 1) {
                            continue;
                        }
                        System.out.println("Delta " + delta);
                        if (delta.equals(Arrays.asList(0, 2))) {
                            // Move right
                            currLoc.set(1, currLoc.get(1) + 1);
                        } else if (delta.equals(Arrays.asList(0, -2))) {
                            // Move left
                            currLoc.set(1, currLoc.get(1) - 1);
                        } else if (delta.equals(Arrays.asList(2, 0))) {
                            // Move down
                            currLoc.set(0, currLoc.get(0) + 1);
                        } else if (delta.equals(Arrays.asList(-2, 0))) {
                            // Move up
                            currLoc.set(0, currLoc.get(0) - 1);
                        } else if (delta.get(0) < 0 && delta.get(1) > 0) {
                            // Move right up
                            currLoc.set(1, currLoc.get(1) + 1);
                            currLoc.set(0, currLoc.get(0) - 1);
                        } else if (delta.get(0) > 0 && delta.get(1) > 0) {
                            // Move right down
                            currLoc.set(1, currLoc.get(1) + 1);
                            currLoc.set(0, currLoc.get(0) + 1);
                        } else if (delta.get(0) < 0 && delta.get(1) < 0) {
                            // Move left up
                            currLoc.set(1, currLoc.get(1) - 1);
                            currLoc.set(0, currLoc.get(0) - 1);
                        } else if (delta.get(0) > 0 && delta.get(1) < 0) {
                            // Move left down
                            currLoc.set(1, currLoc.get(1) - 1);
                            currLoc.set(0, currLoc.get(0) + 1);
                        }
                        if (curr == 9) {
                            seenPos.add(Arrays.asList(currLoc.get(0), currLoc.get(1)));
                        }
                        // System.out.println(String.format("curr: %s, %s", curr, currLoc.toString()));
                    }
                    System.out.println(locs);
                }
                // System.out.println(locs);
            }
            System.out.println("Part 2: " + seenPos.size());
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // doDay9();
        doDay9Part2();
    }
}
