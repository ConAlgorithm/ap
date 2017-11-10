package omni.database.catfish.object.sales;

import java.util.Date;
import java.util.List;

public class UserGetUserInfoRespModel {
    private Integer id;
    private String busilineId;
    private String userId;
    private String idname;
    private String idnumber;
    private String number;
    private String address;
    private String nation;
    private Byte gender;
    private String mobile;
    private String weixinId;
    private Integer status;
    private Byte deletedFlag;
    private String createdOn;
    private String createdBy;
    private String modifiedOn;
    private String modifiedBy;
    private List<UserSelectUserRoleByCondOldUserResp> oldUserExtInfoList;
    private static final long serialVersionUID = 1L;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getBusilineId() {
        return busilineId;
    }
    public void setBusilineId(String busilineId) {
        this.busilineId = busilineId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getIdname() {
        return idname;
    }
    public void setIdname(String idname) {
        this.idname = idname;
    }
    public String getIdnumber() {
        return idnumber;
    }
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public Byte getGender() {
        return gender;
    }
    public void setGender(Byte gender) {
        this.gender = gender;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getWeixinId() {
        return weixinId;
    }
    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Byte getDeletedFlag() {
        return deletedFlag;
    }
    public void setDeletedFlag(Byte deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
    public String getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getModifiedOn() {
        return modifiedOn;
    }
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    public String getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public List<UserSelectUserRoleByCondOldUserResp> getOldUserExtInfoList() {
        return oldUserExtInfoList;
    }
    public void setOldUserExtInfoList(List<UserSelectUserRoleByCondOldUserResp> oldUserExtInfoList) {
        this.oldUserExtInfoList = oldUserExtInfoList;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
