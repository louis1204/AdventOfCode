import java.io.*;
import java.util.*;

public class Day5 {
    public static void doDay5() {
        try {
            File file = new File("input/input5.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            // Build the stacks
            List<Stack<Character>> stacks = buildStacks(br);
            System.out.println(stacks);
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue; // For the blank line inbetween
                }
                // Part 1
                String[] instructions = line.split(" ");
                int amountToMove = Integer.parseInt(instructions[1]);
                int fromStack = Integer.parseInt(instructions[3]);
                int toStack = Integer.parseInt(instructions[5]);
                for (int moveCount = 0; moveCount < amountToMove; moveCount++) {
                    if (!stacks.get(fromStack - 1).isEmpty()) {
                        stacks.get(toStack - 1).push(stacks.get(fromStack - 1).pop());
                    }
                }
            }
            System.out.println("Res pt 1: ");
            for (Stack<Character> stack : stacks) {
                System.out.print(stack.peek());
            }
            // System.out.println("Res pt 2: " + total2);
            fr.close();    //closes the stream and release the resources  
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Stack<Character>> buildStacks(BufferedReader br) throws IOException {
        String line = br.readLine();
        String[] parts = line.trim().split(" ");
        String lastPart = parts[parts.length - 1];
        // Base case
        if (lastPart.charAt(lastPart.length() - 1) != ']') {
            // Build the base of the stacks
            List<Stack<Character>> stacks = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(lastPart); i++) {
                stacks.add(new Stack<>());
            }
            return stacks;
        }
        // Recursive case
        List<Stack<Character>> fromBelow = buildStacks(br);
        // Now parse the characters
        int pos = 0;
        for (int i = 1; i < line.length(); i += 4) {
            if (line.charAt(i) != ' ') {
                fromBelow.get(pos).push(line.charAt(i));
            }
            pos++;
        }
        return fromBelow;
    }
    public static void main(String[] args) {
        doDay5();
    }
}
