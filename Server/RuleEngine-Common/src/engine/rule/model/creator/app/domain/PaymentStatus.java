/**
 * Copyright (C), 上海秦苍信息科技有限公司
 * */
package engine.rule.model.creator.app.domain;

import catfish.base.business.common.CatfishEnum;


/**
 * 还款状态类型
 *
 * @author liucj
 * @version RepaymentTableResult.java, V1.0 2016年10月28日 下午2:27:42
 */
public enum PaymentStatus implements  CatfishEnum<String>{
    
    //还款中
    paymenting("10"),
    
    //逾期
    overdue("20"),
    
    //正常结清
    normalPayment("30"),
    
    //提前结清
    aheadPayment("40"),
    
    //犹豫期提前结清
    hesitationPeriodPayment("50"),
    
    //当前日期为还款日
    todayIsPaymentDate("60")
    
        ; 
    private String value;
    
    PaymentStatus(String value){
        this.value=value;
    }
    @Override
    public String getValue() {
      return   this.value;
    }

}
