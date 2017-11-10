package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "个人信息审核V3_客户公司性质判断", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckPersonalInfoCompanyTypeResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckPersonalInfoCompanyTypeResultForApp{
}

