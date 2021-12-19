import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day15 {
    public static void doDay15() throws Exception {
        File file = new File("input15-test.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

        List<List<Integer>> riskTable = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            List<Integer> newRow = new ArrayList<>();
            for (char ch : line.toCharArray()) {
                newRow.add(Integer.parseInt("" + ch));
            }
            riskTable.add(newRow);
        }
        
    }

// helper(riskTable, 0, 0, new HashSet<>(), 0);
        // int maxI = riskTable.size() - 1;
        // int maxJ = riskTable.get(0).size() - 1;
        // Deque<Point> bfs = new LinkedList<>();
        // HashSet<Pair<Integer, Integer>> seen = new HashSet<>();
        // seen.add(new Pair<>(0, 0));
        // bfs.add(new Point(0, 0, 0, seen));
        // while (!bfs.isEmpty()) {
        //     Point next = bfs.poll();
        //     if (next.i == maxI && next.j == maxJ) {
        //         res = Math.min(res, next.risk);
        //     }
        //     for (int[] dir : dirs) {
        //         int newI = dir[0] + next.i;
        //         int newJ = dir[1] + next.j;
        //         if (newI < 0 || newJ < 0 || newI > maxI || newJ > maxJ || next.seen.contains(new Pair<>(newI, newJ))) {
        //             continue;
        //         }
        //         bfs.offer(new Point(next.risk + riskTable.get(newI).get(newJ), newI, newJ, next.seen));
        //     }
        // }
        // System.out.println("Res: " + res);

    // private static class Point {
    //     HashSet<Pair<Integer, Integer>> seen = new HashSet<>();
    //     int risk;
    //     int i;
    //     int j;

    //     public Point(int risk, int i, int j, HashSet<Pair<Integer, Integer>> seen) {
    //         this.risk = risk;
    //         this.i = i;
    //         this.j = j;
    //         this.seen.addAll(seen);
    //         this.seen.add(new Pair<>(i, j));
    //     }
    // }
    // private static int res = Integer.MAX_VALUE;
    // private static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    // private static void helper(List<List<Integer>> riskTable, int i, int j, HashSet<String> seen, int risk) {
    //     String coord = "" + i + "," + j;
    //     System.out.println(coord);
    //     if (i < 0 || i >= riskTable.size() || j < 0 || j >= riskTable.get(i).size()) {
    //         return;
    //     }
    //     if (i == riskTable.size() - 1 && j == riskTable.get(i).size() - 1) {
    //         res = Math.min(res, risk + riskTable.get(i).get(j));
    //         return; 
    //     }
    //     // String coord = "" + i + "," + j;
    //     // System.out.println(coord);
    //     if (seen.contains(coord)) {
    //         return;
    //     }
    //     seen.add(coord);
    //     for (int[] dir : dirs) {
    //         String newCoord = "" + (i + dir[0]) + "," + (j + dir[1]);
    //         if (!seen.contains(newCoord)
    //             && i + dir[0] >= 0 && i + dir[0] < riskTable.size()
    //             && j + dir[1] >= 0 && j + dir[1] < riskTable.get(i).size()) {
    //             helper(riskTable, i + dir[0], j + dir[1], seen, risk + riskTable.get(i).get(j));
    //         }
    //     }
    //     seen.remove(coord);
    // }
}