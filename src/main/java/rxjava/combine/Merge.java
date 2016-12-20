package rxjava.combine;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;
//merge操作符，合并观察对象
public class Merge {
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
        Observable observable = Observable.merge(observable1, observable2);
        observable.subscribe(new Action1() {
            public void call(Object o) {
                System.out.println("rx--"+o);
            }
        });
    }
}
