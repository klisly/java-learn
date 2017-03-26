package rxjava.create;


import rx.Observable;
import rx.functions.Action1;

//Just发射多个数据
public class Just {
    public static void main(String[] args) {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable
        .subscribe(new Action1() {
            public void call(Object o) {
                System.out.println("scan--" + o);
            }
        });
    }
}
