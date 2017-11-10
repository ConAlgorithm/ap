package catfish.plugins.pdfgenerator;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.ons.MessageNotificationUtil;
import catfish.base.queue.QueueMessager;
import catfish.framework.IListener;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;
import catfish.framework.http.IHTTPService;
import catfish.framework.httprequest.IHttpRequestService;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;
import catfish.framework.restful.IRESTfulService;

public class PDFGeneratorHttpPlugin implements IPlugin,  IListener<HttpData>{

	private static String GeneratePDFServer = "GeneratePDF";
//	private static String RegisterAgreementServer = "ShowRegisterAgreement";
//	private static String ServiceAgreemetnServer = "ShowServiceAgreement";
//	private static String TripartiteAgreementServer = "ShowTripartiteAgreement";
//	private static String TripartiteAgreementPreServer = "ShowTripartiteAgreementPre";
	private IQueue outQueue_jmbox;
	private static final String OUT_QUEUE_NAME_JMBOX = "FundLoanMoneyFinishedQueue";
	private static boolean sendmsg_jmbox = Boolean.parseBoolean(StartupConfig.get("catfish.sendmessage.jmbox", "true"));
	private static boolean sendmsg = Boolean.parseBoolean(StartupConfig.get("catfish.sendMesAftergenerateTripartite.switch", "true"));
	private static boolean sendmsg_yijifu = Boolean.parseBoolean(StartupConfig.get("catfish.sendYJFMesAftergenerateTripartite.switch", "true"));
	
	private static String OUT_QUEUE_NAME_INSTALLMENTSIGN = "WithholdingQueue";
	private static String OUT_QUEUE_NAME_FINISHEDREMITTANCE = "FundAutopayment";
	private static String OUT_QUEUE_NAME_INSTALLMENTSIGN_YINSHENG = "WithholdingByYSQueue";
	private IQueue outQueue_installmentsign;
	private IQueue outQueue_finishedremittance;
	private IQueue outQueue_installmentsignByYS;
	private static String JOBNAME_INSTALLMENTSIGN = "installmentSign";
	private static String  JOBNAME_FINISHEDREMITTANCE = "FinishedRemittance";

	@Override
	public void onMessage(String message, HttpData data) {
		 Logger.get().info("Http Request:" + data.getRequestData());
		 Map<String, Object> request =
		            (Map<String, Object>) new Gson().fromJson(data.getRequestData(), Map.class);
		 String appId = (String) request.get("AppId");

		 String respondData;
		 String logStr;
		 if (appId == null || appId.length()==0){
			 Logger.get().info("appId is null");
			 respondData = new Gson().toJson(CollectionUtils.mapOf("Status", "Failed"));
			 logStr = respondData;
		 }
		 else 
		 {		 
			 PDFGenerator pdfGenerator = new PDFGenerator(appId);
			 if(message.equals(GeneratePDFServer)) {
				 boolean result = pdfGenerator.generatePDFandSave();
				 respondData = new Gson().toJson(CollectionUtils.mapOf("Status", result ? "Success" : "Failed"));			 
				 logStr = respondData;

				 if(result && sendmsg_jmbox){
					 QueueMessager outMsg_jmbox = new QueueMessager(appId, "JMBox");
					 outQueue_jmbox.sendMessage("JMBox", outMsg_jmbox);
				 }
				 
				 if(result && sendmsg)
				 {
					 QueueMessager messager = new QueueMessager(appId, JOBNAME_FINISHEDREMITTANCE);
					 this.outQueue_finishedremittance.sendMessage(JOBNAME_FINISHEDREMITTANCE, messager);
				 }
				 if(result && sendmsg_yijifu)
				 {
					 QueueMessager messager = new QueueMessager(appId, JOBNAME_INSTALLMENTSIGN);
					 this.outQueue_installmentsign.sendMessage(JOBNAME_INSTALLMENTSIGN, messager);
				 }
				 else if(result && !sendmsg_yijifu)
				 {
					 QueueMessager messager = new QueueMessager(appId, JOBNAME_INSTALLMENTSIGN);
					 this.outQueue_installmentsignByYS.sendMessage(JOBNAME_INSTALLMENTSIGN, messager);
				 }
			 } else {
				 respondData = new Gson().toJson(CollectionUtils.mapOf("Status", "Failed"));			 
				 logStr = respondData;
			 }
		 }		
//		 Logger.get().info("Http Respond:" + respondData);
		 Logger.get().info(logStr+"AppId: "+appId);
		 data.setResponseData(respondData);		
		
	}

	@Override
	public void init(IServiceProvider sp) {
		IHTTPService restService = (IHTTPService)sp.getService(IHTTPService.class);
		restService.register(GeneratePDFServer, this);
//		restService.register(RegisterAgreementServer, this);
//		restService.register(ServiceAgreemetnServer, this);
//		restService.register(TripartiteAgreementPreServer, this);
//		restService.register(TripartiteAgreementServer, this);
	    IQueueService queueService = sp.getService(IQueueService.class);
	    outQueue_jmbox = queueService.getQueue(OUT_QUEUE_NAME_JMBOX);
	    this.outQueue_installmentsign = queueService.getQueue(OUT_QUEUE_NAME_INSTALLMENTSIGN);
	    this.outQueue_finishedremittance = queueService.getQueue(OUT_QUEUE_NAME_FINISHEDREMITTANCE);
	    this.outQueue_installmentsignByYS = queueService.getQueue(OUT_QUEUE_NAME_INSTALLMENTSIGN_YINSHENG);
	}

}
