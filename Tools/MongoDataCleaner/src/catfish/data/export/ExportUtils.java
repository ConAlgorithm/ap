package catfish.data.export;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvWriter;
import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.data.model.CompanyAnswerPersonAnswerModel;

public class ExportUtils {
    
    private static char _separator = ',';

    public static void export2Csv(List<CompanyAnswerPersonAnswerModel> modelList, String fileName) {
        CsvWriter csvWriter = new CsvWriter(fileName, _separator, Charset.forName("UTF-8"));
        
        String[] line = getHeader(CompanyAnswerPersonAnswerModel.class).toArray(new String[0]);
        try {
            csvWriter.writeRecord(line);
            csvWriter.flush();
            
            for(CompanyAnswerPersonAnswerModel model : modelList) {
                line = getRecord(model).toArray(new String[0]);
                csvWriter.writeRecord(line);
                csvWriter.flush();
            }
            
        } catch (IOException e) {
            Logger.get().error("write line error : " + new Gson().toJson(line));
        }
        
    }
    
    private static List<String> getHeader(Class<?> obj)
    {
        List<String> list = new ArrayList<>();
        Field[] fields = obj.getDeclaredFields();
        for(Field item : fields)
        {
            list.add(item.getName());
        }
        return list;
    }
    
    private static List<String> getRecord(Object obj)
    {
        List<String> list = new ArrayList<>();
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
                Logger.get().error("get record error for : " + new Gson().toJson(obj));
            }
        }
        return list;
    }
}
