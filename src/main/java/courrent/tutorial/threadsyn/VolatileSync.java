package courrent.tutorial.threadsyn;

/**
 * a.volatile关键字为域变量的访问提供了一种免锁机制
 * b.使用volatile修饰域相当于告诉虚拟机该域可能会被其他线程更新
 * c.因此每次使用该域就要重新计算，而不是使用寄存器中的值
 * d.volatile不会提供任何原子操作，它也不能用来修饰final类型的变量
 */
public class VolatileSync {

    public static void main(String args[]) {
//        final Bank bank = new Bank();
//
//        Thread tadd = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    bank.addMoney(100);
//                    bank.lookMoney();
//                    System.out.println("\n");
//
//                }
//            }
//        });
//
//        Thread tsub = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                while (true) {
//                    bank.subMoney(100);
//                    bank.lookMoney();
//                    System.out.println("\n");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        tsub.start();
//
//        tadd.start();
    }

}
