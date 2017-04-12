package designpattern.state.vendingmachine;

/**
 * Created by wizardholy on 2017/4/12.
 */
public interface State {
    /**
     * 放钱
     */
    public void insertMoney();
    /**
     * 退钱
     */
    public void backMoney();
    /**
     * 转动曲柄
     */
    public void turnCrank();
    /**
     * 出商品
     */
    public void dispense();
}
