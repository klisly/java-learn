package rxjava.create;


import rx.Observable;

//defer 推迟到观察者订阅时才创建Observable
//直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
//Defer操作符会一直等待直到有观察者订阅它，然后它使用Observable工厂方法生成一个Observable。它对每个观察者都这样做，因此尽管每个订阅者都以为自己订阅的是同一个Observable，事实上每个订阅者获取的是它们自己的单独的数据序列。
//在某些情况下，等待直到最后一分钟（就是知道订阅发生时）才生成Observable可以确保Observable包含最新的数据。
//这个操作符接受一个你选择的Observable工厂函数作为单个参数。这个函数没有参数，返回一个Observable。
//defer方法默认不在任何特定的调度器上执行。
public class Defer {
    public static void main(String[] args) {
        Observable<Integer> defered = Observable.defer(Defer::getInt);
        defered.subscribe(number->{
            System.out.println("number:"+number);
        });
    }

    public static Observable<Integer> getInt(){
        return Observable.create(subscriber ->{
            if(subscriber.isUnsubscribed()){
                return;
            }
            System.out.println("get int");
            subscriber.onNext(2);
            subscriber.onCompleted();
        });
    }
}
