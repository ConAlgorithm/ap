/**
 * 
 */
package jma.handlers;

import java.util.Date;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.instinct.InstinctCheckResultType;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import core.InstinctOnlineJudge;
import instinct.service.model.InstinctActionTakenType;
import instinct.service.model.InstinctFraudAlertType;
import instinct.service.model.InstinctResult;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.models.InstinctResultForIA;

/**
 * @author yanxiaonan
 *
 */
public abstract class AbstractCheckInstinctAntiFraudHandler extends NonBlockingJobHandler {
	/* 
	 * anti fraud result from Instinct System
	 */
	
	protected InstinctResult judge(String appId) throws Exception{
		InstinctResult inres = InstinctOnlineJudge.judge(appId, this.getInstinctAction());
		Logger.get().info(inres);

		return inres;
	}
	
	protected void updateDataBacktoInstinct(String appId){
		try{
			InstinctOnlineJudge.update(appId);
		}catch(Exception e){
			//just log when update operation fails
			Logger.get().error(String.format("updateDataBacktoInstinct failed: appId=%s",appId),e);
		}
	}
	
	protected void addRawDatas(String appId, String data){
		RawDataStorageManager.addRawDatas(
		        new RawData(appId, this.getRawDataVariableNames(), data));
	}
	
	protected void generateAppDerivativeVariables(String appId, InstinctFraudAlertType type){
		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
		if(type != null && InstinctFraudAlertType.HFP == type){
			builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.HighRisk.getValue());
		}else if(type != null && InstinctFraudAlertType.Clean == type){
			builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.Clear.getValue());
		}
		
		//
		if(type != null && InstinctFraudAlertType.Suspect == type){
			this.sendsResponse = false;
		}
		
		if (!builder.build().isEmpty()) {
			AppDerivativeVariableManager.addVariables(builder.build());
		}
		
	}
	
	protected void generateManualJobStartTime(String appId){
		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
		builder.add(AppDerivativeVariableNames.INSTINCT_MANUAL_JOB_START_TIME, new Date());
		builder.add(AppDerivativeVariableNames.INSTINCT_INVOLVE_MANUAL_JOB, true);
		AppDerivativeVariableManager.addVariables(builder.build());
	}
	
	protected void generateManualJobEndTime(String appId){
		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
		builder.add(AppDerivativeVariableNames.INSTINCT_MANUAL_JOB_END_TIME, new Date());
		AppDerivativeVariableManager.addVariables(builder.build());
	}
	
	protected void generateNullDataDerivativeVariable(String appId){
		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
		builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.NullAppData.getValue());
		AppDerivativeVariableManager.addVariables(builder.build());
	}
	
	protected void generateAppDerivativeVariables(String appId, InstinctActionTakenType type){
		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
		if(type != null && InstinctActionTakenType.KnownFraud == type){
			builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.HighRisk.getValue());
		}else{ //only KnownFraud and FalsePositive type are expected at final check stage according to instinct system
			builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.Clear.getValue());
		}
		
		if (!builder.build().isEmpty()) {
			AppDerivativeVariableManager.addVariables(builder.build());
		}
	}
	
	
	
	protected abstract String getInstinctAction();
	protected abstract String getRawDataVariableNames();
	protected abstract String getInstinctHasFraudRiskVarName();
	
	/**
	 * <p>决定原因</p>
	 * 
	 * @return
	 */
	protected abstract String getInstinctDecisionReasonVarName();
	
	/**
	 * <p>根据Instinct返回结果存入MongoDB</p>
	 * 
	 * @param appId
	 * @param result
	 */
	protected InstinctResultForIA generateAppDerivativeVariables(String appId, String handle) {
	    
	    if (handle == null) {
            Logger.get().error(String.format("Instinct Result For IA is Null.AppId:%s", appId));
            return null;
        }
	    InstinctResultForIA result = new Gson().fromJson(handle, InstinctResultForIA.class);
	    if (result == null) {
	        Logger.get().error(String.format("Instinct Result For IA is Null.AppId:%s", appId));
	        return null;
	    }
	    
	    AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
        // PreCheck / FinalCheck 欺诈
        if (InstinctFraudAlertType.HFP.getValue().equals(result.getDecisionResult())) {
            builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.HighRisk.getValue());
        }
        // PreCheck / FinalCheck 清白
        else if (InstinctFraudAlertType.Clean.getValue().equals(result.getDecisionResult())) {
            builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.Clear.getValue());
        }
        // PreCheck / FinalCheck 可疑
        else if (InstinctFraudAlertType.Suspect.getValue().equals(result.getDecisionResult())) {
            this.sendsResponse = false;
        }
        // 交易监控 欺诈
        else if (InstinctActionTakenType.KnownFraud.getValue().equals(result.getDecisionResult())) {
            builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.HighRisk.getValue());
        }
        // 交易监控 清白
        else if (InstinctActionTakenType.FalsePositive.getValue().equals(result.getDecisionResult())) {
            builder.add(this.getInstinctHasFraudRiskVarName(), InstinctCheckResultType.Clear.getValue());
        }
        // 其他情况 异常
        else {
            Logger.get().error(String.format("Instinct Result For IA is Error.AppId:%s", appId));
        }
        
        // 决定原因
        if (!StringUtils.isNullOrWhiteSpaces(result.getDecisionReason())) {
            builder.add(this.getInstinctDecisionReasonVarName(), result.getDecisionReason());
        }
        
        if (!builder.build().isEmpty()) {
            AppDerivativeVariableManager.addVariables(builder.build());
        }
        
        return result;
	}
}
