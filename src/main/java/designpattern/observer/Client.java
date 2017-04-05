package designpattern.observer;

/**
 * Created by wizardholy on 2017/4/1.
 * Push Model
 */
public class Client {
    public static void main(String[] args){
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserver observer = new ConcreteObserver();
        subject.attach(observer);
        subject.change("kd");
    }
}
