package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "电子借条审核V3_是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUIsHeadPhotoClarity,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUIsHeadPhotoClarityForApp{
}

