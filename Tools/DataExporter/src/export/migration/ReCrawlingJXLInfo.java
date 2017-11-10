package export.migration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jma.thirdpartyservices.jxl.JXLThreadWatchdog;
import thirdparty.config.JXLConfiguration;
import catfish.base.ThreadUtils;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;


public class ReCrawlingJXLInfo implements IMigratable{

	static{		
        JXLConfiguration.readConfiguration();		
		JXLThreadWatchdog.initialize();
	}
	
	@Override
	public void migrate() {
		BufferedReader reader=null;
        List<String> appIds=new LinkedList<String>();
        try{
           reader=new BufferedReader(new FileReader("D:\\UserId_Mig.csv"));
           String appid;
           while(((appid=reader.readLine())!=null)){
        	   System.out.println(appid);
               appIds.add(appid);
               JXLThreadWatchdog.startJXLInfoCrawling(appid);
               ThreadUtils.sleepInSeconds(3);
           }
           reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
	}
}
