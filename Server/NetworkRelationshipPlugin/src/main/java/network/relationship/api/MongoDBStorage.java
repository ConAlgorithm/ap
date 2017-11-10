package network.relationship.api;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import network.relationship.businessdomain.GroupInfo;

public class MongoDBStorage implements IStorage {
        
    private static BigDecimal defaultRateForZeroGroup = new BigDecimal(-1);
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    public void save(String appId, GroupInfo info) {
        if(null == info || StringUtils.isNullOrWhiteSpaces(appId)) {
            Logger.get().error("Input parameters error! appId or GroupInfo error");
            return;
        }
        
        // 当前由于存储于Mongo中的数据类型为int，boolean，string，date，bigDecimal，故下面仅仅支持上述几种类型,同时如果GroupInfo中相应字段为NULL则不存储
        Field[] fields = GroupInfo.class.getDeclaredFields();
        List<AppDerivativeVariable> variables = new ArrayList<AppDerivativeVariable>();
        
        for(Field field : fields) {
            DBField f = field.getDeclaredAnnotation(DBField.class);
            try {
                if(null != f && field.get(info) != null && !StringUtils.isNullOrWhiteSpaces(f.variableName())) {
                    AppDerivativeVariable variable = null;
                    if(field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                        variable = new AppDerivativeVariable(
                            appId, f.variableName(), (int)field.get(info));
                    }
                    else if(field.getType().equals(BigDecimal.class)) {
                        variable = new AppDerivativeVariable(
                            appId, f.variableName(), new BigDecimal(field.get(info).toString()));
                    }
                    else if(field.getType().equals(Date.class)) {
                        Date date = null;
                        try{
                            date = sdf.parse(field.get(info).toString());
                        } catch (Exception ex) {
                            date = new Date();
                            Logger.get().warn("parse date : " + field.get(info).toString() + " error!");
                        }
                        variable = new AppDerivativeVariable(appId, f.variableName(), date);
                    }
                    else if(field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
                        variable = new AppDerivativeVariable(
                            appId, f.variableName(), Boolean.parseBoolean(field.get(info).toString()));
                    }
                    else if(field.getType().equals(String.class)) {
                        variable = new AppDerivativeVariable(
                            appId, f.variableName(), field.get(info).toString());
                    }
                    
                    if(variable != null) {
                        variables.add(variable);
                    } else {
                        Logger.get().warn("Field " + field.getName() + " can't save mongoDB!");
                    }
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch(Exception e) {
                Logger.get().warn("Generate AppDerivativeVariable failed! field : " + field.getName() + " " + e.getMessage());
            }
        }
        try{
            AppDerivativeVariableManager.addVariables(variables);
        } catch(Exception ex) {
            Logger.get().warn("Save mongodb exception!" + ex.getMessage());
        }
    }
}
