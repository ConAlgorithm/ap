package engine.rule.domain.app;

import com.huateng.toprules.core.annotation.ModelDomainInstance;

@ModelDomainInstance(label = "文件类型", type = "number", adapter = "engine.rule.domain.adapter.FixedDomainFieldsAdapter", params = "catfish.base.business.common.app.UploadFileFlag")
public class UploadFileFlag {

}
