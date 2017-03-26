package rxjava.filter;


import rx.Observable;
import rx.functions.Func1;

import java.util.ArrayList;

/**
 FlatMap将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个单独的Observable
 FlatMap操作符使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，这个函数返回一个本身也发射数据的Observable，
 然后FlatMap合并这些Observables发射的数据，最后将合并后的结果当做它自己的数据序列发射
 这个方法是很有用的，例如，当你有一个这样的Observable：它发射一个数据序列，
 这些数据本身包含Observable成员或者可以变换为Observable，因此你可以创建一个新的Observable发射这些次级Observable发射的数据的完整集合
 注意：FlatMap对这些Observables发射的数据做的是合并(merge)操作，因此它们可能是交错的。
 在许多语言特定的实现中，还有一个操作符不会让变换后的Observables发射的数据交错，它按照严格的顺序发射这些数据，
 这个操作符通常被叫作ConcatMap或者类似的名字
 注意：如果任何一个通过这个flatMap操作产生的单独的Observable调用onError异常终止了，
 这个Observable自身会立即调用onError并终止。
 这个操作符有一个接受额外的int参数的一个变体。这个参数设置flatMap从原来的Observable映射Observables的最大同时订阅数。
 当达到这个限制时，它会等待其中一个终止然后再订阅另一个。

 Javadoc: flatMap(Func1))
 Javadoc: flatMap(Func1,int))

 flatMapIterable:

 这个变体成对的打包数据，然后生成Iterable而不是原始数据和生成的Observables，但是处理方式是相同的

 concatMap

 它类似于最简单版本的flatMap，但是它按次序连接而不是合并那些生成的Observables，然后产生自己的数据序列。

 switchMap:

 它和flatMap很像，除了一点：当原始Observable发射一个新的数据（Observable）时，
 它将取消订阅并停止监视产生执之前那个数据的Observable，只监视当前这一个
 */
public class FlatMap {
    public static void main(String[] args) {
        Observable.just(1,2,3,4,5,6)
                .flatMap(i -> Observable.just("num "+i, "num n "+i))
                .subscribe(s -> System.out.println(s));

        Observable.just(1,2,3,4,5,6)
                .flatMapIterable(num -> {
                    ArrayList<Integer> list = new ArrayList<>();
                    for(int i = 0; i < num; i++){
                        list.add(i);
                    }
                    return list;
                })
                .subscribe(s -> System.out.println(s));
        Observable<Integer> o1 = Observable.just(1,2,3,4,5);
        Observable<Integer> o2 = Observable.just(5,4,3,2,1);

        Observable.just(1,2,3,4,5,6)
                .concatMap(new Func1<Integer, Observable<?>>() {
                    @Override
                    public Observable<?> call(Integer integer) {
                        return Observable.just("concact "+integer * 2);
                    }
                })
                .subscribe(s -> System.out.println(s));
    }
}
