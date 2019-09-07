package com.sskj.common.http;

public class ApiException extends Throwable {
    private String msg;
    private int code;

    public ApiException(String msg) {
        this.msg = msg;
    }

    public ApiException(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
