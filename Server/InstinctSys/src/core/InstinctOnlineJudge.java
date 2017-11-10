/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package core;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import instinct.service.client.OnlineFraudCheck;
import instinct.service.client.OnlineFraudCheckSoap;
import instinct.service.exception.InstinctServiceException;
import instinct.service.exception.MalFormedApplicationException;
import instinct.service.model.InstinctFraudAlertType;
import instinct.service.model.InstinctResult;

/**
 * This class provides synchronized online judge methods.
 * 
 * @author guoqing
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public final class InstinctOnlineJudge {

    private static int MaxRetry = StartupConfig.getAsInt("instinct.maxRetry", 3);

    /**
     * @version 1.0.0
     *
     */
    private InstinctOnlineJudge() {
    }

    /**
     * @version 1.0.0
     *
     */
    private static OnlineFraudCheckSoap portType;
    static {
        OnlineFraudCheck service = new OnlineFraudCheck();
        portType = service.getOnlineFraudCheckSoap();
    }

    /**
     * This method updates the Instinct server's database 
     * with the final check result from Omni system.
     * 
     * @param appId application ID to update.
     * @return InstinctResult from Instinct server.
     * @see InstinctResult
     * 
     */
    public static InstinctResult update(String appId) {
        appId = appId.toUpperCase(); // fix upperCase bug
        String requestJudgement = InstinctizeModel.allInOne(appId, "update");
        String response = portType.instinctFraudCheckString(requestJudgement);
        return new InstinctResult(response);
    }

    /**
     * This method sends an online request to the Instinct server 
     * to judge the application from Omni system.
     * 
     * @param appId application ID to judge.
     * @param instinctCall supports "preCheck" and "finalCheck" online judge.
     * @return InstinctResult indicating judging result from Instinct server.
     * @see InstinctResult
     * @throws InstinctServiceException Instinct server exception.
     * @throws MalFormedApplicationException Omni application exception.
     * 
     */
    public static InstinctResult judge(String appId,
                                       String instinctCall) throws InstinctServiceException,
                                                            MalFormedApplicationException {
        appId = appId.toUpperCase(); // fix upperCase bug
        String requestJudgement = null;
        try {
            requestJudgement = InstinctizeModel.allInOne(appId, instinctCall);
        } catch (Exception e) {
            String type = e.getClass().getTypeName();
            if ("java.lang.NullPointerException".equals(type)) {
                throw new MalFormedApplicationException("Some columns are not correctly settled!",
                    e);
            } else {
                //                Logger.get().warn("The type of this exception is " + type);
                throw new MalFormedApplicationException("What the hell is this exception !!?", e);
            }
        }

        int retry = 0;
        String response = "";
        while (retry < MaxRetry) {
            try {
                // 调用Instinct接口
                response = portType.instinctFraudCheckString(requestJudgement);
                break;
            } catch (Exception e) {
                Logger.get()
                    .warn(String.format(
                        "InstinctFraudCheck Failed, RequestJudgement: %s, Retry count: %d",
                        requestJudgement, retry), e);
                ++retry;
            }
        }

        if ("".equalsIgnoreCase(response) || response == null || response.isEmpty()) {
            throw new InstinctServiceException(
                "Online check fail with an empty response from Instinct system!");
        }

        InstinctResult instinctRes = null;

        try {
            instinctRes = new InstinctResult(response);
        } catch (Exception e) {
            //            Logger.get().fatal("The malformed response is " + response + ".");
            throw new InstinctServiceException("The malformed response is " + response + "."
                                               + "MalFormatted response from Instinct server! "
                                               + "Please check the server's output configuration!",
                e);
        }

        if (instinctRes.fraudAlert == null) {
            throw new InstinctServiceException("Unknown Instinct result returned! "
                                               + "Please check Instinct system for more information!"
                                               + "Maybe somebody changed the configuration or column definition on Instinct system, and please for god's sake, change it back!!!");
        }

        if ("precheck".equalsIgnoreCase(instinctCall)) {
            switch (instinctRes.fraudAlert) {
                case HFP:
                    break;
                case Suspect:
                    instinctRes.fraudAlert = InstinctFraudAlertType.HFP;
                    Logger.get().warn("During precheck, suspected application is considered bad.");
                    Logger.get().warn("The suspected appid is " + instinctRes.appId);
                    break;
                case Clean:
                    break;
                default:
                    throw new InstinctServiceException("Unknown precheck result from Instinct! "
                                                       + "Please check Instinct system for more information!"
                                                       + "Maybe somebody changed the configuration on Instinct system, and please for god's sake, change it back!!!");
            }
        } else if ("finalcheck".equalsIgnoreCase(instinctCall)) {
            switch (instinctRes.fraudAlert) {
                case HFP:
                    break;
                case Suspect:
                    Logger.get().warn(
                        "During finalcheck, suspected application will be under investigation.");
                    break;
                case Clean:
                    break;
                default:
                    throw new InstinctServiceException("Unknown finalcheck result from Instinct! "
                                                       + "Please check Instinct system for more information! "
                                                       + "Maybe somebody changed the configuration on Instinct system, and please for god's sake, change it back!!!");
            }
        } else if ("update".equalsIgnoreCase(instinctCall)) {
            // v20160920 remove start 增加update节点用于任何最终状态的申请件都能进入Instinct
            //          throw new IllegalArgumentException("Invalid action: update! Please use InstinctOnlineJudge.update(String appId) method instead!");
            // v20160920 remove end
        } else {
            throw new IllegalArgumentException("Unsupported action: " + instinctCall + ". "
                                               + "Only preCheck and finalCheck are currently available. "
                                               + "Contact Guoqing for more info.");
        }
        return instinctRes;
    }

}
