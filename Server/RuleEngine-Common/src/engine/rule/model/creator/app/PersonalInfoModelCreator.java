package engine.rule.model.creator.app;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.business.util.DatabaseApiUtil;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.app.PersonalInfoInForm;

public class PersonalInfoModelCreator extends AbstractApplicationModelCreator {

	public PersonalInfoModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<PersonalInfoInForm>(new PersonalInfoInForm())
				.buidDerivativeVariables(appId).getForm();

		PersonalInfoInForm personalForm = (PersonalInfoInForm) form;
		// 过滤掉不正规的性别
		personalForm.setIdCardGender(DatabaseApiUtil.getGender(personalForm
				.getIdCardGender()));

		Map<String, Object> in = CollectionUtils.mapOf("in_Personal",
				(Object) form);
		return in;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
