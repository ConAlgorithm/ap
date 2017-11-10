package engine.rule.domain.cashloan;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(
    label = "CL_客户电话审核_住宅地址确认",
    type = "number",
    adapter = "engine.rule.domain.adapter.EnumDomainAdapter",
    params = "X_CheckImageComparision,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserLivingAddressForCL {
}
