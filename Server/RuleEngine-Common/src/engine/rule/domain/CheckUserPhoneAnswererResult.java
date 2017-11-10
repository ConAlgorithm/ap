package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "通话人接听情况", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserPhoneAnswererResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserPhoneAnswererResult {

}
