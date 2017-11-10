package engine.rule.model.creator.pdl;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.pdl.InvestigationInForm;

public class InvestigationModelCreator extends AbstractApplicationModelCreator {

	public InvestigationModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<InvestigationInForm>(new InvestigationInForm())
				.buidDerivativeVariables(appId).getForm();
		return CollectionUtils.mapOf("in_Investigation", (Object) form);
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
