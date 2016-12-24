package courrent.tutorial.t0create;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过直接继承thread类，然后覆盖run()方法。
 * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
 */
public class ThreadCreateByCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int j = 0; j < 2000; j++) {
            List<FutureTask<Integer>> tasks = new ArrayList<FutureTask<Integer>>();
            for (int i = 0; i <= 10; i++) {
                CalculatorCallable calcula = new CalculatorCallable(i);
                FutureTask<Integer> futureTask = new FutureTask(calcula);
                new Thread(futureTask).start();
                tasks.add(futureTask);
            }
            for (FutureTask futureTask : tasks) {
                System.out.println("task result:" + futureTask.get());
            }
            System.out.println("seperator=================");

        }
    }
}

/**
 * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
 */
class CalculatorCallable implements Callable<Integer> {
    private int number;

    public CalculatorCallable(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int j = 0;
        for (int i = 0; i <= 10; i++) {
            j += i;
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
        }
        return j;
    }
}