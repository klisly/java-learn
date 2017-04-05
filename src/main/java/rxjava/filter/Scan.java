package rxjava.filter;


import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

//累算操作符的使用
public class Scan {
    public static void main(String[] args) {
//        Observable observable = Observable.just(1, 2, 3, 4, 5, 6);
//        observable.scan(new Func2<Integer, Integer, Integer>() {
//
//            public Integer call(Integer integer, Integer integer2) {
//                System.out.println("1:"+integer+" 2:"+integer2);
//                return integer * integer2;
//            }
//        })
//        .subscribe(new Action1() {
//            public void call(Object o) {
//                System.out.println("scan--" + o);
//            }
//        });
        //会把原始数据的第一项当做新的第一项发射
        Observable.just(1, 2, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        System.out.println(">应用函数:" + sum+" ,"+item);
                        return sum + item;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println( "Next:" + integer);
            }
        });

        //scan将发射种子值3作为自己的第一项数据
        Observable.just(1, 2, 3, 4, 5)
                .scan(2, new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        System.out.println( ">应用函数:" + sum+" ,"+item);
                        return sum + item;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println( "Next:" + integer);
            }
        });
    }
}
