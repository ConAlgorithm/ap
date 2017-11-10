package engine.rule.domain.cashloan;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(
    label = "CL_客户电话审核_电话是否正常接听",
    type = "number",
    adapter = "engine.rule.domain.adapter.EnumDomainAdapter",
    params = "X_CheckUserPhoneCallResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserPhoneCallResultForCL{
}

