import java.io.*;
import java.util.*;

public class Day6 {
    public static int doDay6() {
        try {
            File file = new File("input6.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue; // For the blank line inbetween
                }
                // Part 1
                HashMap<Character, Integer> windowCount = new HashMap<>();
                // load up
                for (int i = 0; i < 4; i++) {
                    windowCount.put(line.charAt(i), windowCount.getOrDefault(line.charAt(i), 0) + 1);
                }
                if (windowCount.size() == 4) {
                    return 4;
                }
                int tail = 0;
                int head = 4;
                while (head < line.length()) {
                    char tailChar = line.charAt(tail);
                    windowCount.put(tailChar, windowCount.get(tailChar) - 1);
                    if (windowCount.get(tailChar) == 0) {
                        windowCount.remove(tailChar);
                    }
                    char headChar = line.charAt(head);
                    windowCount.put(headChar, windowCount.getOrDefault(headChar, 0) + 1);
                    if (windowCount.size() == 4) {
                        return head + 1;
                    }
                    tail++;
                    head++;
                }
            }
            fr.close();    //closes the stream and release the resources  
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int doDay6Part2() {
        try {
            File file = new File("input6.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue; // For the blank line inbetween
                }
                // Part 1
                HashMap<Character, Integer> windowCount = new HashMap<>();
                // load up
                for (int i = 0; i < 14; i++) {
                    windowCount.put(line.charAt(i), windowCount.getOrDefault(line.charAt(i), 0) + 1);
                }
                if (windowCount.size() == 14) {
                    return 14;
                }
                int tail = 0;
                int head = 14;
                while (head < line.length()) {
                    char tailChar = line.charAt(tail);
                    windowCount.put(tailChar, windowCount.get(tailChar) - 1);
                    if (windowCount.get(tailChar) == 0) {
                        windowCount.remove(tailChar);
                    }
                    char headChar = line.charAt(head);
                    windowCount.put(headChar, windowCount.getOrDefault(headChar, 0) + 1);
                    if (windowCount.size() == 14) {
                        return head + 1;
                    }
                    tail++;
                    head++;
                }
            }
            fr.close();    //closes the stream and release the resources  
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(doDay6());
        System.out.println(doDay6Part2());
    }
}
