package engine.rule.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import catfish.base.Logger;
import engine.rule.model.BankModel;

public class ApiTest {
	private static final String FundService_URL = "http://120.26.93.91:9101/bank/";		
	
	public static void main(String[] args) {
//		ManualjobsHelper.getAllQueues();
		
		
		//getclient1();
		String appId = "a8abc063-5db8-e511-909c-d89d672b352b";
		if(IfBuckleSupportInfo(appId)){
			System.out.println("sdfhfsdjfhsdjk");
		}else{
			System.out.println("测试");
		}
		
//		getclient1();
//		queryJob();

	    
	}

	

	//http://120.26.93.91:9101/bank/a8abc063-5db8-e511-909c-d89d672b352b/EBuckleSupportInfo
	//通过appId获取是否支持电子代扣协议
	private static boolean IfBuckleSupportInfo(String appId){
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);// 注册json 支持  
        appId = "a8abc063-5db8-e5-d89d672b352b";
        //String IfBuckleSupportInfo_URL = "http://120.26.93.91:9101/bank/"+ appId + "/EBuckleSupportInfo";
        String IfBuckleSupportInfo_URL = FundService_URL + appId + "/EBuckleSupportInfo";
        
        WebTarget target = client.target(IfBuckleSupportInfo_URL);  
        Response response = null;
        BankModel bankModel = null;
        try{
            response = target.request().get(); 
        System.out.println("response" + response);
        	bankModel = response.readEntity(BankModel.class);  
            System.out.println(bankModel.getIsEBuckleSupported()); 
            response.close();  
        }catch(Exception e){
        	//BankInforNotFoundException error =  response.readEntity(BankInforNotFoundException.class);
        	System.out.println("e" + e);
        	response.close();  
        } 
        if(bankModel == null)
        	return false;
        else
            return bankModel.getIsEBuckleSupported();
        		
	}	
	
}
