import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day14 {
    public static void doDay14() throws Exception {
        File file = new File("input14.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

        // Store pair to inbetween in a map
        // Keep the result as a string
        HashMap<Pair<Character, Character>, String> pairToInBtwn = new HashMap<>();
        
        String initLine = br.readLine();
        br.readLine(); // skip the white space

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" -> ");
            Pair<Character, Character> pair = new Pair<>(parts[0].charAt(0), parts[0].charAt(1));
            pairToInBtwn.put(pair, parts[1]);
        }
        System.out.println(pairToInBtwn.toString());
        int timesToDo = 10;
        for (int i = 0; i < timesToDo; i++) {
            String next = "";
            for (int j = 0; j < initLine.length() - 1; j++) {
                char first = initLine.charAt(j);
                char second = initLine.charAt(j + 1);
                Pair<Character, Character> pair = new Pair<>(first, second);
                if (j == 0) {
                    next += first;
                }
                next += pairToInBtwn.get(pair);
                next += second;
            }
            initLine = next;
        }
        // System.out.println(initLine);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Character, Integer> charToCount = new HashMap<>();
        for (char ch : initLine.toCharArray()) {
            charToCount.put(ch, charToCount.getOrDefault(ch, 0) + 1);
        }
        for (Integer val : charToCount.values()) {
            min = Math.min(val, min);
            max = Math.max(val, max);
        }
        System.out.println(max - min);
    }

    public static void doDay14Part2() throws Exception {
        File file = new File("input14.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

        // Store pair to inbetween in a map
        // Keep the result as a string
        // The problem now is that the string grows too large and takes up too much time to parse
        // We can keep the pairs in a map as keys to count?
        // Then when we get a new inbetween we have to decrement the previous pair and add a new count
        // for the new pair
        // Afterwards, we have to aggregate the counts of all the letters
        // BAHBHBA
        // BA = 1
        // HB = 1 // order does matter
        // BH = 1
        // For this case we still need to construct the new ordering string
        HashMap<Pair<Character, Character>, Character> pairToInBtwn = new HashMap<>();
        HashMap<Character, Long> charToCount = new HashMap<>();
        HashMap<Pair<Character, Character>, Long> pairToCount = new HashMap<>();
        String initLine = br.readLine();
        for (int i = 0; i < initLine.length(); i++) {
            char ch = initLine.charAt(i);
            charToCount.put(ch, charToCount.getOrDefault(ch, 0l) + 1l);
            if (i < initLine.length() -  1) {
                Pair<Character, Character> pair = new Pair<>(ch, initLine.charAt(i + 1));
                pairToCount.put(pair, pairToCount.getOrDefault(pair, 0l) + 1l);
            }
        } 

        br.readLine(); // skip the white space

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" -> ");
            Pair<Character, Character> pair = new Pair<>(parts[0].charAt(0), parts[0].charAt(1));
            pairToInBtwn.put(pair, parts[1].charAt(0));
        }
        System.out.println(pairToInBtwn.toString());
        int timesToDo = 40;
        for (int i = 0; i < timesToDo; i++) {
            HashMap<Pair<Character, Character>, Long> next = new HashMap<>();
            // for (pairToCount
            // next.addAll(pairToCount);
            for (Pair<Character, Character> pair : pairToCount.keySet()) {
                char inbtwn = pairToInBtwn.get(pair);
                long amountOfPairs = pairToCount.get(pair);
                // next.remove(pair);
                charToCount.put(inbtwn, charToCount.getOrDefault(inbtwn, 0l) + amountOfPairs);
                Pair<Character, Character> newLeft = new Pair<>(pair.getKey(), inbtwn);
                Pair<Character, Character> newRight = new Pair<>(inbtwn, pair.getValue());
                next.put(newLeft, next.getOrDefault(newLeft, 0l) + amountOfPairs);
                next.put(newRight, next.getOrDefault(newRight, 0l) + amountOfPairs);
            }
            pairToCount = next;
        }
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (Long val : charToCount.values()) {
            min = Math.min(val, min);
            max = Math.max(val, max);
        }
        System.out.println(max - min);
    }
}