package courrent.tutorial.threadmanage.t2hreadmanage;

import java.util.Random;

/**
 * 通过直接继承thread类，然后覆盖run()方法。
 * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
 */
public class ThreadInterrupted {
    public static void main(String[] args) {

        Thread thread = new Thread(new CalculatorRunnable());
        thread.start();
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    /**
     * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
     */
    static class CalculatorRunnable implements Runnable {
        private Random random;

        public CalculatorRunnable() {

            random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                int num = random.nextInt(100000);
                if (isPrime(num)) {
                    System.out.printf("Number %d is Prime\n", num);
                } else {
                    System.out.printf("Number %d is not Prime\n", num);
                }

                try {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.printf("Thead %s is isInterrupted\n", Thread.currentThread().getName());
                        return;
                    }
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }

            }
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