package rxjava.filter;


import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

//累算操作符的使用
public class Filter {
    public static void main(String[] args) {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable.filter(new Func1<Integer, Boolean>(){

            public Boolean call(Integer integer) {
                return integer > 4;
            }
        })
        .subscribe(new Action1() {
            public void call(Object o) {
                System.out.println("scan--" + o);
            }
        });
    }
}
