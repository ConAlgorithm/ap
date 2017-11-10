package catfish.finance.payment.activities;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.flowcontroller.ActivityCreator;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.ActivityFactory;

public class Creator implements ActivityCreator{
    private Map<String, ActivityFactory> activityFactoryMap = new HashMap<String, ActivityFactory>();
    public Creator(){
        activityFactoryMap.put("paymentActivity", new PaymentActivityFactory());
        activityFactoryMap.put("queryActivity", new QueryActivityFactory());
    }

    @Override
    public Activity create(Map<String, Object> activityConfig) {
        Object typeObj = activityConfig.get("type");
        String type = "queryActivity";
        if(typeObj != null){
            type = typeObj.toString();
        }
        
        ActivityFactory af = activityFactoryMap.get(type);
        if(af == null){
            Logger.get().error("fail to get activity factory for " + type);
            return null;
        }
        return af.create(activityConfig);
    }

}
