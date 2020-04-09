package com.sskj.miner.bean;


import java.util.List;

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
     * sum : 0
     * nowpage : 1
     * nownums : 10
     * data : [{"id":"1","userid":"5","nums":"1234","type":"0","time":"2019-03-27 04:31:02"}]
     */

    private String sum;
    private int nowpage;
    private int nownums;
    private List<DataBean> data;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getNownums() {
        return nownums;
    }

    public void setNownums(int nownums) {
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
         * userid : 5
         * nums : 1234
         * type : 0
         * time : 2019-03-27 04:31:02
         */

        private String id;
        private String userid;
        private String nums;
        private String type;
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

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
