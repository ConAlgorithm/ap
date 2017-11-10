package catfish.plugins.pdfgenerator.merchantloan;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.plugins.pdfgenerator.AgreementApi;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;

public class MsAgreementDataService {

	private static final String MERCHANT_FINANCE_HOST = StartupConfig.get("merchant.finance.host");
	private static final String GRASSCARP_HOST = StartupConfig.get("grasscarp.rest.host");
	private static final String FINANCE_PRODUCT_HOST = StartupConfig.get("merchant.financeproduct.rest.host");

	public static FinanceProductOutModel getFinanceProduct(String financeProductId) {
		String uri = String.format("%s/merchantfinanceproduct/installmentproducts/%s", FINANCE_PRODUCT_HOST,
				financeProductId);
		return HttpClientApi.getGson(uri, FinanceProductOutModel.class);
	}

	public static FeeInfoModel getFeeInfoModel(String financeProductId) {
		String uri = String.format("%s/merchantfinanceproduct/installmentproducts/%s/feeinfo", FINANCE_PRODUCT_HOST,
				financeProductId);
		return HttpClientApi.getGson(uri, FeeInfoModel.class);
	}
	/*
	 * public static ApplicationModel getApplication(String appId) { String uri
	 * = String.format("%s/merchantloan/application/%s", MERCHANT_HOST, appId);
	 * return HttpClientApi.getGson(uri, ApplicationModel.class); }
	 * 
	 */

	@SuppressWarnings("unchecked")
	public static RepaymentInfo getRepaymentSchedule(String productId, BigDecimal interest_rate, BigDecimal principal,
			int period) {
		String uri = String.format("%s/%s/repayment?interest-rate=%s&principal=%s&period=%d", MERCHANT_FINANCE_HOST,
				productId, interest_rate, principal, period);
		ObjectMapper mapper = new ObjectMapper();
		URL jsonUrl;
		try {
			jsonUrl = new URL(uri);
			return ((List<RepaymentInfo>) mapper.readValue(jsonUrl, new TypeReference<List<RepaymentInfo>>() {
			})).get(0);
		} catch (IOException e) {
			Logger.get().warn("get RepaymentInfo error" + e);
		}
		return null;
	}

	public static BigDecimal getPenalty(String appId) {
		return AgreementApi.getPenalty(appId);
	}

	public static User getUserInfo(String userId) {
		String uri = String.format("%s/user/%s", GRASSCARP_HOST, userId);
		return HttpClientApi.getGson(uri, User.class);
	}

	public static Account getUserAccountInfo(String userId) {
		String uri = String.format("%s/account?type=userId&value=%s", GRASSCARP_HOST, userId);
		return HttpClientApi.getGson(uri, Account.class);
	}

	/*
	 * public static ProductModel getApplicationProduct(BigDecimal price) {
	 * String uri = String.format("%s/product/rule?ruleKey=%s&ruleValue=%s",
	 * MERCHANT_HOST, "price", price.toString()); return
	 * HttpClientApi.getGson(uri, ProductModel.class); }
	 */

}
