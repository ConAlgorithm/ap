package jma.handlers.preprocess.pdl;

import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.EndUserExtensionObject;
import jma.dataservice.PhoneUtils;
import jma.handlers.preprocess.IPreprocessor;
import jma.handlers.preprocess.model.CheckUserCreditOnTdPreModel;

public class CheckUserCreditOnTdPreprocessor implements IPreprocessor<CheckUserCreditOnTdPreModel> {

  @Override
  public CheckUserCreditOnTdPreModel getPreModel(String appId) {
    EndUserExtensionObject user = new EndUserExtentionDao(appId).getSingle();
    String mobile = PhoneUtils.getUserMobile(appId);
    return new CheckUserCreditOnTdPreModel(user.IdName, user.IdNumber, mobile);
  }

}
