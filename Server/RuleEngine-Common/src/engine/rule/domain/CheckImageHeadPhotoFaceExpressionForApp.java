package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "头像照审核V3_头像现场照片表情", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckImageHeadPhotoFaceExpression,Catfish.Platform.ManualJobV3.Handlers")
public class CheckImageHeadPhotoFaceExpressionForApp{
}

