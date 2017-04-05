package collection.concurrent.atmoic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wizardholy on 2017/4/1.
 */
public class AtomicReferenceABATest {
    public final static AtomicReference<String> ATOMIC_REFERENCE = new AtomicReference<String>("abc");

    /**
     什么是ABA问题呢，当某些流程在处理过程中是顺向的，也就是不允许重复处理的情况下，在某些情况下导致一个数据由A变成B，
     再中间可能经过0-N个环节后变成了A，此时A不允许再变成B了，因为此时的状态已经发生了改变，
     例如：银行资金里面做一批账目操作，要求资金在80-100元的人，增加20元钱，时间持续一天，
     也就是后台程序会不断扫描这些用户的资金是否是在这个范围，但是要求增加过的人就不能再增加了，
     如果增加20后，被人取出10元继续在这个范围，那么就可以无限套现出来，就是ABA问题了，
     类似的还有抢红包或中奖，比如每天每个人限量3个红包，中那个等级的奖的个数等等。

     此时我们需要使用的方式就不是简单的compareAndSet操作，因为它仅仅是考虑到物理上的并发，而不是在业务逻辑上去控制顺序，
     此时我们需要借鉴数据库的事务序列号的一些思想来解决，假如每个对象修改的次数可以记住，修改前先对比下次数是否一致再修改，
     那么这个问题就简单了，AtomicStampedReference类正是提供这一功能的，其实它仅仅是在AtomicReference类的再一次包装，
     里面增加了一层引用和计数器，其实是否为计数器完全由自己控制，大多数我们是让他自增的，你也可以按照自己的方式来标示版本号，
     * @param args
     */
    public static void main(String []args) {
        for(int i = 0 ; i < 100 ; i++) {
            final int num = i;
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(Math.abs((int)(Math.random() * 100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ATOMIC_REFERENCE.compareAndSet("abc" , "abc2")) {
                        System.out.println(" abc2 我是线程：" + num + ",我获得了锁进行了对象修改！");
                    }
                }
            }.start();
        }

        new Thread() {
            public void run() {
                while(!ATOMIC_REFERENCE.compareAndSet("abc2", "abc"));
                System.out.println("已经改为原始值！");
            }
        }.start();
    }
}
