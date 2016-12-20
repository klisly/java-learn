package designpattern;

import java.util.Observable;

/**
 * Created by wizardholy on 2016/12/13.
 */
public class Observer {
    public static void main(String[] args) {
        // Observer后来者居上
        MyObserver observer = new MyObserver("MyObserver1");
        MyObserver observer1 = new MyObserver("MyObserver2");
        MyObserver observer2 = new MyObserver("MyObserver3");
        MyObservable observable = new MyObservable();
        observable.addObserver(observer);
        observable.addObserver(observer1);
        observable.addObserver(observer2);
        observable.publish();
        observable.deleteObserver(observer);
        observable.publish();


    }
}

class MyObserver implements java.util.Observer{
    private String name;

    public MyObserver(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        System.out.println(name + " receive udpate notify,"+arg);
    }
}

class MyObservable extends Observable{
    public void publish(){
        setChanged();
        notifyObservers(" new news");
    }
}