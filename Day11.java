import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day11 {
    static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    static int flashes = 0;

    public static void doDay11() throws Exception {
        File file = new File("input11.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  


        HashMap<Integer, HashSet<Pair<Integer, Integer>>> valToPoints = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            valToPoints.put(i, new HashSet<>());
        }
        int[][] grid = new int[10][10];
        int i = 0;
        String line;
        while ((line = br.readLine()) != null) {
            char[] chars = line.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                int intVal = Integer.parseInt("" + chars[j]);
                HashSet<Pair<Integer, Integer>> existing = valToPoints.get(intVal);
                existing.add(new Pair<>(i, j));
                valToPoints.put(intVal, existing);
                grid[i][j] = intVal;
            }
            i++;
        }
        int maxSteps = 100;
        
        for (i = 0; i < maxSteps; i++) {
            increment(valToPoints, grid);
            // printGrid(grid, i);
            // System.out.println(valToPoints.get(0).toString());
            if (!valToPoints.get(0).isEmpty()) {
                lightup(valToPoints, grid);
            }
            printGrid(grid, i);
            System.out.println(valToPoints.toString());
        }
        System.out.println("flashes: " + flashes);
    }

    public static void doDay11Part2() throws Exception {
        File file = new File("input11.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  


        HashMap<Integer, HashSet<Pair<Integer, Integer>>> valToPoints = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            valToPoints.put(i, new HashSet<>());
        }
        int[][] grid = new int[10][10];
        int i = 0;
        String line;
        while ((line = br.readLine()) != null) {
            char[] chars = line.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                int intVal = Integer.parseInt("" + chars[j]);
                HashSet<Pair<Integer, Integer>> existing = valToPoints.get(intVal);
                existing.add(new Pair<>(i, j));
                valToPoints.put(intVal, existing);
                grid[i][j] = intVal;
            }
            i++;
        }
        int step = 1;
        
        while (true) {
            increment(valToPoints, grid);
            // printGrid(grid, i);
            // System.out.println(valToPoints.get(0).toString());
            if (!valToPoints.get(0).isEmpty()) {
                lightup(valToPoints, grid);
            }
            printGrid(grid, i);
            System.out.println(valToPoints.toString());
            if (valToPoints.get(0).size() == 100) {
                break;
            }
            step++;
        }
        System.out.println("step: " + step);
    }

    private static void increment(HashMap<Integer, HashSet<Pair<Integer, Integer>>> valToPoints, int[][] grid) {
        HashMap<Integer, HashSet<Pair<Integer, Integer>>> temp = new HashMap<>();
        for (int i = 9; i >= 0; i--) {
            int newVal = (i + 1) % 10;
            temp.put(newVal, valToPoints.get(i));
        }
        valToPoints.clear();
        for (Map.Entry<Integer, HashSet<Pair<Integer, Integer>>> entry : temp.entrySet()) {
            valToPoints.put(entry.getKey(), entry.getValue());
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // if (grid[i][j] != 0) {
                grid[i][j] = (grid[i][j] + 1) % 10;
                // }
            }
        }
    }
    private static void lightup(HashMap<Integer, HashSet<Pair<Integer, Integer>>> valToPoints, int[][] grid) {
        HashSet<Pair<Integer, Integer>> zeros = new HashSet<>();
        Deque<Pair<Integer, Integer>> zerosQueue = new LinkedList<>();
        zerosQueue.addAll(valToPoints.get(0));
        zeros.addAll(valToPoints.get(0));
        // Go through all zero's and add to surroundings
        while (!zerosQueue.isEmpty()) {
            Pair<Integer, Integer> point = zerosQueue.poll();
            zeros.add(point);
            for (int[] dir : dirs) {
                int newI = point.getKey() + dir[0];
                int newJ = point.getValue() + dir[1];
                if (newI < 0 || newI >= 10 || newJ < 0 || newJ >= 10) {
                    continue;
                }
                Pair<Integer, Integer> pair = new Pair<>(newI, newJ);
                if (grid[newI][newJ] == 0 && zeros.contains(pair)) {
                    continue;
                }
                // System.out.println("new i " + newI);
                // System.out.println("new j " + newJ);
                int existing = grid[newI][newJ];
                valToPoints.get(existing).remove(pair);
                int newVal = (existing + 1) % 10;
                HashSet<Pair<Integer, Integer>> existingSet = valToPoints.get(newVal);
                existingSet.add(pair);
                valToPoints.put(newVal, existingSet);
                if (newVal == 0) {
                    zerosQueue.add(pair);
                    zeros.add(pair);
                }
                grid[newI][newJ] = newVal;
            }
            flashes++;
        }
    }

    private static void printGrid(int[][] grid, int step) {
        System.out.println("Step " + step);
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}