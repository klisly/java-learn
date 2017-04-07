package designpattern.abstractfactory;

/**
 * Created by wizardholy on 2017/4/6.
 */
public class Factory implements IFactory {
    @Override
    public IProduct1 createProduct1() {
        return new Product1();
    }

    @Override
    public IProduct2 createProduct2() {
        return new Product2();
    }
}
