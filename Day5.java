import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day5 {
    public static void doDay5() {
        try {  
            File file = new File("input5.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            HashMap<Pair<Integer, Integer>, Integer> pointToCount = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] coords = line.split("->");
                String[] coord1 = coords[0].trim().split(",");
                String[] coord2 = coords[1].trim().split(",");
                // System.out.println(Arrays.toString(coord1));
                // System.out.println(Arrays.toString(coord2));
                if (coord1[0].equals(coord2[0]) && !coord1[1].equals(coord2[1])) {
                    int intCoord1 = Integer.parseInt(coord1[1]);
                    int intCoord2 = Integer.parseInt(coord2[1]);
                    int lower =  Math.min(intCoord1, intCoord2);
                    int higher =  Math.max(intCoord1, intCoord2);

                    int x = Integer.parseInt(coord1[0]);
                    for (int i = lower; i <= higher; i++) {
                        Pair pair = new Pair<>(x, i);
                        pointToCount.put(pair, pointToCount.getOrDefault(pair, 0) + 1);
                    }
                    continue;
                }

                if (!coord1[0].equals(coord2[0]) && coord1[1].equals(coord2[1])) {
                    int intCoord1 = Integer.parseInt(coord1[0]);
                    int intCoord2 = Integer.parseInt(coord2[0]);
                    int lower =  Math.min(intCoord1, intCoord2);
                    int higher =  Math.max(intCoord1, intCoord2);

                    int y = Integer.parseInt(coord1[1]);
                    for (int i = lower; i <= higher; i++) {
                        Pair pair = new Pair<>(i, y);
                        pointToCount.put(pair, pointToCount.getOrDefault(pair, 0) + 1);
                    }
                    continue;
                }

                // Stright lines are taken care of, now we have to do 45 degrees
                // Orient ourselves
                // Can go down right, down left
                Pair<Integer, Integer> intCoord1 = new Pair<>(Integer.parseInt(coord1[0]), Integer.parseInt(coord1[1]));
                Pair<Integer, Integer> intCoord2 = new Pair<>(Integer.parseInt(coord2[0]), Integer.parseInt(coord2[1]));
                // System.out.println("Coord1 = " + intCoord1.toString());
                // System.out.println("Coord2 = " + intCoord2.toString());
                // Down right
                if (intCoord1.getKey() < intCoord2.getKey() && intCoord1.getValue() < intCoord2.getValue()) {
                    while (!intCoord1.equals(intCoord2)) {
                        pointToCount.put(intCoord1, pointToCount.getOrDefault(intCoord1, 0) + 1);
                        intCoord1 = new Pair<>(intCoord1.getKey() + 1, intCoord1.getValue() + 1);
                        // System.out.println("1");
                    }
                    pointToCount.put(intCoord1, pointToCount.getOrDefault(intCoord1, 0) + 1);
                    continue;
                }
                if (intCoord2.getKey() < intCoord1.getKey() && intCoord2.getValue() < intCoord1.getValue()) {
                    while (!intCoord2.equals(intCoord1)) {
                        pointToCount.put(intCoord2, pointToCount.getOrDefault(intCoord2, 0) + 1);
                        intCoord2 = new Pair<>(intCoord2.getKey() + 1, intCoord2.getValue() + 1);
                        // System.out.println("2");
                    }
                    pointToCount.put(intCoord2, pointToCount.getOrDefault(intCoord2, 0) + 1);
                    continue;
                }
                // Down left
                if (intCoord1.getKey() > intCoord2.getKey() && intCoord1.getValue() < intCoord2.getValue()) {
                    while (!intCoord1.equals(intCoord2)) {
                        pointToCount.put(intCoord1, pointToCount.getOrDefault(intCoord1, 0) + 1);
                        intCoord1 = new Pair<>(intCoord1.getKey() - 1, intCoord1.getValue() + 1);
                        // System.out.println("3");
                    }
                    pointToCount.put(intCoord1, pointToCount.getOrDefault(intCoord1, 0) + 1);
                    continue;
                }
                if (intCoord2.getKey() > intCoord1.getKey() && intCoord2.getValue() < intCoord1.getValue()) {
                    while (!intCoord2.equals(intCoord1)) {
                        pointToCount.put(intCoord2, pointToCount.getOrDefault(intCoord2, 0) + 1);
                        intCoord2 = new Pair<>(intCoord2.getKey() - 1, intCoord2.getValue() + 1);
                        // System.out.println("4");
                    }
                    pointToCount.put(intCoord2, pointToCount.getOrDefault(intCoord2, 0) + 1);
                    continue;
                }
            }
            int res = 0;
            for (Map.Entry<Pair<Integer,Integer>, Integer> entry : pointToCount.entrySet()) {
                if (entry.getValue() > 1) {
                    res++;
                }
            }
            System.out.println("Total points: " + res);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}