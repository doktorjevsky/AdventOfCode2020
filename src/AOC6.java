import java.io.FileNotFoundException;
import java.util.*;

public class AOC6 {


    private static Scanner scanner;


    public static void main(String[] args) throws FileNotFoundException {
        scanner = AocTemplate.getScanner(args[0]);
        List<Set<String>> answers = new ArrayList<>();
        Set<String> set = new HashSet<>();

        while(scanner.hasNextLine()){
            String row = scanner.nextLine();
            if (row.equals("")){
                answers.add(set);
                set = new HashSet<>();
            } else {
                set.add(row);
            }
        }
        aoc6a(answers);
        aoc6b(answers);

    }

    private static void aoc6a(List<Set<String>> list){
        int sumAns = 0;
        for (Set<String> set : list){
            Set<Character> dummy = new HashSet<>();
            for (String s : set){
                for (int i = 0; i < s.length(); i++){
                    dummy.add(s.charAt(i));
                }

            }
            sumAns += dummy.size();
        }

        System.out.println("Part 1: " + sumAns);
    }

    private static void aoc6b(List<Set<String>> list){
        int same = 0;
        for (Set<String> set : list){
            List<Set<Character>> dummy = new ArrayList<>();
            for (String s : set){
                dummy.add(charSet(s));
            }
            Set<Character> dummySet = dummy.get(0);
            for (int i = 1; i < dummy.size(); i++){
                dummySet = union(dummySet, dummy.get(i));
            }
            same += dummySet.size();
        }
        System.out.println("Part 2: " + same);

    }

    private static Set<Character> union(Set<Character> s1, Set<Character> s2){
        Set<Character> out = new HashSet<>();
        for (Character c : s1){
            if (s2.contains(c)){
                out.add(c);
            }
        }
        return out;
    }

    private static Set<Character> charSet(String s){
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            set.add(s.charAt(i));
        }
        return set;
    }
}
