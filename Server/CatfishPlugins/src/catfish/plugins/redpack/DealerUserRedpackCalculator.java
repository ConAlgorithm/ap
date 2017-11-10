package catfish.plugins.redpack;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.*;
import catfish.base.business.object.*;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by nick on 16/7/21.
 */
public class DealerUserRedpackCalculator implements IWeixinRedpackCalculator {

    private String appServiceHost = StartupConfig.get("catfish.service.application.host");
    // d1红包,用factorType=2来区分
    private static final int factorType = 2;
    private String activityId;

    private static String sql_getD1ForApp =
            "select top 1 d.id from dealeruserapplicationrelationobjects da " +
            "join dealeruserobjects d on d.userid = da.userid " +
            "where da.appid = :appId";

    private static double distinct = Double.parseDouble(StartupConfig.get("catfish.plugin.rpDistinct", "0.8"));
    private static double factor = Double.parseDouble(StartupConfig.get("catfish.plugin.rpFactor", "0.3"));

    @Override
    public String getActivityId() {
        return activityId;
    }

    @Override
    public BigDecimal calculate(String appId) {
        ActivityFactorObject activity = getActivityFactor(appId);
        if (activity == null) {
            Logger.get().warn("not any activity factor found for appId "+appId);
            return null;
        }
        if (activity != null && activity.MaxReward>0) {

            this.activityId = activity.Id;

            Logger.get().info("activity Id : "+activity.Id);
            Random random = new Random();
            double reward;
            double delta;

            // 最大最小值相等
            if ((activity.MaxReward - activity.MinReward) < 0.01) {
                return new BigDecimal(activity.MinReward).setScale(2, BigDecimal.ROUND_HALF_UP);
            }


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
            ActivityFactorDao.updateOffset(activity.Id, (redpack.doubleValue() - activity.AvgReward));

            return redpack;
        }

        return  null;
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
            // Find POS first
            MerchantStoreObject pos = MerchantStoreDao.getMerchantStoreById(appObj.MerchantStoreId);
            afObj = ActivityFactorDao.getByFactorAndSales(factorType, productId, pos.Id);
            if (afObj != null) {
                return  afObj;
            }
            afObj = ActivityFactorDao.getDefaultBySalesId(factorType, pos.Id);
            if (afObj != null) {
                return  afObj;
            }

            // Find D1
            String d1Id = DatabaseApi.querySingleString(sql_getD1ForApp, CollectionUtils.mapOf("appId", appId));
            afObj = ActivityFactorDao.getByFactorAndSales(factorType, productId, d1Id);
            if (afObj != null) {
                return  afObj;
            }
            afObj = ActivityFactorDao.getDefaultBySalesId(factorType, d1Id);
            if (afObj != null) {
                return  afObj;
            }
            // Find Seller
            afObj = ActivityFactorDao.getByFactorAndSales(factorType, productId, pos.MerchantCompanyId);
            if (afObj != null) {
                return  afObj;
            }
            afObj = ActivityFactorDao.getDefaultBySalesId(factorType, pos.MerchantCompanyId);
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
            afObj = ActivityFactorDao.getDefaultBySalesId(factorType, d3.Id);
            if (afObj != null) {
                return  afObj;
            }

            // Find default
//            afObj = ActivityFactorDao.getDefaultByFactorId(factorType, productId);
//            if (afObj != null) {
//                return  afObj;
//            }
//            afObj = ActivityFactorDao.getDefaultByFactorType(factorType);
        } catch (Exception e) {
            Logger.get().error(e);
        }
        return afObj;
    }

    public String getProductId(String appId) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("AppId", appId);
        String uri = appServiceHost + "/application/"+appId;
        String response = HttpClientApi.get(uri);
        if (response == null) {
            Logger.get().error("call product service failed. uri: " + uri);
            return null;
        }
        Logger.get().info("res: " + response);
        Map<String, String> appInfo = new Gson().fromJson(response, new TypeToken<HashMap<String, String>>() {}.getType());
        Logger.get().info(appInfo);
        String productId = appInfo.get("productId");

        return productId;
    }
}
