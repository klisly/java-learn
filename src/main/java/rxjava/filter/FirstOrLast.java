package rxjava.filter;


import rx.Observable;
import rx.functions.Action1;

//从可观测源序列中创建只发射第一个元素的序列。

public class FirstOrLast {
    public static void main(String[] args) {
        //获取指定项数据
        Observable.just(1, 2, 3, 4, 5,1,2,3)
                .first()
                .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println( "first Next:" + integer);
            }
        });

        //distinct
        Observable.just(1, 2, 3, 4, 5,1,2,3)
                .last()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println( "last Next:" + integer);
                    }
                });
    }
}
