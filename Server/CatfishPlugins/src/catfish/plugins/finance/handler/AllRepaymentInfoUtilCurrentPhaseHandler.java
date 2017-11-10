//package catfish.plugins.finance.handler;
//
//import java.math.BigDecimal;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
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
//import catfish.base.exception.CatfishException;
//import catfish.plugins.finance.object.JsonDataBuilder;
//import catfish.plugins.finance.object.Status;
//
//class TempModel {
//    public int numInstalment;             // 期数
//    public BigDecimal total;              // 金额总额
//}
//
//class RepaymentInfo {
//     public BigDecimal principalTotal;    // 本金总额
//     public BigDecimal interestTotal;     // 利息总额
//     public BigDecimal penaltyTotal;      // 罚金总额
//     public BigDecimal serviceFeeTotal;   // 服务费总额
//     public int overDueTotal;             //逾期总天数
//     public int numInstalment;            //期数
//}
//
//class TempModelMapper implements RowMapper<TempModel>{
//
//	@Override
//    public TempModel mapRow(ResultSet result, int rowIndex) throws SQLException {
//    	TempModel ts = new TempModel();
//        ts.total = result.getBigDecimal(1);
//        ts.numInstalment = result.getInt(2);
//        return ts;
//    } 
//}
//
//public class AllRepaymentInfoUtilCurrentPhaseHandler implements IHandler{
//    private String appId;
//
//    private static final String sqlToDecideAppIdExistOrNot = 
//    "SELECT COUNT(*) FROM InstalmentObjects WHERE AppId = :appId ";
//    
//    // 期数 
//    private static final String sqlQueryCurrentPhase = 
//    "SELECT  Max(NumInstalment) AS numInstalment FROM InstalmentObjects WHERE AppId = :appId AND DateDue <= GETDATE() ";
//    // 本金  + 期数
//    private static final String sqlQueryPrincipal = 
//    "SELECT SUM(Outstanding), NumInstalment FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '10' AND NumInstalment <= :currentNumInstalment GROUP BY NumInstalment";
//    // 利息  + 期数
//    private static final String sqlQueryInterest = 
//    "SELECT SUM(Outstanding), NumInstalment FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '50' AND NumInstalment <= :currentNumInstalment GROUP BY NumInstalment ";
//    // 服务费  + 期数
//    private static final String sqlQueryFee = 
//    "SELECT SUM(Outstanding), NumInstalment FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '100' AND NumInstalment <= :currentNumInstalment GROUP BY NumInstalment ";
//    //罚金 + 期数
//    private static final String sqlQueryPenalty =
//    "SELECT SUM(Outstanding), NumInstalment FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '150' AND NumInstalment <= :currentNumInstalment AND Outstanding > 0 GROUP BY NumInstalment ";
//    //逾期总天数
//    private static final String sqlQueryTotalOverDue = 
//    "SELECT COUNT(*) FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '150' AND Outstanding > 0 GROUP BY NumInstalment ";
//    
//    public AllRepaymentInfoUtilCurrentPhaseHandler(String appId) {
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
//    	int currentNumInstalment = DatabaseApi.querySingleInteger(sqlQueryCurrentPhase, 
//    			new MapBuilder<String, Object>().add("appId", appId).build());
//    	
//        Map<String, Object> params = new MapBuilder<String, Object>().add("appId", appId).add("currentNumInstalment", currentNumInstalment).build();
//
//
//        List<TempModel> listOFPrincipal = DatabaseApi.queryMultipleResults(sqlQueryPrincipal, params, new TempModelMapper());
//        List<TempModel> listOFInterest = DatabaseApi.queryMultipleResults(sqlQueryInterest, params, new TempModelMapper());
//        List<TempModel> listOFFee = DatabaseApi.queryMultipleResults(sqlQueryFee, params, new TempModelMapper());
//        List<TempModel> listOFPenalty = DatabaseApi.queryMultipleResults(sqlQueryPenalty, params, new TempModelMapper());
//        
//        @SuppressWarnings("unchecked")
//		List<Integer> listOFOverDueTotal = DatabaseApi.queryMultipleResults(sqlQueryTotalOverDue, params, new RowMapper(){
//
//			@Override
//			public Object mapRow(ResultSet result, int rowIndex) throws SQLException {
//				Integer totalOverDue = result.getInt(1);
//				return totalOverDue;
//			}
//        });
//        
//        List<RepaymentInfo> list = new ArrayList<RepaymentInfo>();
//        for(int i = 0; i < listOFPrincipal.size(); i++){
//        	RepaymentInfo info = new RepaymentInfo();
//        	info.principalTotal = listOFPrincipal.get(i).total;
//        	info.interestTotal = listOFInterest.get(i).total;
//        	info.serviceFeeTotal = listOFFee.get(i).total;
//        	
//        	info.penaltyTotal = new BigDecimal(0);
//        	info.overDueTotal = 0;
//        	//存在逾期的分期，则重写罚金总额以及逾期总天数
//        	for(int j = 0; j < listOFPenalty.size(); j++){
//        		if(listOFPrincipal.get(i).numInstalment == listOFPenalty.get(j).numInstalment){
//        			info.penaltyTotal = listOFPenalty.get(j).total;
//        			info.overDueTotal = listOFOverDueTotal.get(j).intValue();
//        		}
//        	}
//        	
//        	info.numInstalment = listOFPrincipal.get(i).numInstalment;
//        	
//        	list.add(info);
//        }
//        
//        
//       return new Gson().toJson(list);
//    }
//}
//
