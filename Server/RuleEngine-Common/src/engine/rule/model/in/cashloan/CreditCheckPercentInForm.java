package engine.rule.model.in.cashloan;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import catfish.base.business.common.jxl.CheckPointResult;
import catfish.base.business.util.AppDerivativeVariableNames;
import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "聚信力增信额度所需数据信息")
public class CreditCheckPercentInForm extends BaseForm{

	/********************
	 * 聚信力
	 ***********************/
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPROTDATA_SUCCESS_FLAG)
	@ModelField(name = "聚信立报告是否收集到(默认值否)")
	private boolean jxlReportDataSuccess = false;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_REGION_COUNT)
	@ModelField(name = "联系地区数量(默认值-1)")
	private Integer contactRegionCount = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_COUNT)
	@ModelField(name = "联系人数量(默认值-1)")
	private Integer contactCount = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_LENGTH)
	@ModelField(name = "呼出通话总时长(默认值-1)")
	private int callOutLength = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_COUNT)
	@ModelField(name = "呼出通话总数量(默认值-1)")
	private int callOutCount = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_LENGTH)
	@ModelField(name = "呼入通话总时长(默认值-1)")
	private int callInLength = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_COUNT)
	@ModelField(name = "呼入通话总数量(默认值-1)")
	private int callInCount = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_DATA_EXIST_MONTH_COUNT)
	@ModelField(name = "有数据月数(默认值-1)")
	private int dataExistMonthCount = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_NUMBER_COUNT)
	@ModelField(name = "联系号码数量(默认值-1)")
	private int contactNumberCount = -1;

	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_REAL_AUTHENTICATED)
	@ModelField(name = "是否实名认证(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
	private String realAuthencated = CheckPointResult.NoData.getValue();

	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_PROVIDER_INFO_MATCH)
	@ModelField(name = "运营商信息是否匹配(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
	private String providerInfoMatch = CheckPointResult.NoData.getValue();

	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_ALWAYS_POWEROFF)
	@ModelField(name = "是否长时间关机(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
	private String alwaysPowerOff = CheckPointResult.NoData.getValue();

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALLlOANPHONE)
	@ModelField(name = "是否拨打含‘贷款’的电话(默认值否)")
	private boolean hasCallLoanPhone = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALLFINANCEPHONE)
	@ModelField(name = "是否拨打含‘金融’的电话(默认值否)")
	private boolean hasCallFinancePhone = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALLJIEXINPHONE)
	@ModelField(name = "是否拨打含‘捷信’的电话(默认值否)")
	private boolean hasCallJiexinPhone = false;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_REG_CONTACTPHONE_IN_JXL_NUM)
	@ModelField(name = "用户所填联系人电话在聚信力中出现个数(默认值0)")
	private int contactPhoneNumberInJxlCount = 0;

	@ModelMethod(name = "(this)是否收集到聚信力报告")
	public boolean isJXLReportExist() {
		return this.jxlReportDataSuccess;
	}

	public boolean isJxlReportDataSuccess() {
		return jxlReportDataSuccess;
	}

	public void setJxlReportDataSuccess(boolean jxlReportDataSuccess) {
		this.jxlReportDataSuccess = jxlReportDataSuccess;
	}

	public Integer getContactRegionCount() {
		return contactRegionCount;
	}

	public void setContactRegionCount(Integer contactRegionCount) {
		this.contactRegionCount = contactRegionCount;
	}

	public Integer getContactCount() {
		return contactCount;
	}

	public void setContactCount(Integer contactCount) {
		this.contactCount = contactCount;
	}

	public int getCallOutLength() {
		return callOutLength;
	}

	public void setCallOutLength(int callOutLength) {
		this.callOutLength = callOutLength;
	}

	public int getCallOutCount() {
		return callOutCount;
	}

	public void setCallOutCount(int callOutCount) {
		this.callOutCount = callOutCount;
	}

	public int getCallInLength() {
		return callInLength;
	}

	public void setCallInLength(int callInLength) {
		this.callInLength = callInLength;
	}

	public int getCallInCount() {
		return callInCount;
	}

	public void setCallInCount(int callInCount) {
		this.callInCount = callInCount;
	}

	public int getDataExistMonthCount() {
		return dataExistMonthCount;
	}

	public void setDataExistMonthCount(int dataExistMonthCount) {
		this.dataExistMonthCount = dataExistMonthCount;
	}

	public int getContactNumberCount() {
		return contactNumberCount;
	}

	public void setContactNumberCount(int contactNumberCount) {
		this.contactNumberCount = contactNumberCount;
	}

	public String getRealAuthencated() {
		return realAuthencated;
	}

	public void setRealAuthencated(String realAuthencated) {
		this.realAuthencated = realAuthencated;
	}

	public String getProviderInfoMatch() {
		return providerInfoMatch;
	}

	public void setProviderInfoMatch(String providerInfoMatch) {
		this.providerInfoMatch = providerInfoMatch;
	}

	public String getAlwaysPowerOff() {
		return alwaysPowerOff;
	}

	public void setAlwaysPowerOff(String alwaysPowerOff) {
		this.alwaysPowerOff = alwaysPowerOff;
	}

	public boolean isHasCallLoanPhone() {
		return hasCallLoanPhone;
	}

	public void setHasCallLoanPhone(boolean hasCallLoanPhone) {
		this.hasCallLoanPhone = hasCallLoanPhone;
	}

	public boolean isHasCallFinancePhone() {
		return hasCallFinancePhone;
	}

	public void setHasCallFinancePhone(boolean hasCallFinancePhone) {
		this.hasCallFinancePhone = hasCallFinancePhone;
	}

	public boolean isHasCallJiexinPhone() {
		return hasCallJiexinPhone;
	}

	public void setHasCallJiexinPhone(boolean hasCallJiexinPhone) {
		this.hasCallJiexinPhone = hasCallJiexinPhone;
	}

	public int getContactPhoneNumberInJxlCount() {
		return contactPhoneNumberInJxlCount;
	}

	public void setContactPhoneNumberInJxlCount(int contactPhoneNumberInJxlCount) {
		this.contactPhoneNumberInJxlCount = contactPhoneNumberInJxlCount;
	}

}
