package jma.handlers.preprocess.pos;

import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.EndUserExtensionObject;
import jma.handlers.preprocess.IPreprocessor;
import jma.handlers.preprocess.model.CheckUserCreditOn3rdPartyPreModel;

public class CheckUserCreditOn3rdPartyPreprocessor implements IPreprocessor<CheckUserCreditOn3rdPartyPreModel> {

  @Override
  public CheckUserCreditOn3rdPartyPreModel getPreModel(String appId) {
    EndUserExtensionObject user = new EndUserExtentionDao(appId).getSingle();
    return new CheckUserCreditOn3rdPartyPreModel(user.IdName, user.IdNumber);
  }

}
