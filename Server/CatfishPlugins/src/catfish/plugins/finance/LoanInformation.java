package catfish.plugins.finance;

import java.util.Date;

public class LoanInformation {
	
	public double princpal;  // 本金
	public int periods;      // 期数
	public Date firstPaybackDay;  // 首次还款日
	
	public double interestRate;	  // 利率（仅包含利息）
	public double restRate;       // 利率（其他费用） = 总利率 - 利率(仅包含利息)
	

}
