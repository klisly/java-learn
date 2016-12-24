package courrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors
            .newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println("save data");
                        if(s.getQueueLength() % 2 == 0) {
                            System.out.println("sleep data");
                            Thread.currentThread().sleep(300);
                        }
                        s.release();
                    } catch (InterruptedException e) {
                    }
                }
            });
        }

        threadPool.shutdown();
    }
}