import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AOC3 {


    private static Scanner scanner;

    public static void main(String[] args) throws FileNotFoundException {
        scanner = AocTemplate.getScanner(args[0]);
        aoc3a();

    }

    public static void aoc3a(){
        List<String> area = new ArrayList<>();

        while(scanner.hasNextLine()){
            area.add(scanner.nextLine());
        }

        // part 1
        long t1 = treeEncounter(3, 1, area);
        System.out.println("Part 1: " + t1);

        // part 2
        long t2 = treeEncounter(1, 1, area);
        long t3 = treeEncounter(5, 1, area);
        long t4 = treeEncounter(7, 1, area);
        long t5 = treeEncounter(1, 2, area);

        System.out.println("Part 2: " + (t1 * t2 * t3 * t4 * t5));

    }

    private static long treeEncounter(int dx, int dy, List<String> area){
        long count = 0;
        int x = 0;
        for (int y = 0; y < area.size(); y += dy){
            String row = area.get(y);
            if (row.charAt(x % row.length()) == '#'){
                count++;
            }
            x += dx;
        }
        return count;
    }
}
