package rxjava.filter;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by wizardholy on 2017/3/30.
 */
//SkipUtil操作符是根据一个目标Observable为基准，当目标Observable没发射出去数据的时
// ，原Observable发射出去的数据将会被忽略，当目标Observable发射数据时，
// 则原Observable才开始发射数据。
public class SkipUntil {
    public static void main(String[] args){
        //所需发射的数据，在observable1发射之前的数据将会被丢失
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        //当此observale发射数据时，observable的数据才会被传递到onNext中
        Observable <Long> observable1 = Observable.timer(5,TimeUnit.SECONDS);

        Subscriber<Long> subscriber = new Subscriber<Long>() {
            @Override
            public void onNext(Long v) {
                System.out.println( "onNext................." + v);

            }

            @Override
            public void onCompleted() {
                System.out.println( "onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println( "onError.....................");
            }
        };

        observable.subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate())
                .skipUntil(observable1)
                .subscribe(subscriber);
    }
}
