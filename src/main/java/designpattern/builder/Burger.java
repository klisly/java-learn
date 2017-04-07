package designpattern.builder;

/**
 * Created by wizardholy on 2017/4/6.
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}