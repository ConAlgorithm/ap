package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "公司电话审核V3_电核过程交流中异常", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckCompanyAnswerPersonAnswerResult,Catfish.Platform.ManualJobV3.Handlers")
public class CheckCompanyAnswerPersonAnswerResultForApp{
}

