package engine.rule.model.creator.pdl;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.pdl.HistoricalPerformanceInForm;

public class HistoricalPerformanceCreator extends AbstractApplicationModelCreator {

	public HistoricalPerformanceCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<HistoricalPerformanceInForm>(new HistoricalPerformanceInForm())
				.buidDerivativeVariables(appId).getForm();
		
		Map<String, Object> in = CollectionUtils.mapOf(HistoricalPerformanceInForm.Key,
				(Object) form);
		return in;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
