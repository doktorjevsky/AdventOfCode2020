import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AocTemplate {

    public static Scanner getScanner(String filePath) throws FileNotFoundException {
        File input = new File(filePath);
        return new Scanner(input);
    }

}
