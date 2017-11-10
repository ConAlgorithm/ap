package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "头像对比V3_公安部照片是否存在", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckImagePhotoFromPoliceExist,Catfish.Platform.ManualJobV3.Handlers")
public class CheckImagePhotoFromPoliceExistForApp{
}

