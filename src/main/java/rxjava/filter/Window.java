package rxjava.filter;


import rx.Observable;
import rx.functions.Action1;

/**
 Window和Buffer类似，但不是发射来自原始Observable的数据包，它发射的是Observables，这些Observables中的每一个都发射原始Observable数据的一个子集，最后发射一个onCompleted通知

 和Buffer一样，Window有很多变体，每一种都以自己的方式将原始Observable分解为多个作为结果的Observable，
 每一个都包含一个映射原始数据的window。用Window操作符的术语描述就是，当一个窗口打开(when a window “opens”)
 意味着一个新的Observable已经发射（产生）了，而且这个Observable开始发射来自原始Observable的数据；
 当一个窗口关闭(when a window “closes”)意味着发射(产生)的Observable停止发射原始Observable的数据，
 并且发射终止通知onCompleted给它的观察者们
 */
public class Window {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .window(3) //每次发射出一个包含三个整数的子Observable
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        //每次发射一个子Observable
                        System.out.println(integerObservable+"");
                        //订阅子Observable
                        integerObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("window:" + integer);
                            }
                        });
                    }
                });

        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .window(3, 2)
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        System.out.println(integerObservable+"");
                        //订阅子Observable
                        integerObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("windowSkip:" + integer);
                            }
                        });
                    }
                });
    }
}
