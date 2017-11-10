package jma.handlers;

import jma.dataservice.PhoneUtils;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckUserMobileOn3rdPartyHandler extends CheckMobileOn3rdPartyHandler {
  @Override
  protected String getPhoneNumber(String appId) {
    return PhoneUtils.getUserMobile(appId);
  }

  @Override
  protected String getProvinceVariable() {
    return AppDerivativeVariableNames.USER_MOBILE_PROVINCE;
  }

  @Override
  protected String getCityVariable() {
    return AppDerivativeVariableNames.USER_MOBILE_CITY;
  }

  @Override
  protected String getSupplierVariable() {
    return AppDerivativeVariableNames.USER_MOBILE_SUPPLIER;
  }

  @Override
  protected int getMobileType() {
    return USER_MOBILE;
  }
}
