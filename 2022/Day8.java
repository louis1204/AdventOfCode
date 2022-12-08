import java.io.*;
import java.util.*;

public class Day8 {
    public static void doDay8() {
        try {
            File file = new File("input8.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            // For parsing we create a list of lists since we don't know the dimensions prior
            // Then we know that the outer edge is visible
            // Then we can either at each point in the grid check to see if each tree is visible in the 4 cardinal directions
            // this is an m * n * (m + n) algorithm. n^3 essentially
            // We can also create 4 other grids to represent the biggest so far from each direction (from top, bottom, left, right)
            // Then when we check if a tree is visible, we just have to see if one of the directions, it is the biggest
            // this is a n^2 algorithm
            String line;
            List<List<Integer>> grid = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                List<Integer> row = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    row.add(Integer.parseInt("" + c));
                }
                grid.add(row);
            }
            // Create the 3 dp's
            int m = grid.size();
            int n = grid.get(0).size();
            int[][] biggestFromLeft = new int[grid.size()][grid.get(0).size()];
            int[][] biggestFromRight = new int[grid.size()][grid.get(0).size()];
            int[][] biggestFromTop = new int[grid.size()][grid.get(0).size()];
            int[][] biggestFromBottom = new int[grid.size()][grid.get(0).size()];

            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (col == 0) {
                        biggestFromLeft[row][col] = Integer.MIN_VALUE;
                        continue;
                    }
                    biggestFromLeft[row][col] = Math.max(biggestFromLeft[row][col - 1], grid.get(row).get(col - 1));
                }
            }
            for (int row = 0; row < m; row++) {
                for (int col = n - 1; col >= 0; col--) {
                    if (col == n - 1) {
                        biggestFromRight[row][col] = Integer.MIN_VALUE;
                        continue;
                    }
                    biggestFromRight[row][col] = Math.max(biggestFromRight[row][col + 1], grid.get(row).get(col + 1));
                }
            }
            for (int col = 0; col < n; col++) {
                for (int row = 0; row < m; row++) {
                    if (row == 0) {
                        biggestFromTop[row][col] = Integer.MIN_VALUE;
                        continue;
                    }
                    biggestFromTop[row][col] = Math.max(biggestFromTop[row - 1][col], grid.get(row - 1).get(col));
                }
            }
            for (int col = 0; col < n; col++) {
                for (int row = m - 1; row >= 0; row--) {
                    if (row == m - 1) {
                        biggestFromBottom[row][col] = Integer.MIN_VALUE;
                        continue;
                    }
                    biggestFromBottom[row][col] = Math.max(biggestFromBottom[row + 1][col], grid.get(row + 1).get(col));
                }
            }
            int res = 0;
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    int curr = grid.get(row).get(col);
                    int left = biggestFromLeft[row][col];
                    int right = biggestFromRight[row][col];
                    int top = biggestFromTop[row][col];
                    int bottom = biggestFromBottom[row][col];
                    if (curr > left || curr > right || curr > top || curr > bottom) {
                        res++;
                    }
                }
            }
            System.out.println("Part 1: " + res);

            // Part 2: just do the n3 solution
            int res2 = 0;
            for (int row = 1; row < m - 1; row++) {
                for (int col = 1; col < n - 1; col++) {
                    int originalTree = grid.get(row).get(col);
                    int treesLeft = getVisibleTrees(row, col - 1, -1, 0, grid, originalTree);
                    int treesRight = getVisibleTrees(row, col + 1, 1, 0, grid, originalTree);
                    int treesTop = getVisibleTrees(row - 1, col, 0, -1, grid, originalTree);
                    int treesBot = getVisibleTrees(row + 1, col, 0, 1, grid, originalTree);
                    res2 = Math.max(res2, treesLeft * treesRight * treesTop * treesBot);
                    // System.out.println(String.format("Row: %s Col: %s res: %s", row, col, treesLeft * treesRight * treesTop * treesBot));
                    // System.out.println(String.format("Left: %s Right: %s Top: %s Bot: %s", treesLeft, treesRight, treesTop, treesBot));
                }
            }
            System.out.println("Part 2: " + res2);
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getVisibleTrees(int row, int col, int colDir, int rowDir, List<List<Integer>> grid, int originalTree) {
        int res = 0;
        while (row >= 0 && col >= 0 && row < grid.size() && col < grid.get(0).size()) {
            res++;
            if (grid.get(row).get(col) >= originalTree) {
                break;
            }
            row += rowDir;
            col += colDir;
        }
        return res;
    }

    public static void main(String[] args) {
        doDay8();
    }
}
