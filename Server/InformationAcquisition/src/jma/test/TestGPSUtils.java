package jma.test;

import jma.models.GPSRecord;
import jma.util.GPSUtils;

public class TestGPSUtils {

    public static void main(String[] args) {
        GPSRecord a = new GPSRecord();
        a.setLatituede(24.118418);
        a.setLongitude(117.60972);
        
        GPSRecord b = new GPSRecord();
        b.setLatituede(24.11931);
        b.setLongitude(117.61113);
        
        System.out.println(GPSUtils.getDistance(a, b));
        
        
        
    }

}
