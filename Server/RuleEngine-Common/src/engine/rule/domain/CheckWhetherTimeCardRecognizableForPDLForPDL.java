package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-考勤卡审核_考勤卡是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherTimeCardRecognizableForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherTimeCardRecognizableForPDLForPDL{
}

