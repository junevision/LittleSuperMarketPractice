package com.geekbang.supermarket;

/**
 * 超市类
 *
 * @author jun_lei
 */
public class LittleSuperMarket {
    public String superMarketName; //超市名字
    public String address; //超市地址
    public int parkingCount; //停车位数量
    public double incomingSum; //收入总营业额
    public Merchandise[] merchandises; //所有商品的集合
    public int[] merchandiseSold; //各个商品售卖的个数

    /**
     * 构造方法（构造实例的方法，必须和类名一致且没有返回值），完成超市的初始化数据
     *
     * @param superMarketName
     * @param address
     * @param parkingCount
     * @param merchandiseCount 商品种类数
     * @param count            每种商品缺省库存
     */
    public void LittleSuperMarket(String superMarketName, String address, int parkingCount,
                                  int merchandiseCount, int count) {
        this.superMarketName = superMarketName;
        this.address = address;
        this.parkingCount = parkingCount;

        merchandises = new Merchandise[merchandiseCount]; // 初始化定义了商品集合种数
        merchandiseSold = new int[merchandises.length]; // 商品售卖情况和商品集合数量对应
        // 把商品集合赋值给all，方便调用
        Merchandise[] all = merchandises;
        // 对商品集合里面的商品进行初始化
        for (int i = 0; i < all.length; i++) {
            // 创建并给商品的属性赋值
            Merchandise m = new Merchandise();
            m.count = count; // 用于计数商品还有多少库存
            m.id = "ID" + i;
            m.name = "商品" + i;
            m.purchasePrice = Math.random() * 200; // 商品进货价格是0-200之间
            m.soldPrice = (Math.random() + 1) * 200; // 商品售出价格是200-400之间
            all[i] = m; // 把当前循环的商品信息存入到商品集合数组里面
        }
    }

    /**
     * 定义了超市的描述方法
     */
    public void describe() {
        System.out.println("本店叫做" + this.superMarketName);
        System.out.println("本店地址" + this.address);
        System.out.println("共有停车位" + this.parkingCount + "个");
        System.out.println("今天的营业额为" + this.incomingSum);
        System.out.println("共有商品" + this.merchandises.length + "种");
    }

    // 简单的访问成员变量
    public String getSuperMarketName() {
        return superMarketName;
    }

    public String getAddress() {
        return address;
    }

    public int getParkingCount() {
        return parkingCount;
    }

    public double getIncomingSum() {
        return incomingSum;
    }

    public void setSuperMarketName(String superMarketName) {
        this.superMarketName = superMarketName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setParkingCount(int parkingCount) {
        this.parkingCount = parkingCount;
    }

    public void setIncomingSum(double incomingSum) {
        this.incomingSum = incomingSum;
    }

    public void setMerchandises(Merchandise[] merchandises) {
        this.merchandises = merchandises;
    }

    public void setMerchandiseSold(int[] merchandiseSold) {
        this.merchandiseSold = merchandiseSold;
    }
}
