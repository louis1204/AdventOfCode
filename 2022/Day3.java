import java.io.*;
import java.util.*;

public class Day3 {
    public static void doDay3() {
        try {
            File file = new File("input3.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            int total = 0;
            int total2 = 0;
            String line;
            HashMap<Character, Integer> charCount = new HashMap<>();
            while ((line = br.readLine()) != null) {
                // Part 1
                String firstHalf = line.substring(0, line.length() / 2);
                String secondHalf = line.substring(line.length() / 2, line.length());

                Set<Character> firstHalfChars = new HashSet<>();
                for (char c : firstHalf.toCharArray()) {
                    firstHalfChars.add(Character.valueOf(c));
                }
                for (char c : secondHalf.toCharArray()) {
                    if (firstHalfChars.contains(Character.valueOf(c))) {
                        if (Character.isUpperCase(c)) {
                            total += c - 'A' + 27;
                        } else {
                            total += c - 'a' + 1;
                        }
                        break;
                    }
                }

                // Part 2
                Set<Character> lineCharacters = new HashSet<>();
                for (char c : line.toCharArray()) {
                    lineCharacters.add(c);
                }
                for (Character c : lineCharacters) {
                    charCount.put(c, charCount.getOrDefault(c, 0) + 1);
                    if (charCount.get(c) == 3) {
                        if (Character.isUpperCase(c)) {
                            total2 += c - 'A' + 27;
                        } else {
                            total2 += c - 'a' + 1;
                        }
                        charCount.clear();
                        break;
                    }
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
        doDay3();
    }
}
