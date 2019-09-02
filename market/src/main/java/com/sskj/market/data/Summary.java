package com.sskj.market.data;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Summary {

        /**
         * pname : BTC_USDT
         * code : btc_usdt
         * fxtime : 2008-11-01
         * fxnum : 2100.00
         * fxprice : 0.00
         * fxweb : https://bitcoin.org/en/
         * fxbook : https://bitcoin.org/bitcoin.pdf
         * memo : 比特币（Bitcoin，简称BTC）是目前使用最为广泛的一种数字货币，它诞生于2009年1月3日，是一种点对点（P2P）传输的数字加密货币，总量2100万枚。比特币网络每10分钟释放出一定数量币，预计在2140年达到极限。比特币被投资者称为“数字黄金”。比特币依据特定算法，通过大量的计算产生，不依靠特定货币机构发行，其使用整个P2P网络中众多节点构成的分布式数据库来确认并记录所有的交易行为，并使用密码学设计确保货币流通各个环节安全性，可确保无法通过大量制造比特币来人为操控币值。基于密码学的设计可以使比特币只能被真实拥有者转移、支付及兑现。同样确保了货币所有权与流通交易的匿名性。

         */

        private String pname;
        private String code;
        private String fxtime;
        private String fxnum;
        private String fxprice;
        private String fxweb;
        private String fxbook;
        private String memo;

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFxtime() {
            return fxtime;
        }

        public void setFxtime(String fxtime) {
            this.fxtime = fxtime;
        }

        public String getFxnum() {
            return fxnum;
        }

        public void setFxnum(String fxnum) {
            this.fxnum = fxnum;
        }

        public String getFxprice() {
            return fxprice;
        }

        public void setFxprice(String fxprice) {
            this.fxprice = fxprice;
        }

        public String getFxweb() {
            return fxweb;
        }

        public void setFxweb(String fxweb) {
            this.fxweb = fxweb;
        }

        public String getFxbook() {
            return fxbook;
        }

        public void setFxbook(String fxbook) {
            this.fxbook = fxbook;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
}
