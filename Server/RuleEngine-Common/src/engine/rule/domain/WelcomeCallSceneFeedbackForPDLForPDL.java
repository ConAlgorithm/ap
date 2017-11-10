package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-欢迎电话审核V3_客户是否接受", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_WelcomeCallSceneFeedbackForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class WelcomeCallSceneFeedbackForPDLForPDL{
}

