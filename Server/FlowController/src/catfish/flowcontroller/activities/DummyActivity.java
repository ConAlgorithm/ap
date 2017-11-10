package catfish.flowcontroller.activities;

import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class DummyActivity extends Activity{

	@Override
	protected void init(Application application, IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		this.done = true;
	}
}

class DummyActivityFactory extends ActivityFactoryBase<DummyActivity>{
	
}
