package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-第二联系人电话审核V3_主观判断联系人是否关心申请人", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckSecondContactCareApplicantResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckSecondContactCareApplicantResultForPDLForPDL{
}

