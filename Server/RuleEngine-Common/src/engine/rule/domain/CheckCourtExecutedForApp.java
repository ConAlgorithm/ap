package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "法院被执行审核V3_法院被执行记录", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckCourtExecuted,Catfish.Platform.ManualJobV3.Handlers")
public class CheckCourtExecutedForApp{
}

