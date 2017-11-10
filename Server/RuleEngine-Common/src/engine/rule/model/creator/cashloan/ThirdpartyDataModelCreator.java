package engine.rule.model.creator.cashloan;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.cashloan.ThirdpartyDataInForm;

public class ThirdpartyDataModelCreator extends AbstractApplicationModelCreator{

	public ThirdpartyDataModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<ThirdpartyDataInForm>(new ThirdpartyDataInForm())
				.buidDerivativeVariables(appId).getForm();
		return CollectionUtils.mapOf("in_Thirdparty", (Object) form);
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}
}
