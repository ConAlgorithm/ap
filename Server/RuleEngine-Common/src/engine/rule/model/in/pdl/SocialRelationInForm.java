package engine.rule.model.in.pdl;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.CompanyContactType;

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

	// finished
	// 在creator中手动填写
	@ModelField(name = "单位联系电话", bindDomain = "engine.rule.domain.CompanyContactType")
	private Integer companyTelNumberType = CompanyContactType.Empty.getValue();

	//group info
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoAppTotalCount)
	@ModelField(name = "团内申请总数（默认值-1）")
    private Integer appTotalCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoAppApprovedCount)
	@ModelField(name = "团内批准申请总数（默认值-1）")
    private Integer appApprovedCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoAppRejectedCount)
	@ModelField(name = "团内拒绝申请总数（默认值-1）")
    private Integer appRejectedCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoAppCancelledCount)
	@ModelField(name = "团内取消申请总数（默认值-1）")
    private Integer appCancelledCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoAppFPD1Count)
	@ModelField(name = "团内FPD1申请总数（默认值-1）")
    private Integer appFPD1Count = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoAppFPD1TotalCount)
	@ModelField(name = "团内FPD1表现期申请总数（默认值-1）")
    private Integer appFPD1TotalCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoAppFPD30Count)
	@ModelField(name = "团内FPD30申请总数（默认值-1）")
    private Integer appFPD30Count = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoAppFPD30TotalCount)
	@ModelField(name = "团内FPD30表现期申请总数（默认值-1）")
    private Integer appFPD30TotalCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserTotalCount)
	@ModelField(name = "团内用户总数（默认值-1）")
    private Integer userTotalCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserApprovedCount)
	@ModelField(name = "团内批准用户总数（默认值-1）")
    private Integer userApprovedCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserRejectedCount)
	@ModelField(name = "团内拒绝用户总数（默认值-1）")
    private Integer userRejectedCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserCancelledCount)
	@ModelField(name = "团内取消用户总数（默认值-1）")
    private Integer userCancelledCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD1Count)
	@ModelField(name = "团内FPD1用户总数（默认值-1）")
    private Integer userFPD1Count = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD1TotalCount)
	@ModelField(name = "团内FPD1表现期用户总数（默认值-1）")
    private Integer userFPD1TotalCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD30Count)
	@ModelField(name = "团内FPD30用户总数（默认值-1）")
    private Integer userFPD30Count = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.GroupInfoUserFPD30TotalCount)
	@ModelField(name = "团内FPD30表现期用户总数（默认值-1）")
    private Integer userFPD30TotalCount = -1;
	
    public String getLocalPhone() {
		return localPhone;
	}

	public void setLocalPhone(String localPhone) {
		this.localPhone = localPhone;
	}

	public Integer getCompanyTelNumberType() {
		return companyTelNumberType;
	}

	public void setCompanyTelNumberType(Integer companyTelNumberType) {
		this.companyTelNumberType = companyTelNumberType;
	}

	public Integer getAppTotalCount() {
        return appTotalCount;
    }

    public void setAppTotalCount(Integer appTotalCount) {
        this.appTotalCount = appTotalCount;
    }

    public Integer getAppApprovedCount() {
        return appApprovedCount;
    }

    public void setAppApprovedCount(Integer appApprovedCount) {
        this.appApprovedCount = appApprovedCount;
    }

    public Integer getAppRejectedCount() {
        return appRejectedCount;
    }

    public void setAppRejectedCount(Integer appRejectedCount) {
        this.appRejectedCount = appRejectedCount;
    }

    public Integer getAppCancelledCount() {
        return appCancelledCount;
    }

    public void setAppCancelledCount(Integer appCancelledCount) {
        this.appCancelledCount = appCancelledCount;
    }

    public Integer getUserTotalCount() {
        return userTotalCount;
    }

    public void setUserTotalCount(Integer userTotalCount) {
        this.userTotalCount = userTotalCount;
    }

	public Integer getAppFPD1Count() {
		return appFPD1Count;
	}

	public void setAppFPD1Count(Integer appFPD1Count) {
		this.appFPD1Count = appFPD1Count;
	}

	public Integer getAppFPD1TotalCount() {
		return appFPD1TotalCount;
	}

	public void setAppFPD1TotalCount(Integer appFPD1TotalCount) {
		this.appFPD1TotalCount = appFPD1TotalCount;
	}

	public Integer getAppFPD30Count() {
		return appFPD30Count;
	}

	public void setAppFPD30Count(Integer appFPD30Count) {
		this.appFPD30Count = appFPD30Count;
	}

	public Integer getAppFPD30TotalCount() {
		return appFPD30TotalCount;
	}

	public void setAppFPD30TotalCount(Integer appFPD30TotalCount) {
		this.appFPD30TotalCount = appFPD30TotalCount;
	}

	public Integer getUserApprovedCount() {
		return userApprovedCount;
	}

	public void setUserApprovedCount(Integer userApprovedCount) {
		this.userApprovedCount = userApprovedCount;
	}

	public Integer getUserRejectedCount() {
		return userRejectedCount;
	}

	public void setUserRejectedCount(Integer userRejectedCount) {
		this.userRejectedCount = userRejectedCount;
	}

	public Integer getUserCancelledCount() {
		return userCancelledCount;
	}

	public void setUserCancelledCount(Integer userCancelledCount) {
		this.userCancelledCount = userCancelledCount;
	}

	public Integer getUserFPD1Count() {
		return userFPD1Count;
	}

	public void setUserFPD1Count(Integer userFPD1Count) {
		this.userFPD1Count = userFPD1Count;
	}

	public Integer getUserFPD1TotalCount() {
		return userFPD1TotalCount;
	}

	public void setUserFPD1TotalCount(Integer userFPD1TotalCount) {
		this.userFPD1TotalCount = userFPD1TotalCount;
	}

	public Integer getUserFPD30Count() {
		return userFPD30Count;
	}

	public void setUserFPD30Count(Integer userFPD30Count) {
		this.userFPD30Count = userFPD30Count;
	}

	public Integer getUserFPD30TotalCount() {
		return userFPD30TotalCount;
	}

	public void setUserFPD30TotalCount(Integer userFPD30TotalCount) {
		this.userFPD30TotalCount = userFPD30TotalCount;
	}

}
