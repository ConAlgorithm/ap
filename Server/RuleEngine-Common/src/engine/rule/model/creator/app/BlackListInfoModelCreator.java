package engine.rule.model.creator.app;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.app.BlackListInfoForm;

/**
 * Created by licanhui on 2016/12/6.
 */

public class BlackListInfoModelCreator   extends AbstractApplicationModelCreator {

    public BlackListInfoModelCreator(String appId) {
        super(appId);
    }

    
    @Override
    public String createBusinessNo() {
        return null;
    }

    @Override
    public Map<String, Object> createModelForm() {
        form = new ModelBuilder<BlackListInfoForm>(new BlackListInfoForm())
                .buidDerivativeVariables(appId).getForm();
       
        Map<String, Object> in = CollectionUtils.mapOf("in_BlackList",
            (Object) form);
        
        return in;
    }
}
