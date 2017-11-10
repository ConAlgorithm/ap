package jma.handlers;



import com.bfd.facade.MerchantBean;
import com.bfd.facade.MerchantServer;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.services.coordination.CoordinationService;
import catfish.services.coordination.DataCoordinator;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.handlers.preprocess.PreprocessorFactory;
import jma.handlers.preprocess.model.CheckUserCreditOnBRPreModel;
import net.sf.json.JSONObject;
import thirdparty.config.BRConfiguration;


public class CheckUserCreditOnBRHandler extends NonBlockingJobHandler{
	private final static String DATA="BrTokenId";
	private final static String ID="id";
	private CheckUserCreditOnBRPreModel preModel;
	private MerchantServer ms;
	private DataCoordinator dataCoordinator;
	private CoordinationService cs;
	private static final String BR_CODE_RIGHT_HIT="00";//正确返回结果且有内容命中
	private static final String BR_CODE_RIGHT_NOTHIT="100002";//正确返回结果但是没有内容命中
	private static final String BR_CODE_TOKENID_TIMEOUT="100007"; //tokenid过期
	
	@Override
	public void preprocess() {
	    this.preModel = PreprocessorFactory
	        .getProcessor(channel, CheckUserCreditOnBRPreModel.class)
	        .getPreModel(appId);
	  }
	
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(DynamicConfig.read("BRSwitch",JobHandlerSwitch.Off.getValue())
				.equals(JobHandlerSwitch.Off.getValue())) {
					return;
			    }
		try{
			initialize();
			String tokenId=dataCoordinator.get(ID);
			String result=getResult(tokenId);
			if(result!=null){
				checkCodeAndExecute(tokenId,result, 1);
			}
			else{
				Logger.get().warn(String.format("CheckUserCreditOnBR faild ! appId:%s", appId));
				throw new RetryRequiredException();
			}
		}finally{
			cs.close();
		}
	}
	
	private void initialize() throws RetryRequiredException{
		ms=new MerchantServer();
	    //初始化zookeeper
	    try {
			cs = new CoordinationService(BRConfiguration.getZookeeperConnection(), 
					BRConfiguration.getZookeeperTimeout());
			dataCoordinator = cs.registerData(DATA);
			
			try{//如果不存在id这个节点 则创建并赋值为新获得的一个tokenid
				dataCoordinator.lock(ID.toUpperCase());
				if(!dataCoordinator.existChildNode(ID)){
					boolean  flag= dataCoordinator.createChildNode(ID, true);
					if(flag){
		                Logger.get().info("successed to create child node,nodeId: " + ID);
		                String tempid=loginAndGetTokenId();
						if(tempid!=null){
							dataCoordinator.set(ID, tempid);
						}else{
							throw new RetryRequiredException();
						}
		            } else {
		                Logger.get().warn("failed to  create child node ,nodeId:" + ID);
		                throw new RetryRequiredException();
		            }
				}
				
			}finally{
				dataCoordinator.releaseLock(ID.toUpperCase());
			}
			
	    } catch (Exception e) {
			Logger.get().warn("failed to connect zookeeper or failed to check Brtokenid existed !",e);
		} 
	   
		
	}

	private void checkCodeAndExecute(String tokenId,String result,int executeTimes) throws RetryRequiredException{
		JSONObject json=JSONObject.fromObject(result);
		switch (json.getString("code")) {
		case BR_CODE_RIGHT_NOTHIT:
			RawDataStorageManager.addRawDatas(
			        new RawData(appId, RawDataVariableNames.BR_UNITED_RAW_DATA, result));
			
			break;
		case BR_CODE_RIGHT_HIT:
			RawDataStorageManager.addRawDatas(
			        new RawData(appId, RawDataVariableNames.BR_UNITED_RAW_DATA, result));
			
			saveResult(json);
			break;
		case BR_CODE_TOKENID_TIMEOUT:
			if(executeTimes==1){
				try{
					dataCoordinator.lock(ID.toUpperCase());
					String tempId=dataCoordinator.get(ID);
					if(tokenId.equals(tempId)){
						String tempNewId= loginAndGetTokenId();
						if(tempNewId!=null){
							dataCoordinator.set(ID, tempNewId);
							tokenId=tempNewId;
						} else {
							Logger.get().warn("failed to get new tokenId");
							throw new RetryRequiredException();
						}
					} else {
						tokenId=tempId;
					}
				} finally {
					dataCoordinator.releaseLock(ID.toUpperCase());
				}
				
				String secondResult=getResult(tokenId);
				if(secondResult!=null){
					checkCodeAndExecute(tokenId,secondResult, ++executeTimes);
				}else {
					Logger.get().warn("failed to get response");
					throw new RetryRequiredException();
				}
			}else{
				throw new RetryRequiredException();
			}				
			
			break;
		default:
			RawDataStorageManager.addRawDatas(
			        new RawData(appId, RawDataVariableNames.BR_UNITED_RAW_DATA, result));
			Logger.get().warn("wrong code:"+json.getString("code"));
			break;
		}	
	}

	
	private void saveResult(JSONObject json){
		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
		JSONObject jsonTemp=null;
		if(json.getJSONObject("Flag").getString("specialList_c").equals("1")){
			if(json.getJSONObject("SpecialList_c").has("id")){
				jsonTemp=json.getJSONObject("SpecialList_c").getJSONObject("id");
				saveSpecialListById(builder, jsonTemp);
			}
			if(json.getJSONObject("SpecialList_c").has("cell")){
				jsonTemp=json.getJSONObject("SpecialList_c").getJSONObject("cell");
				saveSpecialListByCell(builder, jsonTemp);
			}
			
		}
		if(json.getJSONObject("Flag").getString("applyLoan").equals("1")){
			jsonTemp=json.getJSONObject("ApplyLoan");
			if(jsonTemp.has("month3")){
				saveApplyLoanInMonth3(builder, jsonTemp);
			}
			
			if(jsonTemp.has("month6")){
				saveApplyLoanInMonth6(builder, jsonTemp);
			}
			
			if(jsonTemp.has("month12")){
				saveApplyLoanInMonth12(builder, jsonTemp);
			}
			
		}
		
		if (!builder.build().isEmpty()) 
		      AppDerivativeVariableManager.addVariables(builder.build());
		
	}
	
		private void saveSpecialListById(AppDerivativeVariablesBuilder builder,JSONObject jsonTemp){
		if(jsonTemp.has("bank_bad")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_BAD, 
					jsonTemp.getString("bank_bad"));
		}
		
		if(jsonTemp.has("bank_overdue")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_OVERDUE, 
					jsonTemp.getString("bank_overdue"));
		}
			
		if(jsonTemp.has("bank_fraud")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_FRAUD, 
					jsonTemp.getString("bank_fraud"));
		}
		
		if(jsonTemp.has("bank_lost")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_LOST, 
					jsonTemp.getString("bank_lost"));
		}
		
		if(jsonTemp.has("bank_refuse")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_REFUSE, 
					jsonTemp.getString("bank_refuse"));
		}
		
		if(jsonTemp.has("credit_bad")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_CREDIT_BAD, 
					jsonTemp.getString("credit_bad"));
		}
		
		if(jsonTemp.has("p2p_bad")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_BAD, 
					jsonTemp.getString("p2p_bad"));
		}
		
		if(jsonTemp.has("p2p_overdue")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_OVERDUE, 
					jsonTemp.getString("p2p_overdue"));
		}
		
		if(jsonTemp.has("p2p_fraud")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_FRAUD, 
					jsonTemp.getString("p2p_fraud"));
		}
		
		if(jsonTemp.has("p2p_lost")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_LOST, 
					jsonTemp.getString("p2p_lost"));
		}
		
		if(jsonTemp.has("p2p_refuse")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_REFUSE, 
					jsonTemp.getString("p2p_refuse"));
		}
		
		if(jsonTemp.has("phone_overdue")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_PHONE_OVERDUE, 
					jsonTemp.getString("phone_overdue"));
		}
		
		if(jsonTemp.has("ins_fraud")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_ID_INS_FRAUD, 
					jsonTemp.getString("ins_fraud"));
		}
	}
	
	private void saveSpecialListByCell(AppDerivativeVariablesBuilder builder,JSONObject jsonTemp){
		if(jsonTemp.has("bank_bad")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_BAD, 
					jsonTemp.getString("bank_bad"));
		}
		
		if(jsonTemp.has("bank_overdue")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_OVERDUE, 
					jsonTemp.getString("bank_overdue"));
		}
		
		if(jsonTemp.has("bank_fraud")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_FRAUD, 
					jsonTemp.getString("bank_fraud"));
		}
		
		if(jsonTemp.has("bank_lost")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_LOST, 
					jsonTemp.getString("bank_lost"));
		}
		
		if(jsonTemp.has("bank_refuse")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_REFUSE, 
					jsonTemp.getString("bank_refuse"));
		}
		
		if(jsonTemp.has("p2p_bad")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_BAD, 
					jsonTemp.getString("p2p_bad"));
		}
		
		if(jsonTemp.has("p2p_overdue")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_OVERDUE, 
					jsonTemp.getString("p2p_overdue"));
		}
		
		if(jsonTemp.has("p2p_fraud")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_FRAUD, 
					jsonTemp.getString("p2p_fraud"));
		}
		
		if(jsonTemp.has("p2p_lost")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_LOST, 
					jsonTemp.getString("p2p_lost"));
		}
		
		if(jsonTemp.has("p2p_refuse")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_REFUSE, 
					jsonTemp.getString("p2p_refuse"));
		}
		
		if(jsonTemp.has("phone_overdue")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_PHONE_OVERDUE, 
					jsonTemp.getString("phone_overdue"));
		}
		
		if(jsonTemp.has("ins_fraud")){
			builder.add(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_INS_FRAUD, 
					jsonTemp.getString("ins_fraud"));
		}
	}
	
	private void saveApplyLoanInMonth3(AppDerivativeVariablesBuilder builder,JSONObject jsonTemp){
		if(jsonTemp.getJSONObject("month3").has("id")){
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_ID_BANK_SELFNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("id").
					getJSONObject("bank").getString("selfnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_ID_BANK_ALLNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("id").
					getJSONObject("bank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_ID_BANK_ORGNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("id").
					getJSONObject("bank").getString("orgnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_ID_NOTBANK_SELFNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("id").
					getJSONObject("notbank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_ID_NOTBANK_ALLNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("id").
					getJSONObject("notbank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_ID_NOTBANK_ORGNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("id").
					getJSONObject("notbank").getString("orgnumber"));					
		}
		if(jsonTemp.getJSONObject("month3").has("cell")){
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_CELL_BANK_SELFNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("cell").
					getJSONObject("bank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_CELL_BANK_ALLNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("cell").
					getJSONObject("bank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_CELL_BANK_ORGNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("cell").
					getJSONObject("bank").getString("orgnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_CELL_NOTBANK_SELFNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("cell").
					getJSONObject("notbank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_CELL_NOTBANK_ALLNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("cell").
					getJSONObject("notbank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH3_CELL_NOTBANK_ORGNUM, 
					jsonTemp.getJSONObject("month3").getJSONObject("cell").
					getJSONObject("notbank").getString("orgnumber"));	
		}
	}
	
	private void saveApplyLoanInMonth6(AppDerivativeVariablesBuilder builder,JSONObject jsonTemp){
		if(jsonTemp.getJSONObject("month6").has("id")){
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_ID_BANK_SELFNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("id").
					getJSONObject("bank").getString("selfnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_ID_BANK_ALLNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("id").
					getJSONObject("bank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_ID_BANK_ORGNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("id").
					getJSONObject("bank").getString("orgnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_ID_NOTBANK_SELFNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("id").
					getJSONObject("notbank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_ID_NOTBANK_ALLNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("id").
					getJSONObject("notbank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_ID_NOTBANK_ORGNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("id").
					getJSONObject("notbank").getString("orgnumber"));					
		}
		if(jsonTemp.getJSONObject("month6").has("cell")){
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_CELL_BANK_SELFNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("cell").
					getJSONObject("bank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_CELL_BANK_ALLNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("cell").
					getJSONObject("bank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_CELL_BANK_ORGNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("cell").
					getJSONObject("bank").getString("orgnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_CELL_NOTBANK_SELFNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("cell").
					getJSONObject("notbank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_CELL_NOTBANK_ALLNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("cell").
					getJSONObject("notbank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH6_CELL_NOTBANK_ORGNUM, 
					jsonTemp.getJSONObject("month6").getJSONObject("cell").
					getJSONObject("notbank").getString("orgnumber"));	
		}
	}
	
	private void saveApplyLoanInMonth12(AppDerivativeVariablesBuilder builder,JSONObject jsonTemp){
		if(jsonTemp.getJSONObject("month12").has("id")){
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_ID_BANK_SELFNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("id").
					getJSONObject("bank").getString("selfnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_ID_BANK_ALLNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("id").
					getJSONObject("bank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_ID_BANK_ORGNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("id").
					getJSONObject("bank").getString("orgnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_ID_NOTBANK_SELFNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("id").
					getJSONObject("notbank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_ID_NOTBANK_ALLNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("id").
					getJSONObject("notbank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_ID_NOTBANK_ORGNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("id").
					getJSONObject("notbank").getString("orgnumber"));					
		}
		if(jsonTemp.getJSONObject("month12").has("cell")){
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_CELL_BANK_SELFNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("cell").
					getJSONObject("bank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_CELL_BANK_ALLNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("cell").
					getJSONObject("bank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_CELL_BANK_ORGNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("cell").
					getJSONObject("bank").getString("orgnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_CELL_NOTBANK_SELFNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("cell").
					getJSONObject("notbank").getString("selfnumber"));
		
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_CELL_NOTBANK_ALLNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("cell").
					getJSONObject("notbank").getString("allnumber"));
			
			builder.add(AppDerivativeVariableNames.BR_APPLYLOAN_MONTH12_CELL_NOTBANK_ORGNUM, 
					jsonTemp.getJSONObject("month12").getJSONObject("cell").
					getJSONObject("notbank").getString("orgnumber"));	
		}
	}
	
	private String loginAndGetTokenId(){
		JSONObject json=null;
		try {
			String login_result = ms.login(BRConfiguration.getLoginId(),
					BRConfiguration.getLoginPassword());
			json=JSONObject.fromObject(login_result);
	        String tokenid=json.getString("tokenid");
	        return tokenid;
		} catch (Exception e) {
			if(json!=null){
				Logger.get().warn(String.format("loginAndGetTokenId faild ! code:%s",json.getString("code")),e);
			}
			Logger.get().warn("loginAndGetTokenId faild !",e);
		}
		return null;
		
	}
	
	private String getResult(String tokenid){
		JSONObject json=null;
		try{
			MerchantBean merchantBean=new MerchantBean();
			merchantBean.setTokenid(tokenid);
			merchantBean.setName(preModel.userName);
			merchantBean.setId(preModel.userIdNumber);
			merchantBean.setCell(preModel.userMobile);
			
			String result=ms.getUserPortrait(merchantBean);
			json=JSONObject.fromObject(result);
			Logger.get().info(result);
			return result;
		}catch(Exception e){
			if(json!=null){
				Logger.get().warn(String.format("getResult faild ! code:%s",json.getString("code")),e);
			}
			Logger.get().warn("getResult faild !", e);
		}
		return null;
	}

}
