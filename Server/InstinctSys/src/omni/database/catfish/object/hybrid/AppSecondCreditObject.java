/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package omni.database.catfish.object.hybrid;

import catfish.base.business.object.BaseObject;

/**
 * 〈二次贷〉
 *
 * @author hwei
 * @version AppSecondCreditObject.java, V1.0 2016年12月30日 下午2:57:44
 */
public class AppSecondCreditObject extends BaseObject {
    /** 申请单号 */
    public String appId;
    /** 是否为二次客户 */
    public Boolean IsSecondTimeUser;
    /** 上一次已完成借款最大逾期天数 */
    public Integer LastAppMaxDelayedDays;
    /** 距离上一次已完成借款最后还款日期时间 */
    public Integer LastApplicationInterval;
    /**上一次借款申请状态 */
    public Integer LastLoanStatus;
    /** 上一次借款提前还款天数*/
    public Integer LastLoanPrepaymentdays;
}
