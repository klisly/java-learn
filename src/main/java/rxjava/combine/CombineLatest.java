package rxjava.combine;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

import java.util.ArrayList;
import java.util.List;

//combineLatest操作符把两个Observable产生的结果进行合并，合并的结果组成一个新的Observable。
// 这两个Observable中任意一个Observable产生的结果，都和另一个Observable最后产生的结果，
// 按照一定的规则进行合并。
public class CombineLatest {
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
        Observable.combineLatest(observable1, observable2, new Func2<String, String, String>() {
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
//        Observable<Long> observable1 = Observable.timer(0, 1000, TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, Long>() {
//                    public Long call(Long aLong) {
//                        return aLong * 5;
//                    }
//                }).take(5);
//
//        //产生0,10,20,30,40数列
//        Observable<Long> observable2 = Observable.timer(500, 1000, TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, Long>() {
//                    public Long call(Long aLong) {
//                        return aLong * 10;
//                    }
//                }).take(5);
//
//
//        Observable.combineLatest(observable1, observable2, new Func2<Long, Long, Long>() {
//            public Long call(Long aLong, Long aLong2) {
//                return aLong+aLong2;
//            }
//        }).subscribe(new Subscriber<Long>() {
//            public void onCompleted() {
//                System.out.println("Sequence complete.");
//            }
//
//            public void onError(Throwable e) {
//                System.err.println("Error: " + e.getMessage());
//            }
//
//            public void onNext(Long aLong) {
//                System.out.println("Next: " + aLong);
//            }
//        });
    }
}
