package catfish.plugins.pdfgenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.base.StartupConfig;
import catfish.base.Logger;
import catfish.base.queue.QueueMapMessager;
import catfish.base.queue.QueueMessages;
import catfish.base.queue.QueueMessager;

public class PDFGeneratorPlugin implements IPlugin, IQueueListener {
	
	private static String cssPath = StartupConfig.get("catfish.pdf.css");
	private static String imgPath = StartupConfig.get("catfish.pdf.img");
	private static boolean sendmsg_jmbox = Boolean.parseBoolean(StartupConfig.get("catfish.sendmessage.jmbox", "true"));
	private static final String IN_QUEUE_NAME = "GeneratePDFQueue";
	private static final String OUT_QUEUE_NAME_JMBOX = "FundLoanMoneyFinishedQueue";
	private static final String MSG_GENERATE_PDF = "GeneratePDF";
	private IQueue inQueue;
	private IQueue outQueue_jmbox;
	
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
	public void onMessage(String message, String data) {
		Logger.get().info(String.format("%s:%s:%s", this.getClass().getName(), message, data));
		QueueMapMessager dataMap = QueueMapMessager.fromString(data);
	    if (dataMap == null) {
	    	return;
	    }

	    if (MSG_GENERATE_PDF.equals(message)) {
	    	String appId = dataMap.getAsString("appId");
	    	if(generatePDF(appId)) {
	    		if(sendmsg_jmbox) {
	    			QueueMessager outMsg_jmbox = new QueueMessager(appId, "JMBox");
	    			outQueue_jmbox.sendMessage("JMBox", outMsg_jmbox);
	    		}
	    		if(sendmsg)
				 {
	    			QueueMessager messager = new QueueMessager(appId, JOBNAME_FINISHEDREMITTANCE);
					 this.outQueue_finishedremittance.sendMessage(JOBNAME_FINISHEDREMITTANCE, messager);
				 }
				 if(sendmsg_yijifu)
				 {
					 QueueMessager messager = new QueueMessager(appId, JOBNAME_INSTALLMENTSIGN);
					 this.outQueue_installmentsign.sendMessage(JOBNAME_INSTALLMENTSIGN, messager);
				 }
				 else
				 {
					 QueueMessager messager = new QueueMessager(appId, JOBNAME_INSTALLMENTSIGN);
					 this.outQueue_installmentsignByYS.sendMessage(JOBNAME_INSTALLMENTSIGN, messager);
				 }
	    	}
	    }

	}

	@Override
	public void init(IServiceProvider sp) {
	    IQueueService queueService = sp.getService(IQueueService.class);
	    inQueue = queueService.getQueue(IN_QUEUE_NAME);
	    inQueue.register(MSG_GENERATE_PDF, this);
	    outQueue_jmbox = queueService.getQueue(OUT_QUEUE_NAME_JMBOX);
	    this.outQueue_installmentsign = queueService.getQueue(OUT_QUEUE_NAME_INSTALLMENTSIGN);
	    this.outQueue_finishedremittance = queueService.getQueue(OUT_QUEUE_NAME_FINISHEDREMITTANCE);
	    this.outQueue_installmentsignByYS = queueService.getQueue(OUT_QUEUE_NAME_INSTALLMENTSIGN_YINSHENG);
	}
	
	public boolean generatePDF(String appId) {
		PDFGenerator pdfGenerator = new PDFGenerator(appId);
		if(pdfGenerator.generatePDFandSave()) {
			return true;
		}
		
		return false;
	}
	

	
}
