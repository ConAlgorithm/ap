package jma.handlers;

import java.util.Date;
import java.util.Map;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseEnumValues.ApplicationStatus;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.database.DatabaseApi;

public class CheckIdCardApplicationStatisticsHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {

    String IdNumber = DatabaseUtils.getIdCardNumberBy(appId);
    String startedDate = DateTimeUtils.format(DatabaseUtils.getInstallmentStartedOnBy(appId));

    int rejectedTimes = getCountBy(IdNumber, ApplicationStatus.REJECTED, startedDate);
    int approvedTimes = getApprovedTimesBy(IdNumber, startedDate);
    int repayingCount = getCountBy(IdNumber, ApplicationStatus.COMPLETED, startedDate);
    int delayedCount = getCountBy(IdNumber, ApplicationStatus.DELAYED, startedDate);

    AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.ID_CARD_NUMBER_REPAYING_COUNT, repayingCount)
        .add(AppDerivativeVariableNames.ID_CARD_NUMBER_DELAYED_COUNT, delayedCount);
    if (rejectedTimes > 0) {
        Date lastRejectedDate = getLastRejectedDate(IdNumber, startedDate);
        if(lastRejectedDate != null)
           builder.add(AppDerivativeVariableNames.ID_CARD_NUMBER_LAST_REJECTED_DATE, lastRejectedDate);
        else
        	rejectedTimes = 0;
    }
    if (approvedTimes > 0) {
    	Date lastApprovedDate = getLastApprovedDate(IdNumber, startedDate);
    	if(lastApprovedDate != null)
           builder.add(AppDerivativeVariableNames.ID_CARD_NUMBER_LAST_APPROVED_DATE, lastApprovedDate);
    	else
    	   approvedTimes = 0;
    }
    
    builder.add(AppDerivativeVariableNames.ID_CARD_NUMBER_REJECTED_TIMES, rejectedTimes)
    .add(AppDerivativeVariableNames.ID_CARD_NUMBER_APPROVED_TIMES, approvedTimes);
    
    AppDerivativeVariableManager.addVariables(builder.build());
  }

  private static Date getLastRejectedDate(String IdNumber, String endDate) {
    String sql =
        "SELECT TOP 1 I.RejectedOn " +
        "FROM InstallmentApplicationObjects AS I, EndUserExtensionObjects AS E " +
        "WHERE I.Status = :Status " +
        "    AND I.InstallmentStartedOn < :EndDate " +
        "    AND E.IdNumber = :IdNumber " +
        "    AND I.UserId = E.Id " +
        "ORDER BY I.RejectedOn DESC";

    Map<String, ?> params = CollectionUtils.mapOf(
        "Status", ApplicationStatus.REJECTED,
        "EndDate", endDate,
        "IdNumber", IdNumber);
    String result = DatabaseApi.querySingleString(sql, params);
    if(result != null)
        return DateTimeUtils.parse(result);
    return null;
  }

  private static Date getLastApprovedDate(String IdNumber, String endDate) {
    String sql =
        "SELECT TOP 1 I.ApprovedOn " +
        "FROM InstallmentApplicationObjects AS I, EndUserExtensionObjects AS E " +
        "WHERE I.Status >= :StatusApproved " +
        "    AND I.Status != :StatusRejected " +
        "    AND I.InstallmentStartedOn < :EndDate " +
        "    AND E.IdNumber = :IdNumber " +
        "    AND I.UserId = E.Id " +
        "ORDER BY I.ApprovedOn DESC";

    Map<String, ?> params = CollectionUtils.mapOf(
        "StatusApproved", ApplicationStatus.APPROVED,
        "StatusRejected", ApplicationStatus.REJECTED,
        "EndDate", endDate,
        "IdNumber", IdNumber);
    String result = DatabaseApi.querySingleString(sql, params);
    if(result != null)
        return DateTimeUtils.parse(result);
    return null;
  }

  private static int getCountBy(String IdNumber, int status, String endDate) {
    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects AS I, EndUserExtensionObjects AS E " +
        "WHERE I.Status = :Status " +
        "    AND I.InstallmentStartedOn < :EndDate " +
        "    AND E.IdNumber = :IdNumber " +
        "    AND I.UserId = E.Id";

    Map<String, ?> params = CollectionUtils.mapOf(
        "Status", status,
        "EndDate", endDate,
        "IdNumber", IdNumber);

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private static int getApprovedTimesBy(String IdNumber, String endDate) {
    String sql =
        "SELECT COUNT (*) " +
        "FROM InstallmentApplicationObjects AS I, EndUserExtensionObjects AS E " +
        "WHERE I.Status >= :StatusApproved " +
        "    AND I.Status != :StatusRejected " +
        "    AND  I.InstallmentStartedOn < :EndDate " +
        "    AND E.IdNumber = :IdNumber " +
        "    AND I.UserId = E.Id";

    Map<String, ?> params = CollectionUtils.mapOf(
        "StatusApproved", ApplicationStatus.APPROVED,
        "StatusRejected", ApplicationStatus.REJECTED,
        "EndDate", endDate,
        "IdNumber", IdNumber);

    return DatabaseApi.querySingleInteger(sql, params);
  }
}
