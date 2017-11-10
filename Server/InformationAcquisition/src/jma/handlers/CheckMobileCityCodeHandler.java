package jma.handlers;

import jma.JobHandler;
import jma.RetryRequiredException;
import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckMobileCityCodeHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    AppDerivativeVariableManager.addVariables(
        createVariable(appId, AppDerivativeVariableNames.USER_MOBILE_CITY_CODE, AppDerivativeVariableNames.USER_MOBILE_CITY),
        createVariable(appId, AppDerivativeVariableNames.FIRST_CONTACT_MOBILE_CITY_CODE, AppDerivativeVariableNames.FIRST_CONTACT_MOBILE_CITY),
        createVariable(appId, AppDerivativeVariableNames.SECOND_CONTACT_MOBILE_CITY_CODE, AppDerivativeVariableNames.SECOND_CONTACT_MOBILE_CITY));
  }

  private static AppDerivativeVariable createVariable(
      String appId, String cityCodeVariable, String cityVariable) {
    return new AppDerivativeVariable(
        appId,
        cityCodeVariable,
        getCityCode(AppDerivativeVariableManager.getAsString(appId, cityVariable)));
  }

  private static String getCityCode(String city) {
    if (city.isEmpty()) {
      return "";
    }

    return DatabaseApi.querySingleStringOrDefault(
        "SELECT Code FROM CNAreaObjects WHERE Name LIKE :NameLike",
        CollectionUtils.mapOf("NameLike", city + "%"), "");
  }
}
