package com.sskj.asset.data;

public class ZoomRecordBean {

    /**
     * price : 10
     * ptype : USDT
     * addtime : 1566379222
     * type : 1//1从 杠杆合约账户 到 非杠杆合约账户  2从 非杠杆合约账户 到 杠杆合约账户
     */

    private int price;
    private String ptype;
    private String addtime;
    private int type;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
