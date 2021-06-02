package com.geekbang.person;

/**
 * 顾客类
 *
 * @author jun_lei
 */
public class Customer {
    public String name; //顾客的名字
    public double money; //顾客总共能支付的现金
    public boolean isDrivingCar; //顾客是否开车来

    /**
     * 构造方法（构造实例的方法，必须和类名一致且没有返回值），完成顾客的初始化数据
     */
    public void Customer() {
        this.name = "顾客编号" + (int) (Math.random() * 10000); //顾客名字编号范围是0-10000之间随机数
        this.money = (Math.random() + 1) * 1000; //顾客所带的现金是1000-2000之间
        this.isDrivingCar = Math.random() > 0.5; //只要随机数大于0.5，就默认用户是开车过来，否则就是步行
    }
}
