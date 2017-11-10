package jma.models.shzx;

import java.util.List;

public class SpecialTransactionBean {
    private List<LoanSpecialRecordBean> loanSpecialRecordList   ;// 详细记录

    public List<LoanSpecialRecordBean> getLoanSpecialRecordList() {
        return loanSpecialRecordList;
    }

    public void setLoanSpecialRecordList(List<LoanSpecialRecordBean> loanSpecialRecordList) {
        this.loanSpecialRecordList = loanSpecialRecordList;
    }

}
