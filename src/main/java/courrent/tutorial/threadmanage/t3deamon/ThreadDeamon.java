package courrent.tutorial.threadmanage.t3deamon;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 通过直接继承thread类，然后覆盖run()方法。
 * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
 */
public class ThreadDeamon {
    public static void main(String[] args) {
        Deque<Event> deque = new LinkedBlockingDeque<Event>();
        WorkerTask task = new WorkerTask(deque);
        for(int i = 0; i < 3; i++){
            Thread thread = new Thread(task);
            thread.start();
        }

        DeamonTask deamonTask = new DeamonTask(deque);
        deamonTask.start();
    }

    /**
     * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
     */
    static class WorkerTask implements Runnable {
        Deque deque;

        public WorkerTask(Deque<Event> deque) {
            this.deque = deque;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Event event = new Event();
                event.setDate(new Date());
                event.setEvent(String.format("The thread %s has generated an   event", Thread.currentThread().getId()));
                deque.addFirst(event);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Event {
        Date date;
        String event;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }
    }

    static class DeamonTask extends Thread{
        private Deque<Event> deque;

        public DeamonTask(Deque<Event> deque) {
            this.deque = deque;
            setDaemon(true);
        }

        @Override
        public void run() {
            super.run();
            while (true){
                Date date = new Date();
                clean(date);
            }
        }

        private void clean(Date date) {
            long difference;
            boolean delete;
            if (deque.size()==0) {
                return;
            }
            delete=false;
            do {
                Event e = deque.getLast();
                difference = date.getTime() - e.getDate().getTime();
                if (difference > 10000) {
                    System.out.printf("Cleaner: %s\n",e.getEvent()); deque.removeLast();
                    delete=true;
                }
            } while (difference > 10000);
            if (delete){
                System.out.printf("Cleaner: Size of the queue: %d\n",deque. size());
            }
        }
    }
}