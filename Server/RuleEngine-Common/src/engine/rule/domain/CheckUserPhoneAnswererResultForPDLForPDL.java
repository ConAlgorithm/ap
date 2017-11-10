package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-客户电话审核_通话人接听情况", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckUserPhoneAnswererResultForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckUserPhoneAnswererResultForPDLForPDL{
}

