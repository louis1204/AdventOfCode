import java.io.*;
import java.util.*;

public class Day7 {
    public static void doDay7() {
        try {
            File file = new File("input/input7.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            // Use a stack to keep track of the current directory
            // When we do an ls, read all the files sizes and add to a hashmap with string of stack and the sum
            // When we cd .. pop
            // When we cd dir, push dir to the stack
            Stack<String> dirStack = new Stack<>();
            HashMap<String, Integer> dirSize = new HashMap<>();
            Deque<String[]> line = new LinkedList<>();
            line.offer(br.readLine().split(" "));
            while (!line.isEmpty()) {
                String[] parts = line.poll();
                String command = parts[1];
                if ("cd".equals(command)) {
                    if ("..".equals(parts[2])) {
                        String prevDir = dirStack.toString();
                        dirStack.pop();
                        int sizeOfPrevDir = dirSize.getOrDefault(prevDir, 0);
                        dirSize.put(dirStack.toString(), dirSize.getOrDefault(dirStack.toString(), 0) + sizeOfPrevDir);
                    } else {
                        dirStack.push(parts[2]);
                    }
                } else if ("ls".equals(command)) {
                    String temp = null;
                    int totalSize = 0;
                    while ((temp = br.readLine()) != null && !temp.startsWith("$")) {
                        String[] curr = temp.split(" ");
                        if (!"dir".equals(curr[0])) {
                            totalSize += Integer.parseInt(curr[0]);
                        }
                    }
                    dirSize.put(dirStack.toString(), dirSize.getOrDefault(dirStack.toString(), 0) + totalSize);
                    if (temp != null) {
                        line.offer(temp.split(" "));
                    }
                    continue;
                }
                String temp = br.readLine();
                if (temp != null) {
                    line.offer(temp.split(" "));
                }
            }
            while (dirStack.size() != 1) {
                String prevDir = dirStack.toString();
                dirStack.pop();
                int sizeOfPrevDir = dirSize.getOrDefault(prevDir, 0);
                dirSize.put(dirStack.toString(), dirSize.getOrDefault(dirStack.toString(), 0) + sizeOfPrevDir);
            }
            int res = 0;
            for (Integer size : dirSize.values()) {
                if (size <= 100000) {
                    res += size;
                }
            }
            System.out.println("part 1: " + res);
            // Part 2
            int totalUsedSize = dirSize.get("[/]");
            int totalSize = 70000000;
            int minDist = Integer.MAX_VALUE;
            int freeSizeNeeded = 30000000;
            int part2Res = 0;
            for (Integer size : dirSize.values()) {
                int freeSize = totalSize - (totalUsedSize - size);
                if (freeSize >= freeSizeNeeded) {
                    if (freeSize - freeSizeNeeded < minDist) {
                        minDist = freeSize - freeSizeNeeded;
                        part2Res = size;
                    }
                }
            }
            System.out.println("part 2: " + part2Res);
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doDay7();
    }
}
