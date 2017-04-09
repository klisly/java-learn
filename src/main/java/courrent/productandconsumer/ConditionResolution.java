package courrent.productandconsumer;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class ConditionResolution {
    public static void main(String[] args) {
        Queue queue = new Queue();
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Producer(queue));
        service.submit(new Producer(queue));
        service.submit(new Consumer(queue));
        service.submit(new Consumer(queue));
        service.submit(new Consumer(queue));
        service.submit(new Consumer(queue));
        service.submit(new Consumer(queue));

    }

    private static class Producer implements Runnable{
        private Queue queue;
        int count = 0;

        public Producer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (count < 1000){
                queue.put();
                try {
                    Thread.sleep(queue.random.nextInt(10) * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }
    }

    private static class Consumer implements Runnable{
        private Queue queue;
        int count = 0;

        public Consumer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (count < 1000){
                queue.get();
                try {
                    Thread.sleep(queue.random.nextInt(10) * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }
    }

    private static class Queue {
        private int queueSize = 10;
        public Random random = new Random();
        private ReentrantLock lock = new ReentrantLock();
        private Condition full = lock.newCondition();
        private Condition empty = lock.newCondition();
        private PriorityQueue<Integer> queue = new PriorityQueue<>();

        public void put() {
            lock.lock();
            try {
                while (queue.size() >= queueSize) {
                    System.out.println(Thread.currentThread().getName()+" queue is full wait");
                    try {
                        full.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"  produce a num");
                queue.offer(random.nextInt(1000));
                empty.signal();
            } finally {
                lock.unlock();
            }

        }

        public void get() {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    System.out.println(Thread.currentThread().getName()+" queue is empty wait");
                    try {
                        empty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"  consumer a num");
                queue.poll();
                full.signal(); // 通知
            } finally {
                lock.unlock();
            }

        }
    }
}
