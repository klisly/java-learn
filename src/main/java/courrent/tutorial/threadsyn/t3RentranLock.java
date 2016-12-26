package courrent.tutorial.threadsyn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 你必须要非常小心使用锁来避免死锁，这种情况发生在，当两个或两个以上的线程被阻塞等待将永远不会解开的锁。
 比如，线程A锁定Lock(X)而线程B锁定 Lock(Y)。
 */
public class t3RentranLock {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }

    static class Job implements Runnable {
        private PrintQueue printQueue;

        public Job(PrintQueue printQueue) {
            this.printQueue = printQueue;
        }

        @Override
        public void run() {
            System.out.printf("%s: Going to print a document\n", Thread.
                    currentThread().getName());
            printQueue.printJob(new Object());
            System.out.printf("%s: The document has been printed\n",
                    Thread.currentThread().getName());
        }
    }

    static class PrintQueue {
        private final Lock queueLock = new ReentrantLock();

        public void printJob(Object docment) {
            queueLock.lock();
            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during " + (duration / 1000) +
                        " seconds");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        }
    }
}