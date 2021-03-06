package courrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierRunnableFirst {

    static CyclicBarrier c = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("first execute");
        }
    });

    public static void main(String[] args) {
        for(int i = 0; i < 20000; i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        c.await();
                    } catch (Exception e) {

                    }
                    System.out.println(1);
                }
            }).start();

            try {
                c.await();
            } catch (Exception e) {

            }
            System.out.println(2);
            System.out.println("=====");
            c.reset();
        }

    }
}