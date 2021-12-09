import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day6 {
    public static void doDay6() {
        try {  
            File file = new File("input6-test.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            HashMap<Pair<Integer, Integer>, Integer> pointToCount = new HashMap<>();
            String line = br.readLine();
            String[] initFish = line.split(",");
            List<Integer> fishes = new ArrayList<>();
            for (String fish : initFish) {
                fishes.add(Integer.parseInt(fish));
            }

            for (int i = 0; i < 80; i++) {
                List<Integer> newFishes = new ArrayList<>();
                for (int j = 0; j < fishes.size(); j++) {
                    fishes.set(j, fishes.get(j) - 1);
                    if (fishes.get(j) == 0) {
                        newFishes.add(9);
                        fishes.set(j, 7);
                    }
                }
                if (i != 79) {
                    fishes.addAll(newFishes);
                }
            }
            System.out.println("Res: " + fishes.size());
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doDay6Part2() {
        try {  
            File file = new File("input6.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            String line = br.readLine();
            String[] initFish = line.split(",");

            int maxDays = 256;

            HashMap<Integer, Long> countDownToAmount = new HashMap<>();
            for (String s : initFish) {
                int countdown = Integer.parseInt(s);
                countDownToAmount.put(countdown, countDownToAmount.getOrDefault(countdown, 0l) + 1);
            } 
            long res = 0;
            for (int i = 0; i < maxDays; i++) {
                HashMap<Integer, Long> temp = new HashMap<>();
                for (Integer countdown : countDownToAmount.keySet()) {
                    if (countdown == 0) {
                        temp.put(6, temp.getOrDefault(6, 0l) + countDownToAmount.get(0));
                        temp.put(8, temp.getOrDefault(8, 0l) + countDownToAmount.get(0));
                        continue;
                    }
                    temp.put(countdown - 1, temp.getOrDefault(countdown - 1, 0l) + countDownToAmount.get(countdown));
                }
                countDownToAmount = temp;
            }
            for (Long val : countDownToAmount.values()) {
                res += val;
            }
            System.out.println("Res: " + res);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Group fish countdown date to amount
    // call group fish countdown with the next date, and each of those fishe's countdown minus 1
    // with the return value of this, multiply with the amounts
    // public static void doDay6Part2() {
    //     try {  
    //         File file = new File("input6-test.txt");    //creates a new file instance  
    //         FileReader fr = new FileReader(file);   //reads the file  
    //         BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

    //         String line = br.readLine();
    //         String[] initFish = line.split(",");

    //         int maxDays = 18;

    //         List<Integer> countdowns = new ArrayList<>();
    //         for (String s : initFish) {
    //             int countdown = Integer.parseInt(s);
    //             countdowns.add(countdown);
    //         } 
    //         long res = helper(countdowns, maxDays, 0);
    //         System.out.println("Res: " + res);
    //     } catch(IOException e) {
    //         e.printStackTrace();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // private static long helper(List<Integer> countdowns, int maxDays, int currDay) {
    //     if (currDay == maxDays) {
    //         return (long)countdowns.size();
    //     }
    //     // Consolidate
    //     HashMap<Integer, Integer> countDownToAmount = new HashMap<>();
    //     for (Integer countdown : countdowns) {
    //         countDownToAmount.put(countdown, countDownToAmount.getOrDefault(countdown, 0) + 1);
    //     }
    //     List<Integer> next = new ArrayList<>();
    //     next.addAll(countDownToAmount.keySet());
    //     for (int i = 0; i < next.size(); i++) {
    //         int temp = next.get(i) - 1;
    //         next.set(i, temp == 0 ? 6 : temp);
    //     }
    //     for (int j = 0; j < countDownToAmount.getOrDefault(1, 0); j++) {
    //         next.add(8);
    //     }
    //     long multiplier = 1;
    //     for (Integer val : countDownToAmount.values()) {
    //         multiplier *= (long)val;
    //     }
    //     return multiplier * helper(next, maxDays, currDay + 1);
    // }
}