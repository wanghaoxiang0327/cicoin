package com.sskj.asset.data;

import java.util.List;

public class GAssetBean {

    /**
     * count : 1
     * res : {"total":{"ttl_usable":"20.0000","ttl_frost":0,"ttl_money":"20.0000","ttl_cnymoney":"140.64"},"asset":[{"pname":"USDT","mark":"USDT","usable":"20.0000","frost":0}]}
     */

    private int count;
    private ResBean res;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * total : {"ttl_usable":"20.0000","ttl_frost":0,"ttl_money":"20.0000","ttl_cnymoney":"140.64"}
         * asset : [{"pname":"USDT","mark":"USDT","usable":"20.0000","frost":0}]
         */

        private TotalBean total;
        private List<AssetBean> asset;

        public TotalBean getTotal() {
            return total;
        }

        public void setTotal(TotalBean total) {
            this.total = total;
        }

        public List<AssetBean> getAsset() {
            return asset;
        }

        public void setAsset(List<AssetBean> asset) {
            this.asset = asset;
        }

        public static class TotalBean {
            /**
             * ttl_usable : 20.0000
             * ttl_frost : 0
             * ttl_money : 20.0000
             * ttl_cnymoney : 140.64
             */

            private String ttl_usable;
            private int ttl_frost;
            private String ttl_money;
            private String ttl_cnymoney;

            public String getTtl_usable() {
                return ttl_usable;
            }

            public void setTtl_usable(String ttl_usable) {
                this.ttl_usable = ttl_usable;
            }

            public int getTtl_frost() {
                return ttl_frost;
            }

            public void setTtl_frost(int ttl_frost) {
                this.ttl_frost = ttl_frost;
            }

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

        public static class AssetBean {
            /**
             * pname : USDT
             * mark : USDT
             * usable : 20.0000
             * frost : 0
             */

            private String pname;
            private String mark;
            private String usable;
            private int frost;

            public String getPname() {
                return pname;
            }

            public void setPname(String pname) {
                this.pname = pname;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public String getUsable() {
                return usable;
            }

            public void setUsable(String usable) {
                this.usable = usable;
            }

            public int getFrost() {
                return frost;
            }

            public void setFrost(int frost) {
                this.frost = frost;
            }
        }
    }
}
