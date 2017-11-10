/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;


import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.httpclient.HttpClientApi;
import catfish.cowfish.application.model.ApplicationModel;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;
import jma.resource.CLApplicationResourceFactory;

/**
 * 〈获取Ltv电销详细信息〉
 *
 * @author hwei
 * @version CheckPhoneSaleInfoForCLHandler.java, V1.0 2017年8月25日 下午3:57:48
 */
public class CheckPhoneSaleInfoForCLHandler extends NonBlockingJobHandler{
    public static String ltvPhoneSaleUrl = StartupConfig.get("ltv.phoneSale.url");; 
    @Override
    public void execute(String appId) throws RetryRequiredException {
        ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
        AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
        if(clApp!=null){
            int status = getLTVPhoneSaleStatus(clApp.userId);
            if(status!=0&&status!=1){
                if(status>110&&status<200) {
                    builder.isNotNullAdd(DerivativeVariableNames.LTVCALLSTATUS, 1);
                    if(status==112||status==123){
                        builder.isNotNullAdd(DerivativeVariableNames.LTVCALLREJECT,1); 
                    }else{
                        builder.isNotNullAdd(DerivativeVariableNames.LTVCALLREJECT,0); 
                    }
                }else{
                    builder.isNotNullAdd(DerivativeVariableNames.LTVCALLSTATUS, 0);
                    if(status==202){
                        builder.isNotNullAdd(DerivativeVariableNames.LTVCALLWRONG,1); 
                    }else{
                        builder.isNotNullAdd(DerivativeVariableNames.LTVCALLWRONG,0); 
                    }
                }                
            } else {
                builder.isNotNullAdd(DerivativeVariableNames.LTVCALLSTATUS, -1);
                builder.isNotNullAdd(DerivativeVariableNames.LTVCALLWRONG,-1); 
                builder.isNotNullAdd(DerivativeVariableNames.LTVCALLREJECT,-1);
            }
        }
        if (!builder.build().isEmpty()){
            AppDerivativeVariableManager.addVariables(builder.build());                
        }
        
    }

    
    protected int getLTVPhoneSaleStatus(String userId){
        if ("".equals(ltvPhoneSaleUrl) || ltvPhoneSaleUrl == null) {
            Logger.get().warn("ltvPhoneSaleUrl is null, please add ltvPhoneSaleUrl in configuration file ! ");
            return 0;
        }
        try {
            String json = HttpClientApi.get(ltvPhoneSaleUrl + "/v2/tms/status/" + userId);
            int status = Integer.valueOf(json);
            Logger.get().info("getLTVPhoneSaleStatus result is " + json);
            return status;
        } catch (Exception e) {
            Logger.get().error("getLTVPhoneSaleStatus function error,userId is " + userId, e);
        }
        return 0;
    }
}
