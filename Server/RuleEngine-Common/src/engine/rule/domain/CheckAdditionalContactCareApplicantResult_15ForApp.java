package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "第五补充联系人电话审核V3_主观判断联系人是否关心申请人", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckAdditionalContactCareApplicantResult_15,Catfish.Platform.ManualJobV3.Handlers")
public class CheckAdditionalContactCareApplicantResult_15ForApp{
}

