package courrent.tutorial.threadsyn;

//- 使用synchronized/wait()实现生产者消费者模式如下：

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class t6_1SynWait {
    public static void main(String[] arg){
        Buffer buffer = new Buffer(30);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
         //创建线程执行生产和消费
        for(int i=0;i<3;i++){
            new Thread(producer,"producer-"+i).start();
        }
        for(int i=0;i<3;i++){
            new Thread(consumer,"consumer-"+i).start();
        }
    }


    static class Buffer {
        private int maxSize;
        private List<Date> storage;

        public Buffer(int maxSize) {
            this.maxSize = maxSize;
            storage = new ArrayList<Date>();
        }

        public synchronized void put() {
            try {

                while (storage.size() == maxSize) {
                    System.out.println(Thread.currentThread().getName() + " wait to put");
                    wait();
                }
                storage.add(new Date());
                Thread.sleep(100);
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void take() {
            try {

                while (storage.size() == 0) {
                    System.out.println(Thread.currentThread().getName() + " wait to take");
                    wait();
                }
                storage.remove(0);
                Thread.sleep(100);
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 生产者
    static class Producer implements Runnable{
        private Buffer buffer;

        public Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true){
                buffer.put();
            }
        }
    }

    // 生产者
    static class Consumer implements Runnable{
        private Buffer buffer;

        public Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true){
                buffer.take();
            }
        }
    }
}


