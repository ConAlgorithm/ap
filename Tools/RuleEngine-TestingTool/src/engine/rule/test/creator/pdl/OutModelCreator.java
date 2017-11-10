package engine.rule.test.creator.pdl;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.creator.AbstractModelCreator;
import engine.rule.model.inout.pdl.DecisionInOutForm;
import engine.rule.model.inout.pdl.LoanMoneyDecisionOutForm;
import engine.rule.model.inout.pdl.StoreInOutForm;
import engine.rule.model.out.DynamicQuestionnaireOutForm;

public class OutModelCreator extends AbstractModelCreator {

	private int reuploadCount = 0;
	private Map<String, String> columnNameValueMappings;
	
	public OutModelCreator(int reuploadCount, Map<String, String> columnNameValueMappings) {
		this.reuploadCount = reuploadCount;
		this.columnNameValueMappings = columnNameValueMappings;
	}
	
	@Override
	public Map<String, Object> createModelForm() {
		Map<String, Object> outParams = CollectionUtils	.<String, Object> newMapBuilder().build();
		DecisionInOutForm decisionForm = new DecisionInOutForm();
		
		decisionForm.setReuploadCount(this.reuploadCount);
		
		String key = "someOneRecommend";
		if(this.columnNameValueMappings.containsKey(key)){
			if(new Boolean(this.columnNameValueMappings.get(key)))
				decisionForm.setAffiliateRecommendRole(1);
			else
				decisionForm.setAffiliateRecommendRole(-1);
		}

		key = "personalInfoScore";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			decisionForm.setPersonalInfoScore(new Double(value));
		}

		key = "investigationScore";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			decisionForm.setInvestigationScore(new Double(value));
		}

		key = "fraudCheckScore";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			decisionForm.setFraudCheckScore(new Double(value));
		}

		key = "segmentationCode";
		if(this.columnNameValueMappings.containsKey(key)){
			String value = this.columnNameValueMappings.get(key);
			decisionForm.setSegmentationCode(value);
		}
		else
		{
			decisionForm.setSegmentationCode("F1");
		}
		
		outParams.put("inout_Decision", decisionForm);
		outParams.put("inout_Store", new StoreInOutForm());
		outParams.put("out_Question", new DynamicQuestionnaireOutForm());
		outParams.put("out_LoanMoney", new LoanMoneyDecisionOutForm());
		return outParams;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
