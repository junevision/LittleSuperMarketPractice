package com.geekbang;

import com.geekbang.supermarket.LittleSuperMarket;
import com.geekbang.supermarket.Merchandise;
import java.util.Scanner;

/**
 * 运行超市的主程序
 *
 * @author jun_lei
 */
public class RunLittleSupperMarketAppMainV2 {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket();
        littleSuperMarket.LittleSuperMarket("有家超市", "世纪大道666号",
                100, 200, 200);
        littleSuperMarket.describe();

        Scanner scanner = new Scanner(System.in);
        Merchandise[] all = littleSuperMarket.merchandises;
        while (true) {
            System.out.println("今日超市大特惠，所有商品第二件半价！请选择要购买的商品索引：");
            int index = scanner.nextInt();

            if (index < 0) {
                System.out.println("欢迎下次光临");
                break;
            }
            if (index >= all.length) {
                System.out.println("商品索引超出界限");
                continue;
            }
            Merchandise m = all[index];
            System.out.println("商品" + m.name + "售价为" + m.soldPrice + "。请问要购买几个？");
            int count = scanner.nextInt();
            double totalCost = m.buyWithHalfPrice(count);
            System.out.println("选购的商品总价为" + totalCost);
        }
    }
}
