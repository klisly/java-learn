package designpattern.builder;

/**
 * Created by wizardholy on 2017/4/6.
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        System.out.println("Bottle");
        return "Bottle";
    }
}
