package rxjava.filter;


import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。

 Buffer操作符将一个Observable变换为另一个，原来的Observable正常发射数据，变换产生的Observable发射这些数据的缓存集合。Buffer操作符在很多语言特定的实现中有很多种变体，它们在如何缓存这个问题上存在区别
 注意：如果原来的Observable发射了一个onError通知，Buffer会立即传递这个通知，而不是首先发射缓存的数据，即使在这之前缓存中包含了原始Observable发射的数据
 Window操作符与Buffer类似，但是它在发射之前把收集到的数据放进单独的Observable，而不是放进一个数据结构
 */
public class GroupBy {
    public static void main(String[] args) {
        // groupBy(Func1)：Func1是对数据分组（确定key）
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .groupBy(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        //按照奇数和偶数分组
                        return integer % 2 == 0 ? "偶数" : "奇数";
                    }
                }).subscribe(new Action1<GroupedObservable<String, Integer>>() {
            @Override
            public void call(GroupedObservable<String, Integer> groupedObservable) {
//                groupedObservable.count()
//                        .subscribe(integer -> Log.v(TAG, "key" + groupedObservable.getKey() + " contains:" + integer + " numbers"));
                groupedObservable.subscribe(value->System.out.println("key" + groupedObservable.getKey() + " value:"+value));
            }
        });

        // groupBy(Func1,Func1)：Func1是对数据分组（确定key），Func2发射每个数据，在这里面可以对原始数据做处理
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .groupBy(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        //按照奇数和偶数分组
                        return integer % 2 == 0 ? "偶数" : "奇数";
                    }
                }, new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        //在数字前面加上说明，如果不加这个参数，最后发射的数据就是原始整数
                        return (integer % 2 == 0 ? "偶数" : "奇数")+integer;
                    }
                }).subscribe(groupedObservable -> {
    //                groupedObservable.count()
    //                        .subscribe(integer -> Log.v(TAG, "key" + groupedObservable.getKey() + " contains:" + integer + " numbers"));
                    groupedObservable.subscribe(value->System.out.println("key" + groupedObservable.getKey() + " value:"+value));
                });
    }
}
