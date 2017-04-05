package collection.concurrent.atmoic;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by wizardholy on 2017/4/1.
 */
public class AdderTest {
    static  double d = 0.01;
    public static void main(String []args) throws InterruptedException {
        LongAdder adder = new LongAdder();
        adder.increment();
        adder.increment();
        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    adder.increment();
                    System.out.println(adder.intValue());
                }
            }).start();
        }
        System.out.println(adder.sum());

        DoubleAdder doubleAdder = new DoubleAdder();
        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doubleAdder.add(1.09002);
                    System.out.println("DoubleAdder "+doubleAdder.intValue());
                }
            }).start();
        }


        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d+=1.80100001212;
                    System.out.println("long  "+d);
                }
            }).start();
        }
        System.out.println("long "+d);

        System.out.println("1.6 "+(int)1.6);
        System.out.println("1.3 "+(int)1.3);

    }
}
