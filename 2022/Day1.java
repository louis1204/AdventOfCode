import java.lang.Integer;
import java.io.*;
import java.util.*;

public class Day1 {
    public static void doDay1() {
        try {  
            File file = new File("input/input1.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            int max = -1;
            int curr = 0;
            String line;
            PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    max = Math.max(curr, max);
                    minHeap.offer(curr);
                    curr = 0;
                    if (minHeap.size() > 3) {
                        minHeap.poll();
                    }
                    continue;
                }
                curr += Integer.parseInt(line);
            }
            minHeap.offer(curr);
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
            System.out.println("Res part 1: " + max);
            int topThreeSum = 0;
            System.out.println(minHeap.toString());
            while (!minHeap.isEmpty()) {
                topThreeSum += minHeap.poll();
            }
            System.out.println("Res part 2: " + topThreeSum);
            fr.close();    //closes the stream and release the resources  
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doDay1();
    }
}
