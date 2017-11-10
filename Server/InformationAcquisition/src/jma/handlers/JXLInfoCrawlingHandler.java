package jma.handlers;

import catfish.base.DynamicConfig;
import jma.JobHandler;
import jma.JobHandlerSwitch;
import jma.RetryRequiredException;
import jma.thirdpartyservices.jxl.JXLThreadWatchdog;

public class JXLInfoCrawlingHandler extends JobHandler{

	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(DynamicConfig.read("JXLCrawlingSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue()))
			return;
		JXLThreadWatchdog.startJXLInfoCrawling(appId);
	}

}
