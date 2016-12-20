package rxjava.combine;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//join的效果类似于排列组合，把第一个数据源A作为基座窗口，
// 他根据自己的节奏不断发射数据元素，第二个数据源B，每发射一个数据，

// 我们都把它和第一个数据源A中已经发射的数据进行一对一匹配；
// 也是两个Observable产生的结果进行合并，合并的结果组成一个新的Observable，
// 但是join操作符可以控制每个Observable产生结果的生命周期，在每个结果的生命周期内，
// 可以与另一个Observable产生的结果按照一定的规则进行合并，流程图如下：
public class Join {
    public static void main(String[] args){
        List<String> list1 = new ArrayList<String>() ;
        List<String> list2 = new ArrayList<String>() ;

        list1.add( "1" ) ;
        list1.add( "2" ) ;
        list1.add( "3" ) ;

        list2.add( "a" ) ;
        list2.add( "b" ) ;
        list2.add( "c" ) ;
        Observable observable1 = Observable.from(list1);
        Observable observable2 = Observable.from(list2);
        observable1.join(observable2, new Func1<String, Observable<String>>() {
            public Observable<String> call(String s) {
                return Observable.just(s).delay(2000, TimeUnit.MILLISECONDS);
            }
        }, new Func1<String, Observable<String>>() {
            public Observable<String> call(String aLong) {
                //使Observable延迟600毫秒执行
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Func2<String, String, String>() {
            public String call(String str1, String str2) {
                return str1 + str2;
            }
        }).subscribe(new Subscriber<String>() {
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            public void onNext(String aLong) {
                System.out.println("Next: " + aLong);
            }
        });
    }
}
