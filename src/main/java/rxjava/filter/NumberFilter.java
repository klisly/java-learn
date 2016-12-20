package rxjava.filter;

import rx.Observable;
import rx.functions.Action1;

//累算操作符的使用
public class NumberFilter {
    public static void main(String[] args) {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable.take(3)
                .subscribe(new Action1() {
                    public void call(Object o) {
                        System.out.println("take-- " + o);
                    }
                });

        //takeLast 发送最后三个数据
        Observable observable2 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable2.takeLast(3)
                .subscribe(new Action1() {
                    public void call(Object o) {
                        System.out.println("takeLast-- " + o);
                    }
                });

        //first 只发送第一个数据
        Observable observable3 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable3.first()
                .subscribe(new Action1() {
                    public void call(Object o) {
                        System.out.println("first-- " + o);
                    }
                });

        //last 只发送最后一个数据
        Observable observable4 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable4.last()
                .subscribe(new Action1() {
                    public void call(Object o) {
                        System.out.println("last-- " + o);
                    }
                });

        //skip() 跳过前2个数据发送后面的数据
        Observable observable5 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable5.skip(2)
                .subscribe(new Action1() {
                    public void call(Object o) {
                        System.out.println("skip-- " + o);
                    }
                });

        //skipLast() 跳过最后两个数据，发送前面的数据
        Observable observable6 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable5.skipLast(2)
                .subscribe(new Action1() {
                    public void call(Object o) {
                        System.out.println("skipLast-- " + o);
                    }
                });
    }
}
