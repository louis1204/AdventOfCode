import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Day4 {
    public static void doDay4() {
        try {  
            File file = new File("input4.txt");    //creates a new file instance  
            FileReader fr = new FileReader(file);   //reads the file  
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

            String[] numbers = br.readLine().split(",");
            // skip the next blank
            System.out.println(Arrays.toString(numbers));

            String line;
            List<Board> boards = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                // last line read was a blank so let's parse the new board
                Board board = new Board();
                for (int row = 0; row < 5; row++) {
                    String[] rowz = br.readLine().trim().split("\\s+");
                    // System.out.println(Arrays.toString(rowz));
                    for (int col = 0; col < rowz.length; col++) {
                        board.offerNum(Integer.parseInt(rowz[col]), row, col);
                    }
                }
                boards.add(board);
                // System.out.println("newboard");
            }

            for (String num : numbers) {
                for (int i = 0; i < boards.size(); i++) {
                    Board board = boards.get(i);
                    int res = board.bingo(Integer.parseInt(num));
                    if (res != -1) {
                        System.out.println("Board sum: " + board.sum);
                        System.out.println("Bingo num: " + num);
                        System.out.println("We have a winner: " + res);
                        boards.remove(i);
                        i--;
                        if (boards.isEmpty()) {
                            System.out.println("We have a final winner: " + res);
                            return;
                        }
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Board {
        List<Set<Integer>> completedRows = new ArrayList<>();
        List<Set<Integer>> completedCols = new ArrayList<>();

        int sum = 0;

        HashMap<Integer, Pair<Integer, Integer>> numToCoord = new HashMap<>();

        public Board() {
            // init the rows and cols with 5 empties
            for (int i = 0; i < 5; i++) {
                completedCols.add(new HashSet<Integer>());
                completedRows.add(new HashSet<Integer>());
            }
        }
        
        // When given a number we want to return the coord
        public void offerNum(int number, int row, int col) {
            numToCoord.put(number, new Pair<Integer, Integer>(row, col));
            sum += number;
        }

        public int bingo(int number) {
            if (!numToCoord.containsKey(number)) {
                return -1;
            }
            sum -= number;
            Pair<Integer, Integer> coord = numToCoord.get(number);
            int row = coord.getKey();
            int col = coord.getValue();
            completedRows.get(row).add(number);
            completedCols.get(col).add(number);

            if (completedRows.get(row).size() == 5 || completedCols.get(col).size() == 5) {
                return sum * number;
            }
            return -1;
        }
    }
}