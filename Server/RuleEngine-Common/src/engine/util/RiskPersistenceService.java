package engine.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.httpclient.HttpClientApi;
import engine.rule.coordinate.Workflow;
import engine.rule.domain.app.AddressBook;
import engine.rule.domain.app.ResponseModel;

public class RiskPersistenceService {
    private static final String RISKPERSISTENCEURL = StartupConfig.get("risk.persistence.mongoUrl");
    private static int retryCount = 2;
    public static List<AddressBook> getAddressBook(String userId) {
        if(StringUtils.isNullOrWhiteSpaces(userId)) {
            Logger.get().warn("Get AddressBook useris  is empty!");
            return null;
        }
        try{
            String addressBooksJson = HttpClientApi.get(RISKPERSISTENCEURL + "/addressbook/" + userId);
            ResponseModel<List<AddressBook>> addressBooks =  new Gson().fromJson(addressBooksJson, 
                new TypeToken<ResponseModel<List<AddressBook>>>() {}.getType());
            if(addressBooks.getData() != null && !addressBooks.getData().isEmpty()) {
                return addressBooks.getData();
            }
            return null;
        } catch(RuntimeException ex) {
            Logger.get().error("invoke Risk persistence error!");
            return null;
        }
    }

    public static Workflow getRuleengineCollection(String appId) {
        if(StringUtils.isNullOrWhiteSpaces(appId)) {
            Logger.get().warn("Get ruleengine appid is empty!"+appId);
            return null;
        }
        try{
            String addressBooksJson = HttpClientApi.get(RISKPERSISTENCEURL + "/ruleengine/" + appId);
             
            ResponseModel<Workflow> workflow =  new Gson().fromJson(addressBooksJson, 
                new TypeToken<ResponseModel<Workflow>>() {}.getType());
            if(workflow.getData() != null && workflow.getData().get_id() != null) {
                Workflow a = workflow.getData();
                a.setAppId(a.get_id());
                return workflow.getData();
            }
            return null;
        } catch(RuntimeException ex) {
            Logger.get().error("get data from ruleengine collection error! appId:"+appId);
            return null;
        }
    }
    
    public static void saveToRuleEngine4Workflow(Workflow workflow){ 
        for (int i = 0; i < retryCount; i++) {
            try {
                Map<String, Object> transBean2Map = transBean2Map(workflow);
                HttpClientApi.postJson(RISKPERSISTENCEURL + "/ruleengine/", transBean2Map);
                return;
            } catch (Exception e) {
                if (i == 1) {
                    Logger.get().error("save data to ruleengine collection error! data:" + new Gson().toJson(workflow) + e);
                    return;
                }
                Logger.get().warn("save data to ruleengine collection need retry");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    Logger.get().error("saveToRuleEngine4Workflow timeUnit has error", e1);
                }
            }
        }
    }
    
    public  static <T> void saveToDerivativevariables(Map<String, T> map){ 
        for (int i = 0; i < retryCount; i++) {
            try {
                HttpClientApi.postJson(RISKPERSISTENCEURL + "/derivativevariables/", map);
                return;
            } catch (Exception e) {
                if (i == 1) {
                    Logger.get().error("save data to derivativevariables collection error! data:" + new Gson().toJson(map) + e);
                    return;
                }
                Logger.get().warn("save data to derivativevariables collection need retry");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    Logger.get().error("saveToDerivativevariables timeUnit has error", e1);
                }
            }
        }
    }
    
    public static <T> HashMap getDerivativevariables(String appId){
        String addressBooksJson = HttpClientApi.get(RISKPERSISTENCEURL + "/derivativevariables/" + appId);
        ResponseModel<HashMap> workflow =  new Gson().fromJson(addressBooksJson, 
            new TypeToken<ResponseModel<HashMap>>() {}.getType());
        if(workflow.getData() != null) {
            return workflow.getData();
        }
        return null;
    } 
    
     
 // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> transBean2Map(Object obj) {  
  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
  
        return map;   
    }  
} 
