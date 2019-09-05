package com.sskj.contact.data;

import java.io.Serializable;

public class DealOrder implements Serializable {

    /**
     * pname : BTC
     * otype : 2
     * buynum : 1
     * buyprice : 10121.9600
     * totalprice : 506.0980
     * sxfee : 202.5421
     * dayfee : 2.0244
     * sellprice : 10132.2500
     * leverage : 20
     * addtime : 1566903118
     * selltime : 1566977485
     * profit : -10.2900
     * pc_type : 1
     * type : 1
     * poit_win : 0.0000
     * poit_loss : 0.0000
     */

    private String pname;
    private int otype;
    private String buynum;
    private String buyprice;
    private String totalprice;
    private String sxfee;
    private String dayfee;
    private String sellprice;
    private String leverage;
    private long addtime;
    private long selltime;
    private String profit;
    private int pc_type;
    private int type;
    private String poit_win;
    private String poit_loss;


    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getOtype() {
        return otype;
    }

    public void setOtype(int otype) {
        this.otype = otype;
    }

    public String getBuynum() {
        return buynum;
    }

    public void setBuynum(String buynum) {
        this.buynum = buynum;
    }

    public String getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(String buyprice) {
        this.buyprice = buyprice;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getSxfee() {
        return sxfee;
    }

    public void setSxfee(String sxfee) {
        this.sxfee = sxfee;
    }

    public String getDayfee() {
        return dayfee;
    }

    public void setDayfee(String dayfee) {
        this.dayfee = dayfee;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }

    public long getAddtime() {
        return addtime;
    }

    public void setAddtime(long addtime) {
        this.addtime = addtime;
    }

    public long getSelltime() {
        return selltime;
    }

    public void setSelltime(long selltime) {
        this.selltime = selltime;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public int getPc_type() {
        return pc_type;
    }

    public void setPc_type(int pc_type) {
        this.pc_type = pc_type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPoit_win() {
        return poit_win;
    }

    public void setPoit_win(String poit_win) {
        this.poit_win = poit_win;
    }

    public String getPoit_loss() {
        return poit_loss;
    }

    public void setPoit_loss(String poit_loss) {
        this.poit_loss = poit_loss;
    }
}
