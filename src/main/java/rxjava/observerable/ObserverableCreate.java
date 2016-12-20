package rxjava.observerable;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

public class ObserverableCreate {
    /*

     */
    public static void main(String[] args){
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>(){

            public void call(Subscriber<? super Integer> subscriber) {
                for(int i = 0; i < 5; i++){
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });

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
