package rxjava.combine;


import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

import java.util.ArrayList;
import java.util.List;

//操作符，合并多个观察对象的数据。并且允许 Func2（）函数重新发送合并后的数据
public class Zip {
    public static void main(String[] args){
        List<String> list1 = new ArrayList<String>() ;
        List<String> list2 = new ArrayList<String>() ;

        list1.add( "1" ) ;
        list1.add( "2" ) ;
        list1.add( "3" ) ;
        list1.add( "4" ) ;
        list2.add( "a" ) ;
        list2.add( "b" ) ;
        list2.add( "c" ) ;
        Observable observable1 = Observable.from(list1);
        Observable observable2 = Observable.from(list2);
        Observable observable = Observable.zip(observable1, observable2, new Func2<String, String, String>() {
            public String call(String s, String s2) {
                return s + s2;
            }
        });

        observable.subscribe(new Action1() {
            public void call(Object o) {
                System.out.println("rx--"+o);
            }
        });
    }
}
