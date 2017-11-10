package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-现场照片审核V3_现场照片人脸是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckImageHeadPhotoPersonalPicForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckImageHeadPhotoPersonalPicForPDLForPDL{
}

