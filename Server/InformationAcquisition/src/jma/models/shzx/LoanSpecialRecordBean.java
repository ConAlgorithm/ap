package jma.models.shzx;

public class LoanSpecialRecordBean {
    private String  recordSource    ;// 记录来源
    private String  recordType  ;// 记录类型（展期（延期）、担保人代还、 以资抵债、 提前还款（部分）、 提前还款（全部）、 长期拖欠（拖欠超过 180 天）、 法律诉讼（已生效判决）、 贷款欺诈（由信息提供方判定）、 其他）
    private String  recordDate  ;// 发生日期，格式为YYYY.MM.DD
    private String  changeMounth    ;// 变更月数
    private String  changeMoney ;// 发生金额
    private String  changeDetail    ;// 明细信息
    private String  recordInfoDateTime  ;// 信息获取日期，格式为YYYY.MM.DD
    public String getRecordSource() {
        return recordSource;
    }
    public void setRecordSource(String recordSource) {
        this.recordSource = recordSource;
    }
    public String getRecordType() {
        return recordType;
    }
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    public String getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
    public String getChangeMounth() {
        return changeMounth;
    }
    public void setChangeMounth(String changeMounth) {
        this.changeMounth = changeMounth;
    }
    public String getChangeMoney() {
        return changeMoney;
    }
    public void setChangeMoney(String changeMoney) {
        this.changeMoney = changeMoney;
    }
    public String getChangeDetail() {
        return changeDetail;
    }
    public void setChangeDetail(String changeDetail) {
        this.changeDetail = changeDetail;
    }
    public String getRecordInfoDateTime() {
        return recordInfoDateTime;
    }
    public void setRecordInfoDateTime(String recordInfoDateTime) {
        this.recordInfoDateTime = recordInfoDateTime;
    }

}
