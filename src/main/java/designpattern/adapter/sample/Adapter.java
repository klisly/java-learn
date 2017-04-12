package designpattern.adapter.sample;

/**
 * 类适配器模式
 */
public class Adapter extends Adaptee implements Target {

    @Override
    public void sampleOperator1() {
        this.sampleOperator();
        System.out.println(" Adapter sampleOperator1");
    }

    @Override
    public void sampleOperator2() {
        System.out.println(" Adapter sampleOperator2");
    }
}
