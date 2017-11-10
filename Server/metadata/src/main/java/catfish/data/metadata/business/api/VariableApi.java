package catfish.data.metadata.business.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import catfish.data.metadata.business.model.VariableData;
import catfish.data.metadata.domain.Variable;
import catfish.data.metadata.service.VariableService;

public class VariableApi {
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");;

	public static List<VariableData> getAll(){
		
        VariableService vs = (VariableService)context.getBean("variableService");
        
        List<VariableData> list = new ArrayList<VariableData>();
        for (Variable variable : vs.getVariableRepository().findAll()) {
        	VariableData variableData = new VariableData();
        	variableData.setDefaultValue(variable.getDefaultValue());
        	variableData.setName(variable.getName());
        	variableData.setType(variable.getVariableType().getName());
        	variableData.setDescription(variable.getDescription());
        	list.add(variableData);
		}
        
        return list;
	}
}
