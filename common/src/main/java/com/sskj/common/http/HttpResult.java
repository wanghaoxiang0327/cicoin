package com.sskj.common.http;


import com.alibaba.fastjson.annotation.JSONField;

public class HttpResult<T> {
    private String msg;
    @JSONField(alternateNames = {"status", "code"})
    private int status;
    private T data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
