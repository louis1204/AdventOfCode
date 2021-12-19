import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day16 {
    static String hexToBinary(String hex) {
        int i = Integer.parseInt(hex, 16);
        String bin = Integer.toBinaryString(i);
        return bin;
    }

    public static void doDay16() throws Exception {
        File file = new File("input16-test.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

        Deque<Integer> bits = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("");
            for (String part : parts) {
                for (char ch : part.toCharArray()) {
                    String bitsString = hexToBinary("" + ch);
                    for (char b : bitsString.toCharArray()) {
                        bits.offer(Integer.parseInt("" + b));
                    }
                }
            }
        }
        System.out.println(bits.toString());
        // Skip 21 chars
        int res = 0;
        while (!bits.isEmpty()) {
            int version = getVersion(bits);
            System.out.println(version);
            System.out.println(bits.toString());
            res += version;
            int type = getType(bits);
            System.out.println(type);
            System.out.println(bits.toString());
            res += type;
            int value = getValue(bits, type);
            System.out.println(bits.toString());
        }
        System.out.println(res);
    }

    private static int getVersion(Deque<Integer> queue) {
        String s = "";
        s += queue.poll();
        s += queue.poll();
        s += queue.poll();
        return Integer.parseInt(s, 2);
    }

    private static int getType(Deque<Integer> queue) {
        String s = "";
        s += queue.poll();
        s += queue.poll();
        s += queue.poll();
        return Integer.parseInt(s, 2);
    }

    private static int getValue(Deque<Integer> queue, int type) {
        if (type == 4) {
            return getLiteral(queue);
        }
        return getOperatorValue(queue);
    }

    private static int getLiteral(Deque<Integer> queue) {
        int startBit = queue.poll();
        String s = "";
        while (true) {
            s += queue.poll();
            s += queue.poll();
            s += queue.poll();
            s += queue.poll();
            if (startBit == 0) {
                break;
            }
            startBit = queue.poll();
        }
        return Integer.parseInt(s, 2);
    }

    private static int getOperatorValue(Deque<Integer> queue) {
        int startBit = queue.poll();
        int amount = 15;
        if (startBit == 1) {
            amount = 11;
        }
        for (int i = 0; i < amount; i++) {
            queue.poll();
        }
        return -1;
    }
}