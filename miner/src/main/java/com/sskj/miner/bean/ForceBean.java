package com.sskj.miner.bean;


import com.sskj.common.utils.TimeFormatUtil;

import java.util.Date;

/**
 * 项目包名：com.sskj.miner.bean
 * 项目所属模块：miner
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月05日
 * 类描述：
 * 备注：
 */
public class ForceBean {

    /**
     * id : 1959
     * stockUserId : 180
     * forceNum : 300.0
     * type : 2
     * createTime : 1561365318000
     */

    private String id;
    private String stockUserId;
    private double forceNum;
    private int type;
    private long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockUserId() {
        return stockUserId;
    }

    public void setStockUserId(String stockUserId) {
        this.stockUserId = stockUserId;
    }

    public double getForceNum() {
        return forceNum;
    }

    public void setForceNum(double forceNum) {
        this.forceNum = forceNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_E.format(new Date(createTime));
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
