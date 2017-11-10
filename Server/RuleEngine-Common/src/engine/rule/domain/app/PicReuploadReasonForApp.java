package engine.rule.domain.app;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "图片重传原因", type = "string", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "engine.rule.domain.app.consts.PicReuploadReason")
public class PicReuploadReasonForApp {

}
