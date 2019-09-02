package com.sskj.common.data;

public class LanguageType {

    /**
     * id : 1
     * title : 充值
     */

    private int id;
    private String title;

    public LanguageType(String title,int id) {
        this.title = title;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
