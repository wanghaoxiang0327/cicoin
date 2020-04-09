package com.sskj.common.data;

public class CycleCoinAsset {

    /**
     * pid : 1
     * pname : USDT
     * name : 泰达币
     * min_num : 20
     * max_num : 100
     * yue : 159106.2218
     */

    private String pid;
    private String pname;
    private String name;
    private String min_num;
    private String max_num;
    private double yue;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
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

    public String getMin_num() {
        return min_num;
    }

    public void setMin_num(String min_num) {
        this.min_num = min_num;
    }

    public String getMax_num() {
        return max_num;
    }

    public void setMax_num(String max_num) {
        this.max_num = max_num;
    }

    public double getYue() {
        return yue;
    }

    public void setYue(double yue) {
        this.yue = yue;
    }
}
