package courrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("sub thread");
                        c.await();
                        System.out.println("sub thread run");
                    } catch (Exception e) {

                    }
                    System.out.println(1);
                }
            }).start();

            try {
                System.out.println("main thread");
                c.await();
                System.out.println("main thread run");
            } catch (Exception e) {

            }
            System.out.println(2);
            System.out.println("=====");
            c.reset();
        }

    }
}