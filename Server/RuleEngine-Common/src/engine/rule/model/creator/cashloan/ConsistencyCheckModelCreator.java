package engine.rule.model.creator.cashloan;

import java.util.Map;

import catfish.base.CollectionUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.cashloan.ConsistencyCheckInForm;


public class ConsistencyCheckModelCreator extends AbstractApplicationModelCreator {

    public ConsistencyCheckModelCreator(String appId) {
        super(appId);
    }

    @Override
    public Map<String, Object> createModelForm() {
        form = new ModelBuilder<ConsistencyCheckInForm>(new ConsistencyCheckInForm())
                .buidDerivativeVariables(appId).getForm();
        
        return CollectionUtils.mapOf("in_Derived", (Object) form);
    }

    @Override
    public String createBusinessNo() {
        // TODO Auto-generated method stub
        return null;
    }
}
