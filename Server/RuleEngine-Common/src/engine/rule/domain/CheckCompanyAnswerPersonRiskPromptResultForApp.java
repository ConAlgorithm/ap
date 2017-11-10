package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "公司电话审核V3_接听人风险提示", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckCompanyAnswerPersonRiskPromptResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckCompanyAnswerPersonRiskPromptResultForApp{
}

