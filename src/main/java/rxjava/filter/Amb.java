package rxjava.filter;


import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

//累算操作符的使用
public class Amb {
    public static void main(String[] args) {
        Long[]items = {6l,7l,8l,9l,10l};
        Observable<Long> observable = Observable.interval(2, TimeUnit.SECONDS).take(5);
        Observable<Long> observable1 = Observable.from(items);

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

        Observable.amb(observable,observable1).subscribeOn(Schedulers.immediate()).subscribe(subscriber);
    }
}
