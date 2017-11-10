package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "电子借条审核V3_检查客户是否左手无名指戴戒指", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOURingFinger,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOURingFingerForApp{
}

