/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

import java.math.BigDecimal;


/**
 * 〈账务接口相关数据1〉
 *
 * @author hwei
 * @version SettlementModel.java, V1.0 2017年5月18日 上午11:14:58
 */
public class SettlementModel {
    //申请appId
    private String appId;
    //用户userId
    private String userId;
    //有首逾但没有超过N天用户（现在是7天）  0:未超过,1:超过
    private Integer notOverDueNdaysUserCount;
    /*
     * 借款申请状态 
     * 10：还款中；20：逾期；  30：正常结清； 40：提前结清；50：犹豫期提前结清；60：当前日期为还款日  
     */
    private int loanStatus;
    //借款申请状态跟新时间 
    private Long loanStatusUpdatetime;
    //本次申请逾期天数大于等于5天的期数
    private int overDueCount;
    //本次申请最大还款（月还款额） 
    private BigDecimal maxRepayment;
    //本次借款提前还款天数
    private int loanPrepaymentdays;
    //已完成借款的最大逾期天数
    private int appMaxDelayedDays;
    //逾期金额总和
    private BigDecimal overdueAmount;
    //逾期天数(最小未还期数约定还款日到当前时间的天数)
    private int overdueDays;
    
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getNotOverDueNdaysUserCount() {
        return notOverDueNdaysUserCount;
    }
    public void setNotOverDueNdaysUserCount(Integer notOverDueNdaysUserCount) {
        this.notOverDueNdaysUserCount = notOverDueNdaysUserCount;
    }
    public int getLoanStatus() {
        return loanStatus;
    }
    public void setLoanStatus(int loanStatus) {
        this.loanStatus = loanStatus;
    }
    public Long getLoanStatusUpdatetime() {
        return loanStatusUpdatetime;
    }
    public void setLoanStatusUpdatetime(Long loanStatusUpdatetime) {
        this.loanStatusUpdatetime = loanStatusUpdatetime;
    }
    public int getOverDueCount() {
        return overDueCount;
    }
    public void setOverDueCount(int overDueCount) {
        this.overDueCount = overDueCount;
    }
    public BigDecimal getMaxRepayment() {
        return maxRepayment;
    }
    public void setMaxRepayment(BigDecimal maxRepayment) {
        this.maxRepayment = maxRepayment;
    }
    public int getLoanPrepaymentdays() {
        return loanPrepaymentdays;
    }
    public void setLoanPrepaymentdays(int loanPrepaymentdays) {
        this.loanPrepaymentdays = loanPrepaymentdays;
    }
    public int getAppMaxDelayedDays() {
        return appMaxDelayedDays;
    }
    public void setAppMaxDelayedDays(int appMaxDelayedDays) {
        this.appMaxDelayedDays = appMaxDelayedDays;
    }
    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }
    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }
    public int getOverdueDays() {
        return overdueDays;
    }
    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }
    
}
