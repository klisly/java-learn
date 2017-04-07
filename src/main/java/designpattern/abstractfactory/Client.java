package designpattern.abstractfactory;

/**
 * Created by wizardholy on 2017/4/6.
 */
public class Client {
    public static void main(String[] args){
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();
    }
}
