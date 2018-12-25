package courrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Exchanger用于进行线程间的数据交换。它提供一个同步点，在这个同步点两个线程可以交换彼此的数据。
public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<String>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(() -> {
            try {
                String A = "银行流水A";// A录入银行流水数据
                String B = exgr.exchange(A);
                System.out.println("a done! "+B);
            } catch (InterruptedException e) {
            }
        });

        threadPool.execute(() -> {
            try {
                String B = "银行流水A";// B录入银行流水数据
                String A = exgr.exchange(B);
                System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是："
                        + A + ",B录入是：" + B);
            } catch (InterruptedException e) {
            }
        });

        threadPool.shutdown();

    }
}