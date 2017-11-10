package engine.rule.domain.app;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "D1合影审查_是否是合影", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherImageIsDGroupPhoto,Catfish.Platform.ManualJobV3.Handlers")
public class CheckWhetherImageIsDGroupPhotoForApp {

}
