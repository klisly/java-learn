package courrent.tutorial.threadmanage.t6threadgroup;

/**
 * Created by wizardholy on 2016/12/25.
 */
public class ThreadGroupDemo {
    public static void main(String[] args) {
        printGroupInfo(Thread.currentThread());

        Thread appThread = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i=0;i<5;i++) {
                    System.out.println("do loop " + i);
                }
            }
        });
        appThread.setName("appThread");
        appThread.start();
        printGroupInfo(appThread);
    }

    static void printGroupInfo(Thread t) {
        ThreadGroup group = t.getThreadGroup();
        System.out.println("thread " + t.getName() + " group name is "
                + group.getName()+ " max priority is " + group.getMaxPriority()
                + " thread count is " + group.activeCount());

        ThreadGroup parent=group;
        do {
            ThreadGroup current = parent;
            parent = parent.getParent();
            if (parent == null) {
                break;
            }

            System.out.println(current.getName() + "'s parent is " + parent.getName());
        } while (true);
        System.out.println("--------------------------");
    }
}
