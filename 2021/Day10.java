import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day10 {
    public static void doDay10() throws Exception {
        File file = new File("input10.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

        List<List<Integer>> heights = new ArrayList<>();
        String line;
        HashMap<Character, Character> endToOpen = new HashMap<>();
        HashMap<Character, Integer> endToPoint = new HashMap<>();
        endToOpen.put(')', '(');
        endToPoint.put(')', 3);
        endToOpen.put(']', '[');
        endToPoint.put(']', 57);
        endToOpen.put('}', '{');
        endToPoint.put('}', 1197);
        endToOpen.put('>', '<');
        endToPoint.put('>', 25137);
        int res = 0;
        List<Stack<Character>> incompletes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            Stack<Character> stack = new Stack<>();
            incompletes.add(stack);
            for (char ch : line.toCharArray()) {
                if (endToOpen.containsKey(ch)) {
                    if (stack.pop() != endToOpen.get(ch)) {
                        res += endToPoint.get(ch);
                        incompletes.remove(incompletes.size() - 1);
                        break;
                    }
                } else {
                    stack.push(ch);
                }
            }
        }
        System.out.println("res: " + res);

        HashMap<Character, Integer> openToPoint = new HashMap<>();
        openToPoint.put('(', 1);
        openToPoint.put('[', 2);
        openToPoint.put('{', 3);
        openToPoint.put('<', 4);
        List<Long> scores = new ArrayList<>();
        for (Stack<Character> incomplete : incompletes) {
            long temp = 0l;
            while (!incomplete.isEmpty()) {
                temp *= 5l;
                temp += openToPoint.get(incomplete.pop());
            }
            scores.add(temp);
        }
        scores.sort(new Comparator<Long>() {
            @Override
            public int compare(Long a, Long b) {
                if (a < b) {
                    return -1;
                }
                if (a > b) {
                    return 1;
                }
                return 0;
            }
        });
        System.out.println("Res 2: " + scores.get(scores.size() / 2));
    }
}