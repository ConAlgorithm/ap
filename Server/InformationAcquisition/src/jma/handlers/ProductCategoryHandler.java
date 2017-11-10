package jma.handlers;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.httpclient.HttpClientApi;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;
import jma.models.RawDataVariableNames;
import net.sf.json.JSONObject;
/**
 * PSL获取用户申请商品标志
 * @author yeyb
 * @date 2017年8月24日
 */
public class ProductCategoryHandler extends NonBlockingJobHandler{
	static final String grasscarpHost = StartupConfig.get("catfish.grasscarp.host");
	@Override
	public void execute(String appId) throws RetryRequiredException {
		Logger.get().info("ProductCategoryHandler execute appId:" + appId);
		String productCategory=getProductCategory(appId);
		if(productCategory==null){
			Logger.get().info("failed to execute ProductCategoryHandler,because has not productCategory,appId:"+ appId);
			return ;
		}
		try {
		RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.PRODUCT_CATEGORY,productCategory));
		
		//写mongo
        AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        		.isNotNullAdd(DerivativeVariableNames.PRODUCT_CATEGORY,productCategory)
        		.build());
		} catch (Exception e) {
            Logger.get().warn(String.format("ProductCategoryHandler exception occurred!appId=%s", appId), e);
            throw new RetryRequiredException();
        }
	}
	
	//通过appId获取商品分类
	public String getProductCategory(String appId){
		try {
			if("".equals(grasscarpHost) || grasscarpHost==null){
	            Logger.get().warn("grasscarpHost is null,please add grasscarpHost in configuration file ! ");
	            return null;
	        }
			Logger.get().info("begin to get productCategory form grasscarp,appId=="+appId);
			//1.先通过appid获取商品id
			String applicationDetailUrl=grasscarpHost + "/application/"+appId+"/detail";
			JSONObject fromObject =JSONObject.fromObject(HttpClientApi.get(applicationDetailUrl));
			Logger.get().info("infos get commodityId from grasscarp,appId:"+appId+",fromObject:"+fromObject);
			if(fromObject==null||(!fromObject.has("commodityId"))){
				Logger.get().info("failed get commodityId from grasscarp,appId:"+appId+",fromObject:"+fromObject);
				return null;
			}
			String commodityId =fromObject.getString("commodityId");
			if(commodityId==null){
				Logger.get().info("failed get commodityId from grasscarp,appId:"+appId);
				return null;
			}
			//2.根据商品id商品groupName
			String producetUrl=grasscarpHost + "/product/"+commodityId+"/commodityInfo";
			String productString = HttpClientApi.get(producetUrl);
			if("".equals(productString)||productString==null){
				Logger.get().info("failed get commodityId from grasscarp,appId:"+appId+",null productString:"+productString);
				return null;
			}
			JSONObject fromObject2 = JSONObject.fromObject(productString);
			if(!fromObject2.has("groupName")){
				Logger.get().info("has no key groupName from grasscarp-fromObject2,appId:"+appId+",fromObject2:"+fromObject2);
				return null;
			}
			String productCategory = fromObject2.getString("groupName");
			if(productCategory==null){
				Logger.get().info("failed get productCategory from grasscarp,appId:"+appId+",commodityId:"+commodityId);
				return null;
			}
			Logger.get().info("success get productCategory from grasscarp,appId:"+appId+",productCategory:"+productCategory);
			return productCategory;
		} catch (Exception e) {
			Logger.get().warn(String.format("exception to get productCategory from grasscarp!appId=%s, grasscarpHost=%s", appId, grasscarpHost), e);
			return null;
		}
	}

}
