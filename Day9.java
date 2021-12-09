import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day9 {
    public static void doDay9() {
        try {  
            File file = new File("input9.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            List<List<Integer>> heights = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("");
                List<Integer> temp = new ArrayList<>();
                for (String s : strings) {
                    temp.add(Integer.parseInt(s));
                }
                heights.add(temp);
            }
            int res = 0;
            for (int i = 0; i < heights.size(); i++) {
                for (int j = 0; j < heights.get(i).size(); j++) {
                    int top = i - 1 < 0 ? Integer.MAX_VALUE : heights.get(i - 1).get(j);
                    int bot = i + 1 >= heights.size() ? Integer.MAX_VALUE : heights.get(i + 1).get(j);
                    int left = j - 1 < 0 ? Integer.MAX_VALUE : heights.get(i).get(j - 1);
                    int right = j + 1 >= heights.get(i).size() ? Integer.MAX_VALUE : heights.get(i).get(j + 1);
                    int curr = heights.get(i).get(j);

                    if (curr < top && curr < bot && curr < left && curr < right) {
                        res += curr + 1;
                    }
                }
            }
            System.out.println("res: " + res);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HashSet<Pair<Integer, Integer>> seen = new HashSet<>();
    public static void doDay9Part2() {
        try {  
            File file = new File("input9.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            List<List<Integer>> heights = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("");
                List<Integer> temp = new ArrayList<>();
                for (String s : strings) {
                    temp.add(Integer.parseInt(s));
                }
                heights.add(temp);
            }
            int res = 1;
            PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
            for (int i = 0; i < heights.size(); i++) {
                for (int j = 0; j < heights.get(i).size(); j++) {
                    if (heights.get(i).get(j) == 9 || seen.contains(new Pair<>(i, j))) {
                        continue;
                    }
                    heap.offer(dfs(heights, i, j));
                }
            }
            res *= heap.poll();
            res *= heap.poll();
            res *= heap.poll();
            System.out.println("res: " + res);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int dfs(List<List<Integer>> heights, int i, int j) {
        if (i < 0 || j < 0 || i >= heights.size() || j >= heights.get(i).size() || heights.get(i).get(j) == 9 || seen.contains(new Pair<>(i, j))) {
            return 0;
        }
        seen.add(new Pair<>(i, j));
        int count = 1;
        for (int[] dir : dirs) {
            count += dfs(heights, i + dir[0], j + dir[1]);
        }
        return count;
    }
}