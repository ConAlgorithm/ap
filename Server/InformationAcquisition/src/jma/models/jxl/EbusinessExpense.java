/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈电商月消费〉
 *
 * @author hwei
 * @version EbusinessExpense.java, V1.0 2017年2月6日 下午2:13:10
 */
public class EbusinessExpense {
    // 月份
    private String transMth;
    // 全部消费金额
    private String category;
    // 全部消费次数
    private Float allAmount;
    // 本月商品品类
    private Integer allCount;
    
    public String getTransMth() {
        return transMth;
    }
    public void setTransMth(String transMth) {
        this.transMth = transMth;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Float getAllAmount() {
        return allAmount;
    }
    public void setAllAmount(Float allAmount) {
        this.allAmount = allAmount;
    }
    public Integer getAllCount() {
        return allCount;
    }
    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

}
