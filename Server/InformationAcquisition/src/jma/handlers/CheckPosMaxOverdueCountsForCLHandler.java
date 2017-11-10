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

import jma.models.OverDueInfoModel;
import jma.models.ResponseModel;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

/**
 * function: get pos max voerdue counts,and send to rule engine.
 * date : 2016-12-27
 * @author wk
 */
public class CheckPosMaxOverdueCountsForCLHandler extends JobHandler {
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
            result = this.getOverdueCount(latestPosAppId);
            Logger.get().info("get max overdue counts:" + result + ", url:" + url);

            AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId,
                AppDerivativeVariableNames.POS_MAX_OVERDUE_COUNTS, result));
        } catch (Exception e) {
            Logger.get().warn("get max overdue counts failed, will not add derivative variable, "
                              + "use the default value! url:" + url,
                e);
        }

    }

    protected int getOverdueCount(String appId) {
        if ("".equals(routerdwUrl) || routerdwUrl == null) {
            Logger.get().warn("routerdwUrl is null,please add routerdwUrl in configuration file ! ");
            return 0;
        }
        try {
            String json = HttpClientApi.get(routerdwUrl + "/overdueinfo/" + appId);
            ResponseModel<OverDueInfoModel> response = new Gson().fromJson(json, new TypeToken<ResponseModel<OverDueInfoModel>>() {
            }.getType());
            if (response != null && response.getCode() == 0) {
                Logger.get().info("getOverdueCount is success ! response is " + new Gson().toJson(response));
                OverDueInfoModel model = response.getData();
                if (model != null) {
                    return model.getM3();
                }
            }
        } catch (Exception e) {
            Logger.get().error("getOverdueCount function error,appId is " + appId, e);
        }
        return 0;

    }
    

}
