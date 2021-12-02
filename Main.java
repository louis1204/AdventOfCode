import java.lang.Integer;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {  
            File file = new File("input1.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
            StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters  

            int prev = -1;
            int res = 0;
            String line;
            List<Integer> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                int curr = Integer.parseInt(line);
                if (prev != -1 && curr > prev) {
                    res++;
                }
                list.add(curr);
                prev = curr;
            }
            System.out.println("Res part 1: " + res);

            prev = -1;
            res = 0;
            for (int i = 0; i < list.size() - 2; i++) {
                int sum = list.get(i) + list.get(i + 1) + list.get(i + 2);
                if (prev != -1 && sum > prev) {
                    res++;
                }
                prev = sum;
            }
            System.out.println("Res part 1.5: " + res);
            fr.close();    //closes the stream and release the resources  
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}