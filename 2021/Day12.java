import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day12 {
    public static void doDay12() throws Exception {
        File file = new File("input12.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

        // Graph problem
        // Create a hashmap of the paths
        // Start with start
        // Dfs to one until we hit end or can't go anywhere else
        // We can keep a set of lowercase letters and a set of seen lowercase letters
        // backtrack by removing the seen and removing from list
        HashMap<String, Set<String>> graph = new HashMap<>();
        Set<String> lower = new HashSet<>();

        String line;
        while ((line = br.readLine()) != null) {
            String[] pair = line.split("-");
            if (!"start".equals(pair[0]) && !"end".equals(pair[0]) && pair[0].charAt(0) - 'a' >= 0 && pair[0].charAt(0) - 'a' < 26) {
                lower.add(pair[0]);
            }
            if (!"start".equals(pair[1]) && !"end".equals(pair[1]) && pair[1].charAt(0) - 'a' >= 0 && pair[1].charAt(0) - 'a' < 26) {
                lower.add(pair[1]);
            }
            Set<String> existing = graph.getOrDefault(pair[0], new HashSet<>());
            existing.add(pair[1]);
            graph.put(pair[0], existing);

            existing = graph.getOrDefault(pair[1], new HashSet<>());
            existing.add(pair[0]);
            graph.put(pair[1], existing);
        }
        // helper(graph, lower, new HashSet<>(), new ArrayList<>(), "start");
        // System.out.println("res: " + res);
        helper2(graph, lower, new HashMap<>(), new ArrayList<>(), "start", "");
        System.out.println("res2: " + res2);
    }
    static int res, res2 = 0;

    private static void helper(HashMap<String, Set<String>> graph, Set<String> lower, Set<String> seen, List<String> sequence, String curr) {
        System.out.println(sequence.toString());
        if ("end".equals(curr)) {
            res++;
            return;
        }
        Set<String> next = graph.getOrDefault(curr, new HashSet<>());
        for (String n : next) {
            if (seen.contains(n) || "start".equals(n)) {
                continue;
            }
            sequence.add(n);
            if (lower.contains(n)) {
                seen.add(n);
            }
            helper(graph, lower, seen, sequence, n);
            sequence.remove(sequence.size() - 1);
            seen.remove(n);
        }
    }

    private static void helper2(HashMap<String, Set<String>> graph, Set<String> lower, HashMap<String, Integer> seen, List<String> sequence, String curr, String small) {
        if ("end".equals(curr)) {
            res2++;
            System.out.println(sequence.toString());
            return;
        }
        Set<String> next = graph.getOrDefault(curr, new HashSet<>());
        for (String n : next) {
            if (seen.getOrDefault(n, 0) == 2 || "start".equals(n)) {
                continue;
            }
            if (lower.contains(n) && seen.getOrDefault(n, 0) == 1 && seen.getOrDefault(small, 0) == 2) {
                continue;
            }
            sequence.add(n);
            if (lower.contains(n)) {
                seen.put(n, seen.getOrDefault(n, 0) + 1);
            }
            helper2(graph, lower, seen, sequence, n, seen.getOrDefault(n, 0) == 2 ? n : small);
            sequence.remove(sequence.size() - 1);
            if (lower.contains(n)) {
                seen.put(n, seen.getOrDefault(n, 1) - 1);
            }
        }
    }
}