package jma.handlers;

import jma.dataservice.PhoneUtils;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckCompanyPhoneOn3rdPartyHandler extends CheckMobileOn3rdPartyHandler {

  @Override
  protected String getPhoneNumber(String appId) {
    return PhoneUtils.getCompanyTel(appId);
  }

  @Override
  protected String getProvinceVariable() {
    return AppDerivativeVariableNames.COMPANY_PHONE_PROVINCE;
  }

  @Override
  protected String getCityVariable() {
    return AppDerivativeVariableNames.COMPANY_PHONE_CITY;
  }

  @Override
  protected String getSupplierVariable() {
    return AppDerivativeVariableNames.COMPANY_PHONE_SUPPLIER;
  }

}
