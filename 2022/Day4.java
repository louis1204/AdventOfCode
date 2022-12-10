import java.io.*;
import java.util.*;

public class Day4 {
    public static void doDay4() {
        try {
            File file = new File("input/input4.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            int total = 0;
            int total2 = 0;
            String line;
            while ((line = br.readLine()) != null) {
                // Part 1
                // Place in a list and sort by start time, then see if the second interval is in the previous
                List<int[]> list = new ArrayList<>();
                String[] parts = line.split(",");
                String[] first = parts[0].split("-");
                String[] second = parts[1].split("-");
                list.add(new int[] {Integer.parseInt(first[0]), Integer.parseInt(first[1])});
                list.add(new int[] {Integer.parseInt(second[0]), Integer.parseInt(second[1])});

                list.sort((a, b) -> {
                    if (a[0] == b[0]) {
                        return b[1] - a[1];
                    }
                    return a[0] - b[0];
                });

                int[] earlier = list.get(0);
                int[] later = list.get(1);
                if (later[1] <= earlier[1]) {
                    // System.out.println(Arrays.toString(earlier));
                    // System.out.println(Arrays.toString(later));
                    total++;
                }
                if (later[0] <= earlier[1]) {
                    total2++;
                }
            }
            System.out.println("Res pt 1: " + total);
            System.out.println("Res pt 2: " + total2);
            fr.close();    //closes the stream and release the resources  
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doDay4();
    }
}
