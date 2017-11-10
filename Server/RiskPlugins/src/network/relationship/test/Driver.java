package network.relationship.test;

import java.io.IOException;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.httpclient.HttpClientConfig;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver 
{
    public static void main( String[] args )
    {
    	Server server = Server.Create();
		ServerConfig serverConfig = new ServerConfig();
		
//		StartupConfig.initialize();
//		Logger.initialize();
		
		validateApps();
    }
    
    private static void validateApps(){
    	
    	CsvFileReader reader = null;
    	try {
			reader = new CsvFileReader("C:\\Users\\dell\\Desktop\\relationX.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Logger.get().info("id,当时fpd1逾期率,当时fpd30逾期率,当时团人数,当时批核人数,当时拒绝人数");
    	for(String appId :  reader.getAllAppIDs()){
    		int totalCount = AppDerivativeVariableManager.getAsInt(appId, AppDerivativeVariableConsts.GroupInfoAppTotalCount, 0);
    		int approveCount = AppDerivativeVariableManager.getAsInt(appId, AppDerivativeVariableConsts.GroupInfoAppApprovedCount, 0);
    		int rejectCount = AppDerivativeVariableManager.getAsInt(appId, AppDerivativeVariableConsts.GroupInfoAppRejectedCount, 1);
    		
//    		String fpd1 = AppDerivativeVariableManager.getAsString(appId, AppDerivativeVariableConsts.GroupInfoFPD1Rate);
//    		String fpd30 = AppDerivativeVariableManager.getAsString(appId, AppDerivativeVariableConsts.GroupInfoFPD30Rate);
    		
    		if(totalCount != new Integer(reader.getValue(appId, "当时团人数"))
    		|| approveCount != new Integer(reader.getValue(appId, "当时批核人数"))
    		|| rejectCount != new Integer(reader.getValue(appId, "当时拒绝人数"))){
    			Logger.get().info(appId);
    		}
    	}
    }
}
