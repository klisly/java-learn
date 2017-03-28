package courrent.tutorial.threadsyn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 一个锁可能伴随着多个条件。这些条件声明在Condition接口中。
 这些条件的目的是允许线程拥有锁的控制并且检查条件是否为true，
 如果是false，那么线程将被阻塞，直到其他线程唤醒它们。
 Condition接口提供一种机制，阻塞一个线程和唤醒一个被阻塞的线程。

 在并发编程中，生产者与消费者是经典的问题。我们有一个数据缓冲区，
 一个或多个数据生产者往缓冲区存储数据，一个或多个数据消费者从缓冲区中取出数据，
 正如在这一章中前面所解释的一样。

 Condition
 那么引入本篇的主角，Condition，Condition 将 Object的通信方法（wait、notify 和 notifyAll）分解成截然不同的对象，
 以便通过将这些对象与任意 Lock 实现组合使用，为每个对象提供多个等待 set （wait-set）。
 其中，Lock 替代了 synchronized 方法和语句的使用，Condition 替代了 Object 通信方法的使用。

 在Condition中，用await()替换wait()，用signal()替换notify()，
 用signalAll()替换notifyAll()，传统线程的通信方式，Condition都可以实现，
 这里注意，Condition是被绑定到Lock上的，要创建一个Lock的Condition必须用
 newCondition()方法。

 Condition的强大之处在于它可以为多个线程间建立不同的Condition，
 使用synchronized/wait()只有一个阻塞队列，notifyAll会唤起所有阻塞队列下的线程，
 而使用lock/condition，可以实现多个阻塞队列，signalAll只会唤起某个阻塞队列下的阻塞线程。


 所 有Condition对象都与锁有关，并且使用声明在Lock接口中的newCondition()方法来创建。使用condition做任何操作之前， 你必须获取与这个condition相关的锁的控制。所以，condition的操作一定是在以调用Lock对象的lock()方法为开头，以调用相同 Lock对象的unlock()方法为结尾的代码块中。

 当一个线程在一个condition上调用await()方法时，它将自动释放锁的控制，所以其他线程可以获取这个锁的控制并开始执行相同操作，或者由同个锁保护的其他临界区。

 注释：当一个线程在一个condition上调用signal()或signallAll()方法，一个或者全部在这个condition上等待的线程将被唤醒。这并不能保证的使它们现在睡眠的条件现在是true，所以你必须在while循环内部调用await()方法。你不能离开这个循环，直到 condition为true。当condition为false，你必须再次调用 await()方法。

 你必须十分小心 ，在使用await()和signal()方法时。如果你在condition上调用await()方法而却没有在这个condition上调用signal()方法，这个线程将永远睡眠下去。

 在调用await()方法后，一个线程可以被中断的，所以当它正在睡眠时，你必须处理InterruptedException异常。

 不止这些…

 Condition接口提供不同版本的await()方法，如下：

 await(long time, TimeUnit unit):这个线程将会一直睡眠直到：
 (1)它被中断

 (2)其他线程在这个condition上调用singal()或signalAll()方法

 (3)指定的时间已经过了

 (4)TimeUnit类是一个枚举类型如下的常量：

 DAYS,HOURS, MICROSECONDS, MILLISECONDS, MINUTES, NANOSECONDS,SECONDS

 awaitUninterruptibly():这个线程将不会被中断，一直睡眠直到其他线程调用signal()或signalAll()方法
 awaitUntil(Date date):这个线程将会一直睡眠直到：
 (1)它被中断

 (2)其他线程在这个condition上调用singal()或signalAll()方法

 (3)指定的日期已经到了

 你可以在一个读/写锁中的ReadLock和WriteLock上使用conditions。



 */

public class ThreadTest2 {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadExecute(business, "sub");
            }
        }).start();
        threadExecute(business, "main");
    }
    public static void threadExecute(Business business, String threadType) {
        for(int i = 0; i < 1000; i++) {
            try {
                if("main".equals(threadType)) {
                    business.main(i);
                } else {
                    business.sub(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Business {
    private boolean bool = true;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public /*synchronized*/ void main(int loop) throws InterruptedException {
        lock.lock();
        try {
            while(bool) {
                condition.await();//this.wait();
            }
            for(int i = 0; i < 100; i++) {
                System.out.println("main thread seq of " + i + ", loop of " + loop);
            }
            bool = true;
            condition.signal();//this.notify();
        } finally {
            lock.unlock();
        }
    }
    public /*synchronized*/ void sub(int loop) throws InterruptedException {
        lock.lock();
        try {
            while(!bool) {
                condition.await();//this.wait();
            }
            for(int i = 0; i < 10; i++) {
                System.out.println("sub thread seq of " + i + ", loop of " + loop);
            }
            bool = false;
            condition.signal();//this.notify();
        } finally {
            lock.unlock();
        }
    }
}
