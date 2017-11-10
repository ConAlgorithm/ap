package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "电子借条审核V3_电子借条审核信息是否可以辨认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUIsTextClarify,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUIsTextClarifyForApp{
}

