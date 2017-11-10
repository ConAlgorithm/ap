package network.relationship.api;

public class GraphApi {
    
//    private static CatfishNeo4jConfiguration config = new CatfishNeo4jConfiguration();
//
//    public static void queryConnectedNodes(String appId, List<Application> apps, List<User> users){
//    	try{
//            Iterable<Application> appResult = config.neo4jTemplate().queryForObjects( Application.class, appQuery, CollectionUtils
//                    .<String, Object> newMapBuilder()
//                    .add("appId", appId).build());
//            appResult.forEach( x -> apps.add(x));
//
//            Iterable<User> userResult = config.neo4jTemplate().queryForObjects( User.class, userQuery, CollectionUtils
//                    .<String, Object> newMapBuilder()
//                    .add("appId", appId).build());
//            userResult.forEach( x -> users.add(x));
//    	} catch (Exception ex) {
//    	    Logger.get().warn("neo4j query list field : " + ex);
//        }
//    }
}