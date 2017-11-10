/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.shzx;

import java.util.List;

/**
 * 〈个人身份信息〉
 *
 * @author hwei
 * @version ShzxNfcsUserInfoBean.java, V1.0 2017年3月13日 下午4:29:47
 */
public class ShzxNfcsUserInfoBean {
    private String  name    ;// 姓名
    private String  idType  ;// 证件类型
    private String  idNo    ;// 证件号码
    private String  gender  ;// 性别（未知的性别、男性、女性、未说明性别）
    private String  birthday    ;// 出生日期，格式为YYYY.MM.DD
    private ShzxNfcsMaritalInfoBean maritalInfoBean ;// 婚姻状况
    private EduDegreeInfoBean   eduDegreeInfoBean   ;// 最高学历
    private TitleInfoBean   titleInfoBean   ;// 职称
    private HomePhonInfoBean    homePhonInfoBean    ;// 住宅电话
    private MobileInfoBean  mobileInfoBean  ;// 手机号码
    private EmailInfoBean   emailInfoBean   ;// 电子邮箱
    private List<AddressInfoBean>   addressInfoList ;// 地址 （最多披露5条）
    private List<PersonWorkDetailBean>  personWorkDetailList    ;// 工作单位 （最多披露5条）
    private String  mateName    ;// 配偶姓名
    private String  mateIdType  ;// 配偶证件类型
    private String  mateIdNo    ;// 配偶证件号码
    private String  mateGender  ;// 配偶性别
    private String  mateBirthday    ;// 配偶出生日期
    private MateWorkDetailBean  mateWorkDetailBean  ;// 配偶工作单位
    private MateContactInfoBean mateContactInfoBean ;// 配偶联系电话
    private List<Contact1InfoBean>  contact1InfoBeanList    ;// 第一联系人信息
    private List<Contact2InfoBean>  contact2InfoBeanList    ;// 第二联系人信息
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public ShzxNfcsMaritalInfoBean getMaritalInfoBean() {
        return maritalInfoBean;
    }
    public void setMaritalInfoBean(ShzxNfcsMaritalInfoBean maritalInfoBean) {
        this.maritalInfoBean = maritalInfoBean;
    }
    public EduDegreeInfoBean getEduDegreeInfoBean() {
        return eduDegreeInfoBean;
    }
    public void setEduDegreeInfoBean(EduDegreeInfoBean eduDegreeInfoBean) {
        this.eduDegreeInfoBean = eduDegreeInfoBean;
    }
    public TitleInfoBean getTitleInfoBean() {
        return titleInfoBean;
    }
    public void setTitleInfoBean(TitleInfoBean titleInfoBean) {
        this.titleInfoBean = titleInfoBean;
    }
    public HomePhonInfoBean getHomePhonInfoBean() {
        return homePhonInfoBean;
    }
    public void setHomePhonInfoBean(HomePhonInfoBean homePhonInfoBean) {
        this.homePhonInfoBean = homePhonInfoBean;
    }
    public MobileInfoBean getMobileInfoBean() {
        return mobileInfoBean;
    }
    public void setMobileInfoBean(MobileInfoBean mobileInfoBean) {
        this.mobileInfoBean = mobileInfoBean;
    }
    public EmailInfoBean getEmailInfoBean() {
        return emailInfoBean;
    }
    public void setEmailInfoBean(EmailInfoBean emailInfoBean) {
        this.emailInfoBean = emailInfoBean;
    }
    public List<AddressInfoBean> getAddressInfoList() {
        return addressInfoList;
    }
    public void setAddressInfoList(List<AddressInfoBean> addressInfoList) {
        this.addressInfoList = addressInfoList;
    }
    public List<PersonWorkDetailBean> getPersonWorkDetailList() {
        return personWorkDetailList;
    }
    public void setPersonWorkDetailList(List<PersonWorkDetailBean> personWorkDetailList) {
        this.personWorkDetailList = personWorkDetailList;
    }
    public String getMateName() {
        return mateName;
    }
    public void setMateName(String mateName) {
        this.mateName = mateName;
    }
    public String getMateIdType() {
        return mateIdType;
    }
    public void setMateIdType(String mateIdType) {
        this.mateIdType = mateIdType;
    }
    public String getMateIdNo() {
        return mateIdNo;
    }
    public void setMateIdNo(String mateIdNo) {
        this.mateIdNo = mateIdNo;
    }
    public String getMateGender() {
        return mateGender;
    }
    public void setMateGender(String mateGender) {
        this.mateGender = mateGender;
    }
    public String getMateBirthday() {
        return mateBirthday;
    }
    public void setMateBirthday(String mateBirthday) {
        this.mateBirthday = mateBirthday;
    }
    public MateWorkDetailBean getMateWorkDetailBean() {
        return mateWorkDetailBean;
    }
    public void setMateWorkDetailBean(MateWorkDetailBean mateWorkDetailBean) {
        this.mateWorkDetailBean = mateWorkDetailBean;
    }
    public MateContactInfoBean getMateContactInfoBean() {
        return mateContactInfoBean;
    }
    public void setMateContactInfoBean(MateContactInfoBean mateContactInfoBean) {
        this.mateContactInfoBean = mateContactInfoBean;
    }
    public List<Contact1InfoBean> getContact1InfoBeanList() {
        return contact1InfoBeanList;
    }
    public void setContact1InfoBeanList(List<Contact1InfoBean> contact1InfoBeanList) {
        this.contact1InfoBeanList = contact1InfoBeanList;
    }
    public List<Contact2InfoBean> getContact2InfoBeanList() {
        return contact2InfoBeanList;
    }
    public void setContact2InfoBeanList(List<Contact2InfoBean> contact2InfoBeanList) {
        this.contact2InfoBeanList = contact2InfoBeanList;
    }

}
