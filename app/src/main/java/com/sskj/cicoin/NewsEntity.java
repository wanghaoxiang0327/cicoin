package com.sskj.cicoin;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class NewsEntity implements Serializable {
    public String id;
    public String bm_date;
    public String bm_title;
    public String pic_addr;
    public String page_views;
    public String username;
    @JSONField(name = "abs", alternateNames = {"abstract"})
    public String abs;
    public String context;
    public String content;
    public String issue_time;
    public String lang;
    public String timestamp;
}
