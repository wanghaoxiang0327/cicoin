package com.sskj.miner.bean;

public class UsdtPaoBean implements IWaterBean {

    private String code;
    private String num;
    private int res;

    public UsdtPaoBean(String code, String num, int res) {
        this.code = code;
        this.num = num;
        this.res = res;
    }

    @Override
    public String getNum() {
        return num;
    }

    @Override
    public int getRes() {
        return res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
