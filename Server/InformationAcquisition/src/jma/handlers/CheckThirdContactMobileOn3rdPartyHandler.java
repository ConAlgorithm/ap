package jma.handlers;

import jma.dataservice.PhoneUtils;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckThirdContactMobileOn3rdPartyHandler extends CheckMobileOn3rdPartyHandler {

  @Override
  protected String getPhoneNumber(String appId) {
    return PhoneUtils.getThirdContactMobile(appId);
  }

  @Override
  protected String getProvinceVariable() {
    return AppDerivativeVariableNames.THIRD_CONTACT_MOBILE_PROVINCE;
  }

  @Override
  protected String getCityVariable() {
    return AppDerivativeVariableNames.THIRD_CONTACT_MOBILE_CITY;
  }

  @Override
  protected String getSupplierVariable() {
    return AppDerivativeVariableNames.THIRD_CONTACT_MOBILE_SUPPLIER;
  }

}
