package engine.rule.analyzer.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import catfish.base.Logger;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.rule.analyzer.model.entity.ClassEntity;
import engine.rule.analyzer.model.entity.Entity;
import engine.rule.analyzer.model.entity.FieldEntity;
import engine.rule.analyzer.model.entity.MethodEntity;
import engine.rule.model.annotation.DBField;

public class ModelGenerator {
    
	public static String getModelDescription(Class resourceCls)
	{
		String clsRuleName = null;
		try{
			@SuppressWarnings("unchecked")
			Annotation instanceAt = resourceCls.getAnnotation(ModelInstance.class);
			if(instanceAt != null)
			{
				clsRuleName = ((ModelInstance)instanceAt).description();
			}else{
				System.out.println("ModelInstance is Null of " + resourceCls.getName());
			}
		}catch(Exception e){
			Logger.get().error(String.format("getModelDescription of %s error!", resourceCls.getName()), e);
		}
		return clsRuleName;
	}
	
	public static Entity generateClassEntity(Class resourceCls)
	{		
		try{
			Entity classEntity = new ClassEntity(resourceCls, getModelDescription(resourceCls));
			Field[] fields = resourceCls.getDeclaredFields();
			for(Field item : fields)
			{
				ModelField fieldAt = item.getAnnotation(ModelField.class);
				if(fieldAt != null)
				{
					FieldEntity fldEntity = new FieldEntity(fieldAt.name(), fieldAt.bindDomain(), item, resourceCls);
					
					DBField  dbfield = item.getAnnotation(DBField.class);
					
			        if (dbfield != null)
			        {
			            fldEntity.setDerivativeVariableName(dbfield.variableType());
			        }		        
					classEntity.addEntity(fldEntity);
				}
				
			}
			Method[] methods = resourceCls.getMethods();
			for(Method item : methods)
			{
				ModelMethod  methodAt = item.getAnnotation(ModelMethod.class); 
				if(methodAt != null)
				{
					MethodEntity mdEntity = new MethodEntity(item, methodAt.name(), methodAt.paramDomains(), methodAt.returnValueDomain());
					classEntity.addEntity(mdEntity);
				}
			}
			return classEntity;
		}catch(Exception e)
		{
			Logger.get().error(String.format("generateClassEntity of %s error! ", resourceCls.getName()),e);
		}
		return null;
	}
	
	public static void generateClassFile(Class resourceCls, String targetPath)
	{
		String path = targetPath;
		if(!targetPath.endsWith(File.separator))
		{
			path = targetPath.concat(File.separator);
		}
		String resource = generateClassEntity(resourceCls).template();
		try {
			File dir = new File(path);
			if(!dir.exists())
			{
				Logger.get().info("Dir: " + path +" does not exist, now will create it automatically");
				if(!dir.mkdir())
				{
					Logger.get().error("Create Dir: " + path + "failed!");
					return;
				}else{
					Logger.get().info("Create Dir: " + path + "Succeed!");
				}
			}
			FileOutputStream out = new FileOutputStream(new File(path + resourceCls.getSimpleName().concat(".java")));
			out.write(resource.getBytes());
			out.close();
		} catch (Exception e) {
			Logger.get().error("Can not create class file: " + resourceCls.getName(), e);
		}
	}
	
	public static void clearFolder(String path)
	{
		File folder = new File(path);
		Logger.get().info("Begin to delete files in " + path);
		boolean result = true;
		if(folder.exists() && folder.isDirectory())
		{
			File[] files = folder.listFiles();
			for(File item : files)
			{
				if(!item.delete())
				{
					result = false;
					Logger.get().error("Can not delete file: " + item.getName());
				}
			}
		}
		Logger.get().info("Delete files in " + path +" Succeed!");
	}
}
