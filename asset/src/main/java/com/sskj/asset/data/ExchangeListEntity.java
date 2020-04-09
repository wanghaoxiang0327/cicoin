package com.sskj.asset.data;

import java.io.Serializable;
import java.util.List;

public class ExchangeListEntity implements Serializable {
    public List<Exchange> list;

    public static class Exchange {
        public String num;
        public String exnum;
        public String pname;
        public String expname;
        public String memo;
        public String addtime;
    }
}
