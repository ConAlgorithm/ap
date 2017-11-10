package jma.handlers;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseUtils;
import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckAddressApplicantResidenceHandler extends CheckAddressAbstractHandler {

  @Override
  protected String getAddress(String appId) {
    String sql =
        "SELECT LivingAddress " +
        "FROM PersonalInfoObjects " +
        "WHERE AppId = :AppId";

    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }

  @Override
  protected String getCity(String appId) {
    return getApplicantCity(appId);
  }

  @Override
  protected int getAddressType() {
    return RESIDENCE;
  }

  @Override
  protected String getRefId(String appId) {
    return DatabaseUtils.getUserIdBy(appId);
  }

  @Override
  protected void saveAreaInfo(String appId, String areaCode) {
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
      .add(AppDerivativeVariableNames.RESIDENCE_ADDRESS_DISTRICT_INFO_FROM_BAIDU, areaCode)
      .build());
  }
}
