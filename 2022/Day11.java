import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day11 {
    public static void doDay11() {
        try {
            File file = new File("input/input11.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            // Use a queue to store all the instructions, it can be integer
            // Use another integer to store the cycle and result
            HashMap<Integer, Monkey> monkeys = new HashMap<>();
            String line;
            int amtOfMonkeys = 0;
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split(" ");
                if ("Monkey".equals(splittedLine[0])) {
                    int number = Integer.parseInt("" + splittedLine[1].charAt(0));
                    Monkey monkey = parseMonkey(br);
                    monkey.number = number;
                    monkeys.put(number, monkey);
                    amtOfMonkeys = number + 1;
                }
            }
            // System.out.println(monkeys);
            int rounds = 20;
            for (int round = 1; round <= rounds; round++) {
                for (int monkeyNum = 0; monkeyNum < amtOfMonkeys; monkeyNum++) {
                    Monkey monkey = monkeys.get(monkeyNum);
                    while (!monkey.items.isEmpty()) {
                        long resultFromInspection = monkey.inspect(monkey.items.poll()) / 3;
                        if (resultFromInspection % monkey.moduloValue == 0) {
                            monkeys.get(monkey.trueMonkey).items.offer(resultFromInspection);
                        } else {
                            monkeys.get(monkey.falseMonkey).items.offer(resultFromInspection);
                        }
                    }
                }
            }
            List<Integer> inspectionList = monkeys.values().stream()
                    .map(m -> m.inspectCount)
                    .collect(Collectors.toList());
            inspectionList.sort((a, b) -> b - a);
            System.out.println(inspectionList);
            System.out.println("Part 1: " + (inspectionList.get(0) * inspectionList.get(1)));
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doDay11Part2() {
        try {
            File file = new File("input/input11.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            // Use a queue to store all the instructions, it can be integer
            // Use another integer to store the cycle and result
            HashMap<Integer, Monkey> monkeys = new HashMap<>();
            String line;
            int amtOfMonkeys = 0;
            long modulo = 1;
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split(" ");
                if ("Monkey".equals(splittedLine[0])) {
                    int number = Integer.parseInt("" + splittedLine[1].charAt(0));
                    Monkey monkey = parseMonkey(br);
                    monkey.number = number;
                    monkeys.put(number, monkey);
                    amtOfMonkeys = number + 1;
                    modulo *= monkey.moduloValue;
                }
            }
            // modulo += 1;
            // System.out.println(monkeys);
            int rounds = 10000;
            // Take the highest moduloVal and apply that to the item each time?
            for (int round = 1; round <= rounds; round++) {
                for (int monkeyNum = 0; monkeyNum < amtOfMonkeys; monkeyNum++) {
                    Monkey monkey = monkeys.get(monkeyNum);
                    while (!monkey.items.isEmpty()) {
                        long resultFromInspection = monkey.inspect(monkey.items.poll()) % modulo;
                        if (resultFromInspection % monkey.moduloValue == 0) {
                            monkeys.get(monkey.trueMonkey).items.offer(resultFromInspection);
                        } else {
                            monkeys.get(monkey.falseMonkey).items.offer(resultFromInspection);
                        }
                    }
                }
            }
            List<Long> inspectionList = monkeys.values().stream()
                    .map(m -> (long)m.inspectCount)
                    .collect(Collectors.toList());
            inspectionList.sort((a, b) -> (int)(b - a));
            System.out.println(inspectionList);
            System.out.println("Part 2: " + (inspectionList.get(0) * inspectionList.get(1)));
            fr.close();    //closes the stream and release the resources
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doDay11();
        doDay11Part2();
    }

    private static Monkey parseMonkey(BufferedReader br) throws IOException {
        Monkey monkey = new Monkey();
        String[] startingItems = br.readLine().split(":")[1].split("," );
        for (String item : startingItems) {
            monkey.items.offer((long)Integer.parseInt(item.trim()));
        }
        String[] operation = br.readLine().split(":")[1].trim().split(" ");
        if ("old".equals(operation[4])) {
            monkey.operation = "double";
        } else {
            monkey.operation = operation[3];
            monkey.operationValue = Integer.parseInt(operation[4]);
        }
        String[] test = br.readLine().trim().split(" ");
        monkey.moduloValue = Integer.parseInt(test[3]);
        String[] trueMonkey = br.readLine().trim().split(" ");
        monkey.trueMonkey = Integer.parseInt(trueMonkey[5]);
        String[] falseMonkey = br.readLine().trim().split(" ");
        monkey.falseMonkey = Integer.parseInt(falseMonkey[5]);
        return monkey;
    }

    private static class Monkey {
        int number = 0;
        Deque<Long> items = new LinkedList<>();
        String operation = "";
        int operationValue = 0;
        int moduloValue = 0;
        int trueMonkey = 0;
        int falseMonkey = 0;
        int inspectCount = 0;

        @Override
        public String toString() {
            return String.format("num: %s, items: %s, operation: %s, operationValue: %s, modulo: %s, trueMOnkey: %s, falseMonkey: %s",
            number, items, operation, operationValue, moduloValue, trueMonkey, falseMonkey);
        }

        public long inspect(long item) {
            switch (operation) {
                case "double":
                    item *= item;
                    break;
                case "+":
                    item += operationValue;
                    break;
                case "-":
                    item -= operationValue;
                    break;
                case "*":
                    item *= operationValue;
                    break;
                case "/":
                default:
                    item /= operationValue;
                    break;
            }
            inspectCount++;
            return item;
        }
    }
}
