
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AOC1 {

    private static Scanner scanner;

    public static void main(String[] args) throws FileNotFoundException {
        scanner = AocTemplate.getScanner(args[0]);
        aoc1a();

    }

    public static void aoc1a(){
        List<Integer> entries = new ArrayList<>();

        while (scanner.hasNext()){
            entries.add(scanner.nextInt());
        }

        boolean found = false;

        for (int i = 0; i < entries.size(); i++){
            if (found){
                break;
            }
            for (int j = 0; j < entries.size(); j++){
                if (j != i && entries.get(i) + entries.get(j) == 2020){
                    System.out.println("Part 1: " + entries.get(i) * entries.get(j));
                    found = true;
                }
            }
        }
        found = false;

        for (int i = 0; i < entries.size(); i++){
            if (found){
                break;
            }
            for (int j = 0; j < entries.size(); j++){
                if (found){
                    break;
                }
                for (int k = 0; k < entries.size(); k++){
                    int n1 = entries.get(i);
                    int n2 = entries.get(j);
                    int n3 = entries.get(k);
                    if (i != j && j != k && n1 + n2 + n3 == 2020){
                        System.out.println("Part 2: " + (n1*n2*n3));
                        found = true;
                    }
                }
            }
        }
    }
}
