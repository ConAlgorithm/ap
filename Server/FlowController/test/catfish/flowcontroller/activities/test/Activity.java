package catfish.flowcontroller.activities.test;

import catfish.flowcontroller.test.Application;
import catfish.framework.IServiceProvider;

public abstract class Activity {
    public String name;
    
    protected boolean pass = false;
    
    public abstract void run(Application app, IServiceProvider sp);
    
    public abstract boolean complete(Application app, IServiceProvider sp);
    
    public boolean isPass(){
        return this.pass;
    }
}
