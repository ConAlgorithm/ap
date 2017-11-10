/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package com.dectechsolutions.instinct;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.queue.QueueApi;
import instinct.service.model.InstinctActionTakenType;
import instinct.service.model.InstinctResult;
import instinct.service.model.InstinctResultForIA;

@javax.jws.WebService(endpointInterface = "com.dectechsolutions.instinct.InstinctActionWebServiceSoap", targetNamespace = "http://dectechsolutions.com/Instinct", serviceName = "InstinctActionWebService", portName = "InstinctActionWebServiceSoap")
public class InstinctActionWebServiceSoapImpl {

    /**
     * 
     * <p>判定结果返回接口</p>
     * <p>自动判定、人工审核都会返回改接口，但只需人工审核接口才能返回IA</p>
     * 
     * @param actionString
     * @return
     */
    @SuppressWarnings("unused")
    public String instinctActionStringReturn(String actionString) {
        Map<String, String> iaMessage = new HashMap<String, String>();
        InstinctResult instinctRes = null;
        try {
            instinctRes = new InstinctResult(actionString);
        } catch (Exception e) {
            //            e.printStackTrace();
            Logger.get().error("", e);
            return "Fail";
        }

        if (instinctRes == null) {
            Logger.get().error("InstinctResult is null.");
            return "Fail";
        }

        Logger.get().info("Received string is " + actionString);
        Logger.get().info("Received instinct result is " + instinctRes);

        // v20161025 add start 自动判定返回结果不返回IA
        if ("SYSTEM".equalsIgnoreCase(instinctRes.userId)) {
            Logger.get().info("This result is received by Instinct System User.");
            return "Success";
        }
        // v20161025 add end

        iaMessage.put("appId", instinctRes.appId);
        iaMessage.put("jobName", "CheckInstinctAntiFraudFinal");
        iaMessage.put("jobStatus", "InstinctActionCallBack");
        switch (instinctRes.actionTaken) {
            case FalsePositive:
                break;
            default:
                instinctRes.actionTaken = InstinctActionTakenType.KnownFraud;
                break;
        }
        // v20160920 modifty start update节点时无返回信息、返回信息增加拒绝原因
        //		    iaMessage.put("handle", instinctRes.actionTaken.getValue());
        //		    iaMessage.put("handle", new Gson().toJson(instinctRes));
        InstinctResultForIA result = new InstinctResultForIA();
        result.setDecisionResult(
            instinctRes.actionTaken == null ? "" : instinctRes.actionTaken.getValue());
        if (instinctRes.actionTaken != null
            && InstinctActionTakenType.KnownFraud.equals(instinctRes.actionTaken)) {
            // 决定原因编码
            String decisionCode = instinctRes.getRecisionCode();
            if (decisionCode.length() > 1) {
                result.setDecisionReason(decisionCode);
            } else {
                Logger.get()
                    .error(String.format("DecisionReason is Empty or Invaild!DecisionReason:%s",
                        instinctRes.decisionReason));
            }
        }
        iaMessage.put("handle", new Gson().toJson(result));
        // v20160920 modifty end
        String messageBody = new Gson().toJson(iaMessage);
        //            System.out.println("messageBody is " + messageBody);
        Logger.get().info("messageBody is " + messageBody);
        //		    QueueApi.ensureQueueExist("JobRequestQueue", 30L);
        QueueApi.writeMessage("JobRequestQueue", messageBody, 1, 0);

        //            System.out.println("Received string is " + actionString);
        //            System.out.println("Received instinct result is " + instinctRes);
        return "Success";

    }

    /**
     * <p>接口以XML形式返回内容</p>
     * <p><b>未使用</b></p>
     * 
     * @param actionString
     * @return
     */
    public String instinctActionXMLStringReturn(String actionString) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}