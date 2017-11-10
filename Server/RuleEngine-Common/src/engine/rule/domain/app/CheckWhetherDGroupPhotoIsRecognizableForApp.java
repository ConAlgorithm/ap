package engine.rule.domain.app;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "D1合影审查_合影是否清晰可辨识", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherDGroupPhotoIsRecognizable,Catfish.Platform.ManualJobV3.Handlers")
public class CheckWhetherDGroupPhotoIsRecognizableForApp {

}
