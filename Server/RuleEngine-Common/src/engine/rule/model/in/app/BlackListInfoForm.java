package engine.rule.model.in.app;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import catfish.base.business.util.AppDerivativeVariableNames;
import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

/**
 * Created by licanhui on 2016/12/6.
 */
@ModelInstance(description = "黑名单")
public class BlackListInfoForm extends BaseForm {

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.XBaiduBlackLevel)
    @ModelField(name = "百度黑名单等级", bindDomain = "engine.rule.domain.BlackListLevel")
    private String baidu_blackLevel = "-9999";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.XBaiduBlackReason)
    @ModelField(name = "百度加黑原因", bindDomain = "engine.rule.domain.BlackListReason")
    private String baidu_blackReason = "-9999";

    public String getBaidu_blackLevel() {
        return baidu_blackLevel;
    }

    public void setBaidu_blackLevel(String baidu_blackLevel) {
        this.baidu_blackLevel = baidu_blackLevel;
    }

    public String getBaidu_blackReason() {
        return baidu_blackReason;
    }

    public void setBaidu_blackReason(String baidu_blackReason) {
        this.baidu_blackReason = baidu_blackReason;
    }

}
