package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "公司电话审核V3_单位电话拨打情况", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckCompanyPhoneCallResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckCompanyPhoneCallResultForApp{
}

