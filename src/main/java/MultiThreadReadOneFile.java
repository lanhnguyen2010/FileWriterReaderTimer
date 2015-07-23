import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lanhnguyen on 7/23/2015.
 */
public class MultiThreadReadOneFile {

    public static void main(String[] args) {
        int numsOfThread = 5;
        File file = new File("test.txt");
        for(int i = 1; i<=numsOfThread ; i++){
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
            try {
                customScanner = new CustomScanner(new FileReader(file), '\n', 1);
                int i = 0;
                while (customScanner.hasNext()) {
                    System.out.println(customScanner.next());
                    Thread.sleep(0);
                    i++;
                }
                System.out.println("times: " + i);
                customScanner.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
