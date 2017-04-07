package designpattern.builder;

/**
 * Created by wizardholy on 2017/4/6.
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
