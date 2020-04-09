package com.sskj.market.data;


import java.io.Serializable;
import java.util.List;

public class TradePushBean implements Serializable {
    public String code;
    public String name;
    public long timestamp;
    public List<TradePush> data;

    public static class TradePush {
        public long dt;
        public String dc;
        public float amount;
        public float price;
    }
}
