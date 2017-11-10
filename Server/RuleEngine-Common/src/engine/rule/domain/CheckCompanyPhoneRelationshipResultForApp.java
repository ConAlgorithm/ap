package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "公司电话审核V3_与联系人关系", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckCompanyPhoneRelationshipResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckCompanyPhoneRelationshipResultForApp{
}

