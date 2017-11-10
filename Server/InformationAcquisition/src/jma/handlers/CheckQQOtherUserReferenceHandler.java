package jma.handlers;

import java.util.Map;

import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import catfish.base.CollectionUtils;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.dao.FraudRuleResultDao;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckQQOtherUserReferenceHandler extends JobHandler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    String userQQ = DatabaseUtils.getUserQQ(appId);
    int reference = getQQReference(DatabaseUtils.getIdCardNumberBy(appId), userQQ);
    
    AppDerivativeVariableManager.addVariables(
        new AppDerivativeVariable(
            appId, AppDerivativeVariableNames.QQ_OTHER_USER_REFERENCE, reference));
    
    //int score = reference > 0 ? 100 : 0;
    FraudRuleResultDao.saveFraudDetailResult(appId, "A011", "QQ", reference > 0 ? FraudRuleResultStatus.Fail.getValue() : FraudRuleResultStatus.Pass.getValue());
  }

  private static int getQQReference(String currentUserIdNumber, String userQQ) {
    String sql =
        "SELECT COUNT (DISTINCT EE.IdNumber) " +
        "FROM InstallmentApplicationObjects A1, EndUserExtensionObjects EE, " +
        "    PersonalInfoObjects P1, ContactObjects C1 " +
        "WHERE A1.UserId = EE.Id " +
        "    AND A1.Id = P1.AppId " +
        "    AND P1.QQContactId = C1.Id " +
        "    AND C1.Content = :UserQQ " +
        "    AND EE.IdNumber != :CurrentUserIdNumber ";
        /*"    AND NOT EXISTS ( " +
        "        SELECT A2.Id " +
        "        FROM InstallmentApplicationObjects A2, PersonalInfoObjects P2," +
        "            ContactObjects C2 " +
        "        WHERE A2.Id = P2.AppId " +
        "            AND P2.QQContactId = C2.Id " +
        "            AND A2.UserId = A1.UserId " +
        "            AND C2.Id != C1.Id " +
        "            AND DATEDIFF (SS, C1.LastModified, C2.LastModified) > 0)"; */ 
    	// C2 after C1

    Map<String, ?> params = CollectionUtils.mapOf(
        "CurrentUserIdNumber", currentUserIdNumber,
        "UserQQ", userQQ);

    return DatabaseApi.querySingleInteger(sql, params);
  }
}