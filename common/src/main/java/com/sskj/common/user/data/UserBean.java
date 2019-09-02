package com.sskj.common.user.data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * 用户信息
 *
 * @author Hey
 */
@Entity(tableName = "user_data")
public class UserBean {

    /**
     * account : 231783
     * userid : 3
     * grade : 1
     * mobile : 15901791596
     * realname : null
     * upic : null
     * wallone : 888.00
     * idcard : null
     * type : 0
     * status : 1
     */

    @PrimaryKey
    @NonNull
    private Integer userid;
    private String account;
    private String grade;
    private String mobile;
    private String realname;
    private String upic;
    private String wallone;
    private String idcard;

    /**
     *    初级身份认证 0 未认证 1 认证
     */
    private String type;
    /**
     *1 未认证 2 待审核 3 已通过  4拒绝
     */
    private String status;
    /**
     * 是否设置密码 1是 0否
     */
    private int is_tpwd;

    public int getIs_tpwd() {
        return is_tpwd;
    }

    public void setIs_tpwd(int is_tpwd) {
        this.is_tpwd = is_tpwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @NonNull
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(@NonNull Integer userid) {
        this.userid = userid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUpic() {
        return upic;
    }

    public void setUpic(String upic) {
        this.upic = upic;
    }

    public String getWallone() {
        return wallone;
    }

    public void setWallone(String wallone) {
        this.wallone = wallone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
