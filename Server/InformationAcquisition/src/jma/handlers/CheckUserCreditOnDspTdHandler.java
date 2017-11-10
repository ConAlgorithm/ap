package jma.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.handlers.preprocess.PreprocessorFactory;
import jma.handlers.preprocess.model.CheckUserCreditOnTdPreModel;
import jma.models.DataSourceResponseBase;
import jma.models.TongDunModel;
import jma.models.TongDunRequest;
import jma.util.DSPApiUtils;

public class CheckUserCreditOnDspTdHandler extends NonBlockingJobHandler {
  protected final String url = StartupConfig.get("dsp.api.resource.tdv2");
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
	  Gson gson = new Gson();
	  DataSourceResponseBase<TongDunModel> response = null;
	  try {
		  TongDunRequest request = new TongDunRequest(preModel.userName,preModel.userIdNumber, preModel.userMobile);
          //请求数据转换
          Map<String,Object> param = BeanUtils.describe(request);
          // 调用同盾接口
          response = DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<TongDunModel>>(){}.getType());
          if(response.getCode() != 200){
              Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(response)));
              throw new RetryRequiredException();
          }
          
          RawDataStorageManager.addRawDatas(
        	        new RawData(appId, RawDataVariableNames.TD_UNITED_RAW_DATA, gson.toJson(response.getData())));
          //写DB
          writeDB(response, appId);
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
          Logger.get().warn(String.format("failed changing Object to Map. requestModelObject=%s", gson.toJson(response)),e);
          throw new RetryRequiredException();
      }catch (Exception re){
          Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, gson.toJson(response)),re);
          throw new RetryRequiredException();
      }
	  

    }

  protected void writeDB(DataSourceResponseBase<TongDunModel> response, String appId){
      List<TongDunModel> data = response.getData();
      if(data == null || data.size() == 0){
          Logger.get().info("response data is null.");
          return ;
      }
      TongDunModel tdV2Response = data.get(0);
      AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33638, tdV2Response.getX_TD_Rule_33638())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33640, tdV2Response.getX_TD_Rule_33640())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33642, tdV2Response.getX_TD_Rule_33642())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33652, tdV2Response.getX_TD_Rule_33652())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33654, tdV2Response.getX_TD_Rule_33654())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33674, tdV2Response.getX_TD_Rule_33674())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33674_LOAN_AMOUNT, tdV2Response.getX_TD_Rule_33674_LoanAmount())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33674_OUTER_PLATFORM_LOAN_AMOUNT, tdV2Response.getX_TD_Rule_33674_OuterPlatformLoanAmount())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33676, tdV2Response.getX_TD_Rule_33676())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33676_LOAN_AMOUNT, tdV2Response.getX_TD_Rule_33676_LoanAmount())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_33676_OUTER_PLATFORM_LOAN_AMOUNT, tdV2Response.getX_TD_Rule_33676_OuterPlatformLoanAmount())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_57284, tdV2Response.getX_TD_Rule_57284())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_811950, tdV2Response.getX_TD_Rule_811950())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_811952, tdV2Response.getX_TD_Rule_811952())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_811954, tdV2Response.getX_TD_Rule_811954())
          .isNotNullAdd(AppDerivativeVariableNames.TD_RULE_811956, tdV2Response.getX_TD_Rule_811956())
          .build());
  }
 
}
