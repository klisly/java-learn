package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wizardholy on 2017/4/1.
 */
public class PullObserver {
    public static void main(String[] args){
        ConcreteISubject subject = new ConcreteISubject();
        ConcreteIObserver iObserver = new ConcreteIObserver();
        subject.attach(iObserver);
        subject.change("st");
    }
}
interface IObserver {
    /**
     * 更新接口
     * @param subject 传入主题对象，方面获取相应的主题对象的状态
     */
    public void update(ISubject subject);
}

class ConcreteIObserver implements IObserver {
    //观察者的状态
    private String observerState;

    @Override
    public void update(ISubject subject) {
        /**
         * 更新观察者的状态，使其与目标的状态保持一致
         */
        observerState = ((ConcreteISubject)subject).getState();
        System.out.println("观察者状态为："+observerState);
    }
}
abstract class ISubject {
    /**
     * 用来保存注册的观察者对象
     */
    private List<IObserver> list = new ArrayList<IObserver>();
    /**
     * 注册观察者对象
     * @param observer    观察者对象
     */
    public void attach(IObserver observer){

        list.add(observer);
        System.out.println("Attached an observer");
    }
    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void detach(IObserver observer){

        list.remove(observer);
    }
    /**
     * 通知所有注册的观察者对象
     */
    public void nodifyObservers(){

        for(IObserver observer : list){
            observer.update(this);
        }
    }
}

class ConcreteISubject extends ISubject{

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState){
        state = newState;
        System.out.println("主题状态为：" + state);
        //状态发生改变，通知各个观察者
        this.nodifyObservers();
    }
}