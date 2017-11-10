package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_身份证住址确认", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserIDCardAddress,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserIDCardAddressForApp{
}

