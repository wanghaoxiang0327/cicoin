package com.sskj.mine.data;

import java.util.List;

/**
 * 项目包名：com.sskj.mine.data
 * 项目所属模块：资产bean
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月04日
 * 类描述：
 * 备注：
 */
public class MIneBean {


    /**
     * ttl_money : 0
     * ttl_cnymoney : 0
     * asset : [{"pid":0,"pname":"USDT","mark":"usdt_usdt","usable":"0.0000","frost":"0.0000","uptime":1567578367,"cny":0},{"pid":"1","mark":"btc_usdt","pname":"BTC","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"2","mark":"xrp_usdt","pname":"XRP","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"3","mark":"eth_usdt","pname":"ETH","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"5","mark":"ltc_usdt","pname":"LTC","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"6","mark":"eos_usdt","pname":"EOS","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"11","mark":"bch_usdt","pname":"BCH","usable":0,"frost":0,"uptime":1567578367,"cny":0}]
     */

    private double ttl_money;
    private double ttl_cnymoney;
    private List<AssetBean> asset;

    public double getTtl_money() {
        return ttl_money;
    }

    public void setTtl_money(double ttl_money) {
        this.ttl_money = ttl_money;
    }

    public double getTtl_cnymoney() {
        return ttl_cnymoney;
    }

    public void setTtl_cnymoney(double ttl_cnymoney) {
        this.ttl_cnymoney = ttl_cnymoney;
    }

    public List<AssetBean> getAsset() {
        return asset;
    }

    public void setAsset(List<AssetBean> asset) {
        this.asset = asset;
    }

    public static class AssetBean {
        /**
         * pid : 0
         * pname : USDT
         * mark : usdt_usdt
         * usable : 0.0000
         * frost : 0.0000
         * uptime : 1567578367
         * cny : 0
         */

        private int pid;
        private String pname;
        private String mark;
        private String usable;
        private String frost;
        private int uptime;
        private int cny;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUsable() {
            return usable;
        }

        public void setUsable(String usable) {
            this.usable = usable;
        }

        public String getFrost() {
            return frost;
        }

        public void setFrost(String frost) {
            this.frost = frost;
        }

        public int getUptime() {
            return uptime;
        }

        public void setUptime(int uptime) {
            this.uptime = uptime;
        }

        public int getCny() {
            return cny;
        }

        public void setCny(int cny) {
            this.cny = cny;
        }
    }
}
