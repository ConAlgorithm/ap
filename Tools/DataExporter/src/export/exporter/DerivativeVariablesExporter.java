package export.exporter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;

public abstract class DerivativeVariablesExporter implements IExportable<AppDerivativeVariables>{

	protected IWritable writer;
	
	public DerivativeVariablesExporter(IWritable writer) {
		this.writer = writer;
	}

	protected List<String> getRecordHeader(Class<?> obj)
	{
		List<String> list = new ArrayList<>();
		list.add("AppId");
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field item : fields)
		{
			list.add(item.getName());
		}
		return list;
	}
	
	protected List<String> getRecord(String appId, Object obj)
	{
		List<String> list = new ArrayList<>();
		list.add(appId);
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field item : fields)
		{
			try {
				PropertyDescriptor pd = new PropertyDescriptor(item.getName(),  
	                    obj.getClass());  
	            Method getMethod = pd.getReadMethod();
	            Object o = getMethod.invoke(obj);
	            if(o != null)
				     list.add(o.toString());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static AppDerivativeVariables getDerivativeVariables(String appId)
	{
    	AppDerivativeVariables variable = AppDerivativeVariableManager.getVariables(appId, 
    			AppDerivativeVariableNames.YLZH_BANKCARD_MOBILE_MATCH,
    			AppDerivativeVariableNames.YLZH_CONSUMEREGION_LIST,
    			AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_PRINCIPALS,
    			AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_PRODUCTS,
    			AppDerivativeVariableNames.MERCHANT_VIEW_STATISTICS_REPAYMONTHS,
    			AppDerivativeVariableNames.JXL_REPORTDATA_PERSON,
    			AppDerivativeVariableNames.JXL_REPORTDATA_APPLICATION_CHECK,
    			AppDerivativeVariableNames.JXL_REPORTDATA_BEHAVIOR_CHECK,
    			AppDerivativeVariableNames.JXL_REPORTDATA_DATA_SOURCE,
    			AppDerivativeVariableNames.JXL_REPORTDATA_CELL_BEHAVIOR,
    			AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_REGION,
    			AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_LIST);
    	return variable;
	}
}
