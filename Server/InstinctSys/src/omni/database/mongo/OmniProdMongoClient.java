package omni.database.mongo;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;

import catfish.base.Logger;

/**
 * This class defines query methods to use with Mongo client.
 * 
 * @author Guoqing
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class OmniProdMongoClient extends OmniMongoClient 
{
	
	public OmniProdMongoClient()
	{
		try
		{
			this.initialize();
        } 	
		catch (Exception e) 
		{
            	Logger.get().fatal("Cannot init the Mongo Client for Prod", e);
        }
    }
    

    public final DerivativeVariables load(String appId)
    {
    	Gson gson = new Gson();
        try 
        {
        	DBCollection currentCollection = this.getCurrentCollection();
            DBObject params = new BasicDBObject("_id", appId.toUpperCase());
            DBObject result = currentCollection.findOne(params);
            if (result == null)
            {
                return null;
            }
            return gson.fromJson(result.toString(), DerivativeVariables.class);
        } 	
        catch (Exception e) 
        {
        	e.printStackTrace();
        	return null;
        }
    }
    
    public final DerivativeVariables queryOne(DBObject queryCondition)
    {
    	Gson gson = new Gson();
        try 
        {
        	DBCollection currentCollection = this.getCurrentCollection();
            DBObject result = currentCollection.findOne(queryCondition);
            if (result == null)
            {
                return null;
            }
            return gson.fromJson(result.toString(), DerivativeVariables.class);
        } 	
        catch (Exception e) 
        {
        	e.printStackTrace();
        	return null;
        }
    }
    
    public final HashMap<String, DerivativeVariables> load(List<String> appIds)
    {
    	try 
    	{
    		BasicDBObject fields = null;

            String[] ids = new String[appIds.size()];
            for (int i = 0; i < ids.length; i++)
            {
                ids[i] = appIds.get(i).toUpperCase();
            }
            BasicDBObject query = new BasicDBObject(QueryOperators.IN, ids);
            DBObject queryCondition = new BasicDBObject("_id", query);
            return query(queryCondition, fields);
        } 	
    	catch (Exception e) 
    	{
            Logger.get().error("load DerivativeVariables error", e);
            return null;
        }
    }
    
    public final HashMap<String, DerivativeVariables> load(List<String> appIds, List<String> appfields)
    {
        try 
        {
            BasicDBObject fields = null;
            if (appfields != null)
            {
                fields = new BasicDBObject();
                for (String appfield : appfields)
                {
                    fields.append(appfield, true);
                }
            }

            String[] ids = new String[appIds.size()];
            for (int i = 0; i < ids.length; i++)
            {
                ids[i] = appIds.get(i).toUpperCase();
            }
            BasicDBObject query = new BasicDBObject(QueryOperators.IN, ids);
            DBObject queryCondition = new BasicDBObject("_id", query);
            return query(queryCondition, fields);
        } 	
        catch (Exception e) 
        {
            Logger.get().error("load applications flow error", e);
            return null;
        }
    }
    
    public final HashMap<String, DerivativeVariables> query(DBObject queryCondition, BasicDBObject fields)
    {
        Gson gson = new Gson();
        DBCollection currentCollection = this.getCurrentCollection();
        DBCursor dbCursor = currentCollection.find(queryCondition, fields);
        
        HashMap<String, DerivativeVariables> result = new  HashMap<String, DerivativeVariables>();

        while (dbCursor.hasNext())
        {
        	DBObject dbObject = dbCursor.next(); 
        	if (dbObject == null)
        	{
        		continue;
            }
            DerivativeVariables app = gson.fromJson(dbObject.toString(), DerivativeVariables.class);
            result.put(app._id, app);
        }
        return result;
    }
    
}

