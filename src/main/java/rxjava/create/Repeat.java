package rxjava.create;


import rx.Observable;
import rx.functions.Action1;

//Repeat 将发射的数据重复发射N次
public class Repeat {
    public static void main(String[] args) {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable
                .repeat(2)
                .subscribe(new Action1() {
                    public void call(Object o) {
                        System.out.println("scan--" + o);
                    }
                });
    }
}
