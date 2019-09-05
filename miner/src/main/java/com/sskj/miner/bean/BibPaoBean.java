package com.sskj.miner.bean;

/**
 * 项目包名：com.sskj.miner.bean
 * 项目所属模块：miner
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月05日
 * 类描述：
 * 备注：
 */
public class BibPaoBean implements IWaterBean {

    private String code;
    private String num;
    private int res;

    public BibPaoBean(String code,String num, int res) {
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
