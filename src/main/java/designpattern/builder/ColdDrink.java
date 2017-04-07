package designpattern.builder;

/**
 * Created by wizardholy on 2017/4/6.
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}