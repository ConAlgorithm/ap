package jma.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.handlers.preprocess.model.CalCulateImgSimilarityRequestModel;
import jma.models.DataSourceResponseBase;
import jma.models.YTDataSourceResponse;
import jma.util.DSPApiUtils;
import jma.util.GrasscarpClient;
/**
 * 
 * @ClassName: CalculateImgSimilarityBaseHandler 
 * @Description: 依图接口计算身份证、头像照相似度
 * @author: zhangll
 * @date: 2016年7月11日 下午4:05:53
 */
public abstract class CalculateImgSimilarityBaseHandler extends JobHandler{
    protected final String url = StartupConfig.get("dsp.api.resource.yt");
    
    protected CalCulateImgSimilarityRequestModel getModel(String appId) throws RetryRequiredException{
        CalCulateImgSimilarityRequestModel requestModel = this.getIdInfoAndMobileByAppId(appId);
        
        String idPhoto = GrasscarpClient.getIdPhotoKey(appId);
        String headPhoto = GrasscarpClient.getUserHeadPhotoKey(appId);
        
        if(idPhoto == null || headPhoto == null){
            Logger.get().info(String.format("photo is null.idPhoto=%s, headPhoto=%s", idPhoto, headPhoto));
            throw new RetryRequiredException();
        }
        requestModel.setIdcardImage(idPhoto);
        requestModel.setQueryImage(headPhoto);
        
        return requestModel;
    }
    /**
     * 
     * @Title: getIdInfoAndMobileByAppId 
     * @Description: 根据appId获取 用户身份证、姓名 、电话
     * @param appId
     * @return
     * @return: CalCulateImgSimilarityRequestModel
     */
    protected CalCulateImgSimilarityRequestModel getIdInfoAndMobileByAppId(String appId){
        CalCulateImgSimilarityRequestModel requestModel = new CalCulateImgSimilarityRequestModel();
        EndUserExtensionObject user = new EndUserExtentionDao(appId).getSingle();
        String mobile = PhoneUtils.getUserMobile(appId);
        requestModel.setIdNo(user.IdNumber);
        requestModel.setName(user.IdName);
        requestModel.setMobile(mobile);
        return requestModel;
    }
    protected void writeDB(DataSourceResponseBase<YTDataSourceResponse> response, String appId){
        List<YTDataSourceResponse> data = response.getData();
        if(data == null || data.size() == 0){
            Logger.get().info("response data is null.");
            return ;
        }
        YTDataSourceResponse ytResponse = data.get(0);
        AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            .add(AppDerivativeVariableNames.YT_Similarity, ytResponse.getX_YT_Similarity())
            .add(AppDerivativeVariableNames.YT_SimilarityQueryDatabase, ytResponse.getX_YT_SimilarityQueryDatabase())
            .add(AppDerivativeVariableNames.YT_SimilarityQueryIdcard, ytResponse.getX_YT_SimilarityQueryIdcard())
            .add(AppDerivativeVariableNames.YT_SimilarityIdcardDatabase, ytResponse.getX_YT_SimilarityIdcardDatabase())
            .add(AppDerivativeVariableNames.YT_IsPass, ytResponse.getX_YT_IsPass())
            .build());
    }
    
    @Override
    public void execute(String appId) throws RetryRequiredException {
        CalCulateImgSimilarityRequestModel requestModel = getModel(appId);
        try {
            @SuppressWarnings("unchecked")
            //请求数据转换
            Map<String,Object> param = BeanUtils.describe(requestModel);
            // 调用依图接口
            DataSourceResponseBase<YTDataSourceResponse> res = DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<YTDataSourceResponse>>(){}.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            //写DB
            writeDB(res, appId);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Logger.get().warn(String.format("failed changing Object to Map. requestModelObject=%s", requestModel.toString()));
            throw new RetryRequiredException();
        }catch (Exception re){
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, requestModel.toString()));
            throw new RetryRequiredException();
        }
        
    }
}
