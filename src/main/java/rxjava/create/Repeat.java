package rxjava.create;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.concurrent.TimeUnit;

//Repeat 将发射的数据重复发射N次
public class Repeat {
    public static void main(String[] args) {
//        Observable observable = Observable.just(1, 2, 3, 4, 5, 6);
//        observable
//                .repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(Observable<? extends Void> observable) {
//                        return observable.delay(5, TimeUnit.SECONDS);
//                    }
//                })
//                .subscribe(new Action1() {
//                    public void call(Object o) {
//                        System.out.println("repeatWhen--" + o);
//                    }
//                });
        final String[]items = {"just1","just2","just3","just4","just5","just6"};

        Observable<String> myObservable = Observable.from(items).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                return observable.delay(5, TimeUnit.SECONDS);
            }
        });


        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("onNext.................."+s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError....................");
            }
        };

        myObservable.subscribe(mySubscriber);
    }
}
