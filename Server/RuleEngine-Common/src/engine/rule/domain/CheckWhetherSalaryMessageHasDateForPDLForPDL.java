package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-工资到账短信审核_是否有日期", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherSalaryMessageHasDateForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherSalaryMessageHasDateForPDLForPDL{
}

