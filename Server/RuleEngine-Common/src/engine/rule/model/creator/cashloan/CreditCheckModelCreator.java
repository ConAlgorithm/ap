package engine.rule.model.creator.cashloan;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.cashloan.CreditCheckInForm;

public class CreditCheckModelCreator extends AbstractApplicationModelCreator {

	private Integer clusercreditrating;

	private Integer clappsuccesstimes;

	public CreditCheckModelCreator(String appId, Integer clusercreditrating, Integer clappsuccesstimes) {
		super(appId);
		this.clusercreditrating = clusercreditrating;
		this.clappsuccesstimes = clappsuccesstimes;

	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<CreditCheckInForm>(new CreditCheckInForm()).buidDerivativeVariables(appId).getForm();

		CreditCheckInForm creditCheckInForm = (CreditCheckInForm) form;
		creditCheckInForm.setClusercreditrating(clusercreditrating);
		creditCheckInForm.setClappsuccesstimes(clappsuccesstimes);
		return CollectionUtils.mapOf("in_CreditCheck", (Object) creditCheckInForm);
	}

	@Override
	public String createBusinessNo() {
		return null;
	}

}
