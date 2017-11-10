package catfish.plugins.pdfgenerator.merchantloan;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

import com.google.common.io.CharStreams;

import catfish.base.Logger;
import catfish.plugins.pdfgenerator.cashloan.CLUtil;
import grasscarp.user.model.User;

public class MsAgreementService {
	private static final String PARTIAL_TEMPLATE = "./agreementTemplate/agreement-merchantloan.html";
	private static final String FULL_TEMPLATE = "./agreementTemplate/agreement-merchantloan.html";
	private static String STAMP = CLUtil.getImgBaset64FromFile("agreementTemplate/stamp.gif");

	private String appId;
	private MsAgreementModel model;
	private FinanceProductOutModel financeProductOutModel;

	/*
	 * public MsAgreementService(String appId) { super(); this.appId = appId;
	 * model = new MsAgreementModel(); fillApplicationInfo(); }
	 */

	public MsAgreementService(String userId, BigDecimal principal, String financeProductId, String requestSN) {
		super();
		model = new MsAgreementModel();
		financeProductOutModel = MsAgreementDataService.getFinanceProduct(financeProductId);
		this.appId = requestSN;
		model.setAppId(appId);
		model.setUserId(userId);
		model.setPrincipal(principal);
		model.setRepayments(financeProductOutModel.getRepaymentMonths());
		model.setPrincipalCapital(CLUtil.changeNumber2Chinese(model.getPrincipal().doubleValue()));
		model.setMoneyTransferredOn(new Date());
	}

	public String getFullHtml() {
		buildAgreementModel();
		return buildHtml(FULL_TEMPLATE);
	}

	public String getPartialHtml() {
		buildAgreementModel();
		return buildHtml(PARTIAL_TEMPLATE);
	}

	private String buildHtml(String templateFile) {
		try (FileInputStream fin = new FileInputStream(templateFile)) {
			String htmlStr = CharStreams.toString(new InputStreamReader(fin, "UTF-8"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

			htmlStr = htmlStr.replace("./stamp.gif", "data:image/gif;base64, " + STAMP);

			htmlStr = htmlStr.replace("%Name%", model.getName());
			htmlStr = htmlStr.replace("%IdNumber%", model.getIdNumber());
			htmlStr = htmlStr.replace("%Address%", notNullString(model.getAddress()));
			htmlStr = htmlStr.replace("%BankName%", model.getBankName());
			htmlStr = htmlStr.replace("%BankAccount%", model.getBankAccount());

			htmlStr = htmlStr.replace("%AppId%", model.getAppId().replace("-", ""));
			htmlStr = htmlStr.replace("%Repayments%", String.valueOf(model.getRepayments()));
			htmlStr = htmlStr.replace("%Principal%", model.getPrincipal().toString());
			htmlStr = htmlStr.replace("%PrincipalCapital%", model.getPrincipalCapital());
			htmlStr = htmlStr.replace("%MonthlyRepayment%", model.getMonthlyRepayment().toString());
			htmlStr = htmlStr.replace("%MonthlyRepaymentCapital%", model.getMonthlyRepaymentCapital());
			htmlStr = htmlStr.replace("%PrincipalInterestPerMonth%", model.getPrincipalInterestPerMonth().toString());
			htmlStr = htmlStr.replace("%FeePerMonth%", model.getFeePerMonth().toString());
			htmlStr = htmlStr.replace("%FeePerMonthCapital%", model.getFeePerMonthCapital());
			htmlStr = htmlStr.replace("%Fee%", model.getFee().toString());
			htmlStr = htmlStr.replace("%MonthlyFeeRate%", model.getMonthlyFeeRate().toString());
			htmlStr = htmlStr.replace("%MonthlyInterestRate%", model.getMonthlyInterestRate().toString());
			htmlStr = htmlStr.replace("%PenaltyPerDay%", model.getPenaltyPerDay().toString());
			htmlStr = htmlStr.replace("%PrePaymentFee%", model.getPrePaymentFee().toString());
			htmlStr = htmlStr.replace("%MonthlyPaybackDay%", String.valueOf(model.getMonthlyPaybackDay()));
			htmlStr = htmlStr.replace("%FirstPaybackDate%", sdf.format(model.getFirstPaybackDate()));
			htmlStr = htmlStr.replace("%EndTime%", sdf.format(model.getEndTime()));
			htmlStr = htmlStr.replace("%MoneyTransferredOn%", sdf.format(model.getMoneyTransferredOn()));
			htmlStr = htmlStr.replace("%RepaymentSchedule%", model.getRepaymentSchedule());
			htmlStr = htmlStr.replace("%TechMaintenFee%", model.getTechMaintenFee().toString());
			htmlStr = htmlStr.replace("%MonthlyTechMaintenFeeRate%", model.getMonthlyTechMaintenFeeRate().toString());

			return htmlStr;
		} catch (UnsupportedEncodingException e) {
			Logger.get().error(e);
		} catch (IOException e) {
			Logger.get().error(e);
		}
		return null;
	}

	private void buildAgreementModel() {
		fillUserInfo();
		fillRepaymentInfo();
		fillExtraInfo();
	}

	/*
	 * private void fillApplicationInfo() { ApplicationModel appModel =
	 * MsAgreementDataService.getApplication(appId); Date moneyTransferredOn =
	 * new Date();
	 * 
	 * model.setAppId(appId); model.setUserId(appModel.getUserId());
	 * model.setPrincipal(appModel.getPrincipal());
	 * model.setPrincipalCapital(CLUtil.changeNumber2Chinese(model.getPrincipal(
	 * ).doubleValue())); model.setRepayments(appModel.getRepayments());
	 * model.setMoneyTransferredOn(moneyTransferredOn == null ? new Date() :
	 * moneyTransferredOn); }
	 */
	private void fillUserInfo() {
		String userId = model.getUserId();
		User userInfo = MsAgreementDataService.getUserInfo(userId);
		String livingAddress = userInfo.getLivingAddress();

		model.setName(userInfo.getIdName());
		model.setIdNumber(userInfo.getIdNumber());
		model.setAddress(livingAddress);
		model.setBankName(userInfo.getBankName());
		model.setBankAccount(userInfo.getBankCardAccount());
	}

	private void fillRepaymentInfo() {
		BigDecimal repayments = new BigDecimal(model.getRepayments());
		RepaymentInfo repaymentInfo = MsAgreementDataService.getRepaymentSchedule(
				financeProductOutModel.getId().toString(), financeProductOutModel.getInterestRate(),
				model.getPrincipal(), model.getRepayments());
		RepaymentItem firstRepayment = repaymentInfo.items.stream().filter(item -> item.index == 1).findFirst().get();
		Date firstPaybackDate = firstRepayment.dateDue;
		Date endTime = repaymentInfo.items.stream().filter(item -> item.index == model.getRepayments()).findFirst()
				.get().dateDue;
		BigDecimal totalFee = repaymentInfo.items.stream().map(item -> item.fee).reduce(BigDecimal.ZERO,
				(a, b) -> a.add(b));
		BigDecimal totalTechMaintenFee = repaymentInfo.items.stream()
				.map(item -> notNullBigDecimal(item.techMaintainFee)).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		StringBuilder repaymentScheduleSB = new StringBuilder();
		for (RepaymentItem item : repaymentInfo.items) {
			BigDecimal payback = item.principal.add(item.interest).add(item.fee)
					.add(notNullBigDecimal(item.techMaintainFee)).setScale(2, BigDecimal.ROUND_HALF_UP);
			repaymentScheduleSB.append("<tr>").append(String.format("<td>%s</td>", item.index))
					.append(String.format("<td>%s</td>", sdf.format(item.dateDue)))
					.append(String.format("<td>本息服务费共【%s】元</td>", payback.toString())).append("</tr>");
		}
		FeeInfoModel feeInfoModel = MsAgreementDataService.getFeeInfoModel(financeProductOutModel.getId().toString());
		model.setMonthlyRepayment(firstRepayment.principal.add(firstRepayment.interest).add(firstRepayment.fee)
				.add(notNullBigDecimal(firstRepayment.techMaintainFee)).setScale(2, BigDecimal.ROUND_HALF_UP));
		model.setMonthlyRepaymentCapital(CLUtil.changeNumber2Chinese(model.getMonthlyRepayment().doubleValue()));
		model.setPrincipalInterestPerMonth(firstRepayment.principal.add(firstRepayment.interest));
		model.setFeePerMonth(firstRepayment.fee.setScale(2, BigDecimal.ROUND_HALF_UP));
		model.setFeePerMonthCapital(CLUtil.changeNumber2Chinese(model.getFeePerMonth().doubleValue()));
		model.setFee(totalFee.setScale(2, BigDecimal.ROUND_HALF_UP));
		model.setMonthlyInterestRate(financeProductOutModel.getInterestRate().multiply(new BigDecimal(100))
				.divide(repayments, 2, BigDecimal.ROUND_HALF_UP));
		model.setMonthlyFeeRate(feeInfoModel.getClientFee().getRate().multiply(new BigDecimal(100)).setScale(2,
				BigDecimal.ROUND_HALF_UP));
		model.setPenaltyPerDay(new BigDecimal(3).setScale(2, BigDecimal.ROUND_HALF_UP));
		model.setPrePaymentFee(new BigDecimal(200).setScale(2, BigDecimal.ROUND_HALF_UP));
		model.setFirstPaybackDate(firstPaybackDate);
		model.setMonthlyPaybackDay(new DateTime(firstPaybackDate).getDayOfMonth());
		model.setEndTime(endTime);
		model.setRepaymentSchedule(repaymentScheduleSB.toString());
		model.setTechMaintenFee(totalTechMaintenFee.setScale(2, BigDecimal.ROUND_HALF_UP));
		model.setMonthlyTechMaintenFeeRate(
				feeInfoModel.getTechFeeRate().multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	private void fillExtraInfo() {
	}

	private String notNullString(String in) {
		return in == null ? "" : in;
	}

	private BigDecimal notNullBigDecimal(BigDecimal amount) {
		return amount == null ? BigDecimal.ZERO : amount;
	}

}
