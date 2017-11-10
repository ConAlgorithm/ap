package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-银行卡审核_是否是银行卡", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherImageIsBankCardForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherImageIsBankCardForPDLForPDL{
}

