package collection.list;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by wizardholy on 2017/4/8.
 */
public class CopyOnWriteListDemo {


        /**
         * 内容编号
         */
        private static AtomicLong contentNum;

        /**
         * 日期格式器
         */
        private static DateFormat format;

        /**
         * 线程池
         */
        private final ExecutorService threadPool;

        public CopyOnWriteListDemo() {
            contentNum = new AtomicLong();
            format = new SimpleDateFormat("HH:mm:ss");
            threadPool = Executors.newFixedThreadPool(10);
        }

        public void doExec(int num) throws InterruptedException {
            List<String> list = new CopyOnWriteArrayList<>();
            for (int i = 0; i < num; i++){
                list.add(i,"main-content-" + i);
            }
            //5个写线程
            for (int i = 0; i < 5; i++){
                threadPool.execute(new Writer(list,i));
            }
            //启动10个读线程
            for (int i = 0; i < 10; i++){
                threadPool.execute(new Reader(list));
            }
            //关闭线程池
            threadPool.shutdown();
        }

        /**
         * 写线程
         *
         * @author rhwayfun
         */
        static class Writer implements Runnable {

            private final List<String> copyOnWriteArrayList;
            private int i;

            public Writer(List<String> copyOnWriteArrayList,int i) {
                this.copyOnWriteArrayList = copyOnWriteArrayList;
                this.i = i;
            }

            @Override
            public void run() {
                copyOnWriteArrayList.add(i,"content-" + contentNum.incrementAndGet());
                System.out.println(Thread.currentThread().getName() + ": write content-" + contentNum.get()
                        + " " +format.format(new Date()));
                System.out.println(Thread.currentThread().getName() + ": remove " + copyOnWriteArrayList.remove(i));
            }
        }

        static class Reader implements Runnable {

            private final List<String> list;

            public Reader(List<String> list) {
                this.list = list;
            }

            @Override
            public void run() {
                for (String s : list) {
                    System.out.println(Thread.currentThread().getName() + ": read " + s
                            + " " +format.format(new Date()));
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            CopyOnWriteListDemo demo = new CopyOnWriteListDemo();
            demo.doExec(5);
        }
}
