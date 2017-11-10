/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package omni.database.adapter.settlement;

import java.util.List;
import java.util.Map;

import omni.database.catfish.object.hybrid.AppCountObject;
import omni.database.catfish.object.hybrid.AppSecondCreditObject;

/**
 * 对接清结算接口
 *
 * @author baowzh
 * @version SettlementService.java, V1.0 2016年9月13日 上午10:04:11
 */
public interface SettlementService {

    /**
     * <p>通过AppId列表获取D2FPD7%信息数据集合</p>
     * 
     * @param appIds
     * @return
     */
    Map<String, AppCountObject> getMassiveAppD2FPD7RateInfoById(List<String> appIds);
    
    /**
     * <p>〈通过appId获取二次贷相关信息〉</p>
     * 
     * @param appId
     * @return
     */
    Map<String,AppSecondCreditObject> getSecondCreditInfoById(List<String> appIds);
}
