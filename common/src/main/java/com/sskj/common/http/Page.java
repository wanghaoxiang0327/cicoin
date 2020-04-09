package com.sskj.common.http;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Page<T> {

    @JSONField(name = "res", alternateNames = {"res", "list", "product_list", "data"})
    private List<T> res;
    @JSONField(name = "p", alternateNames = {"p", "nowpage"})
    private int p;
    @JSONField(name = "count", alternateNames = {"count", "sum"})
    private int count;
    @JSONField(name = "size", alternateNames = {"size", "nownums"})
    private int size;

    public List<T> getRes() {
        return res;
    }

    public void setRes(List<T> res) {
        this.res = res;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
