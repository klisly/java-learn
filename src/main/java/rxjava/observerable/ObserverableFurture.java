package rxjava.observerable;


import rx.Observable;
import rx.Observer;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ObserverableFurture {
    /*

     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<List<Integer>> callable = new Callable<List<Integer>>() {

            public List<Integer> call() throws Exception {
                List<Integer> list = new ArrayList<Integer>();
                Random random = new Random();
                for(int i = 0; i < 20; i++){
                    list.add( random.nextInt(100));
                }
                return list;
            }
        };
        Future future = new FutureTask(callable);
        Thread.sleep(5000);// 可能做一些事情
        Observable<Integer> observable = Observable.from((Iterable<? extends Integer>) future.get());
        Subscription subscription = observable.subscribe(new Observer<Integer>() {
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            public void onNext(Integer integer) {
                System.out.println("onNext:"+integer);
            }
        });
    }
}
