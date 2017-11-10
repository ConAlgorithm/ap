package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-客户电话审核_主观判断申请人对使用", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserCarenessLevelForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckUserCarenessLevelForPDLForPDL{
}

