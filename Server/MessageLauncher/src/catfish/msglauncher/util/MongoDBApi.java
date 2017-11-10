package catfish.msglauncher.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.mongo.CatfishMongoClient;
import catfish.msglauncher.message.ShortMessage;
import catfish.msglauncher.model.MessageTemplate;

public class MongoDBApi {
	private static CatfishMongoClient mongoClient;
	
	static{
		mongoClient = new CatfishMongoClient("messagelauncher");
	}
	
	public static void initialize(){
		mongoClient.initialize();
	}
	
	public static void test(){
		mongoClient.getCollection("testcoll").insert(
				new BasicDBObject()
				.append("templateCode", "WITHHOLDING")
				.append("templateId", "123"));
	}
	
	public static List<String> getTempateIdsByTemplateCode(String templateCode){
		List<String> list = new ArrayList<String>();
		
		DBObject obj = mongoClient.getCollection("alitemplate").findOne(
				new BasicDBObject()
				.append("templateCode", templateCode));
		BasicDBList tids = (BasicDBList)obj.get("templateIds");
		for(int i = 0; i < tids.size(); i++){
			list.add((String)tids.get(i));
		}
		return list;
	}
	
	public static void logShortMessage(ShortMessage message, String res){
		mongoClient.getCollection("shortmessagelog").insert(
				new BasicDBObject()
				.append("templateCode", message.getTemplateCode())
				.append("templateId", message.getTemplateId())
				.append("mobile", message.getMobile())
				.append("content", message.getContentJson())
				.append("contentJson", message.getContentJson())
				.append("response", res)
				.append("dateCreated", new Date())
				.append("dateUpdated", new Date()));
	}
	
	public static List<MessageTemplate> findAllMessageTemplates(){
		List<MessageTemplate> list = new ArrayList<MessageTemplate>();
		DBCursor dbCursor = mongoClient.getCollection("messagetemplate").find();
		while(dbCursor.hasNext()){
			DBObject dbObject = dbCursor.next();
			MessageTemplate mt = dbObject2Bean(dbObject, MessageTemplate.class);
			list.add(mt);
		}
		return list;
	}
	
	public static MessageTemplate findMessageTemplateByTemplateCode(String templateCode){
		DBObject dbObject = mongoClient.getCollection("messagetemplate").findOne(new BasicDBObject().append("templateCode", templateCode));
		if(null == dbObject){
			return null;
		}
		return dbObject2Bean(dbObject, MessageTemplate.class);
	}
	
	public static boolean insertMessageTemplate(MessageTemplate messageTemplate){
		WriteResult wr = mongoClient.getCollection("messagetemplate").insert(
				new BasicDBObject().append("templateCode", messageTemplate.getTemplateCode())
					.append("templateId", messageTemplate.getTemplateId())
					.append("name", messageTemplate.getName())
					.append("content", messageTemplate.getContent()));
		return true;
	}
	
	public static boolean updateMessageTemplateContentByTemplateCode(MessageTemplate messageTemplate){
		mongoClient.getCollection("messagetemplate").update(
				new BasicDBObject().append("templateCode", messageTemplate.getTemplateCode()),
				new BasicDBObject().append("$set",new BasicDBObject().append("content", messageTemplate.getContent()))
				);
		
		
		
		return true;
	}
	
	private static <T> T dbObject2Bean(DBObject dbObject, Class<T> c){
		Gson gson = new Gson();
		return (T)gson.fromJson(gson.toJson(dbObject.toMap()),c);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartupConfig.initialize();
		Logger.initialize();
		MongoDBApi.initialize();
//		MongoDBApi.test();
//		System.out.println(MongoDBApi.getTempateIdsByTemplateCode("WITHHOLDING").get(0));
		List<MessageTemplate> list = MongoDBApi.findAllMessageTemplates();
		System.out.println(list.size());
	}

}
