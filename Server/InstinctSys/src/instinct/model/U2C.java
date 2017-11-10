/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package instinct.model;

import util.InstinctizeUtil;

/**
 * 〈前海黑名单字段〉
 *
 * @author dengw
 * @version U2C.java, V1.0 2016年11月21日 下午5:46:15
 */
public class U2C {
    /** Text    1   Must be “Z” */
    public final String category = "Z";
    /** Text    50 前海欺诈风险(X_QHZX_HasFraudRisk) */
    public String user_Field1;
    /** Text    50 前海风险得分(X_QHZX_RiskScore) */
    public String user_Field2;
    /** Text    50 前海手机号欺诈风险(X_QHZX_HasMobileFraudRisk) */
    public String user_Field3;
    /** Text    50 前海银行卡号欺诈风险(X_QHZX_HasBankCardFraudRisk) */
    public String user_Field4;
    /** Text    50 前海身份证号欺诈风险(X_QHZX_HasIdCardFraudRisk) */
    public String user_Field5;
    /** Text    50 申请机构applyOrganization*/
    public String user_Field26;
    /** Text    50 申请时间applyTime */
    public String user_Field27;
    /** Text    50 申请金额applyMoney */
    public String user_Field28;
    /** Text    50 申请月数applyMonth */
    public String user_Field29;
    /** Text    50 申请状态applyStatus */
    public String user_Field30;
    /** Text    50 申请类型applyType */
    public String user_Field31;
    /** Text    50 申请信息获取时间applyInfoDateTime*/
    public String user_Field32;
    /** Text    50 贷款笔数loanNumsTotal */
    public String user_Field33;
    /** Text    50 最大授信额度maxCreditLineTotal*/
    public String user_Field34;
    /** Text    50 贷款总额loanMoneyTotal*/
    public String user_Field35;
    /**Text    50 贷款余额remainMoneyTotal */
    public String user_Field36;
    /** Text    50 当前逾期总额repayOverdueMoneyTotal*/
    public String user_Field37;
    /** Text    50 最高逾期金额repayOverdueMaxMoney*/
    public String user_Field38;
    /** Text    50 贷款机构查询次数LoanReportQueryCount*/
    public String user_Field39;
    
    /** Text    50 在网时长LengthOfNetwork*/
    public String user_Field40;
    /** Text    50 在网时长描述LengthOfNetworkDesc*/
    public String user_Field41;
    /** Text    50 三元素验证IsRealAuthenticated3*/
    public String user_Field42;
    /** Text    50 三元素验证描述IsRealAuthenticated3Desc*/
    public String user_Field43;
    /** Text    50 百度评分是否匹配上百度BaiduScore*/
    public String user_Field44;
    /** Text    50 百度评分BaiduMdx*/
    public String user_Field45;
    /** Text    50 手机多平台借款平台数LoanAmount*/
    public String user_Field46;
    /** Text    50 身份证多平台借款平台数LoanAmount*/
    public String user_Field47;
    
    public U2C(omni.model.U2C u2c){
        this.user_Field1 = InstinctizeUtil.string(u2c.getX_QHZX_HasFraudRisk());
        this.user_Field2 = InstinctizeUtil.string(u2c.getX_QHZX_RiskScore());
        this.user_Field3 = InstinctizeUtil.string(u2c.getX_QHZX_HasMobileFraudRisk());
        this.user_Field4 = InstinctizeUtil.string(u2c.getX_QHZX_HasBankCardFraudRisk());
        this.user_Field5 = InstinctizeUtil.string(u2c.getX_QHZX_HasIdCardFraudRisk());
        this.user_Field26 = InstinctizeUtil.string(u2c.getX_SHZX_ApplyOrganization());
        this.user_Field27 = InstinctizeUtil.string(u2c.getX_SHZX_ApplyTime());
        this.user_Field28 = InstinctizeUtil.string(u2c.getX_SHZX_ApplyMoney());
        this.user_Field29 = InstinctizeUtil.string(u2c.getX_SHZX_ApplyMonth());
        this.user_Field30 = InstinctizeUtil.string(u2c.getX_SHZX_ApplyStatus());
        this.user_Field31 = InstinctizeUtil.string(u2c.getX_SHZX_ApplyType());
        this.user_Field32 = InstinctizeUtil.string(u2c.getX_SHZX_ApplyInfoDateTime());
        this.user_Field33 = InstinctizeUtil.string(u2c.getX_SHZX_LoanNumsTotal());
        this.user_Field34 = InstinctizeUtil.string(u2c.getX_SHZX_MaxCreditLineTotal());
        this.user_Field35 = InstinctizeUtil.string(u2c.getX_SHZX_LoanMoneyTotal());
        this.user_Field36 = InstinctizeUtil.string(u2c.getX_SHZX_RemainMoneyTotal());
        this.user_Field37 = InstinctizeUtil.string(u2c.getX_SHZX_RepayOverdueMoneyTotal());
        this.user_Field38 = InstinctizeUtil.string(u2c.getX_SHZX_RepayOverdueMaxMoney());
        this.user_Field39 = InstinctizeUtil.string(u2c.getX_SHZX_LoanReportQueryCount());
        this.user_Field40 = InstinctizeUtil.string(u2c.getX_JO_LengthOfNetwork());
        this.user_Field41 = InstinctizeUtil.string(u2c.getX_JO_LengthOfNetworkDesc());
        this.user_Field42 = InstinctizeUtil.string(u2c.getX_JO_IsRealAuthenticated3());
        this.user_Field43 = InstinctizeUtil.string(u2c.getX_JO_IsRealAuthenticated3Desc());
        this.user_Field44 = InstinctizeUtil.string(u2c.getX_BaiduScore_type());
        this.user_Field45 = InstinctizeUtil.string(u2c.getX_BaiduMdx_usmbl());
        this.user_Field46 = InstinctizeUtil.string(u2c.getX_TD_Rule_33674_LoanAmount());
        this.user_Field47 = InstinctizeUtil.string(u2c.getX_TD_Rule_33676_LoanAmount());
    }
    public U2C(){
        
    }
}
