package multithread;

/**
 * Created by wizardholy on 2017/3/28.
 */
public class ThreadVisiblity extends Thread {
    private static boolean ready = false;
    private static int number;

    @Override
    public void run() {
        while (!ready) {
        }
        System.out.println(number);
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadVisiblity().start();
        Thread.sleep(200);
        number = 42;
        ready = true;
    }
}
