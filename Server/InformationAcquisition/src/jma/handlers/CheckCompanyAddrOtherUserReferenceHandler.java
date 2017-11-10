package jma.handlers;

import java.util.Map;

import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckCompanyAddrOtherUserReferenceHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    int reference=getReference(appId);
    AppDerivativeVariableManager.addVariables(
        new AppDerivativeVariable(
            appId,
            AppDerivativeVariableNames.COMPANY_ADDR_OTHER_USER_REFERENCE,
            reference));
  }

  private static int getReference(String appId) {
    String sql =
        "SELECT COUNT (DISTINCT A1.UserId) " +
        "FROM InstallmentApplicationObjects A1, JobInfoObjects J1 " +
        "WHERE A1.Id = J1.ApplicationId " +
        "  AND J1.CompanyAddress = :CompanyAddr " +
        "  AND A1.UserId != :UserId " +
        "  AND NOT EXISTS ( " +
        "      SELECT A2.Id " +
        "      FROM InstallmentApplicationObjects A2, JobInfoObjects J2 " +
        "      WHERE A2.Id = J2.ApplicationId " +
        "          AND A2.UserId = A1.UserId " +
        "          AND J2.Id != J1.Id " +
        "          AND DATEDIFF (SS, J1.LastModified, J2.LastModified) > 0)";  // J2 after J1

    Map<String, ?> params = CollectionUtils.mapOf(
        "CompanyAddr", getCompanyAddr(appId),
        "UserId", DatabaseUtils.getUserIdBy(appId));

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private static String getCompanyAddr(String appId) {
    String sql =
        "SELECT CompanyAddress " +
        "FROM JobInfoObjects " +
        "WHERE ApplicationId = :AppId " +
        "ORDER BY LastModified DESC";
    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }
}
