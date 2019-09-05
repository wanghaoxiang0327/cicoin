package com.sskj.miner.bean;

import com.google.gson.annotations.SerializedName;
import com.sskj.common.language.LocalManageUtil;

/**
 * 项目包名：com.sskj.miner.bean
 * 项目所属模块：miner
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月05日
 * 类描述：
 * 备注：
 */
public class RuleBean {

    /**
     * id : 2
     * contentCN : 活动规则
     * contentUS : chinese
     * createTime : null
     * type : 2
     * timestamp : 1561106894000
     */

    private String id;
    @SerializedName(value = "contentCN", alternate = {"content"})
    private String contentCN;
    @SerializedName(value = "contentUS", alternate = {"contentUs"})
    private String contentUS;
    private Object createTime;
    private int type;
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentCN() {
        return LocalManageUtil.isSimpleChinese() ? contentCN : contentUS;
    }

    public void setContentCN(String contentCN) {
        this.contentCN = contentCN;
    }

    public String getContentUS() {
        return contentUS;
    }

    public void setContentUS(String contentUS) {
        this.contentUS = contentUS;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
