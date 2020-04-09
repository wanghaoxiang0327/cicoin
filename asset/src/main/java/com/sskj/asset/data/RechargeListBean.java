package com.sskj.asset.data;

import java.util.List;

/**
 * package:com.sskj.asset.data
 * author:布兜小爱
 * e-main:budou1994@qq.com
 * date:2019年09月06日
 * desc:充币记录bean
 */
public class RechargeListBean {

    /**
     * count : 1
     * p : 1
     * size : 15
     * res : [{"rec_id":"9","ordnum":"CZzNDGSNjK","userid":"9","account":"877434536","chongzhi_url":null,"username":"刘*雨","ti_id":null,"price":"1000000.0000","state":"2","addtime":"1567560452","check_time":"1567560452","memo":"imcoin充值","ptype":"1","tpath":"1,2,4,5","pid":"0","cny_price":"0.0000"}]
     */

    private int count;
    private String p;
    private String size;
    private List<ResBean> res;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * rec_id : 9
         * ordnum : CZzNDGSNjK
         * userid : 9
         * account : 877434536
         * chongzhi_url : null
         * username : 刘*雨
         * ti_id : null
         * price : 1000000.0000
         * state : 2
         * addtime : 1567560452
         * check_time : 1567560452
         * memo : imcoin充值
         * ptype : 1
         * tpath : 1,2,4,5
         * pid : 0
         * cny_price : 0.0000
         */

        private String rec_id;
        private String ordnum;
        private String userid;
        private String account;
        private String chongzhi_url;
        private String username;
        private String ti_id;
        private String price;
        private String state;
        private String addtime;
        private String check_time;
        private String memo;
        private String ptype;
        private String tpath;
        private String pid;
        private String cny_price;

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getOrdnum() {
            return ordnum;
        }

        public void setOrdnum(String ordnum) {
            this.ordnum = ordnum;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getChongzhi_url() {
            return chongzhi_url;
        }

        public void setChongzhi_url(String chongzhi_url) {
            this.chongzhi_url = chongzhi_url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getTi_id() {
            return ti_id;
        }

        public void setTi_id(String ti_id) {
            this.ti_id = ti_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public String getTpath() {
            return tpath;
        }

        public void setTpath(String tpath) {
            this.tpath = tpath;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getCny_price() {
            return cny_price;
        }

        public void setCny_price(String cny_price) {
            this.cny_price = cny_price;
        }
    }
}
