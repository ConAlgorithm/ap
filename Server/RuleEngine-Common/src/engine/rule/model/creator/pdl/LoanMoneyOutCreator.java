package engine.rule.model.creator.pdl;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.inout.pdl.LoanMoneyDecisionOutForm;

public class LoanMoneyOutCreator extends AbstractApplicationModelCreator {

	public LoanMoneyOutCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		Map<String, Object> outParams = CollectionUtils
				.<String, Object> newMapBuilder().build();
				
		outParams.put(LoanMoneyDecisionOutForm.Key, new LoanMoneyDecisionOutForm());
		return outParams;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
