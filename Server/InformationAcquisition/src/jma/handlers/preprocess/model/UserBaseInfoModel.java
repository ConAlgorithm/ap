/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers.preprocess.model;

/**
 * 〈用户基本信息〉
 *
 * @author hwei
 * @version UserBaseInfoModel.java, V1.0 2016年11月21日 下午2:56:17
 */
public class UserBaseInfoModel {
    private String name;
    private String idNo;
    private String mobile;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
}
