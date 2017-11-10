package engine.rule.model.in.app;

import java.util.Set;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.util.CommonUtil;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "调查信息")
public class InvestigationInForm extends BaseForm{

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactPhoneCallResult")
	@ModelField(name = "第一联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckFirstContactPhoneCallResultForApp")
	private Integer checkFirstContactPhoneCallResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactPhoneAnswererResult")
	@ModelField(name = "第一联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckFirstContactPhoneAnswererResultForApp")
	private Integer checkFirstContactPhoneAnswererResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactSceneIntroduceResult")
	@ModelField(name = "第一联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckFirstContactSceneIntroduceResultForApp")
	private Integer checkFirstContactSceneIntroduceResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactIdentificationResult")
	@ModelField(name = "第一联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckFirstContactIdentificationResultForApp")
	private Integer checkFirstContactIdentificationResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactSoundJudgeResult")
	private Integer checkFirstContactSoundJudgeResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactHereJudgeResult")
	@ModelField(name = "第一联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckFirstContactHereJudgeResultForApp")
	private Integer checkFirstContactHereJudgeResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactCareApplicantResult")
	@ModelField(name = "第一联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckFirstContactCareApplicantResultForApp")
	private Integer checkFirstContactCareApplicantResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactAttitudeOfPhoneResult")
	@ModelField(name = "第一联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckFirstContactAttitudeOfPhoneResultForApp")
	private Integer checkFirstContactAttitudeOfPhoneResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckFirstContactRiskPromptResult")
	private Integer checkFirstContactRiskPromptResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactPhoneCallResult")
	@ModelField(name = "第二联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckSecondContactPhoneCallResultForApp")
	private Integer checkSecondContactPhoneCallResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactPhoneAnswererResult")
	@ModelField(name = "第二联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckSecondContactPhoneAnswererResultForApp")
	private Integer checkSecondContactPhoneAnswererResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactSceneIntroduceResult")
	@ModelField(name = "第二联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckSecondContactSceneIntroduceResultForApp")
	private Integer checkSecondContactSceneIntroduceResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactIdentificationResult")
	@ModelField(name = "第二联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckSecondContactIdentificationResultForApp")
	private Integer checkSecondContactIdentificationResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactSoundJudgeResult")
	private Integer checkSecondContactSoundJudgeResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactHereJudgeResult")
	@ModelField(name = "第二联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckSecondContactHereJudgeResultForApp")
	private Integer checkSecondContactHereJudgeResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactCareApplicantResult")
	@ModelField(name = "第二联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckSecondContactCareApplicantResultForApp")
	private Integer checkSecondContactCareApplicantResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactAttitudeOfPhoneResult")
	@ModelField(name = "第二联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckSecondContactAttitudeOfPhoneResultForApp")
	private Integer checkSecondContactAttitudeOfPhoneResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckSecondContactRiskPromptResult")
	private Integer checkSecondContactRiskPromptResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactPhoneCallResult")
	@ModelField(name = "第三联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckThirdContactPhoneCallResultForApp")
	private Integer checkThirdContactPhoneCallResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactPhoneAnswererResult")
	@ModelField(name = "第三联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckThirdContactPhoneAnswererResultForApp")
	private Integer checkThirdContactPhoneAnswererResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactSceneIntroduceResult")
	@ModelField(name = "第三联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckThirdContactSceneIntroduceResultForApp")
	private Integer checkThirdContactSceneIntroduceResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactIdentificationResult")
	@ModelField(name = "第三联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckThirdContactIdentificationResultForApp")
	private Integer checkThirdContactIdentificationResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactSoundJudgeResult")
	private Integer checkThirdContactSoundJudgeResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactHereJudgeResult")
	@ModelField(name = "第三联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckThirdContactHereJudgeResultForApp")
	private Integer checkThirdContactHereJudgeResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactCareApplicantResult")
	@ModelField(name = "第三联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckThirdContactCareApplicantResultForApp")
	private Integer checkThirdContactCareApplicantResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactAttitudeOfPhoneResult")
	@ModelField(name = "第三联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckThirdContactAttitudeOfPhoneResultForApp")
	private Integer checkThirdContactAttitudeOfPhoneResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckThirdContactRiskPromptResult")
	private Integer checkThirdContactRiskPromptResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneCallResult_11")
	@ModelField(name = "第一补充联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneCallResult_11ForApp")
	private Integer checkAdditionalContactPhoneCallResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneAnswererResult_11")
	@ModelField(name = "第一补充联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneAnswererResult_11ForApp")
	private Integer checkAdditionalContactPhoneAnswererResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSceneIntroduceResult_11")
	@ModelField(name = "第一补充联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckAdditionalContactSceneIntroduceResult_11ForApp")
	private Integer checkAdditionalContactSceneIntroduceResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactIdentificationResult_11")
	@ModelField(name = "第一补充联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckAdditionalContactIdentificationResult_11ForApp")
	private Integer checkAdditionalContactIdentificationResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSoundJudgeResult_11")
	private Integer checkAdditionalContactSoundJudgeResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactHereJudgeResult_11")
	@ModelField(name = "第一补充联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckAdditionalContactHereJudgeResult_11ForApp")
	private Integer checkAdditionalContactHereJudgeResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactCareApplicantResult_11")
	@ModelField(name = "第一补充联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckAdditionalContactCareApplicantResult_11ForApp")
	private Integer checkAdditionalContactCareApplicantResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactAttitudeOfPhoneResult_11")
	@ModelField(name = "第一补充联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckAdditionalContactAttitudeOfPhoneResult_11ForApp")
	private Integer checkAdditionalContactAttitudeOfPhoneResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactRiskPromptResult_11")
	private Integer checkAdditionalContactRiskPromptResult_11 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneCallResult_12")
	@ModelField(name = "第二补充联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneCallResult_12ForApp")
	private Integer checkAdditionalContactPhoneCallResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneAnswererResult_12")
	@ModelField(name = "第二补充联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneAnswererResult_12ForApp")
	private Integer checkAdditionalContactPhoneAnswererResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSceneIntroduceResult_12")
	@ModelField(name = "第二补充联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckAdditionalContactSceneIntroduceResult_12ForApp")
	private Integer checkAdditionalContactSceneIntroduceResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactIdentificationResult_12")
	@ModelField(name = "第二补充联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckAdditionalContactIdentificationResult_12ForApp")
	private Integer checkAdditionalContactIdentificationResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSoundJudgeResult_12")
	private Integer checkAdditionalContactSoundJudgeResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactHereJudgeResult_12")
	@ModelField(name = "第二补充联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckAdditionalContactHereJudgeResult_12ForApp")
	private Integer checkAdditionalContactHereJudgeResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactCareApplicantResult_12")
	@ModelField(name = "第二补充联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckAdditionalContactCareApplicantResult_12ForApp")
	private Integer checkAdditionalContactCareApplicantResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactAttitudeOfPhoneResult_12")
	@ModelField(name = "第二补充联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckAdditionalContactAttitudeOfPhoneResult_12ForApp")
	private Integer checkAdditionalContactAttitudeOfPhoneResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactRiskPromptResult_12")
	private Integer checkAdditionalContactRiskPromptResult_12 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneCallResult_13")
	@ModelField(name = "第三补充联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneCallResult_13ForApp")
	private Integer checkAdditionalContactPhoneCallResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneAnswererResult_13")
	@ModelField(name = "第三补充联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneAnswererResult_13ForApp")
	private Integer checkAdditionalContactPhoneAnswererResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSceneIntroduceResult_13")
	@ModelField(name = "第三补充联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckAdditionalContactSceneIntroduceResult_13ForApp")
	private Integer checkAdditionalContactSceneIntroduceResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactIdentificationResult_13")
	@ModelField(name = "第三补充联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckAdditionalContactIdentificationResult_13ForApp")
	private Integer checkAdditionalContactIdentificationResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSoundJudgeResult_13")
	private Integer checkAdditionalContactSoundJudgeResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactHereJudgeResult_13")
	@ModelField(name = "第三补充联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckAdditionalContactHereJudgeResult_13ForApp")
	private Integer checkAdditionalContactHereJudgeResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactCareApplicantResult_13")
	@ModelField(name = "第三补充联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckAdditionalContactCareApplicantResult_13ForApp")
	private Integer checkAdditionalContactCareApplicantResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactAttitudeOfPhoneResult_13")
	@ModelField(name = "第三补充联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckAdditionalContactAttitudeOfPhoneResult_13ForApp")
	private Integer checkAdditionalContactAttitudeOfPhoneResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactRiskPromptResult_13")
	private Integer checkAdditionalContactRiskPromptResult_13 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneCallResult_14")
	@ModelField(name = "第四补充联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneCallResult_14ForApp")
	private Integer checkAdditionalContactPhoneCallResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneAnswererResult_14")
	@ModelField(name = "第四补充联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneAnswererResult_14ForApp")
	private Integer checkAdditionalContactPhoneAnswererResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSceneIntroduceResult_14")
	@ModelField(name = "第四补充联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckAdditionalContactSceneIntroduceResult_14ForApp")
	private Integer checkAdditionalContactSceneIntroduceResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactIdentificationResult_14")
	@ModelField(name = "第四补充联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckAdditionalContactIdentificationResult_14ForApp")
	private Integer checkAdditionalContactIdentificationResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSoundJudgeResult_14")
	private Integer checkAdditionalContactSoundJudgeResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactHereJudgeResult_14")
	@ModelField(name = "第四补充联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckAdditionalContactHereJudgeResult_14ForApp")
	private Integer checkAdditionalContactHereJudgeResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactCareApplicantResult_14")
	@ModelField(name = "第四补充联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckAdditionalContactCareApplicantResult_14ForApp")
	private Integer checkAdditionalContactCareApplicantResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactAttitudeOfPhoneResult_14")
	@ModelField(name = "第四补充联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckAdditionalContactAttitudeOfPhoneResult_14ForApp")
	private Integer checkAdditionalContactAttitudeOfPhoneResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactRiskPromptResult_14")
	private Integer checkAdditionalContactRiskPromptResult_14 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneCallResult_15")
	@ModelField(name = "第五补充联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneCallResult_15ForApp")
	private Integer checkAdditionalContactPhoneCallResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneAnswererResult_15")
	@ModelField(name = "第五补充联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneAnswererResult_15ForApp")
	private Integer checkAdditionalContactPhoneAnswererResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSceneIntroduceResult_15")
	@ModelField(name = "第五补充联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckAdditionalContactSceneIntroduceResult_15ForApp")
	private Integer checkAdditionalContactSceneIntroduceResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactIdentificationResult_15")
	@ModelField(name = "第五补充联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckAdditionalContactIdentificationResult_15ForApp")
	private Integer checkAdditionalContactIdentificationResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSoundJudgeResult_15")
	private Integer checkAdditionalContactSoundJudgeResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactHereJudgeResult_15")
	@ModelField(name = "第五补充联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckAdditionalContactHereJudgeResult_15ForApp")
	private Integer checkAdditionalContactHereJudgeResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactCareApplicantResult_15")
	@ModelField(name = "第五补充联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckAdditionalContactCareApplicantResult_15ForApp")
	private Integer checkAdditionalContactCareApplicantResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactAttitudeOfPhoneResult_15")
	@ModelField(name = "第五补充联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckAdditionalContactAttitudeOfPhoneResult_15ForApp")
	private Integer checkAdditionalContactAttitudeOfPhoneResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactRiskPromptResult_15")
	private Integer checkAdditionalContactRiskPromptResult_15 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneCallResult_16")
	@ModelField(name = "第六补充联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneCallResult_16ForApp")
	private Integer checkAdditionalContactPhoneCallResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneAnswererResult_16")
	@ModelField(name = "第六补充联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneAnswererResult_16ForApp")
	private Integer checkAdditionalContactPhoneAnswererResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSceneIntroduceResult_16")
	@ModelField(name = "第六补充联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckAdditionalContactSceneIntroduceResult_16ForApp")
	private Integer checkAdditionalContactSceneIntroduceResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactIdentificationResult_16")
	@ModelField(name = "第六补充联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckAdditionalContactIdentificationResult_16ForApp")
	private Integer checkAdditionalContactIdentificationResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSoundJudgeResult_16")
	private Integer checkAdditionalContactSoundJudgeResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactHereJudgeResult_16")
	@ModelField(name = "第六补充联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckAdditionalContactHereJudgeResult_16ForApp")
	private Integer checkAdditionalContactHereJudgeResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactCareApplicantResult_16")
	@ModelField(name = "第六补充联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckAdditionalContactCareApplicantResult_16ForApp")
	private Integer checkAdditionalContactCareApplicantResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactAttitudeOfPhoneResult_16")
	@ModelField(name = "第六补充联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckAdditionalContactAttitudeOfPhoneResult_16ForApp")
	private Integer checkAdditionalContactAttitudeOfPhoneResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactRiskPromptResult_16")
	private Integer checkAdditionalContactRiskPromptResult_16 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneCallResult_17")
	@ModelField(name = "第七补充联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneCallResult_17ForApp")
	private Integer checkAdditionalContactPhoneCallResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneAnswererResult_17")
	@ModelField(name = "第七补充联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneAnswererResult_17ForApp")
	private Integer checkAdditionalContactPhoneAnswererResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSceneIntroduceResult_17")
	@ModelField(name = "第七补充联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckAdditionalContactSceneIntroduceResult_17ForApp")
	private Integer checkAdditionalContactSceneIntroduceResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactIdentificationResult_17")
	@ModelField(name = "第七补充联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckAdditionalContactIdentificationResult_17ForApp")
	private Integer checkAdditionalContactIdentificationResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSoundJudgeResult_17")
	private Integer checkAdditionalContactSoundJudgeResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactHereJudgeResult_17")
	@ModelField(name = "第七补充联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckAdditionalContactHereJudgeResult_17ForApp")
	private Integer checkAdditionalContactHereJudgeResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactCareApplicantResult_17")
	@ModelField(name = "第七补充联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckAdditionalContactCareApplicantResult_17ForApp")
	private Integer checkAdditionalContactCareApplicantResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactAttitudeOfPhoneResult_17")
	@ModelField(name = "第七补充联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckAdditionalContactAttitudeOfPhoneResult_17ForApp")
	private Integer checkAdditionalContactAttitudeOfPhoneResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactRiskPromptResult_17")
	private Integer checkAdditionalContactRiskPromptResult_17 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneCallResult_18")
	@ModelField(name = "第八补充联系人电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneCallResult_18ForApp")
	private Integer checkAdditionalContactPhoneCallResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactPhoneAnswererResult_18")
	@ModelField(name = "第八补充联系人电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckAdditionalContactPhoneAnswererResult_18ForApp")
	private Integer checkAdditionalContactPhoneAnswererResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSceneIntroduceResult_18")
	@ModelField(name = "第八补充联系人电话审核V3_场景陈述结果", bindDomain = "engine.rule.domain.CheckAdditionalContactSceneIntroduceResult_18ForApp")
	private Integer checkAdditionalContactSceneIntroduceResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactIdentificationResult_18")
	@ModelField(name = "第八补充联系人电话审核V3_申请人关系审核", bindDomain = "engine.rule.domain.CheckAdditionalContactIdentificationResult_18ForApp")
	private Integer checkAdditionalContactIdentificationResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactSoundJudgeResult_18")
	private Integer checkAdditionalContactSoundJudgeResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactHereJudgeResult_18")
	@ModelField(name = "第八补充联系人电话审核V3_联系人是否在分期现场", bindDomain = "engine.rule.domain.CheckAdditionalContactHereJudgeResult_18ForApp")
	private Integer checkAdditionalContactHereJudgeResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactCareApplicantResult_18")
	@ModelField(name = "第八补充联系人电话审核V3_主观判断联系人是否关心申请人", bindDomain = "engine.rule.domain.CheckAdditionalContactCareApplicantResult_18ForApp")
	private Integer checkAdditionalContactCareApplicantResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactAttitudeOfPhoneResult_18")
	@ModelField(name = "第八补充联系人电话审核V3_主观判断联系人是否对本次通话反感", bindDomain = "engine.rule.domain.CheckAdditionalContactAttitudeOfPhoneResult_18ForApp")
	private Integer checkAdditionalContactAttitudeOfPhoneResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckAdditionalContactRiskPromptResult_18")
	private Integer checkAdditionalContactRiskPromptResult_18 = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCompanyPhoneCallResult")
	@ModelField(name = "公司电话审核V3_单位电话拨打情况", bindDomain = "engine.rule.domain.CheckCompanyPhoneCallResultForApp")
	private Integer checkCompanyPhoneCallResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCompanyIdentificationResult")
	@ModelField(name = "公司电话审核V3_电话接通后，通话人情况", bindDomain = "engine.rule.domain.CheckCompanyIdentificationResultForApp")
	private Integer checkCompanyIdentificationResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCompanyPhoneRelationshipResult")
	@ModelField(name = "公司电话审核V3_与联系人关系", bindDomain = "engine.rule.domain.CheckCompanyPhoneRelationshipResultForApp")
	private Integer checkCompanyPhoneRelationshipResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCompanyApplicantCheckResult")
	@ModelField(name = "公司电话审核V3_申请人核对", bindDomain = "engine.rule.domain.CheckCompanyApplicantCheckResultForApp")
	private Integer checkCompanyApplicantCheckResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCompanyAddressConsistency")
	@ModelField(name = "公司电话审核V3_单位地址核对", bindDomain = "engine.rule.domain.CheckCompanyAddressConsistencyForApp")
	private Integer checkCompanyAddressConsistency = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCompanyAnswerPersonRiskPromptResult")
	private Integer checkCompanyAnswerPersonRiskPromptResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCompanyAnswerPersonAnswerResult")	
	private Integer checkCompanyAnswerPersonAnswerResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_InputIdCardInfoIsOtherCertProvided")
	@ModelField(name = "身份信息录入_是否提供了其他证件", bindDomain = "engine.rule.domain.InputIdCardInfoIsOtherCertProvidedForApp")
	private Integer inputIdCardInfoIsOtherCertProvided = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_InputIdCardInfoOtherCertType")
	private Integer inputIdCardInfoOtherCertType = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckIOUIsIOU")
	@ModelField(name = "电子借条审核V3_是否是电子借条", bindDomain = "engine.rule.domain.CheckIOUIsIOUForApp")
	private Integer checkIOUIsIOU = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckIOUIsHeadPhotoClarity")
	@ModelField(name = "电子借条审核V3_是否清晰可辨识", bindDomain = "engine.rule.domain.CheckIOUIsHeadPhotoClarityForApp")
	private Integer checkIOUIsHeadPhotoClarity = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckIOUIsTextClarify")
	@ModelField(name = "电子借条审核V3_电子借条审核信息是否可以辨认", bindDomain = "engine.rule.domain.CheckIOUIsTextClarifyForApp")
	private Integer checkIOUIsTextClarify = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckIOUImageComparision")
	@ModelField(name = "电子借条审核V3_两张照片比对", bindDomain = "engine.rule.domain.CheckIOUImageComparisionForApp")
	private Integer checkIOUImageComparision = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckIOUMobileColor")
	@ModelField(name = "电子借条审核V3_检查客户电子借条的手机外壳颜色", bindDomain = "engine.rule.domain.CheckIOUMobileColorForApp")
	private Integer checkIOUMobileColor = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckIOURingFinger")
	@ModelField(name = "电子借条审核V3_检查客户是否左手无名指戴戒指", bindDomain = "engine.rule.domain.CheckIOURingFingerForApp")
	private Integer checkIOURingFinger = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckIOUIsSmile")
	@ModelField(name = "电子借条审核V3_客户是否有笑容", bindDomain = "engine.rule.domain.CheckIOUIsSmileForApp")
	private Integer checkIOUIsSmile = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserPhoneCallResult")
	@ModelField(name = "客户电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckUserPhoneCallResultForApp")
	private Integer checkUserPhoneCallResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserPhoneAnswererResult")
	@ModelField(name = "客户电话审核V3_通话人接听情况", bindDomain = "engine.rule.domain.CheckUserPhoneAnswererResultForApp")
	private Integer checkUserPhoneAnswererResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserSceneIntroduceResult")
	@ModelField(name = "客户电话审核V3_客户是否接受", bindDomain = "engine.rule.domain.CheckUserSceneIntroduceResultForApp")
	private Integer checkUserSceneIntroduceResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserIsCancelApplication")
	@ModelField(name = "客户电话审核V3_客户是否取消申请", bindDomain = "engine.rule.domain.CheckUserIsCancelApplicationForApp")
	private Integer checkUserIsCancelApplication = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserIdentification")
	@ModelField(name = "客户电话审核V3_身份确认", bindDomain = "engine.rule.domain.CheckUserIdentificationForApp")
	private Integer checkUserIdentification = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserSymbolicAnimal")
	@ModelField(name = "客户电话审核V3_生肖确认", bindDomain = "engine.rule.domain.CheckUserSymbolicAnimalForApp")
	private Integer checkUserSymbolicAnimal = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserCompanyName")
	@ModelField(name = "客户电话审核V3_单位名称确认", bindDomain = "engine.rule.domain.CheckUserCompanyNameForApp")
	private Integer checkUserCompanyName = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserIDCardAddress")
	@ModelField(name = "客户电话审核V3_身份证住址确认", bindDomain = "engine.rule.domain.CheckUserIDCardAddressForApp")
	private Integer checkUserIDCardAddress = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserInstalmentNumberAndValue")
	@ModelField(name = "客户电话审核V3_开放式问题询问产品熟悉程度", bindDomain = "engine.rule.domain.CheckUserInstalmentNumberAndValueForApp")
	private Integer checkUserInstalmentNumberAndValue = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserJXLInforCrawlStatus")
	@ModelField(name = "客户电话审核V3_聚信立信息收集", bindDomain = "engine.rule.domain.CheckUserJXLInforCrawlStatusForApp")
	private Integer checkUserJXLInforCrawlStatus = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserConsequenceIntroduceResult")
	@ModelField(name = "客户电话审核V3_后果陈述", bindDomain = "engine.rule.domain.CheckUserConsequenceIntroduceResultForApp")
	private Integer checkUserConsequenceIntroduceResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserRiskReveal")
	private Integer checkUserRiskReveal = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserSound")
	private Integer checkUserSound = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserCancelApplicationReason")
	@ModelField(name = "客户电话审核V3_客户执意取消申请，询问原因", bindDomain = "engine.rule.domain.CheckUserCancelApplicationReasonForApp")
	private Integer checkUserCancelApplicationReason = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserCarenessLevel")
	@ModelField(name = "客户电话审核V3_主观判断申请人对使用CF产品重视程度", bindDomain = "engine.rule.domain.CheckUserCarenessLevelForApp")
	private Integer checkUserCarenessLevel = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckUserDislikeLevel")
	@ModelField(name = "客户电话审核V3_主观判断联系人对本通电话反感程度", bindDomain = "engine.rule.domain.CheckUserDislikeLevelForApp")
	private Integer checkUserDislikeLevel = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckMerchantPhoneCallResult")
	@ModelField(name = "商户电话审核V3_电话是否正常接听", bindDomain = "engine.rule.domain.CheckMerchantPhoneCallResultForApp")
	private Integer checkMerchantPhoneCallResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckMerchantReplacedBySales")
	@ModelField(name = "商户电话审核V3_致电销售人员代行放款信息核对", bindDomain = "engine.rule.domain.CheckMerchantReplacedBySalesForApp")
	private Integer checkMerchantReplacedBySales = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckMerchantAnswererResult")
	@ModelField(name = "商户电话审核V3_电话接通后，通话人情况", bindDomain = "engine.rule.domain.CheckMerchantAnswererResultForApp")
	private Integer checkMerchantAnswererResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckMerchantStoreNameConsistent")
	@ModelField(name = "商户电话审核V3_门店名称确认", bindDomain = "engine.rule.domain.CheckMerchantStoreNameConsistentForApp")
	private Integer checkMerchantStoreNameConsistent = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckMerchantCustomerNameConsistent")
	@ModelField(name = "商户电话审核V3_门店确定客户姓名", bindDomain = "engine.rule.domain.CheckMerchantCustomerNameConsistentForApp")
	private Integer checkMerchantCustomerNameConsistent = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckMerchantCustomerOnSpot")
	@ModelField(name = "商户电话审核V3_客户是否在现场", bindDomain = "engine.rule.domain.CheckMerchantCustomerOnSpotForApp")
	private Integer checkMerchantCustomerOnSpot = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckMerchantPurchaseConfirmed")
	@ModelField(name = "商户电话审核V3_购机条件确定，等待放款", bindDomain = "engine.rule.domain.CheckMerchantPurchaseConfirmedForApp")
	private Integer checkMerchantPurchaseConfirmed = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckMerchantRiskHint")
	private Integer checkMerchantRiskHint = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBuckleIsBuckleAgreement")
	@ModelField(name = "代扣协议审核V3_检查上传照片是否为代扣协议", bindDomain = "engine.rule.domain.CheckBuckleIsBuckleAgreementForApp")
	private Integer checkBuckleIsBuckleAgreement = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBuckleTextClarifyResult")
	@ModelField(name = "代扣协议审核V3_客户代扣协议照片是否清晰可以辨识", bindDomain = "engine.rule.domain.CheckBuckleTextClarifyResultForApp")
	private Integer checkBuckleTextClarifyResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBuckleSignatureClarifyResult")
	@ModelField(name = "代扣协议审核V3_客户代扣协议照片中客户填写信息是否清晰可以辨认", bindDomain = "engine.rule.domain.CheckBuckleSignatureClarifyResultForApp")
	private Integer checkBuckleSignatureClarifyResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBuckleDoesSignatureAndIdNameMatch")
	@ModelField(name = "代扣协议审核V3_客户代扣协议照片中客户填写信息与申请人信息是否一致", bindDomain = "engine.rule.domain.CheckBuckleDoesSignatureAndIdNameMatchForApp")
	private Integer checkBuckleDoesSignatureAndIdNameMatch = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCourtExecuted")
	@ModelField(name = "法院被执行审核V3_法院被执行记录", bindDomain = "engine.rule.domain.CheckCourtExecutedForApp")
	private Integer checkCourtExecuted = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckHomeCreditRecord")
	@ModelField(name = "捷信审核V3_捷信记录", bindDomain = "engine.rule.domain.CheckHomeCreditRecordForApp")
	private Integer checkHomeCreditRecord = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckBilFinanceRecord")
	@ModelField(name = "佰仟审核V3_佰仟记录", bindDomain = "engine.rule.domain.CheckBilFinanceRecordForApp")
	private Integer checkBilFinanceRecord = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckDafyRecord")
	@ModelField(name = "达飞和好贷审核V3_达飞记录", bindDomain = "engine.rule.domain.CheckDafyRecordForApp")
	private Integer checkDafyRecord = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckHaodaiRecord")
	@ModelField(name = "达飞和好贷审核V3_好贷记录", bindDomain = "engine.rule.domain.CheckHaodaiRecordForApp")
	private Integer checkHaodaiRecord = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckCreditEaseRecord")
	@ModelField(name = "宜信审核V3_宜信记录", bindDomain = "engine.rule.domain.CheckCreditEaseRecordForApp")
	private Integer checkCreditEaseRecord = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckVCashRecord")
	@ModelField(name = "维信审核V3_维信记录", bindDomain = "engine.rule.domain.CheckVCashRecordForApp")
	private Integer checkVCashRecord = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_LoanMoneyResult")
	@ModelField(name = "放款V3_转账情况", bindDomain = "engine.rule.domain.LoanMoneyResultForApp")
	private Integer loanMoneyResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_LoanMoneyFailureReason")
	@ModelField(name = "放款V3_转账失败原因", bindDomain = "engine.rule.domain.LoanMoneyFailureReasonForApp")
	private Integer loanMoneyFailureReason = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_FinishApplicationOperation")
	@ModelField(name = "结束申请V3_操作", bindDomain = "engine.rule.domain.FinishApplicationOperationForApp")
	private Integer finishApplicationOperation = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageHeadPhotoPersonalPic")
	@ModelField(name = "头像照审核V3_现场照片人脸是否清晰可辨识", bindDomain = "engine.rule.domain.CheckImageHeadPhotoPersonalPicForApp")
	private Integer checkImageHeadPhotoPersonalPic = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageHeadPhotoHeadDirection")
	@ModelField(name = "头像照审核V3_头像现场照片头像方向", bindDomain = "engine.rule.domain.CheckImageHeadPhotoHeadDirectionForApp")
	private Integer checkImageHeadPhotoHeadDirection = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageHeadPhotoFaceExpression")
	@ModelField(name = "头像照审核V3_头像现场照片表情", bindDomain = "engine.rule.domain.CheckImageHeadPhotoFaceExpressionForApp")
	private Integer checkImageHeadPhotoFaceExpression = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherImageIsIdCardPhoto")
	@ModelField(name = "身份证照审核V3_是否是身份证件", bindDomain = "engine.rule.domain.CheckWhetherImageIsIdCardPhotoForApp")
	private Integer checkWhetherImageIsIdCardPhoto = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageIdCardPersonalPic")
	@ModelField(name = "身份证照审核V3_身份证件是否清晰可辨识", bindDomain = "engine.rule.domain.CheckImageIdCardPersonalPicForApp")
	private Integer checkImageIdCardPersonalPic = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageIdCardInfo")
	@ModelField(name = "身份证照审核V3_身份证信息是否可以辨认", bindDomain = "engine.rule.domain.CheckImageIdCardInfoForApp")
	private Integer checkImageIdCardInfo = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImagePhotoFromPoliceExist")
	@ModelField(name = "头像对比V3_公安部照片是否存在", bindDomain = "engine.rule.domain.CheckImagePhotoFromPoliceExistForApp")
	private Integer checkImagePhotoFromPoliceExist = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckImageComparision")
	@ModelField(name = "头像对比V3_三张照片是否一致", bindDomain = "engine.rule.domain.CheckImageComparisionForApp")
	private Integer checkImageComparision = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherImageIsBankCard")
	@ModelField(name = "银行卡审核V3_是否是银行卡", bindDomain = "engine.rule.domain.CheckWhetherImageIsBankCardForApp")
	private Integer checkWhetherImageIsBankCard = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBankCardPhotoIsRecognizable")
	@ModelField(name = "银行卡审核V3_是否清晰可辨识", bindDomain = "engine.rule.domain.CheckWhetherBankCardPhotoIsRecognizableForApp")
	private Integer checkWhetherBankCardPhotoIsRecognizable = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherBankCardInfoIsRecognizable")
	@ModelField(name = "银行卡审核V3_卡片审核信息是否可以辨认", bindDomain = "engine.rule.domain.CheckWhetherBankCardInfoIsRecognizableForApp")
	private Integer checkWhetherBankCardInfoIsRecognizable = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckPersonalInfoCompanyTypeResult")
	@ModelField(name = "个人信息审核V3_客户公司性质判断", bindDomain = "engine.rule.domain.CheckPersonalInfoCompanyTypeResultForApp")
	private Integer checkPersonalInfoCompanyTypeResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;


	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_TransactionMonitorResult")
	@ModelField(name = "App交易监控_判断结果", bindDomain = "engine.rule.domain.TransactionMonitorResultForApp")
	private Integer transactionMonitorResult = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_TransactionMonitorRejectReason")
	@ModelField(name = "App交易监控_拒绝原因", bindDomain = "engine.rule.domain.TransactionMonitorRejectReasonForApp")
	private Integer transactionMonitorRejectReason = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;
	
	/*************D1合影Job***********************/
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherImageIsDGroupPhoto")
	@ModelField(name = "D1合影审查_是否是合影", bindDomain = "engine.rule.domain.app.CheckWhetherImageIsDGroupPhotoForApp")
	private Integer checkWhetherImageIsDGroupPhoto = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherDGroupPhotoIsRecognizable")
	@ModelField(name = "D1合影审查_合影是否清晰可辨识", bindDomain = "engine.rule.domain.app.CheckWhetherDGroupPhotoIsRecognizableForApp")
	private Integer checkWhetherDGroupPhotoIsRecognizable = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherCustomerPhotoIsTheSamePerson")
	@ModelField(name = "D1合影审查_客户照片判断（是否为同一个人）", bindDomain = "engine.rule.domain.app.CheckWhetherCustomerPhotoIsTheSamePersonForApp")
	private Integer checkWhetherCustomerPhotoIsTheSamePerson = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CheckWhetherDPhotoIsTheSamePerson")
	@ModelField(name = "D1合影审查_D照片判断（是否为同一个人）", bindDomain = "engine.rule.domain.app.CheckWhetherDPhotoIsTheSamePersonForApp")
	private Integer checkWhetherDPhotoIsTheSamePerson = AppDerivativeVariableConsts.DefaultOptionVauleForJobQuestion;
	/********************************************/
	
	@ModelMethod(name = "(this)的App-客户电话审核_本人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckUserRiskRevealForApp")
    public boolean checkUserRiskRevealForAppContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkUserRiskReveal);
		return optionSet.contains(option);
    }
    
	@ModelMethod(name = "(this)的App-客户电话审核V3_申请人声音和背景音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckUserSoundForApp")
	public boolean checkUserSoundContains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkUserSound);
		return optionSet.contains(option);
	}
	
    @ModelMethod(name = "(this)的App-第一联系人电话审核V3_本人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckFirstContactRiskPromptResultForApp")
    public boolean checkFirstContactRiskPromptResultForAppContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkFirstContactRiskPromptResult);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第二联系人电话审核V3_本人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckSecondContactRiskPromptResultForApp")
    public boolean checkSecondContactRiskPromptResultForAppContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkSecondContactRiskPromptResult);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第三联系人电话审核V3_本人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckThirdContactRiskPromptResultForApp")
    public boolean checkThirdContactRiskPromptResultForPDLContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkThirdContactRiskPromptResult);
		return optionSet.contains(option);
    }

    @ModelMethod(name = "(this)的App-第一补充联系人电话审核V3_联系人提示申请人是否有风险是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactRiskPromptResult_11ForApp")
    public boolean checkAdditionalContactRiskPromptResult_11Contains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactRiskPromptResult_11);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第二补充联系人电话审核V3_联系人提示申请人是否有风险是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactRiskPromptResult_12ForApp")
    public boolean checkAdditionalContactRiskPromptResult_12Contains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactRiskPromptResult_12);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第三补充联系人电话审核V3_联系人提示申请人是否有风险是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactRiskPromptResult_13ForApp")
    public boolean checkAdditionalContactRiskPromptResult_13Contains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactRiskPromptResult_13);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第四补充联系人电话审核V3_联系人提示申请人是否有风险是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactRiskPromptResult_14ForApp")
    public boolean checkAdditionalContactRiskPromptResult_14Contains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactRiskPromptResult_17);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第五补充联系人电话审核V3_联系人提示申请人是否有风险是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactRiskPromptResult_15ForApp")
    public boolean checkAdditionalContactRiskPromptResult_15Contains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactRiskPromptResult_15);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第六补充联系人电话审核V3_联系人提示申请人是否有风险是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactRiskPromptResult_16ForApp")
    public boolean checkAdditionalContactRiskPromptResult_16Contains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactRiskPromptResult_16);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第七补充联系人电话审核V3_联系人提示申请人是否有风险是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactRiskPromptResult_17ForApp")
    public boolean checkAdditionalContactRiskPromptResult_17Contains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactRiskPromptResult_17);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-第八补充联系人电话审核V3_联系人提示申请人是否有风险是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactRiskPromptResult_18ForApp")
    public boolean checkAdditionalContactRiskPromptResult_18Contains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactRiskPromptResult_18);
		return optionSet.contains(option);
    }
    
    @ModelMethod(name = "(this)的App-公司电话审核V3_接听人风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckCompanyAnswerPersonRiskPromptResultForApp")
    public boolean checkCompanyAnswerPersonRiskPromptResultContains(int option)
    {
    	Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkCompanyAnswerPersonRiskPromptResult);
		return optionSet.contains(option);
    }
    
	@ModelMethod(name = "(this)的App-公司电话审核V3_电核过程交流中异常包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckCompanyAnswerPersonAnswerResultForApp")
	public boolean containsRecommendRole(int role) {
		Set<Integer> resultSet = CommonUtil
				.decomposeBinarySum(this.checkCompanyAnswerPersonAnswerResult);
		return resultSet.contains(role);
	}
	
	@ModelMethod(name = "(this)的App-商户电话审核V3_商户风险提示是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckMerchantRiskHintForApp")
	public boolean checkMerchantRiskHintContains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkMerchantRiskHint);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第一联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckFirstContactSoundJudgeResultForApp")
	public boolean checkFirstContactSoundJudgeResultContains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkFirstContactSoundJudgeResult);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第二联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckSecondContactSoundJudgeResultForApp")
	public boolean checkSecondContactSoundJudgeResultContains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkSecondContactSoundJudgeResult);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第三联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckThirdContactSoundJudgeResultForApp")
	public boolean checkThirdContactSoundJudgeResultContains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkThirdContactSoundJudgeResult);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第一补充联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactSoundJudgeResult_11ForApp")
	public boolean checkAdditionalContactSoundJudgeResult_11Contains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactSoundJudgeResult_11);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第二补充联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactSoundJudgeResult_12ForApp")
	public boolean checkAdditionalContactSoundJudgeResult_12Contains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactSoundJudgeResult_12);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第三补充联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactSoundJudgeResult_13ForApp")
	public boolean checkAdditionalContactSoundJudgeResult_13Contains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactSoundJudgeResult_13);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第四补充联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactSoundJudgeResult_14ForApp")
	public boolean checkAdditionalContactSoundJudgeResult_14Contains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactSoundJudgeResult_14);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第五补充联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactSoundJudgeResult_15ForApp")
	public boolean checkAdditionalContactSoundJudgeResult_15Contains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactSoundJudgeResult_15);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第六补充联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactSoundJudgeResult_16ForApp")
	public boolean checkAdditionalContactSoundJudgeResult_16Contains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactSoundJudgeResult_16);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第七补充联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactSoundJudgeResult_17ForApp")
	public boolean checkAdditionalContactSoundJudgeResult_17Contains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactSoundJudgeResult_17);
		return optionSet.contains(option);
	}
	
	@ModelMethod(name = "(this)的App-第八补充联系人电话审核V3_联系人声音与常识推断是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.CheckAdditionalContactSoundJudgeResult_18ForApp")
	public boolean checkAdditionalContactSoundJudgeResult_18Contains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.checkAdditionalContactSoundJudgeResult_18);
		return optionSet.contains(option);
	}

	@ModelMethod(name = "(this)的App-身份信息录入_其他证件类型是否包含(#1,<选项>)", paramDomains = "engine.rule.domain.InputIdCardInfoOtherCertTypeForApp")
	public boolean inputIdCardInfoOtherCertTypeContains(int option)
	{
		Set<Integer> optionSet = CommonUtil.decomposeBinarySum(this.inputIdCardInfoOtherCertType);
		return optionSet.contains(option);
	}
	
	public Integer getCheckFirstContactPhoneCallResult() {
		return checkFirstContactPhoneCallResult;
	}
	
	
	public void setCheckFirstContactPhoneCallResult(
			Integer checkFirstContactPhoneCallResult) {
		this.checkFirstContactPhoneCallResult = checkFirstContactPhoneCallResult;
	}
	
	
	public Integer getCheckFirstContactPhoneAnswererResult() {
		return checkFirstContactPhoneAnswererResult;
	}
	
	
	public void setCheckFirstContactPhoneAnswererResult(
			Integer checkFirstContactPhoneAnswererResult) {
		this.checkFirstContactPhoneAnswererResult = checkFirstContactPhoneAnswererResult;
	}
	
	
	public Integer getCheckFirstContactSceneIntroduceResult() {
		return checkFirstContactSceneIntroduceResult;
	}
	
	
	public void setCheckFirstContactSceneIntroduceResult(
			Integer checkFirstContactSceneIntroduceResult) {
		this.checkFirstContactSceneIntroduceResult = checkFirstContactSceneIntroduceResult;
	}
	
	
	public Integer getCheckFirstContactIdentificationResult() {
		return checkFirstContactIdentificationResult;
	}
	
	
	public void setCheckFirstContactIdentificationResult(
			Integer checkFirstContactIdentificationResult) {
		this.checkFirstContactIdentificationResult = checkFirstContactIdentificationResult;
	}
	
	
	public Integer getCheckFirstContactSoundJudgeResult() {
		return checkFirstContactSoundJudgeResult;
	}
	
	
	public void setCheckFirstContactSoundJudgeResult(
			Integer checkFirstContactSoundJudgeResult) {
		this.checkFirstContactSoundJudgeResult = checkFirstContactSoundJudgeResult;
	}
	
	
	public Integer getCheckFirstContactHereJudgeResult() {
		return checkFirstContactHereJudgeResult;
	}
	
	
	public void setCheckFirstContactHereJudgeResult(
			Integer checkFirstContactHereJudgeResult) {
		this.checkFirstContactHereJudgeResult = checkFirstContactHereJudgeResult;
	}
	
	
	public Integer getCheckFirstContactCareApplicantResult() {
		return checkFirstContactCareApplicantResult;
	}
	
	
	public void setCheckFirstContactCareApplicantResult(
			Integer checkFirstContactCareApplicantResult) {
		this.checkFirstContactCareApplicantResult = checkFirstContactCareApplicantResult;
	}
	
	
	public Integer getCheckFirstContactAttitudeOfPhoneResult() {
		return checkFirstContactAttitudeOfPhoneResult;
	}
	
	
	public void setCheckFirstContactAttitudeOfPhoneResult(
			Integer checkFirstContactAttitudeOfPhoneResult) {
		this.checkFirstContactAttitudeOfPhoneResult = checkFirstContactAttitudeOfPhoneResult;
	}
	
	
	public Integer getCheckSecondContactPhoneCallResult() {
		return checkSecondContactPhoneCallResult;
	}
	
	
	public void setCheckSecondContactPhoneCallResult(
			Integer checkSecondContactPhoneCallResult) {
		this.checkSecondContactPhoneCallResult = checkSecondContactPhoneCallResult;
	}
	
	
	public Integer getCheckSecondContactPhoneAnswererResult() {
		return checkSecondContactPhoneAnswererResult;
	}
	
	
	public void setCheckSecondContactPhoneAnswererResult(
			Integer checkSecondContactPhoneAnswererResult) {
		this.checkSecondContactPhoneAnswererResult = checkSecondContactPhoneAnswererResult;
	}
	
	
	public Integer getCheckSecondContactSceneIntroduceResult() {
		return checkSecondContactSceneIntroduceResult;
	}
	
	
	public void setCheckSecondContactSceneIntroduceResult(
			Integer checkSecondContactSceneIntroduceResult) {
		this.checkSecondContactSceneIntroduceResult = checkSecondContactSceneIntroduceResult;
	}
	
	
	public Integer getCheckSecondContactIdentificationResult() {
		return checkSecondContactIdentificationResult;
	}
	
	
	public void setCheckSecondContactIdentificationResult(
			Integer checkSecondContactIdentificationResult) {
		this.checkSecondContactIdentificationResult = checkSecondContactIdentificationResult;
	}
	
	
	public Integer getCheckSecondContactSoundJudgeResult() {
		return checkSecondContactSoundJudgeResult;
	}
	
	
	public void setCheckSecondContactSoundJudgeResult(
			Integer checkSecondContactSoundJudgeResult) {
		this.checkSecondContactSoundJudgeResult = checkSecondContactSoundJudgeResult;
	}
	
	
	public Integer getCheckSecondContactHereJudgeResult() {
		return checkSecondContactHereJudgeResult;
	}
	
	
	public void setCheckSecondContactHereJudgeResult(
			Integer checkSecondContactHereJudgeResult) {
		this.checkSecondContactHereJudgeResult = checkSecondContactHereJudgeResult;
	}
	
	
	public Integer getCheckSecondContactCareApplicantResult() {
		return checkSecondContactCareApplicantResult;
	}
	
	
	public void setCheckSecondContactCareApplicantResult(
			Integer checkSecondContactCareApplicantResult) {
		this.checkSecondContactCareApplicantResult = checkSecondContactCareApplicantResult;
	}
	
	
	public Integer getCheckSecondContactAttitudeOfPhoneResult() {
		return checkSecondContactAttitudeOfPhoneResult;
	}
	
	
	public void setCheckSecondContactAttitudeOfPhoneResult(
			Integer checkSecondContactAttitudeOfPhoneResult) {
		this.checkSecondContactAttitudeOfPhoneResult = checkSecondContactAttitudeOfPhoneResult;
	}
	
	
	public Integer getCheckThirdContactPhoneCallResult() {
		return checkThirdContactPhoneCallResult;
	}
	
	
	public void setCheckThirdContactPhoneCallResult(
			Integer checkThirdContactPhoneCallResult) {
		this.checkThirdContactPhoneCallResult = checkThirdContactPhoneCallResult;
	}
	
	
	public Integer getCheckThirdContactPhoneAnswererResult() {
		return checkThirdContactPhoneAnswererResult;
	}
	
	
	public void setCheckThirdContactPhoneAnswererResult(
			Integer checkThirdContactPhoneAnswererResult) {
		this.checkThirdContactPhoneAnswererResult = checkThirdContactPhoneAnswererResult;
	}
	
	
	public Integer getCheckThirdContactSceneIntroduceResult() {
		return checkThirdContactSceneIntroduceResult;
	}
	
	
	public void setCheckThirdContactSceneIntroduceResult(
			Integer checkThirdContactSceneIntroduceResult) {
		this.checkThirdContactSceneIntroduceResult = checkThirdContactSceneIntroduceResult;
	}
	
	
	public Integer getCheckThirdContactIdentificationResult() {
		return checkThirdContactIdentificationResult;
	}
	
	
	public void setCheckThirdContactIdentificationResult(
			Integer checkThirdContactIdentificationResult) {
		this.checkThirdContactIdentificationResult = checkThirdContactIdentificationResult;
	}
	
	
	public Integer getCheckThirdContactSoundJudgeResult() {
		return checkThirdContactSoundJudgeResult;
	}
	
	
	public void setCheckThirdContactSoundJudgeResult(
			Integer checkThirdContactSoundJudgeResult) {
		this.checkThirdContactSoundJudgeResult = checkThirdContactSoundJudgeResult;
	}
	
	
	public Integer getCheckThirdContactHereJudgeResult() {
		return checkThirdContactHereJudgeResult;
	}
	
	
	public void setCheckThirdContactHereJudgeResult(
			Integer checkThirdContactHereJudgeResult) {
		this.checkThirdContactHereJudgeResult = checkThirdContactHereJudgeResult;
	}
	
	
	public Integer getCheckThirdContactCareApplicantResult() {
		return checkThirdContactCareApplicantResult;
	}
	
	
	public void setCheckThirdContactCareApplicantResult(
			Integer checkThirdContactCareApplicantResult) {
		this.checkThirdContactCareApplicantResult = checkThirdContactCareApplicantResult;
	}
	
	
	public Integer getCheckThirdContactAttitudeOfPhoneResult() {
		return checkThirdContactAttitudeOfPhoneResult;
	}
	
	
	public void setCheckThirdContactAttitudeOfPhoneResult(
			Integer checkThirdContactAttitudeOfPhoneResult) {
		this.checkThirdContactAttitudeOfPhoneResult = checkThirdContactAttitudeOfPhoneResult;
	}
	
	
	public Integer getCheckAdditionalContactPhoneCallResult_11() {
		return checkAdditionalContactPhoneCallResult_11;
	}
	
	
	public void setCheckAdditionalContactPhoneCallResult_11(
			Integer checkAdditionalContactPhoneCallResult_11) {
		this.checkAdditionalContactPhoneCallResult_11 = checkAdditionalContactPhoneCallResult_11;
	}
	
	
	public Integer getCheckAdditionalContactPhoneAnswererResult_11() {
		return checkAdditionalContactPhoneAnswererResult_11;
	}
	
	
	public void setCheckAdditionalContactPhoneAnswererResult_11(
			Integer checkAdditionalContactPhoneAnswererResult_11) {
		this.checkAdditionalContactPhoneAnswererResult_11 = checkAdditionalContactPhoneAnswererResult_11;
	}
	
	
	public Integer getCheckAdditionalContactSceneIntroduceResult_11() {
		return checkAdditionalContactSceneIntroduceResult_11;
	}
	
	
	public void setCheckAdditionalContactSceneIntroduceResult_11(
			Integer checkAdditionalContactSceneIntroduceResult_11) {
		this.checkAdditionalContactSceneIntroduceResult_11 = checkAdditionalContactSceneIntroduceResult_11;
	}
	
	
	public Integer getCheckAdditionalContactIdentificationResult_11() {
		return checkAdditionalContactIdentificationResult_11;
	}
	
	
	public void setCheckAdditionalContactIdentificationResult_11(
			Integer checkAdditionalContactIdentificationResult_11) {
		this.checkAdditionalContactIdentificationResult_11 = checkAdditionalContactIdentificationResult_11;
	}
	
	
	public Integer getCheckAdditionalContactSoundJudgeResult_11() {
		return checkAdditionalContactSoundJudgeResult_11;
	}
	
	
	public void setCheckAdditionalContactSoundJudgeResult_11(
			Integer checkAdditionalContactSoundJudgeResult_11) {
		this.checkAdditionalContactSoundJudgeResult_11 = checkAdditionalContactSoundJudgeResult_11;
	}
	
	
	public Integer getCheckAdditionalContactHereJudgeResult_11() {
		return checkAdditionalContactHereJudgeResult_11;
	}
	
	
	public void setCheckAdditionalContactHereJudgeResult_11(
			Integer checkAdditionalContactHereJudgeResult_11) {
		this.checkAdditionalContactHereJudgeResult_11 = checkAdditionalContactHereJudgeResult_11;
	}
	
	
	public Integer getCheckAdditionalContactCareApplicantResult_11() {
		return checkAdditionalContactCareApplicantResult_11;
	}
	
	
	public void setCheckAdditionalContactCareApplicantResult_11(
			Integer checkAdditionalContactCareApplicantResult_11) {
		this.checkAdditionalContactCareApplicantResult_11 = checkAdditionalContactCareApplicantResult_11;
	}
	
	
	public Integer getCheckAdditionalContactAttitudeOfPhoneResult_11() {
		return checkAdditionalContactAttitudeOfPhoneResult_11;
	}
	
	
	public void setCheckAdditionalContactAttitudeOfPhoneResult_11(
			Integer checkAdditionalContactAttitudeOfPhoneResult_11) {
		this.checkAdditionalContactAttitudeOfPhoneResult_11 = checkAdditionalContactAttitudeOfPhoneResult_11;
	}
	
	
	public Integer getCheckAdditionalContactPhoneCallResult_12() {
		return checkAdditionalContactPhoneCallResult_12;
	}
	
	
	public void setCheckAdditionalContactPhoneCallResult_12(
			Integer checkAdditionalContactPhoneCallResult_12) {
		this.checkAdditionalContactPhoneCallResult_12 = checkAdditionalContactPhoneCallResult_12;
	}
	
	
	public Integer getCheckAdditionalContactPhoneAnswererResult_12() {
		return checkAdditionalContactPhoneAnswererResult_12;
	}
	
	
	public void setCheckAdditionalContactPhoneAnswererResult_12(
			Integer checkAdditionalContactPhoneAnswererResult_12) {
		this.checkAdditionalContactPhoneAnswererResult_12 = checkAdditionalContactPhoneAnswererResult_12;
	}
	
	
	public Integer getCheckAdditionalContactSceneIntroduceResult_12() {
		return checkAdditionalContactSceneIntroduceResult_12;
	}
	
	
	public void setCheckAdditionalContactSceneIntroduceResult_12(
			Integer checkAdditionalContactSceneIntroduceResult_12) {
		this.checkAdditionalContactSceneIntroduceResult_12 = checkAdditionalContactSceneIntroduceResult_12;
	}
	
	
	public Integer getCheckAdditionalContactIdentificationResult_12() {
		return checkAdditionalContactIdentificationResult_12;
	}
	
	
	public void setCheckAdditionalContactIdentificationResult_12(
			Integer checkAdditionalContactIdentificationResult_12) {
		this.checkAdditionalContactIdentificationResult_12 = checkAdditionalContactIdentificationResult_12;
	}
	
	
	public Integer getCheckAdditionalContactSoundJudgeResult_12() {
		return checkAdditionalContactSoundJudgeResult_12;
	}
	
	
	public void setCheckAdditionalContactSoundJudgeResult_12(
			Integer checkAdditionalContactSoundJudgeResult_12) {
		this.checkAdditionalContactSoundJudgeResult_12 = checkAdditionalContactSoundJudgeResult_12;
	}
	
	
	public Integer getCheckAdditionalContactHereJudgeResult_12() {
		return checkAdditionalContactHereJudgeResult_12;
	}
	
	
	public void setCheckAdditionalContactHereJudgeResult_12(
			Integer checkAdditionalContactHereJudgeResult_12) {
		this.checkAdditionalContactHereJudgeResult_12 = checkAdditionalContactHereJudgeResult_12;
	}
	
	
	public Integer getCheckAdditionalContactCareApplicantResult_12() {
		return checkAdditionalContactCareApplicantResult_12;
	}
	
	
	public void setCheckAdditionalContactCareApplicantResult_12(
			Integer checkAdditionalContactCareApplicantResult_12) {
		this.checkAdditionalContactCareApplicantResult_12 = checkAdditionalContactCareApplicantResult_12;
	}
	
	
	public Integer getCheckAdditionalContactAttitudeOfPhoneResult_12() {
		return checkAdditionalContactAttitudeOfPhoneResult_12;
	}
	
	
	public void setCheckAdditionalContactAttitudeOfPhoneResult_12(
			Integer checkAdditionalContactAttitudeOfPhoneResult_12) {
		this.checkAdditionalContactAttitudeOfPhoneResult_12 = checkAdditionalContactAttitudeOfPhoneResult_12;
	}
	
	
	public Integer getCheckAdditionalContactPhoneCallResult_13() {
		return checkAdditionalContactPhoneCallResult_13;
	}
	
	
	public void setCheckAdditionalContactPhoneCallResult_13(
			Integer checkAdditionalContactPhoneCallResult_13) {
		this.checkAdditionalContactPhoneCallResult_13 = checkAdditionalContactPhoneCallResult_13;
	}
	
	
	public Integer getCheckAdditionalContactPhoneAnswererResult_13() {
		return checkAdditionalContactPhoneAnswererResult_13;
	}
	
	
	public void setCheckAdditionalContactPhoneAnswererResult_13(
			Integer checkAdditionalContactPhoneAnswererResult_13) {
		this.checkAdditionalContactPhoneAnswererResult_13 = checkAdditionalContactPhoneAnswererResult_13;
	}
	
	
	public Integer getCheckAdditionalContactSceneIntroduceResult_13() {
		return checkAdditionalContactSceneIntroduceResult_13;
	}
	
	
	public void setCheckAdditionalContactSceneIntroduceResult_13(
			Integer checkAdditionalContactSceneIntroduceResult_13) {
		this.checkAdditionalContactSceneIntroduceResult_13 = checkAdditionalContactSceneIntroduceResult_13;
	}
	
	
	public Integer getCheckAdditionalContactIdentificationResult_13() {
		return checkAdditionalContactIdentificationResult_13;
	}
	
	
	public void setCheckAdditionalContactIdentificationResult_13(
			Integer checkAdditionalContactIdentificationResult_13) {
		this.checkAdditionalContactIdentificationResult_13 = checkAdditionalContactIdentificationResult_13;
	}
	
	
	public Integer getCheckAdditionalContactSoundJudgeResult_13() {
		return checkAdditionalContactSoundJudgeResult_13;
	}
	
	
	public void setCheckAdditionalContactSoundJudgeResult_13(
			Integer checkAdditionalContactSoundJudgeResult_13) {
		this.checkAdditionalContactSoundJudgeResult_13 = checkAdditionalContactSoundJudgeResult_13;
	}
	
	
	public Integer getCheckAdditionalContactHereJudgeResult_13() {
		return checkAdditionalContactHereJudgeResult_13;
	}
	
	
	public void setCheckAdditionalContactHereJudgeResult_13(
			Integer checkAdditionalContactHereJudgeResult_13) {
		this.checkAdditionalContactHereJudgeResult_13 = checkAdditionalContactHereJudgeResult_13;
	}
	
	
	public Integer getCheckAdditionalContactCareApplicantResult_13() {
		return checkAdditionalContactCareApplicantResult_13;
	}
	
	
	public void setCheckAdditionalContactCareApplicantResult_13(
			Integer checkAdditionalContactCareApplicantResult_13) {
		this.checkAdditionalContactCareApplicantResult_13 = checkAdditionalContactCareApplicantResult_13;
	}
	
	
	public Integer getCheckAdditionalContactAttitudeOfPhoneResult_13() {
		return checkAdditionalContactAttitudeOfPhoneResult_13;
	}
	
	
	public void setCheckAdditionalContactAttitudeOfPhoneResult_13(
			Integer checkAdditionalContactAttitudeOfPhoneResult_13) {
		this.checkAdditionalContactAttitudeOfPhoneResult_13 = checkAdditionalContactAttitudeOfPhoneResult_13;
	}
	
	
	public Integer getCheckAdditionalContactPhoneCallResult_14() {
		return checkAdditionalContactPhoneCallResult_14;
	}
	
	
	public void setCheckAdditionalContactPhoneCallResult_14(
			Integer checkAdditionalContactPhoneCallResult_14) {
		this.checkAdditionalContactPhoneCallResult_14 = checkAdditionalContactPhoneCallResult_14;
	}
	
	
	public Integer getCheckAdditionalContactPhoneAnswererResult_14() {
		return checkAdditionalContactPhoneAnswererResult_14;
	}
	
	
	public void setCheckAdditionalContactPhoneAnswererResult_14(
			Integer checkAdditionalContactPhoneAnswererResult_14) {
		this.checkAdditionalContactPhoneAnswererResult_14 = checkAdditionalContactPhoneAnswererResult_14;
	}
	
	
	public Integer getCheckAdditionalContactSceneIntroduceResult_14() {
		return checkAdditionalContactSceneIntroduceResult_14;
	}
	
	
	public void setCheckAdditionalContactSceneIntroduceResult_14(
			Integer checkAdditionalContactSceneIntroduceResult_14) {
		this.checkAdditionalContactSceneIntroduceResult_14 = checkAdditionalContactSceneIntroduceResult_14;
	}
	
	
	public Integer getCheckAdditionalContactIdentificationResult_14() {
		return checkAdditionalContactIdentificationResult_14;
	}
	
	
	public void setCheckAdditionalContactIdentificationResult_14(
			Integer checkAdditionalContactIdentificationResult_14) {
		this.checkAdditionalContactIdentificationResult_14 = checkAdditionalContactIdentificationResult_14;
	}
	
	
	public Integer getCheckAdditionalContactSoundJudgeResult_14() {
		return checkAdditionalContactSoundJudgeResult_14;
	}
	
	
	public void setCheckAdditionalContactSoundJudgeResult_14(
			Integer checkAdditionalContactSoundJudgeResult_14) {
		this.checkAdditionalContactSoundJudgeResult_14 = checkAdditionalContactSoundJudgeResult_14;
	}
	
	
	public Integer getCheckAdditionalContactHereJudgeResult_14() {
		return checkAdditionalContactHereJudgeResult_14;
	}
	
	
	public void setCheckAdditionalContactHereJudgeResult_14(
			Integer checkAdditionalContactHereJudgeResult_14) {
		this.checkAdditionalContactHereJudgeResult_14 = checkAdditionalContactHereJudgeResult_14;
	}
	
	
	public Integer getCheckAdditionalContactCareApplicantResult_14() {
		return checkAdditionalContactCareApplicantResult_14;
	}
	
	
	public void setCheckAdditionalContactCareApplicantResult_14(
			Integer checkAdditionalContactCareApplicantResult_14) {
		this.checkAdditionalContactCareApplicantResult_14 = checkAdditionalContactCareApplicantResult_14;
	}
	
	
	public Integer getCheckAdditionalContactAttitudeOfPhoneResult_14() {
		return checkAdditionalContactAttitudeOfPhoneResult_14;
	}
	
	
	public void setCheckAdditionalContactAttitudeOfPhoneResult_14(
			Integer checkAdditionalContactAttitudeOfPhoneResult_14) {
		this.checkAdditionalContactAttitudeOfPhoneResult_14 = checkAdditionalContactAttitudeOfPhoneResult_14;
	}
	
	
	public Integer getCheckAdditionalContactPhoneCallResult_15() {
		return checkAdditionalContactPhoneCallResult_15;
	}
	
	
	public void setCheckAdditionalContactPhoneCallResult_15(
			Integer checkAdditionalContactPhoneCallResult_15) {
		this.checkAdditionalContactPhoneCallResult_15 = checkAdditionalContactPhoneCallResult_15;
	}
	
	
	public Integer getCheckAdditionalContactPhoneAnswererResult_15() {
		return checkAdditionalContactPhoneAnswererResult_15;
	}
	
	
	public void setCheckAdditionalContactPhoneAnswererResult_15(
			Integer checkAdditionalContactPhoneAnswererResult_15) {
		this.checkAdditionalContactPhoneAnswererResult_15 = checkAdditionalContactPhoneAnswererResult_15;
	}
	
	
	public Integer getCheckAdditionalContactSceneIntroduceResult_15() {
		return checkAdditionalContactSceneIntroduceResult_15;
	}
	
	
	public void setCheckAdditionalContactSceneIntroduceResult_15(
			Integer checkAdditionalContactSceneIntroduceResult_15) {
		this.checkAdditionalContactSceneIntroduceResult_15 = checkAdditionalContactSceneIntroduceResult_15;
	}
	
	
	public Integer getCheckAdditionalContactIdentificationResult_15() {
		return checkAdditionalContactIdentificationResult_15;
	}
	
	
	public void setCheckAdditionalContactIdentificationResult_15(
			Integer checkAdditionalContactIdentificationResult_15) {
		this.checkAdditionalContactIdentificationResult_15 = checkAdditionalContactIdentificationResult_15;
	}
	
	
	public Integer getCheckAdditionalContactSoundJudgeResult_15() {
		return checkAdditionalContactSoundJudgeResult_15;
	}
	
	
	public void setCheckAdditionalContactSoundJudgeResult_15(
			Integer checkAdditionalContactSoundJudgeResult_15) {
		this.checkAdditionalContactSoundJudgeResult_15 = checkAdditionalContactSoundJudgeResult_15;
	}
	
	
	public Integer getCheckAdditionalContactHereJudgeResult_15() {
		return checkAdditionalContactHereJudgeResult_15;
	}
	
	
	public void setCheckAdditionalContactHereJudgeResult_15(
			Integer checkAdditionalContactHereJudgeResult_15) {
		this.checkAdditionalContactHereJudgeResult_15 = checkAdditionalContactHereJudgeResult_15;
	}
	
	
	public Integer getCheckAdditionalContactCareApplicantResult_15() {
		return checkAdditionalContactCareApplicantResult_15;
	}
	
	
	public void setCheckAdditionalContactCareApplicantResult_15(
			Integer checkAdditionalContactCareApplicantResult_15) {
		this.checkAdditionalContactCareApplicantResult_15 = checkAdditionalContactCareApplicantResult_15;
	}
	
	
	public Integer getCheckAdditionalContactAttitudeOfPhoneResult_15() {
		return checkAdditionalContactAttitudeOfPhoneResult_15;
	}
	
	
	public void setCheckAdditionalContactAttitudeOfPhoneResult_15(
			Integer checkAdditionalContactAttitudeOfPhoneResult_15) {
		this.checkAdditionalContactAttitudeOfPhoneResult_15 = checkAdditionalContactAttitudeOfPhoneResult_15;
	}
	
	
	public Integer getCheckAdditionalContactPhoneCallResult_16() {
		return checkAdditionalContactPhoneCallResult_16;
	}
	
	
	public void setCheckAdditionalContactPhoneCallResult_16(
			Integer checkAdditionalContactPhoneCallResult_16) {
		this.checkAdditionalContactPhoneCallResult_16 = checkAdditionalContactPhoneCallResult_16;
	}
	
	
	public Integer getCheckAdditionalContactPhoneAnswererResult_16() {
		return checkAdditionalContactPhoneAnswererResult_16;
	}
	
	
	public void setCheckAdditionalContactPhoneAnswererResult_16(
			Integer checkAdditionalContactPhoneAnswererResult_16) {
		this.checkAdditionalContactPhoneAnswererResult_16 = checkAdditionalContactPhoneAnswererResult_16;
	}
	
	
	public Integer getCheckAdditionalContactSceneIntroduceResult_16() {
		return checkAdditionalContactSceneIntroduceResult_16;
	}
	
	
	public void setCheckAdditionalContactSceneIntroduceResult_16(
			Integer checkAdditionalContactSceneIntroduceResult_16) {
		this.checkAdditionalContactSceneIntroduceResult_16 = checkAdditionalContactSceneIntroduceResult_16;
	}
	
	
	public Integer getCheckAdditionalContactIdentificationResult_16() {
		return checkAdditionalContactIdentificationResult_16;
	}
	
	
	public void setCheckAdditionalContactIdentificationResult_16(
			Integer checkAdditionalContactIdentificationResult_16) {
		this.checkAdditionalContactIdentificationResult_16 = checkAdditionalContactIdentificationResult_16;
	}
	
	
	public Integer getCheckAdditionalContactSoundJudgeResult_16() {
		return checkAdditionalContactSoundJudgeResult_16;
	}
	
	
	public void setCheckAdditionalContactSoundJudgeResult_16(
			Integer checkAdditionalContactSoundJudgeResult_16) {
		this.checkAdditionalContactSoundJudgeResult_16 = checkAdditionalContactSoundJudgeResult_16;
	}
	
	
	public Integer getCheckAdditionalContactHereJudgeResult_16() {
		return checkAdditionalContactHereJudgeResult_16;
	}
	
	
	public void setCheckAdditionalContactHereJudgeResult_16(
			Integer checkAdditionalContactHereJudgeResult_16) {
		this.checkAdditionalContactHereJudgeResult_16 = checkAdditionalContactHereJudgeResult_16;
	}
	
	
	public Integer getCheckAdditionalContactCareApplicantResult_16() {
		return checkAdditionalContactCareApplicantResult_16;
	}
	
	
	public void setCheckAdditionalContactCareApplicantResult_16(
			Integer checkAdditionalContactCareApplicantResult_16) {
		this.checkAdditionalContactCareApplicantResult_16 = checkAdditionalContactCareApplicantResult_16;
	}
	
	
	public Integer getCheckAdditionalContactAttitudeOfPhoneResult_16() {
		return checkAdditionalContactAttitudeOfPhoneResult_16;
	}
	
	
	public void setCheckAdditionalContactAttitudeOfPhoneResult_16(
			Integer checkAdditionalContactAttitudeOfPhoneResult_16) {
		this.checkAdditionalContactAttitudeOfPhoneResult_16 = checkAdditionalContactAttitudeOfPhoneResult_16;
	}
	
	
	public Integer getCheckAdditionalContactPhoneCallResult_17() {
		return checkAdditionalContactPhoneCallResult_17;
	}
	
	
	public void setCheckAdditionalContactPhoneCallResult_17(
			Integer checkAdditionalContactPhoneCallResult_17) {
		this.checkAdditionalContactPhoneCallResult_17 = checkAdditionalContactPhoneCallResult_17;
	}
	
	
	public Integer getCheckAdditionalContactPhoneAnswererResult_17() {
		return checkAdditionalContactPhoneAnswererResult_17;
	}
	
	
	public void setCheckAdditionalContactPhoneAnswererResult_17(
			Integer checkAdditionalContactPhoneAnswererResult_17) {
		this.checkAdditionalContactPhoneAnswererResult_17 = checkAdditionalContactPhoneAnswererResult_17;
	}
	
	
	public Integer getCheckAdditionalContactSceneIntroduceResult_17() {
		return checkAdditionalContactSceneIntroduceResult_17;
	}
	
	
	public void setCheckAdditionalContactSceneIntroduceResult_17(
			Integer checkAdditionalContactSceneIntroduceResult_17) {
		this.checkAdditionalContactSceneIntroduceResult_17 = checkAdditionalContactSceneIntroduceResult_17;
	}
	
	
	public Integer getCheckAdditionalContactIdentificationResult_17() {
		return checkAdditionalContactIdentificationResult_17;
	}
	
	
	public void setCheckAdditionalContactIdentificationResult_17(
			Integer checkAdditionalContactIdentificationResult_17) {
		this.checkAdditionalContactIdentificationResult_17 = checkAdditionalContactIdentificationResult_17;
	}
	
	
	public Integer getCheckAdditionalContactSoundJudgeResult_17() {
		return checkAdditionalContactSoundJudgeResult_17;
	}
	
	
	public void setCheckAdditionalContactSoundJudgeResult_17(
			Integer checkAdditionalContactSoundJudgeResult_17) {
		this.checkAdditionalContactSoundJudgeResult_17 = checkAdditionalContactSoundJudgeResult_17;
	}
	
	
	public Integer getCheckAdditionalContactHereJudgeResult_17() {
		return checkAdditionalContactHereJudgeResult_17;
	}
	
	
	public void setCheckAdditionalContactHereJudgeResult_17(
			Integer checkAdditionalContactHereJudgeResult_17) {
		this.checkAdditionalContactHereJudgeResult_17 = checkAdditionalContactHereJudgeResult_17;
	}
	
	
	public Integer getCheckAdditionalContactCareApplicantResult_17() {
		return checkAdditionalContactCareApplicantResult_17;
	}
	
	
	public void setCheckAdditionalContactCareApplicantResult_17(
			Integer checkAdditionalContactCareApplicantResult_17) {
		this.checkAdditionalContactCareApplicantResult_17 = checkAdditionalContactCareApplicantResult_17;
	}
	
	
	public Integer getCheckAdditionalContactAttitudeOfPhoneResult_17() {
		return checkAdditionalContactAttitudeOfPhoneResult_17;
	}
	
	
	public void setCheckAdditionalContactAttitudeOfPhoneResult_17(
			Integer checkAdditionalContactAttitudeOfPhoneResult_17) {
		this.checkAdditionalContactAttitudeOfPhoneResult_17 = checkAdditionalContactAttitudeOfPhoneResult_17;
	}
	
	
	public Integer getCheckAdditionalContactPhoneCallResult_18() {
		return checkAdditionalContactPhoneCallResult_18;
	}
	
	
	public void setCheckAdditionalContactPhoneCallResult_18(
			Integer checkAdditionalContactPhoneCallResult_18) {
		this.checkAdditionalContactPhoneCallResult_18 = checkAdditionalContactPhoneCallResult_18;
	}
	
	
	public Integer getCheckAdditionalContactPhoneAnswererResult_18() {
		return checkAdditionalContactPhoneAnswererResult_18;
	}
	
	
	public void setCheckAdditionalContactPhoneAnswererResult_18(
			Integer checkAdditionalContactPhoneAnswererResult_18) {
		this.checkAdditionalContactPhoneAnswererResult_18 = checkAdditionalContactPhoneAnswererResult_18;
	}
	
	
	public Integer getCheckAdditionalContactSceneIntroduceResult_18() {
		return checkAdditionalContactSceneIntroduceResult_18;
	}
	
	
	public void setCheckAdditionalContactSceneIntroduceResult_18(
			Integer checkAdditionalContactSceneIntroduceResult_18) {
		this.checkAdditionalContactSceneIntroduceResult_18 = checkAdditionalContactSceneIntroduceResult_18;
	}
	
	
	public Integer getCheckAdditionalContactIdentificationResult_18() {
		return checkAdditionalContactIdentificationResult_18;
	}
	
	
	public void setCheckAdditionalContactIdentificationResult_18(
			Integer checkAdditionalContactIdentificationResult_18) {
		this.checkAdditionalContactIdentificationResult_18 = checkAdditionalContactIdentificationResult_18;
	}
	
	
	public Integer getCheckAdditionalContactSoundJudgeResult_18() {
		return checkAdditionalContactSoundJudgeResult_18;
	}
	
	
	public void setCheckAdditionalContactSoundJudgeResult_18(
			Integer checkAdditionalContactSoundJudgeResult_18) {
		this.checkAdditionalContactSoundJudgeResult_18 = checkAdditionalContactSoundJudgeResult_18;
	}
	
	
	public Integer getCheckAdditionalContactHereJudgeResult_18() {
		return checkAdditionalContactHereJudgeResult_18;
	}
	
	
	public void setCheckAdditionalContactHereJudgeResult_18(
			Integer checkAdditionalContactHereJudgeResult_18) {
		this.checkAdditionalContactHereJudgeResult_18 = checkAdditionalContactHereJudgeResult_18;
	}
	
	
	public Integer getCheckAdditionalContactCareApplicantResult_18() {
		return checkAdditionalContactCareApplicantResult_18;
	}
	
	
	public void setCheckAdditionalContactCareApplicantResult_18(
			Integer checkAdditionalContactCareApplicantResult_18) {
		this.checkAdditionalContactCareApplicantResult_18 = checkAdditionalContactCareApplicantResult_18;
	}
	
	
	public Integer getCheckAdditionalContactAttitudeOfPhoneResult_18() {
		return checkAdditionalContactAttitudeOfPhoneResult_18;
	}
	
	
	public void setCheckAdditionalContactAttitudeOfPhoneResult_18(
			Integer checkAdditionalContactAttitudeOfPhoneResult_18) {
		this.checkAdditionalContactAttitudeOfPhoneResult_18 = checkAdditionalContactAttitudeOfPhoneResult_18;
	}
	
	
	public Integer getCheckCompanyPhoneCallResult() {
		return checkCompanyPhoneCallResult;
	}
	
	
	public void setCheckCompanyPhoneCallResult(Integer checkCompanyPhoneCallResult) {
		this.checkCompanyPhoneCallResult = checkCompanyPhoneCallResult;
	}
	
	
	public Integer getCheckCompanyIdentificationResult() {
		return checkCompanyIdentificationResult;
	}
	
	
	public void setCheckCompanyIdentificationResult(
			Integer checkCompanyIdentificationResult) {
		this.checkCompanyIdentificationResult = checkCompanyIdentificationResult;
	}
	
	
	public Integer getCheckCompanyPhoneRelationshipResult() {
		return checkCompanyPhoneRelationshipResult;
	}
	
	
	public void setCheckCompanyPhoneRelationshipResult(
			Integer checkCompanyPhoneRelationshipResult) {
		this.checkCompanyPhoneRelationshipResult = checkCompanyPhoneRelationshipResult;
	}
	
	
	public Integer getCheckCompanyApplicantCheckResult() {
		return checkCompanyApplicantCheckResult;
	}
	
	
	public void setCheckCompanyApplicantCheckResult(
			Integer checkCompanyApplicantCheckResult) {
		this.checkCompanyApplicantCheckResult = checkCompanyApplicantCheckResult;
	}
	
	
	public Integer getCheckCompanyAddressConsistency() {
		return checkCompanyAddressConsistency;
	}
	
	
	public void setCheckCompanyAddressConsistency(
			Integer checkCompanyAddressConsistency) {
		this.checkCompanyAddressConsistency = checkCompanyAddressConsistency;
	}
	
	
	public Integer getCheckCompanyAnswerPersonRiskPromptResult() {
		return checkCompanyAnswerPersonRiskPromptResult;
	}
	
	
	public void setCheckCompanyAnswerPersonRiskPromptResult(
			Integer checkCompanyAnswerPersonRiskPromptResult) {
		this.checkCompanyAnswerPersonRiskPromptResult = checkCompanyAnswerPersonRiskPromptResult;
	}
	
	
	public Integer getCheckCompanyAnswerPersonAnswerResult() {
		return checkCompanyAnswerPersonAnswerResult;
	}
	
	
	public void setCheckCompanyAnswerPersonAnswerResult(
			Integer checkCompanyAnswerPersonAnswerResult) {
		this.checkCompanyAnswerPersonAnswerResult = checkCompanyAnswerPersonAnswerResult;
	}
	
	
	public Integer getInputIdCardInfoIsOtherCertProvided() {
		return inputIdCardInfoIsOtherCertProvided;
	}
	
	
	public void setInputIdCardInfoIsOtherCertProvided(
			Integer inputIdCardInfoIsOtherCertProvided) {
		this.inputIdCardInfoIsOtherCertProvided = inputIdCardInfoIsOtherCertProvided;
	}
	
	
	public Integer getInputIdCardInfoOtherCertType() {
		return inputIdCardInfoOtherCertType;
	}
	
	
	public void setInputIdCardInfoOtherCertType(Integer inputIdCardInfoOtherCertType) {
		this.inputIdCardInfoOtherCertType = inputIdCardInfoOtherCertType;
	}
	
	public Integer getCheckUserPhoneCallResult() {
		return checkUserPhoneCallResult;
	}
	
	
	public void setCheckUserPhoneCallResult(Integer checkUserPhoneCallResult) {
		this.checkUserPhoneCallResult = checkUserPhoneCallResult;
	}
	
	
	public Integer getCheckUserPhoneAnswererResult() {
		return checkUserPhoneAnswererResult;
	}
	
	
	public void setCheckUserPhoneAnswererResult(Integer checkUserPhoneAnswererResult) {
		this.checkUserPhoneAnswererResult = checkUserPhoneAnswererResult;
	}
	
	
	public Integer getCheckUserSceneIntroduceResult() {
		return checkUserSceneIntroduceResult;
	}
	
	
	public void setCheckUserSceneIntroduceResult(
			Integer checkUserSceneIntroduceResult) {
		this.checkUserSceneIntroduceResult = checkUserSceneIntroduceResult;
	}
	
	
	public Integer getCheckUserIdentification() {
		return checkUserIdentification;
	}
	
	
	public void setCheckUserIdentification(Integer checkUserIdentification) {
		this.checkUserIdentification = checkUserIdentification;
	}
	
	
	public Integer getCheckUserSymbolicAnimal() {
		return checkUserSymbolicAnimal;
	}
	
	
	public void setCheckUserSymbolicAnimal(Integer checkUserSymbolicAnimal) {
		this.checkUserSymbolicAnimal = checkUserSymbolicAnimal;
	}
	
	
	public Integer getCheckUserCompanyName() {
		return checkUserCompanyName;
	}
	
	
	public void setCheckUserCompanyName(Integer checkUserCompanyName) {
		this.checkUserCompanyName = checkUserCompanyName;
	}
	
	
	public Integer getCheckUserIDCardAddress() {
		return checkUserIDCardAddress;
	}
	
	
	public void setCheckUserIDCardAddress(Integer checkUserIDCardAddress) {
		this.checkUserIDCardAddress = checkUserIDCardAddress;
	}
	
	
	public Integer getCheckUserInstalmentNumberAndValue() {
		return checkUserInstalmentNumberAndValue;
	}
	
	
	public void setCheckUserInstalmentNumberAndValue(
			Integer checkUserInstalmentNumberAndValue) {
		this.checkUserInstalmentNumberAndValue = checkUserInstalmentNumberAndValue;
	}
	
	
	public Integer getCheckUserJXLInforCrawlStatus() {
		return checkUserJXLInforCrawlStatus;
	}
	
	
	public void setCheckUserJXLInforCrawlStatus(Integer checkUserJXLInforCrawlStatus) {
		this.checkUserJXLInforCrawlStatus = checkUserJXLInforCrawlStatus;
	}
	
	
	public Integer getCheckUserConsequenceIntroduceResult() {
		return checkUserConsequenceIntroduceResult;
	}
	
	
	public void setCheckUserConsequenceIntroduceResult(
			Integer checkUserConsequenceIntroduceResult) {
		this.checkUserConsequenceIntroduceResult = checkUserConsequenceIntroduceResult;
	}
	
	
	public Integer getCheckUserRiskReveal() {
		return checkUserRiskReveal;
	}
	
	
	public void setCheckUserRiskReveal(Integer checkUserRiskReveal) {
		this.checkUserRiskReveal = checkUserRiskReveal;
	}
	
	
	public Integer getCheckUserSound() {
		return checkUserSound;
	}
	
	
	public void setCheckUserSound(Integer checkUserSound) {
		this.checkUserSound = checkUserSound;
	}
	
	
	public Integer getCheckUserCancelApplicationReason() {
		return checkUserCancelApplicationReason;
	}
	
	
	public void setCheckUserCancelApplicationReason(
			Integer checkUserCancelApplicationReason) {
		this.checkUserCancelApplicationReason = checkUserCancelApplicationReason;
	}
	
	
	public Integer getCheckUserCarenessLevel() {
		return checkUserCarenessLevel;
	}
	
	
	public void setCheckUserCarenessLevel(Integer checkUserCarenessLevel) {
		this.checkUserCarenessLevel = checkUserCarenessLevel;
	}
	
	
	public Integer getCheckUserDislikeLevel() {
		return checkUserDislikeLevel;
	}
	
	
	public void setCheckUserDislikeLevel(Integer checkUserDislikeLevel) {
		this.checkUserDislikeLevel = checkUserDislikeLevel;
	}
	
	
	public Integer getCheckMerchantPhoneCallResult() {
		return checkMerchantPhoneCallResult;
	}
	
	
	public void setCheckMerchantPhoneCallResult(Integer checkMerchantPhoneCallResult) {
		this.checkMerchantPhoneCallResult = checkMerchantPhoneCallResult;
	}
	
	
	public Integer getCheckMerchantReplacedBySales() {
		return checkMerchantReplacedBySales;
	}
	
	
	public void setCheckMerchantReplacedBySales(Integer checkMerchantReplacedBySales) {
		this.checkMerchantReplacedBySales = checkMerchantReplacedBySales;
	}
	
	
	public Integer getCheckMerchantAnswererResult() {
		return checkMerchantAnswererResult;
	}
	
	
	public void setCheckMerchantAnswererResult(Integer checkMerchantAnswererResult) {
		this.checkMerchantAnswererResult = checkMerchantAnswererResult;
	}
	
	
	public Integer getCheckMerchantStoreNameConsistent() {
		return checkMerchantStoreNameConsistent;
	}
	
	
	public void setCheckMerchantStoreNameConsistent(
			Integer checkMerchantStoreNameConsistent) {
		this.checkMerchantStoreNameConsistent = checkMerchantStoreNameConsistent;
	}
	
	
	public Integer getCheckMerchantCustomerNameConsistent() {
		return checkMerchantCustomerNameConsistent;
	}
	
	
	public void setCheckMerchantCustomerNameConsistent(
			Integer checkMerchantCustomerNameConsistent) {
		this.checkMerchantCustomerNameConsistent = checkMerchantCustomerNameConsistent;
	}
	
	
	public Integer getCheckMerchantCustomerOnSpot() {
		return checkMerchantCustomerOnSpot;
	}
	
	
	public void setCheckMerchantCustomerOnSpot(Integer checkMerchantCustomerOnSpot) {
		this.checkMerchantCustomerOnSpot = checkMerchantCustomerOnSpot;
	}
	
	
	public Integer getCheckMerchantPurchaseConfirmed() {
		return checkMerchantPurchaseConfirmed;
	}
	
	
	public void setCheckMerchantPurchaseConfirmed(
			Integer checkMerchantPurchaseConfirmed) {
		this.checkMerchantPurchaseConfirmed = checkMerchantPurchaseConfirmed;
	}
	
	
	public Integer getCheckMerchantRiskHint() {
		return checkMerchantRiskHint;
	}
	
	
	public void setCheckMerchantRiskHint(Integer checkMerchantRiskHint) {
		this.checkMerchantRiskHint = checkMerchantRiskHint;
	}
	
	
	public Integer getCheckBuckleIsBuckleAgreement() {
		return checkBuckleIsBuckleAgreement;
	}
	
	
	public void setCheckBuckleIsBuckleAgreement(Integer checkBuckleIsBuckleAgreement) {
		this.checkBuckleIsBuckleAgreement = checkBuckleIsBuckleAgreement;
	}
	
	
	public Integer getCheckBuckleTextClarifyResult() {
		return checkBuckleTextClarifyResult;
	}
	
	
	public void setCheckBuckleTextClarifyResult(Integer checkBuckleTextClarifyResult) {
		this.checkBuckleTextClarifyResult = checkBuckleTextClarifyResult;
	}
	
	
	public Integer getCheckBuckleSignatureClarifyResult() {
		return checkBuckleSignatureClarifyResult;
	}
	
	
	public void setCheckBuckleSignatureClarifyResult(
			Integer checkBuckleSignatureClarifyResult) {
		this.checkBuckleSignatureClarifyResult = checkBuckleSignatureClarifyResult;
	}
	
	
	public Integer getCheckBuckleDoesSignatureAndIdNameMatch() {
		return checkBuckleDoesSignatureAndIdNameMatch;
	}
	
	
	public void setCheckBuckleDoesSignatureAndIdNameMatch(
			Integer checkBuckleDoesSignatureAndIdNameMatch) {
		this.checkBuckleDoesSignatureAndIdNameMatch = checkBuckleDoesSignatureAndIdNameMatch;
	}
	
	
	public Integer getCheckDafyRecord() {
		return checkDafyRecord;
	}
	
	
	public void setCheckDafyRecord(Integer checkDafyRecord) {
		this.checkDafyRecord = checkDafyRecord;
	}
	
	
	public Integer getCheckHaodaiRecord() {
		return checkHaodaiRecord;
	}
	
	
	public void setCheckHaodaiRecord(Integer checkHaodaiRecord) {
		this.checkHaodaiRecord = checkHaodaiRecord;
	}
	
	
	public Integer getCheckCreditEaseRecord() {
		return checkCreditEaseRecord;
	}
	
	
	public void setCheckCreditEaseRecord(Integer checkCreditEaseRecord) {
		this.checkCreditEaseRecord = checkCreditEaseRecord;
	}
	
	
	public Integer getCheckVCashRecord() {
		return checkVCashRecord;
	}
	
	
	public void setCheckVCashRecord(Integer checkVCashRecord) {
		this.checkVCashRecord = checkVCashRecord;
	}
	
	
	public Integer getCheckIOUIsIOU() {
		return checkIOUIsIOU;
	}
	
	
	public void setCheckIOUIsIOU(Integer checkIOUIsIOU) {
		this.checkIOUIsIOU = checkIOUIsIOU;
	}
	
	
	public Integer getCheckIOUIsHeadPhotoClarity() {
		return checkIOUIsHeadPhotoClarity;
	}
	
	
	public void setCheckIOUIsHeadPhotoClarity(Integer checkIOUIsHeadPhotoClarity) {
		this.checkIOUIsHeadPhotoClarity = checkIOUIsHeadPhotoClarity;
	}
	
	
	public Integer getCheckIOUIsTextClarify() {
		return checkIOUIsTextClarify;
	}
	
	
	public void setCheckIOUIsTextClarify(Integer checkIOUIsTextClarify) {
		this.checkIOUIsTextClarify = checkIOUIsTextClarify;
	}
	
	
	public Integer getCheckIOUImageComparision() {
		return checkIOUImageComparision;
	}
	
	
	public void setCheckIOUImageComparision(Integer checkIOUImageComparision) {
		this.checkIOUImageComparision = checkIOUImageComparision;
	}
	
	
	public Integer getCheckIOUMobileColor() {
		return checkIOUMobileColor;
	}
	
	
	public void setCheckIOUMobileColor(Integer checkIOUMobileColor) {
		this.checkIOUMobileColor = checkIOUMobileColor;
	}
	
	
	public Integer getCheckIOURingFinger() {
		return checkIOURingFinger;
	}
	
	
	public void setCheckIOURingFinger(Integer checkIOURingFinger) {
		this.checkIOURingFinger = checkIOURingFinger;
	}
	
	
	public Integer getCheckIOUIsSmile() {
		return checkIOUIsSmile;
	}
	
	
	public void setCheckIOUIsSmile(Integer checkIOUIsSmile) {
		this.checkIOUIsSmile = checkIOUIsSmile;
	}
	
	
	public Integer getCheckUserIsCancelApplication() {
		return checkUserIsCancelApplication;
	}
	
	
	public void setCheckUserIsCancelApplication(Integer checkUserIsCancelApplication) {
		this.checkUserIsCancelApplication = checkUserIsCancelApplication;
	}
	
	
	public Integer getCheckCourtExecuted() {
		return checkCourtExecuted;
	}
	
	
	public void setCheckCourtExecuted(Integer checkCourtExecuted) {
		this.checkCourtExecuted = checkCourtExecuted;
	}
	
	
	public Integer getCheckBilFinanceRecord() {
		return checkBilFinanceRecord;
	}
	
	
	public void setCheckBilFinanceRecord(Integer checkBilFinanceRecord) {
		this.checkBilFinanceRecord = checkBilFinanceRecord;
	}
	
	
	public Integer getCheckImageHeadPhotoPersonalPic() {
		return checkImageHeadPhotoPersonalPic;
	}
	
	
	public void setCheckImageHeadPhotoPersonalPic(
			Integer checkImageHeadPhotoPersonalPic) {
		this.checkImageHeadPhotoPersonalPic = checkImageHeadPhotoPersonalPic;
	}
	
	
	public Integer getCheckImageHeadPhotoHeadDirection() {
		return checkImageHeadPhotoHeadDirection;
	}
	
	
	public void setCheckImageHeadPhotoHeadDirection(
			Integer checkImageHeadPhotoHeadDirection) {
		this.checkImageHeadPhotoHeadDirection = checkImageHeadPhotoHeadDirection;
	}
	
	
	public Integer getCheckImageHeadPhotoFaceExpression() {
		return checkImageHeadPhotoFaceExpression;
	}
	
	
	public void setCheckImageHeadPhotoFaceExpression(
			Integer checkImageHeadPhotoFaceExpression) {
		this.checkImageHeadPhotoFaceExpression = checkImageHeadPhotoFaceExpression;
	}
	
	
	public Integer getCheckWhetherImageIsIdCardPhoto() {
		return checkWhetherImageIsIdCardPhoto;
	}
	
	
	public void setCheckWhetherImageIsIdCardPhoto(
			Integer checkWhetherImageIsIdCardPhoto) {
		this.checkWhetherImageIsIdCardPhoto = checkWhetherImageIsIdCardPhoto;
	}
	
	
	public Integer getCheckImageIdCardPersonalPic() {
		return checkImageIdCardPersonalPic;
	}
	
	
	public void setCheckImageIdCardPersonalPic(Integer checkImageIdCardPersonalPic) {
		this.checkImageIdCardPersonalPic = checkImageIdCardPersonalPic;
	}
	
	
	public Integer getCheckImageIdCardInfo() {
		return checkImageIdCardInfo;
	}
	
	
	public void setCheckImageIdCardInfo(Integer checkImageIdCardInfo) {
		this.checkImageIdCardInfo = checkImageIdCardInfo;
	}
	
	
	public Integer getCheckImagePhotoFromPoliceExist() {
		return checkImagePhotoFromPoliceExist;
	}
	
	
	public void setCheckImagePhotoFromPoliceExist(
			Integer checkImagePhotoFromPoliceExist) {
		this.checkImagePhotoFromPoliceExist = checkImagePhotoFromPoliceExist;
	}
	
	
	public Integer getCheckImageComparision() {
		return checkImageComparision;
	}
	
	
	public void setCheckImageComparision(Integer checkImageComparision) {
		this.checkImageComparision = checkImageComparision;
	}
	
	
	public Integer getCheckWhetherImageIsBankCard() {
		return checkWhetherImageIsBankCard;
	}
	
	
	public void setCheckWhetherImageIsBankCard(Integer checkWhetherImageIsBankCard) {
		this.checkWhetherImageIsBankCard = checkWhetherImageIsBankCard;
	}
	
	
	public Integer getCheckWhetherBankCardPhotoIsRecognizable() {
		return checkWhetherBankCardPhotoIsRecognizable;
	}
	
	
	public void setCheckWhetherBankCardPhotoIsRecognizable(
			Integer checkWhetherBankCardPhotoIsRecognizable) {
		this.checkWhetherBankCardPhotoIsRecognizable = checkWhetherBankCardPhotoIsRecognizable;
	}
	
	
	public Integer getCheckWhetherBankCardInfoIsRecognizable() {
		return checkWhetherBankCardInfoIsRecognizable;
	}
	
	
	public void setCheckWhetherBankCardInfoIsRecognizable(
			Integer checkWhetherBankCardInfoIsRecognizable) {
		this.checkWhetherBankCardInfoIsRecognizable = checkWhetherBankCardInfoIsRecognizable;
	}
	
	
	public Integer getCheckPersonalInfoCompanyTypeResult() {
		return checkPersonalInfoCompanyTypeResult;
	}
	
	
	public void setCheckPersonalInfoCompanyTypeResult(
			Integer checkPersonalInfoCompanyTypeResult) {
		this.checkPersonalInfoCompanyTypeResult = checkPersonalInfoCompanyTypeResult;
	}
	
	
	public Integer getTransactionMonitorResult() {
		return transactionMonitorResult;
	}
	
	
	public void setTransactionMonitorResult(Integer transactionMonitorResult) {
		this.transactionMonitorResult = transactionMonitorResult;
	}
	
	
	public Integer getTransactionMonitorRejectReason() {
		return transactionMonitorRejectReason;
	}
	
	
	public void setTransactionMonitorRejectReason(
			Integer transactionMonitorRejectReason) {
		this.transactionMonitorRejectReason = transactionMonitorRejectReason;
	}
	
	
	public Integer getLoanMoneyFailureReason() {
		return loanMoneyFailureReason;
	}
	
	
	public void setLoanMoneyFailureReason(Integer loanMoneyFailureReason) {
		this.loanMoneyFailureReason = loanMoneyFailureReason;
	}
	
	
	public Integer getFinishApplicationOperation() {
		return finishApplicationOperation;
	}
	
	
	public void setFinishApplicationOperation(Integer finishApplicationOperation) {
		this.finishApplicationOperation = finishApplicationOperation;
	}
	
	
	public Integer getCheckFirstContactRiskPromptResult() {
		return checkFirstContactRiskPromptResult;
	}
	
	
	public void setCheckFirstContactRiskPromptResult(
			Integer checkFirstContactRiskPromptResult) {
		this.checkFirstContactRiskPromptResult = checkFirstContactRiskPromptResult;
	}
	
	
	public Integer getCheckSecondContactRiskPromptResult() {
		return checkSecondContactRiskPromptResult;
	}
	
	
	public void setCheckSecondContactRiskPromptResult(
			Integer checkSecondContactRiskPromptResult) {
		this.checkSecondContactRiskPromptResult = checkSecondContactRiskPromptResult;
	}
	
	
	public Integer getCheckThirdContactRiskPromptResult() {
		return checkThirdContactRiskPromptResult;
	}
	
	
	public void setCheckThirdContactRiskPromptResult(
			Integer checkThirdContactRiskPromptResult) {
		this.checkThirdContactRiskPromptResult = checkThirdContactRiskPromptResult;
	}
	
	
	public Integer getCheckAdditionalContactRiskPromptResult_11() {
		return checkAdditionalContactRiskPromptResult_11;
	}
	
	
	public void setCheckAdditionalContactRiskPromptResult_11(
			Integer checkAdditionalContactRiskPromptResult_11) {
		this.checkAdditionalContactRiskPromptResult_11 = checkAdditionalContactRiskPromptResult_11;
	}
	
	
	public Integer getCheckAdditionalContactRiskPromptResult_12() {
		return checkAdditionalContactRiskPromptResult_12;
	}
	
	
	public void setCheckAdditionalContactRiskPromptResult_12(
			Integer checkAdditionalContactRiskPromptResult_12) {
		this.checkAdditionalContactRiskPromptResult_12 = checkAdditionalContactRiskPromptResult_12;
	}
	
	
	public Integer getCheckAdditionalContactRiskPromptResult_13() {
		return checkAdditionalContactRiskPromptResult_13;
	}
	
	
	public void setCheckAdditionalContactRiskPromptResult_13(
			Integer checkAdditionalContactRiskPromptResult_13) {
		this.checkAdditionalContactRiskPromptResult_13 = checkAdditionalContactRiskPromptResult_13;
	}
	
	
	public Integer getCheckAdditionalContactRiskPromptResult_14() {
		return checkAdditionalContactRiskPromptResult_14;
	}
	
	
	public void setCheckAdditionalContactRiskPromptResult_14(
			Integer checkAdditionalContactRiskPromptResult_14) {
		this.checkAdditionalContactRiskPromptResult_14 = checkAdditionalContactRiskPromptResult_14;
	}
	
	
	public Integer getCheckAdditionalContactRiskPromptResult_15() {
		return checkAdditionalContactRiskPromptResult_15;
	}
	
	
	public void setCheckAdditionalContactRiskPromptResult_15(
			Integer checkAdditionalContactRiskPromptResult_15) {
		this.checkAdditionalContactRiskPromptResult_15 = checkAdditionalContactRiskPromptResult_15;
	}
	
	
	public Integer getCheckAdditionalContactRiskPromptResult_16() {
		return checkAdditionalContactRiskPromptResult_16;
	}
	
	
	public void setCheckAdditionalContactRiskPromptResult_16(
			Integer checkAdditionalContactRiskPromptResult_16) {
		this.checkAdditionalContactRiskPromptResult_16 = checkAdditionalContactRiskPromptResult_16;
	}
	
	
	public Integer getCheckAdditionalContactRiskPromptResult_17() {
		return checkAdditionalContactRiskPromptResult_17;
	}
	
	
	public void setCheckAdditionalContactRiskPromptResult_17(
			Integer checkAdditionalContactRiskPromptResult_17) {
		this.checkAdditionalContactRiskPromptResult_17 = checkAdditionalContactRiskPromptResult_17;
	}
	
	
	public Integer getCheckAdditionalContactRiskPromptResult_18() {
		return checkAdditionalContactRiskPromptResult_18;
	}
	
	
	public void setCheckAdditionalContactRiskPromptResult_18(
			Integer checkAdditionalContactRiskPromptResult_18) {
		this.checkAdditionalContactRiskPromptResult_18 = checkAdditionalContactRiskPromptResult_18;
	}
	
	
	public Integer getCheckHomeCreditRecord() {
		return checkHomeCreditRecord;
	}
	
	
	public void setCheckHomeCreditRecord(Integer checkHomeCreditRecord) {
		this.checkHomeCreditRecord = checkHomeCreditRecord;
	}
	
	
	public Integer getLoanMoneyResult() {
		return loanMoneyResult;
	}
	
	
	public void setLoanMoneyResult(Integer loanMoneyResult) {
		this.loanMoneyResult = loanMoneyResult;
	}

	public Integer getCheckWhetherImageIsDGroupPhoto() {
		return checkWhetherImageIsDGroupPhoto;
	}

	public void setCheckWhetherImageIsDGroupPhoto(
			Integer checkWhetherImageIsDGroupPhoto) {
		this.checkWhetherImageIsDGroupPhoto = checkWhetherImageIsDGroupPhoto;
	}

	public Integer getCheckWhetherDGroupPhotoIsRecognizable() {
		return checkWhetherDGroupPhotoIsRecognizable;
	}

	public void setCheckWhetherDGroupPhotoIsRecognizable(
			Integer checkWhetherDGroupPhotoIsRecognizable) {
		this.checkWhetherDGroupPhotoIsRecognizable = checkWhetherDGroupPhotoIsRecognizable;
	}

	public Integer getCheckWhetherCustomerPhotoIsTheSamePerson() {
		return checkWhetherCustomerPhotoIsTheSamePerson;
	}

	public void setCheckWhetherCustomerPhotoIsTheSamePerson(
			Integer checkWhetherCustomerPhotoIsTheSamePerson) {
		this.checkWhetherCustomerPhotoIsTheSamePerson = checkWhetherCustomerPhotoIsTheSamePerson;
	}

	public Integer getCheckWhetherDPhotoIsTheSamePerson() {
		return checkWhetherDPhotoIsTheSamePerson;
	}

	public void setCheckWhetherDPhotoIsTheSamePerson(
			Integer checkWhetherDPhotoIsTheSamePerson) {
		this.checkWhetherDPhotoIsTheSamePerson = checkWhetherDPhotoIsTheSamePerson;
	}
}

