import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day8 {
    public static void doDay8() {
        try {  
            File file = new File("input8.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            HashMap<String, Integer> wordCounts = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("\\|")[1].trim().split(" ");
                System.out.println(Arrays.toString(strings));
                for (String s : strings) {
                    int existing = wordCounts.getOrDefault(s, 0);
                    switch (s.length()) {
                        case 2: // 1
                            wordCounts.put(s, existing + 1);
                            break;
                        case 4: // 4
                            wordCounts.put(s, existing + 1);
                            break;
                        case 3: // 7
                            wordCounts.put(s, existing + 1);
                            break;
                        case 7: // 8
                            wordCounts.put(s, existing + 1);
                            break;
                    }
                }
            }
            int res = 0;
            for (Integer count : wordCounts.values()) {
                res += count;
            }
            System.out.println("Res: " + res);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doDay8Part2() {
        try {  
            File file = new File("input8-test.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
            // 1, 4, 7, 8 can be deduced from count of characters
            // 0
            // 2
            // 3
            // 5
            // 6
            // 9
            HashMap<Set<Character>, Integer> setToNum = new HashMap<>();
            Set<Character> eight = new HashSet<Character>() {{
                add('a');
                add('c');
                add('e');
                add('d');
                add('g');
                add('f');
                add('b');
            }};
            Set<Character> five = new HashSet<Character>() {{
                add('c');
                add('d');
                add('f');
                add('b');
                add('e');
            }};
            Set<Character> two = new HashSet<Character>() {{
                add('g');
                add('c');
                add('d');
                add('f');
                add('a');
            }};
            Set<Character> three = new HashSet<Character>() {{
                add('f');
                add('b');
                add('c');
                add('a');
                add('d');
            }};
            Set<Character> seven = new HashSet<Character>() {{
                add('d');
                add('a');
                add('b');
            }};
            Set<Character> nine = new HashSet<Character>() {{
                add('c');
                add('e');
                add('f');
                add('a');
                add('b');
                add('d');
            }};
            Set<Character> six = new HashSet<Character>() {{
                add('c');
                add('d');
                add('f');
                add('g');
                add('e');
                add('b');
            }};
            Set<Character> four = new HashSet<Character>() {{
                add('e');
                add('a');
                add('f');
                add('b');
            }};
            Set<Character> zero = new HashSet<Character>() {{
                add('c');
                add('a');
                add('g');
                add('e');
                add('d');
                add('b');
                add('e');
            }};
            Set<Character> one = new HashSet<Character>() {{
                add('a');
                add('b');
            }};
            setToNum.put(zero, 0);
            setToNum.put(one, 1);
            setToNum.put(two, 2);
            setToNum.put(three, 3);
            setToNum.put(four, 4);
            setToNum.put(five, 5);
            setToNum.put(six, 6);
            setToNum.put(seven, 7);
            setToNum.put(eight, 8);
            setToNum.put(nine, 9);
            String line;
            int res = 0;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("\\|")[1].trim().split(" ");
                System.out.println(Arrays.toString(strings));
                for (int i = strings.length - 1; i >= 0; i--) {
                    char[] arr = strings[i].toCharArray();
                    Set<Character> set = new HashSet<>();
                    for (char ch : arr) {
                        set.add(ch);
                    }
                    res *= 10;
                    System.out.println(setToNum.toString());
                    System.out.println(set.toString());
                    res += setToNum.get(set);
                }
            }
            System.out.println("Res: " + res);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}