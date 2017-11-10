/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import jma.AppDerivativeVariablesBuilder;
import jma.Configuration;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;
import jma.models.InstallmentStatus;
import jma.models.PaymentDetail;
import jma.models.RepaymentTableResult;
import jma.models.enums.PaymentStatus;

/**
 * 〈查询二次贷相关信息存入mongo〉
 *
 * @author hwei
 * @version CheckSecondLoanInfoHandler.java, V1.0 2017年5月18日 上午11:08:51
 */
public class CheckSecondLoanInfoHandler extends NonBlockingJobHandler {

    @Override
    public void execute(String appId) throws RetryRequiredException {
        AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
        try {
            InstallmentApplicationObject lastInstallmentApplicationObjects = getInstallmentObject(appId);
            //set是否是二次贷
            if (lastInstallmentApplicationObjects == null) {
                builder.isNotNullAdd(DerivativeVariableNames.ISSECONDTIMEUSER, false);
            } else {
                if (lastInstallmentApplicationObjects.getId() != null) {
                    builder.isNotNullAdd(DerivativeVariableNames.ISSECONDTIMEUSER, true);
                }
                //获取还款计划表信息
                Logger.get().info("上一次申请借款appId is "+lastInstallmentApplicationObjects.getId());
                Map<Integer, RepaymentTableResult> repaymentTable = this.getRepaymentTable(lastInstallmentApplicationObjects.getId());
                try {
                    //设置上一次已完成借款最大逾期天数
                    Integer lastAppMaxDelayedDays = getLastAppMaxDelayedDays(repaymentTable);
                    builder.isNotNullAdd(DerivativeVariableNames.LASTAPPMAXDELAYEDDAYS, lastAppMaxDelayedDays);                    
                } catch (Exception e) {
                    Logger.get().error("getLastAppMaxDelayedDays function error", e);
                }
                InstallmentStatus installmentStatus = this.getStatus(lastInstallmentApplicationObjects.getId());
                //还款结清日期
                Long paymentDate = null;
                if (installmentStatus != null) {
                    String status = installmentStatus.getStatus();
                    Long updateDate = installmentStatus.getUpdateDate();
                    //set上一次借款状态
                    if (status.equals(PaymentStatus.aheadPayment.getValue()) || status.equals(PaymentStatus.hesitationPeriodPayment.getValue())) {
                        builder.isNotNullAdd(DerivativeVariableNames.LASTLOANSTATUS, ApplicationStatus.ClosedInAdvanced.getValue());
                        //上一次借款提前还款天数
                        builder.isNotNullAdd(DerivativeVariableNames.LASTLOANPREPAYMENTDAYS, this.getDayDiff(lastInstallmentApplicationObjects.getInstallmentStartedOn().getTime(), updateDate));
                        paymentDate = updateDate;
                    } else if (status.equals(PaymentStatus.normalPayment.getValue())) {
                        builder.isNotNullAdd(DerivativeVariableNames.LASTLOANSTATUS, ApplicationStatus.Closed.getValue());
                        paymentDate = updateDate;
                    } else if (status.equals(PaymentStatus.overdue.getValue())) {
                        builder.isNotNullAdd(DerivativeVariableNames.LASTLOANSTATUS, ApplicationStatus.Delayed.getValue());
                    } else if (status.equals(PaymentStatus.paymenting.getValue())) {
                        builder.isNotNullAdd(DerivativeVariableNames.LASTLOANSTATUS, ApplicationStatus.Completed.getValue());
                    } else {
                        Logger.get().info("上一次还款状态异常");
                    }                    
                }
                //set距离上一次已完成借款最后还款日期时间
                if (paymentDate == null) {
                    Logger.get().warn("上一次还款状态逾期或者正在还款中,状态异常");
                } else {
                    builder.isNotNullAdd(DerivativeVariableNames.LASTAPPLICATIONINTERVAL, this.getDayDiff(paymentDate, new Date().getTime()));
                }
            }
            if (!builder.build().isEmpty()){
                AppDerivativeVariableManager.addVariables(builder.build());                
            }
        } catch (Exception e) {
            Logger.get().error("CheckSecondLoanInfoHandler has error,appId is " + appId, e);
            throw new RetryRequiredException();
        }
    }

   
    /**
     * <p>〈获取installment信息
     * 上一次是否有贷款
     * 〉</p>
     * 
     * @param appId
     * @return
     */
    public InstallmentApplicationObject getInstallmentObject(String appId) {
        try {
            String sql = "select top 1 Id,InstallmentStartedOn from InstallmentApplicationObjects b  where b.UserId=( "
                         + "select a.UserId from InstallmentApplicationObjects a where a.Id= :AppId ) and (b.Status=500 or b.Status=600) " + "order by InstallmentStartedOn  desc ";
            Dao<InstallmentApplicationObject> instalmentDao = Dao.create(InstallmentApplicationObject.class, DatabaseApi.database);
            return instalmentDao.getSingle(sql, CollectionUtils.mapOf("AppId", appId));
        } catch (Exception e) {
            Logger.get().error("getLastInstallmentApplicationObjectsById function error,appId is " + appId, e);
            return null;
        }
    }

    /**
     * <p>日期差值</p>
     * 
     * @param start
     * @param end
     * @return
     */
    public Integer getDayDiff(long start, long end) {
        long millsOfDay = 1000 * 60 * 60 * 24;
        if (start > 0 && end > 0) {
            return (int) ((end - start) / millsOfDay);
        }
        return null;
    }
    
    //通过appId得到用户贷后还款计划表
    public Map<Integer, RepaymentTableResult> getRepaymentTable(String appId) {
        String settlementUrl = Configuration.getPhoebusUrl();
        if (StringUtils.isNullOrWhiteSpaces(settlementUrl)) {
            Logger.get().warn("SettlementUrl  is null");
            return null;
        }
        String requestUrl = settlementUrl + "settlement/repayment-table" + "?appId=" + appId;
        HashMap<Integer, RepaymentTableResult> map = new HashMap<Integer, RepaymentTableResult>();
        try {
            String str = HttpClientApi.get(requestUrl);
            map = new Gson().fromJson(str, new TypeToken<Map<Integer, RepaymentTableResult>>() {
            }.getType());
        } catch (Exception e) {
            Logger.get().warn("get Settlement RepaymentTable exception");
        }
        return map;
    }

    //根据Map类型还款记录求最大逾期天数
    private Integer getLastAppMaxDelayedDays(Map<Integer, RepaymentTableResult> map) {
        if (map == null || map.size() == 0) {
            Logger.get().warn("得到空的还款计划");
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
            temp = this.getDayDiff(dueDate, payDate);
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }
    
    //通过appId得到还款状态
    public InstallmentStatus getStatus(String appId) {
        String settlementUrl = Configuration.getPhoebusUrl();
        if (StringUtils.isNullOrWhiteSpaces(settlementUrl)) {
            Logger.get().warn("SettlementUrl  is null");
            return null;
        }
        String requestUrl = settlementUrl + "settlement/status" + "?appId=" + appId;
        InstallmentStatus status = null;
        try {
            String str=HttpClientApi.get(requestUrl);
            status=new Gson().fromJson(str,new TypeToken<InstallmentStatus>() {
            }.getType());
        } catch (Exception e) {
            Logger.get().error("get Settlement Status exception", e);
        }
        return status;
    }
    
}
