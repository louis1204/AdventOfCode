import java.lang.Integer;
import java.io.*;
import java.util.*;

public class Day3 {
    public static void doDay3() {
        try {  
            File file = new File("input3.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            String line;
            List<String> nums = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                nums.add(line);
            }

            List<String> oxyGen = new ArrayList<String>() {{
                addAll(nums);
            }};
            List<String> scrubber = new ArrayList<String>() {{
                addAll(nums);
            }};
            // Make a copy of the counts
            for (int i = 0; i < nums.get(0).length() && oxyGen.size() > 1; i++) { // iterate the positions
                int onesCount = 0;
                int zerosCount = 0;
                ArrayList<String> temp = new ArrayList<>();
                for (String num : oxyGen) {
                    char[] chars = num.toCharArray();
                    onesCount += chars[i] == '1' ? 1 : 0;
                    zerosCount += chars[i] == '0' ? 1 : 0;
                }
                for (String num : oxyGen) {
                    char[] chars = num.toCharArray();
                    if (onesCount >= zerosCount && chars[i] == '1') {
                        temp.add(num);
                    } else if(onesCount < zerosCount && chars[i] == '0') {
                        temp.add(num);
                    }
                }
                oxyGen = temp;
            }
            // Make a copy of the counts
            for (int i = 0; i < nums.get(0).length() && scrubber.size() > 1; i++) { // iterate the positions
                int onesCount = 0;
                int zerosCount = 0;
                ArrayList<String> temp = new ArrayList<>();
                for (String num : scrubber) {
                    char[] chars = num.toCharArray();
                    onesCount += chars[i] == '1' ? 1 : 0;
                    zerosCount += chars[i] == '0' ? 1 : 0;
                }
                for (String num : scrubber) {
                    char[] chars = num.toCharArray();
                    if (zerosCount <= onesCount && chars[i] == '0') {
                        temp.add(num);
                    } else if(zerosCount > onesCount && chars[i] == '1') {
                        temp.add(num);
                    }
                }
                scrubber = temp;
            }
            System.out.println("OxyGen count: " + oxyGen);
            System.out.println("Scrubber count: " + scrubber);
            System.out.println("Res day 3 part 2: " + Integer.parseInt(oxyGen.get(0), 2) * Integer.parseInt(scrubber.get(0), 2));
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}