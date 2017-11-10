package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-考勤卡审核_是否是正面考勤卡", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_IsTimeCardForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class IsTimeCardForPDLForPDL{
}

