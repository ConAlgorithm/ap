package catfish.flowcontroller.mongo;

import catfish.base.StartupConfig;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by zhujx on 2017/4/25.
 */
public class MongoDBConfig {
    private DBCollection currentCollection;
    private MongoClient client = null;

    public MongoDBConfig(){

    }

    protected String mongoUrls()
    {
        return StartupConfig.get("database.mongo.urls");
    }

    protected String mongoUserName()
    {
        return StartupConfig.get("database.mongo.username");
    }

    protected String mongoPassword()
    {
        return StartupConfig.get("database.mongo.password");
    }

    protected String mongoDBName()
    {
        return StartupConfig.get("database.mongo.databasename");
    }
    
    protected String mongoAuthDB()
    {
        return StartupConfig.get("database.mongo.authdb");
    }

    protected String mongoReplsetName()
    {
        return StartupConfig.get("database.mongo.replsetname");
    }

    protected int maxWaitTime()
    {
        //unit: milliseconds, default value is 2 minutes
        return StartupConfig.getAsInt("mongo.maxwaittime", 3 * 60 * 1000);
    }

    protected int connectTimeout()
    {
        //unit: milliseconds, default value is 10s
        return StartupConfig.getAsInt("mongo.connecttimeout", 30 * 1000);
    }

    protected String mongoCollectionName()
    {

        return "appflow";
    }


    private List<ServerAddress> getServers() throws UnknownHostException {
        String mongoConnections = mongoUrls();
        if(mongoConnections == null || mongoConnections.length() ==0){
            return null;
        }
        String[] servers = mongoConnections.split(",");
        List<ServerAddress> serverList = new ArrayList<ServerAddress>();
        for(String server: servers){
            String[] items = server.trim().split(":");
            String ip = items[0];
            int port = Integer.parseInt(items[1]);
            serverList.add(new ServerAddress(ip, port));
        }
        return serverList;
    }

    @SuppressWarnings("deprecation")
	public void initialize() {
        List<MongoCredential> credentials = Arrays.asList(MongoCredential
                .createCredential(mongoUserName(), mongoAuthDB(),
                        mongoPassword().toCharArray()));
        try {
        	MongoClientOptions builder = null;
            if(null == mongoReplsetName() || "".equals(mongoReplsetName())){
                builder = MongoClientOptions.builder().maxWaitTime(maxWaitTime()).connectTimeout(connectTimeout()).build();
            }else{
                builder = MongoClientOptions.builder().requiredReplicaSetName(mongoReplsetName()).maxWaitTime(maxWaitTime()).connectTimeout(connectTimeout()).build();
            }
            List<ServerAddress> servers = getServers();
            if (servers != null) {
                client = new MongoClient(servers, credentials, builder);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        currentCollection = client.getDB(mongoDBName()).getCollection(mongoCollectionName());

    }
    public DBCollection getCurrentCollection() {
        return currentCollection;
    }

    @SuppressWarnings("deprecation")
	public Set<String> getCollections(){
        return client.getDB(mongoDBName()).getCollectionNames();
    }

    @SuppressWarnings("deprecation")
	public DBCollection getCollection(String collectionName){
        return client.getDB(mongoDBName()).getCollection(collectionName);
    }

    public void setCurrentCollection(DBCollection currentCollection) {
        this.currentCollection = currentCollection;
    }
}
