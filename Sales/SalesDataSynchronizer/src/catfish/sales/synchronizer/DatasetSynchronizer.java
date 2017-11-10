package catfish.sales.synchronizer;

import java.lang.reflect.Type;
import java.util.Map;

public class DatasetSynchronizer {

	public Type objectType;
	public Map<String,String> map;
	
	public DatasetSynchronizer(Type type, Map<String,String> map){
		this.objectType=type;
		this.map=map;
	}
}
