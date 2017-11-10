package engine.rule.model.creator.cashloan;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.cashloan.CreditCheckPercentInForm;

public class CreditCheckPercentModelCreator extends AbstractApplicationModelCreator {

	public CreditCheckPercentModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<CreditCheckPercentInForm>(new CreditCheckPercentInForm())
				.buidDerivativeVariables(appId).getForm();
		return CollectionUtils.mapOf("in_CreditPcent", (Object) form);
	}

	@Override
	public String createBusinessNo() {
		return null;
	}

}
