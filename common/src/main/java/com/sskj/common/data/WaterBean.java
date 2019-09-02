package com.sskj.common.data;

import com.alibaba.fastjson.annotation.JSONField;
import com.sskj.common.view.WaterView;

public class WaterBean {

    /**
     * id : 5
     * userid : 6
     * usdt_num : 0.00
     * gtc_num : 83.33
     * status : 0
     * type : 2
     */

    private int type;
    private String id;
    private int status;
    @JSONField(name = "pro_get")
    private String num;

    public WaterBean(){

    }

    public WaterBean(String num) {

        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
