package engine.rule.model.creator.app;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.rule.model.inout.app.StoreInOutForm;
import engine.rule.model.out.DynamicQuestionnaireOutForm;

public class OutModelCreator extends AbstractApplicationModelCreator {

	private int reuploadCount = 0;
	private String jobName = StringUtils.EMPTY_STRING;
	
	public OutModelCreator(String appId) {
		super(appId);
	}
	
	public OutModelCreator(String appId, int reuploadCount) {
		super(appId);
		this.reuploadCount = reuploadCount;
	}
	
	public OutModelCreator(String appId, String jobName) {
	    super(appId);
	    this.jobName = jobName;
    }
	
	public OutModelCreator(String appId, int reuploadCount, String jobName) {
	    super(appId);
        this.jobName = jobName;
        this.reuploadCount = reuploadCount;
    }

	@Override
	public Map<String, Object> createModelForm() {
		Map<String, Object> outParams = CollectionUtils
				.<String, Object> newMapBuilder().build();
		DecisionInOutForm decisionForm = new ModelBuilder<DecisionInOutForm>(
				new DecisionInOutForm()).buidDerivativeVariables(appId)
				.getForm();
		decisionForm.setReuploadCount(this.reuploadCount);
				
		if(!StringUtils.isNullOrWhiteSpaces(jobName)) {
		    AppDerivativeVariables variable = AppDerivativeVariableManager.getVariables(
		        appId, AppDerivativeVariableNames.DISTRIBUTE_STRATEGY_TAG);
		    if(variable != null) {
		        String distributeInfo = variable.getAsString(AppDerivativeVariableNames.DISTRIBUTE_STRATEGY_TAG);
		        if(!StringUtils.isNullOrWhiteSpaces(distributeInfo)) {
    	            @SuppressWarnings("unchecked")
    	            Map<String, String> mapResult = new Gson().fromJson(distributeInfo, Map.class);
    	            
    	            if(mapResult != null && !StringUtils.isNullOrWhiteSpaces(mapResult.get(jobName))) {
    	                Logger.get().info("Jobname : " + jobName + " distribute tag is " + mapResult.get(jobName));
    	                decisionForm.setDistributeTag(mapResult.get(jobName));
    	            }
		        }
		    }
	        
		}
		
		outParams.put("inout_Decision", decisionForm);
		outParams.put("inout_Store", new StoreInOutForm());
		outParams.put("out_Question", new DynamicQuestionnaireOutForm());
		return outParams;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
