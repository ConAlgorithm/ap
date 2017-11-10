package engine.rule.test.creator.cashloan;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.creator.AbstractModelCreator;
import engine.rule.model.inout.cashloan.DecisionInOutForm;

public class OutModelCreator extends AbstractModelCreator {

	private int reuploadCount = 0;
	private Map<String, String> columnNameValueMappings;

	public OutModelCreator(int reuploadCount, Map<String, String> columnNameValueMappings) {
		this.reuploadCount = reuploadCount;
		this.columnNameValueMappings = columnNameValueMappings;
	}

	@Override
	public Map<String, Object> createModelForm() {
		Map<String, Object> outParams = CollectionUtils.<String, Object>newMapBuilder().build();
		DecisionInOutForm decisionForm = new DecisionInOutForm();

		decisionForm.setReuploadCount(this.reuploadCount);

		String key = "investigationScore";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			decisionForm.setInvestigationScore(new Double(value));
		}

		outParams.put("inout_Decision", decisionForm);
		return outParams;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
