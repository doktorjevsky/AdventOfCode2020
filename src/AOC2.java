import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AOC2 {


    private static Scanner scanner;

    public static void main(String[] args) throws FileNotFoundException {
        scanner = AocTemplate.getScanner(args[0]);
        aoc2a();

    }

    private static void aoc2a(){
        int valids = 0;
        int valids2 = 0;
        while (scanner.hasNextLine()){
            PasswordPolicy p = parseLine(scanner.nextLine());
            if (p.isValid()){
                valids++;
            }
            if (p.isValid2()){
                valids2++;
            }
        }
        System.out.println("Part 1: " + valids);
        System.out.println("Part 2: " + valids2);

    }

    private static PasswordPolicy parseLine(String line){
        String[] parts = line.split(" ");
        String[] bounds = parts[0].split("-");
        String pw = parts[2];
        char target = parts[1].charAt(0);
        int low = Integer.parseInt(bounds[0]);
        int high = Integer.parseInt(bounds[1]);
        return new PasswordPolicy(low, high, target, pw);
    }


    private static class PasswordPolicy {
        int lowBound;
        int highBound;
        char target;
        String password;

        PasswordPolicy(int l, int h, char t, String pw){
            lowBound = l;
            highBound = h;
            target = t;
            password = pw;
        }

        boolean isValid(){
            int count = 0;
            for (int i = 0; i < password.length(); i++){
                if (password.charAt(i) == target){
                    count++;
                }
            }
            return lowBound <= count && count <= highBound;
        }

        boolean isValid2(){
            return password.charAt(lowBound - 1) == target ? password.charAt(highBound - 1) != target : password.charAt(highBound - 1) == target;
        }
    }
}
/*
*
    1-3 a: abcde is valid: position 1 contains a and position 3 does not.
    1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
    2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.

*
* */


