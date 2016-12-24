package courrent.tutorial.t0create;

/**
 * 通过直接继承thread类，然后覆盖run()方法。
    构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
 */
public class ThreadCreateByRunnable {
       public static void main(String[] args){

           for(int i = 0; i <= 10; i++){
               Calculator calculator = new Calculator(i);
               Thread thread = new Thread(calculator);
               thread.start();
           }
       }
}

/**
 * 构建一个实现Runnable接口的类, 然后创建一个thread类对象并传递Runnable对象作为构造参数
 */
class Calculator implements Runnable{
    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for(int i = 0; i <= 10; i++){
            System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,i*number);
        }
    }
}