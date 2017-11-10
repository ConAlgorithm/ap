package jma.handlers;

import jma.JobV2Handler;
import jma.RetryRequiredException;
import catfish.base.business.dao.InstallmentApplicationTagDao;

public class SetImageUploadFirstStageUnReadyHandler extends JobV2Handler {
  @Override
  public void execute(String appId) throws RetryRequiredException {
    InstallmentApplicationTagDao.setFirstStageStatus(appId, false);
  }
}
