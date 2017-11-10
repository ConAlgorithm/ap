/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package instinct.model;

import util.InstinctizeUtil;

/**
 * 〈百融黑名单字段〉
 *
 * @author dengw
 * @version U2B.java, V1.0 2016年11月18日 下午6:20:08
 */
public class U2B {
    /** Text    1   Must be “L” */
    public final String category = "L";
    /** Text   50 百融身份证银行欺诈(X_BR_SpecialListIdBankFraud) */
    public String user_Field1;
    /** Text   50 百融身份证银行失联(X_BR_SpecialListIdBankLost) */
    public String user_Field2;
    /** Text   50 百融身份证银行拒绝(X_BR_SpecialListIdBankRefuse) */
    public String user_Field3;
    /** Text   50 百融身份证P2P欺诈(X_BR_SpecialListIdP2PFraud) */
    public String user_Field4;
    /** Text   50 百融身份证P2P失联(X_BR_SpecialListIdP2PLost) */
    public String user_Field5;
    /** Text   50 百融身份证P2P拒绝(X_BR_SpecialListIdP2PRefuse) */
    public String user_Field6;
    /** Text   50 百融身份证保险骗保(X_BR_SpecialListIdInsFraud) */
    public String user_Field7;
    /** Text   50 百融手机银行欺诈(X_BR_SpecialListCellBankFraud) */
    public String user_Field8;
    /** Text   50 百融手机银行失联(X_BR_SpecialListCellBankLost) */
    public String user_Field9;
    /** Text   50 百融手机银行拒绝(X_BR_SpecialListCellBankRefuse) */
    public String user_Field10;
    /** Text   50 百融手机P2P欺诈(X_BR_SpecialListCellP2PFraud) */
    public String user_Field11;
    /** Text   50 百融手机P2P失联(X_BR_SpecialListCellP2PLost) */
    public String user_Field12;
    /** Text   50 百融手机P2P拒绝(X_BR_SpecialListCellP2PRefuse) */
    public String user_Field13;
    /** Text   50 百融手机保险骗保(X_BR_SpecialListCellInsFraud) */
    public String user_Field14;
    /**Text 100 姓名（name） */
    public String user_Field15;
    /** Text 100 证件号码idNo*/
    public String user_Field16;
    /** Text    100 配偶姓名mateName*/
    public String user_Field17;
    /** Text    100 配偶证件号码mateIdNo*/
    public String user_Field18;
    /** Text    100 婚姻明细maritalStatus*/
    public String user_Field19;
    /** Text    100 婚姻信息获取日期（格式为YYYY.MM.DD）maritalInfoDateTime*/
    public String user_Field20;
    /** Text    100 学历明细educationDegree*/
    public String user_Field21;
    /** Text    100 职称明细titleDetails*/
    public String user_Field22;
    /** Text    100 住宅电话明细homePhone*/
    public String user_Field23;
    /** Text    100 手机号码明细mobile*/
    public String user_Field24;
    /** Text    100 电子邮箱明细email*/
    public String user_Field25;
    /** Text    100 配偶工作单位明细mateWorkDetail*/
    public String user_Field26;
    /** Text    100 配偶联系电话明细mateContactPhone*/
    public String user_Field27;
    /** Text    100 联系人姓名是否在联系人姓名里ifincontact1Name*/
    public String user_Field28;
    /** Text    100 联系人电话是否联系人电话里ifincontact1phone*/
    public String user_Field29;
    /** Text    100 GPS经度 X_DEVICEINFO_LOGITUDE*/
    public String user_Field30;
    /** Text    100 GPSGPS纬度 X_DEVICEINFO_LATITUDE*/
    public String user_Field31;
    /** Text    100 申请手机设备码 X_DEVICEINFO_ID*/
    public String user_Field32;
    /** Text    100 申请手机机型  X_DEVICEINFO_NAME*/
    public String user_Field33;
    
    /**Date 8   手机号码信息获取日期mobileInfoDateTime */
    public String user_Field46;
    /** Date    8   配偶联系方式信息获取日期mateContactInfoDateTime*/
    public String user_Field47;
    /** Text    max X_SHZX_PersonWorkDetailList上海资信 -工作单位列表(xx公司，职业，日期；)*/
    public String user_Field56;
    /** Text    max X_SHZX_AddressInfoList上海资信 -地址列表(xx室，日期；)*/
    public String user_Field57;
    
    
    

    public U2B(omni.model.U2B u2b) {
        this.user_Field1 = InstinctizeUtil.string(u2b.getX_BR_SpecialListIdBankFraud());
        this.user_Field2 = InstinctizeUtil.string(u2b.getX_BR_SpecialListIdBankLost());
        this.user_Field3 = InstinctizeUtil.string(u2b.getX_BR_SpecialListIdBankRefuse());
        this.user_Field4 = InstinctizeUtil.string(u2b.getX_BR_SpecialListIdP2PFraud());
        this.user_Field5 = InstinctizeUtil.string(u2b.getX_BR_SpecialListIdP2PLost());
        this.user_Field6 = InstinctizeUtil.string(u2b.getX_BR_SpecialListIdP2PRefuse());
        this.user_Field7 = InstinctizeUtil.string(u2b.getX_BR_SpecialListIdInsFraud());
        this.user_Field8 = InstinctizeUtil.string(u2b.getX_BR_SpecialListCellBankFraud());
        this.user_Field9 = InstinctizeUtil.string(u2b.getX_BR_SpecialListCellBankLost());
        this.user_Field10 = InstinctizeUtil.string(u2b.getX_BR_SpecialListCellBankRefuse());
        this.user_Field11 = InstinctizeUtil.string(u2b.getX_BR_SpecialListCellP2PFraud());
        this.user_Field12 = InstinctizeUtil.string(u2b.getX_BR_SpecialListCellP2PLost());
        this.user_Field13 = InstinctizeUtil.string(u2b.getX_BR_SpecialListCellP2PRefuse());
        this.user_Field14 = InstinctizeUtil.string(u2b.getX_BR_SpecialListCellInsFraud());
        this.user_Field15 = InstinctizeUtil.string(u2b.getX_SHZX_Name());
        this.user_Field16 = InstinctizeUtil.string(u2b.getX_SHZX_IdNo());
        this.user_Field17 = InstinctizeUtil.string(u2b.getX_SHZX_MateName());
        this.user_Field18 = InstinctizeUtil.string(u2b.getX_SHZX_MateIdNo());
        this.user_Field19 = InstinctizeUtil.string(u2b.getX_SHZX_MaritalStatus());
        this.user_Field20 = InstinctizeUtil.date(u2b.getX_SHZX_MaritalInfoDateTime());
        this.user_Field21 = InstinctizeUtil.string(u2b.getX_SHZX_EducationDegree());
        this.user_Field22 = InstinctizeUtil.string(u2b.getX_SHZX_TitleDetails());
        this.user_Field23 = InstinctizeUtil.string(u2b.getX_SHZX_HomePhone());
        this.user_Field24 = InstinctizeUtil.string(u2b.getX_SHZX_Mobile());
        this.user_Field25 = InstinctizeUtil.string(u2b.getX_SHZX_Email());
        this.user_Field26 = InstinctizeUtil.string(u2b.getX_SHZX_MateWorkDetail());
        this.user_Field27 = InstinctizeUtil.string(u2b.getX_SHZX_MateContactPhone());
        this.user_Field28 = InstinctizeUtil.string(u2b.getX_SHZX_IsInContact1Name());
        this.user_Field29 = InstinctizeUtil.string(u2b.getX_SHZX_IsInContact1Phone());
        this.user_Field30 = InstinctizeUtil.string(u2b.getX_DEVICEINFO_LOGITUDE());
        this.user_Field31 = InstinctizeUtil.string(u2b.getX_DEVICEINFO_LATITUDE());
        this.user_Field32 = InstinctizeUtil.string(u2b.getX_DEVICEINFO_ID());
        this.user_Field33 = InstinctizeUtil.string(u2b.getX_DEVICEINFO_NAME());
        this.user_Field46 = InstinctizeUtil.date(u2b.getX_SHZX_MobileInfoDateTime());
        this.user_Field47 = InstinctizeUtil.date(u2b.getX_SHZX_MateContactInfoDateTime());
        this.user_Field56 = InstinctizeUtil.string(u2b.getX_SHZX_PersonWorkDetailList());
        this.user_Field57 = InstinctizeUtil.string(u2b.getX_SHZX_AddressInfoList());
        
        
    }

    public U2B() {

    }
}
