package rxjava.subject;

import rx.Observer;
import rx.Subscription;
import rx.subjects.PublishSubject;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PublishSubjectTest {
    public static void main(String[] args) {

        PublishSubject subject = PublishSubject.create();
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