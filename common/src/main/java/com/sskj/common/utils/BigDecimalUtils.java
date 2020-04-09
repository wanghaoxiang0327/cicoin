package com.sskj.common.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    //两个字符串相乘
    public static BigDecimal multiply(String v1, String v2) {
        if (TextUtils.isEmpty(v1) || TextUtils.isEmpty(v2)) {
            return new BigDecimal(0);
        } else {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.multiply(b2);
        }
    }

    //两个字符串相加
    public static BigDecimal add(String v1, String v2) {
        if (TextUtils.isEmpty(v1) || TextUtils.isEmpty(v2)) {
            return new BigDecimal(0);
        } else {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.add(b2);
        }
    }

    //相除以
    public static BigDecimal divide(String v1, String v2) {
        if (TextUtils.isEmpty(v1) || TextUtils.isEmpty(v2)) {
            return new BigDecimal(0);
        } else {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.divide(b2);
        }
    }

    //相减
    public static BigDecimal subtract(String v1, String v2) {
        if (TextUtils.isEmpty(v1) || TextUtils.isEmpty(v2)) {
            return new BigDecimal(0);
        } else {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.subtract(b2);
        }
    }

    //比较两个字符串数字大小  如果v1>=v2 返回true
    public static boolean compareTo(String v1, String v2) {
        if (TextUtils.isEmpty(v1) || TextUtils.isEmpty(v2)) {
            return false;
        } else {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            //如果 b1>b2  返回1
            //如果 b1<b2  返回-1
            //如果 b1=b2  返回0
            return b1.compareTo(b2) >= 0;
        }
    }
}
