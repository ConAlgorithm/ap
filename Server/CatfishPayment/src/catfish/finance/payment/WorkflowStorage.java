package catfish.finance.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.mongo.CatfishMongoClient;
import catfish.flowcontroller.models.Application;

public class WorkflowStorage extends CatfishMongoClient{
    public WorkflowStorage(){
        try{
            this.initialize();
        } catch (Exception e) {
            Logger.get().fatal("Cannot init the Mongo Client for WorkflowStorage", e);
        }
    }
    
    @Override
    protected String mongoCollectionName() {
         return String.format(
                 "%s_paymentworkflow", StartupConfig.get("catfish.mongo.collectionprefix"));
    }

    @Override
    protected String mongoHistoryCollectionName() {
         return String.format(
                    "%s_paymentworkflowhistory", StartupConfig.get("catfish.mongo.collectionprefix"));
    }
    
    public void save(String id, List<HashMap<String, Object>> activityConfigs){
        Gson gson = new Gson();
        Map<String, Object> data = new HashMap<String, Object>();
        String appId = id.toUpperCase();
        data.put("_id", appId);
        data.put("activityConfigs", gson.toJson(activityConfigs));

        BasicDBObject fields = new BasicDBObject().append("_id", true);
        try{
            DBObject newDataObject =(DBObject) JSON.parse(gson.toJson(data));
            
            DBCollection currentCollection = this.getCurrentCollection();
            DBObject result = currentCollection.findOne(new BasicDBObject("_id", appId), fields);
            if (result == null) {
                currentCollection.insert(newDataObject);
            } else {
                currentCollection.update(new BasicDBObject("_id", appId), newDataObject);
            }
            Logger.get().info("save work flow for "+ id);
        } catch (Exception e) {
            Logger.get().error(String.format("save application flow of appid: %s error", appId), e);
        }
    }

    public List<HashMap<String, Object>> load(String appId){
        Gson gson = new Gson();
        try {
            DBCollection currentCollection = this.getCurrentCollection();
            DBObject result = currentCollection.findOne(new BasicDBObject("_id", appId.toUpperCase()));
            if(result == null){
                Logger.get().warn("can not find workflow for "+ appId);
                return null;
            }
            Map<String, Object> data =gson.fromJson(result.toString(), new TypeToken<HashMap<String, Object>>() { }.getType());
            String json = data.get("activityConfigs").toString();
            List<HashMap<String, Object>> activityConfigs = gson.fromJson(json, new TypeToken<List<HashMap<String, Object>>>() { }.getType());
            Logger.get().info(String.format("load workflow for %s done", appId));
            return activityConfigs;
        } catch (Exception e) {
            Logger.get().error(String.format("load application flow of appid: %s error", appId), e);
            return null;
        }
    }
}
