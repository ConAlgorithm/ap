//package catfish.plugins.finance.handler;
//
//import java.math.BigDecimal;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
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
//class TodayRepaymentModel {
//    public int type;             // 类型
//    public BigDecimal total;     // 总额
//    public int count;            // 记录数目
//}
//
//class TodayRepaymentInfoJson {
//     public BigDecimal principalTotal;    // 本金总额
//     public BigDecimal interestTotal;     // 利息总额
//     public BigDecimal penaltyTotal;      // 罚金总额
//     public BigDecimal serviceFeeTotal;   // 服务费总额
//     public int overDueTotal;             //逾期总天数
//}
//
//public class TodayRepaymentInfoHandler implements IHandler{
//    private String appId;
//    private Date today;
//    
//    private static final String sqlToDecideAppIdExistOrNot = 
//    "SELECT COUNT(*) FROM InstalmentObjects WHERE AppId = :appId ";
//
//    private static final String sqlQueryInstalment = 
//    "select instalmentType, sum(outstanding), count(*) from InstalmentObjects where appId = :appId and datedue <= :datedue and outstanding > 0 group by instalmentType";
//    
//    public TodayRepaymentInfoHandler(String appId, Date today) {
//        super();
//        this.appId = appId;
//        this.today = today;
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
//        Map<String, Object> params = new MapBuilder<String, Object>().add("appId", appId).add("datedue", today).build();
//
//        Logger.get().info(String.format("use appId[%s] datedue[%s] to query", appId, today));
//
//        List<TodayRepaymentModel> list = DatabaseApi.queryMultipleResults(sqlQueryInstalment, params, new RowMapper<TodayRepaymentModel>(){
//            @Override
//            public TodayRepaymentModel mapRow(ResultSet result, int rowIndex) throws SQLException {
//            	TodayRepaymentModel ts = new TodayRepaymentModel();
//                ts.type  = result.getInt(1);
//                ts.total = result.getBigDecimal(2);
//                ts.count = result.getInt(3);
//                return ts;
//            } 
//        });
//
//        TodayRepaymentInfoJson  tsj = new TodayRepaymentInfoJson();
//       list.forEach( li -> {
//           if ( li.type == 10) {   // 本金
//               tsj.principalTotal = li.total;
//           }
//           else if ( li.type == 50) {   // 利息
//               tsj.interestTotal = li.total;
//           }
//           else if ( li.type == 100) {   // 服务费
//               tsj.serviceFeeTotal = li.total;
//           }
//           else if ( li.type == 150) {   
//               tsj.penaltyTotal = li.total;  // 罚金
//               tsj.overDueTotal = li.count;  //逾期总天数
//           }
//       });
//       
//      return new Gson().toJson(tsj);
//    }
//}
//
