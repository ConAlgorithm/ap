package catfish.data.metadata;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import catfish.data.metadata.domain.Variable;
import catfish.data.metadata.domain.VariableType;
import catfish.data.metadata.service.VariableService;
import catfish.data.metadata.util.CSVUtils;

public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        VariableService vs = (VariableService)context.getBean("variableService");
        
        CSVUtils.importCsv("src/main/resources/Variables.csv", vs);

        context.close();
    }
}
