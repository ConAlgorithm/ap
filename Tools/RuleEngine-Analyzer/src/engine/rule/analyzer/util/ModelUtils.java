package engine.rule.analyzer.util;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;
import engine.rule.analyzer.model.entity.ClassEntity;
import engine.rule.analyzer.model.entity.Entity;
import engine.rule.analyzer.model.entity.FieldEntity;
import engine.rule.analyzer.model.entity.MethodEntity;
import engine.rule.analyzer.model.entity.ValueEntity;
import engine.rule.analyzer.resource.ModelGenerator;

public class ModelUtils {

    private static final String CSV_FIELD_SEPARATOR = ",";
    private static final String CSV_LINE_SEPARATOR = System.lineSeparator();
    
	public static Class getWrapper(Class type)
	{
		if(type.equals(int.class))
		{
			return Integer.class;
		}else if(type.equals(float.class))
		{
			return Float.class;
		}else if(type.equals(double.class))
		{
			return Double.class;
		}else if(type.equals(long.class))
		{
			return Long.class;
		}else if(type.equals(boolean.class))
		{
			return Boolean.class;
		}else{
			return type;
		}
	}
	
	private static List<String> getLinesFromEntity(Entity entity)
	{
	    List<String> lineList = Lists.newArrayList();
	    
	    String entityString = getLineFromEntity(entity);
	    
	    if(entity == null 
	            || entity.getInnerEntities() == null 
	            || entity.getInnerEntities().isEmpty())
	    {
	        lineList.add(entityString + CSV_LINE_SEPARATOR);
	    }
	    else{
	        for(Entity childEntity : entity.getInnerEntities()) {
	            for(String str : getLinesFromEntity(childEntity)) {
	                lineList.add(entityString + str);
	            }
	        }
	    }
	    
	    return lineList;
	}
	
    private static String getLineFromEntity(Entity entity) {
        StringBuilder sb = new StringBuilder();
        if (entity instanceof ClassEntity) {
            ClassEntity ce = (ClassEntity) entity;
            return sb.append(ce.getCls().getSimpleName())
                    .append(CSV_FIELD_SEPARATOR).append(ce.getRuleName())
                    .append(CSV_FIELD_SEPARATOR).toString();
        } else if (entity instanceof FieldEntity) {
            FieldEntity fe = (FieldEntity) entity;
            return sb.append("Field").append(CSV_FIELD_SEPARATOR)
                    .append(fe.getFieldName()).append(CSV_FIELD_SEPARATOR)
                    .append(fe.getDerivativeVariableName()).append(CSV_FIELD_SEPARATOR)
                    .append(fe.getRuleName()).append(CSV_FIELD_SEPARATOR)
                    .toString();
        } else if (entity instanceof MethodEntity) {
            MethodEntity fe = (MethodEntity) entity;
            sb.append("Method").append(CSV_FIELD_SEPARATOR)
              .append(fe.getMethodName());
            for (Class<?> clazz : fe.getParamTypes()) {
                sb.append("_").append(clazz.getSimpleName());
            }
            return sb.append(CSV_FIELD_SEPARATOR)
                    .append(fe.getRuleName())
                    .append(CSV_FIELD_SEPARATOR).toString();
        } else if(entity instanceof ValueEntity) {
            ValueEntity ve = (ValueEntity)entity;
            return sb.append(ve.getValue())
            .append(CSV_FIELD_SEPARATOR)
            .append(ve.getDescription())
            .append(CSV_FIELD_SEPARATOR).toString();
        }
        else {
            return CSV_FIELD_SEPARATOR;
        }
    }
	
    public static String getCsvString(List<Class<?>> clsList) {
        StringBuilder csv = new StringBuilder();
        for(Class<?> cls : clsList)
        {
            Entity clazzEntity = ModelGenerator.generateClassEntity(cls);
            for(String line : getLinesFromEntity(clazzEntity)) {
                csv.append(line);
            }
        }
        return csv.toString();
    }
}
