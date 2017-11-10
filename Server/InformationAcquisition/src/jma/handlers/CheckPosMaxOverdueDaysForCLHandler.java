package jma.handlers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.httpclient.HttpClientApi;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.models.ResponseModel;
import jma.models.SettlementModel;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

/**
 * function: get pos max voerdue days,and send to rule engine.
 * date : 2016-12-29
 * @author wk
 */
public class CheckPosMaxOverdueDaysForCLHandler extends JobHandler {
    public static String routerdwUrl = StartupConfig.get("risk.router.dw.url");

    @Override
    public void execute(String appId) throws RetryRequiredException {

        int result = 0;

        String url = "can't get pos latest appId to concat url !";
        try {
            String userId = CLApplicationResourceFactory.getUserId(appId);

            String latestPosAppId = UserResourceFactory.getLatestPosAppId(userId);
            if(latestPosAppId == null){
            	return;
            }
            result = this.getOverdueDays(latestPosAppId);
            Logger.get().info(
                "get max overdue days:" + result + ", url:" + url);

            AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId,
                AppDerivativeVariableNames.POS_MAX_OVERDUE_DAYS, result));
        } catch (Exception e) {
            Logger.get().warn("get max overdue days failed, will not add derivative variable,"
                              + "use the default value ! url:" + url,
                e);
        }
    }

    protected int getOverdueDays(String appId) {
        if ("".equals(routerdwUrl) || routerdwUrl == null) {
            Logger.get().warn("routerdwUrl is null,please add routerdwUrl in configuration file ! ");
            return 0;
        }
        try {
            //调接口获取数据
            String json = HttpClientApi.get(routerdwUrl + "/settlement/" + appId);
            ResponseModel<SettlementModel> response = new Gson().fromJson(json, new TypeToken<ResponseModel<SettlementModel>>() {
            }.getType());
            if (response != null && response.getCode() == 0) {
                Logger.get().info("getSettlementModel is success ! response is " + new Gson().toJson(response));
                SettlementModel model = response.getData();
                if (model != null) {
                    return model.getAppMaxDelayedDays();
                }
            }
        } catch (Exception e) {
            Logger.get().error("getOverdueDays function error,appId is " + appId, e);
        }
        return 0;

    }
    
    
}