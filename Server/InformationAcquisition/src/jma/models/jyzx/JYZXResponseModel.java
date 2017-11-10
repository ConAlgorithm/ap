/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jyzx;

import java.util.List;

/**
 * 〈91征信〉
 *
 * @author dengw
 * @version JYZXResponseModel.java, V1.0 2017年8月21日 下午5:54:26
 */
public class JYZXResponseModel {
	//交易代码
	private String X_JYZX_trxNo;
	//借款个数
	private int X_JYZX_loanInfo_size;
	//多个借款结果情况
	private List<LoanInfo> X_JYZX_loanInfos;
	
	public String getX_JYZX_trxNo() {
		return X_JYZX_trxNo;
	}
	public void setX_JYZX_trxNo(String x_JYZX_trxNo) {
		X_JYZX_trxNo = x_JYZX_trxNo;
	}
	public int getX_JYZX_loanInfo_size() {
		return X_JYZX_loanInfo_size;
	}
	public void setX_JYZX_loanInfo_size(int x_JYZX_loanInfo_size) {
		X_JYZX_loanInfo_size = x_JYZX_loanInfo_size;
	}
	public List<LoanInfo> getX_JYZX_loanInfos() {
		return X_JYZX_loanInfos;
	}
	public void setX_JYZX_loanInfos(List<LoanInfo> x_JYZX_loanInfos) {
		X_JYZX_loanInfos = x_JYZX_loanInfos;
	}
}
