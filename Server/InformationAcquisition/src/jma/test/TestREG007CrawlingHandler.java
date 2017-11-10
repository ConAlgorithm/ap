package jma.test;

import jma.thirdpartyservices.reg007.*;
import thirdparty.config.REG007Configuration;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;

public class TestREG007CrawlingHandler {
	public static void main(String[] args) {
		HttpClientConfig.initialize();
		StartupConfig.initialize();
		REG007Configuration.initialize();
		
		REG007ThreadService.initialize();
		Logger.initialize();
		PersistenceConfig.initialize();
		
//		REG007ThreadService.startCrawling("163A0002-7A23-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("B95E6F8C-9123-E411-98E3-AC853DA49BEC");
		REG007ThreadService.startCrawling("4055BF04-9C23-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("2D84E46C-A523-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("54A91189-1B24-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("4EF09379-2324-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("B590E6D0-3124-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("6DF708B4-4A24-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("2BBE824F-5124-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("6E0709BC-5224-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("FC0AD746-E100-E411-B348-E0DB5516D568");
//		REG007ThreadService.startCrawling("B6D09207-6DFE-E311-B348-E0DB5516D568");
//		REG007ThreadService.startCrawling("73B5FBB0-E1F9-E311-B348-E0DB5516D568");
//		REG007ThreadService.startCrawling("DF6E7661-32F1-E311-B348-E0DB5516D568");
//		REG007ThreadService.startCrawling("002A398C-10E0-E311-B348-E0DB5516D568");
//		REG007ThreadService.startCrawling("8910829E-36DF-E311-B348-E0DB5516D568");
//		REG007ThreadService.startCrawling("89DA98AD-22AD-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("BE0AA90B-06AD-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("F1651849-EEAC-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("6F88761C-EBAC-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("B9D79B80-53AC-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("051A33FC-4DAC-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("C40781E4-44AC-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("6515281B-39AC-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("BC055107-17AC-E411-98E3-AC853DA49BEC");
//		REG007ThreadService.startCrawling("302FAC6E-8EAB-E411-98E3-AC853DA49BEC");
		
		
	}
}