package jma.handlers;

import jma.dataservice.PhoneUtils;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckFirstContactMobileOn3rdPartyHandler extends CheckMobileOn3rdPartyHandler {
  @Override
  protected String getPhoneNumber(String appId) {
    return PhoneUtils.getFirstContactMobile(appId);
  }

  @Override
  protected String getProvinceVariable() {
    return AppDerivativeVariableNames.FIRST_CONTACT_MOBILE_PROVINCE;
  }

  @Override
  protected String getCityVariable() {
    return AppDerivativeVariableNames.FIRST_CONTACT_MOBILE_CITY;
  }

  @Override
  protected String getSupplierVariable() {
    return AppDerivativeVariableNames.FIRST_CONTACT_MOBILE_SUPPLIER;
  }
}
