package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "电子借条审核V3_是否是电子借条", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUIsIOU,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUIsIOUForApp{
}

