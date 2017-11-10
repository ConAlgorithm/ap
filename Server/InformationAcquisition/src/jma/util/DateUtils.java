package jma.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date toUTC(Date localDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(localDate);
        
        //2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        //3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        //4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        
        return new Date(cal.getTimeInMillis());
    }
    
    public static Date toLocal(Date utcDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(utcDate);
        
        //2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        //3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        //4、从utc时间里 + 这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, zoneOffset + dstOffset);
        
        return new Date(cal.getTimeInMillis());
    }
}
