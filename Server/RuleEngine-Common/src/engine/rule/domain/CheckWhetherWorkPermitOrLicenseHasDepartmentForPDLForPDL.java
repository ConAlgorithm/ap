package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-工作证-上岗证审核_是否有部门信息", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherWorkPermitOrLicenseHasDepartmentForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherWorkPermitOrLicenseHasDepartmentForPDLForPDL{
}

