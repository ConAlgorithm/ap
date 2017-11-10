/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

/**
 * 〈前海征信model〉
 *
 * @author hwei
 * @version QHZXModel.java, V1.0 2016年11月10日 上午10:15:41
 */
public class QHZXModel {
    //最近的黑名单创建时间
    private String x_QHZX_LatestDataBuildTime;
    //历史上是否有征信记录
    private String x_QHZX_HasBlackList;
    //历史是否是被执行人
    private String x_QHZX_HasBeenExcuted;
    //历史是否有违约
    private String x_QHZX_BreakPromise;
    //历史是否是失信被执行人
    private String x_QHZX_BreakPromiseBeenExecuted;
    //历史最严重逾期时间
    private String x_QHZX_MaximizeOverdueDay;
    //历史最高金额涉足范围(0-5),
    private String x_QHZX_MoneyBound;
    //是否信贷逾期风险
    private Boolean x_QHZX_HasCreditOverdueRisk;
    //是否行政负面风险
    private Boolean x_QHZX_HasAdministrationNegativeRisk;
    //是否欺诈风险
    private Boolean x_QHZX_HasFraudRisk;
    //风险得分(10-45)
    private Integer x_QHZX_RiskScore;
    //是否交通严重违章
    private Boolean x_QHZX_HasSeriousTrafficViolationRisk;
    //是否手机号存在欺诈风险
    private Boolean x_QHZX_HasMobileFraudRisk;
    //是否卡号存在欺诈风险
    private Boolean x_QHZX_HasBankCardFraudRisk;
    //是否身份证号存在欺诈风险
    private Boolean x_QHZX_HasIdCardFraudRisk;
    //是否IP存在欺诈风险
    private Boolean x_QHZX_HasIPAddressFraudRisk;
    //数据状态（99-权限不足，1-已验证，2-待验证，3-异议中）
    private String x_QHZX_DataStatus;

    public String getX_QHZX_LatestDataBuildTime() {
        return x_QHZX_LatestDataBuildTime;
    }

    public void setX_QHZX_LatestDataBuildTime(String x_QHZX_LatestDataBuildTime) {
        this.x_QHZX_LatestDataBuildTime = x_QHZX_LatestDataBuildTime;
    }

    public String getX_QHZX_HasBlackList() {
        return x_QHZX_HasBlackList;
    }

    public void setX_QHZX_HasBlackList(String x_QHZX_HasBlackList) {
        this.x_QHZX_HasBlackList = x_QHZX_HasBlackList;
    }

    public String getX_QHZX_HasBeenExcuted() {
        return x_QHZX_HasBeenExcuted;
    }

    public void setX_QHZX_HasBeenExcuted(String x_QHZX_HasBeenExcuted) {
        this.x_QHZX_HasBeenExcuted = x_QHZX_HasBeenExcuted;
    }

    public String getX_QHZX_BreakPromise() {
        return x_QHZX_BreakPromise;
    }

    public void setX_QHZX_BreakPromise(String x_QHZX_BreakPromise) {
        this.x_QHZX_BreakPromise = x_QHZX_BreakPromise;
    }

    public String getX_QHZX_BreakPromiseBeenExecuted() {
        return x_QHZX_BreakPromiseBeenExecuted;
    }

    public void setX_QHZX_BreakPromiseBeenExecuted(String x_QHZX_BreakPromiseBeenExecuted) {
        this.x_QHZX_BreakPromiseBeenExecuted = x_QHZX_BreakPromiseBeenExecuted;
    }

    public String getX_QHZX_MaximizeOverdueDay() {
        return x_QHZX_MaximizeOverdueDay;
    }

    public void setX_QHZX_MaximizeOverdueDay(String x_QHZX_MaximizeOverdueDay) {
        this.x_QHZX_MaximizeOverdueDay = x_QHZX_MaximizeOverdueDay;
    }

    public String getX_QHZX_MoneyBound() {
        return x_QHZX_MoneyBound;
    }

    public void setX_QHZX_MoneyBound(String x_QHZX_MoneyBound) {
        this.x_QHZX_MoneyBound = x_QHZX_MoneyBound;
    }

    public Boolean getX_QHZX_HasCreditOverdueRisk() {
        return x_QHZX_HasCreditOverdueRisk;
    }

    public void setX_QHZX_HasCreditOverdueRisk(Boolean x_QHZX_HasCreditOverdueRisk) {
        this.x_QHZX_HasCreditOverdueRisk = x_QHZX_HasCreditOverdueRisk;
    }

    public Boolean getX_QHZX_HasAdministrationNegativeRisk() {
        return x_QHZX_HasAdministrationNegativeRisk;
    }

    public void setX_QHZX_HasAdministrationNegativeRisk(Boolean x_QHZX_HasAdministrationNegativeRisk) {
        this.x_QHZX_HasAdministrationNegativeRisk = x_QHZX_HasAdministrationNegativeRisk;
    }

    public Boolean getX_QHZX_HasFraudRisk() {
        return x_QHZX_HasFraudRisk;
    }

    public void setX_QHZX_HasFraudRisk(Boolean x_QHZX_HasFraudRisk) {
        this.x_QHZX_HasFraudRisk = x_QHZX_HasFraudRisk;
    }

    public Integer getX_QHZX_RiskScore() {
        return x_QHZX_RiskScore;
    }

    public void setX_QHZX_RiskScore(Integer x_QHZX_RiskScore) {
        this.x_QHZX_RiskScore = x_QHZX_RiskScore;
    }

    public Boolean getX_QHZX_HasSeriousTrafficViolationRisk() {
        return x_QHZX_HasSeriousTrafficViolationRisk;
    }

    public void setX_QHZX_HasSeriousTrafficViolationRisk(Boolean x_QHZX_HasSeriousTrafficViolationRisk) {
        this.x_QHZX_HasSeriousTrafficViolationRisk = x_QHZX_HasSeriousTrafficViolationRisk;
    }

    public Boolean getX_QHZX_HasMobileFraudRisk() {
        return x_QHZX_HasMobileFraudRisk;
    }

    public void setX_QHZX_HasMobileFraudRisk(Boolean x_QHZX_HasMobileFraudRisk) {
        this.x_QHZX_HasMobileFraudRisk = x_QHZX_HasMobileFraudRisk;
    }

    public Boolean getX_QHZX_HasBankCardFraudRisk() {
        return x_QHZX_HasBankCardFraudRisk;
    }

    public void setX_QHZX_HasBankCardFraudRisk(Boolean x_QHZX_HasBankCardFraudRisk) {
        this.x_QHZX_HasBankCardFraudRisk = x_QHZX_HasBankCardFraudRisk;
    }

    public Boolean getX_QHZX_HasIdCardFraudRisk() {
        return x_QHZX_HasIdCardFraudRisk;
    }

    public void setX_QHZX_HasIdCardFraudRisk(Boolean x_QHZX_HasIdCardFraudRisk) {
        this.x_QHZX_HasIdCardFraudRisk = x_QHZX_HasIdCardFraudRisk;
    }

    public Boolean getX_QHZX_HasIPAddressFraudRisk() {
        return x_QHZX_HasIPAddressFraudRisk;
    }

    public void setX_QHZX_HasIPAddressFraudRisk(Boolean x_QHZX_HasIPAddressFraudRisk) {
        this.x_QHZX_HasIPAddressFraudRisk = x_QHZX_HasIPAddressFraudRisk;
    }

    public String getX_QHZX_DataStatus() {
        return x_QHZX_DataStatus;
    }

    public void setX_QHZX_DataStatus(String x_QHZX_DataStatus) {
        this.x_QHZX_DataStatus = x_QHZX_DataStatus;
    }

}
