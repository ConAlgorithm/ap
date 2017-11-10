package jma.models;

public class StatisticsList {
    private String transNum;           //查询次数
    private String prdGrpName;         //查询产品类别
    private String indusStatisticsList;//按行业统计详情列表
    private String industry;           //查询行业
    private String cusNum;             //查询客户数量

    public String getTransNum() {
        return transNum;
    }
    public void setTransNum(String transNum) {
        this.transNum = transNum;
    }
    public String getPrdGrpName() {
        return prdGrpName;
    }
    public void setPrdGrpName(String prdGrpName) {
        this.prdGrpName = prdGrpName;
    }
    public String getIndusStatisticsList() {
        return indusStatisticsList;
    }
    public void setIndusStatisticsList(String indusStatisticsList) {
        this.indusStatisticsList = indusStatisticsList;
    }
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getCusNum() {
        return cusNum;
    }
    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StatisticsList [transNum=");
        builder.append(transNum);
        builder.append(", prdGrpName=");
        builder.append(prdGrpName);
        builder.append(", indusStatisticsList=");
        builder.append(indusStatisticsList);
        builder.append(", industry=");
        builder.append(industry);
        builder.append(", cusNum=");
        builder.append(cusNum);
        builder.append("]");
        return builder.toString();
    }
}
