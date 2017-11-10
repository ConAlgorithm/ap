package omni.database.catfish.object.sales;

import java.io.Serializable;
import java.util.List;

public class UserGetUserInfoReqModel implements Serializable{
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String mobile;
    private String userId;
    private String idnumber;
    private String idname;
    private boolean needQueryOldUserExtInfo = false;
    private List<String> busilineCodeList;
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getIdnumber() {
        return idnumber;
    }
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }
    public String getIdname() {
        return idname;
    }
    public void setIdname(String idname) {
        this.idname = idname;
    }
    public boolean isNeedQueryOldUserExtInfo() {
        return needQueryOldUserExtInfo;
    }
    public void setNeedQueryOldUserExtInfo(boolean needQueryOldUserExtInfo) {
        this.needQueryOldUserExtInfo = needQueryOldUserExtInfo;
    }
    public List<String> getBusilineCodeList() {
        return busilineCodeList;
    }
    public void setBusilineCodeList(List<String> busilineCodeList) {
        this.busilineCodeList = busilineCodeList;
    }
    
}
