package catfish.plugins.finance.object;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hary on 15/9/29.
 */
public class FinanceCheck {
    public String id;          // 主键
    public Date datedue;       // 还款日
    public int numInstalment;  // 第几期
    public String appId;       // 申请Id
    public int status;         // 处理状态
    public Date LastModified;  //
    public Date DateAdded;     //
}

