package engine.rule.domain.cashloan;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(
    label = "CL_客户电话审核_通话人接听情况",
    type = "number",
    adapter = "engine.rule.domain.adapter.EnumDomainAdapter",
    params = "X_CheckUserPhoneAnswererResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserPhoneAnswererResultForCL{
}

