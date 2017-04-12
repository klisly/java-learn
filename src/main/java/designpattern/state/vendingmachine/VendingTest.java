package designpattern.state.vendingmachine;

/**
 * Created by wizardholy on 2017/4/12.
 */
public class VendingTest {
    public static void main(String[] args)
    {
        VendingMachine machine = new VendingMachine(10);
        machine.insertMoney();
        machine.backMoney();

        System.out.println("----我要中奖----");

        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();
        machine.insertMoney();
        machine.turnCrank();

        System.out.println("-------压力测试------");

        machine.insertMoney();
        machine.backMoney();
        machine.backMoney();
        machine.turnCrank();// 无效操作
        machine.turnCrank();// 无效操作
        machine.backMoney();

    }
}
