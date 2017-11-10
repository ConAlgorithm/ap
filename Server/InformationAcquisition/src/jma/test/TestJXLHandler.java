package jma.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import jma.handlers.CheckContactInJXLCountHandler;
import jma.thirdpartyservices.jxl.JXLThreadWatchdog;
import thirdparty.config.JXLConfiguration;
/*
 * 测试聚信立报告为false，数据库中有联系人个数的bug
 * 2016/08/30
 */
public class TestJXLHandler {
	public static void main(String[] args){
		StartupConfig.initialize();
	//	Logger.initialize();
	//  HttpClientConfig.initialize();
	// JXLConfiguration.readConfiguration();
	// JXLThreadWatchdog.initialize();
	// PersistenceConfig.initialize();
		CheckContactInJXLCountHandler c = new CheckContactInJXLCountHandler();
		c.execute("5B66995F-F305-E611-83C4-AC853D9F54BA");
	}
}
