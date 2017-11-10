package engine.rule.model.in.cashloan;

import catfish.base.business.common.AppDerivativeVariableConsts;
import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "调查信息")
public class InvestigationInForm extends BaseForm{
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserPhoneCallResult")
	@ModelField(name = "CL_客户电话审核_电话是否正常接听", bindDomain = "engine.rule.domain.cashloan.CheckUserPhoneCallResultForCL")
	private Integer checkWhetherUserPhoneResultForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserPhoneAnswererResult")
	@ModelField(name = "CL_客户电话审核_通话人接听情况", bindDomain = "engine.rule.domain.cashloan.CheckUserPhoneAnswererResultForCL")
	private Integer checkWhetherUserPhoneAnswererResultForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserSceneIntroduceResult")
	@ModelField(name = "CL_客户电话审核_客户是否接受", bindDomain = "engine.rule.domain.cashloan.CheckUserSceneIntroduceResultForCL")
	private Integer checkWhetherUserPhoneSceneIntroduceResultForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserIsCancelApplication")
	@ModelField(name = "CL_客户电话审核_客户是否取消申请", bindDomain = "engine.rule.domain.cashloan.CheckUserIsCancelApplicationForCL")
	private Integer checkWhetherUserPhoneIsCancelApplicationForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserIdentification")
	@ModelField(name = "CL_客户电话审核_身份确认", bindDomain = "engine.rule.domain.cashloan.CheckUserIdentificationForCL")
	private Integer checkUserIdentificationForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserLivingAddress")
    @ModelField(name = "CL_客户电话审核_住宅地址确认", bindDomain = "engine.rule.domain.cashloan.CheckUserLivingAddressForCL")
    private Integer checkUserAddressForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserCompanyName")
	@ModelField(name = "CL_客户电话审核_工作单位确认", bindDomain = "engine.rule.domain.cashloan.CheckUserCompanyNameForCL")
	private Integer checkUserCompanyNameForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserSalary")
    @ModelField(name = "CL_客户电话审核_收入确认", bindDomain = "engine.rule.domain.cashloan.CheckUserSalaryForCL")
    private Integer checkUseSalaryForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserConsequenceIntroduceResult")
	@ModelField(name = "CL_客户电话审核_后果陈述", bindDomain = "engine.rule.domain.cashloan.CheckUserConsequenceIntroduceResultForCL")
	private Integer checkUseConsequenceIntroduceForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageHeadPhotoPersonalPic")
	@ModelField(name = "CL_头像照审核_自拍照片人脸是否清晰可辨识", bindDomain = "engine.rule.domain.cashloan.CheckImageHeadPhotoPersonalPicForCL")
	private Integer checkWhetherHeadPhotoRecognizableForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageComparision")
	@ModelField(name = "CL_头像照审核_三张照片是否一致", bindDomain = "engine.rule.domain.cashloan.CheckImageComparisionForCL")
	private Integer checkWhetherThreePhotoesConsistentForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_TransactionMonitorIsOneself")
    @ModelField(name = "CL_交易监控审核_是否本人", bindDomain = "engine.rule.domain.cashloan.TransactionMonitorForCL")
    private Integer checkTransactionMonitorIsOneselfForLTV = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    public Integer getCheckWhetherUserPhoneResultForLTV() {
      return checkWhetherUserPhoneResultForLTV;
    }

    public void setCheckWhetherUserPhoneResultForLTV(
        Integer checkWhetherUserPhoneResultForLTV) {
      this.checkWhetherUserPhoneResultForLTV = checkWhetherUserPhoneResultForLTV;
    }

    public Integer getCheckWhetherUserPhoneAnswererResultForLTV() {
      return checkWhetherUserPhoneAnswererResultForLTV;
    }

    public void setCheckWhetherUserPhoneAnswererResultForLTV(
        Integer checkWhetherUserPhoneAnswererResultForLTV) {
      this.checkWhetherUserPhoneAnswererResultForLTV = checkWhetherUserPhoneAnswererResultForLTV;
    }

    public Integer getCheckWhetherUserPhoneSceneIntroduceResultForLTV() {
      return checkWhetherUserPhoneSceneIntroduceResultForLTV;
    }

    public void setCheckWhetherUserPhoneSceneIntroduceResultForLTV(
        Integer checkWhetherUserPhoneSceneIntroduceResultForLTV) {
      this.checkWhetherUserPhoneSceneIntroduceResultForLTV = checkWhetherUserPhoneSceneIntroduceResultForLTV;
    }

    public Integer getCheckWhetherUserPhoneIsCancelApplicationForLTV() {
      return checkWhetherUserPhoneIsCancelApplicationForLTV;
    }

    public void setCheckWhetherUserPhoneIsCancelApplicationForLTV(
        Integer checkWhetherUserPhoneIsCancelApplicationForLTV) {
      this.checkWhetherUserPhoneIsCancelApplicationForLTV = checkWhetherUserPhoneIsCancelApplicationForLTV;
    }

    public Integer getCheckUserIdentificationForLTV() {
      return checkUserIdentificationForLTV;
    }

    public void setCheckUserIdentificationForLTV(
        Integer checkUserIdentificationForLTV) {
      this.checkUserIdentificationForLTV = checkUserIdentificationForLTV;
    }

    public Integer getCheckUserAddressForLTV() {
      return checkUserAddressForLTV;
    }

    public void setCheckUserAddressForLTV(Integer checkUserAddressForLTV) {
      this.checkUserAddressForLTV = checkUserAddressForLTV;
    }

    public Integer getCheckUserCompanyNameForLTV() {
      return checkUserCompanyNameForLTV;
    }

    public void setCheckUserCompanyNameForLTV(Integer checkUserCompanyNameForLTV) {
      this.checkUserCompanyNameForLTV = checkUserCompanyNameForLTV;
    }

    public Integer getCheckUseSalaryForLTV() {
      return checkUseSalaryForLTV;
    }

    public void setCheckUseSalaryForLTV(Integer checkUseSalaryForLTV) {
      this.checkUseSalaryForLTV = checkUseSalaryForLTV;
    }

    public Integer getCheckUseConsequenceIntroduceForLTV() {
      return checkUseConsequenceIntroduceForLTV;
    }

    public void setCheckUseConsequenceIntroduceForLTV(
        Integer checkUseConsequenceIntroduceForLTV) {
      this.checkUseConsequenceIntroduceForLTV = checkUseConsequenceIntroduceForLTV;
    }

    public Integer getCheckWhetherHeadPhotoRecognizableForLTV() {
      return checkWhetherHeadPhotoRecognizableForLTV;
    }

    public void setCheckWhetherHeadPhotoRecognizableForLTV(
        Integer checkWhetherHeadPhotoRecognizableForLTV) {
      this.checkWhetherHeadPhotoRecognizableForLTV = checkWhetherHeadPhotoRecognizableForLTV;
    }

    public Integer getCheckWhetherThreePhotoesConsistentForLTV() {
      return checkWhetherThreePhotoesConsistentForLTV;
    }

    public void setCheckWhetherThreePhotoesConsistentForLTV(
        Integer checkWhetherThreePhotoesConsistentForLTV) {
      this.checkWhetherThreePhotoesConsistentForLTV = checkWhetherThreePhotoesConsistentForLTV;
    }

    public Integer getCheckTransactionMonitorIsOneselfForLTV() {
        return checkTransactionMonitorIsOneselfForLTV;
    }

    public void setCheckTransactionMonitorIsOneselfForLTV(Integer checkTransactionMonitorIsOneselfForLTV) {
        this.checkTransactionMonitorIsOneselfForLTV = checkTransactionMonitorIsOneselfForLTV;
    }
}

