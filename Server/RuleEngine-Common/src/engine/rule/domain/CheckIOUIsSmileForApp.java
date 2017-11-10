package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "电子借条审核V3_客户是否有笑容", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUIsSmile,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUIsSmileForApp{
}

