package rxjava.filter;


import rx.Observable;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Delay {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just( "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" ) ;

        //延迟数据发射的时间，仅仅延时一次，也就是发射第一个数据前延时。发射后面的数据不延时
        observable.delay( 1 , TimeUnit.SECONDS )  //延迟3秒钟
                .subscribe(new Action1() {
                    public void call(Object o) {
                        System.out.println("delay-- " + o);
                    }
                }) ;
    }
}
