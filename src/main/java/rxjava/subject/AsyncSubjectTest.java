package rxjava.subject;

import rx.Observer;
import rx.Subscription;
import rx.subjects.AsyncSubject;

public class AsyncSubjectTest {
    public static void main(String[] args) {
        // 当Observable完成时,只发布最后一个数据给订阅者
        AsyncSubject subject = AsyncSubject.create();
        Subscription subscription = subject.subscribe(new Observer<String>() {
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            public void onError(Throwable e) {
                System.out.println("error");
            }

            public void onNext(String o) {
                System.out.println("subscription onNext:"+o);
            }
        });
        subject.onNext("1");
        subject.onNext("2");
        Subscription subscription2 = subject.subscribe(new Observer<String>() {
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            public void onError(Throwable e) {
                System.out.println("error");
            }

            public void onNext(String o) {
                System.out.println("subscription2 onNext:"+o);
            }
        });
        subject.onNext("3");
        subject.onNext("4");
        subject.onCompleted();
    }
}