package catfish.plugins.pdfgenerator;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.framework.restful.IRESTfulService;
import catfish.plugins.pdfgenerator.cashloan.CLAgreementService;
import catfish.plugins.pdfgenerator.cashloan.CLPdfService;
import catfish.plugins.pdfgenerator.merchantloan.MsAgreementService;
import catfish.plugins.pdfgenerator.merchantloan.MsPdfService;
import catfish.plugins.pdfgenerator.pdl.PdlAgreementService;

@Path("Agreement")
public class Agreement {

	private static final String JSON_UTF8=IRESTfulService.JSON_UTF8;
	private static String mdqFundId =
		      StartupConfig.get("catfish.cashloan.mdq.fundId", "defaultFundId");
	private static String brFundId =
		      StartupConfig.get("catfish.cashloan.br.fundId", "defaultFundId");
	private static String jd2FundId =
		      StartupConfig.get("catfish.cashloan.jd2.fundId", "defaultFundId");
	private static String drFundId =
		      StartupConfig.get("catfish.cashloan.dr.fundId", "defaultFundId");
	
	private static String scFundId =
		      StartupConfig.get("catfish.cashloan.sc.fundId", "defaultFundId");
	private static String hryFundId = 
			StartupConfig.get("catfish.cashloan.hry.fundId");
	
	private static String wsmFundId = StartupConfig.get("catfish.cashloan.wsm.fundId","defaultFundId");
	
	private static String lancaiFundId = StartupConfig.get("catfish.cashloan.lancai.fundId");
			
	private static Gson gson = new Gson();
	
	private static String MDQCHANNEL = "mdq";
	AgreementService agreementService;

	public Agreement() {
		agreementService = new AgreementService();
	}

	@GET
	@Path("/Register")
    @Produces(JSON_UTF8)
	public String registerAgreement(){
		Logger.get().info("require for register agreement");
		return new Gson().toJson(
				CollectionUtils.mapOf("Html", agreementService.getHtmlDom(null, AgreementType.REGISTER)));
	}

	@GET
	@Path("/Service/{AppId}")
    @Produces(JSON_UTF8)
	public String serviceAgreement(@PathParam("AppId")String appId){
		Logger.get().info("require for service agreement for AppId : "+appId);
		return new Gson().toJson(
				CollectionUtils.mapOf("Html", agreementService.getHtmlDom(appId, AgreementType.SERVICE)));
	}

	@GET
	@Path("/TripartitePre/{AppId}")
    @Produces(JSON_UTF8)
	public String tripartiteAgreementPre(@PathParam("AppId")String appId){
		Logger.get().info("require for pre tripartite agreement for AppId : "+appId);
		return new Gson().toJson(
				CollectionUtils.mapOf("Html", agreementService.getHtmlDom(appId, AgreementType.PRETRIPARTITE)));
	}

	@GET
	@Path("/Both/{AppId}")
    @Produces(JSON_UTF8)
	public String bothAgreement(@PathParam("AppId")String appId){
		Logger.get().info("require for both agreement for AppId : "+appId);
		return new Gson().toJson(
				CollectionUtils.mapOf("Html", agreementService.getHtmlDom(appId, AgreementType.BOTH)));
	}

    @GET
	@Path("/paydayloan")
    @Produces("text/html;charset=UTF-8")
	public String prePDLAgreement(@QueryParam("userId") String userId,@QueryParam("days") Integer days){
		Logger.get().info("request pdl agreement for userId="+userId);
		String html=new PdlAgreementService().getAgreementByUserId(userId,days);
		return html;
	}

	@GET
	@Path("/Tripartite/{AppId}")
    @Produces(JSON_UTF8)
	public String tripartite(@PathParam("AppId")String appId){
		Logger.get().info("require for tripartite agreement for AppId : "+appId);
		return new Gson().toJson(
				CollectionUtils.mapOf("Html", agreementService.getHtmlDom(appId, AgreementType.TRIPARTITE)));
	}

    @GET
    @Path("/cashloan/{AppId}")
    @Produces(JSON_UTF8)
    public String getCashLoanAgreement(@PathParam("AppId") String appId){
      CLAgreementService service = new CLAgreementService(appId);
      String fundId = service.getFundId(appId);

      if (mdqFundId.equalsIgnoreCase(fundId)){
    	  service.buildModel();
          Logger.get().info("create cl cus html appid : " + appId);
          String customHtml = service.getCustomHtml();
    	  Logger.get().info("create cl gov html appid : " + appId);
          String govHtml = service.getGovHtml();
//          String mergeHtml = service.getMergeHtml();
          
          return gson.toJson(CollectionUtils.mapOf("html", customHtml + govHtml));
      } else if (brFundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("create cl common html appid : " + appId);
    	  String html = service.getCommonHtml();
          return gson.toJson(CollectionUtils.mapOf("html", html));
      } else if(jd2FundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("create cl jd2 html appid : " + appId);
    	  String html = service.getJD2Html();
          return gson.toJson(CollectionUtils.mapOf("html", html));
      } else if(drFundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("create cl dr self html appid : " + appId);
          String selfHtml = service.getDRSelfHtml();
          
          return gson.toJson(CollectionUtils.mapOf("html", selfHtml));
      } else if(scFundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("create cl sc law html appid : " + appId);
          String scLawHtml = service.getScLawHtml();
          
          Logger.get().info("create cl sc loan guarantee html appid : " + appId);
          String scLoanGuaranteeHtml = service.getScLoanGuaranteeHtml();
          
          Logger.get().info("create cl sc plat service html appid : " + appId);
          String scPlatServiceHtml = service.getScPlatServiceHtml();
          
          Logger.get().info("create cl sc guarantee html appid : " + appId);
          String scGuaranteeHtml = service.getScGuaranteeHtml();
          
          Logger.get().info("create cl sc credit inquiry html appid : " + appId);
          String scCreditInquiryHtml = service.getScCreditInquiryHtml();
          
          return gson.toJson(CollectionUtils.mapOf("html", scLawHtml + scLoanGuaranteeHtml
        		  + scPlatServiceHtml + scGuaranteeHtml + scCreditInquiryHtml));
      } else if (hryFundId.equalsIgnoreCase(fundId)) {
    	  // 海融易协议预览需要三方协议+海融易授权委托书
    	  service.buildModel();
          Logger.get().info("create cl hry html appid : " + appId);
          String hryAgreementHtml = service.getCommonHtml();
          
          Logger.get().info("create cl hry authorized html appid : " + appId);
          String hryAuthorizedHtml = service.getHRYAuthorizedHtml();
          
          return gson.toJson(CollectionUtils.mapOf("html", hryAgreementHtml + hryAuthorizedHtml));
      } else if(wsmFundId.equalsIgnoreCase(fundId)){
    	  service.buildModel();
          Logger.get().info("create cl wsm html appid : " + appId);
    	  String html = service.getWSMHtml();
          return gson.toJson(CollectionUtils.mapOf("html", html));
      } else if (lancaiFundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("create cl lancai html appid : " + appId);
          String html = service.getCommonHtml();
          
          return gson.toJson(CollectionUtils.mapOf("html", html));
      }
      
      String html = service.getFullHtml();
      return gson.toJson(CollectionUtils.mapOf("html", html));
    }

    @PUT
    @Path("/cashloan/key")
    @Produces(JSON_UTF8)
    public BoolModel updateAgreementFilePath(Map<String, String> filePath) {

      return new CLPdfService().updateAgreementFilePath(filePath);
    }

    @POST
    @Path("/cashloan/{AppId}")
    @Produces(JSON_UTF8)
    public String saveCashLoanAgreement(@PathParam("AppId") String appId){
      CLAgreementService service = new CLAgreementService(appId);
      CLPdfService pdfService = new CLPdfService(appId);
      
      String fundId = service.getFundId(appId);
      if (mdqFundId.equalsIgnoreCase(fundId)){
    	  service.buildModel();
    	  StringBuilder stringBuilder = new StringBuilder("");
    	  
          Logger.get().info("create cl cus html appid : " + appId);
          String customHtml = service.getCustomHtml();
    	  Logger.get().info("create cl gov html appid : " + appId);
          String govHtml = service.getGovHtml();
//    	  Logger.get().info("create cl merge html appid : " + appId);
//          String mergeHtml = service.getGovHtml();
          
          stringBuilder.append(pdfService.generatePDFandSave(customHtml + govHtml, "merge"));
          stringBuilder.append(",");
          
          stringBuilder.append(pdfService.generatePDFandSave(customHtml, "custom"));
          stringBuilder.append(",");
          
          stringBuilder.append(pdfService.generatePDFandSave(govHtml, "gov"));

          
          return gson.toJson(
              CollectionUtils.mapOf("id", stringBuilder));   	  
      } else if (brFundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
    	  Logger.get().info("create cl common html appid : " + appId);
          String html = service.getCommonHtml();
          String attachmentId = pdfService.generatePDFandSave(html);
          return gson.toJson(
              CollectionUtils.mapOf("id", attachmentId));
      } else if(jd2FundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("create cl jd2 html appid : " + appId);
    	  String html = service.getJD2Html();
          String attachmentId = pdfService.generatePDFandSave(html);
          return gson.toJson(
              CollectionUtils.mapOf("id", attachmentId));
      } else if(drFundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("create cl dr self html appid : " + appId);
          String selfHtml = service.getDRSelfHtml();
          Logger.get().info("create cl dr credit assignment html appid : " + appId);
          String creditAssignmentHtml = service.getDRCreditAssignmentHtml();
    	  Logger.get().info("create cl authorized html appid : " + appId);
          String authorizedHtml = service.getDRAuthorizedHtml();
          
    	  StringBuilder stringBuilder = new StringBuilder();
          
          stringBuilder.append(pdfService.generatePDFandSave(selfHtml, "self"));
          stringBuilder.append(",");
          
          stringBuilder.append(pdfService.generatePDFandSave(creditAssignmentHtml, "credit"));
          stringBuilder.append(",");
          
          stringBuilder.append(pdfService.generatePDFandSave(authorizedHtml, "author"));
          
          return gson.toJson(
                  CollectionUtils.mapOf("id", stringBuilder));  
      } else if(scFundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("save cl sc law html appid : " + appId);
          String scLawHtml = service.getScLawHtml();
          
          Logger.get().info("save cl sc loan guarantee html appid : " + appId);
          String scLoanGuaranteeHtml = service.getScLoanGuaranteeHtml();
          
          Logger.get().info("save cl sc plat service html appid : " + appId);
          String scPlatServiceHtml = service.getScPlatServiceHtml();
          
          Logger.get().info("save cl sc guarantee html appid : " + appId);
          String scGuaranteeHtml = service.getScGuaranteeHtml();
          
          Logger.get().info("save cl sc credit inquiry html appid : " + appId);
          String scCreditInquiryHtml = service.getScCreditInquiryHtml();
    	  
          String attachmentId = pdfService.generatePDFandSave(scLawHtml + scLoanGuaranteeHtml
        		  + scPlatServiceHtml + scGuaranteeHtml + scCreditInquiryHtml);
          return gson.toJson(
              CollectionUtils.mapOf("id", attachmentId));
      } else if (hryFundId.equalsIgnoreCase(fundId)) {
    	  // 海融易协议保存仅需要三方协议
    	  service.buildModel();
          Logger.get().info("save cl hry html appid : " + appId);
          String hryAgreementHtml = service.getCommonHtml();
          
          String attachmentId = pdfService.generatePDFandSave(hryAgreementHtml);
          return gson.toJson(CollectionUtils.mapOf("id", attachmentId));
      } else if(wsmFundId.equalsIgnoreCase(fundId)){
    	  service.buildModel();
    	  Logger.get().info("create wsm common html appid : " + appId);
          String html = service.getWSMHtml();
          String attachmentId = pdfService.generatePDFandSave(html);
          return gson.toJson(
              CollectionUtils.mapOf("id", attachmentId));
      } else if(lancaiFundId.equalsIgnoreCase(fundId)) {
    	  service.buildModel();
          Logger.get().info("save cl lancai html appid : " + appId);
          String html = service.getCommonHtml();
          
          String attachmentId = pdfService.generatePDFandSave(html);
          return gson.toJson(CollectionUtils.mapOf("id", attachmentId));
      }
      
      Logger.get().info("create cl full html appid : " + appId);
      String html = service.getFullHtml();
      String attachmentId = pdfService.generatePDFandSave(html);
      return gson.toJson(
          CollectionUtils.mapOf("id", attachmentId));
    }

	@GET
    @Path("/merchantloan")
    @Produces("text/html;charset=UTF-8")
    public String getMerchantLoanAgreement(@QueryParam("userid") String userId, @QueryParam("principal") BigDecimal principal,
			@QueryParam("financeProductId") String financeProductId, @QueryParam("serialnumber") String serialNumber){
      MsAgreementService service = new MsAgreementService(userId, principal, financeProductId, serialNumber);
      String html = service.getFullHtml();
      return html;
    }

    @POST
    @Path("/merchantloan")
    @Produces(JSON_UTF8)
    public String saveMerchantLoanAgreement(@QueryParam("userid") String userId, @QueryParam("principal") BigDecimal principal,
			@QueryParam("financeProductId") String financeProductId, @QueryParam("serialnumber") String serialNumber){
      MsAgreementService service = new MsAgreementService(userId, principal, financeProductId, serialNumber);
      String html = service.getFullHtml();
      MsPdfService pdfService = new MsPdfService(serialNumber);
      String attachmentId = pdfService.generatePDFandSave(html);
      return new Gson().toJson(
              CollectionUtils.mapOf("id", attachmentId));
    }
	/**
	 * 供产品线调用，生成纸质版PDF文件并保存
	 * 暂时供cashloan调用，后期其他产品线会切入
	 * @param pdfHtml  PDF文件对应的html字符串
	 * @param appId  InstalmentApplicationObjects的主键id
	 * @return
	 */
	@POST
	@Path("/GenerateAndSavePDF/")
    @Produces(JSON_UTF8)
	public String generateAndSavePDF(TransferAgreementInfoModel model){
		Logger.get().info("generateAndSavePDF called by user : appId:" + model.appId + " \nuserId:" + model.userId + "\n agreement:" + model.agreement);
		return new Gson().toJson("{\"agreementId\":" + agreementService.pdfGenerateAndSave(model) + "}");
	}

    // 用于审计的PDF，实时生成，这样历史的合同也做了服务费前置
    @GET
    @Path("/cashloan/auditpdf/{appId}")
    @Produces("application/pdf")
    public Response getCashloanAuditAgreement(@PathParam("appId") String appId) {
      CLAgreementService service = new CLAgreementService(appId);
      CLPdfService pdfService = new CLPdfService(appId);
      String html = service.getFullHtml();
      InputStream is = pdfService.generatePDF(html);
      ResponseBuilder responseBuilder = Response.ok((Object) is);
      String fileName = "agreement-auditpdf-" + appId + ".pdf";
      responseBuilder.type("application/pdf");
      responseBuilder.header("Content-Disposition", "filename=" + fileName);
      return responseBuilder.build();
    }
    
    @POST
    @Path("/cashloan/asset")
    @Produces(JSON_UTF8)
    public String saveCashLoanAgreementForAsset(AssetThirdAgreementModel assetThirdAgreementModel){
      CLAgreementService service = new CLAgreementService(assetThirdAgreementModel.getOldAppId());
      CLPdfService pdfService = new CLPdfService(assetThirdAgreementModel.getAppId());
      Logger.get().info("create asset cl full html appid : " + assetThirdAgreementModel.getAppId());
      String html = service.getAssetFullHtml(assetThirdAgreementModel);
      Boolean isSuccess  = pdfService.generatePDFandSaveForAssetMDQ(html);
      return gson.toJson(CollectionUtils.mapOf(assetThirdAgreementModel.getAppId(), isSuccess));
    }
    
    @POST
    @Path("/cashloan/asset/mdq")
    @Produces(JSON_UTF8)
    public String saveCashLoanAgreementForAssetMDQ(List<String> appIds){
      Map<String,Boolean> returnMap = new HashMap<>();
      appIds.stream().forEach(appId->{
    	  CLAgreementService service = new CLAgreementService(appId);
    	  CLPdfService pdfService = new CLPdfService(appId);
          Logger.get().info("create asset cl mdq full html appid : " + appId);
          Boolean isSuccess = false;
          try{
        	  String html = service.getFullHtml();
        	  isSuccess= pdfService.generatePDFandSaveForAssetMDQ(html); 
          }catch(Exception e){
        	  Logger.get().error("create asset cl mdq full html get exception !!! appid :{}" + appId,e);
          }
          returnMap.put(appId, isSuccess);
      });
      return gson.toJson(returnMap);
    }
    
    /**
     * 仅用于健康检查使用
     * @return
     */
	@GET
    @Path("/health")
	@Produces(MediaType.APPLICATION_JSON)
    public String getAgreementHealthStatus(){
		return "{\"status\": \"UP\"}";
    }

}
