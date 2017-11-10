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

public class CheckMerchantApplicationStatisticsHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String merchantStoreId = getMerchantStoreIdBy(appId);
    Date installmentStartOn = DatabaseUtils.getInstallmentStartedOnBy(appId);

    AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.MERCHANT_APPLICATION_HISTORY_TOTAL, getApplicationHistoryTotal(merchantStoreId))
        .add(AppDerivativeVariableNames.MERCHANT_APPLICATION_HISTORY_APPROVED, getApplicationHistoryApproved(merchantStoreId))
        .add(AppDerivativeVariableNames.MERCHANT_APPLICATION_HISTORY_REJECTED, getApplicationHistoryRejected(merchantStoreId))
        .add(AppDerivativeVariableNames.MERCHANT_APPLICATION_HISTORY_DELAYED, SettlementClient.overdueApplicationCount(AppDataService.getHistoryDelayedAppIdListByMerchantStoreId(merchantStoreId), 4))
        .add(AppDerivativeVariableNames.MERCHANT_APPLICATION_TODAY_TOTAL,
            getApplicationTodayTotal(merchantStoreId, installmentStartOn))
        .add(AppDerivativeVariableNames.MERCHANT_APPLICATION_TODAY_APPROVED,
            getApplicationTodayApproved(merchantStoreId, installmentStartOn))
        .add(AppDerivativeVariableNames.MERCHANT_APPLICATION_TODAY_REJECTED,
            getApplicationTodayRejected(merchantStoreId, installmentStartOn));

    Date startDate = getCooperationStartTime(merchantStoreId);
    if (startDate != null) {
      builder.add(AppDerivativeVariableNames.MERCHANT_COOPERATION_START_TIME, startDate);
    }

    AppDerivativeVariableManager.addVariables(builder.build());
  }

  private int getApplicationHistoryTotal(String merchantStoreId) {
    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantStoreId = :MerchantStoreId " +
        "    AND Status >= :StatusSubmitted";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantStoreId", merchantStoreId,
        "StatusSubmitted", ApplicationStatus.SUBMITTED);

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private int getApplicationHistoryApproved(String merchantStoreId) {
    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantStoreId = :MerchantStoreId " +
        "    AND Status >= :StatusApproved " +
        "    AND Status != :StatusRejected";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantStoreId", merchantStoreId,
        "StatusApproved", ApplicationStatus.APPROVED,
        "StatusRejected", ApplicationStatus.REJECTED);

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private int getApplicationHistoryRejected(String merchantStoreId) {
    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantStoreId = :MerchantStoreId " +
        "    AND Status = :StatusRejected";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantStoreId", merchantStoreId,
        "StatusRejected", ApplicationStatus.REJECTED);

    return DatabaseApi.querySingleInteger(sql, params);
  }

//  private int getApplicationHistoryDelayed(String merchantStoreId) {
//    // day past due 4
//    Calendar dpd4 = Calendar.getInstance();
//    dpd4.add(Calendar.DAY_OF_MONTH, -4);
//
//    String sql =
//        "SELECT COUNT(*) " +
//        "FROM InstallmentApplicationObjects A " +
//        "WHERE A.Status = :Delayed " +
//        "    AND A.MerchantStoreId = :MerchantStoreId " +
//        "    AND EXISTS ( " +
//        "        SELECT * " +
//        "        FROM InstalmentObjects I " +
//        "        WHERE A.Id = I.AppId " +
//        "            AND I.Outstanding > 0 " +
//        "            AND CONVERT (varchar(30), I.DateDue, 120) <= :Dpd4 )";
//
//    Map<String, ?> params = CollectionUtils.mapOf(
//        "Delayed", ApplicationStatus.DELAYED,
//        "MerchantStoreId", merchantStoreId,
//        "Dpd4", DateTimeUtils.format(dpd4.getTime()));
//
//    return DatabaseApi.querySingleInteger(sql, params);
//  }

  private int getApplicationTodayTotal(String merchantStoreId, Date endDate) {
    Date startDate = getStartDate(endDate);

    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantStoreId = :MerchantStoreId " +
        "    AND Status >= :StatusSubmitted " +
        "    AND InstallmentStartedOn >= :StartDate " +
        "    AND InstallmentStartedOn < :EndDate";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantStoreId", merchantStoreId,
        "StatusSubmitted", ApplicationStatus.SUBMITTED,
        "StartDate", DateTimeUtils.format(startDate),
        "EndDate", DateTimeUtils.format(endDate));

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private int getApplicationTodayApproved(String merchantStoreId, Date endDate) {
    Date startDate = getStartDate(endDate);

    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects " +
        "WHERE MerchantStoreId = :MerchantStoreId " +
        "    AND Status >= :StatusApproved " +
        "    AND Status != :StatusRejected " +
        "    AND InstallmentStartedOn >= :StartDate " +
        "    AND InstallmentStartedOn < :EndDate";

    Map<String, ?> params = CollectionUtils.<String, Object>newMapBuilder()
        .add("MerchantStoreId", merchantStoreId)
        .add("StatusApproved", ApplicationStatus.APPROVED)
        .add("StatusRejected", ApplicationStatus.REJECTED)
        .add("StartDate", DateTimeUtils.format(startDate))
        .add("EndDate", DateTimeUtils.format(endDate))
        .build();

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private int getApplicationTodayRejected(String merchantStoreId, Date endDate) {
    Date startDate = getStartDate(endDate);

    String sql =
        "SELECT COUNT (*) " +
            "FROM InstallmentApplicationObjects " +
            "WHERE MerchantStoreId = :MerchantStoreId " +
            "    AND Status = :StatusRejected " +
            "    AND InstallmentStartedOn >= :StartDate " +
            "    AND InstallmentStartedOn < :EndDate";

    Map<String, ?> params = CollectionUtils.mapOf(
        "MerchantStoreId", merchantStoreId,
        "StatusRejected", ApplicationStatus.REJECTED,
        "StartDate", DateTimeUtils.format(startDate),
        "EndDate", DateTimeUtils.format(endDate));

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private static String getMerchantStoreIdBy(String appId) {
    String sql =
        "SELECT MerchantStoreId " +
        "FROM InstallmentApplicationObjects " +
        "WHERE Id = :AppId";

    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }

  private Date getCooperationStartTime(String merchantStoreId) {
    String sql =
        "SELECT CooperationStartTime " +
        "FROM MerchantStoreObjects " +
        "WHERE Id = :MerchantStoreId";

    Map<String, ?> params = CollectionUtils.mapOf("MerchantStoreId", merchantStoreId);

    String dateString = DatabaseApi.querySingleString(sql, params);
    return dateString == null
        ? null
        : DateTimeUtils.parse(dateString);
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
