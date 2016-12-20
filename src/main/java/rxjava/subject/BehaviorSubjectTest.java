package rxjava.subject;

import rx.Observer;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class BehaviorSubjectTest {
    public static void main(String[] args) {
        // 带有默认初始值的Subject, 会想订阅者发送初始值
        BehaviorSubject subject = BehaviorSubject.create("1");
        Subscription subscription = subject.subscribe(new Observer<String>() {
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            public void onError(Throwable e) {
                System.out.println("error");
            }

            public void onNext(String o) {
                System.out.println("onNext:"+o);
            }
        });
        subject.onNext("23");
        subject.onNext("22");
        subject.onCompleted();
    }
}