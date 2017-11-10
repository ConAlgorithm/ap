package jma.handlers;

import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckIsSurnameOfFatherAndSonSameHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String sql =
        "SELECT C.Name " +
        "FROM ContactPersonObjects AS C " +
        "WHERE C.AppId = :AppId " +
        "    AND C.Relationship = 6";    // 6: father

    String fatherName = DatabaseApi.querySingleStringOrDefault(
        sql, CollectionUtils.mapOf("AppId", appId), null);

    if (fatherName == null) {
      return;
    }

    String sonSurname = DatabaseUtils.getIdCardNameAndNumber(appId).get(0).substring(0, 1);

    AppDerivativeVariableManager.addVariables(
        new AppDerivativeVariable(
            appId,
            AppDerivativeVariableNames.IS_SURNAME_OF_FATHER_AND_SON_SAME,
            sonSurname.equals(fatherName.substring(0, 1))));
  }
}
