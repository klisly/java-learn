package collection.concurrent.atmoic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * Created by wizardholy on 2017/4/1.
 */
public class AccumulatorTest {
    static  double d = 0.01;
    public static void main(String []args) throws InterruptedException {
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left - right;
            }
        }, 1);
        longAccumulator.accumulate(2);
        longAccumulator.accumulate(4);
        longAccumulator.accumulate(8);
//        for(int i = 0; i < 10; i++){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    adder.increment();
//                    System.out.println(adder.intValue());
//                }
//            }).start();
//        }
        System.out.println(longAccumulator.get());


    }
}
