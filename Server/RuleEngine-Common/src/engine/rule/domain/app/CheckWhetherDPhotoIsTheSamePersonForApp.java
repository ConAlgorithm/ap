package engine.rule.domain.app;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "D1合影审查_D照片判断（是否为同一个人）", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherDPhotoIsTheSamePerson,Catfish.Platform.ManualJobV3.Handlers")
public class CheckWhetherDPhotoIsTheSamePersonForApp {

}
