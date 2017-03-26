package rxjava.create;


import rx.Observable;
import rx.Subscriber;

//Create

//使用Create操作符从头开始创建一个Observable，给这个操作符传递一个接受观察者作为参数的函数，我们需要实现call方法发射一些数据，并恰当的调用观察者的onNext，onError和onCompleted方法；
//        一个形式正确的有限Observable必须尝试调用观察者的onCompleted正好一次或者它的onError正好一次，而且此后不能再调用观察者的任何其它方法；
//        建议你在传递给create方法的函数中检查观察者的isUnsubscribed状态，以便在没有观察者的时候，让你的Observable停止发射数据或者做昂贵的运算；
//        create方法默认不在任何特定的调度器上执行。
public class Create {
    public static void main(String[] args) {
        //订阅者
        Subscriber subscriber= new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }
            @Override
            public void onError(Throwable error) {
                System.out.println("Error: " + error.getMessage());
            }
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        };
//create方法默认不在任何特定的调度器上执行。
        Observable observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            //当Observable.subscribe被调用时（有订阅者时）执行call方法
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    //检查观察者的isUnsubscribed状态，以便在没有观察者的时候，让Observable停止发射数据或者做昂贵的运算
                    for (int i = 1; i < 5; i++) {
                        if(i == 4){
                            //取消订阅 (Unsubscribing),调用这个方法表示你不关心当前订阅的Observable了，
                            //因此Observable可以选择停止发射新的数据项（如果没有其它观察者订阅）。
                            subscriber.unsubscribe();
                        }
                        if (!observer.isUnsubscribed()) {
                            observer.onNext(i);
                        }
                    }
                    if (!observer.isUnsubscribed()) {
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        } );
//订阅
        observable.subscribe(subscriber);
    }
}
