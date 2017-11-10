/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.queue.QueueApi;
import instinct.service.model.InstinctFraudAlertType;
import jma.JobHandlerSwitch;
import jma.RetryRequiredException;
import jma.models.InstinctResultForIA;
import jma.util.GrasscarpClient;

public class CheckInstinctAntiFraudFinalHandler extends AbstractCheckInstinctAntiFraudHandler {

    private String myRawDataVariableNames = RawDataVariableNames.INSTINCT_UNITED_RAW_DATA_FINAL;

    @Override
    protected String getInstinctAction() {
        return "finalcheck";
    }

    @Override
    protected String getRawDataVariableNames() {
        return myRawDataVariableNames;
    }

    @Override
    protected String getInstinctHasFraudRiskVarName() {
        return AppDerivativeVariableNames.INSTINCT_HAS_FRAUD_RISK_FINAL;
    }

    /* (non-Javadoc)
     * @see jma.handlers.AbstractCheckInstinctAntiFraudHandler#getInstinctDecisionReasonVarName()
     */
    @Override
    protected String getInstinctDecisionReasonVarName() {
        return AppDerivativeVariableConsts.InstinctDecisionReasonFinal;
    }

    @Override
    public void execute(String appId) throws RetryRequiredException {
        if (DynamicConfig.read("CheckInstinctAntiFraudFinalSwitch", JobHandlerSwitch.Off.getValue())
            .equals(JobHandlerSwitch.Off.getValue())) {
            return;
        }

        if(GrasscarpClient.isCMCC(appId)){
        	Logger.get().info(String.format("This is a ChinaMobile appId: %s , will not do instinct.", appId));
        	this.sendsResponse = true;
        	return;
        }
        // v20161025 add start 调整Instinct流程节点阻塞开关
        this.sendsResponse = true;
        // v20161025 add end

        String jobStatus = this.requestMessager.getJobStatus();
        if (!StringUtils.isNullOrWhiteSpaces(jobStatus)) {
            String insResString = this.requestMessager.getHandle();
            //			InstinctResult insRes = new Gson().fromJson(insResString, InstinctResult.class);

            if ("InstinctJudgeCallBack".equals(jobStatus)) {
                this.myRawDataVariableNames = RawDataVariableNames.INSTINCT_UNITED_RAW_DATA_FINAL;

                this.addRawDatas(appId, this.requestMessager.toString());
                // v20160920 modify start 调整Instinct返回内容
                //				this.generateAppDerivativeVariables(appId, InstinctFraudAlertType.forValue(insResString));
                InstinctResultForIA result = super.generateAppDerivativeVariables(appId,
                    insResString);

                //				if(InstinctFraudAlertType.Suspect.getValue().equalsIgnoreCase(insResString)){
                if (result != null && InstinctFraudAlertType.Suspect.getValue()
                    .equalsIgnoreCase(result.getDecisionResult())) {
                    this.generateManualJobStartTime(appId);
                }
                // v20160920 modify start 调整Instinct返回内容
            } else if ("InstinctActionCallBack".equals(jobStatus)) {
                this.generateManualJobEndTime(appId);
                //				String actionTakenString = insRes.actionTaken.getValue();

                this.myRawDataVariableNames = RawDataVariableNames.INSTINCT_UNITED_RAW_DATA_FINAL_CALLBACK;
                this.addRawDatas(appId, this.requestMessager.toString());

                // v20160920 modify start 调整Instinct返回内容
                //				if(insResString.equals(InstinctActionTakenType.KnownFraud.getValue())){
                //					//process result
                //					this.generateAppDerivativeVariables(appId, InstinctActionTakenType.KnownFraud);
                //				}else{//only KnownFraud and FalsePositive type are expected at final check stage according to instinct system
                //					this.generateAppDerivativeVariables(appId, InstinctActionTakenType.FalsePositive);
                //				}
                super.generateAppDerivativeVariables(appId, insResString);
                // v20160920 modify end 调整Instinct返回内容
            }
            // v20161025 remove start 调整Instinct流程节点阻塞开关
            //			this.sendsResponse = false;
            // v20161025 remove end
        } else {
            //request instinct system
            Map<String, String> iaMessage = new HashMap<String, String>();
            iaMessage.put("appId", this.requestMessager.getAppId());
            iaMessage.put("jobName", "CheckInstinctAntiFraudFinal");
            iaMessage.put("jobStatus", "ToBeJudged");
            String messageBody = new Gson().toJson(iaMessage);
            Logger.get()
                .info("Send CheckInstinctAntiFraudFinal Msg to InstinctQueue, msg: " + messageBody);
            QueueApi.writeMessage("InstinctQueue", messageBody, 1, 0);

            // v20161025 add start 调整Instinct流程节点阻塞开关
            this.sendsResponse = false;
            // v20161025 add end
        }
    }

}
