/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈数据检查点〉
 *
 * @author hwei
 * @version ApplicationCheckPoint.java, V1.0 2017年2月6日 上午10:23:44
 */
public class ApplicationCheckPoint {
    private String keyValue;// 申请表数据值
    private String gender;// 性别
    private String age;// 年龄
    private String province;// 省份
    private String city;// 城市
    private String region;// 区县
    private String website;// 移动运营商
    private String reliability;// 实名认证
    private String regTime;// 注册时间
    private String checkName;// 姓名检查
    private String checkIdCard;// 身份证号检查
    private String checkEbusiness;// 电商使用号码/地址检查
    private String checkAddr;// 地址检查
    private String relationship;// 联系人关系
    private String contactName;// 联系人姓名
    private String checkXiaohao;// 临时小号检查
    private String checkMobile;// 运营商联系号码检查
    private ApplicationCheckBlackList courtBlacklist;// 法院黑名单检查
    private ApplicationCheckBlackList financialBalcklist;// 金融服务类机构黑名单检查
    
    public String getKeyValue() {
        return keyValue;
    }
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getReliability() {
        return reliability;
    }
    public void setReliability(String reliability) {
        this.reliability = reliability;
    }
    public String getRegTime() {
        return regTime;
    }
    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }
    public String getCheckName() {
        return checkName;
    }
    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }
    public String getCheckIdCard() {
        return checkIdCard;
    }
    public void setCheckIdCard(String checkIdCard) {
        this.checkIdCard = checkIdCard;
    }
    public String getCheckEbusiness() {
        return checkEbusiness;
    }
    public void setCheckEbusiness(String checkEbusiness) {
        this.checkEbusiness = checkEbusiness;
    }
    public String getCheckAddr() {
        return checkAddr;
    }
    public void setCheckAddr(String checkAddr) {
        this.checkAddr = checkAddr;
    }
    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getCheckXiaohao() {
        return checkXiaohao;
    }
    public void setCheckXiaohao(String checkXiaohao) {
        this.checkXiaohao = checkXiaohao;
    }
    public String getCheckMobile() {
        return checkMobile;
    }
    public void setCheckMobile(String checkMobile) {
        this.checkMobile = checkMobile;
    }
    public ApplicationCheckBlackList getCourtBlacklist() {
        return courtBlacklist;
    }
    public void setCourtBlacklist(ApplicationCheckBlackList courtBlacklist) {
        this.courtBlacklist = courtBlacklist;
    }
    public ApplicationCheckBlackList getFinancialBalcklist() {
        return financialBalcklist;
    }
    public void setFinancialBalcklist(ApplicationCheckBlackList financialBalcklist) {
        this.financialBalcklist = financialBalcklist;
    }
    
    
}
