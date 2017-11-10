package jma.test;

import java.util.LinkedList;
import java.util.List;

import jma.dataservice.PDLAppService;
import jma.models.GPSRecord;
import jma.util.StatisticsUtil;

public class TestStatisticsUtil {

    public static void main(String[] args) {
        
        List<GPSRecord> records = new LinkedList<>();
        
        for(int i=0; i< 10; i++) {
            GPSRecord record = new GPSRecord();
            record.setLatituede(new Double(i+1));
            record.setLongitude(new Double(i+10));
            records.add(record);
        }
        
        String fieldName = "longitude";
        String getterName = "getLongitude";
        
        Double max = StatisticsUtil.max(records, fieldName, Double.class);
        
        System.out.println(max);
        
        max = StatisticsUtil.max(records, getterName, Double.class);
        
        System.out.println(max);
        
        Double min = StatisticsUtil.min(records, fieldName, Double.class);
        
        System.out.println(min);
        
        min = StatisticsUtil.min(records, getterName, Double.class);
        
        System.out.println(min);
        
        Double sum = StatisticsUtil.sum(records, fieldName, Double.class);
        
        System.out.println(sum);
        
        min = StatisticsUtil.sum(records, getterName, Double.class);
        
        System.out.println(sum);
        
        Double avg = StatisticsUtil.avg(records, fieldName, Double.class);
        
        System.out.println(avg);
        
        min = StatisticsUtil.avg(records, getterName, Double.class);
        
        System.out.println(avg);
        
        //System.out.println(PDLAppService.getAverateGpsRecord(records));
        
    }

}
