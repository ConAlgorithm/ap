package jma.handlers;

import jma.JobHandler;
import jma.RetryRequiredException;
import jma.thirdpartyservices.reg007.REG007ThreadService;



public class REG007InfoCrawlingHandler extends JobHandler {

	@Override
	public void execute(String appId) throws RetryRequiredException {
		REG007ThreadService.startCrawling(appId);
	}
}