package engine.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import engine.databaseapi.DerivativeVariableApi;

public class DataTypeMappings {
	private static Map<String, String> mongoJavaDataTypeMappings = new HashMap<String, String>();
	private static Map<String, String> javaMongoDataTypeMappings = new HashMap<String, String>();

	static{
		mongoJavaDataTypeMappings.put(DerivativeVariableApi.IntValue, "Integer");
		mongoJavaDataTypeMappings.put(DerivativeVariableApi.BoolValue, "boolean");
		mongoJavaDataTypeMappings.put(DerivativeVariableApi.StringValue, "String");
		mongoJavaDataTypeMappings.put(DerivativeVariableApi.DecimalValue, "Double");
		mongoJavaDataTypeMappings.put(DerivativeVariableApi.DateTimeValue, "Date");
		
		for (Entry<String, String> entry : mongoJavaDataTypeMappings.entrySet()) {
			javaMongoDataTypeMappings.put(entry.getValue(), entry.getKey());
		}
	}
	
	public static String toMongoDataType(String javaDataType){
		return javaMongoDataTypeMappings.get(javaDataType);
	}
	
	public static String toJavaDataType(String mongoDataType){
		return mongoJavaDataTypeMappings.get(mongoDataType);
	}
}
