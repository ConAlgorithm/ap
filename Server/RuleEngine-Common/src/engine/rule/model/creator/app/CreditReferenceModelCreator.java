package engine.rule.model.creator.app;

import java.util.List;
import java.util.Map;

import thirdparty.ylzh.response.tradereport.IndexConsumeCity;
import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.ylzh.ConsumeRegionNative;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.httpclient.object.BaseObject;

import com.google.gson.reflect.TypeToken;

import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.app.CreditReferenceInForm;

public class CreditReferenceModelCreator extends
		AbstractApplicationModelCreator {

	public CreditReferenceModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {

		form = new ModelBuilder<CreditReferenceInForm>(
				new CreditReferenceInForm()).buidDerivativeVariables(appId)
				.getForm();

		CreditReferenceInForm creditReferForm = (CreditReferenceInForm) form;
		bankReferenceScore(creditReferForm.getCreditReferQuestions(),
				creditReferForm);
			
		Map<String, Object> params = CollectionUtils.mapOf("in_Bureau",
				(Object) form);
		return params;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String[] referQuestions = new String[] {
			"办理的贷款每月应还款额（即还款计划表上和银行约定的还款金额）是多少", "曾在以下哪家机构办理过信用卡，并且正在使用",
			"通讯地址是哪里", "婚姻状况是什么", "学历是什么", "首次身份证领取地是哪里", "在何处办理过贷款",
			"在哪家机构办理过信用卡", "在哪家机构办理过贷款", "办理的信用卡额度", "办理的贷款额度",
			"您办理的所有贷款中，最近办理的贷款是以下哪种类型", "办理的所有贷款中，额度最高的贷款是以下哪种类型",
			"所有贷款中，最近办理的一笔贷款额度为多少", "办理的所有贷款中，最高一笔贷款额度为多少",
			"所有贷款中，最近办理的一笔贷款的还款频率是什么", "所有正在使用的信用卡中，最近办理的一张信用卡的发卡行为哪家",
			"办理的所有正在使用的信用卡中，额度最高的一张信用卡", "办理的所有正在使用的信用卡中，最高授信额度为多少",
			"所有尚未还清的贷款中，最近办理的一笔贷款何时到期", "办理了何种贷款", "办理的所有贷款中，最近一笔贷款是在哪家商业银行办理",
			"按照您和贷款机构的约定", "办理的所有贷款中，最早办理的贷款是以下哪种类型", };

	private static void bankReferenceScore(String referenceQuestions,
			CreditReferenceInForm form) {
		form.setAnd_b(mappingCalculation(referenceQuestions, "B", true));
		form.setAnd_h(mappingCalculation(referenceQuestions, "H", true));
		form.setAnd_r(mappingCalculation(referenceQuestions, "R", true));
		form.setAnd_ef(mappingCalculation(referenceQuestions, "EF", true));
		form.setAnd_fhj(mappingCalculation(referenceQuestions, "FHJ", true));
		form.setOr_all(mappingCalculation(referenceQuestions,
				"ABCDEFGHIJKLMNOPQRSTUVWX", false));
		form.setOr_agiklmnoptuvwx(mappingCalculation(referenceQuestions,
				"AGIKLMNOPTUVWX", false));
		form.setOr_bhjqrs(mappingCalculation(referenceQuestions, "BHJQRS",
				false));
	}

	private static boolean mappingCalculation(String questions, String chars,
			boolean andOperation) {
		if (chars.length() == 0)
			return true;

		boolean result = questions
				.contains(referQuestions[chars.charAt(0) - 'A']);
		for (int i = 1; i < chars.length(); ++i) {
			if (andOperation) {
				result &= questions
						.contains(referQuestions[chars.charAt(i) - 'A']);
			} else {
				result |= questions
						.contains(referQuestions[chars.charAt(i) - 'A']);
			}
		}

		return result;
	}
	
	//判定消费最多的地区是不是分期本地
	public ConsumeRegionNative checkConsumeMaxCityNative(List<IndexConsumeCity> cityList)
	{
		ConsumeRegionNative result = ConsumeRegionNative.EmptyData;
		if(cityList == null || cityList.size() == 0)
			result = ConsumeRegionNative.EmptyData;
		
		IndexConsumeCity max = cityList.get(0);
		for(IndexConsumeCity item : cityList)
		{
			if(item.getConsumeAmount() > max.getConsumeAmount())
				max = item;
		}
		
		String city = MerchantStoreDao.getLocatedCityByAreaCode(CNAreaDao.getCNAreaObjByAppId(appId).Code);
		boolean checkNative = city.contains(max.getName()) || max.getName().contains(city);
		result = (checkNative ? ConsumeRegionNative.IsNative : ConsumeRegionNative.NotNative);
		return result;
	}
}
