package catfish.flowcontroller.storage;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.flowcontroller.mongo.MongoDBConfig;
import catfish.flowcontroller.ui.Layout;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class FlowUIStorage extends MongoDBConfig{
	
	private static final String KEY = "workflow";
	
	private static FlowUIStorage storage = new FlowUIStorage();
	
	private Map<String, Layout> layoutCache = new HashMap<>();
	
	public static FlowUIStorage Instance()
	{
		return storage;
	}
	
	private FlowUIStorage(){
        try{
            this.initialize();
        } catch (Exception e) {
            Logger.get().fatal("Cannot init the Mongo Client for flow UI", e);
        }
    }
    
    @Override
    protected String mongoCollectionName() {
    	return "flowui";
    }

//    @Override
//    protected String mongoHistoryCollectionName() {
//         return String.format(
//                    "%s_flowuihistory", StartupConfig.get("catfish.mongo.collectionprefix"));
//    }   
//    
    public synchronized void saveLayout(Layout layout)
    {	
    	String workflow = layout.workflow;
        try{
        	DBObject obj =(DBObject) JSON.parse(new Gson().toJson(layout));
            DBCollection currentCollection = this.getCurrentCollection();
            DBObject result = currentCollection.findOne(new BasicDBObject(KEY, workflow));
            if (result == null) {
                currentCollection.insert(obj);
            } else {
                currentCollection.update(new BasicDBObject(KEY, workflow), obj);
            }
            layoutCache.put(workflow, layout);
        } catch (Exception e) {
            Logger.get().error(String.format("save Flow UI of %s error", workflow), e);
        }
    }
    
    public Layout loadLayout(String workflow)
    {
    	if(workflow == null)
    		return null;
    	try{
    		if(layoutCache.get(workflow) == null)
        	{
        		BasicDBObject query = new BasicDBObject();
        		query.put(KEY, workflow);
        		DBCollection currentCollection = this.getCurrentCollection();
                DBObject result = currentCollection.findOne(query);
                if(result == null)
                	return null;
                layoutCache.put(workflow, new Gson().fromJson(result.toString(), Layout.class));
        	}
    	}catch(Exception e)
    	{
    		Logger.get().error(String.format("get Flow UI of %s error", workflow), e);
    	}
    	return layoutCache.get(workflow);
    }
}
