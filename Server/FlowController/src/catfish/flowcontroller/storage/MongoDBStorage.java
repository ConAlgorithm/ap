package catfish.flowcontroller.storage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;

import catfish.base.Logger;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.mongo.MongoDBConfig;

public class MongoDBStorage extends MongoDBConfig implements Storage{
	
    private static int retryCount = 2;

    public MongoDBStorage() {
        try {
            this.initialize();
        } catch (Exception e) {
            Logger.get().fatal("Cannot init the Mongo Client for flow controller", e);
        }
    }

    private List<Application> query(DBObject queryCondition, BasicDBObject fields) {
        Gson gson = new Gson();
        DBCollection currentCollection = this.getCurrentCollection();
        List<DBObject> list = currentCollection.find(queryCondition, fields).toArray();
        List<Application> result = new ArrayList<Application>();
        for (DBObject dbObject : list) {
            if (dbObject == null) {
                continue;
            }

            Application app = gson.fromJson(dbObject.toString(), Application.class);
            result.add(app);
        }
        return result;
    }

    @Override
    public List<Application> queryPendingApps(String workflow, List<String> appfields) {
        try {
            BasicDBObject fields = null;
            if (appfields != null) {
                fields = new BasicDBObject();
                for (String appfield : appfields) {
                    fields.append(appfield, true);
                }
            }

            DBObject queryCondition = new BasicDBObject();
            queryCondition.put("workflow", workflow);

            DBObject appAgreed = new BasicDBObject("jobName", "ApplicationAgreed");
            queryCondition.put("messages", new BasicDBObject(QueryOperators.ELEM_MATCH, appAgreed));
            Calendar rightNow = Calendar.getInstance();
            rightNow.set(rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH), rightNow.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            long starttime = rightNow.getTime().getTime();
            queryCondition.put("startTime", new BasicDBObject(QueryOperators.GT, starttime));
            queryCondition.put("endTime", new BasicDBObject(QueryOperators.LT, 0));

            return query(queryCondition, fields);
        } catch (Exception e) {
            Logger.get().error("load applications flow error", e);
            return null;
        }
    }

    @Override
    public int save(Application data) {
        String appId = data.appId;
        Gson gson = new Gson();
        data._id = appId.toUpperCase();
        data.update_time = new Date();
        BasicDBObject fields = new BasicDBObject().append("_id", true);
        try {
            DBObject newDataObject = (DBObject) JSON.parse(gson.toJson(data));
            DBCollection currentCollection = this.getCurrentCollection();
            DBObject result = currentCollection.findOne(new BasicDBObject("_id", data._id), fields);
            if (result == null) {
            	//插入失败，隔2秒重试一次，重试2次
            	for (int i = 0; i <= retryCount; i++) {
                    try {
                        currentCollection.insert(newDataObject);
                        Logger.get().info("appflow insert success");
                        return 1;
                    } catch (Exception e) {
                        if(i==2){
                            Logger.get().error(" appflow upsert still error: " + new Gson().toJson(newDataObject),e);
                        }
                        Logger.get().warn("appflow insert need retry " + i+1);
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e1) {
                            Logger.get().error("appflow TimeUnit has error ",e1);
                        }
                    }
                }
            } else {
                currentCollection.update(new BasicDBObject("_id", data._id), newDataObject);
            }
        } catch (Exception e) {
            Logger.get().error(String.format("save application flow of appid: %s error", appId), e);
        }

        return 1;
    }

    @Override
    public Application load(String appId) {
        Gson gson = new Gson();
        try {
            DBCollection currentCollection = this.getCurrentCollection();
            DBObject result = currentCollection.findOne(new BasicDBObject("_id", appId.toUpperCase()));
            if (result == null) {
                return null;
            }
            return gson.fromJson(result.toString(), Application.class);
        } catch (Exception e) {
            Logger.get().error(String.format("load application flow of appid: %s error", appId), e);
            return null;
        }
    }

    @Override
    public List<Application> load(List<String> appIds, List<String> appfields) {
        try {
            BasicDBObject fields = null;
            if (appfields != null) {
                fields = new BasicDBObject();
                for (String appfield : appfields) {
                    fields.append(appfield, true);
                }
            }

            String[] ids = new String[appIds.size()];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = appIds.get(i).toUpperCase();
            }
            BasicDBObject query = new BasicDBObject(QueryOperators.IN, ids);
            DBObject queryCondition = new BasicDBObject("_id", query);
            return query(queryCondition, fields);
        } catch (Exception e) {
            Logger.get().error("load applications flow error", e);
            return null;
        }
    }
}
