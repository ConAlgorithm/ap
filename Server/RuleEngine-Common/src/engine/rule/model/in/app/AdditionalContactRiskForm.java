package engine.rule.model.in.app;

import static catfish.base.business.common.AppDerivativeVariableConsts.CheckAdditionalContactRiskPromptResult;
import static catfish.base.business.common.AppDerivativeVariableConsts.CheckAdditionalContactSomeoneElseHelpResult;
import static catfish.base.business.common.AppDerivativeVariableConsts.CheckAdditionalContactSoundJudgeResult;
import catfish.base.business.common.CheckContactAnswerWithReminderResult;
import catfish.base.business.common.CheckContactRiskRevealResult;
import catfish.base.business.common.CheckContactSoundResult;

import com.huateng.toprules.core.annotation.ModelField;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

public class AdditionalContactRiskForm extends BaseForm {
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = CheckAdditionalContactRiskPromptResult)
	@ModelField(name = "其他联系人电核风险提示", bindDomain = "engine.rule.domain.CheckContactRiskRevealResult")
	private Integer additionalContactRiskReveal = CheckContactRiskRevealResult.NoRiskPrompt
			.getValue();

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = CheckAdditionalContactSoundJudgeResult)
	@ModelField(name = "其他联系人声音风险提示", bindDomain = "engine.rule.domain.CheckContactSoundResult")
	private Integer additionalContactSoundReveal = CheckContactSoundResult.NoSoundException
			.getValue();

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = CheckAdditionalContactSomeoneElseHelpResult)
	@ModelField(name = "其他联系人回答问题风险提示", bindDomain = "engine.rule.domain.CheckContactAnswerWithReminderResult")
	private Integer additionalContactAnswerReminderReveal = 999;

	public Integer getAdditionalContactRiskReveal() {
		return additionalContactRiskReveal;
	}

	public void setAdditionalContactRiskReveal(
			Integer additionalContactRiskReveal) {
		this.additionalContactRiskReveal = additionalContactRiskReveal;
	}

	public Integer getAdditionalContactSoundReveal() {
		return additionalContactSoundReveal;
	}

	public void setAdditionalContactSoundReveal(
			Integer additionalContactSoundReveal) {
		this.additionalContactSoundReveal = additionalContactSoundReveal;
	}

	public Integer getAdditionalContactAnswerReminderReveal() {
		return additionalContactAnswerReminderReveal;
	}

	public void setAdditionalContactAnswerReminderReveal(
			Integer additionalContactAnswerReminderReveal) {
		this.additionalContactAnswerReminderReveal = additionalContactAnswerReminderReveal;
	}
}
