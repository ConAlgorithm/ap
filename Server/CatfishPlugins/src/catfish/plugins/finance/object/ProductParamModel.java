package catfish.plugins.finance.object;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hary on 15/8/31.
 */
public class ProductParamModel {
    public BigDecimal minPrincipal;
    public BigDecimal maxPrincipal;
    public List<Integer> repaymentMonths; // 还款期数

    public BigDecimal apr;              // 年利率
    public int step; // 最小可调金额
    public List<CellPhone> items;

    public BigDecimal interestRate;     // 利息利率
    public BigDecimal penaltyFee;       // 每日罚金金额
    public BigDecimal monthlyFeeRate;   // 月服务费比例

    public int monthlyFeeDigit;         // 月服务费精度
    public int monthlyFeeNper;          // 月服务费期数

    public BigDecimal feeRate;          // 每月服务费比例
}

class CellPhone {
    public String id;
    public String name;

    public BigDecimal price;

    public BigDecimal max;

    public BigDecimal min;

    public int priority;
}
