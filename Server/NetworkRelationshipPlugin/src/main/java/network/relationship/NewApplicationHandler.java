package network.relationship;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.queue.QueueMessager;
import network.relationship.api.GraphApi;
import network.relationship.api.IStorage;
import network.relationship.api.MongoDBStorage;
import network.relationship.api.StatisticsApi;
import network.relationship.businessdomain.GroupInfo;
import network.relationship.domain.Application;
import network.relationship.domain.User;
import network.relationship.graphupdator.ApplicationGraphUpdator;
import network.relationship.graphupdator.ApplicationGraphUpdator.ApplicationRelatedInfo;

public class NewApplicationHandler implements IMessageHandler, Runnable {

	private IStorage storage = new MongoDBStorage();
	private String data;
	
	public NewApplicationHandler(String data){
		this.data = data;
	}
	
	@Override
	public void handle(String msg) {
	    QueueMessager messager = QueueMessager.fromString(msg);
	    
	      String appId = messager.getAppId();
	      
	      if (appId == null || appId.isEmpty()) {
	          Logger.get().warn(
	                  "Null or empty appId from message : " + messager );
	          return ;
	      }

	      handleAppId(appId, this.storage);	    
	}
	
	public static void handleAppId(String appId, IStorage storage) {
		
//		if(ApplicationGraphUpdator.exists(Application.class, new Application(appId.toLowerCase()))
//		         || ApplicationGraphUpdator.exists(Application.class, new Application(appId.toUpperCase()))){
//		    	  Logger.get().warn(String.format("AppId:%s already exists in the Neo4j. Ignore...", appId));
//		    	  return;
//		      }
		      
        ApplicationRelatedInfo appDirectGraph = ApplicationGraphUpdator.createApplicationDirectGraph(appId);
        if (appDirectGraph == null) {
            Logger.get().warn("can not get ApplicationDirectGraph from AppId : " + appId);
            return;
        }
		ApplicationGraphUpdator applicationGraphUpdator = new ApplicationGraphUpdator(appId);
	    long id = applicationGraphUpdator.update(appDirectGraph);

	    if (isQueryAllowed()) {
	    	List<Application> apps = new LinkedList<Application>();
	    	List<User> users = new LinkedList<User>();
			applicationGraphUpdator.getAppNodeList(new Application(),apps);
			applicationGraphUpdator.getUserNodeList(new User(),users);
	    	//GraphApi.queryConnectedNodes(appId, apps, users);
	    	
	    	//Remove the current user
	    	for (int i = 0; i < users.size(); i++) {
				if(users.get(i).getIdNumber().equalsIgnoreCase(appDirectGraph.user.getIdNumber())){
					users.remove(i);
					--i;
				}
			}
	    	
	    	//List<Application> apps = GraphApi.getAllConnectedApps(appId);
	        //List<Application> apps = GraphApi.getConnectedApps(appId);
	        Logger.get().info(String.format("apps count:  %d", apps.size()));
	        Logger.get().info("Connected apps : " + new Gson().toJson(apps));
	        
	        //List<User> users = GraphApi.getConnectedUsers(appId);
	        Logger.get().info(String.format("users count:  %d", users.size()));
	        Logger.get().info("Connected users : " + new Gson().toJson(users));	        
	        
	        GroupInfo groupInfo = new GroupInfo();

	        if(apps.isEmpty() || users.isEmpty()) {
	            Logger.get().info("Group app is empty! current Appid is " + appId);
	            return;
	        }

	        // Filter current application
	        List<Application> appList = apps.parallelStream()
                    .filter( x -> x != null && x.appId != appId ).collect(Collectors.toList());
	        
	        try{
	            StatisticsApi.getStatisticsFromApps(appList, groupInfo);
	            StatisticsApi.getPaymentStatisticsFromApps(appList, groupInfo);
	        } catch(Exception ex) {
	            Logger.get().error("Generate statistice error!", ex);
	            return;
	        }
	        Logger.get().info("GroupInfo : " + new Gson().toJson(groupInfo));
	        storage.save(appId, groupInfo);
	    }
	}
	
	private static boolean isQueryAllowed() {
	    return StartupConfig
	            .get("catfish.graph.query.switch", "off")
	            .equalsIgnoreCase("on");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.handle(this.data);
	}
}
