package engine.rule.model.creator.pdl;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.pdl.BehaviorInForm;

public class BehaviorModelCreator extends AbstractApplicationModelCreator {

	public BehaviorModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<BehaviorInForm>(new BehaviorInForm())
				.buidDerivativeVariables(appId).getForm();

		BehaviorInForm behaviorForm = (BehaviorInForm) form;
		InstallmentApplicationObject obj = new InstallmentApplicationDao(appId)
				.getSingle();
		behaviorForm.setApplicationTimestamp(obj.DateAdded);

		Map<String, Object> in = CollectionUtils.mapOf("in_Behavior",
				(Object) behaviorForm);
		return in;
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}
}
