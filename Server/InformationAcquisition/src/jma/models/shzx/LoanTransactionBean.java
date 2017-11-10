package jma.models.shzx;

import java.util.List;

public class LoanTransactionBean {
    private LoanOutlineRecordBean   loanOutlineRecordBean   ;// 信息概要
    private List<LoanRecordBean>    loanRecordList  ;// 贷款
    public LoanOutlineRecordBean getLoanOutlineRecordBean() {
        return loanOutlineRecordBean;
    }
    public void setLoanOutlineRecordBean(LoanOutlineRecordBean loanOutlineRecordBean) {
        this.loanOutlineRecordBean = loanOutlineRecordBean;
    }
    public List<LoanRecordBean> getLoanRecordList() {
        return loanRecordList;
    }
    public void setLoanRecordList(List<LoanRecordBean> loanRecordList) {
        this.loanRecordList = loanRecordList;
    }
   

}
