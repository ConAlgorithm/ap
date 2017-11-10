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
import jma.JobHandlerSwitch;
import jma.RetryRequiredException;

public class CheckInstinctAntiFraudPreHandler extends AbstractCheckInstinctAntiFraudHandler {

    @Override
    protected String getInstinctAction() {
        return "precheck";
    }

    @Override
    protected String getRawDataVariableNames() {
        return RawDataVariableNames.INSTINCT_UNITED_RAW_DATA_PRE;
    }

    @Override
    protected String getInstinctHasFraudRiskVarName() {
        return AppDerivativeVariableNames.INSTINCT_HAS_FRAUD_RISK_PRE;
    }

    /* (non-Javadoc)
     * @see jma.handlers.AbstractCheckInstinctAntiFraudHandler#getInstinctDecisionReasonVarName()
     */
    @Override
    protected String getInstinctDecisionReasonVarName() {
        return AppDerivativeVariableConsts.InstinctDecisionReasonPre;
    }

    @Override
    public void execute(String appId) throws RetryRequiredException {
        if (DynamicConfig.read("CheckInstinctAntiFraudPreSwitch", JobHandlerSwitch.Off.getValue())
            .equals(JobHandlerSwitch.Off.getValue())) {
            return;
        }

        String jobStatus = this.requestMessager.getJobStatus();

        if (!StringUtils.isNullOrWhiteSpaces(jobStatus)
            && "InstinctJudgeCallBack".equals(jobStatus)) {

            String insResString = this.requestMessager.getHandle();
            //			InstinctResult insRes = new Gson().fromJson(insResString, InstinctResult.class);

            this.addRawDatas(appId, this.requestMessager.toString());
            // v20160920 modify start 调整Instinct返回内容
            //			this.generateAppDerivativeVariables(appId, InstinctFraudAlertType.forValue(insResString));
            super.generateAppDerivativeVariables(appId, insResString);
            // v20160920 modify end

            // v20161025 modify start 调整Instinct流程节点阻塞开关
            //			this.sendsResponse = false;
            this.sendsResponse = true;
            // v20161025 modify end
        } else {
            //request instinct system
            Map<String, String> iaMessage = new HashMap<String, String>();
            iaMessage.put("appId", this.requestMessager.getAppId());
            iaMessage.put("jobName", "CheckInstinctAntiFraudPre");
            iaMessage.put("jobStatus", "ToBeJudged");
            String messageBody = new Gson().toJson(iaMessage);
            Logger.get()
                .info("Send CheckInstinctAntiFraudPre Msg to InstinctQueue, msg: " + messageBody);
            QueueApi.writeMessage("InstinctQueue", messageBody, 1, 0);

            // v20161025 modify start 调整Instinct流程节点阻塞开关
            //		    this.sendsResponse = true;
            this.sendsResponse = false;
            // v20161025 modify end
        }
    }

}
