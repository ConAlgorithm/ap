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

public class CheckCompanyNameOtherUserReferenceHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    int reference = getReference(appId);
    AppDerivativeVariableManager.addVariables(
        new AppDerivativeVariable(
            appId,
            AppDerivativeVariableNames.COMPANY_NAME_OTHER_USER_REFERENCE,
            reference));
  }

  private static int getReference(String appId) {
	 //之前慢sql，现在拆分为如下两个sql，最终结果sql1-sql2
    /*String sql =
        "SELECT COUNT (DISTINCT A1.UserId) " +
        "FROM InstallmentApplicationObjects A1, JobInfoObjects J1 " +
        "WHERE A1.Id = J1.ApplicationId " +
        "  AND J1.CompanyName = :CompanyName " +
        "  AND A1.UserId != :UserId " +
        "  AND NOT EXISTS ( " +
        "      SELECT A2.Id " +
        "      FROM InstallmentApplicationObjects A2, JobInfoObjects J2 " +
        "      WHERE A2.Id = J2.ApplicationId " +
        "          AND A2.UserId = A1.UserId " +
        "          AND J2.Id != J1.Id " +
        "          AND DATEDIFF (SS, J1.LastModified, J2.LastModified) > 0)";  // J2 after J1
*/
    Map<String, ?> params = CollectionUtils.mapOf(
        "CompanyName", getCompanyName(appId),
        "UserId", DatabaseUtils.getUserIdBy(appId));

    String sql1="SELECT COUNT(DISTINCT A1.UserId) FROM InstallmentApplicationObjects A1 INNER JOIN JobInfoObjects J1 ON A1.Id = J1.ApplicationId AND J1.CompanyName = :CompanyName AND A1.UserId != :UserId;";
    	
    String sql2="SELECT COUNT(DISTINCT A2.UserId) FROM InstallmentApplicationObjects A2, JobInfoObjects J2, (SELECT A1.UserId, J1.Id, J1.LastModified FROM InstallmentApplicationObjects A1, JobInfoObjects J1 WHERE A1.Id = J1.ApplicationId AND J1.CompanyName = :CompanyName AND A1.UserId != :UserId ) AJ1 WHERE A2.Id = J2.ApplicationId AND A2.UserId = AJ1.UserId AND J2.Id != AJ1.Id AND J2.CompanyName != :CompanyName AND J2.LastModified > AJ1.LastModified;";
    
    int differ=DatabaseApi.querySingleInteger(sql1, params)-DatabaseApi.querySingleInteger(sql2, params);
    
    return differ;
  }

  private static String getCompanyName(String appId) {
    String sql =
        "SELECT CompanyName " +
        "FROM JobInfoObjects " +
        "WHERE ApplicationId = :AppId " +
        "ORDER BY LastModified DESC";
    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }
}
