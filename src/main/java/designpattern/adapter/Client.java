package designpattern.adapter;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class Client {
    public static void main(String[] args){
        Adapter adapter = new Adapter();
        adapter.sampleOperator1();
        ObjectAdapter objectAdapter = new ObjectAdapter(new Adaptee());
        objectAdapter.sampleOperator1();
    }
}
