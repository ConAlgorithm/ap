package catfish.plugins.finance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.dao.InstalmentDao;
import catfish.base.business.object.InstalmentObject;
import catfish.framework.restful.IRESTfulService;
//import catfish.plugins.finance.handler.AllRepaymentInfoUtilCurrentPhaseHandler;
//import catfish.plugins.finance.handler.CurrentPhaseRepaymentInfoHandler;
import catfish.plugins.finance.handler.LastPaybackDateHandler;
//import catfish.plugins.finance.handler.OverDueNumInstalmentCountHandler;
import catfish.plugins.finance.handler.PenaltyPerDayHandler;
import catfish.plugins.finance.handler.RepaymentByDateHandler;
//import catfish.plugins.finance.handler.TodayRepaymentInfoHandler;
//import catfish.plugins.finance.handler.TodaySummaryHandler;
import catfish.plugins.finance.object.JsonDataBuilder;
import catfish.plugins.finance.object.Status;

@Path("Repayment")
public class Repayment {

	private static final String JSON_UTF8=IRESTfulService.JSON_UTF8;
	
  @GET
  @Path("/RepaymentByDate")
  @Produces(JSON_UTF8)
  public String generateRepaymentByDate(@QueryParam("AppId") String appId, @QueryParam("Date") String date) {
    Logger.get().info(String.format("Got Request: Repayment/RepaymentByDate?AppId=%s&Date=%s", appId, date));

    if (StringUtils.isNullOrWhiteSpaces(appId) || date == null) {
      Logger.get().error(String.format("params not qualified! appId:%s, dateString:%s", appId, date));
      return JsonDataBuilder.build(Status.ERROR, "params not qualified!", null).toString();
    }

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date queryDate = null;
    try {
      queryDate = format.parse(date);
    } catch (ParseException e) {
      Logger.get().error(String.format("date format error! date string:%s", date), e);
      return JsonDataBuilder.build(Status.ERROR, "date format error!", null).toString();
    }

    return new RepaymentByDateHandler(appId, queryDate).handle();
  }

  @GET
  @Path("/PenaltyPerDay")
  @Produces(JSON_UTF8)
  public String generatePenaltyPerDay(@QueryParam("AppId") String appId) {
    Logger.get().info(String.format("Got Request: Repayment/PenaltyPerDay?AppId=%s", appId));

    if (StringUtils.isNullOrWhiteSpaces(appId)) {
      Logger.get().error(String.format("params not qualified! appId:%s", appId));
      return JsonDataBuilder.build(Status.ERROR, "params not qualified!", null).toString();
    }

    return new PenaltyPerDayHandler(appId).handle();
  }
  
  @GET
  @Path("/LastPaybackDate")
  @Produces(JSON_UTF8)
  public String generateLastPaybackDate(@QueryParam("UserId") String userId) {
    Logger.get().info(String.format("Got Request: Repayment/LastPaybackDate?UserId=%s", userId));

    if (StringUtils.isNullOrWhiteSpaces(userId)) {
      Logger.get().error(String.format("params not qualified! userId :%s", userId));
      return JsonDataBuilder.build(Status.ERROR, "params not qualified!", null).toString();
    }

    return new LastPaybackDateHandler(userId).handle();
  }

  @GET
  @Path("/Repayments/{AppId}")
    @Produces(JSON_UTF8)
  public String generateRepayments(@PathParam("AppId")String appId)
  {
    Logger.get().info(String.format("Rest Request: Repayment/Repayments/%s", appId));
    List<RepaymentItem> result = RepaymentSchedule.generateRepaymentSchedule(appId);
    String respondData = new Gson().toJson(result);
    return respondData;
  }

  @GET
  @Path("/Instalments/{IdsStr}")
    @Produces(JSON_UTF8)
  public String getRepayments(@PathParam("IdsStr")String idsStr)
  {
    Logger.get().info(String.format("Rest Request: Repayment/Instalments/%s", idsStr));
    String[] appIds = new Gson().fromJson(idsStr, String[].class);
    List<String> appIdList = Arrays.asList(appIds);
    List<InstalmentObject> result = InstalmentDao.getAllInstalmentsOfAppIds(appIdList);
    Map<String, List<InstalmentObject>> dict = new HashMap<String, List<InstalmentObject>>();
    for (InstalmentObject item : result)
    {
      if (!dict.containsKey(item.AppId))
      {
        dict.put(item.AppId, new ArrayList<InstalmentObject>());

      }
      dict.get(item.AppId).add(item);
    }
    String respondData = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(dict);
    return respondData;
  }
  
//	private static double rate = Double.valueOf(StartupConfig.get("catfish.finance.rate")).doubleValue();

//	@GET
//	@Path("/AllRepaymentInfo")
//    @Produces(JSON_UTF8)
//	public String generateAllRepaymentInfo(
//	    @QueryParam("merchantStoreId") String merchantStoreId, @QueryParam("productId") String productId)
//	{
//		List<RepaymentInfo> result = RepaymentSchedule.generateAllRepaymentInfo(merchantStoreId, productId);
//		String respondData = new Gson().toJson(result);
//		return respondData;
//	}

//	@POST
//	@Path("/{AppId}/{AdminUserId}")
//    @Produces(JSON_UTF8)
//	public String generateAndUpdateDB(@PathParam("AppId")String appId, @PathParam("AdminUserId")String adminUserId)
//	{
//		String respondData;
//		if (appId == null || appId.length()==0){
//			 Logger.get().info("appId is null");
//			 respondData = new Gson().toJson(CollectionUtils.mapOf("Status", "Failed"));
//		 }
//		 else {
//			 boolean result = RepaymentSchedule.generateAndUpdateDB(appId, adminUserId);
//			 respondData = new Gson().toJson(CollectionUtils.mapOf("Status", result ? "Success" : "Failed"));
//		 }
//		return respondData;
//	}

    //@QueryParam("Date") String date) {
     // Logger.get().info(String.format("Got Request: Repayment/RepaymentByDate?AppId=%s&Date=%s", appId, date));
//
//    @GET
//    @Path("/{appId}")
//    @Produces(JSON_UTF8)
//    public String todaySummary(@PathParam("appId") String appId, @QueryParam("date") String date) { 
//      Logger.get().info(String.format("Got Request: Repayment/%s?date=%s", appId, date));
//      Date deadline;
//      if (StringUtils.isNullOrWhiteSpaces(date)) {
//          deadline = getToday();
//      } else {
//          deadline = parseDate(date);
//      }
//      Logger.get().info(String.format("deadline is %s", deadline));
//
//      if (StringUtils.isNullOrWhiteSpaces(appId)) {
//        Logger.get().error(String.format("params not qualified! appId :%s", appId));
//        return JsonDataBuilder.build(Status.ERROR, "params not qualified!", null).toString();
//      }
//
//      return new TodaySummaryHandler(appId, deadline).handle();
//    }

    /**
     * 获取今天日期
     * @return
     */
//    private Date getToday() {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = df.format(Calendar.getInstance().getTime());
//        return parseDate(dateStr);
//    }

//    private Date parseDate(String dateStr) {
//        Date date;
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            date = df.parse(dateStr);
//        } catch (java.text.ParseException pe) {
//            throw new RuntimeException("can not parse date string: " + dateStr);
//        }
//        return date;
//    }
    
//    @GET
//    @Path("/GetCurrentPhaseRepaymentInfo/{appId}")
//    @Produces(JSON_UTF8)
//    public String getCurrentPhaseRepaymentInfo(@PathParam("appId") String appId) { 
//      Logger.get().info(String.format("Got Request: Repayment/GetCurrentPhaseRepaymentInfo/%s", appId));
//      
//      if (StringUtils.isNullOrWhiteSpaces(appId)) {
//        Logger.get().error(String.format("params not qualified! appId :%s", appId));
//        return JsonDataBuilder.build(Status.ERROR, "params not qualified!", null).toString();
//      }
//
//      return new CurrentPhaseRepaymentInfoHandler(appId).handle();
//    }
    
//    @GET
//    @Path("/GetTodayRepaymentInfo/{appId}")
//    @Produces(JSON_UTF8)
//    public String getTodayRepaymentInfo(@PathParam("appId") String appId) { 
//      Logger.get().info(String.format("Got Request: Repayment/GetTodayRepaymentInfo/%s", appId));
//      Date today = getToday();
//      
//      if (StringUtils.isNullOrWhiteSpaces(appId)) {
//        Logger.get().error(String.format("params not qualified! appId :%s", appId));
//        return JsonDataBuilder.build(Status.ERROR, "params not qualified!", null).toString();
//      }
//
//      return new TodayRepaymentInfoHandler(appId, today).handle();
//    }
//    
//    @GET
//    @Path("/GetAllRepaymentInfoUtilCurrentPhase/{appId}")
//    @Produces(JSON_UTF8)
//    public String getAllRepaymentInfoUtilCurrentPhase(@PathParam("appId") String appId) { 
//      Logger.get().info(String.format("Got Request: Repayment/GetAllRepaymentInfoUtilCurrentPhase/%s", appId));
//      
//      if (StringUtils.isNullOrWhiteSpaces(appId)) {
//        Logger.get().error(String.format("params not qualified! appId :%s", appId));
//        return JsonDataBuilder.build(Status.ERROR, "params not qualified!", null).toString();
//      }
//
//      return new AllRepaymentInfoUtilCurrentPhaseHandler(appId).handle();
//    }
//    
//    @GET
//    @Path("/GetOverDueNumInstalmentCount/{appId}")
//    @Produces(JSON_UTF8)
//    public String getOverDueNumInstalmentCount(@PathParam("appId") String appId) { 
//      Logger.get().info(String.format("Got Request: Repayment/GetOverDueNumInstalmentCount/%s", appId));
//      
//      if (StringUtils.isNullOrWhiteSpaces(appId)) {
//        Logger.get().error(String.format("params not qualified! appId :%s", appId));
//        return JsonDataBuilder.build(Status.ERROR, "params not qualified!", null).toString();
//      }
//
//      return new OverDueNumInstalmentCountHandler(appId).handle();
//    }
}

