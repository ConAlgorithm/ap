package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "电子借条审核V3_两张照片比对", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUImageComparision,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUImageComparisionForApp{
}

