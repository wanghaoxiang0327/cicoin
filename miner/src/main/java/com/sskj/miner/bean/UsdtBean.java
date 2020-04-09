package com.sskj.miner.bean;

import java.util.List;

/**
 * 项目包名：com.sskj.miner.bean
 * 项目所属模块：
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月05日
 * 类描述：
 * 备注：
 */
public class UsdtBean {

    /**
     * sum : 0
     * nowpage : 1
     * nownums : 3
     * data : [{"id":"1","userid":"21","task":"0","type":"usdt_num","num":"0.83","time":"2019-04-01 11:48:04"},{"id":"2","userid":"21","task":"0","type":"gtc_num","num":"0.83","time":"2019-04-01 03:26:21"},{"id":"3","userid":"21","task":"0","type":"usdt_num","num":"0.83","time":"2019-04-01 03:26:25"}]
     */

    private String sum;
    private String nowpage;
    private String nownums;
    private List<DataBean> data;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getNowpage() {
        return nowpage;
    }

    public void setNowpage(String nowpage) {
        this.nowpage = nowpage;
    }

    public String getNownums() {
        return nownums;
    }

    public void setNownums(String nownums) {
        this.nownums = nownums;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * userid : 21
         * task : 0
         * type : usdt_num
         * num : 0.83
         * time : 2019-04-01 11:48:04
         */

        private String id;
        private String userid;
        private String task;
        private String type;
        private String num;
        private String time;

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

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
