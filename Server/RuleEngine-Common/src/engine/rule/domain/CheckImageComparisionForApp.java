package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "头像对比V3_三张照片是否一致", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckImageComparision,Catfish.Platform.ManualJobV3.Handlers")
public class CheckImageComparisionForApp{
}

