package engine.rule.model.in.pdl;

import java.util.Set;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.util.CommonUtil;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "外部调查材料")
public class InvestigationInForm extends BaseForm {

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_OverdueRepaymentRemindPhoneCallResultForPDL")
    @ModelField(name = "PDL-逾期还款V3_电话是否正常接听", bindDomain = "engine.rule.domain.OverdueRepaymentRemindPhoneCallResultForPDLForPDL")
    private Integer overdueRepaymentRemindPhoneCallResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_OverdueRepaymentRemindPhoneAnswererResultForPDL")
    @ModelField(name = "PDL-逾期还款V3_通话人接听情况", bindDomain = "engine.rule.domain.OverdueRepaymentRemindPhoneAnswererResultForPDLForPDL")
    private Integer overdueRepaymentRemindPhoneAnswererResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_OverdueRepaymentRemindSceneIntroduceResultForPDL")
    @ModelField(name = "PDL-逾期还款V3_客户了解并接受", bindDomain = "engine.rule.domain.OverdueRepaymentRemindSceneIntroduceResultForPDLForPDL")
    private Integer overdueRepaymentRemindSceneIntroduceResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_OverdueRepaymentRemindIdentificationForPDL")
    @ModelField(name = "PDL-逾期还款V3_身份确认", bindDomain = "engine.rule.domain.OverdueRepaymentRemindIdentificationForPDLForPDL")
    private Integer overdueRepaymentRemindIdentificationForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_OverdueRepaymentRemindResultForPDL")
    @ModelField(name = "PDL-逾期还款V3_还款提醒结果", bindDomain = "engine.rule.domain.OverdueRepaymentRemindResultForPDLForPDL")
    private Integer overdueRepaymentRemindResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_OverdueRepaymentRemindCertificateResultForPDL")
    @ModelField(name = "PDL-逾期还款V3_凭证情况", bindDomain = "engine.rule.domain.OverdueRepaymentRemindCertificateResultForPDLForPDL")
    private Integer overdueRepaymentRemindCertificateResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_OverdueRepaymentRemindCareApplicantResultForPDL")
    @ModelField(name = "PDL-逾期还款V3_主观判断申请人对使用CF产品重视程度", bindDomain = "engine.rule.domain.OverdueRepaymentRemindCareApplicantResultForPDLForPDL")
    private Integer overdueRepaymentRemindCareApplicantResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_OverdueRepaymentRemindAttitudeOfPhoneResultForPDL")
    @ModelField(name = "PDL-逾期还款V3_主观判断联系人对本通电话反感程度", bindDomain = "engine.rule.domain.OverdueRepaymentRemindAttitudeOfPhoneResultForPDLForPDL")
    private Integer overdueRepaymentRemindAttitudeOfPhoneResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_InputIdCardInfoIsOtherCertProvidedForPDL")
    @ModelField(name = "PDL-身份信息录入_是否提供了其他证件", bindDomain = "engine.rule.domain.InputIdCardInfoIsOtherCertProvidedForPDLForPDL")
    private Integer inputIdCardInfoIsOtherCertProvidedForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_InputIdCardInfoOtherCertTypeForPDL")
    private Integer inputIdCardInfoOtherCertTypeForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBuckleIsBuckleAgreementForPDL")
    @ModelField(name = "PDL-代扣协议审核V3_检查上传照片是否为代扣协议", bindDomain = "engine.rule.domain.CheckBuckleIsBuckleAgreementForPDLForPDL")
    private Integer checkBuckleIsBuckleAgreementForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBuckleTextClarifyResultForPDL")
    @ModelField(name = "PDL-代扣协议审核V3_客户代扣协议照片是否清晰可以辨识", bindDomain = "engine.rule.domain.CheckBuckleTextClarifyResultForPDLForPDL")
    private Integer checkBuckleTextClarifyResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBuckleSignatureClarifyResultForPDL")
    @ModelField(name = "PDL-代扣协议审核V3_客户代扣协议照片中客户填写信息是否清晰可以辨认", bindDomain = "engine.rule.domain.CheckBuckleSignatureClarifyResultForPDLForPDL")
    private Integer checkBuckleSignatureClarifyResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBuckleDoesSignatureAndIdNameMatchForPDL")
    @ModelField(name = "PDL-代扣协议审核V3_客户代扣协议照片中客户填写信息与申请人信息是否一致", bindDomain = "engine.rule.domain.CheckBuckleDoesSignatureAndIdNameMatchForPDLForPDL")
    private Integer checkBuckleDoesSignatureAndIdNameMatchForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCourtExecutedForPDL")
    @ModelField(name = "PDL-法院被执行审核V3_法院被执行记录", bindDomain = "engine.rule.domain.CheckCourtExecutedForPDLForPDL")
    private Integer checkCourtExecutedForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_LoanMoneyResultForPDL")
    @ModelField(name = "PDL-放款V3_转账情况", bindDomain = "engine.rule.domain.LoanMoneyResultForPDLForPDL")
    private Integer loanMoneyResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_LoanMoneyFailureReasonPDL")
    @ModelField(name = "PDL-放款V3_转账失败原因", bindDomain = "engine.rule.domain.LoanMoneyFailureReasonPDLForPDL")
    private Integer loanMoneyFailureReasonPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_FinishApplicationOperationForPDL")
    @ModelField(name = "PDL-结束申请V3_操作", bindDomain = "engine.rule.domain.FinishApplicationOperationForPDLForPDL")
    private Integer finishApplicationOperationForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckDafyRecordForPDL")
    @ModelField(name = "PDL-达飞和好贷审核V3_达飞记录", bindDomain = "engine.rule.domain.CheckDafyRecordForPDLForPDL")
    private Integer checkDafyRecordForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckHaodaiRecordForPDL")
    @ModelField(name = "PDL-达飞和好贷审核V3_好贷记录", bindDomain = "engine.rule.domain.CheckHaodaiRecordForPDLForPDL")
    private Integer checkHaodaiRecordForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCreditEaseRecordForPDL")
    @ModelField(name = "PDL-宜信审核V3_宜信记录", bindDomain = "engine.rule.domain.CheckCreditEaseRecordForPDLForPDL")
    private Integer checkCreditEaseRecordForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckVCashRecordForPDL")
    @ModelField(name = "PDL-维信审核V3_维信记录", bindDomain = "engine.rule.domain.CheckVCashRecordForPDLForPDL")
    private Integer checkVCashRecordForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageHeadPhotoPersonalPicForPDL")
    @ModelField(name = "PDL-现场照片审核V3_现场照片人脸是否清晰可辨识", bindDomain = "engine.rule.domain.CheckImageHeadPhotoPersonalPicForPDLForPDL")
    private Integer checkImageHeadPhotoPersonalPicForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckIsSalesmanExistedForPDL")
    @ModelField(name = "PDL-现场照片审核V3_是否有销售", bindDomain = "engine.rule.domain.CheckIsSalesmanExistedForPDLForPDL")
    private Integer checkIsSalesmanExistedForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageHeadPhotoHeadDirectionForPDL")
    @ModelField(name = "PDL-现场照片审核V3_头像现场照片头像方向", bindDomain = "engine.rule.domain.CheckImageHeadPhotoHeadDirectionForPDLForPDL")
    private Integer checkImageHeadPhotoHeadDirectionForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageHeadPhotoFaceExpressionForPDL")
    @ModelField(name = "PDL-现场照片审核V3_头像现场照片表情", bindDomain = "engine.rule.domain.CheckImageHeadPhotoFaceExpressionForPDLForPDL")
    private Integer checkImageHeadPhotoFaceExpressionForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherImageIsIdCardPhotoForPDL")
    @ModelField(name = "PDL-身份证照审核V3_是否是身份证", bindDomain = "engine.rule.domain.CheckWhetherImageIsIdCardPhotoForPDLForPDL")
    private Integer checkWhetherImageIsIdCardPhotoForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageIdCardPersonalPicForPDL")
    @ModelField(name = "PDL-身份证照审核V3_身份证件是否清晰可辨识", bindDomain = "engine.rule.domain.CheckImageIdCardPersonalPicForPDLForPDL")
    private Integer checkImageIdCardPersonalPicForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageIdCardInfoForPDL")
    @ModelField(name = "PDL-身份证照审核V3_身份证信息是否可以辨认", bindDomain = "engine.rule.domain.CheckImageIdCardInfoForPDLForPDL")
    private Integer checkImageIdCardInfoForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImagePhotoFromPoliceExistForPDL")
    @ModelField(name = "PDL-头像对比V3_公安部照片是否存在", bindDomain = "engine.rule.domain.CheckImagePhotoFromPoliceExistForPDLForPDL")
    private Integer checkImagePhotoFromPoliceExistForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageComparisionForPDL")
    @ModelField(name = "PDL-头像对比V3_三张照片是否一致", bindDomain = "engine.rule.domain.CheckImageComparisionForPDLForPDL")
    private Integer checkImageComparisionForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckHomeCreditRecordForPDL")
    @ModelField(name = "PDL-捷信审核V3_捷信记录", bindDomain = "engine.rule.domain.CheckHomeCreditRecordForPDLForPDL")
    private Integer checkHomeCreditRecordForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherImageIsWorkPermitForPDL")
    @ModelField(name = "PDL-工作证检查_是否是工作证", bindDomain = "engine.rule.domain.CheckWhetherImageIsWorkPermitForPDLForPDL")
    private Integer checkWhetherImageIsWorkPermitForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherImageIsConsistentWithTemplateWorkPermitForPDL")
    @ModelField(name = "PDL-工作证检查_工作证合规", bindDomain = "engine.rule.domain.CheckWhetherImageIsConsistentWithTemplateWorkPermitForPDLForPDL")
    private Integer checkWhetherImageIsConsistentWithTemplateWorkPermitForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL")
    @ModelField(name = "PDL-工作证检查_工作证姓名与身份证姓名比对", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitNameIsConsistentWithIDCardNameForPDLForPDL")
    private Integer checkWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL")
    @ModelField(name = "PDL-工作证检查_公司名/部门信息比对", bindDomain = "engine.rule.domain.CheckWhetherCompanyNameAndDepartmentIsConsistentForPDLForPDL")
    private Integer checkWhetherCompanyNameAndDepartmentIsConsistentForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitOfficialSealConsistentForPDL")
@ModelField(name = "PDL-工作证检查_公章", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitOfficialSealConsistentForPDLForPDL")
private Integer checkWhetherWorkPermitOfficialSealConsistentForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitConsistentForPDL")
    @ModelField(name = "PDL-工作证检查_工作证", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitConsistentForPDLForPDL")
    private Integer checkWhetherWorkPermitConsistentForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsBadgeForPDL")
@ModelField(name = "PDL-胸牌审核_是否是正面胸牌", bindDomain = "engine.rule.domain.IsBadgeForPDLForPDL")
private Integer isBadgeForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBadgeRecognizableForPDL")
@ModelField(name = "PDL-胸牌审核_胸牌是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherBadgeRecognizableForPDLForPDL")
private Integer checkWhetherBadgeRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBadgeOnBreastForPDL")
@ModelField(name = "PDL-胸牌审核_胸牌是否在客户胸前佩戴", bindDomain = "engine.rule.domain.CheckWhetherBadgeOnBreastForPDLForPDL")
private Integer checkWhetherBadgeOnBreastForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBadgeHasClientNameForPDL")
@ModelField(name = "PDL-胸牌审核_是否有姓名", bindDomain = "engine.rule.domain.CheckWhetherBadgeHasClientNameForPDLForPDL")
private Integer checkWhetherBadgeHasClientNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBadgeHasCompanyNameForPDL")
@ModelField(name = "PDL-胸牌审核_是否有单位名称", bindDomain = "engine.rule.domain.CheckWhetherBadgeHasCompanyNameForPDLForPDL")
private Integer checkWhetherBadgeHasCompanyNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBadgeHasJobNumberForPDL")
@ModelField(name = "PDL-胸牌审核_是否有员工编号-工号", bindDomain = "engine.rule.domain.CheckWhetherBadgeHasJobNumberForPDLForPDL")
private Integer checkWhetherBadgeHasJobNumberForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBadgeHasJobTitleForPDL")
@ModelField(name = "PDL-胸牌审核_是否有职位信息", bindDomain = "engine.rule.domain.CheckWhetherBadgeHasJobTitleForPDLForPDL")
private Integer checkWhetherBadgeHasJobTitleForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsWorkPermitOrLicenseForPDL")
@ModelField(name = "PDL-工作证-上岗证审核_是否是正面工作证-上岗证", bindDomain = "engine.rule.domain.IsWorkPermitOrLicenseForPDLForPDL")
private Integer isWorkPermitOrLicenseForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitOrLicenseRecognizableForPDL")
@ModelField(name = "PDL-工作证-上岗证审核_工作证-上岗证是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitOrLicenseRecognizableForPDLForPDL")
private Integer checkWhetherWorkPermitOrLicenseRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitOrLicenseHasCompanyNameForPDL")
@ModelField(name = "PDL-工作证-上岗证审核_是否有单位名称", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitOrLicenseHasCompanyNameForPDLForPDL")
private Integer checkWhetherWorkPermitOrLicenseHasCompanyNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitOrLicenseHasClientNameForPDL")
@ModelField(name = "PDL-工作证-上岗证审核_是否有姓名", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitOrLicenseHasClientNameForPDLForPDL")
private Integer checkWhetherWorkPermitOrLicenseHasClientNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitOrLicenseHasHeadShotForPDL")
@ModelField(name = "PDL-工作证-上岗证审核_是否有头像", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitOrLicenseHasHeadShotForPDLForPDL")
private Integer checkWhetherWorkPermitOrLicenseHasHeadShotForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitOrLicenseHasJobNumberForPDL")
@ModelField(name = "PDL-工作证-上岗证审核_是否有员工编号-工号", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitOrLicenseHasJobNumberForPDLForPDL")
private Integer checkWhetherWorkPermitOrLicenseHasJobNumberForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitOrLicenseHasDepartmentForPDL")
@ModelField(name = "PDL-工作证-上岗证审核_是否有部门信息", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitOrLicenseHasDepartmentForPDLForPDL")
private Integer checkWhetherWorkPermitOrLicenseHasDepartmentForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkPermitOrLicenseHasJobTitleForPDL")
@ModelField(name = "PDL-工作证-上岗证审核_是否有职位信息", bindDomain = "engine.rule.domain.CheckWhetherWorkPermitOrLicenseHasJobTitleForPDLForPDL")
private Integer checkWhetherWorkPermitOrLicenseHasJobTitleForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsTimeCardForPDL")
@ModelField(name = "PDL-考勤卡审核_是否是正面考勤卡", bindDomain = "engine.rule.domain.IsTimeCardForPDLForPDL")
private Integer isTimeCardForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherTimeCardRecognizableForPDL")
@ModelField(name = "PDL-考勤卡审核_考勤卡是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherTimeCardRecognizableForPDLForPDL")
private Integer checkWhetherTimeCardRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherTimeCardHasCompanySealForPDL")
@ModelField(name = "PDL-考勤卡审核_是否有单位印章", bindDomain = "engine.rule.domain.CheckWhetherTimeCardHasCompanySealForPDLForPDL")
private Integer checkWhetherTimeCardHasCompanySealForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherTimeCardHasClientNameForPDL")
@ModelField(name = "PDL-考勤卡审核_是否有姓名", bindDomain = "engine.rule.domain.CheckWhetherTimeCardHasClientNameForPDLForPDL")
private Integer checkWhetherTimeCardHasClientNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherTimeCardHasRecordForPDL")
@ModelField(name = "PDL-考勤卡审核_是否有考勤记录", bindDomain = "engine.rule.domain.CheckWhetherTimeCardHasRecordForPDLForPDL")
private Integer checkWhetherTimeCardHasRecordForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherTimeCardTheLatestDateForPDL")
@ModelField(name = "PDL-考勤卡审核_考勤卡日期是否为当月", bindDomain = "engine.rule.domain.CheckWhetherTimeCardTheLatestDateForPDLForPDL")
private Integer checkWhetherTimeCardTheLatestDateForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherTimeCardJobNumberForPDL")
@ModelField(name = "PDL-考勤卡审核_是否有员工编号-工号", bindDomain = "engine.rule.domain.CheckWhetherTimeCardJobNumberForPDLForPDL")
private Integer checkWhetherTimeCardJobNumberForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsWorkClothForPDL")
@ModelField(name = "PDL-工作服审核_是否是全身工作服照片", bindDomain = "engine.rule.domain.IsWorkClothForPDLForPDL")
private Integer isWorkClothForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkClothRecognizableForPDL")
@ModelField(name = "PDL-工作服审核_工作服是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherWorkClothRecognizableForPDLForPDL")
private Integer checkWhetherWorkClothRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherClientInWorkClothForPDL")
@ModelField(name = "PDL-工作服审核_是否为客户本人穿着工作服", bindDomain = "engine.rule.domain.CheckWhetherClientInWorkClothForPDLForPDL")
private Integer checkWhetherClientInWorkClothForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherWorkClothFitWithClientJobForPDL")
@ModelField(name = "PDL-工作服审核_工作服与职业性质是否相符", bindDomain = "engine.rule.domain.CheckWhetherWorkClothFitWithClientJobForPDLForPDL")
private Integer checkWhetherWorkClothFitWithClientJobForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsHealthCertificateForPDL")
@ModelField(name = "PDL-健康证审核_是否是正面健康证", bindDomain = "engine.rule.domain.IsHealthCertificateForPDLForPDL")
private Integer isHealthCertificateForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateRecognizableForPDL")
@ModelField(name = "PDL-健康证审核_健康证是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateRecognizableForPDLForPDL")
private Integer checkWhetherHealthCertificateRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateHasClientNameForPDL")
@ModelField(name = "PDL-健康证审核_是否有姓名", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateHasClientNameForPDLForPDL")
private Integer checkWhetherHealthCertificateHasClientNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateHasHeadShotForPDL")
@ModelField(name = "PDL-健康证审核_是否有头像", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateHasHeadShotForPDLForPDL")
private Integer checkWhetherHealthCertificateHasHeadShotForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateHasGenderForPDL")
@ModelField(name = "PDL-健康证审核_是否有性别", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateHasGenderForPDLForPDL")
private Integer checkWhetherHealthCertificateHasGenderForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateIDNumberForPDL")
@ModelField(name = "PDL-健康证审核_是否有身份证号码", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateIDNumberForPDLForPDL")
private Integer checkWhetherHealthCertificateIDNumberForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateHasAgeForPDL")
@ModelField(name = "PDL-健康证审核_是否有年龄", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateHasAgeForPDLForPDL")
private Integer checkWhetherHealthCertificateHasAgeForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateExaminationResultForPDL")
@ModelField(name = "PDL-健康证审核_是否有体检结果", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateExaminationResultForPDLForPDL")
private Integer checkWhetherHealthCertificateExaminationResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateExaminationTimeForPDL")
@ModelField(name = "PDL-健康证审核_是否有体检时间-发证时间", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateExaminationTimeForPDLForPDL")
private Integer checkWhetherHealthCertificateExaminationTimeForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherHealthCertificateExaminationNumberForPD")
@ModelField(name = "PDL-健康证审核_是否有编号", bindDomain = "engine.rule.domain.CheckWhetherHealthCertificateExaminationNumberForPDForPDL")
private Integer checkWhetherHealthCertificateExaminationNumberForPD = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsSocialSecurityCardForPDL")
@ModelField(name = "PDL-社保卡审核_是否是正面社保卡", bindDomain = "engine.rule.domain.IsSocialSecurityCardForPDLForPDL")
private Integer isSocialSecurityCardForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardRecognizableForPDL")
@ModelField(name = "PDL-社保卡审核_社保卡是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardRecognizableForPDLForPDL")
private Integer checkWhetherSocialSecurityCardRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardHasClientNameForPDL")
@ModelField(name = "PDL-社保卡审核_是否有姓名", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardHasClientNameForPDLForPDL")
private Integer checkWhetherSocialSecurityCardHasClientNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardHasHeadShotForPDL")
@ModelField(name = "PDL-社保卡审核_是否有头像", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardHasHeadShotForPDLForPDL")
private Integer checkWhetherSocialSecurityCardHasHeadShotForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardHasGenderForPDL")
@ModelField(name = "PDL-社保卡审核_是否有性别", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardHasGenderForPDLForPDL")
private Integer checkWhetherSocialSecurityCardHasGenderForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardHasHasNationForPDL")
@ModelField(name = "PDL-社保卡审核_是否有民族", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardHasHasNationForPDLForPDL")
private Integer checkWhetherSocialSecurityCardHasHasNationForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardIDNumberForPDL")
@ModelField(name = "PDL-社保卡审核_是否有身份证号码", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardIDNumberForPDLForPDL")
private Integer checkWhetherSocialSecurityCardIDNumberForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardHasHasBankCardNoForPDL")
@ModelField(name = "PDL-社保卡审核_是否有卡号", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardHasHasBankCardNoForPDLForPDL")
private Integer checkWhetherSocialSecurityCardHasHasBankCardNoForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardHasHasIssuingDateForPDL")
@ModelField(name = "PDL-社保卡审核_是否有发卡日期", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardHasHasIssuingDateForPDLForPDL")
private Integer checkWhetherSocialSecurityCardHasHasIssuingDateForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSocialSecurityCardHasHasValidDateForPDL")
@ModelField(name = "PDL-社保卡审核_是否有有效期限-有效日期", bindDomain = "engine.rule.domain.CheckWhetherSocialSecurityCardHasHasValidDateForPDLForPDL")
private Integer checkWhetherSocialSecurityCardHasHasValidDateForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsSalaryMessageForPDL")
@ModelField(name = "PDL-工资到账短信审核_是否是工资到账短信截屏照", bindDomain = "engine.rule.domain.IsSalaryMessageForPDLForPDL")
private Integer isSalaryMessageForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSalaryMessageRecognizableForPDL")
@ModelField(name = "PDL-工资到账短信审核_工资短信是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherSalaryMessageRecognizableForPDLForPDL")
private Integer checkWhetherSalaryMessageRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSalaryMessageHasBankCardTheLastFourNoForPDL")
@ModelField(name = "PDL-工资到账短信审核_是否有银行卡尾号后四位", bindDomain = "engine.rule.domain.CheckWhetherSalaryMessageHasBankCardTheLastFourNoForPDLForPDL")
private Integer checkWhetherSalaryMessageHasBankCardTheLastFourNoForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSalaryMessageHasBankNameForPDL")
@ModelField(name = "PDL-工资到账短信审核_是否有银行名称", bindDomain = "engine.rule.domain.CheckWhetherSalaryMessageHasBankNameForPDLForPDL")
private Integer checkWhetherSalaryMessageHasBankNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSalaryMessageHasDateForPDL")
@ModelField(name = "PDL-工资到账短信审核_是否有日期", bindDomain = "engine.rule.domain.CheckWhetherSalaryMessageHasDateForPDLForPDL")
private Integer checkWhetherSalaryMessageHasDateForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSalaryMessageHasSalaryAmountForPDL")
@ModelField(name = "PDL-工资到账短信审核_是否有收入金额", bindDomain = "engine.rule.domain.CheckWhetherSalaryMessageHasSalaryAmountForPDLForPDL")
private Integer checkWhetherSalaryMessageHasSalaryAmountForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSalaryMessageHasSenderInfoForPDL")
@ModelField(name = "PDL-工资到账短信审核_是否有发件人信息", bindDomain = "engine.rule.domain.CheckWhetherSalaryMessageHasSenderInfoForPDLForPDL")
private Integer checkWhetherSalaryMessageHasSenderInfoForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherImageIsGroupPhotoForPDL")
    @ModelField(name = "PDL-合影检查_是否是合影", bindDomain = "engine.rule.domain.CheckWhetherImageIsGroupPhotoForPDLForPDL")
    private Integer checkWhetherImageIsGroupPhotoForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherGroupPhotoIsRecognizableForPDL")
    @ModelField(name = "PDL-合影检查_合影是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherGroupPhotoIsRecognizableForPDLForPDL")
    private Integer checkWhetherGroupPhotoIsRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherCustomerPhotoIsTheSamePersonForPDL")
    @ModelField(name = "PDL-合影检查_客户照片判断（是否为同一个人）", bindDomain = "engine.rule.domain.CheckWhetherCustomerPhotoIsTheSamePersonForPDLForPDL")
    private Integer checkWhetherCustomerPhotoIsTheSamePersonForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherSalesmanPhotoIsTheSamePersonForPDL")
    @ModelField(name = "PDL-合影检查_销售照片判断（是否为同一个人）", bindDomain = "engine.rule.domain.CheckWhetherSalesmanPhotoIsTheSamePersonForPDLForPDL")
    private Integer checkWhetherSalesmanPhotoIsTheSamePersonForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactPhoneCallResultForPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckFirstContactPhoneCallResultForPDLForPDL")
    private Integer checkFirstContactPhoneCallResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactPhoneAnswererResultForPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckFirstContactPhoneAnswererResultForPDLForPDL")
    private Integer checkFirstContactPhoneAnswererResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactSceneIntroduceResultForPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_客户是否接受", bindDomain = "engine.rule.domain.CheckFirstContactSceneIntroduceResultForPDLForPDL")
    private Integer checkFirstContactSceneIntroduceResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactIdentificationResultForPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckFirstContactIdentificationResultForPDLForPDL")
    private Integer checkFirstContactIdentificationResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactZodiacResultForPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_关系核身-属相($(Relation))", bindDomain = "engine.rule.domain.CheckFirstContactZodiacResultForPDLForPDL")
    private Integer checkFirstContactZodiacResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactMarriageStatusResultForPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_关系核身婚姻状况($(Relation))", bindDomain = "engine.rule.domain.CheckFirstContactMarriageStatusResultForPDLForPDL")
    private Integer checkFirstContactMarriageStatusResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactCompanyNameResultPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_关系核身单位名称($(Relation))", bindDomain = "engine.rule.domain.CheckFirstContactCompanyNameResultPDLForPDL")
    private Integer checkFirstContactCompanyNameResultPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactRiskPromptResultForPDL")
    private Integer checkFirstContactRiskPromptResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactSoundForPDL")
    private Integer checkFirstContactSoundForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactCareApplicantResultForPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckFirstContactCareApplicantResultForPDLForPDL")
    private Integer checkFirstContactCareApplicantResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactAttitudeOfPhoneResultForPDL")
    @ModelField(name = "PDL-第一联系人电话审核V3_主观判断联系人对本通电话反感程度", bindDomain = "engine.rule.domain.CheckFirstContactAttitudeOfPhoneResultForPDLForPDL")
    private Integer checkFirstContactAttitudeOfPhoneResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactPhoneCallResultForPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckSecondContactPhoneCallResultForPDLForPDL")
    private Integer checkSecondContactPhoneCallResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactPhoneAnswererResultForPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckSecondContactPhoneAnswererResultForPDLForPDL")
    private Integer checkSecondContactPhoneAnswererResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactSceneIntroduceResultForPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_客户是否接受", bindDomain = "engine.rule.domain.CheckSecondContactSceneIntroduceResultForPDLForPDL")
    private Integer checkSecondContactSceneIntroduceResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactIdentificationResultForPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckSecondContactIdentificationResultForPDLForPDL")
    private Integer checkSecondContactIdentificationResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactZodiacResultForPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_关系核身属相($(Relation))", bindDomain = "engine.rule.domain.CheckSecondContactZodiacResultForPDLForPDL")
    private Integer checkSecondContactZodiacResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactMarriageStatusResultForPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_关系核身婚姻状况($(Relation))", bindDomain = "engine.rule.domain.CheckSecondContactMarriageStatusResultForPDLForPDL")
    private Integer checkSecondContactMarriageStatusResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactCompanyNameResultPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_关系核身单位名称($(Relation))", bindDomain = "engine.rule.domain.CheckSecondContactCompanyNameResultPDLForPDL")
    private Integer checkSecondContactCompanyNameResultPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactRiskPromptResultForPDL")
    private Integer checkSecondContactRiskPromptResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactSoundForPDL")
    private Integer checkSecondContactSoundForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactCareApplicantResultForPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckSecondContactCareApplicantResultForPDLForPDL")
    private Integer checkSecondContactCareApplicantResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactAttitudeOfPhoneResultForPDL")
    @ModelField(name = "PDL-第二联系人电话审核V3_主观判断联系人对本通电话反感程度", bindDomain = "engine.rule.domain.CheckSecondContactAttitudeOfPhoneResultForPDLForPDL")
    private Integer checkSecondContactAttitudeOfPhoneResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactPhoneCallResultForPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckThirdContactPhoneCallResultForPDLForPDL")
    private Integer checkThirdContactPhoneCallResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactPhoneAnswererResultForPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckThirdContactPhoneAnswererResultForPDLForPDL")
    private Integer checkThirdContactPhoneAnswererResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactSceneIntroduceResultForPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_客户是否接受", bindDomain = "engine.rule.domain.CheckThirdContactSceneIntroduceResultForPDLForPDL")
    private Integer checkThirdContactSceneIntroduceResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactIdentificationResultForPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckThirdContactIdentificationResultForPDLForPDL")
    private Integer checkThirdContactIdentificationResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactZodiacResultForPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_关系核身属相($(Relation))", bindDomain = "engine.rule.domain.CheckThirdContactZodiacResultForPDLForPDL")
    private Integer checkThirdContactZodiacResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactMarriageStatusResultForPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_关系核身婚姻状况($(Relation))", bindDomain = "engine.rule.domain.CheckThirdContactMarriageStatusResultForPDLForPDL")
    private Integer checkThirdContactMarriageStatusResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactCompanyNameResultPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_关系核身单位名称($(Relation))", bindDomain = "engine.rule.domain.CheckThirdContactCompanyNameResultPDLForPDL")
    private Integer checkThirdContactCompanyNameResultPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactRiskPromptResultForPDL")
    private Integer checkThirdContactRiskPromptResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactSoundForPDL")
    private Integer checkThirdContactSoundForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactCareApplicantResultForPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckThirdContactCareApplicantResultForPDLForPDL")
    private Integer checkThirdContactCareApplicantResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactAttitudeOfPhoneResultForPDL")
    @ModelField(name = "PDL-第三联系人电话审核V3_主观判断联系人对本通电话反感程度", bindDomain = "engine.rule.domain.CheckThirdContactAttitudeOfPhoneResultForPDLForPDL")
    private Integer checkThirdContactAttitudeOfPhoneResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_WelcomeCallPhoneStatusForPDL")
    @ModelField(name = "PDL-欢迎电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.WelcomeCallPhoneStatusForPDLForPDL")
    private Integer welcomeCallPhoneStatusForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_WelcomeCallPhoneAnswererResultForPDL")
    @ModelField(name = "PDL-欢迎电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.WelcomeCallPhoneAnswererResultForPDLForPDL")
    private Integer welcomeCallPhoneAnswererResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_WelcomeCallSceneFeedbackForPDL")
    @ModelField(name = "PDL-欢迎电话审核V3_客户是否接受", bindDomain = "engine.rule.domain.WelcomeCallSceneFeedbackForPDLForPDL")
    private Integer welcomeCallSceneFeedbackForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_WelcomeCallPaybackInfoConfirmationForPDL")
    @ModelField(name = "PDL-欢迎电话审核V3_还款信息确认", bindDomain = "engine.rule.domain.WelcomeCallPaybackInfoConfirmationForPDLForPDL")
    private Integer welcomeCallPaybackInfoConfirmationForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_WelcomeCallCustomerAttitudeResultForPDL")
    @ModelField(name = "PDL-欢迎电话审核V3_客户态度", bindDomain = "engine.rule.domain.WelcomeCallCustomerAttitudeResultForPDLForPDL")
    private Integer welcomeCallCustomerAttitudeResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_RepaymentRemindPhoneCallResultForPDL")
    @ModelField(name = "PDL-还款提醒_电话是否正常接听", bindDomain = "engine.rule.domain.RepaymentRemindPhoneCallResultForPDLForPDL")
    private Integer repaymentRemindPhoneCallResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_RepaymentRemindIdentificationResultForPDL")
    @ModelField(name = "PDL-还款提醒_通话人接听情况", bindDomain = "engine.rule.domain.RepaymentRemindIdentificationResultForPDLForPDL")
    private Integer repaymentRemindIdentificationResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_FirstRepaymentRemindSceneResultForPDL")
    @ModelField(name = "PDL-还款提醒_客户接受（首次）", bindDomain = "engine.rule.domain.FirstRepaymentRemindSceneResultForPDLForPDL")
    private Integer firstRepaymentRemindSceneResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_SecondRepaymentRemindSceneResultForPDL")
    @ModelField(name = "PDL-还款提醒_客户接受（非首次）", bindDomain = "engine.rule.domain.SecondRepaymentRemindSceneResultForPDLForPDL")
    private Integer secondRepaymentRemindSceneResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_RepaymentRemindPaybackConfirmationForPDL")
    @ModelField(name = "PDL-还款提醒_还款信息确认", bindDomain = "engine.rule.domain.RepaymentRemindPaybackConfirmationForPDLForPDL")
    private Integer repaymentRemindPaybackConfirmationForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_RepaymentRemindCustomerAttitudeResultForPDL")
    @ModelField(name = "PDL-还款提醒_客户态度", bindDomain = "engine.rule.domain.RepaymentRemindCustomerAttitudeResultForPDLForPDL")
    private Integer repaymentRemindCustomerAttitudeResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserPhoneCallResultForPDL")
    @ModelField(name = "PDL-客户电话审核_电话是否正常接听", bindDomain = "engine.rule.domain.CheckUserPhoneCallResultForPDLForPDL")
    private Integer checkUserPhoneCallResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserPhoneAnswererResultForPDL")
    @ModelField(name = "PDL-客户电话审核_通话人接听情况", bindDomain = "engine.rule.domain.CheckUserPhoneAnswererResultForPDLForPDL")
    private Integer checkUserPhoneAnswererResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserSceneIntroduceResultForPDL")
    @ModelField(name = "PDL-客户电话审核_客户是否接受", bindDomain = "engine.rule.domain.CheckUserSceneIntroduceResultForPDLForPDL")
    private Integer checkUserSceneIntroduceResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserIdentificationForPDL")
    @ModelField(name = "PDL-客户电话审核_身份确认", bindDomain = "engine.rule.domain.CheckUserIdentificationForPDLForPDL")
    private Integer checkUserIdentificationForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserCompanyPaydayForPDL")
    @ModelField(name = "PDL-客户电话审核_单位信息核实", bindDomain = "engine.rule.domain.CheckUserCompanyPaydayForPDLForPDL")
    private Integer checkUserCompanyPaydayForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserSymbolicAnimalForPDL")
    @ModelField(name = "PDL-客户电话审核_生肖确认", bindDomain = "engine.rule.domain.CheckUserSymbolicAnimalForPDLForPDL")
    private Integer checkUserSymbolicAnimalForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserCompanyNameForPDL")
    @ModelField(name = "PDL-客户电话审核_单位名称确认", bindDomain = "engine.rule.domain.CheckUserCompanyNameForPDLForPDL")
    private Integer checkUserCompanyNameForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserIDCardAddressForPDL")
    @ModelField(name = "PDL-客户电话审核_身份证住址确认", bindDomain = "engine.rule.domain.CheckUserIDCardAddressForPDLForPDL")
    private Integer checkUserIDCardAddressForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserConsequenceIntroduceResultForPDL")
    @ModelField(name = "PDL-客户电话审核_后果陈述", bindDomain = "engine.rule.domain.CheckUserConsequenceIntroduceResultForPDLForPDL")
    private Integer checkUserConsequenceIntroduceResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserRiskRevealForPDL")   
    private Integer checkUserRiskRevealForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserSoundForPDL")
    private Integer checkUserSoundForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserCarenessLevelForPDL")
    @ModelField(name = "PDL-客户电话审核_主观判断申请人对使用", bindDomain = "engine.rule.domain.CheckUserCarenessLevelForPDLForPDL")
    private Integer checkUserCarenessLevelForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserDislikeLevelForPDL")
    @ModelField(name = "PDL-客户电话审核_主观判断联系人对本通电话反感程度", bindDomain = "engine.rule.domain.CheckUserDislikeLevelForPDLForPDL")
    private Integer checkUserDislikeLevelForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherImageIsBankCardForPDL")
    @ModelField(name = "PDL-银行卡审核_是否是银行卡", bindDomain = "engine.rule.domain.CheckWhetherImageIsBankCardForPDLForPDL")
    private Integer checkWhetherImageIsBankCardForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBankCardPhotoIsRecognizableForPDL")
    @ModelField(name = "PDL-银行卡审核_是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherBankCardPhotoIsRecognizableForPDLForPDL")
    private Integer checkWhetherBankCardPhotoIsRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBankCardInfoIsRecognizableForPDL")
    @ModelField(name = "PDL-银行卡审核_卡片审核信息是否可以辨认", bindDomain = "engine.rule.domain.CheckWhetherBankCardInfoIsRecognizableForPDLForPDL")
    private Integer checkWhetherBankCardInfoIsRecognizableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_TransactionMonitorResultForPDL")
    @ModelField(name = "PDL-交易监控_判断结果", bindDomain = "engine.rule.domain.TransactionMonitorResultForPDLForPDL")
    private Integer transactionMonitorResultForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_TransactionMonitorRejectReasonForPDL")
    @ModelField(name = "PDL-交易监控_拒绝原因", bindDomain = "engine.rule.domain.TransactionMonitorRejectReasonForPDLForPDL")
    private Integer transactionMonitorRejectReasonForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsElectronicNoticeForPDL")
    @ModelField(name = "PDL-电子告知书检查_是否是电子告知书", bindDomain = "engine.rule.domain.IsElectronicNoticeForPDLForPDL")
    private Integer isElectronicNoticeForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsElectronicNoticeDistinguishableForPDL")
    @ModelField(name = "PDL-电子告知书检查_是否清晰可辨识", bindDomain = "engine.rule.domain.IsElectronicNoticeDistinguishableForPDLForPDL")
    private Integer isElectronicNoticeDistinguishableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsElectronicNoticeInformationDistinguishableForPDL")
    @ModelField(name = "PDL-电子告知书检查_电子告知书审核信息是否可以辨认", bindDomain = "engine.rule.domain.IsElectronicNoticeInformationDistinguishableForPDLForPDL")
    private Integer isElectronicNoticeInformationDistinguishableForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_ElectronicNoticetwoPhotosComparisonForPDL")
    @ModelField(name = "PDL-电子告知书检查_两张照片比对", bindDomain = "engine.rule.domain.ElectronicNoticetwoPhotosComparisonForPDLForPDL")
    private Integer electronicNoticetwoPhotosComparisonForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsElectronicNoticeFourVerificationCodeExistForPDL")
    @ModelField(name = "PDL-电子告知书检查_4位验证码审核（是否存在）", bindDomain = "engine.rule.domain.IsElectronicNoticeFourVerificationCodeExistForPDLForPDL")
    private Integer isElectronicNoticeFourVerificationCodeExistForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsElectronicNoticeFourVerificationCodeConsistentForPDL")
    @ModelField(name = "PDL-电子告知书检查_4位验证码审核（是否一致）", bindDomain = "engine.rule.domain.IsElectronicNoticeFourVerificationCodeConsistentForPDLForPDL")
    private Integer isElectronicNoticeFourVerificationCodeConsistentForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckElectronicNoticePhoneColorForPDL")
    @ModelField(name = "PDL-电子告知书检查_检查客户电子告知书的手机外壳颜色", bindDomain = "engine.rule.domain.CheckElectronicNoticePhoneColorForPDLForPDL")
    private Integer checkElectronicNoticePhoneColorForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckElectronicNoticeRingFingerForPDL")
    @ModelField(name = "PDL-电子告知书检查_检查客户是否左手无名指戴戒指", bindDomain = "engine.rule.domain.CheckElectronicNoticeRingFingerForPDLForPDL")
    private Integer checkElectronicNoticeRingFingerForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_IsElectronicNoticeCustomerHasSmileForPDL")
    @ModelField(name = "PDL-电子告知书检查_客户是否有笑容", bindDomain = "engine.rule.domain.IsElectronicNoticeCustomerHasSmileForPDLForPDL")
    private Integer isElectronicNoticeCustomerHasSmileForPDL = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


    @ModelMethod(name = "(this)的PDL-客户电话审核_本人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckUserRiskRevealForPDLForPDL")
    public boolean checkUserRiskRevealForPDLContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkUserRiskRevealForPDL);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的PDL-第一联系人电话审核V3_本人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckFirstContactRiskPromptResultForPDLForPDL")
    public boolean checkFirstContactRiskPromptResultForPDLContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkFirstContactRiskPromptResultForPDL);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的PDL-第二联系人电话审核V3_本人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckSecondContactRiskPromptResultForPDLForPDL")
    public boolean checkSecondContactRiskPromptResultForPDLContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkSecondContactRiskPromptResultForPDL);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的PDL-第三联系人电话审核V3_本人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckThirdContactRiskPromptResultForPDLForPDL")
    public boolean checkThirdContactRiskPromptResultForPDLContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkThirdContactRiskPromptResultForPDL);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的PDL-第一联系人电话审核V3_申请人声音和背景音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckFirstContactSoundForPDLForPDL")
    public boolean checkFirstContactSoundForPDLContains(int option)
    {
        Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkFirstContactSoundForPDL);
        return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的PDL-第二联系人电话审核V3_申请人声音和背景音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckSecondContactSoundForPDLForPDL")
    public boolean checkSecondContactSoundForPDLContains(int option)
    {
        Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkSecondContactSoundForPDL);
        return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的PDL-第三联系人电话审核V3_申请人声音和背景音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckThirdContactSoundForPDLForPDL")
    public boolean checkThirdContactSoundForPDLContains(int option)
    {
        Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkThirdContactSoundForPDL);
        return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的PDL-客户电话审核_申请人声音和背景音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckUserSoundForPDLForPDL")
    public boolean checkUserSoundForPDLContains(int option)
    {
        Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkUserSoundForPDL);
        return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的PDL-身份信息录入_其他证件类型是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.InputIdCardInfoOtherCertTypeForPDLForPDL")
    public boolean checkInputIdCardInfoOtherCertTypeForPDLContains(int option)
    {
        Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.inputIdCardInfoOtherCertTypeForPDL);
        return optionSet.contains(option);
    }
    
    public Integer getOverdueRepaymentRemindPhoneCallResultForPDL() {
        return overdueRepaymentRemindPhoneCallResultForPDL;
    }

    public void setOverdueRepaymentRemindPhoneCallResultForPDL(
            Integer overdueRepaymentRemindPhoneCallResultForPDL) {
        this.overdueRepaymentRemindPhoneCallResultForPDL = overdueRepaymentRemindPhoneCallResultForPDL;
    }

    public Integer getOverdueRepaymentRemindPhoneAnswererResultForPDL() {
        return overdueRepaymentRemindPhoneAnswererResultForPDL;
    }

    public void setOverdueRepaymentRemindPhoneAnswererResultForPDL(
            Integer overdueRepaymentRemindPhoneAnswererResultForPDL) {
        this.overdueRepaymentRemindPhoneAnswererResultForPDL = overdueRepaymentRemindPhoneAnswererResultForPDL;
    }

    public Integer getOverdueRepaymentRemindSceneIntroduceResultForPDL() {
        return overdueRepaymentRemindSceneIntroduceResultForPDL;
    }

    public void setOverdueRepaymentRemindSceneIntroduceResultForPDL(
            Integer overdueRepaymentRemindSceneIntroduceResultForPDL) {
        this.overdueRepaymentRemindSceneIntroduceResultForPDL = overdueRepaymentRemindSceneIntroduceResultForPDL;
    }

    public Integer getOverdueRepaymentRemindIdentificationForPDL() {
        return overdueRepaymentRemindIdentificationForPDL;
    }

    public void setOverdueRepaymentRemindIdentificationForPDL(
            Integer overdueRepaymentRemindIdentificationForPDL) {
        this.overdueRepaymentRemindIdentificationForPDL = overdueRepaymentRemindIdentificationForPDL;
    }

    public Integer getOverdueRepaymentRemindResultForPDL() {
        return overdueRepaymentRemindResultForPDL;
    }

    public void setOverdueRepaymentRemindResultForPDL(
            Integer overdueRepaymentRemindResultForPDL) {
        this.overdueRepaymentRemindResultForPDL = overdueRepaymentRemindResultForPDL;
    }

    public Integer getOverdueRepaymentRemindCertificateResultForPDL() {
        return overdueRepaymentRemindCertificateResultForPDL;
    }

    public void setOverdueRepaymentRemindCertificateResultForPDL(
            Integer overdueRepaymentRemindCertificateResultForPDL) {
        this.overdueRepaymentRemindCertificateResultForPDL = overdueRepaymentRemindCertificateResultForPDL;
    }

    public Integer getOverdueRepaymentRemindCareApplicantResultForPDL() {
        return overdueRepaymentRemindCareApplicantResultForPDL;
    }

    public void setOverdueRepaymentRemindCareApplicantResultForPDL(
            Integer overdueRepaymentRemindCareApplicantResultForPDL) {
        this.overdueRepaymentRemindCareApplicantResultForPDL = overdueRepaymentRemindCareApplicantResultForPDL;
    }

    public Integer getOverdueRepaymentRemindAttitudeOfPhoneResultForPDL() {
        return overdueRepaymentRemindAttitudeOfPhoneResultForPDL;
    }

    public void setOverdueRepaymentRemindAttitudeOfPhoneResultForPDL(
            Integer overdueRepaymentRemindAttitudeOfPhoneResultForPDL) {
        this.overdueRepaymentRemindAttitudeOfPhoneResultForPDL = overdueRepaymentRemindAttitudeOfPhoneResultForPDL;
    }

    public Integer getInputIdCardInfoIsOtherCertProvidedForPDL() {
        return inputIdCardInfoIsOtherCertProvidedForPDL;
    }

    public void setInputIdCardInfoIsOtherCertProvidedForPDL(
            Integer inputIdCardInfoIsOtherCertProvidedForPDL) {
        this.inputIdCardInfoIsOtherCertProvidedForPDL = inputIdCardInfoIsOtherCertProvidedForPDL;
    }

    public Integer getInputIdCardInfoOtherCertTypeForPDL() {
        return inputIdCardInfoOtherCertTypeForPDL;
    }

    public void setInputIdCardInfoOtherCertTypeForPDL(
            Integer inputIdCardInfoOtherCertTypeForPDL) {
        this.inputIdCardInfoOtherCertTypeForPDL = inputIdCardInfoOtherCertTypeForPDL;
    }

    public Integer getCheckBuckleIsBuckleAgreementForPDL() {
        return checkBuckleIsBuckleAgreementForPDL;
    }

    public void setCheckBuckleIsBuckleAgreementForPDL(
            Integer checkBuckleIsBuckleAgreementForPDL) {
        this.checkBuckleIsBuckleAgreementForPDL = checkBuckleIsBuckleAgreementForPDL;
    }

    public Integer getCheckBuckleTextClarifyResultForPDL() {
        return checkBuckleTextClarifyResultForPDL;
    }

    public void setCheckBuckleTextClarifyResultForPDL(
            Integer checkBuckleTextClarifyResultForPDL) {
        this.checkBuckleTextClarifyResultForPDL = checkBuckleTextClarifyResultForPDL;
    }

    public Integer getCheckBuckleSignatureClarifyResultForPDL() {
        return checkBuckleSignatureClarifyResultForPDL;
    }

    public void setCheckBuckleSignatureClarifyResultForPDL(
            Integer checkBuckleSignatureClarifyResultForPDL) {
        this.checkBuckleSignatureClarifyResultForPDL = checkBuckleSignatureClarifyResultForPDL;
    }

    public Integer getCheckBuckleDoesSignatureAndIdNameMatchForPDL() {
        return checkBuckleDoesSignatureAndIdNameMatchForPDL;
    }

    public void setCheckBuckleDoesSignatureAndIdNameMatchForPDL(
            Integer checkBuckleDoesSignatureAndIdNameMatchForPDL) {
        this.checkBuckleDoesSignatureAndIdNameMatchForPDL = checkBuckleDoesSignatureAndIdNameMatchForPDL;
    }

    public Integer getCheckCourtExecutedForPDL() {
        return checkCourtExecutedForPDL;
    }

    public void setCheckCourtExecutedForPDL(Integer checkCourtExecutedForPDL) {
        this.checkCourtExecutedForPDL = checkCourtExecutedForPDL;
    }

    public Integer getLoanMoneyResultForPDL() {
        return loanMoneyResultForPDL;
    }

    public void setLoanMoneyResultForPDL(Integer loanMoneyResultForPDL) {
        this.loanMoneyResultForPDL = loanMoneyResultForPDL;
    }

    public Integer getLoanMoneyFailureReasonPDL() {
        return loanMoneyFailureReasonPDL;
    }

    public void setLoanMoneyFailureReasonPDL(Integer loanMoneyFailureReasonPDL) {
        this.loanMoneyFailureReasonPDL = loanMoneyFailureReasonPDL;
    }

    public Integer getFinishApplicationOperationForPDL() {
        return finishApplicationOperationForPDL;
    }

    public void setFinishApplicationOperationForPDL(
            Integer finishApplicationOperationForPDL) {
        this.finishApplicationOperationForPDL = finishApplicationOperationForPDL;
    }

    /*    public Integer getCheckBilFinanceRecordForPDL() {
        return checkBilFinanceRecordForPDL;
    }

    public void setCheckBilFinanceRecordForPDL(
            Integer checkBilFinanceRecordForPDL) {
        this.checkBilFinanceRecordForPDL = checkBilFinanceRecordForPDL;
    }*/

    public Integer getCheckDafyRecordForPDL() {
        return checkDafyRecordForPDL;
    }

    public void setCheckDafyRecordForPDL(Integer checkDafyRecordForPDL) {
        this.checkDafyRecordForPDL = checkDafyRecordForPDL;
    }

    public Integer getCheckHaodaiRecordForPDL() {
        return checkHaodaiRecordForPDL;
    }

    public void setCheckHaodaiRecordForPDL(Integer checkHaodaiRecordForPDL) {
        this.checkHaodaiRecordForPDL = checkHaodaiRecordForPDL;
    }

    public Integer getCheckCreditEaseRecordForPDL() {
        return checkCreditEaseRecordForPDL;
    }

    public void setCheckCreditEaseRecordForPDL(
            Integer checkCreditEaseRecordForPDL) {
        this.checkCreditEaseRecordForPDL = checkCreditEaseRecordForPDL;
    }

    public Integer getCheckVCashRecordForPDL() {
        return checkVCashRecordForPDL;
    }

    public void setCheckVCashRecordForPDL(Integer checkVCashRecordForPDL) {
        this.checkVCashRecordForPDL = checkVCashRecordForPDL;
    }

    public Integer getCheckImageHeadPhotoPersonalPicForPDL() {
        return checkImageHeadPhotoPersonalPicForPDL;
    }

    public void setCheckImageHeadPhotoPersonalPicForPDL(
            Integer checkImageHeadPhotoPersonalPicForPDL) {
        this.checkImageHeadPhotoPersonalPicForPDL = checkImageHeadPhotoPersonalPicForPDL;
    }

    public Integer getCheckIsSalesmanExistedForPDL() {
        return checkIsSalesmanExistedForPDL;
    }

    public void setCheckIsSalesmanExistedForPDL(
            Integer checkIsSalesmanExistedForPDL) {
        this.checkIsSalesmanExistedForPDL = checkIsSalesmanExistedForPDL;
    }

    public Integer getCheckImageHeadPhotoHeadDirectionForPDL() {
        return checkImageHeadPhotoHeadDirectionForPDL;
    }

    public void setCheckImageHeadPhotoHeadDirectionForPDL(
            Integer checkImageHeadPhotoHeadDirectionForPDL) {
        this.checkImageHeadPhotoHeadDirectionForPDL = checkImageHeadPhotoHeadDirectionForPDL;
    }

    public Integer getCheckImageHeadPhotoFaceExpressionForPDL() {
        return checkImageHeadPhotoFaceExpressionForPDL;
    }

    public void setCheckImageHeadPhotoFaceExpressionForPDL(
            Integer checkImageHeadPhotoFaceExpressionForPDL) {
        this.checkImageHeadPhotoFaceExpressionForPDL = checkImageHeadPhotoFaceExpressionForPDL;
    }

    public Integer getCheckWhetherImageIsIdCardPhotoForPDL() {
        return checkWhetherImageIsIdCardPhotoForPDL;
    }

    public void setCheckWhetherImageIsIdCardPhotoForPDL(
            Integer checkWhetherImageIsIdCardPhotoForPDL) {
        this.checkWhetherImageIsIdCardPhotoForPDL = checkWhetherImageIsIdCardPhotoForPDL;
    }

    public Integer getCheckImageIdCardPersonalPicForPDL() {
        return checkImageIdCardPersonalPicForPDL;
    }

    public void setCheckImageIdCardPersonalPicForPDL(
            Integer checkImageIdCardPersonalPicForPDL) {
        this.checkImageIdCardPersonalPicForPDL = checkImageIdCardPersonalPicForPDL;
    }

    public Integer getCheckImageIdCardInfoForPDL() {
        return checkImageIdCardInfoForPDL;
    }

    public void setCheckImageIdCardInfoForPDL(Integer checkImageIdCardInfoForPDL) {
        this.checkImageIdCardInfoForPDL = checkImageIdCardInfoForPDL;
    }

    public Integer getCheckImagePhotoFromPoliceExistForPDL() {
        return checkImagePhotoFromPoliceExistForPDL;
    }

    public void setCheckImagePhotoFromPoliceExistForPDL(
            Integer checkImagePhotoFromPoliceExistForPDL) {
        this.checkImagePhotoFromPoliceExistForPDL = checkImagePhotoFromPoliceExistForPDL;
    }

    public Integer getCheckImageComparisionForPDL() {
        return checkImageComparisionForPDL;
    }

    public void setCheckImageComparisionForPDL(
            Integer checkImageComparisionForPDL) {
        this.checkImageComparisionForPDL = checkImageComparisionForPDL;
    }

    public Integer getCheckHomeCreditRecordForPDL() {
        return checkHomeCreditRecordForPDL;
    }

    public void setCheckHomeCreditRecordForPDL(
            Integer checkHomeCreditRecordForPDL) {
        this.checkHomeCreditRecordForPDL = checkHomeCreditRecordForPDL;
    }

    public Integer getCheckWhetherImageIsWorkPermitForPDL() {
        return checkWhetherImageIsWorkPermitForPDL;
    }

    public void setCheckWhetherImageIsWorkPermitForPDL(
            Integer checkWhetherImageIsWorkPermitForPDL) {
        this.checkWhetherImageIsWorkPermitForPDL = checkWhetherImageIsWorkPermitForPDL;
    }

    public Integer getCheckWhetherImageIsConsistentWithTemplateWorkPermitForPDL() {
        return checkWhetherImageIsConsistentWithTemplateWorkPermitForPDL;
    }

    public void setCheckWhetherImageIsConsistentWithTemplateWorkPermitForPDL(
            Integer checkWhetherImageIsConsistentWithTemplateWorkPermitForPDL) {
        this.checkWhetherImageIsConsistentWithTemplateWorkPermitForPDL = checkWhetherImageIsConsistentWithTemplateWorkPermitForPDL;
    }

    public Integer getCheckWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL() {
        return checkWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL;
    }

    public void setCheckWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL(
            Integer checkWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL) {
        this.checkWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL = checkWhetherWorkPermitNameIsConsistentWithIDCardNameForPDL;
    }

    public Integer getCheckWhetherCompanyNameAndDepartmentIsConsistentForPDL() {
        return checkWhetherCompanyNameAndDepartmentIsConsistentForPDL;
    }

    public void setCheckWhetherCompanyNameAndDepartmentIsConsistentForPDL(
            Integer checkWhetherCompanyNameAndDepartmentIsConsistentForPDL) {
        this.checkWhetherCompanyNameAndDepartmentIsConsistentForPDL = checkWhetherCompanyNameAndDepartmentIsConsistentForPDL;
    }

    public Integer getCheckWhetherWorkPermitOfficialSealConsistentForPDL() {
        return checkWhetherWorkPermitOfficialSealConsistentForPDL;
    }

    public void setCheckWhetherWorkPermitOfficialSealConsistentForPDL(
            Integer checkWhetherWorkPermitOfficialSealConsistentForPDL) {
        this.checkWhetherWorkPermitOfficialSealConsistentForPDL = checkWhetherWorkPermitOfficialSealConsistentForPDL;
    }

    public Integer getCheckWhetherWorkPermitConsistentForPDL() {
        return checkWhetherWorkPermitConsistentForPDL;
    }

    public void setCheckWhetherWorkPermitConsistentForPDL(
            Integer checkWhetherWorkPermitConsistentForPDL) {
        this.checkWhetherWorkPermitConsistentForPDL = checkWhetherWorkPermitConsistentForPDL;
    }

    public Integer getCheckWhetherImageIsGroupPhotoForPDL() {
        return checkWhetherImageIsGroupPhotoForPDL;
    }

    public void setCheckWhetherImageIsGroupPhotoForPDL(
            Integer checkWhetherImageIsGroupPhotoForPDL) {
        this.checkWhetherImageIsGroupPhotoForPDL = checkWhetherImageIsGroupPhotoForPDL;
    }

    public Integer getCheckWhetherGroupPhotoIsRecognizableForPDL() {
        return checkWhetherGroupPhotoIsRecognizableForPDL;
    }

    public void setCheckWhetherGroupPhotoIsRecognizableForPDL(
            Integer checkWhetherGroupPhotoIsRecognizableForPDL) {
        this.checkWhetherGroupPhotoIsRecognizableForPDL = checkWhetherGroupPhotoIsRecognizableForPDL;
    }

    public Integer getCheckWhetherCustomerPhotoIsTheSamePersonForPDL() {
        return checkWhetherCustomerPhotoIsTheSamePersonForPDL;
    }

    public void setCheckWhetherCustomerPhotoIsTheSamePersonForPDL(
            Integer checkWhetherCustomerPhotoIsTheSamePersonForPDL) {
        this.checkWhetherCustomerPhotoIsTheSamePersonForPDL = checkWhetherCustomerPhotoIsTheSamePersonForPDL;
    }

    public Integer getCheckWhetherSalesmanPhotoIsTheSamePersonForPDL() {
        return checkWhetherSalesmanPhotoIsTheSamePersonForPDL;
    }

    public void setCheckWhetherSalesmanPhotoIsTheSamePersonForPDL(
            Integer checkWhetherSalesmanPhotoIsTheSamePersonForPDL) {
        this.checkWhetherSalesmanPhotoIsTheSamePersonForPDL = checkWhetherSalesmanPhotoIsTheSamePersonForPDL;
    }

    public Integer getCheckFirstContactPhoneCallResultForPDL() {
        return checkFirstContactPhoneCallResultForPDL;
    }

    public void setCheckFirstContactPhoneCallResultForPDL(
            Integer checkFirstContactPhoneCallResultForPDL) {
        this.checkFirstContactPhoneCallResultForPDL = checkFirstContactPhoneCallResultForPDL;
    }

    public Integer getCheckFirstContactPhoneAnswererResultForPDL() {
        return checkFirstContactPhoneAnswererResultForPDL;
    }

    public void setCheckFirstContactPhoneAnswererResultForPDL(
            Integer checkFirstContactPhoneAnswererResultForPDL) {
        this.checkFirstContactPhoneAnswererResultForPDL = checkFirstContactPhoneAnswererResultForPDL;
    }

    public Integer getCheckFirstContactSceneIntroduceResultForPDL() {
        return checkFirstContactSceneIntroduceResultForPDL;
    }

    public void setCheckFirstContactSceneIntroduceResultForPDL(
            Integer checkFirstContactSceneIntroduceResultForPDL) {
        this.checkFirstContactSceneIntroduceResultForPDL = checkFirstContactSceneIntroduceResultForPDL;
    }

    public Integer getCheckFirstContactIdentificationResultForPDL() {
        return checkFirstContactIdentificationResultForPDL;
    }

    public void setCheckFirstContactIdentificationResultForPDL(
            Integer checkFirstContactIdentificationResultForPDL) {
        this.checkFirstContactIdentificationResultForPDL = checkFirstContactIdentificationResultForPDL;
    }

    public Integer getCheckFirstContactZodiacResultForPDL() {
        return checkFirstContactZodiacResultForPDL;
    }

    public void setCheckFirstContactZodiacResultForPDL(
            Integer checkFirstContactZodiacResultForPDL) {
        this.checkFirstContactZodiacResultForPDL = checkFirstContactZodiacResultForPDL;
    }

    public Integer getCheckFirstContactMarriageStatusResultForPDL() {
        return checkFirstContactMarriageStatusResultForPDL;
    }

    public void setCheckFirstContactMarriageStatusResultForPDL(
            Integer checkFirstContactMarriageStatusResultForPDL) {
        this.checkFirstContactMarriageStatusResultForPDL = checkFirstContactMarriageStatusResultForPDL;
    }

    public Integer getCheckFirstContactCompanyNameResultPDL() {
        return checkFirstContactCompanyNameResultPDL;
    }

    public void setCheckFirstContactCompanyNameResultPDL(
            Integer checkFirstContactCompanyNameResultPDL) {
        this.checkFirstContactCompanyNameResultPDL = checkFirstContactCompanyNameResultPDL;
    }

    public Integer getCheckFirstContactRiskPromptResultForPDL() {
        return checkFirstContactRiskPromptResultForPDL;
    }

    public void setCheckFirstContactRiskPromptResultForPDL(
            Integer checkFirstContactRiskPromptResultForPDL) {
        this.checkFirstContactRiskPromptResultForPDL = checkFirstContactRiskPromptResultForPDL;
    }

    public Integer getCheckFirstContactSoundForPDL() {
        return checkFirstContactSoundForPDL;
    }

    public void setCheckFirstContactSoundForPDL(
            Integer checkFirstContactSoundForPDL) {
        this.checkFirstContactSoundForPDL = checkFirstContactSoundForPDL;
    }

    public Integer getCheckFirstContactCareApplicantResultForPDL() {
        return checkFirstContactCareApplicantResultForPDL;
    }

    public void setCheckFirstContactCareApplicantResultForPDL(
            Integer checkFirstContactCareApplicantResultForPDL) {
        this.checkFirstContactCareApplicantResultForPDL = checkFirstContactCareApplicantResultForPDL;
    }

    public Integer getCheckFirstContactAttitudeOfPhoneResultForPDL() {
        return checkFirstContactAttitudeOfPhoneResultForPDL;
    }

    public void setCheckFirstContactAttitudeOfPhoneResultForPDL(
            Integer checkFirstContactAttitudeOfPhoneResultForPDL) {
        this.checkFirstContactAttitudeOfPhoneResultForPDL = checkFirstContactAttitudeOfPhoneResultForPDL;
    }

    public Integer getCheckSecondContactPhoneCallResultForPDL() {
        return checkSecondContactPhoneCallResultForPDL;
    }

    public void setCheckSecondContactPhoneCallResultForPDL(
            Integer checkSecondContactPhoneCallResultForPDL) {
        this.checkSecondContactPhoneCallResultForPDL = checkSecondContactPhoneCallResultForPDL;
    }

    public Integer getCheckSecondContactPhoneAnswererResultForPDL() {
        return checkSecondContactPhoneAnswererResultForPDL;
    }

    public void setCheckSecondContactPhoneAnswererResultForPDL(
            Integer checkSecondContactPhoneAnswererResultForPDL) {
        this.checkSecondContactPhoneAnswererResultForPDL = checkSecondContactPhoneAnswererResultForPDL;
    }

    public Integer getCheckSecondContactSceneIntroduceResultForPDL() {
        return checkSecondContactSceneIntroduceResultForPDL;
    }

    public void setCheckSecondContactSceneIntroduceResultForPDL(
            Integer checkSecondContactSceneIntroduceResultForPDL) {
        this.checkSecondContactSceneIntroduceResultForPDL = checkSecondContactSceneIntroduceResultForPDL;
    }

    public Integer getCheckSecondContactIdentificationResultForPDL() {
        return checkSecondContactIdentificationResultForPDL;
    }

    public void setCheckSecondContactIdentificationResultForPDL(
            Integer checkSecondContactIdentificationResultForPDL) {
        this.checkSecondContactIdentificationResultForPDL = checkSecondContactIdentificationResultForPDL;
    }

    public Integer getCheckSecondContactZodiacResultForPDL() {
        return checkSecondContactZodiacResultForPDL;
    }

    public void setCheckSecondContactZodiacResultForPDL(
            Integer checkSecondContactZodiacResultForPDL) {
        this.checkSecondContactZodiacResultForPDL = checkSecondContactZodiacResultForPDL;
    }

    public Integer getCheckSecondContactMarriageStatusResultForPDL() {
        return checkSecondContactMarriageStatusResultForPDL;
    }

    public void setCheckSecondContactMarriageStatusResultForPDL(
            Integer checkSecondContactMarriageStatusResultForPDL) {
        this.checkSecondContactMarriageStatusResultForPDL = checkSecondContactMarriageStatusResultForPDL;
    }

    public Integer getCheckSecondContactCompanyNameResultPDL() {
        return checkSecondContactCompanyNameResultPDL;
    }

    public void setCheckSecondContactCompanyNameResultPDL(
            Integer checkSecondContactCompanyNameResultPDL) {
        this.checkSecondContactCompanyNameResultPDL = checkSecondContactCompanyNameResultPDL;
    }

    public Integer getCheckSecondContactRiskPromptResultForPDL() {
        return checkSecondContactRiskPromptResultForPDL;
    }

    public void setCheckSecondContactRiskPromptResultForPDL(
            Integer checkSecondContactRiskPromptResultForPDL) {
        this.checkSecondContactRiskPromptResultForPDL = checkSecondContactRiskPromptResultForPDL;
    }

    public Integer getCheckSecondContactSoundForPDL() {
        return checkSecondContactSoundForPDL;
    }

    public void setCheckSecondContactSoundForPDL(
            Integer checkSecondContactSoundForPDL) {
        this.checkSecondContactSoundForPDL = checkSecondContactSoundForPDL;
    }

    public Integer getCheckSecondContactCareApplicantResultForPDL() {
        return checkSecondContactCareApplicantResultForPDL;
    }

    public void setCheckSecondContactCareApplicantResultForPDL(
            Integer checkSecondContactCareApplicantResultForPDL) {
        this.checkSecondContactCareApplicantResultForPDL = checkSecondContactCareApplicantResultForPDL;
    }

    public Integer getCheckSecondContactAttitudeOfPhoneResultForPDL() {
        return checkSecondContactAttitudeOfPhoneResultForPDL;
    }

    public void setCheckSecondContactAttitudeOfPhoneResultForPDL(
            Integer checkSecondContactAttitudeOfPhoneResultForPDL) {
        this.checkSecondContactAttitudeOfPhoneResultForPDL = checkSecondContactAttitudeOfPhoneResultForPDL;
    }

    public Integer getCheckThirdContactPhoneCallResultForPDL() {
        return checkThirdContactPhoneCallResultForPDL;
    }

    public void setCheckThirdContactPhoneCallResultForPDL(
            Integer checkThirdContactPhoneCallResultForPDL) {
        this.checkThirdContactPhoneCallResultForPDL = checkThirdContactPhoneCallResultForPDL;
    }

    public Integer getCheckThirdContactPhoneAnswererResultForPDL() {
        return checkThirdContactPhoneAnswererResultForPDL;
    }

    public void setCheckThirdContactPhoneAnswererResultForPDL(
            Integer checkThirdContactPhoneAnswererResultForPDL) {
        this.checkThirdContactPhoneAnswererResultForPDL = checkThirdContactPhoneAnswererResultForPDL;
    }

    public Integer getCheckThirdContactSceneIntroduceResultForPDL() {
        return checkThirdContactSceneIntroduceResultForPDL;
    }

    public void setCheckThirdContactSceneIntroduceResultForPDL(
            Integer checkThirdContactSceneIntroduceResultForPDL) {
        this.checkThirdContactSceneIntroduceResultForPDL = checkThirdContactSceneIntroduceResultForPDL;
    }

    public Integer getCheckThirdContactIdentificationResultForPDL() {
        return checkThirdContactIdentificationResultForPDL;
    }

    public void setCheckThirdContactIdentificationResultForPDL(
            Integer checkThirdContactIdentificationResultForPDL) {
        this.checkThirdContactIdentificationResultForPDL = checkThirdContactIdentificationResultForPDL;
    }

    public Integer getCheckThirdContactZodiacResultForPDL() {
        return checkThirdContactZodiacResultForPDL;
    }

    public void setCheckThirdContactZodiacResultForPDL(
            Integer checkThirdContactZodiacResultForPDL) {
        this.checkThirdContactZodiacResultForPDL = checkThirdContactZodiacResultForPDL;
    }

    public Integer getCheckThirdContactMarriageStatusResultForPDL() {
        return checkThirdContactMarriageStatusResultForPDL;
    }

    public void setCheckThirdContactMarriageStatusResultForPDL(
            Integer checkThirdContactMarriageStatusResultForPDL) {
        this.checkThirdContactMarriageStatusResultForPDL = checkThirdContactMarriageStatusResultForPDL;
    }

    public Integer getCheckThirdContactCompanyNameResultPDL() {
        return checkThirdContactCompanyNameResultPDL;
    }

    public void setCheckThirdContactCompanyNameResultPDL(
            Integer checkThirdContactCompanyNameResultPDL) {
        this.checkThirdContactCompanyNameResultPDL = checkThirdContactCompanyNameResultPDL;
    }

    public Integer getCheckThirdContactRiskPromptResultForPDL() {
        return checkThirdContactRiskPromptResultForPDL;
    }

    public void setCheckThirdContactRiskPromptResultForPDL(
            Integer checkThirdContactRiskPromptResultForPDL) {
        this.checkThirdContactRiskPromptResultForPDL = checkThirdContactRiskPromptResultForPDL;
    }

    public Integer getCheckThirdContactSoundForPDL() {
        return checkThirdContactSoundForPDL;
    }

    public void setCheckThirdContactSoundForPDL(
            Integer checkThirdContactSoundForPDL) {
        this.checkThirdContactSoundForPDL = checkThirdContactSoundForPDL;
    }

    public Integer getCheckThirdContactCareApplicantResultForPDL() {
        return checkThirdContactCareApplicantResultForPDL;
    }

    public void setCheckThirdContactCareApplicantResultForPDL(
            Integer checkThirdContactCareApplicantResultForPDL) {
        this.checkThirdContactCareApplicantResultForPDL = checkThirdContactCareApplicantResultForPDL;
    }

    public Integer getCheckThirdContactAttitudeOfPhoneResultForPDL() {
        return checkThirdContactAttitudeOfPhoneResultForPDL;
    }

    public void setCheckThirdContactAttitudeOfPhoneResultForPDL(
            Integer checkThirdContactAttitudeOfPhoneResultForPDL) {
        this.checkThirdContactAttitudeOfPhoneResultForPDL = checkThirdContactAttitudeOfPhoneResultForPDL;
    }

    public Integer getWelcomeCallPhoneStatusForPDL() {
        return welcomeCallPhoneStatusForPDL;
    }

    public void setWelcomeCallPhoneStatusForPDL(
            Integer welcomeCallPhoneStatusForPDL) {
        this.welcomeCallPhoneStatusForPDL = welcomeCallPhoneStatusForPDL;
    }

    public Integer getWelcomeCallPhoneAnswererResultForPDL() {
        return welcomeCallPhoneAnswererResultForPDL;
    }

    public void setWelcomeCallPhoneAnswererResultForPDL(
            Integer welcomeCallPhoneAnswererResultForPDL) {
        this.welcomeCallPhoneAnswererResultForPDL = welcomeCallPhoneAnswererResultForPDL;
    }

    public Integer getWelcomeCallSceneFeedbackForPDL() {
        return welcomeCallSceneFeedbackForPDL;
    }

    public void setWelcomeCallSceneFeedbackForPDL(
            Integer welcomeCallSceneFeedbackForPDL) {
        this.welcomeCallSceneFeedbackForPDL = welcomeCallSceneFeedbackForPDL;
    }

    public Integer getWelcomeCallPaybackInfoConfirmationForPDL() {
        return welcomeCallPaybackInfoConfirmationForPDL;
    }

    public void setWelcomeCallPaybackInfoConfirmationForPDL(
            Integer welcomeCallPaybackInfoConfirmationForPDL) {
        this.welcomeCallPaybackInfoConfirmationForPDL = welcomeCallPaybackInfoConfirmationForPDL;
    }

    public Integer getWelcomeCallCustomerAttitudeResultForPDL() {
        return welcomeCallCustomerAttitudeResultForPDL;
    }

    public void setWelcomeCallCustomerAttitudeResultForPDL(
            Integer welcomeCallCustomerAttitudeResultForPDL) {
        this.welcomeCallCustomerAttitudeResultForPDL = welcomeCallCustomerAttitudeResultForPDL;
    }

    public Integer getRepaymentRemindPhoneCallResultForPDL() {
        return repaymentRemindPhoneCallResultForPDL;
    }

    public void setRepaymentRemindPhoneCallResultForPDL(
            Integer repaymentRemindPhoneCallResultForPDL) {
        this.repaymentRemindPhoneCallResultForPDL = repaymentRemindPhoneCallResultForPDL;
    }

    public Integer getRepaymentRemindIdentificationResultForPDL() {
        return repaymentRemindIdentificationResultForPDL;
    }

    public void setRepaymentRemindIdentificationResultForPDL(
            Integer repaymentRemindIdentificationResultForPDL) {
        this.repaymentRemindIdentificationResultForPDL = repaymentRemindIdentificationResultForPDL;
    }

    public Integer getFirstRepaymentRemindSceneResultForPDL() {
        return firstRepaymentRemindSceneResultForPDL;
    }

    public void setFirstRepaymentRemindSceneResultForPDL(
            Integer firstRepaymentRemindSceneResultForPDL) {
        this.firstRepaymentRemindSceneResultForPDL = firstRepaymentRemindSceneResultForPDL;
    }

    public Integer getSecondRepaymentRemindSceneResultForPDL() {
        return secondRepaymentRemindSceneResultForPDL;
    }

    public void setSecondRepaymentRemindSceneResultForPDL(
            Integer secondRepaymentRemindSceneResultForPDL) {
        this.secondRepaymentRemindSceneResultForPDL = secondRepaymentRemindSceneResultForPDL;
    }

    public Integer getRepaymentRemindPaybackConfirmationForPDL() {
        return repaymentRemindPaybackConfirmationForPDL;
    }

    public void setRepaymentRemindPaybackConfirmationForPDL(
            Integer repaymentRemindPaybackConfirmationForPDL) {
        this.repaymentRemindPaybackConfirmationForPDL = repaymentRemindPaybackConfirmationForPDL;
    }

    public Integer getRepaymentRemindCustomerAttitudeResultForPDL() {
        return repaymentRemindCustomerAttitudeResultForPDL;
    }

    public void setRepaymentRemindCustomerAttitudeResultForPDL(
            Integer repaymentRemindCustomerAttitudeResultForPDL) {
        this.repaymentRemindCustomerAttitudeResultForPDL = repaymentRemindCustomerAttitudeResultForPDL;
    }

    public Integer getCheckUserPhoneCallResultForPDL() {
        return checkUserPhoneCallResultForPDL;
    }

    public void setCheckUserPhoneCallResultForPDL(
            Integer checkUserPhoneCallResultForPDL) {
        this.checkUserPhoneCallResultForPDL = checkUserPhoneCallResultForPDL;
    }

    public Integer getCheckUserPhoneAnswererResultForPDL() {
        return checkUserPhoneAnswererResultForPDL;
    }

    public void setCheckUserPhoneAnswererResultForPDL(
            Integer checkUserPhoneAnswererResultForPDL) {
        this.checkUserPhoneAnswererResultForPDL = checkUserPhoneAnswererResultForPDL;
    }

    public Integer getCheckUserSceneIntroduceResultForPDL() {
        return checkUserSceneIntroduceResultForPDL;
    }

    public void setCheckUserSceneIntroduceResultForPDL(
            Integer checkUserSceneIntroduceResultForPDL) {
        this.checkUserSceneIntroduceResultForPDL = checkUserSceneIntroduceResultForPDL;
    }

    public Integer getCheckUserIdentificationForPDL() {
        return checkUserIdentificationForPDL;
    }

    public void setCheckUserIdentificationForPDL(
            Integer checkUserIdentificationForPDL) {
        this.checkUserIdentificationForPDL = checkUserIdentificationForPDL;
    }

    public Integer getCheckUserCompanyPaydayForPDL() {
        return checkUserCompanyPaydayForPDL;
    }

    public void setCheckUserCompanyPaydayForPDL(
            Integer checkUserCompanyPaydayForPDL) {
        this.checkUserCompanyPaydayForPDL = checkUserCompanyPaydayForPDL;
    }

    public Integer getCheckUserSymbolicAnimalForPDL() {
        return checkUserSymbolicAnimalForPDL;
    }

    public void setCheckUserSymbolicAnimalForPDL(
            Integer checkUserSymbolicAnimalForPDL) {
        this.checkUserSymbolicAnimalForPDL = checkUserSymbolicAnimalForPDL;
    }

    public Integer getCheckUserCompanyNameForPDL() {
        return checkUserCompanyNameForPDL;
    }

    public void setCheckUserCompanyNameForPDL(Integer checkUserCompanyNameForPDL) {
        this.checkUserCompanyNameForPDL = checkUserCompanyNameForPDL;
    }

    public Integer getCheckUserIDCardAddressForPDL() {
        return checkUserIDCardAddressForPDL;
    }

    public void setCheckUserIDCardAddressForPDL(
            Integer checkUserIDCardAddressForPDL) {
        this.checkUserIDCardAddressForPDL = checkUserIDCardAddressForPDL;
    }

    public Integer getCheckUserConsequenceIntroduceResultForPDL() {
        return checkUserConsequenceIntroduceResultForPDL;
    }

    public void setCheckUserConsequenceIntroduceResultForPDL(
            Integer checkUserConsequenceIntroduceResultForPDL) {
        this.checkUserConsequenceIntroduceResultForPDL = checkUserConsequenceIntroduceResultForPDL;
    }

    public Integer getCheckUserRiskRevealForPDL() {
        return checkUserRiskRevealForPDL;
    }

    public void setCheckUserRiskRevealForPDL(Integer checkUserRiskRevealForPDL) {
        this.checkUserRiskRevealForPDL = checkUserRiskRevealForPDL;
    }

    public Integer getCheckUserSoundForPDL() {
        return checkUserSoundForPDL;
    }

    public void setCheckUserSoundForPDL(Integer checkUserSoundForPDL) {
        this.checkUserSoundForPDL = checkUserSoundForPDL;
    }

    public Integer getCheckUserCarenessLevelForPDL() {
        return checkUserCarenessLevelForPDL;
    }

    public void setCheckUserCarenessLevelForPDL(
            Integer checkUserCarenessLevelForPDL) {
        this.checkUserCarenessLevelForPDL = checkUserCarenessLevelForPDL;
    }

    public Integer getCheckUserDislikeLevelForPDL() {
        return checkUserDislikeLevelForPDL;
    }

    public void setCheckUserDislikeLevelForPDL(
            Integer checkUserDislikeLevelForPDL) {
        this.checkUserDislikeLevelForPDL = checkUserDislikeLevelForPDL;
    }

    public Integer getCheckWhetherImageIsBankCardForPDL() {
        return checkWhetherImageIsBankCardForPDL;
    }

    public void setCheckWhetherImageIsBankCardForPDL(
            Integer checkWhetherImageIsBankCardForPDL) {
        this.checkWhetherImageIsBankCardForPDL = checkWhetherImageIsBankCardForPDL;
    }

    public Integer getCheckWhetherBankCardPhotoIsRecognizableForPDL() {
        return checkWhetherBankCardPhotoIsRecognizableForPDL;
    }

    public void setCheckWhetherBankCardPhotoIsRecognizableForPDL(
            Integer checkWhetherBankCardPhotoIsRecognizableForPDL) {
        this.checkWhetherBankCardPhotoIsRecognizableForPDL = checkWhetherBankCardPhotoIsRecognizableForPDL;
    }

    public Integer getCheckWhetherBankCardInfoIsRecognizableForPDL() {
        return checkWhetherBankCardInfoIsRecognizableForPDL;
    }

    public void setCheckWhetherBankCardInfoIsRecognizableForPDL(
            Integer checkWhetherBankCardInfoIsRecognizableForPDL) {
        this.checkWhetherBankCardInfoIsRecognizableForPDL = checkWhetherBankCardInfoIsRecognizableForPDL;
    }

    public Integer getTransactionMonitorResultForPDL() {
        return transactionMonitorResultForPDL;
    }

    public void setTransactionMonitorResultForPDL(
            Integer transactionMonitorResultForPDL) {
        this.transactionMonitorResultForPDL = transactionMonitorResultForPDL;
    }

    public Integer getTransactionMonitorRejectReasonForPDL() {
        return transactionMonitorRejectReasonForPDL;
    }

    public void setTransactionMonitorRejectReasonForPDL(
            Integer transactionMonitorRejectReasonForPDL) {
        this.transactionMonitorRejectReasonForPDL = transactionMonitorRejectReasonForPDL;
    }

	public Integer getIsElectronicNoticeForPDL() {
		return isElectronicNoticeForPDL;
	}

	public void setIsElectronicNoticeForPDL(Integer isElectronicNoticeForPDL) {
		this.isElectronicNoticeForPDL = isElectronicNoticeForPDL;
	}

	public Integer getIsElectronicNoticeDistinguishableForPDL() {
		return isElectronicNoticeDistinguishableForPDL;
	}

	public void setIsElectronicNoticeDistinguishableForPDL(
			Integer isElectronicNoticeDistinguishableForPDL) {
		this.isElectronicNoticeDistinguishableForPDL = isElectronicNoticeDistinguishableForPDL;
	}

	public Integer getIsElectronicNoticeInformationDistinguishableForPDL() {
		return isElectronicNoticeInformationDistinguishableForPDL;
	}

	public void setIsElectronicNoticeInformationDistinguishableForPDL(
			Integer isElectronicNoticeInformationDistinguishableForPDL) {
		this.isElectronicNoticeInformationDistinguishableForPDL = isElectronicNoticeInformationDistinguishableForPDL;
	}

	public Integer getElectronicNoticetwoPhotosComparisonForPDL() {
		return electronicNoticetwoPhotosComparisonForPDL;
	}

	public void setElectronicNoticetwoPhotosComparisonForPDL(
			Integer electronicNoticetwoPhotosComparisonForPDL) {
		this.electronicNoticetwoPhotosComparisonForPDL = electronicNoticetwoPhotosComparisonForPDL;
	}

	public Integer getIsElectronicNoticeFourVerificationCodeExistForPDL() {
		return isElectronicNoticeFourVerificationCodeExistForPDL;
	}

	public void setIsElectronicNoticeFourVerificationCodeExistForPDL(
			Integer isElectronicNoticeFourVerificationCodeExistForPDL) {
		this.isElectronicNoticeFourVerificationCodeExistForPDL = isElectronicNoticeFourVerificationCodeExistForPDL;
	}

	public Integer getIsElectronicNoticeFourVerificationCodeConsistentForPDL() {
		return isElectronicNoticeFourVerificationCodeConsistentForPDL;
	}

	public void setIsElectronicNoticeFourVerificationCodeConsistentForPDL(
			Integer isElectronicNoticeFourVerificationCodeConsistentForPDL) {
		this.isElectronicNoticeFourVerificationCodeConsistentForPDL = isElectronicNoticeFourVerificationCodeConsistentForPDL;
	}

	public Integer getCheckElectronicNoticePhoneColorForPDL() {
		return checkElectronicNoticePhoneColorForPDL;
	}

	public void setCheckElectronicNoticePhoneColorForPDL(
			Integer checkElectronicNoticePhoneColorForPDL) {
		this.checkElectronicNoticePhoneColorForPDL = checkElectronicNoticePhoneColorForPDL;
	}

	public Integer getCheckElectronicNoticeRingFingerForPDL() {
		return checkElectronicNoticeRingFingerForPDL;
	}

	public void setCheckElectronicNoticeRingFingerForPDL(
			Integer checkElectronicNoticeRingFingerForPDL) {
		this.checkElectronicNoticeRingFingerForPDL = checkElectronicNoticeRingFingerForPDL;
	}

	public Integer getIsElectronicNoticeCustomerHasSmileForPDL() {
		return isElectronicNoticeCustomerHasSmileForPDL;
	}

	public void setIsElectronicNoticeCustomerHasSmileForPDL(
			Integer isElectronicNoticeCustomerHasSmileForPDL) {
		this.isElectronicNoticeCustomerHasSmileForPDL = isElectronicNoticeCustomerHasSmileForPDL;
	}
	public Integer getIsBadgeForPDL() {
		return isBadgeForPDL;
	}

	public void setIsBadgeForPDL(Integer isBadgeForPDL) {
		this.isBadgeForPDL = isBadgeForPDL;
	}

	public Integer getCheckWhetherBadgeRecognizableForPDL() {
		return checkWhetherBadgeRecognizableForPDL;
	}

	public void setCheckWhetherBadgeRecognizableForPDL(
			Integer checkWhetherBadgeRecognizableForPDL) {
		this.checkWhetherBadgeRecognizableForPDL = checkWhetherBadgeRecognizableForPDL;
	}

	public Integer getCheckWhetherBadgeOnBreastForPDL() {
		return checkWhetherBadgeOnBreastForPDL;
	}

	public void setCheckWhetherBadgeOnBreastForPDL(
			Integer checkWhetherBadgeOnBreastForPDL) {
		this.checkWhetherBadgeOnBreastForPDL = checkWhetherBadgeOnBreastForPDL;
	}

	public Integer getCheckWhetherBadgeHasClientNameForPDL() {
		return checkWhetherBadgeHasClientNameForPDL;
	}

	public void setCheckWhetherBadgeHasClientNameForPDL(
			Integer checkWhetherBadgeHasClientNameForPDL) {
		this.checkWhetherBadgeHasClientNameForPDL = checkWhetherBadgeHasClientNameForPDL;
	}

	public Integer getCheckWhetherBadgeHasCompanyNameForPDL() {
		return checkWhetherBadgeHasCompanyNameForPDL;
	}

	public void setCheckWhetherBadgeHasCompanyNameForPDL(
			Integer checkWhetherBadgeHasCompanyNameForPDL) {
		this.checkWhetherBadgeHasCompanyNameForPDL = checkWhetherBadgeHasCompanyNameForPDL;
	}

	public Integer getCheckWhetherBadgeHasJobNumberForPDL() {
		return checkWhetherBadgeHasJobNumberForPDL;
	}

	public void setCheckWhetherBadgeHasJobNumberForPDL(
			Integer checkWhetherBadgeHasJobNumberForPDL) {
		this.checkWhetherBadgeHasJobNumberForPDL = checkWhetherBadgeHasJobNumberForPDL;
	}

	public Integer getCheckWhetherBadgeHasJobTitleForPDL() {
		return checkWhetherBadgeHasJobTitleForPDL;
	}

	public void setCheckWhetherBadgeHasJobTitleForPDL(
			Integer checkWhetherBadgeHasJobTitleForPDL) {
		this.checkWhetherBadgeHasJobTitleForPDL = checkWhetherBadgeHasJobTitleForPDL;
	}

	public Integer getIsTimeCardForPDL() {
		return isTimeCardForPDL;
	}

	public void setIsTimeCardForPDL(Integer isTimeCardForPDL) {
		this.isTimeCardForPDL = isTimeCardForPDL;
	}

	public Integer getCheckWhetherTimeCardRecognizableForPDL() {
		return checkWhetherTimeCardRecognizableForPDL;
	}

	public void setCheckWhetherTimeCardRecognizableForPDL(
			Integer checkWhetherTimeCardRecognizableForPDL) {
		this.checkWhetherTimeCardRecognizableForPDL = checkWhetherTimeCardRecognizableForPDL;
	}

	public Integer getCheckWhetherTimeCardHasCompanySealForPDL() {
		return checkWhetherTimeCardHasCompanySealForPDL;
	}

	public void setCheckWhetherTimeCardHasCompanySealForPDL(
			Integer checkWhetherTimeCardHasCompanySealForPDL) {
		this.checkWhetherTimeCardHasCompanySealForPDL = checkWhetherTimeCardHasCompanySealForPDL;
	}


	public Integer getCheckWhetherTimeCardHasClientNameForPDL() {
		return checkWhetherTimeCardHasClientNameForPDL;
	}

	public void setCheckWhetherTimeCardHasClientNameForPDL(
			Integer checkWhetherTimeCardHasClientNameForPDL) {
		this.checkWhetherTimeCardHasClientNameForPDL = checkWhetherTimeCardHasClientNameForPDL;
	}

	public Integer getCheckWhetherTimeCardHasRecordForPDL() {
		return checkWhetherTimeCardHasRecordForPDL;
	}

	public void setCheckWhetherTimeCardHasRecordForPDL(
			Integer checkWhetherTimeCardHasRecordForPDL) {
		this.checkWhetherTimeCardHasRecordForPDL = checkWhetherTimeCardHasRecordForPDL;
	}

	public Integer getCheckWhetherTimeCardTheLatestDateForPDL() {
		return checkWhetherTimeCardTheLatestDateForPDL;
	}

	public void setCheckWhetherTimeCardTheLatestDateForPDL(
			Integer checkWhetherTimeCardTheLatestDateForPDL) {
		this.checkWhetherTimeCardTheLatestDateForPDL = checkWhetherTimeCardTheLatestDateForPDL;
	}

	public Integer getCheckWhetherTimeCardJobNumberForPDL() {
		return checkWhetherTimeCardJobNumberForPDL;
	}

	public void setCheckWhetherTimeCardJobNumberForPDL(
			Integer checkWhetherTimeCardJobNumberForPDL) {
		this.checkWhetherTimeCardJobNumberForPDL = checkWhetherTimeCardJobNumberForPDL;
	}

	public Integer getIsWorkClothForPDL() {
		return isWorkClothForPDL;
	}

	public void setIsWorkClothForPDL(Integer isWorkClothForPDL) {
		this.isWorkClothForPDL = isWorkClothForPDL;
	}

	public Integer getCheckWhetherWorkClothRecognizableForPDL() {
		return checkWhetherWorkClothRecognizableForPDL;
	}

	public void setCheckWhetherWorkClothRecognizableForPDL(
			Integer checkWhetherWorkClothRecognizableForPDL) {
		this.checkWhetherWorkClothRecognizableForPDL = checkWhetherWorkClothRecognizableForPDL;
	}

	public Integer getCheckWhetherClientInWorkClothForPDL() {
		return checkWhetherClientInWorkClothForPDL;
	}

	public void setCheckWhetherClientInWorkClothForPDL(
			Integer checkWhetherClientInWorkClothForPDL) {
		this.checkWhetherClientInWorkClothForPDL = checkWhetherClientInWorkClothForPDL;
	}

	public Integer getCheckWhetherWorkClothFitWithClientJobForPDL() {
		return checkWhetherWorkClothFitWithClientJobForPDL;
	}

	public void setCheckWhetherWorkClothFitWithClientJobForPDL(
			Integer checkWhetherWorkClothFitWithClientJobForPDL) {
		this.checkWhetherWorkClothFitWithClientJobForPDL = checkWhetherWorkClothFitWithClientJobForPDL;
	}

	public Integer getIsHealthCertificateForPDL() {
		return isHealthCertificateForPDL;
	}

	public void setIsHealthCertificateForPDL(Integer isHealthCertificateForPDL) {
		this.isHealthCertificateForPDL = isHealthCertificateForPDL;
	}

	public Integer getCheckWhetherHealthCertificateRecognizableForPDL() {
		return checkWhetherHealthCertificateRecognizableForPDL;
	}

	public void setCheckWhetherHealthCertificateRecognizableForPDL(
			Integer checkWhetherHealthCertificateRecognizableForPDL) {
		this.checkWhetherHealthCertificateRecognizableForPDL = checkWhetherHealthCertificateRecognizableForPDL;
	}

	public Integer getCheckWhetherHealthCertificateHasClientNameForPDL() {
		return checkWhetherHealthCertificateHasClientNameForPDL;
	}

	public void setCheckWhetherHealthCertificateHasClientNameForPDL(
			Integer checkWhetherHealthCertificateHasClientNameForPDL) {
		this.checkWhetherHealthCertificateHasClientNameForPDL = checkWhetherHealthCertificateHasClientNameForPDL;
	}

	public Integer getCheckWhetherHealthCertificateHasHeadShotForPDL() {
		return checkWhetherHealthCertificateHasHeadShotForPDL;
	}

	public void setCheckWhetherHealthCertificateHasHeadShotForPDL(
			Integer checkWhetherHealthCertificateHasHeadShotForPDL) {
		this.checkWhetherHealthCertificateHasHeadShotForPDL = checkWhetherHealthCertificateHasHeadShotForPDL;
	}

	public Integer getCheckWhetherHealthCertificateHasGenderForPDL() {
		return checkWhetherHealthCertificateHasGenderForPDL;
	}

	public void setCheckWhetherHealthCertificateHasGenderForPDL(
			Integer checkWhetherHealthCertificateHasGenderForPDL) {
		this.checkWhetherHealthCertificateHasGenderForPDL = checkWhetherHealthCertificateHasGenderForPDL;
	}

	public Integer getCheckWhetherHealthCertificateIDNumberForPDL() {
		return checkWhetherHealthCertificateIDNumberForPDL;
	}

	public void setCheckWhetherHealthCertificateIDNumberForPDL(
			Integer checkWhetherHealthCertificateIDNumberForPDL) {
		this.checkWhetherHealthCertificateIDNumberForPDL = checkWhetherHealthCertificateIDNumberForPDL;
	}

	public Integer getCheckWhetherHealthCertificateHasAgeForPDL() {
		return checkWhetherHealthCertificateHasAgeForPDL;
	}

	public void setCheckWhetherHealthCertificateHasAgeForPDL(
			Integer checkWhetherHealthCertificateHasAgeForPDL) {
		this.checkWhetherHealthCertificateHasAgeForPDL = checkWhetherHealthCertificateHasAgeForPDL;
	}

	public Integer getCheckWhetherHealthCertificateExaminationResultForPDL() {
		return checkWhetherHealthCertificateExaminationResultForPDL;
	}

	public void setCheckWhetherHealthCertificateExaminationResultForPDL(
			Integer checkWhetherHealthCertificateExaminationResultForPDL) {
		this.checkWhetherHealthCertificateExaminationResultForPDL = checkWhetherHealthCertificateExaminationResultForPDL;
	}

	public Integer getCheckWhetherHealthCertificateExaminationTimeForPDL() {
		return checkWhetherHealthCertificateExaminationTimeForPDL;
	}

	public void setCheckWhetherHealthCertificateExaminationTimeForPDL(
			Integer checkWhetherHealthCertificateExaminationTimeForPDL) {
		this.checkWhetherHealthCertificateExaminationTimeForPDL = checkWhetherHealthCertificateExaminationTimeForPDL;
	}

	public Integer getCheckWhetherHealthCertificateExaminationNumberForPD() {
		return checkWhetherHealthCertificateExaminationNumberForPD;
	}

	public void setCheckWhetherHealthCertificateExaminationNumberForPD(
			Integer checkWhetherHealthCertificateExaminationNumberForPD) {
		this.checkWhetherHealthCertificateExaminationNumberForPD = checkWhetherHealthCertificateExaminationNumberForPD;
	}

	public Integer getIsSocialSecurityCardForPDL() {
		return isSocialSecurityCardForPDL;
	}

	public void setIsSocialSecurityCardForPDL(Integer isSocialSecurityCardForPDL) {
		this.isSocialSecurityCardForPDL = isSocialSecurityCardForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardRecognizableForPDL() {
		return checkWhetherSocialSecurityCardRecognizableForPDL;
	}

	public void setCheckWhetherSocialSecurityCardRecognizableForPDL(
			Integer checkWhetherSocialSecurityCardRecognizableForPDL) {
		this.checkWhetherSocialSecurityCardRecognizableForPDL = checkWhetherSocialSecurityCardRecognizableForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardHasClientNameForPDL() {
		return checkWhetherSocialSecurityCardHasClientNameForPDL;
	}

	public void setCheckWhetherSocialSecurityCardHasClientNameForPDL(
			Integer checkWhetherSocialSecurityCardHasClientNameForPDL) {
		this.checkWhetherSocialSecurityCardHasClientNameForPDL = checkWhetherSocialSecurityCardHasClientNameForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardHasHeadShotForPDL() {
		return checkWhetherSocialSecurityCardHasHeadShotForPDL;
	}

	public void setCheckWhetherSocialSecurityCardHasHeadShotForPDL(
			Integer checkWhetherSocialSecurityCardHasHeadShotForPDL) {
		this.checkWhetherSocialSecurityCardHasHeadShotForPDL = checkWhetherSocialSecurityCardHasHeadShotForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardHasGenderForPDL() {
		return checkWhetherSocialSecurityCardHasGenderForPDL;
	}

	public void setCheckWhetherSocialSecurityCardHasGenderForPDL(
			Integer checkWhetherSocialSecurityCardHasGenderForPDL) {
		this.checkWhetherSocialSecurityCardHasGenderForPDL = checkWhetherSocialSecurityCardHasGenderForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardHasHasNationForPDL() {
		return checkWhetherSocialSecurityCardHasHasNationForPDL;
	}

	public void setCheckWhetherSocialSecurityCardHasHasNationForPDL(
			Integer checkWhetherSocialSecurityCardHasHasNationForPDL) {
		this.checkWhetherSocialSecurityCardHasHasNationForPDL = checkWhetherSocialSecurityCardHasHasNationForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardIDNumberForPDL() {
		return checkWhetherSocialSecurityCardIDNumberForPDL;
	}

	public void setCheckWhetherSocialSecurityCardIDNumberForPDL(
			Integer checkWhetherSocialSecurityCardIDNumberForPDL) {
		this.checkWhetherSocialSecurityCardIDNumberForPDL = checkWhetherSocialSecurityCardIDNumberForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardHasHasBankCardNoForPDL() {
		return checkWhetherSocialSecurityCardHasHasBankCardNoForPDL;
	}

	public void setCheckWhetherSocialSecurityCardHasHasBankCardNoForPDL(
			Integer checkWhetherSocialSecurityCardHasHasBankCardNoForPDL) {
		this.checkWhetherSocialSecurityCardHasHasBankCardNoForPDL = checkWhetherSocialSecurityCardHasHasBankCardNoForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardHasHasIssuingDateForPDL() {
		return checkWhetherSocialSecurityCardHasHasIssuingDateForPDL;
	}

	public void setCheckWhetherSocialSecurityCardHasHasIssuingDateForPDL(
			Integer checkWhetherSocialSecurityCardHasHasIssuingDateForPDL) {
		this.checkWhetherSocialSecurityCardHasHasIssuingDateForPDL = checkWhetherSocialSecurityCardHasHasIssuingDateForPDL;
	}

	public Integer getCheckWhetherSocialSecurityCardHasHasValidDateForPDL() {
		return checkWhetherSocialSecurityCardHasHasValidDateForPDL;
	}

	public void setCheckWhetherSocialSecurityCardHasHasValidDateForPDL(
			Integer checkWhetherSocialSecurityCardHasHasValidDateForPDL) {
		this.checkWhetherSocialSecurityCardHasHasValidDateForPDL = checkWhetherSocialSecurityCardHasHasValidDateForPDL;
	}

	public Integer getIsSalaryMessageForPDL() {
		return isSalaryMessageForPDL;
	}

	public void setIsSalaryMessageForPDL(Integer isSalaryMessageForPDL) {
		this.isSalaryMessageForPDL = isSalaryMessageForPDL;
	}

	public Integer getCheckWhetherSalaryMessageRecognizableForPDL() {
		return checkWhetherSalaryMessageRecognizableForPDL;
	}

	public void setCheckWhetherSalaryMessageRecognizableForPDL(
			Integer checkWhetherSalaryMessageRecognizableForPDL) {
		this.checkWhetherSalaryMessageRecognizableForPDL = checkWhetherSalaryMessageRecognizableForPDL;
	}

	public Integer getCheckWhetherSalaryMessageHasBankCardTheLastFourNoForPDL() {
		return checkWhetherSalaryMessageHasBankCardTheLastFourNoForPDL;
	}

	public void setCheckWhetherSalaryMessageHasBankCardTheLastFourNoForPDL(
			Integer checkWhetherSalaryMessageHasBankCardTheLastFourNoForPDL) {
		this.checkWhetherSalaryMessageHasBankCardTheLastFourNoForPDL = checkWhetherSalaryMessageHasBankCardTheLastFourNoForPDL;
	}

	public Integer getCheckWhetherSalaryMessageHasBankNameForPDL() {
		return checkWhetherSalaryMessageHasBankNameForPDL;
	}

	public void setCheckWhetherSalaryMessageHasBankNameForPDL(
			Integer checkWhetherSalaryMessageHasBankNameForPDL) {
		this.checkWhetherSalaryMessageHasBankNameForPDL = checkWhetherSalaryMessageHasBankNameForPDL;
	}

	public Integer getCheckWhetherSalaryMessageHasDateForPDL() {
		return checkWhetherSalaryMessageHasDateForPDL;
	}

	public Integer getIsWorkPermitOrLicenseForPDL() {
		return isWorkPermitOrLicenseForPDL;
	}

	public void setIsWorkPermitOrLicenseForPDL(Integer isWorkPermitOrLicenseForPDL) {
		this.isWorkPermitOrLicenseForPDL = isWorkPermitOrLicenseForPDL;
	}

	public Integer getCheckWhetherWorkPermitOrLicenseRecognizableForPDL() {
		return checkWhetherWorkPermitOrLicenseRecognizableForPDL;
	}

	public void setCheckWhetherWorkPermitOrLicenseRecognizableForPDL(
			Integer checkWhetherWorkPermitOrLicenseRecognizableForPDL) {
		this.checkWhetherWorkPermitOrLicenseRecognizableForPDL = checkWhetherWorkPermitOrLicenseRecognizableForPDL;
	}

	public Integer getCheckWhetherWorkPermitOrLicenseHasCompanyNameForPDL() {
		return checkWhetherWorkPermitOrLicenseHasCompanyNameForPDL;
	}

	public void setCheckWhetherWorkPermitOrLicenseHasCompanyNameForPDL(
			Integer checkWhetherWorkPermitOrLicenseHasCompanyNameForPDL) {
		this.checkWhetherWorkPermitOrLicenseHasCompanyNameForPDL = checkWhetherWorkPermitOrLicenseHasCompanyNameForPDL;
	}

	public Integer getCheckWhetherWorkPermitOrLicenseHasClientNameForPDL() {
		return checkWhetherWorkPermitOrLicenseHasClientNameForPDL;
	}

	public void setCheckWhetherWorkPermitOrLicenseHasClientNameForPDL(
			Integer checkWhetherWorkPermitOrLicenseHasClientNameForPDL) {
		this.checkWhetherWorkPermitOrLicenseHasClientNameForPDL = checkWhetherWorkPermitOrLicenseHasClientNameForPDL;
	}

	public Integer getCheckWhetherWorkPermitOrLicenseHasHeadShotForPDL() {
		return checkWhetherWorkPermitOrLicenseHasHeadShotForPDL;
	}

	public void setCheckWhetherWorkPermitOrLicenseHasHeadShotForPDL(
			Integer checkWhetherWorkPermitOrLicenseHasHeadShotForPDL) {
		this.checkWhetherWorkPermitOrLicenseHasHeadShotForPDL = checkWhetherWorkPermitOrLicenseHasHeadShotForPDL;
	}

	public Integer getCheckWhetherWorkPermitOrLicenseHasJobNumberForPDL() {
		return checkWhetherWorkPermitOrLicenseHasJobNumberForPDL;
	}

	public void setCheckWhetherWorkPermitOrLicenseHasJobNumberForPDL(
			Integer checkWhetherWorkPermitOrLicenseHasJobNumberForPDL) {
		this.checkWhetherWorkPermitOrLicenseHasJobNumberForPDL = checkWhetherWorkPermitOrLicenseHasJobNumberForPDL;
	}

	public Integer getCheckWhetherWorkPermitOrLicenseHasDepartmentForPDL() {
		return checkWhetherWorkPermitOrLicenseHasDepartmentForPDL;
	}

	public void setCheckWhetherWorkPermitOrLicenseHasDepartmentForPDL(
			Integer checkWhetherWorkPermitOrLicenseHasDepartmentForPDL) {
		this.checkWhetherWorkPermitOrLicenseHasDepartmentForPDL = checkWhetherWorkPermitOrLicenseHasDepartmentForPDL;
	}

	public Integer getCheckWhetherWorkPermitOrLicenseHasJobTitleForPDL() {
		return checkWhetherWorkPermitOrLicenseHasJobTitleForPDL;
	}

	public void setCheckWhetherWorkPermitOrLicenseHasJobTitleForPDL(
			Integer checkWhetherWorkPermitOrLicenseHasJobTitleForPDL) {
		this.checkWhetherWorkPermitOrLicenseHasJobTitleForPDL = checkWhetherWorkPermitOrLicenseHasJobTitleForPDL;
	}

	public void setCheckWhetherSalaryMessageHasDateForPDL(
			Integer checkWhetherSalaryMessageHasDateForPDL) {
		this.checkWhetherSalaryMessageHasDateForPDL = checkWhetherSalaryMessageHasDateForPDL;
	}

	public Integer getCheckWhetherSalaryMessageHasSalaryAmountForPDL() {
		return checkWhetherSalaryMessageHasSalaryAmountForPDL;
	}

	public void setCheckWhetherSalaryMessageHasSalaryAmountForPDL(
			Integer checkWhetherSalaryMessageHasSalaryAmountForPDL) {
		this.checkWhetherSalaryMessageHasSalaryAmountForPDL = checkWhetherSalaryMessageHasSalaryAmountForPDL;
	}

	public Integer getCheckWhetherSalaryMessageHasSenderInfoForPDL() {
		return checkWhetherSalaryMessageHasSenderInfoForPDL;
	}

	public void setCheckWhetherSalaryMessageHasSenderInfoForPDL(
			Integer checkWhetherSalaryMessageHasSenderInfoForPDL) {
		this.checkWhetherSalaryMessageHasSenderInfoForPDL = checkWhetherSalaryMessageHasSenderInfoForPDL;
	}
}
