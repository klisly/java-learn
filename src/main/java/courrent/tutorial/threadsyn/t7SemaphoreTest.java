package courrent.tutorial.threadsyn;

import java.util.concurrent.Semaphore;

/**
 * Semaphore类是一个计数信号量，必须由获取它的线程释放，
 * 通常用于限制可以访问某些资源（物理或逻辑的）线程数目。
 * <p>
 * <p>
 * 一个信号量有且仅有3种操作，且它们全部是原子的：初始化、增加和减少
 * 增加可以为一个进程解除阻塞；
 * 减少可以让一个进程进入阻塞。
 * <p>
 * --如果朋友您想转载本文章请注明转载地址"http://www.cnblogs.com/XHJT/p/3910406.html "谢谢--
 * <p>
 * 信号量维护一个许可集，若有必要，会在获得许可之前阻塞每一个线程：
 * //从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞。
 * acquireUninterruptibly(int permits){}
 * 每一个release()添加一个许可，从而可能释放一个正在阻塞的获取者。
 * Semaphore只对可用许可的号码进行计数，并采取相应的行动。
 * <p>
 * 如何获得Semaphore对象？
 * public Semaphore(int permits,boolean fair)
 * permits:初始化可用的许可数目。
 * fair: 若该信号量保证在征用时按FIFO的顺序授予许可，则为true，否则为false；
 * <p>
 * 如何从信号量获得许可？
 * public void acquire() throws InterruptedException
 * <p>
 * <p>
 * <p>
 * 如何释放一个许可，并返回信号量？
 * public void release()
 */

/**
 当一个线程想要访问某个共享资源，首先，它必须获得semaphore。如果semaphore的内部计数器的值大于0，
 那么semaphore减少计数器的值并允许访问共享的资源。计数器的值大于0表示，有可以自由使用的资源，所以线程可以访问并使用它们。

 另一种情况，如果semaphore的计数器的值等于0，那么semaphore让线程进入休眠状态一直到计数器大于0。
 计数器的值等于0表示全部的共享资源都正被线程们使用，所以此线程想要访问就必须等到某个资源成为自由的。

 当线程使用完共享资源时，他必须放出semaphore为了让其他线程可以访问共享资源。这个操作会增加semaphore的内部计数器的值。

 在这个指南里，你将学习如何使用Semaphore类来实现一种比较特殊的semaphores种类，称为binary semaphores。
 这个semaphores种类保护访问共享资源的独特性，所以semaphore的内部计数器的值只能是1或者0。为了展示如何使用它，
 你将要实现一个PrintQueue类来让并发任务打印它们的任务。这个PrintQueue类会受到binary semaphore的保护，
 所以每次只能有一个线程可以打印。
 */
//public class t7SemaphoreTest {
//
//    private static final int THREAD_COUNT = 30;
//
//    private static ExecutorService threadPool = Executors
//            .newFixedThreadPool(THREAD_COUNT);
//
//    private static Semaphore s = new Semaphore(10);
//
//    public static void main(String[] args) {
//
//        for (int i = 0; i < THREAD_COUNT; i++) {
//            threadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        s.acquire();
//                        System.out.println(Thread.currentThread().getName()+" save data");
//                        if(s.getQueueLength() % 2 == 0) {
//                            System.out.println(Thread.currentThread().getName()+" sleep data");
//                            Thread.currentThread().sleep(300);
//                        }
//                        s.release();
//                    } catch (InterruptedException e) {
//                    }
//                }
//            });
//        }
//
//        threadPool.shutdown();
//    }
//}


//15. 实现例子的main类，创建名为 Main的类并实现main()方法。
public class t7SemaphoreTest {

    //1.   创建一个会实现print queue的类名为 PrintQueue。
   static class PrintQueue {

        //2.   声明一个对象为Semaphore，称它为semaphore。
        private final Semaphore semaphore;

        //3.   实现类的构造函数并初始能保护print quere的访问的semaphore对象的值。
        public PrintQueue() {
            semaphore = new Semaphore(3);
        }

        //4.   实现Implement the printJob()方法，此方法可以模拟打印文档，并接收document对象作为参数。
        public void printJob(Object document) {

//5.   在这方法内，首先，你必须调用acquire()方法获得demaphore。这个方法会抛出 InterruptedException异常，使用必须包含处理这个异常的代码。
            try {
                semaphore.acquire();

//6.   然后，实现能随机等待一段时间的模拟打印文档的行。
                long duration = (long) (Math.random() * 10);
                System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
                Thread.sleep(duration);

//7.	最后，释放semaphore通过调用semaphore的relaser()方法。
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }

        }
    }


    //8.   创建一个名为Job的类并一定实现Runnable 接口。这个类实现把文档传送到打印机的任务。
   static class Job implements Runnable {

        //9.   声明一个对象为PrintQueue，名为printQueue。
        private PrintQueue printQueue;

        //10. 实现类的构造函数，初始化这个类里的PrintQueue对象。
        public Job(PrintQueue printQueue) {
            this.printQueue = printQueue;
        }

        //11. 实现方法run()。
        @Override
        public void run() {

//12. 首先， 此方法写信息到操控台表明任务已经开始执行了。
            System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());

//13. 然后，调用PrintQueue 对象的printJob()方法。
            printQueue.printJob(new Object());

//14. 最后， 此方法写信息到操控台表明它已经结束运行了。
            System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
        }
    }
    public static void main(String args[]) {

//16. 创建PrintQueue对象名为printQueue。
        PrintQueue printQueue = new PrintQueue();

//17. 创建10个threads。每个线程会执行一个发送文档到print queue的Job对象。
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

//18. 最后，开始这10个线程们。
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}