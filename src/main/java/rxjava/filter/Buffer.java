package rxjava.filter;


import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。

 Buffer操作符将一个Observable变换为另一个，原来的Observable正常发射数据，变换产生的Observable发射这些数据的缓存集合。Buffer操作符在很多语言特定的实现中有很多种变体，它们在如何缓存这个问题上存在区别
 注意：如果原来的Observable发射了一个onError通知，Buffer会立即传递这个通知，而不是首先发射缓存的数据，即使在这之前缓存中包含了原始Observable发射的数据
 Window操作符与Buffer类似，但是它在发射之前把收集到的数据放进单独的Observable，而不是放进一个数据结构
 */
public class Buffer {
    public static void main(String[] args) {
//        Observable<String> observable = Observable.just( "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" ) ;

//        //延迟数据发射的时间，仅仅延时一次，也就是发射第一个数据前延时。发射后面的数据不延时
//        observable
//                .delay(1 , TimeUnit.SECONDS, Schedulers.immediate())  //延迟3秒钟
//                .buffer(2)
//                .subscribe(new Action1() {
//                    public void call(Object o) {
//                        System.out.println("delay-- " + o);
//                    }
//                }) ;
//        //延迟数据发射的时间，仅仅延时一次，也就是发射第一个数据前延时。发射后面的数据不延时
//        observable
//                .buffer(2)
//                .subscribe(new Action1() {
//                    public void call(Object o) {
//                        System.out.println("2 buffer-- " + o);
//                    }
//                }) ;
        //一组缓存3个数据
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .buffer(3)
                .subscribe(i -> System.out.println( "1buffer-count:" + i));
        //每隔三个数据缓存2个数据
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .buffer(2, 3)
                .subscribe(i -> System.out.println( "1buffer-count&skip:" + i));

        Observable.interval(1, TimeUnit.SECONDS, Schedulers.immediate()).
                buffer(3, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println( "2buffer-count:" + i));
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.immediate()).
                buffer(2, 3, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println( "2buffer-count&skip:" + i));
    }
}
