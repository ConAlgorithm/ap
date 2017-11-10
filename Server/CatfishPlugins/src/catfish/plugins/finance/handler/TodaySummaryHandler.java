//package catfish.plugins.finance.handler;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//import java.util.List;
//import catfish.base.Logger;
//import catfish.base.collections.MapBuilder;
//import catfish.base.database.DatabaseApi;
//import catfish.plugins.finance.object.JsonDataBuilder;
//import catfish.plugins.finance.object.Status;
//import java.math.BigDecimal;
//import org.springframework.jdbc.core.RowMapper; 
//import org.springframework.jdbc.core.RowMapper; 
//import java.sql.SQLException;
//import java.sql.ResultSet;
//
//
//class TodaySummaryModel {
//    public int type;             // 类型
//    public BigDecimal total;     // 总额
//}
//
//class TodaySummaryJson {
//     public BigDecimal principalTotal;    // 本金总额
//     public BigDecimal interestTotal;     // 利息总额
//     public BigDecimal penaltyTotal;      // 罚金总额
//     public BigDecimal serviceFeeTotal;   // 服务费总额
//}
//
//public class TodaySummaryHandler implements IHandler{
//    private String appId;
//    private Date today;    
//
//    private static final String sqlQueryInstalment = 
//    "select instalmentType, sum(outstanding) from InstalmentObjects where appId = :appId and datedue <= :datedue and outstanding > 0 group by instalmentType";
//
//    public TodaySummaryHandler(String appId, Date today) {
//        super();
//        this.appId = appId;
//        this.today = today;
//    }
//
//
//    @Override
//    public String handle() {
//      
//        Map<String, Object> params = new MapBuilder<String, Object>().add("appId", appId).add("datedue", today).build();
//
//        Logger.get().info(String.format("use appId[%s] datedue[%s] to query", appId, today));
//
//        List<TodaySummaryModel> list = DatabaseApi.queryMultipleResults(sqlQueryInstalment, params, new RowMapper<TodaySummaryModel>(){
//            @Override
//            public TodaySummaryModel mapRow(ResultSet result, int rowIndex) throws SQLException {
//                TodaySummaryModel ts = new TodaySummaryModel();
//                ts.type  = result.getInt(1);
//                ts.total = result.getBigDecimal(2);
//                return ts;
//            } 
//        });
//
//       TodaySummaryJson  tsj = new TodaySummaryJson();
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
//           else if ( li.type == 150) {   // 罚金
//               tsj.penaltyTotal = li.total;
//           }
//       });
//       return JsonDataBuilder.build(Status.SUCCESS, "success", tsj).toString();
//    }
//}
//
