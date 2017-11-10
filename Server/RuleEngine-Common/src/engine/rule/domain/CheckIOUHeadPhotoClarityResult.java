package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "客户电子借条照片中头像是否清晰可以辨认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUHeadPhotoClarityResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUHeadPhotoClarityResult {

}
