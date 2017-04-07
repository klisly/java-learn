package designpattern.builder;

/**
 * Created by wizardholy on 2017/4/6.
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        System.out.println("Wrapper");
        return "Wrapper";
    }
}
