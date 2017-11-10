package jma.models.shzx;

import java.util.List;

public class LoanReportQueryBean {
    private List<ReportQueryRecordBean> reportQueryRecordList   ;// 查询记录

    public List<ReportQueryRecordBean> getReportQueryRecordList() {
        return reportQueryRecordList;
    }

    public void setReportQueryRecordList(List<ReportQueryRecordBean> reportQueryRecordList) {
        this.reportQueryRecordList = reportQueryRecordList;
    }

}
