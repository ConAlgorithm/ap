package jma.handlers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.handlers.preprocess.PreprocessorFactory;
import jma.handlers.preprocess.model.CheckPhotoOnShangTangPreModel;
import jma.models.DataSourceResponseBase;
import jma.models.DerivativeVariableNames;
import jma.models.ShangTangDSPResponse;
import jma.util.DSPApiUtils;

public class CheckPhotoOnShangTangHandler extends NonBlockingJobHandler{

	protected final String idVerifyUrl = StartupConfig.get("dsp.api.resource.shangtang.idVerify");
	protected final String photoVerifyUrl = StartupConfig.get("dsp.api.resource.shangtang.photoVerify");
	
	private CheckPhotoOnShangTangPreModel model;
	
	@Override
	public void preprocess() {
		this.model = PreprocessorFactory
	            .getProcessor(channel, CheckPhotoOnShangTangPreModel.class).getPreModel(appId);
	}

	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(model == null){
			Logger.get().error("The model of CheckPhotoOnShangTangHandler is null, would retry this job!");
			throw new RetryRequiredException();
		}
		try {
			AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
            // 调用身份证-公安部对比接口
            Map<String, Object> param = toIdVerifyRequest(model, model.getIdcardPhotoUrl());
            DataSourceResponseBase<ShangTangDSPResponse> idPoliceVerifyRes = DSPApiUtils.invokeDspApi(appId, idVerifyUrl, param, new TypeToken<DataSourceResponseBase<ShangTangDSPResponse>>(){}.getType());
            if(isSuccessful(idPoliceVerifyRes, idVerifyUrl, "身份证-公安部对比接口")){
            	buildIdVerifyVariables(builder, idPoliceVerifyRes);
            	buildIdPolicePhotoVerifyVariables(builder, idPoliceVerifyRes);
            }
            // 调用头像照-公安部对比接口
            param = toIdVerifyRequest(model, model.getHeadPhotoUrl());
            DataSourceResponseBase<ShangTangDSPResponse> headPoliceVerifyRes = DSPApiUtils.invokeDspApi(appId, idVerifyUrl, param, new TypeToken<DataSourceResponseBase<ShangTangDSPResponse>>(){}.getType());
            if(isSuccessful(headPoliceVerifyRes, idVerifyUrl, "头像照-公安部对比接口")){
            	buildHeadPolicePhotoVerifyVariables(builder, headPoliceVerifyRes);
            }
            // 调用身份证-头像照对比接口
            param = toPhotoVerifyRequest(model);
            DataSourceResponseBase<ShangTangDSPResponse> idHeadVerifyRes = DSPApiUtils.invokeDspApi(appId, photoVerifyUrl, param, new TypeToken<DataSourceResponseBase<ShangTangDSPResponse>>(){}.getType());
            if(isSuccessful(idHeadVerifyRes, photoVerifyUrl, "身份证-头像照对比接口")){
            	buildIdHeadPhotoVerifyVariables(builder, idHeadVerifyRes);
            }
            Logger.get().info("Variables : " + new Gson().toJson(builder.build()));
            //写DB
            AppDerivativeVariableManager.addVariables(builder.build());    
        } catch (Exception e){
            Logger.get().error(String.format("exception occurred! appId=%s, model=%s", appId, new Gson().toJson(model)), e);
        }
	}
	
	private Map<String, Object> toPhotoVerifyRequest(CheckPhotoOnShangTangPreModel model){
		Map<String, Object> request = Maps.newHashMap();
		request.put("url1", model.getHeadPhotoUrl());
		request.put("url2", model.getIdcardPhotoUrl());
		return request;
	}
	
	private Map<String, Object> toIdVerifyRequest(CheckPhotoOnShangTangPreModel model, String url){
		Map<String, Object> request = Maps.newHashMap();
		request.put("url", url);
		request.put("idNo", model.getIdCardNumber());
		request.put("name", model.getIdCardName());
		return request;
	}
	
	private void buildIdVerifyVariables(AppDerivativeVariablesBuilder builder, DataSourceResponseBase<ShangTangDSPResponse> res){
		getResponse(res).ifPresent(data -> {
			builder.add(DerivativeVariableNames.ST_IS_ID_NAME_MATCH, data.getValidity());
			if(!StringUtils.isEmpty(data.getReason())){
				builder.add(DerivativeVariableNames.ST_POLICE_ERROR_REASON, data.getReason());
			}
		});
	}
	//获取身份证-头像照对比结果
	private void buildIdHeadPhotoVerifyVariables(AppDerivativeVariablesBuilder builder, DataSourceResponseBase<ShangTangDSPResponse> res){
		getResponse(res).ifPresent(data -> {
			Logger.get().info("添加:" + DerivativeVariableNames.ST_ID_HEAD_PHOTO_SIMILARITY + "=" + data.getConfidence());
			builder.isNotNullAdd(DerivativeVariableNames.ST_ID_HEAD_PHOTO_SIMILARITY, data.getConfidence() == null? null: data.getConfidence().toString());
		});
	}
	//获取身份证-公安部照片对比结果
    private void buildIdPolicePhotoVerifyVariables(AppDerivativeVariablesBuilder builder, DataSourceResponseBase<ShangTangDSPResponse> res){
    	getResponse(res).ifPresent(data -> {
			builder.isNotNullAdd(DerivativeVariableNames.ST_ID_POLICE_PHOTO_SIMILARITY, data.getConfidence() == null? null: data.getConfidence().toString());
		});
	}
    //获取头像照-公安部照片对比结果
	private void buildHeadPolicePhotoVerifyVariables(AppDerivativeVariablesBuilder builder, DataSourceResponseBase<ShangTangDSPResponse> res){
		getResponse(res).ifPresent(data -> {
			builder.isNotNullAdd(DerivativeVariableNames.ST_HEAD_POLICE_PHOTO_SIMILARITY, data.getConfidence() == null? null: data.getConfidence().toString());
		});
	}
	
	
	private Optional<ShangTangDSPResponse> getResponse(DataSourceResponseBase<ShangTangDSPResponse> response){
		List<ShangTangDSPResponse> data = response.getData();
        if(data == null || data.size() == 0){
            Logger.get().info("response data is null.");
            return Optional.empty();
        }
        return Optional.of(data.get(0));
	}
	
	private boolean isSuccessful(DataSourceResponseBase<ShangTangDSPResponse> res, String url, String msg){
		if(res.getCode() != 200){
            Logger.get().error(String.format("调用商汤%s失败. url=%s, result=%s", msg, url, new Gson().toJson(res)));
            return false;
        }
		Logger.get().info(String.format("调用商汤%s成功. url=%s, result=%s", msg, url, new Gson().toJson(res)));
		return true;
	}
}
