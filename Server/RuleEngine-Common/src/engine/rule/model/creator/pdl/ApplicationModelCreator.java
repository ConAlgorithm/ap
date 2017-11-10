package engine.rule.model.creator.pdl;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.dao.JobInfoDao;
import catfish.base.business.dao.PersonalInfoDao;
import catfish.base.business.object.JobInfoObject;
import catfish.base.business.object.PersonInfoObject;
import engine.exception.DBFieldAdapterException;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.pdl.ApplicationInForm;

public class ApplicationModelCreator extends AbstractApplicationModelCreator {

	public ApplicationModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		try {
			form = new ModelBuilder<ApplicationInForm>(new ApplicationInForm())
					.buidDerivativeVariables(appId)
					.buildModelFieldsByAdapter(
							"engine.rule.model.adapter.PrincipalAdapter", appId)
					.getForm();

			ApplicationInForm appForm = (ApplicationInForm) form;
			// 教育程度
			Integer education = EndUserExtentionDao.getEducationByAppId(appId);
			if(education != null)
			     appForm.setEducation(education);

			PersonInfoObject personObj = new PersonalInfoDao(appId).getSingle();
			// 婚姻
			if(personObj.MarriageStatus != null)
			     appForm.setMarriage(personObj.MarriageStatus);
			// 居住情况
			if(personObj.LivingCondition != null)
			     appForm.setLivingCondition(personObj.LivingCondition);

			JobInfoObject jobObj = new JobInfoDao(appId).getSingle();
			// 第几份工作
			appForm.setNthJob(jobObj.NthJob);

			Map<String, Object> in = CollectionUtils.mapOf("in_Application",
					(Object) appForm);
			return in;
		} catch (DBFieldAdapterException e) {
			Logger.get().warn(e);
		}
		return null;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
