package com.sskj.common.user.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.annotation.JSONField;

import org.jetbrains.annotations.NotNull;

/**
 * 用户信息
 *
 * @author Hey
 */
@Entity(tableName = "user_data")
public class UserBean {

    /**
     * uid : 52034171
     * mobile : 15738733951
     * mail :
     * nickname : 山人
     * tgno : AKG52034171
     * user_level : 1
     * register_type : 1
     * is_start_google : 0
     * is_start_sms : 1
     * is_bd_mail : 0
     * zc_total : {"ttl_usable":5.4466040608038E7,"ttl_frost":0,"ttl_money":5.4466040608038E7,"ttl_cnymoney":"375815680.20"}
     */

    @PrimaryKey
    @NotNull
    private String uid;
    private String mobile;
    private String mail;
    private String nickname;
    private String email;
    private String tgno;
    //资金密码
    private String tpwd;
    @JSONField(name = "command")
    private int isBindGoogle;
    @JSONField(name = "user_level")
    private String userLevel;
    @JSONField(name = "register_type")
    private String registerType;
    //是否开启谷歌验证 0关  1开
    @JSONField(name = "is_start_google")
    private int isStartGoogle;
    //短信验证  0关  1开
    @JSONField(name = "is_start_sms")
    private int isStartSms;
    //是否绑定邮箱
    @JSONField(name = "is_bd_mail")
    private int isBindMail;
    @JSONField(name = "zc_total")
    @Embedded
    private ZcTotalBean zcTotal;


    // "wallone": "63400.3496",购买锁仓可用金额
    private String wallone;
    // "walltwo": "0.0000",法币冻结
    private String walltwo;
    //"wallthree": "0.0000",待售
    private String wallthree;
    //"wallfour": "0.0000",可售
    private String wallfour;
    //"wallfive": "0.0000",成为商家冻结
    private String wallfive;
    // "apply_reason": "",认证拒绝原因
    private String apply_reason;

    //  "shop_fee": "",成为商家费用
    private String shop_fee;
    //  "sxfee": ""法币交易手续费
    private String sxfee;

    //初级认证状态  1 未认证 2 待审核 3 已通过  4拒绝
    private int status;

    // 高级认证状态 1 未认证 2 待审核 3 已通过  4拒绝
    //是否是商家 0否 1是 2审核中
    private int auth_status;
    private int is_shop;

    //经纪人 0非经纪人  1初级经纪人  2中级  3高级
    private int rank;
    //0：没签到  1 已签到
    private int qd;

    //是否是董事
    private int is_ds;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    public int getIs_ds() {
        return is_ds;
    }

    public void setIs_ds(int is_ds) {
        this.is_ds = is_ds;
    }

    public String getTpwd() {
        return tpwd;
    }

    public void setTpwd(String tpwd) {
        this.tpwd = tpwd;
    }

    public int getIsBindGoogle() {
        return isBindGoogle;
    }

    public void setIsBindGoogle(int isBindGoogle) {
        this.isBindGoogle = isBindGoogle;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public String getWallone() {
        return wallone;
    }

    public void setWallone(String wallone) {
        this.wallone = wallone;
    }

    public String getWalltwo() {
        return walltwo;
    }

    public void setWalltwo(String walltwo) {
        this.walltwo = walltwo;
    }

    public String getWallthree() {
        return wallthree;
    }

    public void setWallthree(String wallthree) {
        this.wallthree = wallthree;
    }

    public String getWallfour() {
        return wallfour;
    }

    public void setWallfour(String wallfour) {
        this.wallfour = wallfour;
    }

    public String getWallfive() {
        return wallfive;
    }

    public void setWallfive(String wallfive) {
        this.wallfive = wallfive;
    }

    public String getApply_reason() {
        return apply_reason;
    }

    public void setApply_reason(String apply_reason) {
        this.apply_reason = apply_reason;
    }

    public String getShop_fee() {
        return shop_fee;
    }

    public void setShop_fee(String shop_fee) {
        this.shop_fee = shop_fee;
    }

    public String getSxfee() {
        return sxfee;
    }

    public void setSxfee(String sxfee) {
        this.sxfee = sxfee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAuth_status() {
        return auth_status;
    }

    public void setAuth_status(int auth_status) {
        this.auth_status = auth_status;
    }

    public int getIs_shop() {
        return is_shop;
    }

    public void setIs_shop(int is_shop) {
        this.is_shop = is_shop;
    }

    public int getQd() {
        return qd;
    }

    public void setQd(int qd) {
        this.qd = qd;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTgno() {
        return tgno;
    }

    public void setTgno(String tgno) {
        this.tgno = tgno;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public int getIsStartGoogle() {
        return isStartGoogle;
    }

    public void setIsStartGoogle(int isStartGoogle) {
        this.isStartGoogle = isStartGoogle;
    }

    public int getIsStartSms() {
        return isStartSms;
    }

    public void setIsStartSms(int isStartSms) {
        this.isStartSms = isStartSms;
    }

    public int getIsBindMail() {
        return isBindMail;
    }

    public void setIsBindMail(int isBindMail) {
        this.isBindMail = isBindMail;
    }

    public ZcTotalBean getZcTotal() {
        return zcTotal;
    }

    public void setZcTotal(ZcTotalBean zcTotal) {
        this.zcTotal = zcTotal;
    }


    public static class ZcTotalBean {
        /**
         * ttl_usable : 5.4466040608038E7
         * ttl_frost : 0
         * ttl_money : 5.4466040608038E7
         * ttl_cnymoney : 375815680.20
         */

        @ColumnInfo
        private double ttl_usable;
        @ColumnInfo
        private double ttl_frost;
        @ColumnInfo
        private double ttl_money;
        @ColumnInfo
        private String ttl_cnymoney;


        public double getTtl_usable() {
            return ttl_usable;
        }

        public void setTtl_usable(double ttl_usable) {
            this.ttl_usable = ttl_usable;
        }

        public double getTtl_frost() {
            return ttl_frost;
        }

        public void setTtl_frost(double ttl_frost) {
            this.ttl_frost = ttl_frost;
        }

        public double getTtl_money() {
            return ttl_money;
        }

        public void setTtl_money(double ttl_money) {
            this.ttl_money = ttl_money;
        }

        public String getTtl_cnymoney() {
            return ttl_cnymoney;
        }

        public void setTtl_cnymoney(String ttl_cnymoney) {
            this.ttl_cnymoney = ttl_cnymoney;
        }
    }
}
