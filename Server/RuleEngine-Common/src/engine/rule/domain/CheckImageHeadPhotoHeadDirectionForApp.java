package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "头像照审核V3_头像现场照片头像方向", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckImageHeadPhotoHeadDirection,Catfish.Platform.ManualJobV3.Handlers")
public class CheckImageHeadPhotoHeadDirectionForApp{
}

