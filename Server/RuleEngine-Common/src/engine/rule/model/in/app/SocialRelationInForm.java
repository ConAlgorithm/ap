package engine.rule.model.in.app;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.CompanyContactType;
import catfish.base.business.util.AppDerivativeVariableNames;

import java.math.BigDecimal;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "社会关系材料")
public class SocialRelationInForm extends BaseForm {

    // finished
    // 在creator中手动填写
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.UserMobileCity)
    @ModelField(name = "手机归属地")
    private String localPhone = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.USER_MOBILE_PROVINCE)
    @ModelField(name = "手机归属地省")
    private String localPhoneProvince = "";

    // finished
    // 在creator中手动填写
    @ModelField(name = "单位联系电话", bindDomain = "engine.rule.domain.CompanyContactType")
    private Integer companyTelNumberType = CompanyContactType.Empty.getValue();

    //Group info
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserTotalCount)
    @ModelField(name = "团内用户总数（默认值-1）")
    private Integer groupInfoUserTotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserApprovedCount)
    @ModelField(name = "团内用户批准总数（默认值-1）")
    private Integer groupInfoUserApprovedCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserApprovedRate)
    @ModelField(name = "团内用户批准率（默认值-1）")
    private BigDecimal groupInfoUserApprovedRate = BigDecimal.valueOf(-1);

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserRejectedCount)
    @ModelField(name = "团内拒绝用户总数（默认值-1）")
    private Integer groupInfoUserRejectedCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserRejectedRate)
    @ModelField(name = "团内用户拒绝率（默认值-1）")
    private BigDecimal groupInfoUserRejectedRate = BigDecimal.valueOf(-1);

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserCancelledCount)
    @ModelField(name = "团内取消用户总数（默认值-1）")
    private Integer groupInfoUserCancelledCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserPreRepayLoanCount)
    @ModelField(name = "团内提前还款用户总数（默认值-1）")
    private Integer groupInfoUserPreRepayLoanCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFemaleCount)
    @ModelField(name = "团内女性用户总数（默认值-1）")
    private Integer groupInfoUserFemaleCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoBlackListCount)
    @ModelField(name = "团内命中黑名单的用户总数（默认值-1）")
    private Integer groupInfoBlackListCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoBlackListCountRate)
    @ModelField(name = "团内拒绝用户命中黑名单的比率（默认值-1）")
    private BigDecimal groupInfoBlackListCountRate = BigDecimal.valueOf(-1);

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoReportedRejectedCount)
    @ModelField(name = "团内拉警报用户总数（默认值-1）")
    private Integer groupInfoReportedRejectedCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoReportedRejectedCountRate)
    @ModelField(name = "团内拒绝用户被拉警报的比例（默认值-1）")
    private BigDecimal groupInfoReportedRejectedCountRate = BigDecimal.valueOf(-1);

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoFausd)
    @ModelField(name = "团内欺诈拒绝用户总数（默认值-1）")
    private Integer groupInfoFausd = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserLoanAmountTotal)
    @ModelField(name = "团内用户借款总额（默认值-1）")
    private Integer groupInfoUserLoanAmountTotal = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserAppRecent3daysCount)
    @ModelField(name = "团内用户最近3天发生申请的用户总数（默认值-1）")
    private Integer groupInfoUserAppRecent3daysCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserRejectRecent3daysCount)
    @ModelField(name = "团内用户最近3天发生的申请被拒绝的用户总数（默认值-1）")
    private Integer groupInfoUserRejectRecent3daysCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserAproveRecent3daysCount)
    @ModelField(name = "团内用户最近3天发生的申请批准的用户总数（默认值-1）")
    private Integer groupInfoUserAproveRecent3daysCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserAppRecent7daysCount)
    @ModelField(name = "团内用户最近7天发生申请的用户总数（默认值-1）")
    private Integer groupInfoUserAppRecent7daysCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserAproveRecent7daysCount)
    @ModelField(name = "团内用户最近7天发生的申请批准的用户总数（默认值-1）")
    private Integer groupInfoUserAproveRecent7daysCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserRejectRecent7daysCount)
    @ModelField(name = "团内用户最近7天发生的申请被拒绝的用户总数（默认值-1）")
    private Integer groupInfoUserRejectRecent7daysCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserAppRecent1monthCount)
    @ModelField(name = "团内用户最近1个月发生申请的用户总数（默认值-1）")
    private Integer groupInfoUserAppRecent1monthCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserAproveRecent1monthCount)
    @ModelField(name = "团内用户最近1个月发生的申请批准的用户总数（默认值-1）")
    private Integer groupInfoUserAproveRecent1monthCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserRejectRecent1monthCount)
    @ModelField(name = "团内用户最近1个月发生的申请被拒绝的用户总数（默认值-1）")
    private Integer groupInfoUserRejectRecent1monthCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserAppRecent3monthsCount)
    @ModelField(name = "团内用户最近3个月发生申请的用户总数（默认值-1）")
    private Integer groupInfoUserAppRecent3monthsCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserAproveRecent3monthCount)
    @ModelField(name = "团内用户最近3个月发生的申请批准的用户总数（默认值-1）")
    private Integer groupInfoUserAproveRecent3monthCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserRejectRecent3monthCount)
    @ModelField(name = "团内用户最近3个月发生的申请被拒绝的用户总数（默认值-1）")
    private Integer groupInfoUserRejectRecent3monthCount = -1;

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.GroupInfoUserMainProvince)
    @ModelField(name = "团内用户最新申请的最大集中省份（默认值为空）")
    private String groupInfoUserMainProvince = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.GroupInfoUserMainCity)
    @ModelField(name = "团内用户最新申请的最大集中城市（默认值为空）")
    private String groupInfoUserMainCity = "";

    //FPD1
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD1Count)
    @ModelField(name = "团内FPD1逾期人数（默认值-1）")
    private Integer groupInfoUserFPD1Count = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD1TotalCount)
    @ModelField(name = "团内FPD1已表现人数（默认值-1）")
    private Integer groupInfoUserFPD1TotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD1Rate)
    @ModelField(name = "批核件FPD1占比（默认值-1）")
    private BigDecimal groupInfoUserFPD1Rate = BigDecimal.valueOf(-1);

    //FPD7
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD7Count)
    @ModelField(name = "团内FPD7逾期人数（默认值-1）")
    private Integer groupInfoUserFPD7Count = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD7TotalCount)
    @ModelField(name = "团内FPD7已表现人数（默认值-1）")
    private Integer groupInfoUserFPD7TotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD7Rate)
    @ModelField(name = "批核件FPD7占比（默认值-1）")
    private BigDecimal groupInfoUserFPD7Rate = BigDecimal.valueOf(-1);

    //FPD30
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD30Count)
    @ModelField(name = "团内FPD30逾期人数（默认值-1）")
    private Integer groupInfoUserFPD30Count = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD30TotalCount)
    @ModelField(name = "团内FPD30已表现人数（默认值-1）")
    private Integer groupInfoUserFPD30TotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD30Rate)
    @ModelField(name = "批核件FPD30占比（默认值-1）")
    private BigDecimal groupInfoUserFPD30Rate = BigDecimal.valueOf(-1);

    //SPD30
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserSPD30Count)
    @ModelField(name = "团内SPD30逾期人数（默认值-1）")
    private Integer groupInfoUserSPD30Count = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserSPD30TotalCount)
    @ModelField(name = "团内SPD30已表现人数（默认值-1）")
    private Integer groupInfoUserSPD30TotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserSPD30Rate)
    @ModelField(name = "批核件SPD30占比（默认值-1）")
    private BigDecimal groupInfoUserSPD30Rate = BigDecimal.valueOf(-1);

    //TPD30
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserTPD30Count)
    @ModelField(name = "团内TPD30逾期人数（默认值-1）")
    private Integer groupInfoUserTPD30Count = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserTPD30TotalCount)
    @ModelField(name = "团内TPD30已表现人数（默认值-1）")
    private Integer groupInfoUserTPD30TotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserTPD30Rate)
    @ModelField(name = "批核件TPD30占比（默认值-1）")
    private BigDecimal groupInfoUserTPD30Rate = BigDecimal.valueOf(-1);

    //FS30
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFS30Count)
    @ModelField(name = "团内FS30逾期人数（默认值-1）")
    private Integer groupInfoUserFS30Count = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFS30TotalCount)
    @ModelField(name = "团内FS30已表现人数（默认值-1）")
    private Integer groupInfoUserFS30TotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFS30Rate)
    @ModelField(name = "批核件FS30占比（默认值-1）")
    private BigDecimal groupInfoUserFS30Rate = BigDecimal.valueOf(-1);

    //FST30
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFST30Count)
    @ModelField(name = "团内FST30逾期人数（默认值-1）")
    private Integer groupInfoUserFST30Count = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFST30TotalCount)
    @ModelField(name = "团内FST30已表现人数（默认值-1）")
    private Integer groupInfoUserFST30TotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFST30Rate)
    @ModelField(name = "批核件FST30占比（默认值-1）")
    private BigDecimal groupInfoUserFST30Rate = BigDecimal.valueOf(-1);

    //FSTQ30
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFSTQ30Count)
    @ModelField(name = "团内FSTQ30逾期人数（默认值-1）")
    private Integer groupInfoUserFSTQ30Count = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFSTQ30TotalCount)
    @ModelField(name = "团内FSTQ30已表现人数（默认值-1）")
    private Integer groupInfoUserFSTQ30TotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFSTQ30Rate)
    @ModelField(name = "批核件FSTQ30占比（默认值-1）")
    private BigDecimal groupInfoUserFSTQ30Rate = BigDecimal.valueOf(-1);

    //M3
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserM3PlusCount)
    @ModelField(name = "团内M3+逾期人数（默认值-1）")
    private Integer groupInfoUserM3PlusCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserM3PlusTotalCount)
    @ModelField(name = "团内M3+已表现人数（默认值-1）")
    private Integer groupInfoUserM3PlusTotalCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.GroupInfoUserM3PlusRate)
    @ModelField(name = "批核件M3+占比（默认值-1）")
    private BigDecimal groupInfoUserM3PlusRate = BigDecimal.valueOf(-1);

    @ModelField(name = "第二联系人关系")
    private String secondRelationship = "";

    @ModelField(name = "通讯录联系人个数(默认值-1)")
    private int addressBookContacts = -1;

    public String getSecondRelationship() {
        return secondRelationship;
    }

    public void setSecondRelationship(String secondRelationship) {
        this.secondRelationship = secondRelationship;
    }

    public int getAddressBookContacts() {
        return addressBookContacts;
    }

    public void setAddressBookContacts(int addressBookContacts) {
        this.addressBookContacts = addressBookContacts;
    }

    public String getLocalPhone() {
        return localPhone;
    }

    public void setLocalPhone(String localPhone) {
        this.localPhone = localPhone;
    }

    public String getLocalPhoneProvince() {
        return localPhoneProvince;
    }

    public void setLocalPhoneProvince(String localPhoneProvince) {
        this.localPhoneProvince = localPhoneProvince;
    }

    public Integer getCompanyTelNumberType() {
        return companyTelNumberType;
    }

    public void setCompanyTelNumberType(Integer companyTelNumberType) {
        this.companyTelNumberType = companyTelNumberType;
    }

    public Integer getGroupInfoUserTotalCount() {
        return groupInfoUserTotalCount;
    }

    public void setGroupInfoUserTotalCount(Integer groupInfoUserTotalCount) {
        this.groupInfoUserTotalCount = groupInfoUserTotalCount;
    }

    public Integer getGroupInfoUserApprovedCount() {
        return groupInfoUserApprovedCount;
    }

    public void setGroupInfoUserApprovedCount(Integer groupInfoUserApprovedCount) {
        this.groupInfoUserApprovedCount = groupInfoUserApprovedCount;
    }

    public BigDecimal getGroupInfoUserApprovedRate() {
        return groupInfoUserApprovedRate;
    }

    public void setGroupInfoUserApprovedRate(BigDecimal groupInfoUserApprovedRate) {
        this.groupInfoUserApprovedRate = groupInfoUserApprovedRate;
    }

    public Integer getGroupInfoUserRejectedCount() {
        return groupInfoUserRejectedCount;
    }

    public void setGroupInfoUserRejectedCount(Integer groupInfoUserRejectedCount) {
        this.groupInfoUserRejectedCount = groupInfoUserRejectedCount;
    }

    public BigDecimal getGroupInfoUserRejectedRate() {
        return groupInfoUserRejectedRate;
    }

    public void setGroupInfoUserRejectedRate(BigDecimal groupInfoUserRejectedRate) {
        this.groupInfoUserRejectedRate = groupInfoUserRejectedRate;
    }

    public Integer getGroupInfoUserCancelledCount() {
        return groupInfoUserCancelledCount;
    }

    public void setGroupInfoUserCancelledCount(Integer groupInfoUserCancelledCount) {
        this.groupInfoUserCancelledCount = groupInfoUserCancelledCount;
    }

    public Integer getGroupInfoUserPreRepayLoanCount() {
        return groupInfoUserPreRepayLoanCount;
    }

    public void setGroupInfoUserPreRepayLoanCount(Integer groupInfoUserPreRepayLoanCount) {
        this.groupInfoUserPreRepayLoanCount = groupInfoUserPreRepayLoanCount;
    }

    public Integer getGroupInfoUserFemaleCount() {
        return groupInfoUserFemaleCount;
    }

    public void setGroupInfoUserFemaleCount(Integer groupInfoUserFemaleCount) {
        this.groupInfoUserFemaleCount = groupInfoUserFemaleCount;
    }

    public Integer getGroupInfoBlackListCount() {
        return groupInfoBlackListCount;
    }

    public void setGroupInfoBlackListCount(Integer groupInfoBlackListCount) {
        this.groupInfoBlackListCount = groupInfoBlackListCount;
    }

    public BigDecimal getGroupInfoBlackListCountRate() {
        return groupInfoBlackListCountRate;
    }

    public void setGroupInfoBlackListCountRate(BigDecimal groupInfoBlackListCountRate) {
        this.groupInfoBlackListCountRate = groupInfoBlackListCountRate;
    }

    public Integer getGroupInfoReportedRejectedCount() {
        return groupInfoReportedRejectedCount;
    }

    public void setGroupInfoReportedRejectedCount(Integer groupInfoReportedRejectedCount) {
        this.groupInfoReportedRejectedCount = groupInfoReportedRejectedCount;
    }

    public BigDecimal getGroupInfoReportedRejectedCountRate() {
        return groupInfoReportedRejectedCountRate;
    }

    public void setGroupInfoReportedRejectedCountRate(BigDecimal groupInfoReportedRejectedCountRate) {
        this.groupInfoReportedRejectedCountRate = groupInfoReportedRejectedCountRate;
    }

    public Integer getGroupInfoFausd() {
        return groupInfoFausd;
    }

    public void setGroupInfoFausd(Integer groupInfoFausd) {
        this.groupInfoFausd = groupInfoFausd;
    }

    public Integer getGroupInfoUserLoanAmountTotal() {
        return groupInfoUserLoanAmountTotal;
    }

    public void setGroupInfoUserLoanAmountTotal(Integer groupInfoUserLoanAmountTotal) {
        this.groupInfoUserLoanAmountTotal = groupInfoUserLoanAmountTotal;
    }

    public Integer getGroupInfoUserAppRecent3daysCount() {
        return groupInfoUserAppRecent3daysCount;
    }

    public void setGroupInfoUserAppRecent3daysCount(Integer groupInfoUserAppRecent3daysCount) {
        this.groupInfoUserAppRecent3daysCount = groupInfoUserAppRecent3daysCount;
    }

    public Integer getGroupInfoUserRejectRecent3daysCount() {
        return groupInfoUserRejectRecent3daysCount;
    }

    public void setGroupInfoUserRejectRecent3daysCount(Integer groupInfoUserRejectRecent3daysCount) {
        this.groupInfoUserRejectRecent3daysCount = groupInfoUserRejectRecent3daysCount;
    }

    public Integer getGroupInfoUserAproveRecent3daysCount() {
        return groupInfoUserAproveRecent3daysCount;
    }

    public void setGroupInfoUserAproveRecent3daysCount(Integer groupInfoUserAproveRecent3daysCount) {
        this.groupInfoUserAproveRecent3daysCount = groupInfoUserAproveRecent3daysCount;
    }

    public Integer getGroupInfoUserAppRecent7daysCount() {
        return groupInfoUserAppRecent7daysCount;
    }

    public void setGroupInfoUserAppRecent7daysCount(Integer groupInfoUserAppRecent7daysCount) {
        this.groupInfoUserAppRecent7daysCount = groupInfoUserAppRecent7daysCount;
    }

    public Integer getGroupInfoUserAproveRecent7daysCount() {
        return groupInfoUserAproveRecent7daysCount;
    }

    public void setGroupInfoUserAproveRecent7daysCount(Integer groupInfoUserAproveRecent7daysCount) {
        this.groupInfoUserAproveRecent7daysCount = groupInfoUserAproveRecent7daysCount;
    }

    public Integer getGroupInfoUserRejectRecent7daysCount() {
        return groupInfoUserRejectRecent7daysCount;
    }

    public void setGroupInfoUserRejectRecent7daysCount(Integer groupInfoUserRejectRecent7daysCount) {
        this.groupInfoUserRejectRecent7daysCount = groupInfoUserRejectRecent7daysCount;
    }

    public Integer getGroupInfoUserAppRecent1monthCount() {
        return groupInfoUserAppRecent1monthCount;
    }

    public void setGroupInfoUserAppRecent1monthCount(Integer groupInfoUserAppRecent1monthCount) {
        this.groupInfoUserAppRecent1monthCount = groupInfoUserAppRecent1monthCount;
    }

    public Integer getGroupInfoUserAproveRecent1monthCount() {
        return groupInfoUserAproveRecent1monthCount;
    }

    public void setGroupInfoUserAproveRecent1monthCount(Integer groupInfoUserAproveRecent1monthCount) {
        this.groupInfoUserAproveRecent1monthCount = groupInfoUserAproveRecent1monthCount;
    }

    public Integer getGroupInfoUserRejectRecent1monthCount() {
        return groupInfoUserRejectRecent1monthCount;
    }

    public void setGroupInfoUserRejectRecent1monthCount(Integer groupInfoUserRejectRecent1monthCount) {
        this.groupInfoUserRejectRecent1monthCount = groupInfoUserRejectRecent1monthCount;
    }

    public Integer getGroupInfoUserAppRecent3monthsCount() {
        return groupInfoUserAppRecent3monthsCount;
    }

    public void setGroupInfoUserAppRecent3monthsCount(Integer groupInfoUserAppRecent3monthsCount) {
        this.groupInfoUserAppRecent3monthsCount = groupInfoUserAppRecent3monthsCount;
    }

    public Integer getGroupInfoUserAproveRecent3monthCount() {
        return groupInfoUserAproveRecent3monthCount;
    }

    public void setGroupInfoUserAproveRecent3monthCount(Integer groupInfoUserAproveRecent3monthCount) {
        this.groupInfoUserAproveRecent3monthCount = groupInfoUserAproveRecent3monthCount;
    }

    public Integer getGroupInfoUserRejectRecent3monthCount() {
        return groupInfoUserRejectRecent3monthCount;
    }

    public void setGroupInfoUserRejectRecent3monthCount(Integer groupInfoUserRejectRecent3monthCount) {
        this.groupInfoUserRejectRecent3monthCount = groupInfoUserRejectRecent3monthCount;
    }

    public String getGroupInfoUserMainProvince() {
        return groupInfoUserMainProvince;
    }

    public void setGroupInfoUserMainProvince(String groupInfoUserMainProvince) {
        this.groupInfoUserMainProvince = groupInfoUserMainProvince;
    }

    public String getGroupInfoUserMainCity() {
        return groupInfoUserMainCity;
    }

    public void setGroupInfoUserMainCity(String groupInfoUserMainCity) {
        this.groupInfoUserMainCity = groupInfoUserMainCity;
    }

    public Integer getGroupInfoUserFPD1Count() {
        return groupInfoUserFPD1Count;
    }

    public void setGroupInfoUserFPD1Count(Integer groupInfoUserFPD1Count) {
        this.groupInfoUserFPD1Count = groupInfoUserFPD1Count;
    }

    public Integer getGroupInfoUserFPD1TotalCount() {
        return groupInfoUserFPD1TotalCount;
    }

    public void setGroupInfoUserFPD1TotalCount(Integer groupInfoUserFPD1TotalCount) {
        this.groupInfoUserFPD1TotalCount = groupInfoUserFPD1TotalCount;
    }

    public Integer getGroupInfoUserFPD7Count() {
        return groupInfoUserFPD7Count;
    }

    public void setGroupInfoUserFPD7Count(Integer groupInfoUserFPD7Count) {
        this.groupInfoUserFPD7Count = groupInfoUserFPD7Count;
    }

    public Integer getGroupInfoUserFPD7TotalCount() {
        return groupInfoUserFPD7TotalCount;
    }

    public void setGroupInfoUserFPD7TotalCount(Integer groupInfoUserFPD7TotalCount) {
        this.groupInfoUserFPD7TotalCount = groupInfoUserFPD7TotalCount;
    }

    public BigDecimal getGroupInfoUserFPD7Rate() {
        return groupInfoUserFPD7Rate;
    }

    public void setGroupInfoUserFPD7Rate(BigDecimal groupInfoUserFPD7Rate) {
        this.groupInfoUserFPD7Rate = groupInfoUserFPD7Rate;
    }

    public Integer getGroupInfoUserFPD30Count() {
        return groupInfoUserFPD30Count;
    }

    public void setGroupInfoUserFPD30Count(Integer groupInfoUserFPD30Count) {
        this.groupInfoUserFPD30Count = groupInfoUserFPD30Count;
    }

    public Integer getGroupInfoUserFPD30TotalCount() {
        return groupInfoUserFPD30TotalCount;
    }

    public void setGroupInfoUserFPD30TotalCount(Integer groupInfoUserFPD30TotalCount) {
        this.groupInfoUserFPD30TotalCount = groupInfoUserFPD30TotalCount;
    }

    public BigDecimal getGroupInfoUserFPD30Rate() {
        return groupInfoUserFPD30Rate;
    }

    public void setGroupInfoUserFPD30Rate(BigDecimal groupInfoUserFPD30Rate) {
        this.groupInfoUserFPD30Rate = groupInfoUserFPD30Rate;
    }

    public Integer getGroupInfoUserSPD30Count() {
        return groupInfoUserSPD30Count;
    }

    public void setGroupInfoUserSPD30Count(Integer groupInfoUserSPD30Count) {
        this.groupInfoUserSPD30Count = groupInfoUserSPD30Count;
    }

    public Integer getGroupInfoUserSPD30TotalCount() {
        return groupInfoUserSPD30TotalCount;
    }

    public void setGroupInfoUserSPD30TotalCount(Integer groupInfoUserSPD30TotalCount) {
        this.groupInfoUserSPD30TotalCount = groupInfoUserSPD30TotalCount;
    }

    public BigDecimal getGroupInfoUserSPD30Rate() {
        return groupInfoUserSPD30Rate;
    }

    public void setGroupInfoUserSPD30Rate(BigDecimal groupInfoUserSPD30Rate) {
        this.groupInfoUserSPD30Rate = groupInfoUserSPD30Rate;
    }

    public Integer getGroupInfoUserTPD30Count() {
        return groupInfoUserTPD30Count;
    }

    public void setGroupInfoUserTPD30Count(Integer groupInfoUserTPD30Count) {
        this.groupInfoUserTPD30Count = groupInfoUserTPD30Count;
    }

    public Integer getGroupInfoUserTPD30TotalCount() {
        return groupInfoUserTPD30TotalCount;
    }

    public void setGroupInfoUserTPD30TotalCount(Integer groupInfoUserTPD30TotalCount) {
        this.groupInfoUserTPD30TotalCount = groupInfoUserTPD30TotalCount;
    }

    public BigDecimal getGroupInfoUserTPD30Rate() {
        return groupInfoUserTPD30Rate;
    }

    public void setGroupInfoUserTPD30Rate(BigDecimal groupInfoUserTPD30Rate) {
        this.groupInfoUserTPD30Rate = groupInfoUserTPD30Rate;
    }

    public Integer getGroupInfoUserFS30Count() {
        return groupInfoUserFS30Count;
    }

    public void setGroupInfoUserFS30Count(Integer groupInfoUserFS30Count) {
        this.groupInfoUserFS30Count = groupInfoUserFS30Count;
    }

    public Integer getGroupInfoUserFS30TotalCount() {
        return groupInfoUserFS30TotalCount;
    }

    public void setGroupInfoUserFS30TotalCount(Integer groupInfoUserFS30TotalCount) {
        this.groupInfoUserFS30TotalCount = groupInfoUserFS30TotalCount;
    }

    public BigDecimal getGroupInfoUserFS30Rate() {
        return groupInfoUserFS30Rate;
    }

    public void setGroupInfoUserFS30Rate(BigDecimal groupInfoUserFS30Rate) {
        this.groupInfoUserFS30Rate = groupInfoUserFS30Rate;
    }

    public Integer getGroupInfoUserFST30Count() {
        return groupInfoUserFST30Count;
    }

    public void setGroupInfoUserFST30Count(Integer groupInfoUserFST30Count) {
        this.groupInfoUserFST30Count = groupInfoUserFST30Count;
    }

    public Integer getGroupInfoUserFST30TotalCount() {
        return groupInfoUserFST30TotalCount;
    }

    public void setGroupInfoUserFST30TotalCount(Integer groupInfoUserFST30TotalCount) {
        this.groupInfoUserFST30TotalCount = groupInfoUserFST30TotalCount;
    }

    public BigDecimal getGroupInfoUserFST30Rate() {
        return groupInfoUserFST30Rate;
    }

    public void setGroupInfoUserFST30Rate(BigDecimal groupInfoUserFST30Rate) {
        this.groupInfoUserFST30Rate = groupInfoUserFST30Rate;
    }

    public Integer getGroupInfoUserFSTQ30Count() {
        return groupInfoUserFSTQ30Count;
    }

    public void setGroupInfoUserFSTQ30Count(Integer groupInfoUserFSTQ30Count) {
        this.groupInfoUserFSTQ30Count = groupInfoUserFSTQ30Count;
    }

    public Integer getGroupInfoUserFSTQ30TotalCount() {
        return groupInfoUserFSTQ30TotalCount;
    }

    public void setGroupInfoUserFSTQ30TotalCount(Integer groupInfoUserFSTQ30TotalCount) {
        this.groupInfoUserFSTQ30TotalCount = groupInfoUserFSTQ30TotalCount;
    }

    public BigDecimal getGroupInfoUserFSTQ30Rate() {
        return groupInfoUserFSTQ30Rate;
    }

    public void setGroupInfoUserFSTQ30Rate(BigDecimal groupInfoUserFSTQ30Rate) {
        this.groupInfoUserFSTQ30Rate = groupInfoUserFSTQ30Rate;
    }

    public Integer getGroupInfoUserM3PlusCount() {
        return groupInfoUserM3PlusCount;
    }

    public void setGroupInfoUserM3PlusCount(Integer groupInfoUserM3PlusCount) {
        this.groupInfoUserM3PlusCount = groupInfoUserM3PlusCount;
    }

    public Integer getGroupInfoUserM3PlusTotalCount() {
        return groupInfoUserM3PlusTotalCount;
    }

    public void setGroupInfoUserM3PlusTotalCount(Integer groupInfoUserM3PlusTotalCount) {
        this.groupInfoUserM3PlusTotalCount = groupInfoUserM3PlusTotalCount;
    }

    public BigDecimal getGroupInfoUserM3PlusRate() {
        return groupInfoUserM3PlusRate;
    }

    public void setGroupInfoUserM3PlusRate(BigDecimal groupInfoUserM3PlusRate) {
        this.groupInfoUserM3PlusRate = groupInfoUserM3PlusRate;
    }

    public BigDecimal getGroupInfoUserFPD1Rate() {
        return groupInfoUserFPD1Rate;
    }

    public void setGroupInfoUserFPD1Rate(BigDecimal groupInfoUserFPD1Rate) {
        this.groupInfoUserFPD1Rate = groupInfoUserFPD1Rate;
    }   
}
