
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class AOC4 {

    private static Scanner scanner;
    private static final String[] KEY_SET = new String[]{
            "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"
    };

    // TODO FIX BUG: input.txt needs to end with 2 newlines
    public static void main(String[] args) throws FileNotFoundException {
        scanner = AocTemplate.getScanner(args[0]);
        List<Map<String, String>> passports = new ArrayList<>();

        Map<String, String> newPassport = new HashMap<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.equals("")){
                passports.add(newPassport);
                newPassport = new HashMap<>();
            } else {
                String[] lines = line.split(" ");
                for (String kv : lines){
                    String[] kvs = kv.split(":");
                    newPassport.put(kvs[0], kvs[1]);
                }
            }
        }
        aoc4a(passports);

        List<Map<String, String>> validPassports = filterPassports(passports);

        aoc4b(validPassports);
    }

    // cid in KEY_SET is optional
    private static void aoc4a(List<Map<String, String>> passports){
        int valids = 0;

        for (Map<String, String> pp : passports){
            valids = validFields(pp) ? valids + 1 : valids;
        }

        System.out.println("Part 1: " + valids);
    }

    private static void aoc4b(List<Map<String, String>> passports){
        System.out.println(passports.size());
        int valids = 0;
        for (Map<String, String> pp : passports){
            try {
                valids = validFormats(pp) ? valids + 1 : valids;
            } catch (Exception e) {

            }
        }

        System.out.println("Part 2: " + valids);
    }

    private static boolean validFields(Map<String, String> passport){
        boolean invalid = false;
        for (String k : KEY_SET){
            if (passport.get(k) == null){
                invalid = invalid || !k.equals("cid");
            }
        }
        return !invalid;
    }

    private static List<Map<String, String>> filterPassports(List<Map<String, String>> passports){
        List<Map<String, String>> valids = new ArrayList<>();
        for (Map<String, String> pp : passports){
            if (validFields(pp)){
                valids.add(pp);
            }
        }
        return valids;
    }

    // TODO fix bugs

    private static boolean validFormats(Map<String, String> passport) throws Exception{
        int bYear = Integer.parseInt(passport.get("byr"));
        int iYear = Integer.parseInt(passport.get("iyr"));
        int eyr   = Integer.parseInt(passport.get("eyr"));
        String measure = passport.get("hgt").substring(0,2);
        int hValue = Integer.parseInt(passport.get("hgt").substring(2));
        String hcl = passport.get("hcl").substring(1);
        int hclVal = Integer.parseInt(hcl, 16);
        String ecl = passport.get("ecl");
        String pid = passport.get("pid");
        Set<String> validClrs = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");


        if (bYear < 1920 || 2002 < bYear){
            return false;
        }
        else if (iYear < 2010 || 2020 < iYear){
            return false;
        }
        else if (eyr < 2020 || 2030 < eyr){
            return false;
        }
        else if (measure.equals("cm") ? hValue < 150 || 193 < hValue : hValue < 59 || 76 < hValue){
            return false;
        }
        else if (hcl.length() != 6){
            return false;
        }
        else if (!validClrs.contains(ecl)){
            return false;
        }
        else if (pid.length() != 9){
            return false;
        }
        int doesItGiveAnError = Integer.parseInt(pid);
        return true;


    }
    /*
    *
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
        If cm, the number must be at least 150 and at most 193.
        If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.
    *
    * */
}
