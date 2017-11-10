/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.shzx;

/**
 * 〈上海资信响应model〉
 *
 * @author hwei
 * @version ShzxNfcsResponseModel.java, V1.0 2017年3月13日 下午4:19:29
 */
public class ShzxNfcsResponseModel {
    private ShzxNfcsHeadBean    shzxNfcsHeadBean    ;// 信用报告头
    private ShzxNfcsUserInfoBean    userInfoBean    ;// 个人身份信息
    private LoanApplyRecordBean loanApplyRecordBean ;// 贷款申请信息
    private LoanTransactionBean loanTransactionBean ;// 贷款交易信息
    private LoanGuarantRecordBean   loanGuarantRecordBean   ;// 为他人担保信息
    private SpecialTransactionBean  specialTransactionBean  ;// 特殊交易信息
    private ShzxNfcsUserDeclareBean shzxNfcsUserDeclareBean ;// 个人声明信息
    private ShzxNfcsTipInfoBean shzxNfcsTipInfoBean ;// 资信提示信息
    private LoanReportQueryBean loanReportQueryBean ;// 查询信息
    public ShzxNfcsHeadBean getShzxNfcsHeadBean() {
        return shzxNfcsHeadBean;
    }
    public void setShzxNfcsHeadBean(ShzxNfcsHeadBean shzxNfcsHeadBean) {
        this.shzxNfcsHeadBean = shzxNfcsHeadBean;
    }
    public ShzxNfcsUserInfoBean getUserInfoBean() {
        return userInfoBean;
    }
    public void setUserInfoBean(ShzxNfcsUserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }
    public LoanApplyRecordBean getLoanApplyRecordBean() {
        return loanApplyRecordBean;
    }
    public void setLoanApplyRecordBean(LoanApplyRecordBean loanApplyRecordBean) {
        this.loanApplyRecordBean = loanApplyRecordBean;
    }
    public LoanTransactionBean getLoanTransactionBean() {
        return loanTransactionBean;
    }
    public void setLoanTransactionBean(LoanTransactionBean loanTransactionBean) {
        this.loanTransactionBean = loanTransactionBean;
    }
    public LoanGuarantRecordBean getLoanGuarantRecordBean() {
        return loanGuarantRecordBean;
    }
    public void setLoanGuarantRecordBean(LoanGuarantRecordBean loanGuarantRecordBean) {
        this.loanGuarantRecordBean = loanGuarantRecordBean;
    }
    public SpecialTransactionBean getSpecialTransactionBean() {
        return specialTransactionBean;
    }
    public void setSpecialTransactionBean(SpecialTransactionBean specialTransactionBean) {
        this.specialTransactionBean = specialTransactionBean;
    }
    public ShzxNfcsUserDeclareBean getShzxNfcsUserDeclareBean() {
        return shzxNfcsUserDeclareBean;
    }
    public void setShzxNfcsUserDeclareBean(ShzxNfcsUserDeclareBean shzxNfcsUserDeclareBean) {
        this.shzxNfcsUserDeclareBean = shzxNfcsUserDeclareBean;
    }
    public ShzxNfcsTipInfoBean getShzxNfcsTipInfoBean() {
        return shzxNfcsTipInfoBean;
    }
    public void setShzxNfcsTipInfoBean(ShzxNfcsTipInfoBean shzxNfcsTipInfoBean) {
        this.shzxNfcsTipInfoBean = shzxNfcsTipInfoBean;
    }
    public LoanReportQueryBean getLoanReportQueryBean() {
        return loanReportQueryBean;
    }
    public void setLoanReportQueryBean(LoanReportQueryBean loanReportQueryBean) {
        this.loanReportQueryBean = loanReportQueryBean;
    }

    
}
