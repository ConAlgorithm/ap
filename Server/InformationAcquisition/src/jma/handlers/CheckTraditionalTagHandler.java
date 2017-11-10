package jma.handlers;

import catfish.base.Logger;
import catfish.base.business.common.JobStatus;
import catfish.base.business.util.AppDerivativeVariableManager;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;

public class CheckTraditionalTagHandler extends NonBlockingJobHandler {
	@Override
	public void execute(String appId) throws RetryRequiredException {
		Boolean observerTag = AppDerivativeVariableManager.getAsBoolean(appId, "X_TraditionObserveTag", false);
		if(observerTag) {
			
		}
		
	}
}
