package engine.rule.model.in.pdl;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.CheckNameIDCardResult;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "衍生变量材料")
public class ConsistencyCheckInForm extends BaseForm {

	// finished
	// 在creator中手动填写
	@ModelField(name = "联系人不同城市数")
	private int contactorCitiesNumber = -99;

	@ModelField(name = "是否本地人（出生地和门店所在城市是否匹配）")
	private boolean nativePerson;

	/************** for precheck **************/
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IsUserInfoInBlacklist)
	@ModelField(name = "用户信息在内部黑名单中")
	private Boolean userInfoInBlackList = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IsIdInSupremeCourtBlacklist)
	@ModelField(name = "用户身份证在最高法院黑名单中")
	private Boolean idInSupremeCourtBlackList = false;

	// 身份证校验码为假
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IdCardIsChecksumValid)
	private Boolean idCardIsChecksumValid;
	
	//
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.AllContactCount)
	@ModelField(name = "联系人数")
	private Integer allContactCount = 2; //default: no additional contacts
	

	public Boolean getIdCardIsChecksumValid() {
		return idCardIsChecksumValid;
	}

	public void setIdCardIsChecksumValid(Boolean idCardIsChecksumValid) {
		this.idCardIsChecksumValid = idCardIsChecksumValid;
	}

	@ModelMethod(name = "(this)的身份证校验码为假")
	public boolean isIdCardIsChecksumInvalid() {
		return (idCardIsChecksumValid == null ? true : !idCardIsChecksumValid);
	}

	/****************************************/

	/*************************** for nameIdcardmatch check ******************/
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.IdCardIdentificationResult)
	@ModelField(name = "(this)的身份证和姓名是否一致(默认一致)", bindDomain = "engine.rule.domain.CheckNameIDCardResult")
	private String idCardIdentificationResult = CheckNameIDCardResult.Match.getValue();

	public String getIdCardIdentificationResult() {
		return idCardIdentificationResult;
	}

	public void setIdCardIdentificationResult(String idCardIdentificationResult) {
		this.idCardIdentificationResult = idCardIdentificationResult;
	}

	/**********************************************************************/
	public boolean isNativePerson() {
		return nativePerson;
	}

	public void setNativePerson(boolean nativePerson) {
		this.nativePerson = nativePerson;
	}

	public int getContactorCitiesNumber() {
		return contactorCitiesNumber;
	}

	public void setContactorCitiesNumber(int contactorCitiesNumber) {
		this.contactorCitiesNumber = contactorCitiesNumber;
	}

	public Boolean getUserInfoInBlackList() {
		return userInfoInBlackList;
	}

	public void setUserInfoInBlackList(Boolean userInfoInBlackList) {
		this.userInfoInBlackList = userInfoInBlackList;
	}

	public Boolean getIdInSupremeCourtBlackList() {
		return idInSupremeCourtBlackList;
	}

	public void setIdInSupremeCourtBlackList(Boolean idInSupremeCourtBlackList) {
		this.idInSupremeCourtBlackList = idInSupremeCourtBlackList;
	}

	public Integer getAllContactCount() {
		return allContactCount;
	}

	public void setAllContactCount(Integer allContactCount) {
		this.allContactCount = allContactCount;
	}
}
