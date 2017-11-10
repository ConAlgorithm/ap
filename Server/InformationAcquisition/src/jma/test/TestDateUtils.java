package jma.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jma.util.DateUtils;

public class TestDateUtils {

    private static final int MILLISECONDS_PER_MINUTE = 60*1000;
    
    public static void main(String[] args) {

//        Double d = 10.0;
//        System.out.println(d.intValue());
        
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
      Date d = null;
      try {
          d = sdf.parse("2015-09-02 16:54:25");
      } catch (ParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      long productSelectedOn = d.getTime();
      
      System.out.println("local time : " + d);
      System.out.println("utc time : " + DateUtils.toUTC(d));
      System.out.println("utc time : " + DateUtils.toLocal(d));
     
//      System.out.println(new Date(productSelectedOn - MILLISECONDS_PER_MINUTE));
//      System.out.println(new Date(productSelectedOn + MILLISECONDS_PER_MINUTE));
    }

}
