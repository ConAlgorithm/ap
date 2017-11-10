package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "客户电话审核V3_聚信立信息收集", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserJXLInforCrawlStatus,Catfish.Platform.ManualJobV3.Handlers")
public class CheckUserJXLInforCrawlStatusForApp{
}

