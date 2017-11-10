package jma.handlers;

import java.math.BigDecimal;
import java.util.List;

import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.thirdpartyservices.YLZHBankCardChecker;
import thirdparty.ylzh.request.CorrespondingRelationVerifyRequest;
import thirdparty.ylzh.request.TradeReportRequest;
import thirdparty.ylzh.request.cardownerverify.AuthType;
import thirdparty.ylzh.request.cardownerverify.CardOwnerIdentityVerifyRequest;
import thirdparty.ylzh.response.CorrespondingRelationVerifyResponse;
import thirdparty.ylzh.response.ResponseCode;
import thirdparty.ylzh.response.TradeReportResponse;
import thirdparty.ylzh.response.cardownerverify.CardOwnerIdentityVerifyResponse;
import thirdparty.ylzh.response.tradereport.Data;
import thirdparty.ylzh.response.tradereport.IndexConsumeCity;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.business.common.ylzh.ConsumeRegionNative;
import catfish.base.business.common.ylzh.CorrespondingRelationVerifyType;
import catfish.base.business.common.ylzh.ResponseState;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.dao.PaymentInfoDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.PaymentObject;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.httpclient.object.BaseObject;

public class CheckBankCardOn3rdPartyHandler extends NonBlockingJobHandler{
	
	private PaymentObject payment = null;
		
	private CorrespondingRelationVerifyRequest createCorrespondingRelationVerifyRequest(String appId)
	{
		CorrespondingRelationVerifyRequest request = new CorrespondingRelationVerifyRequest();
		ContactObject contact = new ContactDao(appId).getSingle();
		if(contact == null)
		{
			Logger.get().error(String.format("Cannot get the Mobile info of appId:%s in 银联智惠",appId));
		}	
		request.setCardNumber(payment.BankAccount);
		request.setMobile(contact.Content);
		request.setType(CorrespondingRelationVerifyType.BankCard_Mobile.getValue());
		
		return request;
	}
	
	private TradeReportRequest createTradeReportRequest(String appId)
	{
		TradeReportRequest request = new TradeReportRequest();
		request.setCardNumber(payment.BankAccount);
		return request;
	}
	
	private CardOwnerIdentityVerifyRequest createCardOwnerIdentityVerifyRequest(String appId)
	{
	    CardOwnerIdentityVerifyRequest request = new CardOwnerIdentityVerifyRequest();
	    request.setAuthType(AuthType.ShortMsg.getValue());
	    //request.setCardNumber(payment.BankAccount);
	    request.setCardNumber("6200000000000100");
	    request.setCardOwnerName(payment.BankAccountName);
	    request.setUserId(appId);
	    return request;
	}
	
	private boolean addCorrespondingRelationVerifyResult(String appId) throws Exception
	{
		CorrespondingRelationVerifyRequest verifyRequest = this.createCorrespondingRelationVerifyRequest(appId);
		//如果无法获取用户信息，则跳过该环节
		if(verifyRequest == null)
		    return false;
		CorrespondingRelationVerifyResponse verifyResponse = YLZHBankCardChecker.correspondingRelationVerify(verifyRequest);
		if(verifyResponse == null)
			return false;
		//保存原始数据
		RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.YLZH_CORRESPONDING_RELATION_VERIFY_RAW_DATA, verifyResponse.toJson()));
				
		AppDerivativeVariableManager.addVariables(
		        new AppDerivativeVariable(appId, AppDerivativeVariableNames.YLZH_BANKCARD_MOBILE_MATCH,verifyResponse.getVerifyState()));
		return verifyResponse.getVerifyState().equals(ResponseState.VerifySuccess.getValue());
	}
	
	private void addTradeRegionResult(String appId) throws Exception
	{
		TradeReportRequest tradeRequest = this.createTradeReportRequest(appId);
		if(tradeRequest == null)
			return;
		TradeReportResponse tradeResponse = YLZHBankCardChecker.getTradeReport(tradeRequest);
		if(tradeResponse == null)
			return;
		
		//保存原始数据
		RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.YLZH_TRADEREPORT_RAW_DATA, tradeResponse.toJson()));
				
		if(tradeResponse.getResCode().equals(ResponseCode.SubmitSuccess.getValue()))
		{
		    parseDerivativeVariables(appId, tradeResponse.getData());
		}	     
	}
	
	//银行卡持有者身份验证
	private void addCardOwnerIdentityVerifyResult(String appId) throws Exception
	{
		CardOwnerIdentityVerifyRequest request = this.createCardOwnerIdentityVerifyRequest(appId);
		if(request == null)
			return;
		CardOwnerIdentityVerifyResponse response = YLZHBankCardChecker.cardOwnerIdentityVerify(request);
		if(response == null)
			return;
	}
	
	@Override
	public void execute(String appId) throws RetryRequiredException {
		try{
			if(DynamicConfig.read("YLZHCheckSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue()))
				return;
			
			this.payment = new PaymentInfoDao(appId).getSingle();
			if(payment == null)
			{
				Logger.get().error(String.format("Cannot get the BankCard info of appId:%s in 银联智惠, this check will be ignored",appId));
				return;
			}
			if(addCorrespondingRelationVerifyResult(appId))
			{
				this.addTradeRegionResult(appId);
			}
			//this.addCardOwnerIdentityVerifyResult(appId);
		}catch(Exception e)
		{
			Logger.get().error(String.format("Get 银联智惠 info of AppId: %s error, this check will be ignored", appId), e);
		}	
	}
	
	private void parseDerivativeVariables(String appId, Data data) {
	    
	    List<IndexConsumeCity> indexConsumeCities = data.getIndexConsumeCityList();
	    
	    int cityCount = indexConsumeCities.size();
	    double totalConsumptionAmount = 0;
	    int totalConsumptionCount = 0;
	    
        IndexConsumeCity maxCountCity = null;
        IndexConsumeCity maxAmountCity = null;
        double maxAmount = 0;
        int maxCount = 0;
        for(IndexConsumeCity city : indexConsumeCities) {
            totalConsumptionAmount += city.getConsumeAmount();
            totalConsumptionCount += city.getConsumeCount();
            if (city.getConsumeAmount() > maxAmount) {
                maxAmountCity = city;
                maxAmount = city.getConsumeAmount();
            }
            if (city.getConsumeCount() > maxCount) {
                maxCountCity = city;
                maxCount = city.getConsumeCount();
            }
        }
        
        String localCity = MerchantStoreDao.getLocatedCityByAreaCode(
                CNAreaDao.getCNAreaObjByAppId(appId).Code);
        
        ConsumeRegionNative isMaxCountCityNative = ConsumeRegionNative.EmptyData;
        if (maxCountCity != null) {
            isMaxCountCityNative = 
                    isAreaIdentical(localCity, maxCountCity.getName()) 
                    ? ConsumeRegionNative.IsNative 
                    : ConsumeRegionNative.NotNative;
        }
        
        ConsumeRegionNative isMaxAmountCityNative = ConsumeRegionNative.EmptyData;
        if (maxAmountCity != null) {
            isMaxAmountCityNative = 
                    isAreaIdentical(localCity, maxAmountCity.getName()) 
                    ? ConsumeRegionNative.IsNative 
                    : ConsumeRegionNative.NotNative;
        }
	    
        AppDerivativeVariableManager.addVariables(
                new AppDerivativeVariable(
                        appId, 
                        AppDerivativeVariableNames.YLZH_CONSUMEREGION_LIST, 
                        BaseObject.toJson(indexConsumeCities)));
        AppDerivativeVariableManager.addVariables(
                new AppDerivativeVariable(appId, 
                        AppDerivativeVariableNames.YLZH_CONSUMPTION_TOTAL_REGION_COUNT, 
                        cityCount));
        AppDerivativeVariableManager.addVariables(
                new AppDerivativeVariable(appId, 
                        AppDerivativeVariableNames.YLZH_CONSUMPTION_TOTAL_AMOUNT, 
                        new BigDecimal(totalConsumptionAmount)));
        AppDerivativeVariableManager.addVariables(
                new AppDerivativeVariable(appId, 
                        AppDerivativeVariableNames.YLZH_CONSUMPTION_TOTAL_COUNT, 
                        totalConsumptionCount));
        AppDerivativeVariableManager.addVariables(
                new AppDerivativeVariable(appId, 
                        AppDerivativeVariableNames.YLZH_IS_MAX_CONSUMPTION_AMOUNT_NATIVE, 
                        isMaxAmountCityNative.getValue()));
        AppDerivativeVariableManager.addVariables(
                new AppDerivativeVariable(appId, 
                        AppDerivativeVariableNames.YLZH_IS_MAX_CONSUMPTION_COUNT_NATIVE, 
                        isMaxCountCityNative.getValue()));
	    
	}
	
	private boolean isAreaIdentical(String areaA, String areaB) {
	    
	    if (areaA != null && areaB != null 
	            && (areaA.contains(areaB) || areaB.contains(areaA))) {
	        return true;
	    }
	    return false;
	}
}
