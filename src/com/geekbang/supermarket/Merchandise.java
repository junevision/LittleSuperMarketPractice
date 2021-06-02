package com.geekbang.supermarket;

/**
 * 商品类
 *
 * @author jun_lei
 */
public class Merchandise {
    public String name; //商品的名称
    public String id; //商品的ID
    public int count; //商品的个数
    public double soldPrice; //商品对外售卖的价格
    public double purchasePrice; //商品进货的价格

    // 静态变量用static修饰符，不赋值也会有初始值
    // 一般用全大写字母和下划线分割，是良好习惯
    // public的静态变量，所有代码都可以使用，可以通过import static xxx.DISCOUNT_FOR_VIP来直接引用
    // 如果没有public，只能在一个包内使用
    public static double DISCOUNT_FOR_VIP = 0.95;

    // 静态方法可以访问静态变量，包括自己类的静态变量和在访问控制符允许的别的类的静态变量
    // 除了没有this，静态方法定义和成员方法一样，也有方法名，返回值，参数
    // 没有this自引用，不属于某个实例，调用也不需要引用，直接用类名调用，所以也不能直接访问成员变量
    // 在静态方法里面，可以自己创建对象，或者通过参数，获得对象引用，进而调用方法和访问成员变量
    // 静态方法只是没有this自引用的方法而已
    // 可以通过import static xxx.getDiscountForVip来直接引用
    public static double getDiscountForVip() {
        return DISCOUNT_FOR_VIP;
    }

    /**
     * 定义了用于半价促销的buy方法
     *
     * @param countToBuy: 要购买的数量
     * @return 消费总额
     */
    public double buyWithHalfPrice(int countToBuy) {
        if (this.count < countToBuy) {
            System.out.println("商品库存不足");
            // 如果返回值是负数，则就代表购买失败，比如库存不足，是程序约定好的
            return -1;
        }
        // 如果出现不是成对，就是奇数，譬如买3个，那就是2个全价，一个半价
        int fullPriceCount = countToBuy / 2 + countToBuy % 2;
        // 商品不是半价就是全价
        int halfPriceCount = countToBuy - fullPriceCount;
        // 半价除以2不能放前面，因为可能出现奇数，3/2就不对了
        double totalCost = fullPriceCount * this.soldPrice + (halfPriceCount * this.soldPrice / 2);
        this.count -= countToBuy;
        return totalCost;
    }

    public double buyWithVIP(int countToBuy) {
        if (this.count < countToBuy) {
            System.out.println("商品库存不足");
            // 如果返回值是负数，则就代表购买失败，比如库存不足，是程序约定好的
            return -1;
        }
        double totalCost = countToBuy * this.soldPrice * DISCOUNT_FOR_VIP;
        this.count -= countToBuy;
        return totalCost;
    }
}
