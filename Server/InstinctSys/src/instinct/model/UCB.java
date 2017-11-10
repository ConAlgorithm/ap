/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package instinct.model;

import util.InstinctizeUtil;

/**
 * 〈百度黑名单字段〉
 *
 * @author dengw
 * @version UCB.java, V1.0 2016年12月6日 下午3:30:14
 */
public class UCB {
    /** Text    1   Must be “J” */
    public final String category = "J";
    /** Text   50 黑名单等级 A/B/C/D,未命中-9999(X_Baidu_blackLevel) */
    public String user_Field1;
    /** Text   50 加黑原因,T01~T09,未命中-9999(X_Baidu_blackReason) */
    public String user_Field2;

    public UCB(omni.model.UCB ucb) {
        this.user_Field1 = InstinctizeUtil.string(ucb.getX_Baidu_blackLevel());
        this.user_Field2 = InstinctizeUtil.string(ucb.getX_Baidu_blackReason());
    }

    public UCB() {

    }
}
