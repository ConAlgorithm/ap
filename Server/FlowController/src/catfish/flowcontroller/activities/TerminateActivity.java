package catfish.flowcontroller.activities;

import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class TerminateActivity extends Activity{

	@Override
	protected void init(Application application, IServiceProvider sp) {	
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		this.terminate();
	}
}

class TerminateActivityFactory extends ActivityFactoryBase<TerminateActivity>{
	
}
