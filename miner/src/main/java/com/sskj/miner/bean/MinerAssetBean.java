package com.sskj.miner.bean;

/**
 * 项目包名：com.sskj.miner.bean
 * 项目所属模块：miner
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月05日
 * 类描述：全部收益接口
 * 备注：
 */
public class MinerAssetBean {

    /**
     * mineForce : 888.8
     * totalUsdt : 100
     * totalBib : 100
     */

    private String mineForce;
    private String totalUsdt;
    private String totalBib;

    public String getMineForce() {
        return mineForce;
    }

    public void setMineForce(String mineForce) {
        this.mineForce = mineForce;
    }

    public String getTotalUsdt() {
        return totalUsdt;
    }

    public void setTotalUsdt(String totalUsdt) {
        this.totalUsdt = totalUsdt;
    }

    public String getTotalBib() {
        return totalBib;
    }

    public void setTotalBib(String totalBib) {
        this.totalBib = totalBib;
    }
}
