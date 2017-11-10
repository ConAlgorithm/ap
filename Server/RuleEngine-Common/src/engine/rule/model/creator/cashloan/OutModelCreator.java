package engine.rule.model.creator.cashloan;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.inout.cashloan.DecisionInOutForm;

public class OutModelCreator extends AbstractApplicationModelCreator {

	private int reuploadCount = 0;

	public OutModelCreator(String appId) {
		super(appId);
	}

	public OutModelCreator(String appId, int reuploadCount) {
		super(appId);
		this.reuploadCount = reuploadCount;
	}

	@Override
	public Map<String, Object> createModelForm() {
		Map<String, Object> outParams = CollectionUtils
				.<String, Object> newMapBuilder().build();
		DecisionInOutForm decisionForm = new ModelBuilder<DecisionInOutForm>(
				new DecisionInOutForm()).buidDerivativeVariables(appId).getForm();
		decisionForm.setReuploadCount(this.reuploadCount);

		outParams.put("inout_Decision", decisionForm);
		return outParams;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
