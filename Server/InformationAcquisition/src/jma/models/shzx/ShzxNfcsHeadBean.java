/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.shzx;

/**
 * 〈信用报告头〉
 *
 * @author hwei
 * @version ShzxNfcsHeadBean.java, V1.0 2017年3月13日 下午4:25:48
 */
public class ShzxNfcsHeadBean {
    private String  queryReason ;// 查询原因
    private String  reportId    ;// 报告编号
    private String  reportDateTime  ;// 报告时间，格式如2014.11.14 13:35:03接入方根据需要自行转换
    public String getQueryReason() {
        return queryReason;
    }
    public void setQueryReason(String queryReason) {
        this.queryReason = queryReason;
    }
    public String getReportId() {
        return reportId;
    }
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    public String getReportDateTime() {
        return reportDateTime;
    }
    public void setReportDateTime(String reportDateTime) {
        this.reportDateTime = reportDateTime;
    }
    
    

}
