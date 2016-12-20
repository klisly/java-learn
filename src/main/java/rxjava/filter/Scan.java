package rxjava.filter;


import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

import java.util.ArrayList;
import java.util.List;

//累算操作符的使用
public class Scan {
    public static void main(String[] args) {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable.scan(new Func2<Integer, Integer, Integer>() {

            public Integer call(Integer integer, Integer integer2) {
                return integer * integer2;
            }
        })
        .subscribe(new Action1() {
            public void call(Object o) {
                System.out.println("scan--" + o);
            }
        });
    }
}
