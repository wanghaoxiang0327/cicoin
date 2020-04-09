package com.sskj.common.data;

import java.io.Serializable;

public class CoinListEntity implements Serializable {
    public String pid;
    public String code;
    public String pname;
    public String actprice;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getActprice() {
        return actprice;
    }

    public void setActprice(String actprice) {
        this.actprice = actprice;
    }
}
