package engine.rule.model.in.app;

import catfish.base.business.common.jxl.CheckPointResult;
import catfish.base.business.util.AppDerivativeVariableNames;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "测试模型")
public class TestInForm {

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_REAL_AUTHENTICATED)
	@ModelField(name = "是否实名认证", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
	private String realAuthencated  = CheckPointResult.NoData.getValue();
	
	@ModelField(name = "输入Integer")
	private Integer input = 0;

	@ModelField(name = "输出Integer")
	private Integer output = 0;

	public Integer getInput() {
		return input;
	}

	public void setInput(Integer input) {
		this.input = input;
	}

	public Integer getOutput() {
		return output;
	}

	public void setOutput(Integer output) {
		this.output = output;
	}

}
