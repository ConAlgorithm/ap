package jma.handlers;

import jma.dataservice.PhoneUtils;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckSecondContactMobileOn3rdPartyHandler extends CheckMobileOn3rdPartyHandler {
  @Override
  protected String getPhoneNumber(String appId) {
    return PhoneUtils.getSecondContactMobile(appId);
  }

  @Override
  protected String getProvinceVariable() {
    return AppDerivativeVariableNames.SECOND_CONTACT_MOBILE_PROVINCE;
  }

  @Override
  protected String getCityVariable() {
    return AppDerivativeVariableNames.SECOND_CONTACT_MOBILE_CITY;
  }

  @Override
  protected String getSupplierVariable() {
    return AppDerivativeVariableNames.SECOND_CONTACT_MOBILE_SUPPLIER;
  }
}
