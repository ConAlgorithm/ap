package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-考勤卡审核_是否有单位印章", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherTimeCardHasCompanySealForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherTimeCardHasCompanySealForPDLForPDL{
}

