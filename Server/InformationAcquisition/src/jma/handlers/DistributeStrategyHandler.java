package jma.handlers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.CNAreaType;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.object.CNAreaObject;
import catfish.base.business.object.MerchantStoreObject;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.DateTimeUtils;
import jma.NonBlockingJobHandler;
import jma.models.DistributeConfig;
import jma.models.DistributeStrategyConfigManager;

/*
 * 分流决策，处于流程的开始位置，当用户提交申请后，按照规则进行匹配，命中的规则则按照百分比，进行打标签操作
 */
public class DistributeStrategyHandler extends NonBlockingJobHandler {
    
    private static ConcurrentMap<String, Integer> thresholdMap =  
            new ConcurrentHashMap<String, Integer>();

    @Override
    public void execute(String appId) {
        MerchantStoreObject storeObj = new MerchantStoreDao(
            MerchantStoreDao.getMerchantStoreId(appId)).getSingle();
        if(storeObj == null) {
            Logger.get().error("Can't get MerchatnStore Info! appId " + appId);
            return;
        }
        CNAreaObject area = new CNAreaDao(storeObj.CNAreaId).getSingle();
        String province = MerchantStoreDao.getLocatedProvinceByAreaCode(area.Code);
        String city = MerchantStoreDao.getLocatedCityByAreaCode(area.Code);
        String distinct = MerchantStoreDao.getLocatedInfoByAreaCodeAndAreaType(area.Code, CNAreaType.Area);
        
        String distributeTag = StringUtils.EMPTY_STRING;
        
        if(StringUtils.isNullOrWhiteSpaces(province) || 
                StringUtils.isNullOrWhiteSpaces(city) || 
                StringUtils.isNullOrWhiteSpaces(distinct)) {
            Logger.get().error("Get merchantStore info error! province or city or distinct error!");
            return;
        }
        
        for(DistributeConfig config : DistributeStrategyConfigManager.getConfigs()) {
            // 执行匹配的第一阶段，省市县的匹配
            if( !StringUtils.isNullOrWhiteSpaces(config.getProvince()) 
                    && !province.equals(config.getProvince())) {
                Logger.get().info("Execute rule match province! role province : " + config.getProvince() + " source : " + province);
                continue;
            }
            
            if( !StringUtils.isNullOrWhiteSpaces(config.getCity()) 
                    && !city.equals(config.getCity())) {
                Logger.get().info("Execute rule match city! role city : " + config.getCity() + " source : " + city);
                continue;
            }
            
            if( !StringUtils.isNullOrWhiteSpaces(config.getDistinct()) 
                    && !distinct.equals(config.getDistinct())) {
                Logger.get().info("Execute rule match distinct! role distinct : " + config.getDistinct() + " source : " + distinct);
                continue;
            }
            
            // 执行规则匹配后的第二阶段，随机的将命中规则的申请打上标签
            Logger.get().info("Rule match success! appId :  " + appId + "  rule : " + new Gson().toJson(config));
            
            int randomValue = (int)(1 + Math.random()*(1000));
            if(config.getValue().multiply(new BigDecimal("1000")).compareTo(new BigDecimal(randomValue)) >= 0) {
                Logger.get().info("Generate tag for appId : " + appId + "  result : " + config.getSuccess());
                distributeTag = config.getSuccess();
            } else {
                if(!StringUtils.isNullOrWhiteSpaces(config.getFailed())) {
                    Logger.get().info("Generate failed tag for appId : " + appId + " result : " + config.getFailed());
                    distributeTag = config.getFailed();
                }
            }
            break;
        }
        
        // 执行第三阶段，如果超出阈值，则不打标签
        if(!distributeTag.isEmpty()) {
            String currentKey = DateTimeUtils.formatYMD(new Date());
            Integer currentValue = thresholdMap.get(currentKey);
            if(currentValue != null && DistributeStrategyConfigManager.getThreshold().compareTo(currentValue) <= 0) {
                Logger.get().warn("Over threshold! CurrentDate : " + currentKey + " currentValue : " + currentValue + 
                    " threshold : " + DistributeStrategyConfigManager.getThreshold().toString());
                return;
            }
            thresholdMap.put(currentKey, currentValue == null ? 1 : currentValue + 1 );
            AppDerivativeVariableManager.addVariables(
                new AppDerivativeVariable(appId, AppDerivativeVariableNames.DISTRIBUTE_STRATEGY_TAG, distributeTag));
        }
    }
}
