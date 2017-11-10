package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "电子借条审核V3_检查客户电子借条的手机外壳颜色", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUMobileColor,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUMobileColorForApp{
}

