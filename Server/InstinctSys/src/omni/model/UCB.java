/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package omni.model;

import omni.database.DataContainer;
import omni.database.mongo.DerivativeVariables;

/**
 * 〈百度黑名单字段〉
 *
 * @author dengw
 * @version UCB.java, V1.0 2016年12月6日 下午4:23:33
 */
public class UCB {
    /** 黑名单等级 A/B/C/D,未命中-9999 */
    private String x_Baidu_blackLevel;
    /** 加黑原因,T01~T09,未命中-9999 */
    private String x_Baidu_blackReason;

    public UCB(String appId) {
        this(appId, "");
    }

    public UCB(String appId, String instinctCall) {
        this.initialize(appId);
    }

    private void initialize(String appId) {
        DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
        if (mongodv != null) {
            this.x_Baidu_blackLevel = mongodv.X_Baidu_blackLevel;
            this.x_Baidu_blackReason = mongodv.X_Baidu_blackReason;
        }
    }

    public String getX_Baidu_blackLevel() {
        return x_Baidu_blackLevel;
    }

    public void setX_Baidu_blackLevel(String x_Baidu_blackLevel) {
        this.x_Baidu_blackLevel = x_Baidu_blackLevel;
    }

    public String getX_Baidu_blackReason() {
        return x_Baidu_blackReason;
    }

    public void setX_Baidu_blackReason(String x_Baidu_blackReason) {
        this.x_Baidu_blackReason = x_Baidu_blackReason;
    }
}
