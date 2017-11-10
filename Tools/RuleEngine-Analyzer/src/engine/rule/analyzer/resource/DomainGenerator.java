package engine.rule.analyzer.resource;

import java.util.LinkedList;
import java.util.List;

import catfish.base.Logger;

import com.huateng.toprules.core.adapter.DynamicDomainAdapter;
import com.huateng.toprules.core.annotation.ModelDomainInstance;
import com.huateng.toprules.core.model.bean.DynamicDomain;
import com.huateng.toprules.core.model.bean.DynamicDomainAttribute;

import engine.rule.analyzer.model.entity.ValueEntity;

public class DomainGenerator {

	public static ModelDomainInstance getDomainInstance(Class<?> domainCls)
	{	
        return domainCls.getAnnotation(ModelDomainInstance.class);      
	}
	
	public static List<ValueEntity> getDomainItemsList(ModelDomainInstance instance, Class<?> adapterCls){
		try {
            if(instance != null) {
                if(adapterCls != null) {
                    DynamicDomainAdapter adapter = (DynamicDomainAdapter)adapterCls.newInstance();
                    DynamicDomain dd = adapter.execute(adapterCls.getName(),instance.adapter(),instance.params().split(","));                      
                    if (dd != null && dd.getAttributes() != null)
                    {
                    	List<ValueEntity> list = new LinkedList<>();
                        for (DynamicDomainAttribute att : dd.getAttributes()) {
                            list.add(new ValueEntity(null,att.getValue(),att.getLabel()));
                        }
                        return list;
                    }                       
                }
            }
        } catch(Exception e){
        	Logger.get().error(String.format("getDomainItemsList of %s error", adapterCls.getName()), e);
        }
        return null;
	}
}
