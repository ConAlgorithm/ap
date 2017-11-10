package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "客户电子借条照片是否有伪造变造痕迹", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUFakedResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUFakedResult {

}
