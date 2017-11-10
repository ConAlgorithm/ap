package catfish.plugins.pdfgenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import catfish.base.business.common.InstalmentChannel;
import catfish.base.httpclient.HttpClientApi;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.dao.PaymentInfoDao;
import catfish.base.business.dao.PersonalInfoDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.MerchantStoreObject;
import catfish.base.business.object.PaymentObject;
import catfish.base.business.object.PersonInfoObject;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.framework.httprequest.HttpRequest;
import catfish.framework.httprequest.HttpRespond;
import catfish.plugins.finance.RepaymentItem;
import catfish.services.httprequest.HttpRequestService;
import grasscarp.product.model.POSProductName;

public class AgreementApi {
	private static String host = StartupConfig.get("catfish.bonuspoints.rest.host");
	private static String port = StartupConfig.get("catfish.bonuspoints.rest.port");
	private static String[] testProductNames = StartupConfig.get("testProductNames").split(",");
	private static String appServiceHost = StartupConfig.get("catfish.service.application.host");
	private static String productServiceHost = StartupConfig.get("catfish.service.product.host");
	private static int repaymentOn = Integer.valueOf(StartupConfig.get("catfish.pdf.repaymenton")).intValue();
	private static final int feePayments = 3;
	
	public static ApplicationAgreementModel getAppAgreementModel(String appId) {
		ApplicationAgreementModel model = new ApplicationAgreementModel();
		// 借款信息
		InstallmentApplicationDao appDao = new InstallmentApplicationDao(appId);
		InstallmentApplicationObject app = appDao.getSingle();
		if(app.ProductSelectedTime == null)
		model.setUserId(app.UserId);
		model.setAgreementStartTime(app.InstallmentStartedOn);
		model.setPrincipal(app.Principal);
		model.setRepayments(app.Repayments);
		model.setPrincipalInMonthlyPay(app.PrincipalInMonthlyPay);
		model.setMonthlyPay(app.MonthlyPay);
		model.setInterestInMonthlyPay(app.InterestsInMonthlyPay);
		BigDecimal interstPerMonth = new BigDecimal(app.InterestsInMonthlyPay.doubleValue()*100/app.Principal.doubleValue());
		model.setInterestPerMonth(interstPerMonth);
		model.setFeeInMonthlyPay(app.FeeInMonthlyPay);
		model.setFirstPaybackDate(app.FirstPaybackDate);
		model.setMonthlyPackbackDay(app.MonthlyPaybackDay);
		System.out.println(app.PrincipalInMonthlyPay.toString());
		model.setProductName(app.ProductName);
		model.setAppId(appId);
		model.setInstallmentStartedOn(app.InstallmentStartedOn);
		String fundTag = InstallmentApplicationDao.getFundTagByAppId(appId);
		model.setFundTag(fundTag);
		model.setInstallmentChannel(app.InstalmentChannel);
		if(app.MoneyTransferredOn == null) {
			Logger.get().info("Money not transferred yet");
		}
		model.setMoneyTransferredOn(app.MoneyTransferredOn);
		// 个人信息
		EndUserExtentionDao eueDao = new EndUserExtentionDao(appId);
		EndUserExtensionObject endUserExtension = eueDao.getSingle();
		model.setName(endUserExtension.IdName);
		model.setIdNumber(endUserExtension.IdNumber);
		PersonalInfoDao piDao = new PersonalInfoDao(appId);
		PersonInfoObject personInfo = piDao.getSingle();
		model.setAddress(personInfo.LivingAddress);
		ContactDao cDao = new ContactDao(appId);
		ContactObject contact = cDao.getSingle();
		model.setMobile(contact.Content);
		// 银行信息
		PaymentInfoDao pDao = new PaymentInfoDao(appId);
		PaymentObject payment = pDao.getSingle();
		model.setBankName(payment.BankName);
		model.setBankAccount(payment.BankAccount);
		model.setBankAccountName(payment.BankAccountName);
		model.setToday(new Date());
		// 商户信息
		MerchantStoreDao msDao = new MerchantStoreDao(app.MerchantStoreId);
		MerchantStoreObject merchantStore = msDao.getSingle();
		model.setMerchantName(merchantStore.Name);
		model.setMerchantAddress(merchantStore.Address);
		model.setOutFileName(String.format("申请三方协议-客户：%s，申请id：%s.pdf", model.getName(), model.getAppId()));
		//还款计划表
		model.setRepaymentSchedule(getRepaymentSchedule(appId));
		if(model.getRepaymentSchedule() == null) {
			Logger.get().warn("Null repayment schedule for appId : "+appId);
			return model;
		}
		BigDecimal fee = new BigDecimal(0.0);
		BigDecimal total = new BigDecimal(0.0);
		BigDecimal principal = new BigDecimal(0.0);
		BigDecimal interest = new BigDecimal(0.0);
		double interestPercent = 0;
		for(RepaymentItem item : model.getRepaymentSchedule()) {
			if(item.repaymentNumber == 1) {
				model.setFirstPayback(item.principal.add(item.interest).add(item.fee).add(item.accountFee));
				model.setPrincipalInterestPerMonth(item.principal.add(item.interest).add(item.accountFee));
			}
			if(item.repaymentNumber == 1+feePayments) {
				model.setOtherPayback(item.principal.add(item.interest).add(item.fee).add(item.accountFee));
			}
			fee = fee.add(item.fee);
			total = total.add(item.principal.add(item.interest).add(item.fee));
			principal = principal.add(item.principal);
			interest = interest.add(item.interest).add(item.accountFee);
		}
		int feeNum = feePayments;
		BigDecimal penalty = new BigDecimal(5).setScale(2);
		if (app.InstalmentChannel == InstalmentChannel.App.getValue()) {
			PosProductModel productInfo = getProductParams(appId);
			if (productInfo != null) {
				if (productInfo.monthlyFeeNper > 0) {
					feeNum = productInfo.monthlyFeeNper;
				}
				if (productInfo.penaltyFee != null) {
					penalty = productInfo.penaltyFee;
				}
			}
		}
		interestPercent = interest.doubleValue()*100/(app.Principal.doubleValue()*app.Repayments);
		model.setInterestPerMonth(new BigDecimal(interestPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
		model.setInterestInMonthlyPay(new BigDecimal(interest.doubleValue()/app.Repayments));
		model.setPrincipalInMonthlyPay(new BigDecimal(principal.doubleValue()/app.Repayments));
		model.setFee(fee);
		model.setFeeInMonthlyPay(new BigDecimal(fee.doubleValue()/feeNum));
		model.setTotalPayback(total);

		//罚金信息
		if(app.InstalmentChannel == InstalmentChannel.PayDayLoanApp.getValue()) {
			model.setPenalty(new BigDecimal(5).setScale(2));
		} else {
			model.setPenalty(penalty);
		}

		return model;
	}
	
	public static ApplicationAgreementModel getUserModel(String appId) {
		
		ApplicationAgreementModel model = new ApplicationAgreementModel();
		InstallmentApplicationDao appDao = new InstallmentApplicationDao(appId);
		InstallmentApplicationObject app = appDao.getSingle();
		model.setInstallmentChannel(app.InstalmentChannel);
		// 个人信息
		EndUserExtentionDao eueDao = new EndUserExtentionDao(appId);
		EndUserExtensionObject endUserExtension = eueDao.getSingle();
		model.setName(endUserExtension.IdName);
		model.setIdNumber(endUserExtension.IdNumber);
		PersonalInfoDao piDao = new PersonalInfoDao(appId);
		PersonInfoObject personInfo = piDao.getSingle();
		model.setAddress(personInfo.LivingAddress);
		ContactDao cDao = new ContactDao(appId);
		ContactObject contact = cDao.getSingle();
		model.setMobile(contact.Content);
		// 银行信息
		PaymentInfoDao pDao = new PaymentInfoDao(appId);
		PaymentObject payment = pDao.getSingle();
		model.setBankName(payment.BankName);
		model.setBankAccount(payment.BankAccount);
		model.setBankAccountName(payment.BankAccountName);
		model.setToday(new Date());
		
		return model;
	}

	public static List<RepaymentItem>  getRepaymentSchedule(String appId) {
		Map<String, String> body = new HashMap<String, String>();
		body.put("AppId", appId);
		HttpRequest request = new HttpRequest("POST", host, port, new String[]{"GetRepaymentSchedule"},new Gson().toJson(body));
		HttpRespond res = new HttpRequestService().sendHttpRequest(request);
		List<RepaymentItem> repaymentSchedule = new Gson().fromJson(res.getResponse(), new TypeToken<List<RepaymentItem>>(){}.getType());
		return repaymentSchedule;
	}

	public static boolean isTestProduct(String appId) {

		try {
			String uri = appServiceHost + "/application/"+appId;
			String response = HttpClientApi.get(uri);
			if (response == null) {
                Logger.get().error("call application service failed. uri: " + uri);
            }
			Map<String, String> appInfo = new Gson().fromJson(response, new TypeToken<HashMap<String, String>>() {}.getType());
			Logger.get().info(appInfo);
			String productId = appInfo.get("productId");
			String productUri = productServiceHost + "/product/"+productId+"/name";
			String res2 = HttpClientApi.get(productUri);
			if (res2 == null) {
                Logger.get().error("call product service for name model failed. uri: " + productUri);
            }
			POSProductName posProductName = new Gson().fromJson(res2, new TypeToken<POSProductName>() {}.getType());
			for(String productName : testProductNames) {
				if (productName.equals(posProductName.getName())) {
					return true;
				}
			}
		} catch (JsonSyntaxException e) {
			Logger.get().warn(e);
		}
		return false;
	}

	public static PosProductModel getProductParams(String appId) {
		BigDecimal defaultPenalty = new BigDecimal(5).setScale(2);
		Map<String, String> body = new HashMap<String, String>();
		body.put("AppId", appId);
		String uri = appServiceHost + "/application/"+appId;
      	String response = HttpClientApi.get(uri);
      	if (response == null) {
        	Logger.get().error("call product service failed. uri: " + uri);
//			Logger.get().info("use default penalty " +defaultPenalty.toString());
        	return null;
      	}
		Logger.get().info("res: " + response);
		Map<String, String> appInfo = new Gson().fromJson(response, new TypeToken<HashMap<String, String>>() {}.getType());
		Logger.get().info(appInfo);
		String productId = appInfo.get("productId");
		Logger.get().info(productId);
		String productUri = productServiceHost + "/product/"+productId+"/params";
		String res2 = HttpClientApi.get(productUri);
		if (res2 == null) {
			Logger.get().error("call product service failed. uri: " + productUri);
//			Logger.get().info("use default penalty " +defaultPenalty.toString());
			return null;
		}
		Logger.get().info("res: " + res2);
//		Map<String, String> productInfo = new Gson().fromJson(res2, new TypeToken<HashMap<String, Object>>() {}.getType());
		PosProductModel productInfo = new Gson().fromJson(res2, new TypeToken<PosProductModel>() {}.getType());
		BigDecimal penalty = productInfo.penaltyFee.setScale(2);
		Logger.get().info("penalty is " + penalty.toString());

		return productInfo;
	}

	public static BigDecimal getPenalty(String appId) {
		BigDecimal defaultPenalty = new BigDecimal(5).setScale(2);
		Map<String, String> body = new HashMap<String, String>();
		body.put("AppId", appId);
		String uri = appServiceHost + "/application/"+appId;
      	String response = HttpClientApi.get(uri);
      	if (response == null) {
        	Logger.get().error("call product service failed. uri: " + uri);
			Logger.get().info("use default penalty " +defaultPenalty.toString());
        	return defaultPenalty;
      	}
		Logger.get().info("res: " + response);
		Map<String, String> appInfo = new Gson().fromJson(response, new TypeToken<HashMap<String, String>>() {}.getType());
		Logger.get().info(appInfo);
		String productId = appInfo.get("productId");
		Logger.get().info(productId);
		String productUri = productServiceHost + "/product/"+productId+"/params";
		String res2 = HttpClientApi.get(productUri);
		if (res2 == null) {
			Logger.get().error("call product service failed. uri: " + productUri);
			Logger.get().info("use default penalty " +defaultPenalty.toString());
			return defaultPenalty;
		}
		Logger.get().info("res: " + res2);
//		Map<String, String> productInfo = new Gson().fromJson(res2, new TypeToken<HashMap<String, Object>>() {}.getType());
		PosProductModel productInfo = new Gson().fromJson(res2, new TypeToken<PosProductModel>() {}.getType());
		BigDecimal penalty = productInfo.penaltyFee.setScale(2);
		Logger.get().info("penalty is " + penalty.toString());

		return penalty;
	}

	private class CellphoneMoel
	{
		String name;
		BigDecimal price;
		BigDecimal max;
		BigDecimal min;
	}

	private class PosProductModel
	{
		BigDecimal minPrincipal;
		BigDecimal maxPrincipal;
		List<Integer> repaymentMonths;
		BigDecimal apr;
		int step;
		List<CellphoneMoel> items;
		BigDecimal interestRate;
		BigDecimal penaltyFee;
		BigDecimal monthlyFeeRate;
		int montylyFeeDigit;
		int monthlyFeeNper;
		BigDecimal feeRate;
	}
}
