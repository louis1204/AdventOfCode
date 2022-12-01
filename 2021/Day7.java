import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day7 {
    public static void doDay7() {
        try {  
            File file = new File("input7.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            String line = br.readLine();
            String[] init = line.split(",");
            Integer[] crabs = new Integer[init.length];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < init.length; i++) {
                crabs[i] = Integer.parseInt(init[i]);
                min = Math.min(min, crabs[i]);
                max = Math.max(max, crabs[i]);
            }
            int res = helper(crabs, min, max);
            System.out.println("Res: " + res);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int helper(Integer[] crabs, int min, int max) {
        int res = Integer.MAX_VALUE;
        String paperTrail = "";
        for (int p = min; p <= max; p++) {
            int temp = 0;
            // String tempS = "";
            for (Integer crabPos : crabs) {
                int n = Math.abs(crabPos - p);
                // tempS += "(" + crabPos + "," + p + ")->" + n + ",";
                temp += n * (n + 1) / 2;
            }
            // if (res > temp) {
            //     paperTrail = tempS;
            // }
            res = Math.min(res, temp);
        }
        // System.out.println(paperTrail);
        return res;
    }
}