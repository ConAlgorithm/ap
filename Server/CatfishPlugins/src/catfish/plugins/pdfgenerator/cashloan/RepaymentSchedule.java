/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package catfish.plugins.pdfgenerator.cashloan;

import java.math.BigDecimal;
import java.util.Date;


public class RepaymentSchedule {

    private Integer id;
    private String appId;
    private String fundId;
    private Byte status;
    private Byte installmentNum;
    private Date dateDue;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal clientFee;
    private BigDecimal techFee;
    private Date createTime;
    private Date updateTime;
    private String updateBy;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }
    public String getFundId() {
        return fundId;
    }
    public void setFundId(String fundId) {
        this.fundId = fundId == null ? null : fundId.trim();
    }
    public Byte getStatus() {
        return status;
    }
    public void setStatus(Byte status) {
        this.status = status;
    }
    public Byte getInstallmentNum() {
        return installmentNum;
    }
    public void setInstallmentNum(Byte installmentNum) {
        this.installmentNum = installmentNum;
    }
    public Date getDateDue() {
        return dateDue;
    }
    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }
    public BigDecimal getPrincipal() {
        return principal;
    }
    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }
    public BigDecimal getInterest() {
        return interest;
    }
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
    public BigDecimal getClientFee() {
        return clientFee;
    }
    public void setClientFee(BigDecimal clientFee) {
        this.clientFee = clientFee;
    }
    public BigDecimal getTechFee() {
        return techFee;
    }
    public void setTechFee(BigDecimal techFee) {
        this.techFee = techFee;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    @Override
    public String toString() {
        return "RepaymentSchedule{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", fundId='" + fundId + '\'' +
                ", status=" + status +
                ", installmentNum=" + installmentNum +
                ", dateDue=" + dateDue +
                ", principal=" + principal +
                ", interest=" + interest +
                ", clientFee=" + clientFee +
                ", techFee=" + techFee +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}