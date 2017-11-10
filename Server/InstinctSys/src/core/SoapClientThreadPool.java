/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.queue.QueueApi;
import catfish.base.queue.QueueMessager;
import instinct.service.exception.InstinctServiceException;
import instinct.service.exception.MalFormedApplicationException;
import instinct.service.model.InstinctFraudAlertType;
import instinct.service.model.InstinctResult;
import instinct.service.model.InstinctResultForIA;

/** A thread of Instinct soap client. */
public final class SoapClientThreadPool {
    private SoapClientThreadPool() {

    }

    private static int workerThreadCount;

    public static void createWorkerThreadPool(int threadCount) {
        workerThreadCount = threadCount;
        ExecutorService pool = Executors.newFixedThreadPool(workerThreadCount);

        for (int i = 0; i < workerThreadCount; i++) {
            Thread t1 = new Thread(new WorkerThread(), "Worker thread " + i);

            pool.execute(t1);
        }
        Logger.get().info(
            String.format("Judge service is running with %d worker threads...", workerThreadCount));
    }

}

class WorkerThread extends Thread {

    @Override
    public void run() {
        Map<String, String> iaMessage = new HashMap<String, String>();
        InstinctResult res = new InstinctResult();
        QueueMessager qm = new QueueMessager(null, null);
        String appId = null;
        String messageBody = null;
        Gson gson = new Gson();

        while (true) {
            qm = QueueApi.consumeMessager("InstinctQueue");

            if (qm != null) {
                if (!("ToBeJudged".equalsIgnoreCase(qm.getJobStatus())
                      || "ToBeUpdate".equalsIgnoreCase(qm.getJobStatus()))) {
                    Logger.get()
                        .error(String.format("Unrecognized %s incoming message with job status %s!",
                            qm.getJobName(), qm.getJobStatus()));
                    continue;
                }

                appId = qm.getAppId();
                Logger.get().info(String.format("The appId is %s!", appId));

                // PreCheck
                if (qm.getJobName().equalsIgnoreCase("CheckInstinctAntiFraudPre")) {
                    //                    try 
                    //                    {
                    //                        res = InstinctOnlineJudge.judge(appId, "preCheck");
                    //                    } 
                    //                    catch (InstinctServiceException | MalFormedApplicationException e1) 
                    //                    {
                    //                        // TODO Auto-generated catch block
                    //                        e1.printStackTrace();
                    //                    }
                    //                    Logger.get().info(String.format(
                    //                            "[Worker thread %d] The preCheck judging result is %s!",
                    //                            threadIndex,
                    //                            res.toString()));
                    //                    iaMessage.put("jobName", "CheckInstinctAntiFraudPre");
                    res = this.judge(appId, "preCheck");
                    iaMessage.put("jobName", qm.getJobName());
                }
                // FinalCheck
                else if (qm.getJobName().equalsIgnoreCase("CheckInstinctAntiFraudFinal")) {
                    //                    try 
                    //                    {
                    //                        res = InstinctOnlineJudge.judge(appId, "finalCheck");
                    //                    } 
                    //                    catch (InstinctServiceException | MalFormedApplicationException e1) 
                    //                    {
                    //                        // TODO Auto-generated catch block
                    //                        e1.printStackTrace();
                    //                    }
                    //                    Logger.get().info(String.format(
                    //                            "[Worker thread %d] The finalCheck judging result is %s!",
                    //                            threadIndex,
                    //                            res.toString()));
                    //                    iaMessage.put("jobName", "CheckInstinctAntiFraudFinal");
                    res = this.judge(appId, "finalCheck");
                    iaMessage.put("jobName", qm.getJobName());
                }
                // Update
                // v20160920 add start 增加update节点用于任何最终状态的申请件都能进入Instinct
                else if (qm.getJobName().equalsIgnoreCase("CheckInstinctAntiFraudUpdate")) {
                    //                    try 
                    //                    {
                    //                        res = InstinctOnlineJudge.judge(appId, "update");
                    //                    } 
                    //                    catch (InstinctServiceException | MalFormedApplicationException e1) 
                    //                    {
                    //                        // TODO Auto-generated catch block
                    //                        e1.printStackTrace();
                    //                    }
                    //                    Logger.get().info(String.format(
                    //                            "[Worker thread %d] The update judging result is %s!",
                    //                            threadIndex,
                    //                            res.toString()));
                    res = this.judge(appId, "update");
                }
                // v20160920 add end
                else {
                    Logger.get().error(String.format(
                        "Unrecognized incoming message with bad job name %s!", qm.getJobName()));
                    continue;
                }

                // v20160920 modifty start update节点时无返回信息、返回信息增加拒绝原因
                if ("ToBeJudged".equalsIgnoreCase(qm.getJobStatus())) {
                    iaMessage.put("appId", appId);
                    iaMessage.put("jobStatus", "InstinctJudgeCallBack");
                    //    				iaMessage.put("handle", new Gson().toJson(res));
                    //    				iaMessage.put("handle", res.fraudAlert == null ? "" : res.fraudAlert.getValue());
                    InstinctResultForIA result = new InstinctResultForIA();
                    result
                        .setDecisionResult(res.fraudAlert == null ? "" : res.fraudAlert.getValue());
                    if (res.fraudAlert != null
                        && InstinctFraudAlertType.HFP.equals(res.fraudAlert)) {
                        // 决定原因编码
                        String decisionCode = res.getRecisionCode();
                        if (decisionCode.length() > 1) {
                            result.setDecisionReason(decisionCode);
                        } else {
                            Logger.get()
                                .error(String.format(
                                    "DecisionReason is Empty or Invaild!DecisionReason:%s",
                                    res.decisionReason));
                        }
                    }

                    iaMessage.put("handle", new Gson().toJson(result));

                    messageBody = gson.toJson(iaMessage);
                    QueueApi.writeMessage("JobRequestQueue", messageBody, 1, 0);
                }
                // v20160920 modifty end

                iaMessage.clear();
                res.clear();
            }
        }
    }

    /**
     * <p>欺诈判定</p>
     * 
     * @param appId
     * @param instinctCall
     * @return
     */
    private InstinctResult judge(String appId, String instinctCall) {
        InstinctResult res = null;
        try {
            res = InstinctOnlineJudge.judge(appId, instinctCall);
        } catch (InstinctServiceException | MalFormedApplicationException e) {
            Logger.get().error("AppId:" + appId, e);
            res = new InstinctResult();
        } catch (Exception e) {
            Logger.get().error("AppId:" + appId, e);
            res = new InstinctResult();
        }
        Logger.get()
            .info(String.format("The %s judging result is %s!", instinctCall, res.toString()));
        return res;
    }
}