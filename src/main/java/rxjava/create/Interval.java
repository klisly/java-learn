package rxjava.create;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;
// interval 定时轮询器
public class Interval {
    public static void main(String[] args) {
        Observable.interval(3, TimeUnit.SECONDS, Schedulers.immediate())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("done");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        // 轮询的次数序号
                        System.out.println("onNext "+aLong);
                    }
                });
    }
}