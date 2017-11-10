package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-银行卡审核_是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherBankCardPhotoIsRecognizableForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherBankCardPhotoIsRecognizableForPDLForPDL{
}

