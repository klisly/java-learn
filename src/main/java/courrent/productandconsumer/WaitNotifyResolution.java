package courrent.productandconsumer;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class WaitNotifyResolution {
    public static void main(String[] args) {
        Queue queue = new Queue();
        ExecutorService service = Executors.newCachedThreadPool();
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
        private PriorityQueue<Integer> queue = new PriorityQueue<>();

        public synchronized void put() {
            try {
                while (queue.size() > queueSize) {
                    System.out.println(Thread.currentThread().getName()+" queue is full wait");
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  produce a num");
            queue.offer(random.nextInt(1000));
            notify();
        }

        public synchronized void get() {
            try {
                while (queue.size() == 0) {
                    System.out.println(Thread.currentThread().getName()+" queue is empty wait");
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  consumer a num");
            queue.poll();
            notify();
        }
    }
}
