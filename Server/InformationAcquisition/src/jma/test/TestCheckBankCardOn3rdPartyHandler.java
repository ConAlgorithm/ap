package jma.test;

import java.util.List;

import thirdparty.config.YLZHConfiguration;
import jma.Configuration;
import jma.RetryRequiredException;
import jma.handlers.CheckBankCardOn3rdPartyHandler;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.mongo.CatfishMongoClient;
import catfish.base.persistence.queue.PersistenceConfig;

public class TestCheckBankCardOn3rdPartyHandler {

	public static void main(String[] args)
	{
		StartupConfig.initialize();
		Logger.initialize();
		Configuration.readConfiguration();
		YLZHConfiguration.readConfiguration();
		HttpClientConfig.initialize();
		PersistenceConfig.initialize();
	    
	    //List<String> ids = InstallmentApplicationDao.getAppIdsForTest();
    	CheckBankCardOn3rdPartyHandler handler = new CheckBankCardOn3rdPartyHandler();
		try {
			handler.execute("F795E4CA-6102-E411-9BA0-AC853D9F54BA");
			//handler.execute(args[0]);
		} catch (RetryRequiredException e) {
			e.printStackTrace();
		}
	}
}
