package rxjava.create;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;
// Timer 定时器 发射0后完成
public class Timer {
    public static void main(String[] args) {
        Observable.timer(3, TimeUnit.SECONDS, Schedulers.trampoline())
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
                        System.out.println("onNext "+aLong);
                    }
                });
        int i = 0;
        while (i < 10){
            try {
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}