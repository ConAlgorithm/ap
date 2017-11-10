package jma.models;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.joda.time.DateTime;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StringUtils;

public class DistributeStrategyConfigManager {
    private static String                FILE_NAME                 = "distributeStrategy.properties";
    private static Set<DistributeConfig> distributeStrategyConfigs = Collections
        .synchronizedSet(new HashSet<DistributeConfig>() {
                                                                           /**
                                                                            * 
                                                                            */
                                                                           private static final long serialVersionUID = 1L;
                                                                       });

    private static Integer               thresholdValue;

    public static Set<DistributeConfig> getConfigs() {
        if (distributeStrategyConfigs == null || distributeStrategyConfigs.isEmpty()) {
            init();
        }
        return distributeStrategyConfigs;
    }

    public static Integer getThreshold() {
        return thresholdValue;
    }

    private static void init() {
        try {
            SAXReader reader = new SAXReader();
            Document document = null;
            document = reader.read(FILE_NAME);
            thresholdValue = Integer.parseInt(document.getRootElement().elementText("Threshold"));
            @SuppressWarnings("unchecked")
            Iterator<Element> elements = document.getRootElement().elementIterator();
            while (elements.hasNext()) {
                DistributeConfig config = new DistributeConfig();
                Element item = elements.next();
                for (Field field : DistributeConfig.class.getDeclaredFields()) {
                    String value = item.elementText(field.getName());
                    fillObject(config, field, value);
                }

                // 生成的配置进行检查，如果success或者failed的结果字符串中的字符不唯一，则需要进行处理
                if (null != config.getName() || config.getValue() != null) {
                    @SuppressWarnings("unchecked")
                    Map<String, String> successValue = new Gson().fromJson(config.getSuccess(), Map.class);
                    
                    @SuppressWarnings("unchecked")
                    Map<String, String> failedValue = new Gson().fromJson(config.getFailed(), Map.class);
                    
                    if(isUniqueStringForMap(successValue) && isUniqueStringForMap(failedValue)) {
                        distributeStrategyConfigs.add(config);
                    } else {
                        Logger.get().warn("success or failed tag is unique!");
                    }
                }
            }
        } catch (Exception e) {
            Logger.get().error(String.format("Cannot read %s : %s", FILE_NAME, e.getMessage()));
            System.exit(0);
        }
    }

    /*
     * 仅仅能够对单一字段进行填充，不能对自定义对象进行填充
     */
    private static void fillObject(Object obj, Field field, String value) throws Exception {
        if (value == null || "null".equalsIgnoreCase(value))
            return;

        try {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
            Method wM = pd.getWriteMethod();
            Object[] oo = new Object[1];
            Class<?> type = field.getType();
            if (type.equals(String.class)) {
                oo[0] = value;
            } else if (type.equals(int.class) || type.equals(Integer.class)) {
                if (value.length() > 0)
                    oo[0] = Integer.valueOf(value);
            } else if (type.equals(float.class) || type.equals(Float.class)) {
                if (value.length() > 0)
                    oo[0] = Float.valueOf(value);
            } else if (type.equals(double.class) || type.equals(Double.class)) {
                if (value.length() > 0)
                    oo[0] = Double.valueOf(value);
            } else if (type.equals(BigDecimal.class)) {
                if (value.length() > 0)
                    oo[0] = new BigDecimal(Double.valueOf(value));
            } else if (type.equals(Date.class)) {
                Date date = null;
                if (value.length() > 0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        date = sdf.parse(value);
                    } catch (Exception e) {
                        date = DateTime.parse(value).toDate();
                    }
                    oo[0] = date;
                }
            } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
                if (value.length() > 0)
                    oo[0] = Boolean.valueOf(value);
            } else if (type.equals(long.class) || type.equals(Long.class)) {
                if (value.length() > 0)
                    oo[0] = Long.valueOf(value);
            }
            wM.invoke(obj, oo);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    private static boolean isUniqueStringForMap( Map<String, String> map) {
        if(null == map) {
            return true;
        }
        for(String value : map.values()) {
            if(!isUniqueChars(value)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isUniqueChars(String str) {
        int checker = 0;
        for(int i = 0; i < str.length(); i++ ) {
            int val = str.charAt(i) - 'a';
            if((checker & ( 1 << val)) > 0 ) {
                return false;
            } 
            checker |= ( 1 << val);
        }
        return true;
    }
}
