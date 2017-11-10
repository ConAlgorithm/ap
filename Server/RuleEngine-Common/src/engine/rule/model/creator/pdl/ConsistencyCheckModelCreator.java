package engine.rule.model.creator.pdl;

import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.CheckNameIDCardResult;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.object.CNAreaObject;
import catfish.base.business.object.MerchantStoreObject;
import catfish.base.business.util.EnumUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.pdl.ConsistencyCheckInForm;
import engine.rule.model.in.pdl.PersonalInfoInForm;

public class ConsistencyCheckModelCreator extends
		AbstractApplicationModelCreator {

	private PersonalInfoModelCreator personModelCreator;

	public ConsistencyCheckModelCreator(String appId,
			PersonalInfoModelCreator personalInfoModelCreator) {
		super(appId);

		this.personModelCreator = personalInfoModelCreator;
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<ConsistencyCheckInForm>(
				new ConsistencyCheckInForm()).buidDerivativeVariables(appId)
				.getForm();

		ConsistencyCheckInForm consisForm = (ConsistencyCheckInForm) form;
		// 联系人不同城市数量
		consisForm.setContactorCitiesNumber(ContactDao
				.getContactPersonCitiesNumberById(appId));

		Map<String, Object> params = CollectionUtils.mapOf("in_Derived",
				(Object) consisForm);

		// 是否本地人
		MerchantStoreObject storeObj = MerchantStoreDao.getMerchantStoreById(MerchantStoreDao.getMerchantStoreId(appId));
		if (personModelCreator != null && storeObj != null) {
			params.putAll(personModelCreator.createModelForm());
			try {
				String birthProvince = ((PersonalInfoInForm) personModelCreator
						.getForm()).getIdCardProvince();
				String birthCity = ((PersonalInfoInForm) personModelCreator
						.getForm()).getIdCardCity();
				CNAreaObject area = new CNAreaDao(storeObj.CNAreaId).getSingle();
				String storeLocatedCity = MerchantStoreDao.getLocatedCityByAreaCode(area.Code);
				// 如果没有值，默认为true
				boolean result = true;
				if (!birthCity.equals("")) {
					result = birthCity.contains(storeLocatedCity);
				} else {
					result = birthProvince.contains(storeLocatedCity);
				}
				consisForm.setNativePerson(result);
			} catch (Exception e) {
				Logger.get().warn(e);
			}
		}

		//使身份证一致性检查结果标准化
		consisForm.setIdCardIdentificationResult(filterIdCardIdentificationResult(consisForm.getIdCardIdentificationResult()));
				
		return params;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

	private String filterIdCardIdentificationResult(String rawData)
	{
		if(rawData.contains(CheckNameIDCardResult.Match.getValue())
				&& !rawData.contains(CheckNameIDCardResult.NotMatch.getValue()))
		{
			return CheckNameIDCardResult.Match.getValue();
		}else if(rawData.contains(CheckNameIDCardResult.NotMatch.getValue()))
		{
			return CheckNameIDCardResult.NotMatch.getValue();
		}
        List<Object> values = EnumUtils.getValues(CheckNameIDCardResult.class);
        if(values != null)
        {
             String value = null;
             for(Object item : values)
             {
            	 value = item.toString();
            	 if(rawData.contains(value))
            		 return value;
             }
        }
        return rawData;
	}
}
