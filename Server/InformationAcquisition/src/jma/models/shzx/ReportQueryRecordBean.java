package jma.models.shzx;

public class ReportQueryRecordBean {
    private String  queryType   ;// 查询者类型
    private String  queryReason ;// 查询原因（贷后管理，贷款审批、担保资格审查、异议查询、本人查询、司法调查、金融监管）
    private String  queryDateTime   ;// 查询日期，格式如2014.11.14 13:35:03 
    public String getQueryType() {
        return queryType;
    }
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
    public String getQueryReason() {
        return queryReason;
    }
    public void setQueryReason(String queryReason) {
        this.queryReason = queryReason;
    }
    public String getQueryDateTime() {
        return queryDateTime;
    }
    public void setQueryDateTime(String queryDateTime) {
        this.queryDateTime = queryDateTime;
    }

}
