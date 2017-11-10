package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-客户电话审核_身份确认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserIdentificationForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckUserIdentificationForPDLForPDL{
}

