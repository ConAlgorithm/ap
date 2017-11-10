package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "公司电话审核V3_回答问题过程中是否有他人提示", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckCompanySomeElseHelpResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckCompanySomeElseHelpResultForApp{
}

