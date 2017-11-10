package engine.rule.model.in.pdl;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.Gender;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "个人特征")
public class PersonalInfoInForm extends BaseForm {

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.IdCardAge)
	@ModelField(name = "年龄")
	public Integer idCardAge = 0;

	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.IdCardGender)
	@ModelField(name = "性别", bindDomain = "engine.rule.domain.Gender")
	private String idCardGender = Gender.Male.getValue();

	/************* 此处在规则编写时需注意,将直辖市放入省份中，城市为空 *****************/
	// finished
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.IdcardProvince)
	@ModelField(name = "出生地省份")
	private String idCardProvince = "";

	// finished
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.IdCardCity)
	@ModelField(name = "出生地城市")
	private String idCardCity = "";
	/*************************************/

	// finished!此处应该将数据库中的StringValue移至IntValue，然后将类型改为Integer
	// 设置默认的民族，汉族
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.IdCardInfoNationality)
	@ModelField(name = "民族", bindDomain = "engine.rule.domain.Nationality")
	private Integer nationality = 11;

	public Integer getIdCardAge() {
		return idCardAge;
	}

	public void setIdCardAge(Integer idCardAge) {
		this.idCardAge = idCardAge;
	}

	public String getIdCardGender() {
		return idCardGender;
	}

	public void setIdCardGender(String idCardGender) {
		this.idCardGender = idCardGender;
	}

	public String getIdCardProvince() {
		return idCardProvince;
	}

	public void setIdCardProvince(String idCardProvince) {
		this.idCardProvince = idCardProvince;
	}

	public String getIdCardCity() {
		return idCardCity;
	}

	public void setIdCardCity(String idCardCity) {
		this.idCardCity = idCardCity;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}
}
