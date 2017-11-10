package engine.rule.model.inout.app;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.rule.model.BaseForm;

@ModelInstance(description = "门店评级结果")
public class StoreInOutForm extends BaseForm {

	@ModelField(name = "门店评级标签")
	private String storeLevelResult = "NOR";

	public String getStoreLevelResult() {
		return storeLevelResult;
	}

	public void setStoreLevelResult(String storeLevelResult) {
		this.storeLevelResult = storeLevelResult;
	}

}
