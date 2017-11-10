package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-工作证-上岗证审核_工作证-上岗证是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherWorkPermitOrLicenseRecognizableForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherWorkPermitOrLicenseRecognizableForPDLForPDL{
}

