package rxjava.observerable;


import rx.Observable;
import rx.Observer;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObserverableJust {
    /*

     */
    public static void main(String[] args){
        Observable<List<Integer>> observable = Observable.just(getData(), getData(), getData(), getData(), getData(), getData(), getData(), getData(), getData(), getData());
        Subscription subscription = observable.subscribe(new Observer<List<Integer>>() {
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            public void onNext(List<Integer> integer) {
                System.out.println("onNext:"+integer);
            }
        });
    }

    private static List<Integer> getData(){
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++){
            list.add(random.nextInt(200));
        }
        return list;
    }
}
