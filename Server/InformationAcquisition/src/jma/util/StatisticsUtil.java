package jma.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class StatisticsUtil {
    
    private static <T extends Number, M> T getValue(M m, String name, Class<T> fieldClz) {
        if(name.startsWith("get")) {
            return getValueByMethod(m, name, fieldClz);
        }
        
        return getValueByField(m, name, fieldClz);
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends Number, M> T getValueByMethod(M m, String getterName, Class<T> fieldClz) {
        
        try {
            Method f = m.getClass().getMethod(getterName);
            f.setAccessible(true);
            Object val = f.invoke(m);
            
            if(val != null && val.getClass().isAssignableFrom(fieldClz))
            {
                return (T) val;
            }
            
            return null;
            
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null; 
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends Number, M> T getValueByField(M m, String fieldName, Class<T> fieldClz) {
        
        try {
            Field f = m.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            Object val = f.get(m);
            
            if(val != null && val.getClass().isAssignableFrom(fieldClz))
            {
                return (T) val;
            }
            
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null; 
    }
    
    public static <T extends Number, M> T max(List<M> dataList, String fieldName, Class<T> fieldClz) {
        if(dataList == null || dataList.isEmpty()) {
            return null;
        }

        M maxM = dataList.get(0);
        T maxT = getValue(maxM, fieldName, fieldClz);
        
        for (int i = 0; i < dataList.size(); i++) {
            M currentM = dataList.get(i);
            T currentT = getValue(currentM, fieldName, fieldClz);
            
            if(currentT == null || maxT == null) {
                continue;
            }
            
            if(currentT.doubleValue() > maxT.doubleValue()) {
                maxM = currentM;
                maxT = currentT;
            }
        }
        
        return maxT;
    }
    
    public static <T extends Number, M> T min(List<M> dataList, String fieldName, Class<T> fieldClz) {
        if(dataList == null || dataList.isEmpty()) {
            return null;
        }

        M minM = dataList.get(0);
        T minT = getValue(minM, fieldName, fieldClz);
        
        for (int i = 0; i < dataList.size(); i++) {
            M currentM = dataList.get(i);
            T currentT = getValue(currentM, fieldName, fieldClz);
            
            if(currentT == null || minT == null) {
                continue;
            }
            
            if(currentT.doubleValue() < minT.doubleValue()) {
                minM = currentM;
                minT = currentT;
            }
        }
        
        return minT;
    }
    
    public static <T extends Number, M> Double sum(List<M> dataList, String fieldName, Class<T> fieldClz) {
        if(dataList == null || dataList.isEmpty()) {
            return null;
        }
        
        double sum = 0;
        for (int i = 0; i < dataList.size(); i++) {
            T currentValue = getValue(dataList.get(i), fieldName, fieldClz);
            if(currentValue == null) {
                return null;
            }
            sum += currentValue.doubleValue();
        }
        
        return sum;
    }
    
    public static <T extends Number, M> Double avg(List<M> dataList, String fieldName, Class<T> fieldClz) {
        if(dataList == null || dataList.isEmpty()) {
            return null;
        }
        
        Double sum = sum(dataList, fieldName, fieldClz);
        
        if(sum == null) {
            return null;
        }
        
        return sum / dataList.size();
    }
    
    public static <T extends Number> T max(List<T> dataList) {
        if(dataList == null || dataList.isEmpty()) {
            return null;
        }
        
        T max = dataList.get(0);
        
        for (int i = 0; i < dataList.size(); i++) {
            T current = dataList.get(i);
            
            if(current.doubleValue() > max.doubleValue()) {
                max = current;
            }
        }
        
        return max;
    }
    
    public static <T extends Number> T min(List<T> dataList) {
        if(dataList == null || dataList.isEmpty()) {
            return null;
        }
        
        T min = dataList.get(0);
        
        for (int i = 0; i < dataList.size(); i++) {
            T current = dataList.get(i);
            
            if(current.doubleValue() < min.doubleValue()) {
                min = current;
            }
        }
        
        return min;
    }
    
    public static <T extends Number> Double sum(List<T> dataList) {
        if(dataList == null || dataList.isEmpty()) {
            return null;
        }
        
        double sum = 0;
        for (int i = 0; i < dataList.size(); i++) {
            sum += dataList.get(i).doubleValue();
        }
        
        return sum;
    }
    
    public static <T extends Number> Double avg(List<T> dataList) {
        if(dataList == null || dataList.isEmpty()) {
            return null;
        }
        
        return sum(dataList)/dataList.size();
    }
    
    public static <T extends Number> int biggerCount(List<T> dataList, double threshold) {
        if(dataList == null || dataList.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        for (int i = 0; i < dataList.size(); i++) {
            if(dataList.get(i).doubleValue() > threshold) {
                count++;
            };
        }
        
        return count;
    }
}
