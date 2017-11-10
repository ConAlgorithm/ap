package catfish.plugins.finance;

import java.math.BigDecimal;

public class RepaymentItem {
	
	public int repaymentNumber;   // 所处在的期数
	public String dateDue;        // 偿还期限
	public BigDecimal principal;  // 偿还本金
	public BigDecimal interest;   // 偿还利息
	public BigDecimal accountFee; // 偿还管理费
	public BigDecimal fee;        // 偿还服务费

}
