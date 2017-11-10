package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-客户电话审核_单位信息核实", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserCompanyPaydayForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckUserCompanyPaydayForPDLForPDL{
}

