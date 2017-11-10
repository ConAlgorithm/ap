package engine.rule.model.creator.cashloan;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.cashloan.CreditReferenceInForm;

public class CreditReferenceModelCreator extends AbstractApplicationModelCreator {

	public CreditReferenceModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {

		form = new ModelBuilder<CreditReferenceInForm>(
				new CreditReferenceInForm()).buidDerivativeVariables(appId)
				.getForm();

		Map<String, Object> params = CollectionUtils.mapOf("in_Bureau", (Object) form);
		return params;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
