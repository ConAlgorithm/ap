package jma.handlers;

import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;

public class TraditionObserveTagHandler extends NonBlockingJobHandler {
	@Override
	public void execute(String appId) throws RetryRequiredException {
		AppDerivativeVariableManager
        .addVariables(new AppDerivativeVariablesBuilder(appId)
            .isNotNullAdd("X_TraditionObserveTag", true).build());
		
	}
}


