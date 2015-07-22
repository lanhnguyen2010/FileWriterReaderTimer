import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lanhnguyen on 7/22/2015.
 */
public class CustomScannerFileReader {
    public static void main(String[] args) throws IOException {
        CustomScanner customScanner = new CustomScanner(new FileReader("scanner.txt"), ',', 1);
        FileReader fileReader = new FileReader("scanner.txt");
        while (customScanner.hasNext()) {
            System.out.println(customScanner.next());
        }
    }
}
