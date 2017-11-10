package jma.handlers;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.handlers.preprocess.PreprocessorFactory;
import jma.handlers.preprocess.model.CheckUserCreditOnTdPreModel;
import thirdparty.td.*;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;

import java.util.List;

public class CheckUserCreditOnTdHandler extends NonBlockingJobHandler {

  private CheckUserCreditOnTdPreModel preModel;

  @Override
  public void preprocess() {
    this.preModel = PreprocessorFactory
        .getProcessor(channel, CheckUserCreditOnTdPreModel.class)
        .getPreModel(appId);
  }

  @Override
  public void execute(String appId) throws RetryRequiredException {
	  if(DynamicConfig.read("TDSwitch",JobHandlerSwitch.Off.getValue())
				.equals(JobHandlerSwitch.Off.getValue())) 
	  {
		  return;
	  }
    Response response = TdApi.query(preModel.userName, preModel.userIdNumber, preModel.userMobile);

    RawDataStorageManager.addRawDatas(
        new RawData(appId, RawDataVariableNames.TD_UNITED_RAW_DATA, response.getRawResponse()));

    if (!response.getHit_rules().isEmpty()) {
      AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
      try {
        for (Rule rule : response.getHit_rules()) {
          builder.add(AppDerivativeVariableNames.TD_RULE_PREFIX + rule.getId(), true);
        }
        String seqId = response.getSeq_id();
        // wait td generating data for seqId
        if (seqId != null && ! seqId.isEmpty()) {
          Thread.sleep(2000);
          DetailResponse detailResponse = TdApi.queryDetailsV2(seqId);
          List<RuleDetailModel> details = TdApi.analyzePolicy(detailResponse);

          RawDataStorageManager.addRawDatas(
                  new RawData(appId, RawDataVariableNames.TD_DETAIL_RAW_DATA, detailResponse.getRawResponse()));
          for (RuleDetailModel detail : details) {
            String varName = TdApi.TdVariableMap.get(detail.getDescription());
            if (varName == null) {
              continue;
            }
            builder.add(varName, detail.getCalculateResult()+detail.getCount());
          }
        }

      } catch (Exception e) {
        Logger.get().error(String.format("CheckUserCreditOnTd faild ! appId:%s",appId),e);
      } finally {
        AppDerivativeVariableManager.addVariables(builder.build());
      }
    }

  }
}
