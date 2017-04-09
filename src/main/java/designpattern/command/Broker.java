package designpattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wizardholy on 2017/4/9.
 */
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
