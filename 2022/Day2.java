import java.io.*;
import java.util.*;

public class Day2 {
    public static void doDay2() {
        try {
            File file = new File("input/input2.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            char[] beat = new char[] {'Y', 'Z', 'X'};
            char[] lose = new char[] {'Z', 'X', 'Y'};
            int total = 0;
            int total2 = 0;
            String line;
            while ((line = br.readLine()) != null) {
                // Part 1
                String[] match = line.split(" ");
                // Points for winning
                if (beat[match[0].charAt(0) - 'A'] == match[1].charAt(0)) {
                    total += 6;
                }
                // Points for playing the hand
                total += (match[1].charAt(0) - 'W');
                // Points for draws
                if (match[0].charAt(0) - 'A' == match[1].charAt(0) - 'X') {
                    // Same hand
                    total += 3;
                }
                // Part 2
                char instruction = match[1].charAt(0);
                char opponentHand = match[0].charAt(0);
                switch (instruction) {
                    case 'X':
                        // lose
                        total2 += lose[opponentHand - 'A'] - 'W';
                        break;
                    case 'Y':
                        // Draw
                        total2 += (opponentHand - 'A') + 1;
                        total2 += 3;
                        break;
                    case 'Z':
                    default:
                        // win
                        total2 += beat[opponentHand - 'A'] - 'W';
                        total2 += 6;
                        break;
                }
            }
            System.out.println("Res part 1: " + total);
            System.out.println("Part 2: " + total2);
            
            fr.close();    //closes the stream and release the resources  
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doDay2();
    }
}
