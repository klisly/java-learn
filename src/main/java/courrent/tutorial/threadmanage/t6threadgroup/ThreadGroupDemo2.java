package courrent.tutorial.threadmanage.t6threadgroup;

import java.net.SocketException;

/**
 * Created by wizardholy on 2016/12/25.
 */
public class ThreadGroupDemo2 {
    public static void main(String[] args) {
        ThreadGroup spiderGroup = new SpiderThreadGroup("spiderGroup");
        //可以统一设定线程是否为守护线程
        spiderGroup.setDaemon(true);

        //可以设置线程组内的最大优先级
        spiderGroup.setMaxPriority(Thread.NORM_PRIORITY);

        //初始化线程
        Thread spiderThread = new Thread(spiderGroup, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException(new SocketException());
            }

        });

        //启动线程
        spiderThread.start();
    }


    /**
     * 此类从ThreadGroup类继承重写了其uncaughtException方法，对于SocketException进行了特殊处理
     *
     * @author outofmemory.cn
     */
    static class SpiderThreadGroup extends ThreadGroup {
        public SpiderThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            if (e.getCause() instanceof SocketException) {
                System.out.println("socket exception should be process");
            } else {
                super.uncaughtException(t, e);
            }
        }
    }
}
