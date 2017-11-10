package engine.rule.domain.cashloan;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(
    label = "CL_交易监控审核_是否本人",
    type = "number",
    adapter = "engine.rule.domain.adapter.EnumDomainAdapter",
    params = "X_TransactionMonitorIsOneself,Catfish.Platform.ManualJobV3.Handlers")
public class TransactionMonitorForCL {

}
