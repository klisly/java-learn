package courrent.tutorial.threadmanage.t1threadinfo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

/**
 * 通过直接继承thread类，然后覆盖run()方法。
 * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
 */
public class ThreadInfo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Thread threads[] = new Thread[10];
        Thread.State status[] = new Thread.State[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new CalculatorRunnable(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread o " + i);
        }


        FileWriter file = new FileWriter("log.txt");
        PrintWriter pw = new PrintWriter(file);
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        boolean finish = false;
        while (!finish) {
            for (int i = 0; i < 10; i++) {
                if (threads[i].getState() != status[i]) {
                    writeThreadInfo(pw, threads[i], status[i]);
                    status[i] = threads[i].getState();
                }
            }

            finish = true;
            for (int i = 0; i < 10; i++) {
                finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
            }
        }
        pw.close();
    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Main : Priority: %d\n", thread.getPriority());
        pw.printf("Main : Old State: %s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : ************************************\n");
    }

    /**
     * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
     */
    static class CalculatorRunnable implements Runnable {
        private int number;

        public CalculatorRunnable(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            int j = 0;
            for (int i = 0; i <= 10; i++) {
                j += i;
                System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
            }
        }
    }
}