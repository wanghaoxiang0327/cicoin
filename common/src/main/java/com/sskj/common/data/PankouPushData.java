package com.sskj.common.data;

import java.io.Serializable;
import java.util.List;

public class PankouPushData implements Serializable {
    public String code;
    public String name;
    public long timestamp;
    public List<PanKou> data;

    public static class PanKou {
        public String dt;
        public String dc;
        public float amount;
        public float price;
    }
}
