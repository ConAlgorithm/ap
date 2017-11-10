package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-工资到账短信审核_是否有收入金额", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherSalaryMessageHasSalaryAmountForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherSalaryMessageHasSalaryAmountForPDLForPDL{
}

