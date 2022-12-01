import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day13 {
    public static void doDay13() throws Exception {
        File file = new File("input13.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

        // Direction of fold will always be a vertical or horizontal line
        // Can we assume that the max x and max y will just be the hole's maximums?
        // No since if we fold in a horizontal line super far off the hole's maximums the points will get reversed?
        // We will always fold up or left, so we can safely assume this
        // We can just have a set of the points, if we really want to optimize then we can also store these points in a hashmap to points
        // but no need to do anything fancy
        // 800 points in the sample
        // When we fold on a y, we evaluate each point to see if they are y or greater.
        // If y, then we remove. If greater, then we need to subtract with ((dist to y) * 2) to the y value, remove from the set and readd the new point
        Set<Pair<Integer, Integer>> holes = new HashSet<>();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String[] splits = line.split(",");
            holes.add(new Pair<>(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]))); // (x, y)
        }
        // foldY(7, holes);
        // foldX(5, holes);
        foldX(655, holes);
        foldY(447, holes);
        foldX(327, holes);
        foldY(223, holes);
        foldX(163, holes);
        foldY(111, holes);
        foldX(81, holes);
        foldY(55, holes);
        foldX(40, holes);
        foldY(27, holes);
        foldY(13, holes);
        foldY(6, holes);
        // System.out.println(holes.size());
    }

    private static void foldY(int val, Set<Pair<Integer, Integer>> holes) {
        fold(false, val, holes);
    }
    private static void foldX(int val, Set<Pair<Integer, Integer>> holes) {
        fold(true, val, holes);
    }

    private static void fold(boolean isX, int val, Set<Pair<Integer, Integer>> holes) {
        Set<Pair<Integer, Integer>> temp = new HashSet<>();
        if (!isX) {
            // fold on Y, so we gotta get holes greater than Y
            for (Pair<Integer, Integer> hole : holes) {
                if (hole.getValue() == val) {
                    continue;
                }
                if (hole.getValue() > val) {
                    int dist = hole.getValue() - val;
                    Pair<Integer, Integer> newPos = new Pair<>(hole.getKey(), hole.getValue() - (2 * dist));
                    temp.add(newPos);
                    continue;
                }
                temp.add(hole); // this hole is not affected
            }
        } else {
            // fold on X, so we gotta get holes greater than X
            for (Pair<Integer, Integer> hole : holes) {
                if (hole.getKey() == val) {
                    continue;
                }
                if (hole.getKey() > val) {
                    int dist = hole.getKey() - val;
                    Pair<Integer, Integer> newPos = new Pair<>(hole.getKey() - (2 * dist), hole.getValue());
                    temp.add(newPos);
                    continue;
                }
                temp.add(hole); // this hole is not affected
            }
        }
        holes.clear();
        holes.addAll(temp);
        print(holes);
    }

    private static void print(Set<Pair<Integer, Integer>> holes) {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Pair<Integer, Integer> hole : holes) {
            maxX = Math.max(maxX, hole.getKey());
            maxY = Math.max(maxY, hole.getValue());
        }
        String[][] display = new String[maxY + 1][maxX + 1];

        for (int i = 0; i < display.length; i++) {
            Arrays.fill(display[i], " ");
        }
        for (Pair<Integer, Integer> hole : holes) {
            display[hole.getValue()][hole.getKey()] = "#";
        }
        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[i].length; j++) {
                if ("#".equals(display[i][j])) {
                    System.out.print("█");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}