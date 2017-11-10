package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "头像照审核V3_现场照片人脸是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckImageHeadPhotoPersonalPic,Catfish.Platform.ManualJobV3.Handlers")
public class CheckImageHeadPhotoPersonalPicForApp{
}

