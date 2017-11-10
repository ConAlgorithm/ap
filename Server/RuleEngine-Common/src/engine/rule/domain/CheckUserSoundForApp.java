package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_申请人声音和背景音与常识推断", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserSound,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserSoundForApp{
}

