package jma.test;

import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import jma.models.GPSRecord;

public class TestHttpUtils {

    public static void main(String[] args) {
            
            List<GPSRecord> records = new LinkedList<>();
            
            for(int i=0; i< 2; i++) {
                GPSRecord record = new GPSRecord();
                record.setLatituede(new Double(i+1));
                record.setLongitude(new Double(i+10));
                records.add(record);
            }
            
            String jsonString = new Gson().toJson(records);
            
            System.out.println(jsonString);
            
            List<GPSRecord> tmp = new Gson().fromJson(jsonString, records.getClass());
    }
}
