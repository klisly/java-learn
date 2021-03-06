package rxjava.subject;

import rx.Observer;
import rx.Subscription;
import rx.subjects.PublishSubject;

public class PublishSubjectTest {
    public static void main(String[] args) {

        PublishSubject  subject = PublishSubject.create();
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
        subject.onNext("222");
        subject.onNext("2223");
        subject.onCompleted();
    }
}