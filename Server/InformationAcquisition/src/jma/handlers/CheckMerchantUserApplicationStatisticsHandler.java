package jma.handlers;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseEnumValues.ApplicationStatus;
import jma.dataservice.AppDataService;
import jma.util.SettlementClient;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.database.DatabaseApi;

public class CheckMerchantUserApplicationStatisticsHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String merchantUserId = getMerchantUserIdBy(appId);
    Date installmentStartedOn = DatabaseUtils.getInstallmentStartedOnBy(appId);

    if (merchantUserId == null){
      return;
    }
    int applicationHistoryTotal = getApplicationHistoryTotal(merchantUserId);
    int applicationHistoryApproved = getApplicationHistoryApproved(merchantUserId);
    int applicationHistoryRejected = getApplicationHistoryRejected(merchantUserId);
    int overdueApplicationCount = SettlementClient.overdueApplicationCount(AppDataService.getHistoryDelayedAppIdListByMerchantUserId(merchantUserId), 4);
    int applicationTodayTotal = getApplicationTodayTotal(merchantUserId, installmentStartedOn);
    int applicationTodayApproved = getApplicationTodayApproved(merchantUserId, installmentStartedOn);
    int applicationTodayRejected = getApplicationTodayRejected(merchantUserId, installmentStartedOn);
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.MERCHANT_USER_APPLICATION_HISTORY_TOTAL,
            applicationHistoryTotal)
        .add(AppDerivativeVariableNames.MERCHANT_USER_APPLICATION_HISTORY_APPROVED,
            applicationHistoryApproved)
        .add(AppDerivativeVariableNames.MERCHANT_USER_APPLICATION_HISTORY_REJECTED,
            applicationHistoryRejected)
        .add(AppDerivativeVariableNames.MERCHANT_USER_APPLICATION_HISTORY_DELAYED,
            overdueApplicationCount)
        .add(AppDerivativeVariableNames.MERCHANT_USER_APPLICATION_TODAY_TOTAL,
            applicationTodayTotal)
        .add(AppDerivativeVariableNames.MERCHANT_USER_APPLICATION_TODAY_APPROVED,
            applicationTodayApproved)
        .add(AppDerivativeVariableNames.MERCHANT_USER_APPLICATION_TODAY_REJECTED,
            applicationTodayRejected)
        .build());
  }

  private int getApplicationHistoryTotal(String merchantUserId) {
    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantUserId = :MerchantUserId " +
        "    AND Status >= :StatusSubmitted";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantUserId", merchantUserId,
        "StatusSubmitted", ApplicationStatus.SUBMITTED);

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private int getApplicationHistoryApproved(String merchantUserId) {
    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantUserId = :MerchantUserId " +
        "    AND Status >= :StatusApproved " +
        "    AND Status != :StatusRejected";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantUserId", merchantUserId,
        "StatusApproved", ApplicationStatus.APPROVED,
        "StatusRejected", ApplicationStatus.REJECTED);

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private int getApplicationHistoryRejected(String merchantUserId) {
    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantUserId = :MerchantUserId " +
        "    AND Status = :StatusRejected";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantUserId", merchantUserId,
        "StatusRejected", ApplicationStatus.REJECTED);

    return DatabaseApi.querySingleInteger(sql, params);
  }

//  private int getApplicationHistoryDelayed(String merchantUserId) {
//    // day past due 4
//    Calendar dpd4 = Calendar.getInstance();
//    dpd4.add(Calendar.DAY_OF_MONTH, -4);
//
//    String sql =
//        "SELECT COUNT(*) " +
//        "FROM InstallmentApplicationObjects A " +
//        "WHERE A.Status = :Delayed " +
//        "    AND A.MerchantUserId = :MerchantUserId " +
//        "    AND EXISTS ( " +
//        "        SELECT * " +
//        "        FROM InstalmentObjects I " +
//        "        WHERE A.Id = I.AppId " +
//        "            AND I.Outstanding > 0 " +
//        "            AND CONVERT (varchar(30), I.DateDue, 120) <= :Dpd4 )";
//
//    Map<String, ?> params = CollectionUtils.mapOf(
//        "Delayed", ApplicationStatus.DELAYED,
//        "MerchantUserId", merchantUserId,
//        "Dpd4", DateTimeUtils.format(dpd4.getTime()));
//
//    return DatabaseApi.querySingleInteger(sql, params);
//  }

  private int getApplicationTodayTotal(String merchantUserId, Date endDate) {
    Date startDate = getStartDate(endDate);

    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantUserId = :MerchantUserId " +
        "    AND Status >= :StatusSubmitted " +
        "    AND InstallmentStartedOn >= :StartDate " +
        "    AND InstallmentStartedOn < :EndDate";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantUserId", merchantUserId,
        "StatusSubmitted", ApplicationStatus.SUBMITTED,
        "StartDate", DateTimeUtils.format(startDate),
        "EndDate", DateTimeUtils.format(endDate));

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private int getApplicationTodayApproved(String merchantUserId, Date endDate) {
    Date startDate = getStartDate(endDate);

    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantUserId = :MerchantUserId " +
        "    AND Status >= :StatusApproved " +
        "    AND Status != :StatusRejected " +
        "    AND InstallmentStartedOn >= :StartDate " +
        "    AND InstallmentStartedOn < :EndDate";

    Map<String, ?> params = CollectionUtils.<String, Object>newMapBuilder()
        .add("MerchantUserId", merchantUserId)
        .add("StatusApproved", ApplicationStatus.APPROVED)
        .add("StatusRejected", ApplicationStatus.REJECTED)
        .add("StartDate", DateTimeUtils.format(startDate))
        .add("EndDate", DateTimeUtils.format(endDate))
        .build();

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private int getApplicationTodayRejected(String merchantUserId, Date endDate) {
    Date startDate = getStartDate(endDate);

    String sql =
        "SELECT COUNT (*) " +
            "FROM InstallmentApplicationObjects " +
            "WHERE MerchantUserId = :MerchantUserId " +
            "    AND Status = :StatusRejected " +
            "    AND InstallmentStartedOn >= :StartDate " +
            "    AND InstallmentStartedOn < :EndDate";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantUserId", merchantUserId,
        "StatusRejected", ApplicationStatus.REJECTED,
        "StartDate", DateTimeUtils.format(startDate),
        "EndDate", DateTimeUtils.format(endDate));

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private String getMerchantUserIdBy(String appId) {
    String sql =
        "SELECT MerchantUserId " +
        "FROM InstallmentApplicationObjects " +
        "WHERE Id = :AppId";

    return DatabaseApi.querySingleStringOrDefault(sql, CollectionUtils.mapOf("AppId", appId), null);
  }

  private static Date getStartDate(Date endDate) {
    Calendar c = Calendar.getInstance();
    c.setTime(endDate);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);

    return c.getTime();
  }
}
