package com.sskj.mine.data;

/**
 * 项目包名：com.sskj.mycenter.bean
 * 项目所属模块：center
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月02日
 * 类描述：个人中心itembean
 * 备注：
 */
public class CentenItemBean {
    private int icon;
    private String item;

    public CentenItemBean(int icon, String item) {
        this.icon = icon;
        this.item = item;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
