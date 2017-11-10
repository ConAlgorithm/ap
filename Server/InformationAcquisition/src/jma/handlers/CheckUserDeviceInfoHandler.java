package jma.handlers;

import java.math.BigDecimal;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.httpclient.HttpClientApi;
import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseUtils;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;
import jma.models.DeviceInfo;
import jma.models.MongoResponseModel;
import jma.models.RawDataVariableNames;
/**
 * 获取用户设备信息
 * @author yeyb
 * @date 2017年9月19日
 */
public class CheckUserDeviceInfoHandler extends NonBlockingJobHandler{
	
	public static String mongoUrl=StartupConfig.get("risk.persistence.mongoUrl");
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("DeviceInfoSwitch")){
			return;
		}
		Logger.get().info("CheckUserDeviceInfoHandler execute appId:" + appId);
		String url="";
		try {
			String userId = DatabaseUtils.getUserIdBy(appId);
			url=String.format("%s/deviceInfo/%s", mongoUrl, userId);
	    	String result = HttpClientApi.get(url);
	    	Logger.get().info("responseResult from mongo-persistence,appId:"+appId+",result:"+result);
	    	if(result == null || result.isEmpty()) {
	    		 Logger.get().info("get null responseResult from mongo-persistence,appId:"+appId);
	    		return ;
	    	}
	    	Gson gson = new Gson();
	    	MongoResponseModel<List<DeviceInfo>> deviceInfos = gson.fromJson(result, new TypeToken<MongoResponseModel<List<DeviceInfo>>>() {
	         }.getType());
			if(deviceInfos==null || deviceInfos.getCode()!=1){
				Logger.get().info("failed get  deviceInfo from mongo-persistence,appId:"+appId);
				return ;
			}
			List<DeviceInfo> infoList = deviceInfos.getData();
			if(infoList==null || infoList.size()==0){
				Logger.get().info("get null deviceInfoList from mongo-persistence,appId:"+appId);
				return ;
			}
			DeviceInfo model = infoList.get(infoList.size()-1);
			RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.DEVICE_INFOS,new Gson().toJson(model)));
			
			//写入mongo
			AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            		.add(DerivativeVariableNames.DEVICEINFO_LATITUDE,new BigDecimal(model.getLatitude()))
            		.add(DerivativeVariableNames.DEVICEINFO_LOGITUDE,new BigDecimal(model.getLongitude()))
            		.isNotNullAdd(DerivativeVariableNames.DEVICEINFO_ID,model.getOpenUUID()==null?model.getUuid():model.getOpenUUID())
            		.isNotNullAdd(DerivativeVariableNames.DEVICEINFO_NAME,model.getMobileType())
            		.build());

		} catch (Exception e) {
			Logger.get().warn(String.format("exception occurred!appId=%s, url=%s", appId, url), e);
            throw new RetryRequiredException();
        }
	}
}
