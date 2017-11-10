package engine.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import catfish.base.Logger;
import engine.main.Configuration;
import engine.rule.model.BankModel;

public class FundService {
	private static final String FundService_URL = Configuration.getFundServiceUrl();	
	
	public static boolean isEBuckleSupported(String appId) {
		//Register json support
        Logger.get().info("FundService isEBuckleSupported begin");
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
        String IfBuckleSupportInfo_URL = FundService_URL + appId + "/EBuckleSupportInfo";
        WebTarget target = client.target(IfBuckleSupportInfo_URL);  
        Response response = null;
        BankModel bankModel = null;
        try{
            response = target.request().get(); 
            Logger.get().info("FundService response--" + response);
        	bankModel = response.readEntity(BankModel.class);  
            response.close();  
        }catch(Exception e){
        	Logger.get().error("FundService Can't get BankModel from service--" + e);
        	response.close();  
        } 
        if(bankModel == null)
        	return false;
        else
            return bankModel.getIsEBuckleSupported();    
	}	

}
