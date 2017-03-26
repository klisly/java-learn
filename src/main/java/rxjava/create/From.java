package rxjava.create;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//
//Javadoc: from(array)
//Javadoc: from(Iterable)
//Javadoc: from(Future)
//Javadoc: from(Future,Scheduler)
//Javadoc: from(Future,timeout, timeUnit)
//将一个Iterable, 一个Future, 或者一个数组转换成一个Observable。
//在RxJava中，from操作符可以转换Future、Iterable和数组。对于Iterable和数组，产生的Observable会发射Iterable或数组的每一项数据
//对于Future，它会发射Future.get()方法返回的单个数据。from方法有一个可接受两个可选参数的版本，分别指定超时时长和时间单位。如果过了指定的时长Future还没有返回一个值，这个Observable会发射错误通知并终止。
//from默认不在任何特定的调度器上执行。然而你可以将Scheduler作为可选的第二个参数传递给Observable，它会在那个调度器上管理这个Future。
public class From {
    public static void main(String[] args) {
        Integer[] items = {0, 1, 2, 3, 4, 5};
        Observable myObservable = Observable.from(items);
        myObservable.subscribe(
                new Action1<Integer>() {
                    @Override
                    public void call(Integer item) {
                        System.out.println(item + "");
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable error) {
                        System.out.println("Error encountered: " + error.getMessage());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Sequence complete");
                    }
                }
        );

        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        myObservable = Observable.from(future);
        myObservable
                .subscribeOn(Schedulers.immediate())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }
                    @Override
                    public void onError(Throwable error) {
                        System.out.println("Error: " + error.getMessage());
                    }
                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }
}
