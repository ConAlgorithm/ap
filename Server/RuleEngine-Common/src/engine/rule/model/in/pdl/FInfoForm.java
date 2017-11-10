package engine.rule.model.in.pdl;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import engine.rule.model.BaseForm;

@ModelInstance(description = "F信息")
public class FInfoForm extends BaseForm{
    
    @ModelField(name = "F1号")
    private String f1Id;
    
    @ModelField(name = "的门店所在城市")
    private String city;
    
    @ModelField(name = "的门店所在省份")
    private String province;
    
    
    public String getF1Id() {
        return f1Id;
    }
    public void setF1Id(String f1Id) {
        this.f1Id = f1Id;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
}
