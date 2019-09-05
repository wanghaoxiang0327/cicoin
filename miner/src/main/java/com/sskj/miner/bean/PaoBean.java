package com.sskj.miner.bean;

import com.sskj.miner.R;

/**
 * 项目包名：com.sskj.miner.bean
 * 项目所属模块：miner
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月05日
 * 类描述：
 * 备注：
 */
public class PaoBean implements IWaterBean {


    /**
     * id : 6
     * userid : 3
     * usdt_num : 0.00
     * gtc_num : 0.00
     * status : 31
     */

    private String id;
    private String userid;
    private String usdt_num;
    private String gtc_num;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsdt_num() {
        return usdt_num;
    }

    public void setUsdt_num(String usdt_num) {
        this.usdt_num = usdt_num;
    }

    public String getGtc_num() {
        return gtc_num;
    }

    public void setGtc_num(String gtc_num) {
        this.gtc_num = gtc_num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getNum() {
        return usdt_num;
    }

    @Override
    public int getRes() {
        return Integer.parseInt(status) <= 3 ? R.drawable.miner_icon_usdt_small : R.drawable.miner_icon_usdt_big;
    }
}