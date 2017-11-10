//package catfish.plugins.finance.handler;
//
//import java.math.BigDecimal;
//import java.util.Map;
//
//import com.google.gson.Gson;
//
//import catfish.base.Logger;
//import catfish.base.collections.MapBuilder;
//import catfish.base.database.DatabaseApi;
//import catfish.plugins.finance.object.JsonDataBuilder;
//import catfish.plugins.finance.object.Status;
//
//class CurrentPhaseRepaymentInfo {
//     public BigDecimal principal;    // 本金
//     public BigDecimal interest;     // 利息
//     public BigDecimal fee;          // 服务费
//     public int numInstalment;        // 当前期是第几期
//     public boolean whetherOverDue;  //是否逾期
//     public String datedue;            //应还款日
//}
//
//public class CurrentPhaseRepaymentInfoHandler implements IHandler{
//    private String appId;
//    
//    private static final String sqlToDecideAppIdExistOrNot = 
//    "SELECT COUNT(*) FROM InstalmentObjects WHERE AppId = :appId ";
//    
//    // 期数 
//    private static final String sqlQueryCurrentPhase = 
//    "SELECT  Max(NumInstalment) AS numInstalment FROM InstalmentObjects WHERE AppId = :appId AND DateDue <= GETDATE() ";
//    // 本金  
//    private static final String sqlQueryPrincipal = 
//    "SELECT InstalmentValue AS principal FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '10' AND NumInstalment = :numInstalment ";
//    // 利息 
//    private static final String sqlQueryInterest = 
//    "SELECT InstalmentValue AS interest FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '50' AND NumInstalment = :numInstalment ";
//    // 服务费 
//    private static final String sqlQueryFee = 
//    "SELECT InstalmentValue AS fee FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '100' AND NumInstalment = :numInstalment ";
//    // 应还款日
//    private static final String sqlQueryDateDue = 
//    "SELECT DateDue AS datedue FROM InstalmentObjects WHERE AppId = :appId AND InstalmentType = '10' AND NumInstalment = :numInstalment ";
//    //当前期逾期天数统计
//    private static final String sqlQueryOverDueCount = 
//    "SELECT COUNT(*) AS overdueCount FROM InstalmentObjects WHERE AppId = :appId AND Outstanding > 0 AND NumInstalment= :numInstalment ";
// 
//    public CurrentPhaseRepaymentInfoHandler(String appId) {
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
//    	Map<String, Object> params = new MapBuilder<String, Object>().add("appId", appId).add("numInstalment", currentNumInstalment).build();
//    	
//    	BigDecimal principal = DatabaseApi.querySingleDecimal(sqlQueryPrincipal, params);
//    	BigDecimal interest = DatabaseApi.querySingleDecimal(sqlQueryInterest, params);
//    	BigDecimal fee = DatabaseApi.querySingleDecimal(sqlQueryFee, params);
//    	String datedue = DatabaseApi.querySingleString(sqlQueryDateDue, params);
//    	int overdueCount = DatabaseApi.querySingleInteger(sqlQueryOverDueCount, params);
//    	
//    	CurrentPhaseRepaymentInfo info = new CurrentPhaseRepaymentInfo();
//    	info.principal = principal;
//    	info.interest = interest;
//    	info.fee = fee;
//    	info.numInstalment = currentNumInstalment;
//    	
//    	if(overdueCount > 0){
//    		info.whetherOverDue = true;
//    	}else{
//    		info.whetherOverDue = false;
//    	}
//    	info.datedue = datedue;
//    	
//    	return new Gson().toJson(info);
//    }
//}
//
