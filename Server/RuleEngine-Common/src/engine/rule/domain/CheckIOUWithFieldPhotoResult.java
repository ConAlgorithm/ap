package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "电子借条:两个照片人像的人脸比对", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckIOUWithFieldPhotoResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckIOUWithFieldPhotoResult {

}
