/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

import java.util.List;

/**
 * 〈用户查询信息〉
 *
 * @author hwei
 * @version UserCheckSearchInfo.java, V1.0 2017年2月6日 上午9:57:16
 */
public class UserCheckSearchInfo {
    // 查询过该用户的相关企业数量
    private Integer searchedOrgCnt;
    // 查询过该用户的相关企业类型
    private List<String> searchedOrgType;
    // 身份证组合过的其他姓名
    private List<String> idcardWithOtherNames;
    // 身份证组合过其他电话
    private List<String> idcardWithOtherPhones;
    // 电话号码组合过其他姓名
    private List<String> phoneWithOtherNames;
    // 电话号码组合过其他身份证
    private List<String> phoneWithOtherIdcards;
    // 电话号码注册过的相关企业数量
    private Integer registerOrgCnt;
    // 电话号码注册过的相关企业类型
    private List<String> registerOrgType;
    // 电话号码出现过的公开网站
    private List<String> arisedOpenWeb;
    public Integer getSearchedOrgCnt() {
        return searchedOrgCnt;
    }
    public void setSearchedOrgCnt(Integer searchedOrgCnt) {
        this.searchedOrgCnt = searchedOrgCnt;
    }
    public List<String> getSearchedOrgType() {
        return searchedOrgType;
    }
    public void setSearchedOrgType(List<String> searchedOrgType) {
        this.searchedOrgType = searchedOrgType;
    }
    public List<String> getIdcardWithOtherNames() {
        return idcardWithOtherNames;
    }
    public void setIdcardWithOtherNames(List<String> idcardWithOtherNames) {
        this.idcardWithOtherNames = idcardWithOtherNames;
    }
    public List<String> getIdcardWithOtherPhones() {
        return idcardWithOtherPhones;
    }
    public void setIdcardWithOtherPhones(List<String> idcardWithOtherPhones) {
        this.idcardWithOtherPhones = idcardWithOtherPhones;
    }
    public List<String> getPhoneWithOtherNames() {
        return phoneWithOtherNames;
    }
    public void setPhoneWithOtherNames(List<String> phoneWithOtherNames) {
        this.phoneWithOtherNames = phoneWithOtherNames;
    }
    public List<String> getPhoneWithOtherIdcards() {
        return phoneWithOtherIdcards;
    }
    public void setPhoneWithOtherIdcards(List<String> phoneWithOtherIdcards) {
        this.phoneWithOtherIdcards = phoneWithOtherIdcards;
    }
    public Integer getRegisterOrgCnt() {
        return registerOrgCnt;
    }
    public void setRegisterOrgCnt(Integer registerOrgCnt) {
        this.registerOrgCnt = registerOrgCnt;
    }
    public List<String> getRegisterOrgType() {
        return registerOrgType;
    }
    public void setRegisterOrgType(List<String> registerOrgType) {
        this.registerOrgType = registerOrgType;
    }
    public List<String> getArisedOpenWeb() {
        return arisedOpenWeb;
    }
    public void setArisedOpenWeb(List<String> arisedOpenWeb) {
        this.arisedOpenWeb = arisedOpenWeb;
    }

    
}
