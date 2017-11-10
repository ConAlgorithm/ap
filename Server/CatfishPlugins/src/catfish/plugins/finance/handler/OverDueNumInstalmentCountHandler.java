//package catfish.plugins.finance.handler;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import com.google.gson.Gson;
//
//import catfish.base.Logger;
//import catfish.base.collections.MapBuilder;
//import catfish.base.database.DatabaseApi;
//import catfish.plugins.finance.object.JsonDataBuilder;
//import catfish.plugins.finance.object.Status;
//
//class OverDueNumInstalmentCountModel{
//	public int count;
//}
//
//public class OverDueNumInstalmentCountHandler implements IHandler{
//    private String appId;
//    
//    private static final String sqlToDecideAppIdExistOrNot = 
//    "SELECT COUNT(*) FROM InstalmentObjects WHERE AppId = :appId ";
//
//    private static final String sqlQueryOverDueNumInstalmentCount = 
//    "select NumInstalment from InstalmentObjects where appId = :appId and outstanding > 0 and "
//    + "numInstalment <= (SELECT  Max(NumInstalment) FROM InstalmentObjects WHERE AppId = :appId AND DateDue <= GETDATE()) group by  NumInstalment ";
//    
//    public OverDueNumInstalmentCountHandler(String appId) {
//        super();
//        this.appId = appId;
//    }
//
//
//    @Override
//    public String handle() {
//    	
//    	int appIdCount = DatabaseApi.querySingleInteger(sqlToDecideAppIdExistOrNot, new MapBuilder<String, Object>().add("appId", appId).build());
//    	
//    	if(appIdCount == 0){
//    		Logger.get().error(String.format("no record for this appId [%s]", appId));
//    		throw new RuntimeException(String.format("no record for this appId [%s]", appId));
//    	}
//      
//        Map<String, Object> params = new MapBuilder<String, Object>().add("appId", appId).build();
//
//        Logger.get().info(String.format("use appId[%s] to query", appId));
//
//        List<Integer> list = DatabaseApi.queryMultipleResults(sqlQueryOverDueNumInstalmentCount, params, new RowMapper<Integer>(){
//            @Override
//            public Integer mapRow(ResultSet result, int rowIndex) throws SQLException {
//            	int count = result.getInt(1);
//            	return count;
//            } 
//        });
//        
//        OverDueNumInstalmentCountModel model = new OverDueNumInstalmentCountModel();
//        model.count = list.size();
//
//        return new Gson().toJson(model);
//    }
//}
//
