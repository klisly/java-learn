package rxjava.filter;


import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

//累算操作符的使用
public class Take {
    public static void main(String[] args) {
        //获取指定项数据
        Observable.just(1, 2, 3, 4, 5,1,2,3)
                .take(2)
                .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println( "take Next:" + integer);
            }
        });

        //distinct
        Observable.just(1, 2, 3, 4, 5,1,2,3)
                .distinct()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println( "distinct Next:" + integer);
                    }
                });
        //takeWhile操作符返回一个镜像原始Observable行为的Observable，
        // 直到某一项数据你指定的函数返回false那一刻，这个新的Observable发射onCompleted终止通知。
        Observable.just(1, 2, 3, 4, 5,1,2,3)
                .takeWhile(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer <= 4;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println( "takeWhile Next:" + integer);
                    }
                });

        //skipWhile操作符返回一个镜像原始Observable行为的Observable，
        // 直到某一项数据你指定的函数返回false那一刻，这个新的Observable发射onCompleted终止通知。
        Observable.just(1, 2, 3, 4, 5,1,2,3)
                .skipWhile(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer <= 4;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println( "skipWhile Next:" + integer);
                    }
                });
    }
}
