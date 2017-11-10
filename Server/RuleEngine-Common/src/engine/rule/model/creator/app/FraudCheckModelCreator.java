package engine.rule.model.creator.app;

import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.common.MerchantUserActionType;
import catfish.base.business.dao.FraudRuleResultDetailsDao;
import catfish.base.business.dao.ManualFraudDao;
import catfish.base.business.object.FraudRuleResultDetailsObject;
import catfish.base.business.object.ManualFraudObject;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.app.FraudCheckInForm;
import engine.util.ApplicationHelper;

public class FraudCheckModelCreator extends AbstractApplicationModelCreator {

	private static final String ADMIN_FRAUD = "AdminFraudCheckResult";
	private static final String WEIXIN_FRAUD = "WeixinFraudCheckResult";

	public FraudCheckModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		form = new ModelBuilder<FraudCheckInForm>(new FraudCheckInForm())
				.buidDerivativeVariables(appId).getForm();
		FraudCheckInForm fraudForm = (FraudCheckInForm) form;

		List<FraudRuleResultDetailsObject> results = new FraudRuleResultDetailsDao(
				appId).getMultiple();
		for (FraudRuleResultDetailsObject fraudRuleResultObject : results) {
			if (hitFraudRule(fraudRuleResultObject, "A003"))
				fraudForm.setMobileNumberSimilarityCheck(true);
			if (hitFraudRule(fraudRuleResultObject, "A007"))
				fraudForm.setParentPhoneAreaCheck(true);
			if(fraudRuleResultObject.FraudId.equalsIgnoreCase("A010"))
				fraudForm.setWeixinHeadPhoto(fraudRuleResultObject.Status);
			else if (hitFraudRule(fraudRuleResultObject, "B002"))
				fraudForm.setSameStoreCompanyPrincipalUploadFileFastCheck(true);
			else if (hitFraudRule(fraudRuleResultObject, "B003"))
				fraudForm
						.setSameCommercialPrincipalRepaymentsApplicantFastCheck(true);
			else if (hitFraudRule(fraudRuleResultObject, "A005"))
				fraudForm.setA005(true);
			else if (hitFraudRule(fraudRuleResultObject, "A006"))
				fraudForm.setA006(true);
			else if (hitFraudRule(fraudRuleResultObject, "A013"))
				fraudForm.setA013(true);
			else if (hitFraudRule(fraudRuleResultObject, "A012"))
				fraudForm.setA012(true);
			else if (hitFraudRule(fraudRuleResultObject, "E001"))
				fraudForm.setE001(true);
			else if (hitFraudRule(fraudRuleResultObject, "E002"))
				fraudForm.setE002(true);
			else if (hitFraudRule(fraudRuleResultObject, "E003"))
				fraudForm.setE003(true);
			else if (hitFraudRule(fraudRuleResultObject, "E004"))
				fraudForm.setE004(true);
			else if (hitFraudRule(fraudRuleResultObject, "A014"))
				fraudForm.setA014(true);
			else if (hitFraudRule(fraudRuleResultObject, "A015"))
				fraudForm.setA015(true);
			else if (hitFraudRule(fraudRuleResultObject, "A016"))
				fraudForm.setA016(true);
			else if (hitFraudRule(fraudRuleResultObject, "B004"))
				fraudForm.setB004(true);
		}

		for (ManualFraudObject fraud : new ManualFraudDao(appId).getMultiple()) {
			switch (fraud.VariableName) {
			//This has been deprecated because the old version "Transaction monitor job" has not been added to the app workflow.
			case ADMIN_FRAUD:
				fraudForm
						.addManualCatfishAdminCheckResultSet(fraud.StringValue);
				break;
			case WEIXIN_FRAUD:
				fraudForm.addManualWeixinFraudCheckResultSet(fraud.IntValue);
				break;
			default:
				break;
			}
		}
		Integer warnActionType = ApplicationHelper.getMerchantWarnActionType(appId);
		fraudForm.setUserWarnedByMerchantUser(warnActionType != null);
		fraudForm.setMerchantWarnActionType(
		    warnActionType == null ? MerchantUserActionType.None.getValue() : warnActionType);

		this.form = fraudForm;
		generateDebugInfo();

		return CollectionUtils.mapOf("in_Fraud", (Object) form);
	}

	@Override
	public String createBusinessNo() {
		// TODO Auto-generated method stub
		return null;
	}

	private static boolean hitFraudRule(
			FraudRuleResultDetailsObject fraudRuleResultObject,
			String dstFraudId) {
		return fraudRuleResultObject.FraudId.equalsIgnoreCase(dstFraudId)
				&& fraudRuleResultObject.Status == FraudRuleResultStatus.Fail
						.getValue();
	}

  private void generateDebugInfo() {
    FraudCheckInForm fraudForm = (FraudCheckInForm) form;
    Logger.get().debug(String.format("AppId: %s, IsUserWarnedByMerchantUser: %s, MerchantWarnActionType: %s", 
    		appId,
    		fraudForm.isUserWarnedByMerchantUser(),
    		fraudForm.getMerchantWarnActionType()));
  }
}
