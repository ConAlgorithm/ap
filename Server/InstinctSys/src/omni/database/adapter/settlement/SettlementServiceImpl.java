/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package omni.database.adapter.settlement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;
import omni.database.DatabaseClient;
import omni.database.catfish.object.OverDueInfoModel;
import omni.database.catfish.object.ResponseModel;
import omni.database.catfish.object.hybrid.AppCountObject;
import omni.database.catfish.object.hybrid.AppSecondCreditObject;
import omni.database.catfish.object.hybrid.InstallmentStatus;
import omni.database.catfish.object.hybrid.PaymentDetail;
import omni.database.catfish.object.hybrid.RepaymentTableResult;
import omni.database.catfish.object.hybrid.constant.PaymentStatus;
import omni.model.PaymentInfoResponse;

/**
 * 对接清结算接口
 *
 * @author baowzh
 * @version SettlementServiceImpl.java, V1.0 2016年9月13日 上午10:09:39
 */
public class SettlementServiceImpl implements SettlementService {

    private static int MaxRetry = StartupConfig.getAsInt("instinct.maxRetry", 3);
    private static String SettlementPaymentUrl;
    private static String SettlementUrl;
    private static String SettlementBatchPaymentInfo;
    private static String SettlementRepaymentInfo;
    private static String SettlementStatus;
    private static int ExecuteSize = StartupConfig.getAsInt("instinct.settlement.batchPaymentInfo.size", 1000);
    public static String routerdwUrl = StartupConfig.get("risk.router.dw.url");

    static {
        SettlementPaymentUrl = StartupConfig.get("instinct.settlement.payment.url");
        SettlementUrl = StartupConfig.get("instinct.settlement.url");
        if (SettlementPaymentUrl != null && SettlementPaymentUrl.length() > 0) {
            SettlementBatchPaymentInfo = StartupConfig.get("instinct.settlement.batchPaymentInfo");
        }
        if (SettlementUrl != null && SettlementUrl.length() > 0) {
            SettlementRepaymentInfo = StartupConfig.get("instinct.settlement.repaymentInfo");
            SettlementStatus = StartupConfig.get("instinct.settlement.status");
        }
    }

    /* (non-Javadoc)
     * @see omni.database.adapter.settlement.SettlementService#getMassiveAppD2FPD7RateInfoById(java.util.List)
     */
    @SuppressWarnings("unused")
    @Override
    public Map<String, AppCountObject> getMassiveAppD2FPD7RateInfoById(List<String> appIds) {

        // 通过AppId列表获取D2批核件信息数据集合
        Map<String, List<String>> appMap = DatabaseClient.catfishDao.getMassiveD2AppInfoById(appIds);

        Map<String, AppCountObject> appCountObjMap = new HashMap<>();

        // 当前时间
        long currentDateTime = System.currentTimeMillis();

        // 遍历所有App申请件
        appMap.forEach((mainAppId, associatedAppIdList) -> {
            // 总批核件数
            int totalSize = associatedAppIdList.size();
            // 起始索引
            int fromIndex = 0;
            // 终止索引
            int toIndex = ExecuteSize < totalSize ? ExecuteSize : totalSize;
            // 处理申请件列表(1~1000)
            List<String> executeList;
            // 处理索引
            int executeIndex = 0;
            // FPD7件数
            int FPD7Count = 0;
            Long FPD7CountTmp = 0l;
            // 是否执行成功
            boolean isSuccess = true;

            while (fromIndex < totalSize) {
                executeList = associatedAppIdList.subList(fromIndex, toIndex);

                // 统计FPD7件数
//                FPD7CountTmp = this.executeBatchPaymentInfo(executeList, currentDateTime, mainAppId, executeIndex);
                FPD7CountTmp = this.getFpd7Count(executeList, mainAppId, executeIndex);
                // 有错误不做后续处理
                if (FPD7CountTmp == null) {
                    isSuccess = false;
                    break;
                }

                FPD7Count += FPD7CountTmp.intValue();

                fromIndex = toIndex;
                toIndex = (toIndex + ExecuteSize) < totalSize ? (toIndex + ExecuteSize) : totalSize;
                executeIndex++;
            }

            // 全部成功
            if (isSuccess) {
                AppCountObject appCountObject = new AppCountObject();
                appCountObject.appId = mainAppId;
                // D2FPD7%
                if (totalSize != 0) {
                    appCountObject.d2FPD7Rate = new BigDecimal(FPD7Count).multiply(new BigDecimal(100)).divide(new BigDecimal(totalSize), 2, BigDecimal.ROUND_HALF_UP);
                }
                // 总件数为0
                else {
                    Logger.get().error(String.format("D2's App count is zero! AppId:%s", mainAppId));
                }

                appCountObjMap.put(mainAppId, appCountObject);
            }

        });

        return appCountObjMap;
    }

    /**
     * <p>统计FPD7件数</p>
     * 
     * @param appIds
     * @param currentDateTime
     * @param appId
     * @param executeIndex
     * @return
     */
    @SuppressWarnings("unused")
    private Long executeBatchPaymentInfo(List<String> appIds, long currentDateTime, String appId, int executeIndex) {

        return HttpClientApi.CallService(MaxRetry, new ServiceHandler<Map<String, List<PaymentInfoResponse>>, Long>() {

            @Override
            public String createUrl() {///settlement/batch/payment-info
                if (StringUtils.isNullOrWhiteSpaces(SettlementPaymentUrl) || StringUtils.isNullOrWhiteSpaces(SettlementBatchPaymentInfo)) {
                    throw new RuntimeException("SettlementBatchPaymentInfoUrl Is empty!");
                }
                return SettlementPaymentUrl + SettlementBatchPaymentInfo;
            }

            @Override
            public Map<String, List<PaymentInfoResponse>> OnRequest(String url) {
                String jsonResult = HttpClientApi.postJson(url, CollectionUtils.mapOf("appIds", appIds, "date", currentDateTime), "map");

                return new Gson().fromJson(jsonResult, new TypeToken<Map<String, List<PaymentInfoResponse>>>() {
                }.getType());
            }

            @Override
            public Long OnSuccess(Map<String, List<PaymentInfoResponse>> response) {

                try {
                    return response.values().stream().map(e -> (e.stream().filter(p -> (p.getInstalmentNum() == 1)).findFirst().get())).filter(e -> {
                        // 没有还款日
                        if (e.getPayDate() == null) {
                            // 当前日，逾期超过七天
                            if (SettlementServiceImpl.this.getDayDiff(e.getDueDate(), currentDateTime) >= 7) {
                                return true;
                            }
                        }
                        // 有还款日
                        else {
                            // 未还完
                            if (e.getOwningTotal().compareTo(BigDecimal.ZERO) > 0) {
                                // 当前日，逾期超过七天
                                if (SettlementServiceImpl.this.getDayDiff(e.getDueDate(), currentDateTime) >= 7) {
                                    return true;
                                }
                            }
                            // 已还完
                            else {
                                // 还款日，逾期超过七天
                                if (SettlementServiceImpl.this.getDayDiff(e.getDueDate(), e.getPayDate()) >= 7) {
                                    return true;
                                }
                            }
                        }

                        return false;
                    }).count();
                } catch (Exception ex) {
                    Logger.get().error(String.format("Calc D2PDF7Count error! AppId:%s,Index:%d", appId, executeIndex), ex);
                    return null;
                }

            }

            @Override
            public Long OnError(Map<String, List<PaymentInfoResponse>> response) {
                Logger.get().error(String.format("Call SettlementBatchPaymentInfo error! AppId:%s,Index:%d", appId, executeIndex));
                return null;
            }
        });
    }

    /**
     * <p>日期差值</p>
     * 
     * @param start
     * @param end
     * @return
     */
    private int getDayDiff(long start, long end) {
        long millsOfDay = 1000 * 60 * 60 * 24;
        return (int) ((end - start) / millsOfDay);
    }

    /**
     * <p>〈根据appId获取二次贷信息〉</p>
     * 
     * @param appId
     * @return
     */
    @Override
    public Map<String, AppSecondCreditObject> getSecondCreditInfoById(List<String> appIds) {
        Map<String, AppSecondCreditObject> map = new HashMap<String, AppSecondCreditObject>();
        for (String appId : appIds) {
            InstallmentApplicationObject lastInstallmentApplicationObjects = DatabaseClient.catfishDao.getLastInstallmentApplicationObjectsById(appId);
            AppSecondCreditObject appSecondCredit = new AppSecondCreditObject();
            //还款结清日期
            Long paymentDate = null;
            //上一次借款状态
            Integer lastLoanStatus = null;
            //上一次借款申请日期
            Date lastInstallmentStartedOn = null;
            //上一次借款提前还款天数
            Integer lastLoanPrepaymentdays = null;
            //上一次已完成借款最大逾期天数
            Integer lastAppMaxDelayedDays = null;
            //距离上一次已完成借款最后还款日期时间
            Integer lastApplicationInterval = null;
            if (lastInstallmentApplicationObjects == null) {
                appSecondCredit.IsSecondTimeUser = false;
            } else {
                lastInstallmentStartedOn = lastInstallmentApplicationObjects.getInstallmentStartedOn();
                String id = lastInstallmentApplicationObjects.getId();
                if (id != null) {
                    Map<Integer, RepaymentTableResult> repaymentTable = this.getRepaymentTable(id);
                    if (repaymentTable == null || repaymentTable.size() == 0) {
                        Logger.get().error("得到空的还款计划");

                    }
                    try {
                        lastAppMaxDelayedDays = getLastAppMaxDelayedDays(repaymentTable);
                    } catch (Exception e) {
                        Logger.get().error("getLastAppMaxDelayedDays function error", e);
                    }

                    //设置上一次已完成借款最大逾期天数
                    appSecondCredit.LastAppMaxDelayedDays = lastAppMaxDelayedDays;
                    //设置是否是二次贷
                    appSecondCredit.IsSecondTimeUser = true;

                    InstallmentStatus installmentStatus = null;
                    try {
                        installmentStatus = getStatus(id);
                    } catch (Exception e) {
                        Logger.get().error("getStatus function error", e);
                    }

                    if (installmentStatus != null) {
                        String status = installmentStatus.getStatus();
                        Long updateDate = installmentStatus.getUpdateDate();
                        if (status.equals(PaymentStatus.aheadPayment.getValue()) || status.equals(PaymentStatus.hesitationPeriodPayment.getValue())) {
                            lastLoanPrepaymentdays = this.getDayDiff(lastInstallmentStartedOn.getTime(), updateDate);
                            //set上一次借款提前还款天数
                            appSecondCredit.LastLoanPrepaymentdays = lastLoanPrepaymentdays;
                            paymentDate = updateDate;
                            lastLoanStatus = ApplicationStatus.ClosedInAdvanced.getValue();
                        } else if (status.equals(PaymentStatus.normalPayment.getValue())) {
                            paymentDate = updateDate;
                            lastLoanStatus = ApplicationStatus.Closed.getValue();
                        } else {
                            Logger.get().error("上一次还款状态逾期或者正在还款中,状态异常");
                        }
                    }

                    //set上一次借款状态
                    appSecondCredit.LastLoanStatus = lastLoanStatus;
                    if (paymentDate == null) {
                        Logger.get().error("上一次还款状态逾期或者正在还款中,状态异常");
                    } else {
                        lastApplicationInterval = this.getDayDiff(paymentDate, new Date().getTime());
                    }
                    //set距离上一次已完成借款最后还款日期时间
                    appSecondCredit.LastApplicationInterval = lastApplicationInterval;
                }
            }
            map.put(appId, appSecondCredit);
        }
        return map;
    }

    //通过appId得到用户贷后还款计划表
    public Map<Integer, RepaymentTableResult> getRepaymentTable(String appId) {
        if (StringUtils.isNullOrWhiteSpaces(SettlementUrl) || StringUtils.isNullOrWhiteSpaces(SettlementRepaymentInfo)) {
            Logger.get().warn("SettlementUrl or SettlementRepaymentInfo is null");
            return null;
        }
        String requestUrl = SettlementUrl + SettlementRepaymentInfo + "?appId=" + appId;
        HashMap<Integer, RepaymentTableResult> map = new HashMap<Integer, RepaymentTableResult>();
        try {
            String str = HttpClientApi.get(requestUrl);
            map = new Gson().fromJson(str, new TypeToken<Map<Integer, RepaymentTableResult>>() {
            }.getType());

        } catch (Exception e) {
            Logger.get().error("get Settlement RepaymentTable exception", e);
        }

        return map;
    }

    //根据Map类型还款记录求最大预期天数
    private Integer getLastAppMaxDelayedDays(Map<Integer, RepaymentTableResult> map) {
        if (map == null || map.size() == 0) {
            Logger.get().error("得到空的还款计划");
            return null;
        }

        Iterator<RepaymentTableResult> iterator = map.values().iterator();
        int max = 0;

        while (iterator.hasNext()) {
            RepaymentTableResult next = iterator.next();
            PaymentDetail principal = next.getPrincipal();
            Long dueDate = principal.getDueDate();
            Long payDate = principal.getPayDate();
            int temp = 0;
            if (dueDate == null || payDate == null) {
                continue;
            }
            temp=this.getDayDiff(dueDate, payDate);
            if (temp > max) {
                max = temp;
            }
        }

        return max;
    }


    //通过appId得到还款状态
    public static InstallmentStatus getStatus(String appId) {
        if (StringUtils.isNullOrWhiteSpaces(SettlementUrl) || StringUtils.isNullOrWhiteSpaces(SettlementStatus)) {
            Logger.get().warn("SettlementUrl or SettlementStatus is null");
            return null;
        }
        String requestUrl = SettlementUrl + SettlementStatus + "?appId=" + appId;
        InstallmentStatus status = null;
        try {
            //status = HttpClientApi.getGson(requestUrl, InstallmentStatus.class);
            String str=HttpClientApi.get(requestUrl);
            status=new Gson().fromJson(str,new TypeToken<InstallmentStatus>() {
            }.getType());
        } catch (Exception e) {
            Logger.get().error("get Settlement Status exception", e);
        }

        if (status == null) {
            Logger.get().error("get Settlement Status result is null");
        }
        return status;
    }
    
    
    //统计FPD7件数，调用数仓-账务接口查询
    public Long getFpd7Count(List<String> appIds, String appId, int executeIndex){
        Long count = 0L;
        int retryCount = 2;
        if ("".equals(routerdwUrl) || routerdwUrl == null) {
            Logger.get().warn("routerdwUrl is null,please add routerdwUrl in configuration file ! ");
            return count;
        }
        if(appIds == null || appIds.isEmpty()){
            return count;
        }
        Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
        for (int i = 0; i < retryCount; i++) {
            try {
                String json = HttpClientApi.postString(routerdwUrl + "/overdueinfo/appIdList", new Gson().toJson(appIds), header);
                ResponseModel<List<OverDueInfoModel>> response = new Gson().fromJson(json, new TypeToken<ResponseModel<List<OverDueInfoModel>>>() {
                }.getType());
                if (response != null && response.getCode() == 0) {
                    Logger.get().info("getOverDueInfoModelList is success ! appId is " + appId);
                    List<OverDueInfoModel> list = response.getData();
                    if (list != null && list.size() > 0) {
                        for (OverDueInfoModel overDueInfoModel : list) {
                            if (overDueInfoModel.getFpd7() == 300) {
                                count++;
                            }
                        }
                    }
                }
                return count;
            } catch (Exception e) {
                if (i == 1) {
                    Logger.get().error(String.format("getFpd7Count error! AppId:%s,Index:%d", appId, executeIndex), e);
                    return count;
                }
                Logger.get().warn("getFpd7Count need retry,appId is " + appId);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    Logger.get().error("getFpd7Count timeUnit has error", e1);
                }

            }
        }
        return count;        
    }
}
