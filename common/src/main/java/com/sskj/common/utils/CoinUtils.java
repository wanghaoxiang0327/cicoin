package com.sskj.common.utils;

/**
 * package:com.sskj.common.utils
 * author:布兜小爱
 * e-main:budou1994@qq.com
 * date:2019年09月08日
 * desc:
 */
public class CoinUtils {

    public static String cny(String cny) {
        return NumberUtils.keep2(cny);
    }

    public static String price(String price) {
        return NumberUtils.keep4(price);
    }

    //资产所有数量保持八位小数，并去除无效零
    public static String zcNum(String num) {
        return NumberUtils.keepMaxDown(num, 8);
    }
}
