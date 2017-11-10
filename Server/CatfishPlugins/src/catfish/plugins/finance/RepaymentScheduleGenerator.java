package catfish.plugins.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import com.google.gson.Gson;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.plugins.finance.object.PdlProductData;

public class RepaymentScheduleGenerator {

	private static double forwardPeriods = Double.valueOf(StartupConfig.get("catfish.finance.forwardPeriods", "3")).doubleValue();
	private static double feeFactor = Double.valueOf(StartupConfig.get("catfish.finance.feeFactor", "20")).doubleValue();;

	private static PdlProductData pdlProductData;


//  0息产品上线后， 不需要了
//	public static List<RepaymentItem> generateRepaymentsOfPMT(LoanInformation loanInfo)
//	{
//		return generateRepaymentsOfPMT(loanInfo, feeFactor);
//	}
//
//	///
//	// 根据借款信息生成还款计划表，公式为PMT
//	// <param loanInfo> 借款信息
//	// <param paramOfFeeFactor> 默认值20.0; 取 inf（无穷）表示服务费不前移，每月还款总额相等。
//	///
//	public static List<RepaymentItem> generateRepaymentsOfPMT(LoanInformation loanInfo, double paramOfFeeFactor)
//	{
//		int scale = 2;
//		int mode= BigDecimal.ROUND_HALF_UP;
//
//		//本金 + 利息
//		double monthRateWithInterest = loanInfo.interestRate/12;
//		double snowBallWithInterest = Math.pow( 1 + monthRateWithInterest, loanInfo.periods);
//		double factorWithInterest = monthRateWithInterest * snowBallWithInterest /(snowBallWithInterest - 1);
//
//		// 本金 + 利息  + 管理费 + 服务费
//		double monthReteAll = (loanInfo.interestRate + loanInfo.restRate)/12;
//		double snowBallAll = Math.pow( 1 + monthReteAll, loanInfo.periods);
//		double factorAll = monthReteAll * snowBallAll /(snowBallAll - 1);
//
//		// 不前移情况下的 每月还款总额
//		BigDecimal monthPayAll = RepaymentUtil.double2Decimal(factorAll * loanInfo.princpal, scale, mode);
//		// 不前移情况下的 每月本金+利息
//		BigDecimal monthPayWithInterest = RepaymentUtil.double2Decimal(factorWithInterest * loanInfo.princpal, scale, mode);
//
//		// 不前移情况下的 每月服务费
//		BigDecimal feeInMonthPay = monthPayAll.subtract(monthPayWithInterest);
//		BigDecimal factor = new BigDecimal(loanInfo.periods / forwardPeriods);
//		BigDecimal feeDivideForward = feeInMonthPay.multiply(factor);
//
//		// 前移的服务费
//		BigDecimal forwardFee = computeForwardFee(loanInfo.princpal, paramOfFeeFactor);
//		// 考虑特殊情况： 前移服务费 *3 > 不前移情况下的每月服务费 * 12
//		forwardFee = forwardFee.compareTo(feeDivideForward) < 0 ? forwardFee : feeDivideForward;
//		BigDecimal accountFeeInMonthPay = feeInMonthPay.subtract(forwardFee.divide(factor));
//
//		List<RepaymentItem> repayments = new ArrayList<RepaymentItem>();
//		double remainPrincpal = loanInfo.princpal;
//		for(int i=1;i<=loanInfo.periods;i++)
//		{
//			RepaymentItem item = new RepaymentItem();
//			item.repaymentNumber = i;
//			item.dateDue = RepaymentUtil.getDateDue(loanInfo.firstPaybackDay, i);
//			item.accountFee = accountFeeInMonthPay;
//			item.fee = i <= forwardPeriods ? forwardFee : BigDecimal.ZERO;
//
//			// 拆分本息
//			item.interest = RepaymentUtil.double2Decimal(remainPrincpal * monthRateWithInterest, scale, mode);
//			// 最后一期本金特殊处理，以保证Sum（principal） = 总本金
//			if (i == loanInfo.periods)
//			{
//				item.principal = RepaymentUtil.double2Decimal(remainPrincpal, scale, mode);
//			}
//			else
//			{
//				item.principal = monthPayWithInterest.subtract(item.interest);
//				remainPrincpal -= item.principal.doubleValue();
//			}
//			repayments.add(item);
//		}
//
//		return repayments;
//	}


	///
	// 根据借款信息生成PDL的还款计划表
	// <param loanInfo> 借款信息。 注意： 字段periods这里表示借款天数。
	///
	public static List<RepaymentItem> generateRepaymentsOfPDL(LoanInformation loanInfo)
	{
		List<RepaymentItem> repayments = new ArrayList<RepaymentItem>();
		RepaymentItem item = generateRepayment(loanInfo.princpal, loanInfo.periods, loanInfo.firstPaybackDay);
		repayments.add(item);
		return repayments;
	}

	///
	//测试PDL曲线，请忽略
	///
	public static double generateRepayment1(double pricinpal, int days)
	{
		double a = 1.0/1000;
		double rate = 1.0 /365;
		double b = a/rate;
		double base = 80;

		return base + a*pricinpal*Math.pow(1+Math.pow(days/b, 2), 1.0/2);
	}

	///
	// 查询表格，获取PDL的利息。
	// PDL的借款金额和借款天数.注意不合理的金额和天数触发警告：  out of range！
	///
	public static RepaymentItem generateRepayment(double principal, int days, Date dateDue)
	{
	    BigDecimal monthlyPay = getMonthlyPay(new BigDecimal(principal), days);
		RepaymentItem repayment = new RepaymentItem();
		repayment.repaymentNumber = 1;
		repayment.dateDue = RepaymentUtil.getDateDue(dateDue, 1);
		repayment.principal = new BigDecimal(principal);
		repayment.interest = new BigDecimal(days);
		repayment.accountFee = BigDecimal.ZERO;
		repayment.fee = monthlyPay.subtract(repayment.interest).subtract(repayment.principal);

		return repayment;
	}

	///
	//计算服务费
	//公式： principal/factor  在十位上向下取整
	///
//	private static BigDecimal computeForwardFee(double principal, double factor)
//	{
//		return RepaymentUtil.double2Decimal( 10.0 * Math.floor(principal/(10.0 * factor)), 2, BigDecimal.ROUND_HALF_UP);
//	}

    // 获取PDL总还款额
	// loanPeriod: 借款天数
    private static BigDecimal getMonthlyPay(BigDecimal principal, int loanPeriod) {
      if (pdlProductData == null) {
        initPdlProductData();
      }

      int principalIndex = principal
          .subtract(pdlProductData.minPrincipal)
          .divide(pdlProductData.step)
          .intValue();
      int dayIndex = loanPeriod - pdlProductData.minLoanDays;
      int validDays = pdlProductData.maxLoanDays - pdlProductData.minLoanDays + 1;
      return pdlProductData.monthlyPay.get(principalIndex * validDays + dayIndex);
    }

    private static void initPdlProductData() {
      String productServiceHost = StartupConfig.get("catfish.service.product.host");
      String uri = productServiceHost + "/product/paydayloan";
      String response = HttpClientApi.get(uri);
      if (response == null) {
        Logger.get().error("call product service failed. uri: " + uri);
        return;
      }
      pdlProductData = new Gson().fromJson(response, PdlProductData.class);
    }

}
