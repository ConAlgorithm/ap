package engine.rule.model.creator.pdl;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.business.common.CompanyContactType;
import catfish.base.business.common.ContactType;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.JobInfoDao;
import catfish.base.business.object.JobInfoObject;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.pdl.SocialRelationInForm;


public class SocialRelationModelCreator extends AbstractApplicationModelCreator {

	public SocialRelationModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<SocialRelationInForm>(
				new SocialRelationInForm()).buidDerivativeVariables(appId)
				.getForm();

		JobInfoObject jobObj = new JobInfoDao(appId).getSingle();

		SocialRelationInForm socialForm = (SocialRelationInForm) form;
		// 公司固定电话或老板电话
		String companyContact = ContactDao
				.getCompanyTelNumberByJobInfoIdAndType(jobObj.Id,
						ContactType.Leader);
		// 如果为空
		if (companyContact == null || companyContact.equals(""))
			socialForm.setCompanyTelNumberType(CompanyContactType.Empty
					.getValue());
		// 如果为老板电话
		else if (companyContact.substring(0, 1).equals("1")
				&& companyContact.length() == 11)
			socialForm
					.setCompanyTelNumberType(CompanyContactType.LeaderPhone
							.getValue());
		else
			socialForm
					.setCompanyTelNumberType(CompanyContactType.CompanyTelePhone
							.getValue());

		Map<String, Object> params = CollectionUtils.mapOf("in_Social",
				(Object) socialForm);
		return params;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
