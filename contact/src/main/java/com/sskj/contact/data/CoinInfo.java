package com.sskj.contact.data;

public class CoinInfo {


    /**
     * code : BTC/USDT
     * leverage : 0
     * num_min : 0.00
     * stoploss : 0
     * stopwin : 0
     * spread : 0.00000000
     * var_price : 1.00000000
     * trans_ware : 50%
     * trans_fee : %
     */

    private String code;
    private String leverage;
    private double num_min;
    private String stoploss;
    private String stopwin;
    //点差
    private String spread;
    //最小变动价
    private String var_price;
    //爆仓率
    private String trans_ware;
    //手续费
    private String trans_fee;
    //张单价
    private String pcs_price;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }

    public double getNum_min() {
        return num_min;
    }

    public void setNum_min(double num_min) {
        this.num_min = num_min;
    }

    public String getStoploss() {
        return stoploss;
    }

    public void setStoploss(String stoploss) {
        this.stoploss = stoploss;
    }

    public String getStopwin() {
        return stopwin;
    }

    public void setStopwin(String stopwin) {
        this.stopwin = stopwin;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getVar_price() {
        return var_price;
    }

    public void setVar_price(String var_price) {
        this.var_price = var_price;
    }

    public String getTrans_ware() {
        return trans_ware;
    }

    public void setTrans_ware(String trans_ware) {
        this.trans_ware = trans_ware;
    }

    public String getTrans_fee() {
        return trans_fee;
    }

    public void setTrans_fee(String trans_fee) {
        this.trans_fee = trans_fee;
    }

    public String getPcs_price() {
        return pcs_price;
    }

    public void setPcs_price(String pcs_price) {
        this.pcs_price = pcs_price;
    }
}
