package courrent.tutorial.threadmanage.t2hreadmanage;

import java.util.concurrent.TimeUnit;

/**
 * 通过直接继承thread类，然后覆盖run()方法。
 * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
 */
public class ThreadJoin {
    public static void main(String[] args) {

        Thread thread = new Thread(new CalculatorRunnable(1));
        thread.start();
        Thread thread1 = new Thread(new CalculatorRunnable(2));
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadJoin finish ");
    }

    /**
     * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
     */
    static class CalculatorRunnable implements Runnable {
        int number;
        public CalculatorRunnable(int num) {
            this.number = num;
        }

        @Override
        public void run() {
            System.out.println("Beginning data sources loading "+number);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Beginning data sources loading finish "+number);

        }
    }

    /**
     * 判断是否是质数
     * 质数（prime number）又称素数，有无限个。
     * 质数定义为在大于1的自然数中，除了1和它本身以外不再有其他因数的数称为质数。
     *
     * @param number
     * @return
     */
    private static boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }

        for (long i = 2; i < number / 2; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }
}