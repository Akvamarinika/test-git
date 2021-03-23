import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        poolThread();
        System.out.println(System.currentTimeMillis() - timeStart);

    }

    public static void poolThread(){
        ExecutorService service = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                long s = 0;
                for (int i = 0; i < 1_000_000; i++){
                    if (i % 2 == 0){ s += i;}
                }
                System.out.println("sum even numbers: " + s);
                countDownLatch.countDown();
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                long s = 0;
                for (int i = 0; i < 1_000_000; i++){
                    if (i % 7 == 0){ s += i;}
                }
                System.out.println("sum dev on 7: " + s);
                countDownLatch.countDown();
            }
        };

        Runnable task3 = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                int[] array = new int[1000];
                for (int i = 0; i < array.length; i++){
                    int num = (int) Math.round(Math.random() * 1000);
                    array[i] = num;
                }

                for (int value : array) {
                    if (value % 2 == 0) {
                        count++;
                    }
                }
                System.out.println("count even numbers in arrays: " + count);
                countDownLatch.countDown();
            }
        };

        service.execute(task1);
        service.execute(task2);
        service.execute(task3);
        service.shutdown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
