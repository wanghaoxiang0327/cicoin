package com.sskj.asset.data;

public class TransferInfoBean {


    /**
     * id : 1
     * coin : eth_usdt
     * name : ETH
     * ex_name : USDT
     * ex_coin : usdt_usdt
     * ex_rate : 6000
     * ex_coin_actprice
     * ex_pid : 10
     * status : 1
     * addtime : 0
     */

    private String id;
    private String coin;
    private String name;
    private String ex_name;
    private String ex_coin;
    private String ex_rate;
    private String ex_pid;
    private String status;
    private String addtime;
    private String ex_coin_actprice;

    public String getEx_coin_actprice() {
        return ex_coin_actprice;
    }

    public void setEx_coin_actprice(String ex_coin_actprice) {
        this.ex_coin_actprice = ex_coin_actprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEx_name() {
        return ex_name;
    }

    public void setEx_name(String ex_name) {
        this.ex_name = ex_name;
    }

    public String getEx_coin() {
        return ex_coin;
    }

    public void setEx_coin(String ex_coin) {
        this.ex_coin = ex_coin;
    }

    public String getEx_rate() {
        return ex_rate;
    }

    public void setEx_rate(String ex_rate) {
        this.ex_rate = ex_rate;
    }

    public String getEx_pid() {
        return ex_pid;
    }

    public void setEx_pid(String ex_pid) {
        this.ex_pid = ex_pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
