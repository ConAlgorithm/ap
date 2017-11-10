package jma.handlers;

import java.util.Date;
import java.util.List;

import jma.AppDerivativeVariablesBuilder;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.AppDataService;
import jma.dataservice.SalesDataService;
import jma.util.SettlementClient;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckMerchantCompanyApplicationStatisticsHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String merchantStoreId = AppDataService.getMerchantStoreIdBy(appId);
    String companyId = SalesDataService.getMerchantCompanyId(merchantStoreId);
    if (companyId == null) {
      return;
    }

    Date startedOn = AppDataService.getInstallmentStartedOnBy(appId);
    List<String> merchantStoreIds = SalesDataService.getMerchantStoreIds(companyId);

    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.MERCHANT_COMPANY_APPLICATION_HISTORY_APPROVED, AppDataService.getHistoryApproved(merchantStoreIds))
        .add(AppDerivativeVariableNames.MERCHANT_COMPANY_APPLICATION_HISTORY_REJECTED, AppDataService.getHistoryRejected(merchantStoreIds))
        .add(AppDerivativeVariableNames.MERCHANT_COMPANY_APPLICATION_HISTORY_TOTAL, AppDataService.getHistoryTotal(merchantStoreIds))
        .add(AppDerivativeVariableNames.MERCHANT_COMPANY_APPLICATION_HISTORY_DELAYED, SettlementClient.overdueApplicationCount(AppDataService.getHistoryDelayedAppIdList(merchantStoreIds), 4))
        .add(AppDerivativeVariableNames.MERCHANT_COMPANY_APPLICATION_TODAY_APPROVED, AppDataService.getTodayApproved(merchantStoreIds, startedOn))
        .add(AppDerivativeVariableNames.MERCHANT_COMPANY_APPLICATION_TODAY_REJECTED, AppDataService.getTodayRejected(merchantStoreIds, startedOn))
        .add(AppDerivativeVariableNames.MERCHANT_COMPANY_APPLICATION_TODAY_TOTAL, AppDataService.getTodayTotal(merchantStoreIds, startedOn))
        .build());
  }


}
