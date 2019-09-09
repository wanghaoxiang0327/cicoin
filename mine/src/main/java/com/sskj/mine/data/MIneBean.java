package com.sskj.mine.data;

import java.util.List;

/**
 * 项目包名：com.sskj.mine.data
 * 项目所属模块：资产bean
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月04日
 * 类描述：
 * 备注：
 */
public class MIneBean {


    /**
     * ttl_money : 0
     * ttl_cnymoney : 0
     * asset : [{"pid":0,"pname":"USDT","mark":"usdt_usdt","usable":"0.0000","frost":"0.0000","uptime":1567578367,"cny":0},{"pid":"1","mark":"btc_usdt","pname":"BTC","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"2","mark":"xrp_usdt","pname":"XRP","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"3","mark":"eth_usdt","pname":"ETH","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"5","mark":"ltc_usdt","pname":"LTC","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"6","mark":"eos_usdt","pname":"EOS","usable":0,"frost":0,"uptime":1567578367,"cny":0},{"pid":"11","mark":"bch_usdt","pname":"BCH","usable":0,"frost":0,"uptime":1567578367,"cny":0}]
     */

    private String ttl_money;
    private String  ttl_cnymoney;

    public String getTtl_money() {
        return ttl_money;
    }

    public void setTtl_money(String ttl_money) {
        this.ttl_money = ttl_money;
    }

    public String getTtl_cnymoney() {
        return ttl_cnymoney;
    }

    public void setTtl_cnymoney(String ttl_cnymoney) {
        this.ttl_cnymoney = ttl_cnymoney;
    }
}
