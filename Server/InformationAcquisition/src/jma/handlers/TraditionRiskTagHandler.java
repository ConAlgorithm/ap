package jma.handlers;

import catfish.base.business.util.AppDerivativeVariableManager;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;

public class TraditionRiskTagHandler extends NonBlockingJobHandler {
	@Override
	public void execute(String appId) throws RetryRequiredException {
		AppDerivativeVariableManager
        .addVariables(new AppDerivativeVariablesBuilder(appId)
            .isNotNullAdd("X_TraditionRiskTag", true).build());
		
	}
}