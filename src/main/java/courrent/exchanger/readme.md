并发工具类（四）两个线程进行数据交换的Exchanger
简介

Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交换。它提供一个同步点，在这个同步点两个线程可以交换彼此的数据。这两个线程通过exchange方法交换数据， 如果第一个线程先执行exchange方法，它会一直等待第二个线程也执行exchange，当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。

Exchanger的应用场景

Exchanger可以用于遗传算法，遗传算法里需要选出两个人作为交配对象，这时候会交换两人的数据，并使用交叉规则得出2个交配结果。
Exchanger也可以用于校对工作。比如我们需要将纸制银流通过人工的方式录入成电子银行流水，为了避免错误，采用AB岗两人进行录入，录入到Excel之后，系统需要加载这两个Excel，并对这两个Excel数据进行校对，看看是否录入的一致。代码如下：


01
public class ExchangerTest {
02

03
    private static final Exchanger<String> exgr = new Exchanger<String>();
04

05
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
06

07
    public static void main(String[] args) {
08

09
        threadPool.execute(new Runnable() {
10
            @Override
11
            public void run() {
12
                try {
13
                    String A = "银行流水A";// A录入银行流水数据
14
                    exgr.exchange(A);
15
                } catch (InterruptedException e) {
16
                }
17
            }
18
        });
19

20
        threadPool.execute(new Runnable() {
21
            @Override
22
            public void run() {
23
                try {
24
                    String B = "银行流水B";// B录入银行流水数据
25
                    String A = exgr.exchange("B");
26
                    System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是："
27
                            + A + ",B录入是：" + B);
28
                } catch (InterruptedException e) {
29
                }
30
            }
31
        });
32

33
        threadPool.shutdown();
34

35
    }
36
}
其他方法

如果两个线程有一个没有到达exchange方法，则会一直等待,如果担心有特殊情况发生，避免一直等待，可以使用exchange(V x, long timeout, TimeUnit unit)设置最大等待时长。