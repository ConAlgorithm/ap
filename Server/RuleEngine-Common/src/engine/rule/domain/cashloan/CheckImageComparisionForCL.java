package engine.rule.domain.cashloan;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(
    label = "CL_头像照审核_三张照片是否一致",
    type = "number",
    adapter = "engine.rule.domain.adapter.EnumDomainAdapter",
    params = "X_CheckImageComparision,Catfish.Platform.ManualJobV3.Handlers")
public class CheckImageComparisionForCL{
}

