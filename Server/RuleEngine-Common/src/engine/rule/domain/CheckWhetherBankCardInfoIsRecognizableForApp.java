package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "银行卡审核V3_卡片审核信息是否可以辨认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherBankCardInfoIsRecognizable,Catfish.Platform.ManualJobV3.Handlers")
public class CheckWhetherBankCardInfoIsRecognizableForApp{
}

