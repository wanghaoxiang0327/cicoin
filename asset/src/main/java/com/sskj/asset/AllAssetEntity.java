package com.sskj.asset;

import java.io.Serializable;
import java.util.List;

public class AllAssetEntity implements Serializable {
    public String ttl_money;
    public String ttl_cnymoney;
    public List<Asset> asset;

    public static class Asset {
        public String pid;
        public String pname;
        public String mark;
        public String usable;
        public String frost;
        public String uptime;
        public String cny;
    }
}
