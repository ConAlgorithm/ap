package catfish.finance.payment.activities;

import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.ActivityFactoryBase;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class QueryActivity extends Activity{

    @Override
    protected void init(Application application, IServiceProvider sp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void process(Application application, IServiceProvider sp) {
        // TODO Auto-generated method stub
        this.done = true;
    }
}

class QueryActivityFactory extends ActivityFactoryBase<QueryActivity>{
    
}
