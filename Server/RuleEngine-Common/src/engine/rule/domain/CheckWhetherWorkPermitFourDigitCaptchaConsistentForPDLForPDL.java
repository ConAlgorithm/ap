package engine.rule.domain;

import com.huateng.toprules.core.annotation.ModelDomainInstance;


@ModelDomainInstance(label = "PDL-工作证检查_4位验证码审核（是否一致）", type = "number", adapter = "engine.rule.domain.adapter.EnumDomainAdapter", params = "X_CheckWhetherWorkPermitFourDigitCaptchaConsistentForPDL,Catfish.Platform.ManualJobV4.PDLHandlers")
public class CheckWhetherWorkPermitFourDigitCaptchaConsistentForPDLForPDL{
}

