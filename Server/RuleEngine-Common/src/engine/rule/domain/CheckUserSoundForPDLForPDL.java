package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-客户电话审核_申请人声音和背景音与常识推断", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserSoundForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckUserSoundForPDLForPDL{
}

