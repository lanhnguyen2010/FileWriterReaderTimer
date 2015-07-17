import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhnguyen on 7/17/2015.
 */
public class FileWriterTimer {

    public static final String TEST_WRITER = "testWriter.txt";

    public static void main(String[] args) throws IOException {
        List<String> records = new ArrayList<String>();

        for (int i = 1; i < 2000000; i++) {
            records.add("This is row : " + i);
        }

        long start = System.currentTimeMillis();
        writeRaw(records);
        long finnishRaw = System.currentTimeMillis();
        writeFilebyPrintWriter(records);
        long finnishPrint = System.currentTimeMillis();
        writeByOutputStream(records);
        long finnishStream = System.currentTimeMillis();
        System.out.println("time Writer :" + (finnishRaw - start));
        System.out.println("time PrintWriter :" + (finnishPrint - finnishRaw));
        System.out.println("time OutputStream :" + (finnishStream - finnishRaw));

    }

    private static void writeRaw(List<String> records) throws IOException {
        File file = new File(TEST_WRITER);
        try {
            FileWriter writer = new FileWriter(file);
            for (String record : records) {
                writer.write(record);
            }
        } finally {
            // comment this out if you want to inspect the files afterward
            file.delete();
        }
    }

    private static void writeFilebyPrintWriter(List<String> records) {
        File file = new File(TEST_WRITER);
        try {
            PrintWriter writer = new PrintWriter(file);
            for (String record : records) {
                writer.write(record);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // comment this out if you want to inspect the files afterward
            file.delete();
        }
    }

    private static void writeByOutputStream(List<String> records) {
        File file = new File(TEST_WRITER);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            for (String record : records) {
                byte[] output = record.getBytes();
                outputStream.write(output);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // comment this out if you want to inspect the files afterward
            file.delete();
        }


    }

}
