package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "公司电话审核V3_电话接通后，通话人情况", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckCompanyIdentificationResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckCompanyIdentificationResultForApp{
}

