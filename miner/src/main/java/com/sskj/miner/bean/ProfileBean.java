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
public class ProfileBean {

    /**
     * id : 1778
     * stockUserId : 199
     * dealNum : 1.0
     * stockCode : USDT
     * type : 6
     * createTime : 1561438762000
     * isDeleted : false
     */

    private String id;
    private String stockUserId;
    private double dealNum;
    private String stockCode;
    private int type;
    private long createTime;
    private boolean isDeleted;

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

    public double getDealNum() {
        return dealNum;
    }

    public void setDealNum(double dealNum) {
        this.dealNum = dealNum;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
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

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
