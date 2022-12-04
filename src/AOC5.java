import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AOC5 {

    private static Scanner scanner;

    public static void main(String[] args) throws FileNotFoundException {
       scanner = AocTemplate.getScanner(args[0]);
        List<String> input = new ArrayList<>();

       while (scanner.hasNextLine()){
           input.add(scanner.nextLine());
       }
       aoc5a(input);


    }

    private static void aoc5a(List<String> in){
        int highest = 0;
        PriorityQueue<Integer> part2 = new PriorityQueue<>();
        for (String s : in){
            String s1 = s.substring(0,7);
            String s2 = s.substring(7);
            int row = binSearch(0, 127, s1);
            int col = binSearch(0, 7, s2);
            int ID = 8 * row + col;
            highest = Math.max(highest, ID);
            if (!(ID <= 7 && 127 * 8 <= ID)){
                part2.add(ID);
            }
        }
        System.out.println("Part 1: " + highest);

        int myID = -1;
        while (!part2.isEmpty() && myID < 0){
            int id1 = part2.poll();
            int id2 = part2.peek();
            if (Math.abs(id1 - id2) == 2){
                myID = Math.min(id1, id2) + 1;
            }
        }

        System.out.println("Part 2: " + myID);
    }


    /*
    * ID FRONT := ID <= [0, 7]  (0 * 8 + c)
    * ID BACK  := 127 * 8 + 0 <= ID
    * */

    private static int binSearch(int low, int high, String instructs){

        for (int i = 0; i < instructs.length(); i++) {
            char inst = instructs.charAt(i);
            if (inst == 'F' || inst == 'L') {
                high = (high - low) / 2 + low;
            } else {
                low = high - (high - low) / 2;
            }
        }
        char last = instructs.charAt(instructs.length() - 1);

        return last == 'F' || last == 'L' ? low : high;
    }
}
