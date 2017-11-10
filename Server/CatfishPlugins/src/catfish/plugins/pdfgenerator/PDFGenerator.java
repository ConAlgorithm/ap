package catfish.plugins.pdfgenerator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.css.CssFile;
import com.google.common.io.CharStreams;

import catfish.base.business.common.AttachmentType;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.MimeType;
import catfish.base.business.dao.ApplicationAttachmentDao;
import catfish.base.business.dao.AttachmentDao;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.UserAttachmentDao;
import catfish.base.business.object.AttachmentObject;
import catfish.base.business.object.UserAttachmentObject;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.plugins.finance.RepaymentItem;

public class PDFGenerator implements IMimeGenerator{

	public static String HTML_FOTIC = StartupConfig.get("catfish.pdf.agreement-fotic") == null ? 
									"./agreementTemplate/agreement-fotic.html": StartupConfig.get("catfish.pdf.agreement-fotic");
	public static String HTML_LTZ = StartupConfig.get("catfish.pdf.agreement-ltz") == null ? 
									"./agreementTemplate/agreement-ltz.html" : StartupConfig.get("catfish.pdf.agreement-ltz");
	public static String HTML_SELF = StartupConfig.get("catfish.pdf.agreement-self") == null ? 
									"./agreementTemplate/agreement-self.html" : StartupConfig.get("catfish.pdf.agreement-self");
	public static String HTML_JMBOX = StartupConfig.get("catfish.pdf.agreement-jmbox") == null ? 
									"./agreementTemplate/agreement-jmbox.html" : StartupConfig.get("catfish.pdf.agreement-jmbox");
	public static String HTML_TRIPARTITE_ZRB = StartupConfig.get("catfish.pdf.agreement-zrb") == null ? 
									"./agreementTemplate/agreement-zrb.html" : StartupConfig.get("catfish.pdf.agreement-zrb");
	public static String HTML_PDL = StartupConfig.get("catfish.pdf.agreement-pdl") == null ? 
									"./agreementTemplate/agreement-pdl.html" : StartupConfig.get("catfish.pdf.agreement-pdl");
	public static String HTML_TRIPARTITE_PDL_SELF = StartupConfig.get("catfish.agreement.agreement-self-pdl") == null ? 
            "./agreementTemplate/agreement-self-pdl.html" : StartupConfig.get("catfish.agreement.agreement-self-pdl");
	public static String HTML_MINREPAYMENT_PDL = StartupConfig.get("catfish.pdf.agreement-minrepayment") == null ?
			"./agreementTemplate/agreement-pdl-minrepayment.html" : StartupConfig.get("catfish.pdf.agreement-minrepayment");
	private static String HTML_TEST_PRODUCT = StartupConfig.get("catfish.pdf.agreement-test") == null ?
			"./agreementTemplate/agreement-test.html" : StartupConfig.get("catfish.pdf.agreement-test");

	public static String DEST = "./out.pdf";
	public static final String CSS = StartupConfig.get("catfish.pdf.css") == null ? 
									"./agreementTemplate/mobile.css" : StartupConfig.get("catfish.pdf.css");
	private static final String IMG_PATH = StartupConfig.get("catfish.pdf.img") == null ? 
									"./agreementTemplate" : StartupConfig.get("catfish.pdf.img");
	private static final int BUFFER_SIZE = 1024;
	private static final long TICKS_AT_EPOCH = 621355968000000000L; 
	public static final String FUNTAG_ZRB = StartupConfig.get("catfish.fundtag.zrb") == null ? 
									"ZhenRongBao" : StartupConfig.get("catfish.fundtag.zrb");
	public static final String FUNTAG_AXDL = StartupConfig.get("catfish.fundtag.axdl", "AXDL");
	public static final String FUNDTAG_SELF = StartupConfig.get("catfish.fundtag.self", "SELF");
	//新增合投资金源
	public static final String FUNDTAG_CROWDFUNDING1 =StartupConfig.get("catfish.fundtag.crowdfunding1", "CROWDFUNDING1");
	public static final String FUNDTAG_CROWDFUNDING2 =StartupConfig.get("catfish.fundtag.crowdfunding2", "CROWDFUNDING2");
	public static final String FUNDTAG_CROWDFUNDING3 =StartupConfig.get("catfish.fundtag.crowdfunding3", "CROWDFUNDING3");
	
	//铜板街
	public static final String FUNDTAG_COPPERSTREET =StartupConfig.get("catfish.fundtag.copperstreet", "COPPERSTREET");
		
	private static String stampimg = getImgBaset64FromFile(IMG_PATH+"/stamp.gif");
	private String appId;
	private String userId;
	private String outFileName;

	public PDFGenerator(String appId) {
		this.appId = appId;
        this.userId = InstallmentApplicationDao.getUserIdByAppId(appId);
	}
	
	public PDFGenerator(){}
	
	@Override
	public ByteArrayOutputStream generate(InputStream ins) {
		return null;
	}
	
	public ByteArrayOutputStream html2pdf(InputStream htmlStream, String cssPath, String imgPath) throws IOException, DocumentException {
		
		 // step 1
       Document document = new Document();
       // step 2
       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
       PdfWriter writer = PdfWriter.getInstance(document, outStream);
       // step 3
       document.open();
       // step 4
       // CSS
       CSSResolver cssResolver = new StyleAttrCSSResolver();
       CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(cssPath));
       cssResolver.addCss(cssFile);

       // HTML
       HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
       htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
       htmlContext.setImageProvider(new Base64ImageProvider() {
           public String getImageRootPath() {
               return imgPath;
           }
       });

//       htmlContext.setLinkProvider(new LinkProvider() {
//           public String getLinkRoot() {
//               return IMG_PATH;
//           }
//       });

       // Pipelines
       PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
       HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
       CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

       // XML Worker
       XMLWorker worker = new XMLWorker(css, true);
       XMLParser p = new XMLParser(worker);
       p.parse(htmlStream, Charset.forName("UTF-8"));
       // step 5
       document.close();
       
       return outStream;
	}
	
	public String getHtmlDom() {
			InputStream ins = constructHtmlStream();
			try {
				String htmlStr = CharStreams.toString(new InputStreamReader(ins, "UTF-8"));
				htmlStr = htmlStr.replace("./stamp.gif", "data:image/gif;base64, "+stampimg);
//				ByteArrayOutputStream outStream = html2pdf(ins, CSS, IMG_PATH);
//				ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
//				saveAttachment(inStream);
				return htmlStr;
			} catch (UnsupportedEncodingException e) {
				Logger.get().error(e);
			} catch (IOException e) {
				Logger.get().error(e);
			} 
			return null;
	}
	
	public String getHtmlDomPre() {
		ApplicationAgreementModel model = new ApplicationAgreementModel();
		InputStream ins = constructHtmlStream();
		try {
			String htmlStr = CharStreams.toString(new InputStreamReader(ins, "UTF-8"));
			htmlStr = htmlStr.replace("./stamp.gif", "data:image/gif;base64, "+stampimg);
			return htmlStr;
		} catch (UnsupportedEncodingException e) {
			Logger.get().error(e);
		} catch (IOException e) {
			Logger.get().error(e);
		}
		return null;
}

public InputStream constructHtmlStream(ApplicationAgreementModel model, String htmlFile) {
        try (FileInputStream fin = new FileInputStream(htmlFile)) {
        	
			String htmlStr = CharStreams.toString(new InputStreamReader(fin, "UTF-8"));
			// 头像信息
			String idImgStr =  getImgBase64String(appId, AttachmentType.UserIdPhoto);
			String headImgStr =  getImgBase64String(appId, AttachmentType.UserHeadPhoto);
			if(idImgStr != null) {
				htmlStr = htmlStr.replace("%IdPhoto%", "data:image/jpg;base64, "+idImgStr);
			} else {
				htmlStr = htmlStr.replace("%IdPhoto%", "");
			}
			if(headImgStr != null) {
				htmlStr = htmlStr.replace("%HeadPhoto%", "data:image/jpg;base64, "+headImgStr);
			} else {
				htmlStr = htmlStr.replace("%HeadPhoto%", "");
			}
			// 基本信息
			htmlStr = htmlStr.replace("%Name%", model.getName());
			htmlStr = htmlStr.replace("%IdNumber%", model.getIdNumber());
			htmlStr = htmlStr.replace("%Address%", checkoutStr(model.getAddress()));
			htmlStr = htmlStr.replace("%Principal%", String.valueOf(model.getPrincipal().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
			htmlStr = htmlStr.replace("%PrincipalString%", model.getPrincipalString());
			htmlStr = htmlStr.replace("%MonthlyPay%", String.valueOf(model.getMonthlyPay().intValue()));
			htmlStr = htmlStr.replace("%Repayments%", String.valueOf(model.getRepayments()));
			htmlStr = htmlStr.replace("%InterestPerMonth%", model.getInterestPerMonth().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			htmlStr = htmlStr.replace("%PrincipalInMonthlyPay%", model.getPrincipalInMonthlyPay().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			htmlStr = htmlStr.replace("%InterestsInMontylyPay%", model.getInterestInMonthlyPay().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			htmlStr = htmlStr.replace("%FeeInMonthlyPay%", model.getFeeInMonthlyPay().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			htmlStr = htmlStr.replace("%FeeInMonthlyPayStr%", model.getFeeInMonthlyPayStr());
			htmlStr = htmlStr.replace("%Fee%", model.getFee().toString());
			htmlStr = htmlStr.replace("%FeeStr%", model.getFeeStr());
			htmlStr = htmlStr.replace("%FeeInMonthlyPay%", model.getFeeInMonthlyPay().toString());
			htmlStr = htmlStr.replace("%PrincipalInterestPerMonth%", model.getPrincipalInterestPerMonth().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			BigDecimal Interest = model.getPrincipalInterestPerMonth().subtract(model.getPrincipal());
            htmlStr = htmlStr.replace("%Interest%", Interest.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
            htmlStr = htmlStr.replace("%Mobile%", checkoutStr(model.getMobile()));
            htmlStr = htmlStr.replace("%PayDay%", PdlServerService.getWithDrawalPayday(model.getFirstPaybackDate()));
			htmlStr = htmlStr.replace("%InterestRate%", PdlServerService.getInterestRate());
			htmlStr = htmlStr.replace("%ServiceFeeRate%", PdlServerService.getServiceFeeRate());
			
			htmlStr = htmlStr.replace("%TotalPaybackStr%", model.getTotalPaybackStr());
			htmlStr = htmlStr.replace("%FirstPaybackStr%", model.getFirstPaybackStr());
			htmlStr = htmlStr.replace("%FirstPayback%", model.getFirstPayback().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			htmlStr = htmlStr.replace("%OtherPaybackStr%", checkoutStr(model.getOtherPaybackStr()));
			if(model.getOtherPayback() != null) {
				htmlStr = htmlStr.replace("%OtherPayback%", model.getOtherPayback().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}
			htmlStr = htmlStr.replace("%MonthlyPaybackDay%", String.valueOf(model.getMonthlyPackbackDay()));
			SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日");
			htmlStr = htmlStr.replace("%FirstPaybackDate%", format.format(model.getFirstPaybackDate()));
			htmlStr = htmlStr.replace("%MonthlyPaybackDay%", String.valueOf(model.getMonthlyPackbackDay()));
			htmlStr = htmlStr.replace("%BankName%", model.getBankName());
			htmlStr = htmlStr.replace("%BankAccount%", model.getBankAccount());
			htmlStr = htmlStr.replace("%BankAccountName%", model.getBankAccountName());
			htmlStr = htmlStr.replace("%Today%", format.format(model.getToday()));
			htmlStr = htmlStr.replace("%MerchantStoreAddress%", model.getMerchantAddress());
			htmlStr = htmlStr.replace("%EndTime%", format.format(model.getEndTime()));
			htmlStr = htmlStr.replace("%MerchantName%", model.getMerchantName());
//			htmlStr = htmlStr.replace("%ProductName%", model.getProductName());
			htmlStr = htmlStr.replace("%AppId%", model.getAppId().replace("-", ""));
			htmlStr = htmlStr.replace("%InstallmentStartedOn%", format.format(model.getInstallmentStartedOn()));
			if (model.getRepaymentSchedule() != null) {
				String rs = "";
				for(RepaymentItem item : model.getRepaymentSchedule()) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(model.getFirstPaybackDate());
					int repayNum = item.repaymentNumber-1;
					if(repayNum < 0) {
						repayNum = 0;
					}
					cal.add(Calendar.MONTH, repayNum);
					rs += String.format("<tr><td>%d</td><td>%s</td><td>本息服务费共【%s】元</td></tr>", item.repaymentNumber
							, format.format(cal.getTime()), item.principal.add(item.interest).add(item.fee)
									.add(item.accountFee).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				htmlStr = htmlStr.replace("%RepaymentSchedule%", rs);
				htmlStr = htmlStr.replace("%Penalty%", model.getPenalty().toString());
			} else {
				htmlStr = htmlStr.replace("%RepaymentSchedule%", "<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			}
			
			htmlStr = fillNullSymbol(htmlStr);
			
			return new ByteArrayInputStream(htmlStr.getBytes(Charset.forName("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			Logger.get().error(e);
		} catch (IOException e) {
			Logger.get().error(e);
		}
		return null;
	}
	
	public InputStream constructHtmlStream() {
        	ApplicationAgreementModel model = AgreementApi.getAppAgreementModel(appId);
        	setOutFileName(model.getOutFileName());
        	if(model.getInstallmentChannel() == InstalmentChannel.PayDayLoanApp.getValue()) {
        		if(FUNDTAG_SELF.toLowerCase().equals(model.getFundTag().toLowerCase())) {
        			return constructHtmlStream(model, HTML_MINREPAYMENT_PDL);
        		}else{
        			return constructHtmlStream(model, HTML_PDL);
        		}
        	} else {
				if (AgreementApi.isTestProduct(appId)) {
					return constructHtmlStream(model, HTML_TEST_PRODUCT);
				} else if(FUNDTAG_SELF.toLowerCase().equals(model.getFundTag().toLowerCase())) {
        			return constructHtmlStream(model, HTML_SELF);
        		}else if(("jmbox").equals(model.getFundTag().toLowerCase())) {
        			return constructHtmlStream(model, HTML_JMBOX);
        		} else if(("lantouzi").equals(model.getFundTag().toLowerCase())) {
        			return constructHtmlStream(model, HTML_LTZ);
        		} else if(FUNTAG_ZRB.toLowerCase().equals(model.getFundTag().toLowerCase())){
        			return constructHtmlStream(model, HTML_TRIPARTITE_ZRB);
				} else if(FUNTAG_AXDL.toLowerCase().equals(model.getFundTag().toLowerCase())){
					return constructHtmlStream(model, HTML_LTZ);
        		}
        		//增加合投资金源  使用自有资金源的三方模板
				else if(FUNDTAG_CROWDFUNDING1.toLowerCase().equals(model.getFundTag().toLowerCase()) ||
						FUNDTAG_CROWDFUNDING2.toLowerCase().equals(model.getFundTag().toLowerCase()) ||
						FUNDTAG_CROWDFUNDING3.toLowerCase().equals(model.getFundTag().toLowerCase())){
					return constructHtmlStream(model, HTML_SELF);
				}
        		//铜板街资金源和懒投资一样
				else if(FUNDTAG_COPPERSTREET.toUpperCase().equals(model.getFundTag().toUpperCase())){
					return constructHtmlStream(model, HTML_LTZ);
				}
				else {
        			return constructHtmlStream(model, HTML_FOTIC);
        		}
        	}
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImgBase64String(String appId, AttachmentType type) {
		String userId = InstallmentApplicationDao.getUserIdByAppId(appId);
		try {
			UserAttachmentDao uaDao = new UserAttachmentDao(userId, type);
			UserAttachmentObject uao = uaDao.getSingle();
			AttachmentDao aDo = new AttachmentDao(uao.AttachmentId);
			AttachmentObject ao = aDo.getSingle();
			InputStream imgStream = OssApi.get(ao.FilePath);
			byte[] imgByte = stream2byte(imgStream);
			return Base64.encodeBytes(imgByte);
		} catch(Exception e) {
			Logger.get().error(e);
		}
		return null;
	}
	
	public static String getImgBaset64FromFile(String filepath) {
		File file = new File(filepath);
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			BufferedImage bufimg = ImageIO.read(file);
		    ImageIO.write(bufimg, "gif", baos);  
		    byte[] bytes = baos.toByteArray();  
		    return Base64.encodeBytes(bytes);
		} catch (IOException e) {
			Logger.get().error(e);
		}
		
		return "";
	}
	
	public byte[] stream2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while((count = inStream.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null;  
        return outStream.toByteArray();  
	}

	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}
	
	public String getOutFileName() {
		return outFileName;
	}
	
	public void saveAttachment(InputStream ins) {
		String key = String.format("%s/%s/%s", "Attachment", appId, String.valueOf(getTicks()));
		if(OssApi.savePDF(key, ins) == null) {
			Logger.get().info("failure : save PDF failed, attachment will not be generated, appId="+appId);
			return;
		}
		Logger.get().info("success : pdf saved to oss, you can visit within 5 minutes \n"+OssApi.getUrl(key));
		String attachId = AttachmentDao.addAttachment(getOutFileName(), key, MimeType.Pdf);
		if(attachId == null) {
			Logger.get().info("failure : insert attachment record failed, appId="+appId);
			return;
		}
		int retCode = UserAttachmentDao.addUserAttachment(userId, attachId, AttachmentType.ApplicationAgreementPDF);
		if(retCode > 0) {
			Logger.get().info("success : pdf generated and attachment saved, appId="+appId);
		} else {
			Logger.get().info("failure : userAttachment insert failed, appId="+appId);
		}
		if(ApplicationAttachmentDao.insert(attachId, appId, AttachmentType.ApplicationAgreementPDF)) {
			Logger.get().info("success : application attachment saved, appId="+appId);
		} else {
			Logger.get().info("failure : application attachment insert failed, appId="+appId);
		}
	}
	
	public String saveAgreement(InputStream ins) {
		String key = String.format("%s/%s/%s", "Attachment", appId, String.valueOf(getTicks()));
		if(OssApi.savePDF(key, ins) == null) {
			Logger.get().info("failure : save PDF failed, attachment will not be generated, appId="+appId);
			return null;
		}
		Logger.get().info("success : pdf saved to oss, you can visit within 5 minutes \n"+OssApi.getUrl(key));
		String attachId = AttachmentDao.addAttachment(getOutFileName(), key, MimeType.Pdf);
		if(attachId == null) {
			Logger.get().info("failure : insert attachment record failed, appId="+appId);
			return null;
		}
		int retCode = UserAttachmentDao.addUserAttachment(userId, attachId, AttachmentType.ApplicationAgreementPDF);
		if(retCode > 0) {
			Logger.get().info("success : pdf generated and attachment saved, appId="+appId);
		} else {
			Logger.get().info("failure : userAttachment insert failed, appId="+appId);
		}
		if(ApplicationAttachmentDao.insert(attachId, appId, AttachmentType.ApplicationAgreementPDF)) {
			Logger.get().info("success : application attachment saved, appId="+appId);
		} else {
			Logger.get().info("failure : application attachment insert failed, appId="+appId);
		}
		
		return attachId;
	}
	
	public String saveAgreement(InputStream ins, String appId, String userId) {
		String key = String.format("%s/%s/%s", "Attachment", appId, String.valueOf(getTicks()));
		if(OssApi.savePDF(key, ins) == null) {
			Logger.get().info("failure : save PDF failed, attachment will not be generated, appId="+appId);
			return null;
		}
		Logger.get().info("success : pdf saved to oss, you can visit within 5 minutes \n"+OssApi.getUrl(key));
		String attachId = AttachmentDao.addAttachment(getOutFileName(), key, MimeType.Pdf);
		if(attachId == null) {
			Logger.get().info("failure : insert attachment record failed, appId="+appId);
			return null;
		}
		int retCode = UserAttachmentDao.addUserAttachment(userId, attachId, AttachmentType.ApplicationAgreementPDF);
		if(retCode > 0) {
			Logger.get().info("success : pdf generated and attachment saved, appId="+appId);
		} else {
			Logger.get().info("failure : userAttachment insert failed, appId="+appId);
		}
		if(ApplicationAttachmentDao.insert(attachId, appId, AttachmentType.ApplicationAgreementPDF)) {
			Logger.get().info("success : application attachment saved, appId="+appId);
		} else {
			Logger.get().info("failure : application attachment insert failed, appId="+appId);
		}
		
		return attachId;
	}
	
	public long getTicks() {
		long ticks = System.currentTimeMillis()*10000 + TICKS_AT_EPOCH;
		return ticks;
	}
	
	public boolean generatePDFandSave() {
		try (ByteArrayOutputStream outStream = html2pdf(constructHtmlStream(), CSS, IMG_PATH);
			 ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray())) {

			saveAttachment(inStream);
			return true;
		} catch (IOException e) {
			Logger.get().error(e);
		} catch (DocumentException e) {
			Logger.get().error(e);
		}
		
		return false;
	}
	
	public String pdfGenerateAndSaveForNewVersion(TransferAgreementInfoModel model){
//		String pdfHtml = model.agreement.replaceAll("#", "/");
		
		try (ByteArrayOutputStream outStream = html2pdf(new ByteArrayInputStream(model.agreement.getBytes(Charset.forName("UTF-8"))), CSS, IMG_PATH);
				 ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray())) {

			return saveAgreement(inStream, model.appId, model.userId);
			} catch (IOException e) {
				Logger.get().error(e);
			} catch (DocumentException e) {
				Logger.get().error(e);
			}
		return null;
	}
	
	private String checkoutStr(String in) {
		if (in == null || in.isEmpty()) {
			return "";
		}
		return in;
	}
	
	private String fillNullSymbol(String htmlStr) {
		htmlStr = htmlStr.replace("%IdPhoto%", "");
		htmlStr = htmlStr.replace("%HeadPhoto%", "");
		// 基本信息
		htmlStr = htmlStr.replace("%Name%", "");
		htmlStr = htmlStr.replace("%IdNumber%", "");
		htmlStr = htmlStr.replace("%Address%", "");
		htmlStr = htmlStr.replace("%Principal%", "");
		htmlStr = htmlStr.replace("%PrincipalString%", "");
		htmlStr = htmlStr.replace("%MonthlyPay%", "");
		htmlStr = htmlStr.replace("%Repayments%", "");
		htmlStr = htmlStr.replace("%InterestPerMonth%", "");
		htmlStr = htmlStr.replace("%PrincipalInMonthlyPay%", "");
		htmlStr = htmlStr.replace("%InterestsInMontylyPay%", "");
		htmlStr = htmlStr.replace("%FeeInMonthlyPay%", "");
		htmlStr = htmlStr.replace("%PrincipalInterestPerMonth%", "");
		htmlStr = htmlStr.replace("%FeeInMonthlyPayStr%", "");
		htmlStr = htmlStr.replace("%Fee%", "");
		htmlStr = htmlStr.replace("%FeeStr%", "");
		htmlStr = htmlStr.replace("%FeeInMonthlyPay%", "");
		htmlStr = htmlStr.replace("%TotalPaybackStr%", "");
		htmlStr = htmlStr.replace("%FirstPaybackStr%", "");
		htmlStr = htmlStr.replace("%FirstPayback%", "");
		htmlStr = htmlStr.replace("%OtherPaybackStr%", "");
		htmlStr = htmlStr.replace("%OtherPayback%", "");
		htmlStr = htmlStr.replace("%MonthlyPaybackDay%", "");
		htmlStr = htmlStr.replace("%FirstPaybackDate%", "");
		htmlStr = htmlStr.replace("%MonthlyPaybackDay%", "");
		htmlStr = htmlStr.replace("%BankName%", "");
		htmlStr = htmlStr.replace("%BankAccount%", "");
		htmlStr = htmlStr.replace("%BankAccountName%", "");
		htmlStr = htmlStr.replace("%Today%", "");
		htmlStr = htmlStr.replace("%EndTime%", "");
		htmlStr = htmlStr.replace("%MerchantName%", "");
		htmlStr = htmlStr.replace("%ProductName%", "");
		htmlStr = htmlStr.replace("%AppId%", "");
		htmlStr = htmlStr.replace("%InstallmentStartedOn%", "");
		htmlStr = htmlStr.replace("%RepaymentSchedule%", "<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
		htmlStr = htmlStr.replace("%Penalty%", "5");

		return htmlStr;
	}
}
