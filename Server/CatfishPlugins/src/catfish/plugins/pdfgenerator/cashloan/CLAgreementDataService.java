package catfish.plugins.pdfgenerator.cashloan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.omniprime.fpp.app.api.representations.RepaymentInfo;
import com.omniprimeinc.finance.installment.settlement.api.models.response.AllInfo;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.cowfish.application.model.ApplicationModel;
import catfish.cowfish.application.model.EventModel;
import catfish.cowfish.common.model.IdModel;
import catfish.cowfish.product.model.ProductModel;
import catfish.cowfish.user.model.UserInfoModel;
import catfish.plugins.pdfgenerator.AgreementApi;
import catfish.plugins.pdfgenerator.cashloan.model.ProductFundModel;
import catfish.plugins.pdfgenerator.cashloan.model.ReturnCode;
import catfish.plugins.pdfgenerator.cashloan.model.SaturnBaseResult;
import catfish.plugins.pdfgenerator.cashloan.utils.GsonUtils;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;

public class CLAgreementDataService {

	private static final String COWFISH_HOST = StartupConfig.get("cowfish.rest.host");
	private static final String GRASSCARP_HOST = StartupConfig.get("grasscarp.rest.host");
	private static final String FPP_HOST = StartupConfig.get("fpp.rest.host");
	private static final String PHOEBUS_HOST = StartupConfig.get("phoebus.rest.host");
	private static final String ASSET_HOST = StartupConfig.get("asset.rest.host");
	private static final String SATURN_PRODUCT_HOST=StartupConfig.get("saturn.product.host");
	private static final String UNIAPPLICATION_HOST = StartupConfig.get("merchant.application.host");
	private static final boolean UNIAPPLICATION_ENABLED = StartupConfig.getAsBoolean("cowfish.uniapplication.enabled");
	
	public static String getFundId(String appId) {
		if (UNIAPPLICATION_ENABLED) {
			// 通过统一申请获取资金源渠道
			String uri = String.format("%s/merchantapplication/application/%s/fund", UNIAPPLICATION_HOST, appId);
			ReturnCode<String> result = HttpClientApi.getGson(uri, ReturnCode.class);
			
			if(result == null || result.getData() == null || "".equals(result.getData())) {
				return null;
			} else {
				return result.getData();
			}
		} else {
			// 通过LTV服务获取资金源渠道
			String uri = String.format("%s/application/%s/fund", COWFISH_HOST, appId);
			IdModel model = HttpClientApi.getGson(uri, IdModel.class);
			return null == model ? null : model.getId();
		}
	}

	/**
	 * 根据appId请求cowfish-service获取productId
	 * 
	 * @param appId
	 * @return
	 */
	public static String getProductId(String appId) {
		String uri = String.format("%s/application/%s/financeProduct", COWFISH_HOST, appId);
		IdModel model = HttpClientApi.getGson(uri, IdModel.class);
		return null == model ? null : model.getId();
	}
	
	public static Map<String, Object> getFinanceProductParamsByProductIdAndFundId(String financeProductId,
			String fundId) {
		String url = String.format("%s/product/queryByProductIdAndFundId", SATURN_PRODUCT_HOST);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("fundId", fundId);
		requestData.put("productId", financeProductId);
		Map<String, Object> result = new HashMap<>();
		try {
			result = HttpClientApi.postJson(url, requestData);
		} catch (Exception e) {
			Logger.get().error("get FinanceProduct Params By ProductId=" + financeProductId + " And FundId= " + fundId
					+ " faild.");
		}
		return result;
	}
	
	/**
	 * get financeproduct by id
	 * @param productConfigId
	 * @return
	 */
	public static Map<String, Object> getFinanceProductById(String productConfigId) {
		String url = String.format("%s/product/query?id=%s", SATURN_PRODUCT_HOST, productConfigId);
		String stringResult;
		try {
			stringResult = HttpClientApi.get(url);
			Gson gson = GsonUtils.getGson();
			Map<String, Object> result = gson.fromJson(stringResult, new TypeToken<Map<String, Object>>() {
				private static final long serialVersionUID = 1L;
			}.getType());
			return result;
		} catch (Exception e) {
			Logger.get().error("get FinanceProductById= " + productConfigId + " faild.");
			return new HashMap<>();
		}
	}

	/**
	 * 根据fundId和productId请求金融产品服务获取Fund信息，主要包含前置服务费率和利息
	 * @param fundId
	 * @param productId
	 * @return
	 */
	public static ProductFundModel queryFundInfo(String productId, String fundId) {
		try {
			// TODO 获取金融产品
			String url = String.format("%s/product/%s/fund/%s", SATURN_PRODUCT_HOST, productId, fundId);
			String stringResult = HttpClientApi.get(url);
			Gson gson = GsonUtils.getGson();
			SaturnBaseResult<ProductFundModel> result = gson.fromJson(stringResult,
					new TypeToken<SaturnBaseResult<ProductFundModel>>() {
						private static final long serialVersionUID = 1L;
					}.getType());
			if (result != null && result.isSuccess()) {
				return result.getData();
			}
			return null;
		} catch (Exception e) {
			Logger.get().warn("queryFundInfo faild for productId=" + productId + ",fundId=" + fundId + e.getMessage());
			return null;
		}
	}

	public static ApplicationModel getApplication(String appId) {
		String uri = String.format("%s/application/%s", COWFISH_HOST, appId);
		return HttpClientApi.getGson(uri, ApplicationModel.class);
	}

	@SuppressWarnings("serial")
	public static List<EventModel> getApplicationEvent(String appId) {
		String uri = String.format("%s/application/%s/event", COWFISH_HOST, appId);
		String json = HttpClientApi.get(uri);
		return CLUtil.getLongDateGson().fromJson(json, new TypeToken<List<EventModel>>() {
		}.getType());
	}

	public static RepaymentInfo getRepaymentSchedule(String appId) {
		String uri = String.format("%s/repayments/%s", FPP_HOST, appId);
		String json = HttpClientApi.get(uri);
		return CLUtil.getLongDateGson().fromJson(json, RepaymentInfo.class);
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

	public static IdModel getApplicationProductId(String appId) {
		String uri = String.format("%s/application/%s/financeProduct", COWFISH_HOST, appId);
		return HttpClientApi.getGson(uri, IdModel.class);
	}

	public static ProductModel getApplicationProduct(String productId) {
		String uri = String.format("%s/product/%s", COWFISH_HOST, productId);
		return HttpClientApi.getGson(uri, ProductModel.class);
	}

	public static UserInfoModel getCLUserInfo(String userId) {
		String uri = String.format("%s/user/%s/info", COWFISH_HOST, userId);
		return HttpClientApi.getGson(uri, UserInfoModel.class);
	}

	public static UserInfoModel getCLUserInfoByAppId(String appId) {
		String uri = String.format("%s/application/%s/userInfo", COWFISH_HOST, appId);
		return HttpClientApi.getGson(uri, UserInfoModel.class);
	}

	public static AllInfo getRepaymentsFromPhoebus(String appId) {
		String uri = String.format("%s/settlement/product/all-info?appId=%s", PHOEBUS_HOST, appId);
		return HttpClientApi.getGson(uri, AllInfo.class);
	}

	public static List<RepaymentSchedule> getRepaymentsFromAsset(String appId) {
		String uri = String.format("%s/fund/asset/ltv/repayment/%s", ASSET_HOST, appId);
		String response = HttpClientApi.get(uri);
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
				(JsonDeserializer<Date>) (json, typeOfT, context) -> json == null ? null : new Date(json.getAsLong()))
				.create();
		return gson.fromJson(response, new TypeToken<List<RepaymentSchedule>>() {
		}.getType());
	}

  public static FundFinanceProduct getFundFinanceProductFromAsset(int repayments) {
      String uri = String.format("%s/fund/asset/ltv/fundFinanceProduct/%s", ASSET_HOST,repayments);
		return HttpClientApi.getGson(uri, FundFinanceProduct.class);
	}

	public static BigDecimal getConsultFeeRateByAppId(String appId) {
		String uri = String.format("%s/application/%s/preChargeFee", COWFISH_HOST, appId);
		BigDecimal result = null;
		try {
			result = HttpClientApi.getGson(uri, BigDecimal.class);
		} catch (Exception e) {
			Logger.get().error("get consult fee rate from cowfish-service by appId=" + appId + " faild.");
		}
		return result;
	}
	
	public static Map<String, Object> getApplicationExtendInfo(String appId) {
		
		Map<String, Object> result = null;
		
		String uri = String.format("%s/application/%s/extend", COWFISH_HOST, appId);
		
		try {
			result = HttpClientApi.getGson(uri);
		} catch (Exception e) {
			Logger.get().error("get application extend info failed, appId=" + appId);
		}
		
		return result;
	}

}
