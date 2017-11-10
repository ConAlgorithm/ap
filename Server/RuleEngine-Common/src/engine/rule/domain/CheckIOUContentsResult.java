package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "检查客户电子借条照片是否①头像和②手机出现", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUContentsResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUContentsResult {

}
