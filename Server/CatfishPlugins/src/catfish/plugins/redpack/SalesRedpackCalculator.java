package catfish.plugins.redpack;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import catfish.base.StartupConfig;
import catfish.base.business.dao.*;
import catfish.base.business.object.*;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by zhaosq on 9/3/2015.
 */
public class SalesRedpackCalculator implements IWeixinRedpackCalculator {
	private static final int factorType = 1;
	private String activityId;

    @Override
    public BigDecimal calculate(String appId) {
		ActivityFactorObject activity = getActivityFactor(appId);
		if (activity == null) {
			Logger.get().warn("not any activity factor found for appId "+appId);
			return null;
		}
		if (activity != null && activity.MaxReward>0) {
			Logger.get().info("activity Id : "+activity.Id);
			Random random = new Random();
			double reward;
			double delta;

			this.activityId = activity.Id;

			double distinct = Double.parseDouble(StartupConfig.get("catfish.plugin.rpDistinct", "0.8"));
			double factor = Double.parseDouble(StartupConfig.get("catfish.plugin.rpFactor", "0.3"));

			if (random.nextDouble()>distinct) {
				delta = activity.MaxReward - activity.MinReward;
			} else {
				delta = (activity.MaxReward - activity.MinReward)*factor;
			}

			reward = activity.MinReward + delta*random.nextDouble();

			if (activity.Offset > 0) {
				if (reward > activity.AvgReward) {
					reward -= activity.Offset/5;
				} else {
					reward -= activity.Offset/10;
				}
			}

			if (reward < activity.MinReward) {
				reward = activity.MinReward + random.nextDouble();
			}
			if (reward > activity.MaxReward) {
				reward = activity.MaxReward - random.nextDouble();
			}
			BigDecimal redpack = new BigDecimal(reward).setScale(2, BigDecimal.ROUND_HALF_UP);
//			ActivityWeixinRedPackDao.updateOffset(activity.Id, (redpack.doubleValue() - activity.AvgReward));
			ActivityFactorDao.updateOffset(activity.Id, (redpack.doubleValue() - activity.AvgReward));

			return redpack;
		}

		return  null;
    }

	@Override
	public String getActivityId() {
		return activityId;
	}

	public ActivityFactorObject getActivityFactor(String appId) {
		ActivityFactorObject afObj = null;
		try {
			// Get InstallmentApplication Info
			InstallmentApplicationObject appObj = InstallmentApplicationDao.getApplicationInfoById(appId);
			String productId = getProductId(appId);
			if (productId == null) {
				return  null;
			}
			// Find S1 first
			MerchantUserObject s1 = MerchantUserDao.getMerchantUserbyAppId(appId);
			afObj = ActivityFactorDao.getByFactorAndSales(factorType, productId, s1.Id);
			if (afObj != null) {
				return  afObj;
			}
			// Find POS
			MerchantStoreObject pos = MerchantStoreDao.getMerchantStoreById(appObj.MerchantStoreId);
			afObj = ActivityFactorDao.getByFactorAndSales(factorType, productId, pos.Id);
			if (afObj != null) {
				return  afObj;
			}
			// Find Seller
			afObj = ActivityFactorDao.getByFactorAndSales(factorType, productId, pos.MerchantCompanyId);
			if (afObj != null) {
				return  afObj;
			}
			// Find D3
			POSDOrgRelationObject pDorgRel = POSDOrgRelationDao.getPOSDOrgRelByPosId(pos.Id);
			DealerUserObject d3 = DealerUserDao.getD3ByD1NodeId(pDorgRel.OrgNodeId);
			afObj = ActivityFactorDao.getByFactorAndSales(factorType, productId, d3.Id);
			if (afObj != null) {
				return  afObj;
			}
			afObj = ActivityFactorDao.getDefaultByFactorId(factorType, productId);
			if (afObj != null) {
				return  afObj;
			}
			afObj = ActivityFactorDao.getDefaultByFactorType(factorType);
		} catch (Exception e) {
			Logger.get().error(e);
		}
		return afObj;
	}

    public static String getProductId(String appId) {
		BigDecimal defaultPenalty = new BigDecimal(5).setScale(2);
		Map<String, String> body = new HashMap<String, String>();
		String appServiceHost = StartupConfig.get("catfish.service.application.host");
		body.put("AppId", appId);
		String uri = appServiceHost + "/application/"+appId;
      	String response = HttpClientApi.get(uri);
      	if (response == null) {
        	Logger.get().error("call product service failed. uri: " + uri);
        	return null;
      	}
		Logger.get().info("res: " + response);
		Map<String, String> appInfo = new Gson().fromJson(response, new TypeToken<HashMap<String, String>>() {
		}.getType());
		Logger.get().info(appInfo);
		String productId = appInfo.get("productId");

		return productId;
	}
}
