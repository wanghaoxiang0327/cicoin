package com.sskj.common.data;

import java.util.List;

public class CoinAsset {


    /**
     * pid : 2
     * pname : BTC
     * name : 比特币
     * aim_point : ["0.01","0.1","1"]
     */

    private String pid;
    private String pname;
    private String name;
    private double yue;
    private String price;
    private String cnyPrice;
    private String mark;

    public CoinAsset() {
    }

    public CoinAsset(String pid, String name, String price, String mark) {
        this.pid = pid;
        this.pname = name;
        this.price = price;
        this.mark = mark;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCnyPrice() {
        return cnyPrice;
    }

    public void setCnyPrice(String cnyPrice) {
        this.cnyPrice = cnyPrice;
    }

    //单位交易量
    private List<String> aim_point;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        if (pname.contains("_")) {
            return pname.substring(0, pname.indexOf("_"));
        }
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAim_point() {
        return aim_point;
    }

    public double getYue() {
        return yue;
    }

    public void setYue(double yue) {
        this.yue = yue;
    }

    public void setAim_point(List<String> aim_point) {
        this.aim_point = aim_point;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}
