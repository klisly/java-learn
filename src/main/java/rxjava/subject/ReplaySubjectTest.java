package rxjava.subject;

import rx.Observer;
import rx.Subscription;
import rx.subjects.ReplaySubject;

public class ReplaySubjectTest {
    public static void main(String[] args) {
        // 缓存所订阅的所有数据,向任意订阅它的观察者重发数据
        ReplaySubject subject = ReplaySubject.create();
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