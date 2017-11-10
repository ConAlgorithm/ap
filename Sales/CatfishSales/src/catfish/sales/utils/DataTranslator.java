package catfish.sales.utils;

import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;

public class DataTranslator {
	
	public static <T> T convert (Object source,T target,Map<String,String> map){
    	if(source==null){
    		return target;
    	}
    	Class<? extends Object> cc=source.getClass();
		Field[] Fields=cc.getFields();		
		for(Field field:Fields){
			try{
				
				Field targetField=target.getClass().getField(convertName(field.getName()));
				if(targetField!=null){
					targetField.set(target, field.get(source));
					//Logger.get().info("["+field.getName()+"]=["+ field.get(model)+"]");
				}
			}
			catch(NoSuchFieldException e){
				Logger.get().warn(field.getName());
				continue;
			}
			catch(Exception e){
				Logger.get().error(field.getName(),e);
				continue;
			}
		}
		return target;
	}
	
	public static <T> T convert (Object source,Class<T> c,Map<String,String> map) throws Exception{
		T t = c.newInstance();
		return convert(source,t,map);	
	}
	
	public static <Tsource,Ttarget> List<Ttarget> convertList(List<Tsource> sources,Class<Ttarget> c,List<Ttarget> targets ,Map<String,String> map){
		if(sources.size()==0){
			return null;
		}
		for(Tsource source : sources){
			try {
				targets.add((Ttarget) convert(source,c,map));
			} catch (Exception e) {
				Logger.get().error(source,e);
			}
		}
		return targets;
	}
	
	private static String convertName(String sourceName){
		if(sourceName==sourceName.toLowerCase().substring(0, 1).concat(sourceName.substring(1))){
			return sourceName.toUpperCase().substring(0, 1).concat(sourceName.substring(1));
		}
		return sourceName.toLowerCase().substring(0, 1).concat(sourceName.substring(1));
	}

}
