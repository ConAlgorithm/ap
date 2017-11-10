package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "银行卡审核V3_是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherBankCardPhotoIsRecognizable,Catfish.Platform.ManualJobV3.Handlers")
public class CheckWhetherBankCardPhotoIsRecognizableForApp{
}

