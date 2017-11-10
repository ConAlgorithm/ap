/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jyzx;

import java.util.Date;

/**
 * 〈91征信〉
 *
 * @author dengw
 * @version LoanInfo.java, V1.0 2017年8月21日 下午5:56:53
 */
public class LoanInfo {
	//借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	private Short borrowType;
	//借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	private Short borrowState ;
	//借款金额
    private Short borrowAmount;
    //合同日期
    private Date contractDate;
    //批贷期数
    private Short loanPeriod;
    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
    private Short repayState;
    //欠款金额
    private Long arrearsAmount;
    //公司代码
	private String companyCode;
	
	public Short getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(Short borrowType) {
		this.borrowType = borrowType;
	}
	public Short getBorrowState() {
		return borrowState;
	}
	public void setBorrowState(Short borrowState) {
		this.borrowState = borrowState;
	}
	public Short getBorrowAmount() {
		return borrowAmount;
	}
	public void setBorrowAmount(Short borrowAmount) {
		this.borrowAmount = borrowAmount;
	}
	public Date getContractDate() {
		return contractDate;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public Short getLoanPeriod() {
		return loanPeriod;
	}
	public void setLoanPeriod(Short loanPeriod) {
		this.loanPeriod = loanPeriod;
	}
	public Short getRepayState() {
		return repayState;
	}
	public void setRepayState(Short repayState) {
		this.repayState = repayState;
	}
	public Long getArrearsAmount() {
		return arrearsAmount;
	}
	public void setArrearsAmount(Long arrearsAmount) {
		this.arrearsAmount = arrearsAmount;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
}
