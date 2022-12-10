import java.io.*;
import java.util.*;

public class Day10 {
    public static void doDay10() {
        try {
            File file = new File("input/input10.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            // Use a queue to store all the instructions, it can be integer
            // Use another integer to store the cycle and result
            Deque<Integer> queue = new LinkedList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] instruction = line.split(" ");
                if (instruction[0].equals("addx")) {
                    queue.offer(0);
                    queue.offer(Integer.parseInt(instruction[1]));
                } else {
                    queue.offer(0);
                }
            }
            int register = 1;
            int cycle = 1;
            int res = 0;
            while (!queue.isEmpty()) {
                if (cycle >= 20 && (cycle - 20) % 40 == 0) {
                    res += register * cycle;
                    // System.out.println(String.format("Cycle %s register %s adder %s res %s", cycle, register, register * cycle, res));
                }
                // if (cycle > 40 && cycle <= 80)
                //     System.out.println(String.format("Cycle %s register %s", cycle, register));
                if (cycle % 40 >= register && cycle % 40 <= register + 2) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
                if (cycle % 40 == 0) {
                    System.out.println();
                }
                register += queue.poll();
                cycle++;
            }
            System.out.println("Part 1: " + res);
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doDay10();
    }
}
