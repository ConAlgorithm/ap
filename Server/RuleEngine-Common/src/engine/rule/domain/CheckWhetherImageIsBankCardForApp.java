package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "银行卡审核V3_是否是银行卡", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherImageIsBankCard,Catfish.Platform.ManualJobV3.Handlers")
public class CheckWhetherImageIsBankCardForApp{
}

