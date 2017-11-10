package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-工资到账短信审核_是否有银行卡尾号后四位", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherSalaryMessageHasBankCardTheLastFourNoForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherSalaryMessageHasBankCardTheLastFourNoForPDLForPDL{
}

