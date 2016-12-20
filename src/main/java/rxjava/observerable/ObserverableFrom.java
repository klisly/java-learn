package rxjava.observerable;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;

public class ObserverableFrom {
    /*

     */
    public static void main(String[] args){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Observable<Integer> observable = Observable.from(list);
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
