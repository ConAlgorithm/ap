package network.relationship.businessdomain;

import java.math.BigDecimal;

import catfish.base.business.common.AppDerivativeVariableConsts;
import network.relationship.api.DBField;

public class GroupInfo {
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserTotalCount)
    public Integer groupInfoUserTotalCount;

    // Approve
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserApprovedCount)
    public Integer groupInfoUserApprovedCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserApprovedRate)
    public BigDecimal groupInfoUserApprovedRate;

    // Rejected
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserRejectedCount)
    public Integer groupInfoUserRejectedCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserRejectedRate)
    public BigDecimal groupInfoUserRejectedRate;

    //Cancelled
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserCancelledCount)
    public Integer groupInfoUserCancelledCount;
    
    // Prepayment
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserPreRepayLoanCount)
    public Integer groupInfoUserPreRepayLoanCount;
    
    // Black
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoBlackListCount)
    public Integer groupInfoBlackListCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoBlackListCountRate)
    public BigDecimal groupInfoBlackListCountRate;
    
    // Reported
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoReportedRejectedCount)
    public Integer groupInfoReportedRejectedCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoReportedRejectedCountRate)
    public BigDecimal groupInfoReportedRejectedCountRate;
    
    //Fraud
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoFausd)
    public Integer groupInfoFausd;
    
    //Fmale
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFemaleCount)
    public Integer groupInfoUserFemaleCount;
    
    //Fmale
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserM3PlusCount)
    public Integer groupInfoUserM3PlusCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserM3PlusTotalCount)
    public Integer groupInfoUserM3PlusTotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserM3PlusRate)
    public BigDecimal groupInfoUserM3PlusRate;
    
    // Recent 3 days
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserAppRecent3daysCount)
    public Integer groupInfoUserAppRecent3daysCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserRejectRecent3daysCount)
    public Integer groupInfoUserRejectRecent3daysCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserAproveRecent3daysCount)
    public Integer groupInfoUserAproveRecent3daysCount;
    
    
    
    // Recent 7 days
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserAppRecent7daysCount)
    public Integer groupInfoUserAppRecent7daysCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserRejectRecent7daysCount)
    public Integer groupInfoUserRejectRecent7daysCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserAproveRecent7daysCount)
    public Integer groupInfoUserAproveRecent7daysCount;
    
    
    // Recent 1 month
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserAppRecent1monthCount)
    public Integer groupInfoUserAppRecent1monthCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserRejectRecent1monthCount)
    public Integer groupInfoUserRejectRecent1monthCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserAproveRecent1monthCount)
    public Integer groupInfoUserAproveRecent1monthCount;
    
    // Recent 3 month
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserAppRecent3monthsCount)
    public Integer groupInfoUserAppRecent3monthsCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserRejectRecent3monthCount)
    public Integer groupInfoUserRejectRecent3monthCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserAproveRecent3monthCount)
    public Integer groupInfoUserAproveRecent3monthCount;
    
    // Main city and province
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserMainProvince)
    public String groupInfoUserMainProvince;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserMainCity)
    public String groupInfoUserMainCity;
    
    // Loan amount
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserLoanAmountTotal)
    public Integer groupInfoUserLoanAmountTotal;
    
    // FPD1
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD1Count)
    public Integer groupInfoUserFPD1Count;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD1TotalCount)
    public Integer groupInfoUserFPD1TotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD1Rate)
    public BigDecimal groupInfoUserFPD1Rate;
    
    //FPD7
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD7Count)
    public Integer groupInfoUserFPD7Count;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD7TotalCount)
    public Integer groupInfoUserFPD7TotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD7Rate)
    public BigDecimal groupInfoUserFPD7Rate;
    
    //FPD30
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD30Count)
    public Integer groupInfoUserFPD30Count;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD30TotalCount)
    public Integer groupInfoUserFPD30TotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFPD30Rate)
    public BigDecimal groupInfoUserFPD30Rate;
    
    //SPD30
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserSPD30Count)
    public Integer groupInfoUserSPD30Count;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserSPD30TotalCount)
    public Integer groupInfoUserSPD30TotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserSPD30Rate)
    public BigDecimal groupInfoUserSPD30Rate;
    
    //TPD30
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserTPD30Count)
    public Integer groupInfoUserTPD30Count;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserTPD30TotalCount)
    public Integer groupInfoUserTPD30TotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserTPD30Rate)
    public BigDecimal groupInfoUserTPD30Rate;
    
    // FS30
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFS30Count)
    public Integer groupInfoUserFS30Count;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFS30TotalCount)
    public Integer groupInfoUserFS30TotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFS30Rate)
    public BigDecimal groupInfoUserFS30Rate;
    
    // FST30
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFST30Count)
    public Integer groupInfoUserFST30Count;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFST30TotalCount)
    public Integer groupInfoUserFST30TotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFST30Rate)
    public BigDecimal groupInfoUserFST30Rate;
    
    // FSTQ30
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFSTQ30Count)
    public Integer groupInfoUserFSTQ30Count;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFSTQ30TotalCount)
    public Integer groupInfoUserFSTQ30TotalCount;
    
    @DBField(variableName = AppDerivativeVariableConsts.GroupInfoUserFSTQ30Rate)
    public BigDecimal groupInfoUserFSTQ30Rate;
}
