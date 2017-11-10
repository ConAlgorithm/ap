package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-合影检查_合影是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherGroupPhotoIsRecognizableForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherGroupPhotoIsRecognizableForPDLForPDL{
}

