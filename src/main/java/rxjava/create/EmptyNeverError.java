package rxjava.create;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wizardholy on 2017/3/24.
 */
public class EmptyNeverError {
    public static void main(String[] args) {
        //enpty默认实现call，只调用onCompleted：public void call(Subscriber<? super Object> child) {child.onCompleted();}
        Observable.empty().subscribe(new Subscriber<Object>() {
            @Override
            public void onNext(Object item) {
                System.out.println("Enpty：Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Enpty：Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Enpty：Sequence complete.");
            }
        });

//Never:创建一个不发射数据也不终止的Observable（不会调用订阅者的任何方法）
        Observable.never().subscribe(new Subscriber<Object>() {
            @Override
            public void onNext(Object item) {
                System.out.println("Nerver：Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Nerver：Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Nerver：Sequence complete.");
            }
        });

//Error:创建一个不发射数据以一个错误终止的Observable（只会调用onError）
        Observable.error(new Throwable("just call onError")).subscribe(new Subscriber<Object>() {
            @Override
            public void onNext(Object item) {
                System.out.println("Error：Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Error：Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Error：Sequence complete.");
            }
        });
    }
}
