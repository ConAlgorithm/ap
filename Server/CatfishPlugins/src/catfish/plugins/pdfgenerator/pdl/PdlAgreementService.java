package catfish.plugins.pdfgenerator.pdl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;

public class PdlAgreementService {

	public String getAgreementByUserId(String userId, Integer days) {
		StartupConfig.initialize();
		Logger.initialize();
		String grasscarpUrl = StartupConfig.get("grasscarp.rest.host");
		PdlAgreementModel pdlAgreementModel = new PdlAgreementModel();

		/*
		 * 设置日期信息
		 */
		pdlAgreementModel.setToday(new Date()); // 设置今天的日期
		pdlAgreementModel.setPayDay(getPayDate(days)); // 设置还款日

		/*
		 * 获取用户的银行卡信息
		 */
		String url = grasscarpUrl + "/user/" + userId + "/bankCardInfo";
		Map<String, Object> result = HttpClientApi.getGson(url);
		pdlAgreementModel.setBankAccount(result.get("bankAccount").toString());
		pdlAgreementModel.setBankAccountName(result.get("bankAccountName").toString());
		pdlAgreementModel.setBankName(result.get("bankName").toString());

		/*
		 * 获取用户的个人信息
		 */
		url = grasscarpUrl + "/user/" + userId;
		result = HttpClientApi.getGson(url);
		pdlAgreementModel.setAddress(result.get("livingAddress").toString());
		pdlAgreementModel.setIdNumber(result.get("idNumber").toString());
		pdlAgreementModel.setName(result.get("idName").toString());

		/*
		 * 获取手机号码信息
		 */
		url = grasscarpUrl + "/user/" + userId + "/mobile";
		String phone = HttpClientApi.get(url);
		pdlAgreementModel.setMobile(phone);

		/*
		 * 设置费率相关信息
		 */
		pdlAgreementModel.setServiceFeeRate(StartupConfig.get("catfish.pdl.servicefeerate"));
		pdlAgreementModel.setInterestRate(StartupConfig.get("catfish.pdl.interestrate"));

		Logger.get().info("get pdl Agreement info:" + new Gson().toJson(pdlAgreementModel));
		String html = fillHtmlTemplate(pdlAgreementModel);
		return html;
	}

	private String fillHtmlTemplate(PdlAgreementModel model) {
		String minrepayment_agrement_url = StartupConfig.get("catfish.agreement.partial-minrepayment");
		try {
			InputStream is = new FileInputStream(minrepayment_agrement_url);
			String htmlStr = CharStreams.toString(new InputStreamReader(is, "UTF-8"));
			htmlStr = htmlStr.replace("%Name%", model.getName());
			htmlStr = htmlStr.replace("%IdNumber%", model.getIdNumber());
			htmlStr = htmlStr.replace("%Address%", model.getAddress());
			htmlStr = htmlStr.replace("%Mobile%", model.getMobile());
			htmlStr = htmlStr.replace("%BankAccountName%", model.getBankAccountName());
			htmlStr = htmlStr.replace("%BankName%", model.getBankName());
			htmlStr = htmlStr.replace("%BankAccount%", model.getBankAccount());
			htmlStr = htmlStr.replace("%InterestRate%", model.getInterestRate());
			htmlStr = htmlStr.replace("%ServiceFeeRate%", model.getServiceFeeRate());
			htmlStr = htmlStr.replace("%Today%", model.getToday().toString());
			htmlStr = htmlStr.replace("%PayDay%", model.getPayDay().toString());
			return htmlStr;
		} catch (Exception e) {
			Logger.get().error("an exception occured while replace key word for html template .");
			return null;
		}
	}

	private Date getPayDate(Integer days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

}
