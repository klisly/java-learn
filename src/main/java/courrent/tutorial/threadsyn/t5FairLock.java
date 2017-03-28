package courrent.tutorial.threadsyn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 在ReentrantLock类和 ReentrantReadWriteLock类的构造器中，允许一个名为fair的boolean类型参数，
 它允许你来控制这些类的行为。默认值为 false，这将启用非公平模式。在这个模式中，当有多个线程正在
 等待一把锁（ReentrantLock或者 ReentrantReadWriteLock），这个锁必须选择它们中间的一个
 来获得进入临界区，选择任意一个是没有任何标准的。

 true值将开启公平 模式。在这个模式中，
 当有多个线程正在等待一把锁（ReentrantLock或者ReentrantReadWriteLock），这个锁
 必须选择它们 中间的一个来获得进入临界区，它将选择等待时间最长的线程。

 考虑到之前解
 释的行为只是使用lock()和unlock()方法。由于tryLock()方 法并不会使线程进入睡眠，
 即使Lock接口正在被使用，这个公平属性并不会影响它的功能。
 */
public class t5FairLock {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        private final Lock queueLock = new ReentrantLock(true);

        public void printJob(Object document){
            queueLock.lock();
            try {
                Long duration=(long)(Math.random()*10000);
                System.out.println(Thread.currentThread().getName()+": PrintQueue: Printing a Job during "+(duration/1000)+" seconds");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
            queueLock.lock();
            try {
                Long duration=(long)(Math.random()*10000);
                System.out.println(Thread.currentThread().getName()+": PrintQueue: Printing a Job during "+(duration/1000)+" seconds");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        }
    }
}