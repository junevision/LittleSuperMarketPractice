package com.geekbang;

import com.geekbang.person.Customer;
import com.geekbang.supermarket.LittleSuperMarket;
import com.geekbang.supermarket.Merchandise;

import java.util.Scanner;

/**
 * 运行超市的主程序
 *
 * @author jun_lei
 */
public class RunLittleSupperMarketAppMain {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket();
        littleSuperMarket.LittleSuperMarket("有家超市", "世纪大道666号",
                100, 200, 200);
        Merchandise[] all = littleSuperMarket.merchandises;
        System.out.println("超市开门啦！");

        boolean open = true;
        Scanner scanner = new Scanner(System.in);
        // 只要超市是开门状态，运行下面程序
        while (open) {
            // 调用超市的描述
            littleSuperMarket.describe();

            Customer customer = new Customer();
            // 初始化顾客的信息
            customer.Customer();

            if (customer.isDrivingCar) { //如果顾客开车过来
                if (littleSuperMarket.parkingCount > 0) { //如果有车位
                    System.out.println("欢迎" + customer.name + "驾车而来。本店已经为您安排了车位，停车免费。车位编号为" + littleSuperMarket.parkingCount);
                    littleSuperMarket.parkingCount--; //总的200个停车位就减一
                } else { //如果停车位已经满了
                    System.out.println("不好意思，本店车位已满，欢迎您下次光临！");
                    continue;
                }
            } else { // 如果是步行过来
                System.out.println("欢迎" + customer.name + "光临本店");
            }

            double totalCost = 0; //本次顾客的消费总额
            // 本轮购买开始
            while (true) {
                System.out.println("本店提供" + all.length + "种商品，欢迎选购。请输入商品编号：");
                int index = scanner.nextInt();
                //如果商品编号是复数，就直接退出
                if (index < 0) {
                    break;
                }
                //如果商品编号超过了商品总数，则要重新输入范围内的数
                if (index >= all.length) {
                    System.out.println("本店没有这种商品，请叙述编号在0到" + (all.length - 1) + "之内的商品编号。");
                    continue;
                }
                //从数组中寻找对应的商品信息
                Merchandise m = all[index];
                System.out.println("您选购的商品名字" + m.name + "。单价是" + m.soldPrice + "。请问您要购买多少个？");
                int numToBuy = scanner.nextInt();
                //如果买的数量小于0
                if (numToBuy <= 0) {
                    System.out.println("不买看看也好，欢迎继续挑选。");
                    continue;
                }
                //如果买的数量超过商品的数量
                if (numToBuy > m.count) {
                    System.out.println("本店此商品库存不足，请重新挑选");
                    continue;
                }
                //如果买的数量乘购买的价格，加一共已经花费的价格，超过顾客的现金流
                if (numToBuy * m.purchasePrice + totalCost > customer.money) {
                    System.out.println("您带的钱不够，请重新挑选");
                    continue;
                }
                //本次花费的总数要记上本次的购买金额
                totalCost += numToBuy * m.soldPrice;
                // 商品的个数数量要除去顾客买进的数量
                m.count -= numToBuy;
                //超市的那个商品的售出情况要除去顾客买进的数量
                littleSuperMarket.merchandiseSold[index] += numToBuy;
            }//本轮购买结束，进入本轮核算阶段
            //顾客的现金流要除去一共消费的总额
            customer.money -= totalCost;
            //默认顾客本轮消费结束，要开车离去，所以停车位要+1
            if (customer.isDrivingCar) {
                littleSuperMarket.parkingCount++;
            }
            //统计顾客的消费总额
            System.out.println("顾客" + customer.name + "共消费了" + totalCost);
            //把顾客本轮消费算进超市的收入额
            littleSuperMarket.incomingSum += totalCost;
            System.out.println("还继续营业吗？请输入 true/false");
            open = scanner.nextBoolean();//提示输入，是就继续下一轮迎接顾客的循环，否就跳出本次循环进入关门环节
        }
        // 超市是close状态
        System.out.println("超市关门了！");
        System.out.println("今天总的营业额为" + littleSuperMarket.incomingSum + "。营业情况如下：");
        for (int i = 0; i < littleSuperMarket.merchandiseSold.length; i++) {
            Merchandise m = all[i];
            int numSold = littleSuperMarket.merchandiseSold[i];
            if (numSold > 0) {//只要商品有被卖出，就核算销售额和净利润
                double incoming = m.soldPrice * numSold;
                double netIncoming = (m.soldPrice - m.purchasePrice) * numSold;
                System.out.println(m.name + "售出了" + numSold + "个。销售额为" + incoming + "。净利润为" + netIncoming);
            }
        }
    }
}
