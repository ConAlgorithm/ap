package catfish.data.metadata.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import catfish.data.metadata.domain.Variable;
import catfish.data.metadata.domain.VariableType;
import catfish.data.metadata.service.VariableService;

public class CSVUtils {
    
    public static String[] headers = {"name", "type", "defaultvalue", "description"};

    public static void importCsv(String fileName, VariableService vs) {
        
        Reader in;
        try {
            in = new FileReader(fileName);
            Iterable<CSVRecord> records;
            records = CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withSkipHeaderRecord(true)
                    .withIgnoreSurroundingSpaces(true)
                    .parse(in);
            for (CSVRecord record : records) {
                System.out.println("Handle csv record : " + record);
                handleCSVRecord(record, vs);
            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static void handleCSVRecord(CSVRecord record, VariableService vs) {
        
        String variableType = record.get("type").trim();
        VariableType type = vs.getVariableTypeRepository().findByName(variableType);
        System.out.println("find type : " + type);
        
        String name = record.get("name");
//        Variable oldVariable = vs.getVariableRepository().findByName(name);
//        if( oldVariable != null) {
//            System.out.println("Already exists : " + oldVariable);
//            return ;
//        }
        
        Variable v = new Variable();
        v.setName(name);
        v.setInnername("X_" + name.substring(0, 1).toUpperCase() + name.substring(1));
        v.setDescription(record.get("description"));
        v.setVariableType(type);
        v.setDefaultValue(record.get("defaultvalue"));
        
        Date now = new Date();
        v.setDateadded(now);
        v.setLastmodified(now);
        v.setLastmodifiedby("sys");
        
        Variable tmp = vs.getVariableRepository().save(v);
        System.out.println("Variable saved : " + tmp);
    }
}
