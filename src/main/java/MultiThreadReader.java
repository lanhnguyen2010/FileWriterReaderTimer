import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by lanhnguyen on 7/23/2015.
 */
public class MultiThreadReader {
    public static void main(String[] args) {
        File inputDir = new File("input");
        File[] files = inputDir.listFiles();
        for (File file : files) {

            Thread threadWorker = new Thread(new ThreadWorker(file));
            threadWorker.start();
        }
    }


    public static class ThreadWorker implements Runnable {
        private File file;
        public ThreadWorker(File file) {
            this.file = file;

        }

        @Override
        public void run() {
            long threadID = Thread.currentThread().getId();
            System.out.println("This is thread :" + threadID);
            CustomScanner customScanner = null;
            int i = 0;
            try {
                customScanner = new CustomScanner(new FileReader(file), ',', 1);
                while (customScanner.hasNext()) {
                    System.out.println(customScanner.next());
                    i++;
                }
                System.out.println("times : " + i);
                customScanner.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
