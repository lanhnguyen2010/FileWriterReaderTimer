
import java.io.*;
import java.util.Scanner;

/**
 * Created by lanhnguyen on 7/17/2015.
 *
 */

public class FileReaderTimer {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        readFileByBuffer();
        long startInputStream = System.currentTimeMillis();
        readFileByInputStream();
        long stopInputStream = System.currentTimeMillis();
        readFileByScanner();
        long stopScaner = System.currentTimeMillis();
        readFileByFileReader();
        long finnish = System.currentTimeMillis();

        System.out.println("Time read file by buffer " + (startInputStream - start));
        System.out.println("Time read file by inputstream " + (stopInputStream - startInputStream));
        System.out.println("Time read file by scanner " + (stopScaner - stopInputStream));
        System.out.println("Time read file by reader " + (finnish - stopScaner));


    }

    public static void readFileByBuffer() {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("test.txt"));
            while (bufferedReader.readLine() != null) {
                System.out.println(bufferedReader.readLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void readFileByInputStream() {
        FileInputStream inputStream = null;
        try {
            int content;
            inputStream = new FileInputStream(new File("test.txt"));
            while ((content = inputStream.read()) != -1) {
                System.out.print((char) content);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFileByScanner() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("test.txt"));
            String line;
            while (scanner.hasNextLine() != false) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public static void readFileByFileReader() {
        try (FileReader reader = new FileReader("test.txt")) {
            int character = reader.read();
            while (character != -1) {
                System.out.print((char) character);
                character = reader.read();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
