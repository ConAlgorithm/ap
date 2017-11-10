package jma.handlers;

import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.handlers.preprocess.PreprocessorFactory;
import jma.handlers.preprocess.model.CheckUserCreditOn3rdPartyPreModel;
import thirdparty.qhzx.DomainResult;
import thirdparty.qhzx.QhzxApi;
import catfish.base.DynamicConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;

public class CheckUserCreditOn3rdPartyHandler extends NonBlockingJobHandler {

  private CheckUserCreditOn3rdPartyPreModel preModel;

  @Override
  public void preprocess() {
    this.preModel = PreprocessorFactory
        .getProcessor(channel, CheckUserCreditOn3rdPartyPreModel.class)
        .getPreModel(appId);
  }

  @Override
  public void execute(String appId) throws RetryRequiredException {
    if(DynamicConfig.read(
        isNewQhzxQuery() ? "QHZXCheckSwitchNew" : "QHZXCheckSwitch",
        JobHandlerSwitch.Off.getValue()
    ).equals(JobHandlerSwitch.Off.getValue())) {
      return;
    }

    DomainResult result = QhzxApi.query(appId, preModel.userIdNumber, preModel.userName, isNewQhzxQuery());

    RawDataStorageManager.addRawDatas(
        new RawData(appId, RawDataVariableNames.QHZX_UNITED_RAW_DATA, result.getRawResult()));

    AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
    if (result.isHasBlacklist()) {
      builder.add(AppDerivativeVariableNames.QHZX_HASBLACKLIST, true);
    }
    if (result.isHasBeenExecuted()) {
      builder.add(AppDerivativeVariableNames.QHZX_HASBEENEXECUTED, true);
    }
    if (result.isHasBeenBrokenPromise()) {
      builder.add(AppDerivativeVariableNames.QHZX_BREAKPROMISE, true);
    }
    if (result.isHasBeenBroeknPromiseAndExecuted()) {
      builder.add(AppDerivativeVariableNames.QHZX_BREAKPROMISEBEENEXECUTED, true);
    }
    if (result.getLatestBuildTime() != null) {
      builder.add(
          AppDerivativeVariableNames.QHZX_DATA_LATEST_BUILDTIME, result.getLatestBuildTime());
    }
    if (result.getMaxOverdueGrade() > 0) {
      builder.add(AppDerivativeVariableNames.QHZX_MAXIMIZEOVERDUEDAY, result.getMaxOverdueGrade());
    }
    if (result.getMaxMoneyBound() > 0) {
      builder.add(AppDerivativeVariableNames.QHZX_MAXIMIZEMONEYBOUND, result.getMaxMoneyBound());
    }

    //v2.0
    if (result.isHasCreditOverdueRisk()) {
        builder.add(AppDerivativeVariableNames.QHZX_HAS_CREDIT_OVERDUE_RISK, true);
    }
    if (result.isHasAdministrationNegativeRisk()) {
        builder.add(AppDerivativeVariableNames.QHZX_HAS_ADMINISTRATION_NEGATIVE_RISK, true);
    }
    if (result.isHasFraudRisk()) {
        builder.add(AppDerivativeVariableNames.QHZX_HAS_FRAUD_RISK, true);
    }
    if (result.getRiskScore() > 0) {
        builder.add(AppDerivativeVariableNames.QHZX_RISK_SCORE, result.getRiskScore());
    }
    if (result.isHasSeriousTrafficViolationRisk()) {
        builder.add(AppDerivativeVariableNames.QHZX_HAS_SERIOUS_TRAFFIC_VIOLATION_RISK, true);
    }
    if (result.isHasMobileFraudRisk()) {
        builder.add(AppDerivativeVariableNames.QHZX_HAS_MOBILE_FRAUD_RISK, true);
    }
    if (result.isHasBankCardFraudRisk()) {
        builder.add(AppDerivativeVariableNames.QHZX_HAS_BANK_CARD_FRAUD_RISK, true);
    }
    if (result.isHasIdCardFraudRisk()) {
        builder.add(AppDerivativeVariableNames.QHZX_HAS_ID_CARD_FRAUD_RISK, true);
    }
    if (result.isHasIPAddressFraudRisk()) {
        builder.add(AppDerivativeVariableNames.QHZX_HAS_IP_ADDRESS_FRAUD_RISK, true);
    }
    if (null != result.getDataStatus()) {
        builder.add(AppDerivativeVariableNames.QHZX_DATA_STATUS, result.getDataStatus());
    }


    if (!builder.build().isEmpty()) {
      AppDerivativeVariableManager.addVariables(builder.build());
    }
  }

  protected boolean isNewQhzxQuery(){
	  return false;
  }

	public CheckUserCreditOn3rdPartyPreModel getPreModel() {
		return preModel;
	}

	public void setPreModel(CheckUserCreditOn3rdPartyPreModel preModel) {
		this.preModel = preModel;
	}
}
