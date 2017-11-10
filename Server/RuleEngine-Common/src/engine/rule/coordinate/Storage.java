/**
 * Created by Felton 2016年4月12日
 */
package engine.rule.coordinate;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.mongo.CatfishMongoClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author felton
 *
 */
public class Storage extends CatfishMongoClient{

	public Storage(){
        try{
            this.initialize();
        } catch (Exception e) {
            Logger.get().fatal("Cannot init the Mongo Client for RuleEngine", e);
        }
    }
	
	@Override
	protected String mongoCollectionName() {
		 return String.format(
	             "%s_ruleengine", StartupConfig.get("catfish.mongo.collectionprefix"));
	}

	@Override
	protected String mongoHistoryCollectionName() {
		 return String.format(
		            "%s_ruleenginestoragehistory", StartupConfig.get("catfish.mongo.collectionprefix"));
	}	
	
	public void save(Workflow data)
	{
		String appId = data.getAppId();
        Gson gson = this.getFormattedGson();
        data.set_id(appId.toUpperCase());
        BasicDBObject fields = new BasicDBObject().append("_id", true);
        try{
            DBObject newDataObject =(DBObject) JSON.parse(gson.toJson(data));
            DBCollection currentCollection = this.getCurrentCollection();
            DBObject result = currentCollection.findOne(new BasicDBObject("_id", data.get_id()), fields);
            if (result == null) {
                currentCollection.insert(newDataObject);
            } else {
                currentCollection.update(new BasicDBObject("_id", data.get_id()), newDataObject);
            }
        } catch (Exception e) {
            Logger.get().error(String.format("save application flow of appid: %s error", appId), e);
        }
	}
	
	public Workflow load(String appId)
	{
		Gson gson = this.getFormattedGson();
        try {
            DBCollection currentCollection = this.getCurrentCollection();
            DBObject result = currentCollection.findOne(new BasicDBObject("_id", appId.toUpperCase()));
            if(result == null){
                return null;
            }
            return gson.fromJson(result.toString(), Workflow.class);
        } catch (Exception e) {
            Logger.get().error(String.format("load application flow of appid: %s error", appId), e);
            return null;
        }
	}
	private Gson getFormattedGson()
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson;
	}
}
