package jma.handlers;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import jma.JobHandler;
import jma.JobHandlerSwitch;
import jma.RetryRequiredException;
import jma.thirdpartyservices.jxl.JXLThreadWatchdog;

public class CrawlingJxlInfoForCLHandler extends JobHandler {

	@Override
	public void execute(String appId) throws RetryRequiredException {
		if (DynamicConfig.read("JXLCrawlingSwitchForCL", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue()))
			return;
		JXLThreadWatchdog.startJXLInfoCrawlingForCL(requestMessager);
	}
	@Override
	 public void writeJobResult(boolean isSuccess) {
		Logger.get().info("Get JXL info for CL , isSuccess=" + isSuccess);
	 }

}
