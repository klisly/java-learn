package designpattern.strategy;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class Client {
    public static void main(String[] args){
        Context context = new Context();
        context.setStrategy(new ConcreteStrategy());
        context.execute();
        context.setStrategy(new ConcreteStrategyA());
        context.execute();
    }
}
