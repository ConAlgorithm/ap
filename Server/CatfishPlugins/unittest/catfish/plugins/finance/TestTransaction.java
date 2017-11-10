package catfish.plugins.finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.InstalmentDao;
import catfish.base.business.object.InstalmentObject;
import catfish.base.database.DatabaseConfig;
import catfish.bonuspoints.BonusPointsPlugin;
import catfish.framework.IPlugin;
import catfish.framework.httprequest.HttpRequest;
import catfish.framework.httprequest.HttpRespond;
import catfish.framework.http.*;
import catfish.plugins.ServiceFactory;
import catfish.plugins.luckdraw.ActivityLuckDrawPlugin;
import catfish.plugins.pdfgenerator.PDFGeneratorPlugin;
import catfish.plugins.redpack.ActivityWeixinRedPackPlugin;
import catfish.plugins.rest.RestPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;
import catfish.services.httprequest.HttpRequestService;

public class TestTransaction {

	public static void main(String[] args) throws ParseException {
		StartupConfig.initialize();
		Logger.initialize();	
		DatabaseConfig.initialize();
		
//		
//		 Server server = Server.Create();
//			ServerConfig serverConfig = new ServerConfig();
//
//			IPlugin[] plugins = new IPlugin[] {
//				    new RestPlugin()};
//			Logger.get().info("Catfish Plugin Server is running ...");;
//			server.run(serverConfig, plugins, new ServiceFactory());
		
		//RepaymentScheduleGenerator.test();
		//testRepaymentRestApi();
		//test2();
		
		List<InstalmentObject> instalmentList = InstalmentDao.getAllInstalments("8743FEC8-35E6-E311-B348-E0DB5516D568");
		System.out.println(instalmentList.size());
		testAppIds();
		String appId = "E0FE0CF6-AAE5-E411-B408-AC853D9D55CB";
		String adminUserId = "ouyang";
		
		
		
		
		
		
		
		//RepaymentSchedule.generateAndUpdateDB(appId, adminUserId);
	}
	
	public static void testJsonOfStringList()
	{
		List<String> strs = new ArrayList<String>();
		strs.add("abcd");
		strs.add("erty");
		
		String s = new Gson().toJson(strs);
		System.out.println(s);
		String[] ss = new Gson().fromJson(s, String[].class);
		
		for(String t:ss)
			System.out.println(t);
	}
	
	public static void testAppIds()
	{
		List<String> strs = new ArrayList<String>();
		strs.add("64072662-7DEF-E311-B348-E0DB5516D568");
		strs.add("8743FEC8-35E6-E311-B348-E0DB5516D568");
		
		String s = new Gson().toJson(strs);

		System.out.println(s);
		
		System.out.println(new Repayment().getRepayments(s));
	}
	
	
	
	public static void testRepaymentRestApi() {
		
		Map<String, String> body = new HashMap<String, String>();
		String appId = "87813016-DEE8-E411-87D5-80DB2F14945F";
		body.put("AppId", appId);
		String host = StartupConfig.get("catfish.bonuspoints.rest.host");
		String port = StartupConfig.get("catfish.plugin.rest.port");
//		HttpRequest request = new HttpRequest("GET", host, port, new String[]{"Repayment/87813016-DEE8-E411-87D5-80DB2F14945F"},new Gson().toJson(body));
		HttpRequest request = new HttpRequest("POST", "127.0.0.1", port, new String[]{"Agreement/Register"},new Gson().toJson(body));

		
		HttpRespond respond = new HttpRequestService().sendHttpRequest(request);
		System.out.println(respond.getResponse());
		
	}
	
	
//	private static void test4()
//	{
//		LoanInformation loanInfo = new LoanInformation();
//		loanInfo.interestRate = 0.12;
//		loanInfo.restRate = 0.66;
//		loanInfo.periods = 12;
//
//
//		System.out.println("\n\n-------------------------------------");
//		System.out.println(String.format("【P2P】 TotalRate:%s, InterestRate:%s, Periods:%s", 0.78, loanInfo.interestRate, loanInfo.periods));
//		DoTest(loanInfo);
//
//
//		System.out.println("\n\n-------------------------------------");
//		loanInfo.interestRate = 0.51;
//		loanInfo.restRate = 0.27;
//		System.out.println(String.format("【FOTIC】 TotalRate:%s, InterestRate:%s, Periods:%s", 0.78, loanInfo.interestRate, loanInfo.periods));
//		DoTest(loanInfo);
//
//	}
	
//	private static void DoTest(LoanInformation loanInfo)
//	{
//		for(double i =1000; i< 3100;i+=100)
//		{
//			System.out.println(String.format("\n\nPrincipal: %s", i));
//			loanInfo.princpal = i;
//			List<RepaymentItem> repayments = RepaymentScheduleGenerator.generateRepaymentsOfPMT(loanInfo);
//			for(int j =0;j<loanInfo.periods;j++)
//			{
//				RepaymentItem item = repayments.get(j);
//				System.out.println(String.format("[%s]  %s,  %s, %s, %s", j + 1, item.principal, item.interest, item.accountFee, item.fee));
//			}
//		}
//	}
	
	private static void test3() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date day1 = sdf.parse("2015-06-27 23:59:59");
		Date day2 = new Date();
		int result = RepaymentUtil.compareDiffDay(day1, day2);
		System.out.println(result);
		LoanInformation loanInfo = new LoanInformation();
		loanInfo.periods = result;
		loanInfo.princpal = 1000;
		
		List<RepaymentItem> repayments = RepaymentScheduleGenerator.generateRepaymentsOfPDL(loanInfo);
		System.out.println(repayments);
	}
	
//	private static void test2()
//	{
//
//		LoanInformation loanInfo = new LoanInformation();
//		loanInfo.restRate = 0.66;
//		loanInfo.interestRate = 0.12;
//		loanInfo.periods =12;
//		loanInfo.princpal = 1000;
//
//		List<RepaymentItem> repayments = RepaymentScheduleGenerator.generateRepaymentsOfPMT(loanInfo);
//		//List<RepaymentItem> repayments = new ArrayList<RepaymentItem>();
//
//
//		String respondData = new Gson().toJson(repayments);
//		System.out.println(respondData);
//
//		List<RepaymentItem> reps = new Gson().fromJson(respondData, ArrayList.class);
//		if (reps.size() == 0)
//		{
//			System.out.println("Haha");
//		}
//
//		System.out.println(reps);
//		System.out.println(new Gson().toJson(reps));
//	}
	
	
	private static void test()
	{
		Date firstPaybackDay = new Date(2015, 5, 31);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstPaybackDay);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.DAY_OF_MONTH, 1);		
		System.out.println(calendar.get(Calendar.DATE));
	}
	 
	 



}
