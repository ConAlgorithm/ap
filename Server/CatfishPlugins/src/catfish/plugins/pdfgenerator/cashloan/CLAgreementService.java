package catfish.plugins.pdfgenerator.cashloan;

import grasscarp.user.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.google.common.io.CharStreams;
import com.omniprime.fpp.app.api.representations.RepaymentInfo;
import com.omniprime.fpp.app.api.representations.RepaymentItem;
import com.omniprimeinc.finance.installment.settlement.api.models.response.AllInfo;
import com.omniprimeinc.finance.installment.settlement.api.models.response.PayDetail;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.cowfish.application.constant.ApplicationEvent;
import catfish.cowfish.application.model.ApplicationModel;
import catfish.cowfish.application.model.EventModel;
import catfish.cowfish.product.model.ProductModel;
import catfish.cowfish.user.model.UserInfoModel;
import catfish.plugins.pdfgenerator.AssetThirdAgreementModel;
import catfish.plugins.pdfgenerator.cashloan.model.ProductFundModel;


public class CLAgreementService {
  private static final String PARTIAL_TEMPLATE = "./agreementTemplate/partial-cashloan.html";
  private static final String FULL_TEMPLATE = "./agreementTemplate/agreement-cashloan.html";
  private static final String MDQ_CUSTOM_TEMPLATE = "./agreementTemplate/agreement-cashloan-mdq-custom.html";
  private static final String MDQ_GOV_TEMPLATE = "./agreementTemplate/agreement-cashloan-mdq-goverment.html";
  private static final String MDQ_MERGE_TEMPLATE = "./agreementTemplate/agreement-cashloan-mdq-merge.html";
  private static final String COMMON_TEMPLATE = "./agreementTemplate/agreement-cashloan-common.html";
  private static final String WSM_TEMPLATE = "./agreementTemplate/agreement-cashloan-wsm.html";
  private static final String JD2_TEMPLATE = "./agreementTemplate/agreement-cashloan-jd2.html";
  private static final String DR_CREDIT_ASSIGNMENT_TEMPLATE = "./agreementTemplate/agreement-cashloan-dr-zqzrtz.html";
  private static final String DR_AUTHORIZED_TEMPLATE = "./agreementTemplate/agreement-cashloan-dr-sqwts.html";
  private static final String SC_LAW_TEMPLATE = "./agreementTemplate/agreement-cashloan-sc-law.html";
  private static final String SC_CREDIT_INQUIRY_TEMPLATE = "./agreementTemplate/agreement-cashloan-sc-credit-inquiry.html";
  private static final String SC_GUARANTEE_TEMPLATE = "./agreementTemplate/agreement-cashloan-sc-guarantee.html";
  private static final String SC_PLAT_SERVICE_TEMPLATE = "./agreementTemplate/agreement-cashloan-sc-plat-service.html";
  private static final String SC_LOAN_GUARANTEE_TEMPLATE = "./agreementTemplate/agreement-cashloan-sc-loan-guarantee.html";
  private static final String HRY_AUTHORIZED_TEMPLATE = "./agreementTemplate/agreement-cashloan-hry-authorized.html";
  
  private static String STAMP = CLUtil.getImgBaset64FromFile("agreementTemplate/stamp.gif");
  private static BigDecimal MONTHLY_FEE_RATE =
      new BigDecimal(StartupConfig.get("catfish.cashloan.finance.monthlyFeeRate"));
  private static BigDecimal MONTHLY_TECH_MAINTEN_FEE_RATE =
      new BigDecimal(StartupConfig.get("catfish.cashloan.finance.monthlyTechMaintenFeeRate"));

  private static final List<String> CONSULT_FEE_PERCENT = StartupConfig.getAsList("consult.fee.percent");
  
  private String appId;
  private CLAgreementModel model;

  public CLAgreementService(String appId) {
    super();
    this.appId = appId;
  }

  public String getFullHtml() {
    buildAgreementModel();
    return buildHtml(FULL_TEMPLATE);
  }
  public String getAssetFullHtml(AssetThirdAgreementModel assetThirdAgreementModel) {
	    buildAssetAgreementModel(assetThirdAgreementModel);
	    return buildHtml(FULL_TEMPLATE);
	  }

  public void buildModel(){
	  buildAgreementModel();
  }
  
  public String getGovHtml() {
	  return buildHtml(MDQ_GOV_TEMPLATE);
  }
  
  public String getScLawHtml() {
	  return buildHtml(SC_LAW_TEMPLATE);
  }
  
  public String getScGuaranteeHtml() {
	  return buildHtml(SC_GUARANTEE_TEMPLATE);
  }
  
  public String getScCreditInquiryHtml() {
	  return buildHtml(SC_CREDIT_INQUIRY_TEMPLATE);
  }
  
  public String getScLoanGuaranteeHtml() {
	  return buildHtml(SC_LOAN_GUARANTEE_TEMPLATE);
  }
  
  public String getScPlatServiceHtml() {
	  return buildHtml(SC_PLAT_SERVICE_TEMPLATE);
  }
  
  public String getCustomHtml() {
	  return buildHtml(MDQ_CUSTOM_TEMPLATE);
  }
  
  public String getMergeHtml() {
	  return buildHtml(MDQ_MERGE_TEMPLATE);
  }
  
  public String getCommonHtml() {
	  return buildHtml(COMMON_TEMPLATE);
  }
  
  public String getJD2Html() {
	  return buildHtml(JD2_TEMPLATE);
  }
  
  public String getHRYAuthorizedHtml() {
	  return buildHtml(HRY_AUTHORIZED_TEMPLATE);
  }
  
  public String getDRSelfHtml() {
	  return buildHtml(FULL_TEMPLATE);
  }
  
  public String getDRCreditAssignmentHtml() {
	  return buildHtml(DR_CREDIT_ASSIGNMENT_TEMPLATE);
  }
  
  public String getDRAuthorizedHtml() {
	  return buildHtml(DR_AUTHORIZED_TEMPLATE);
  }
  
  public String getWSMHtml(){
	  return buildHtml(WSM_TEMPLATE);
  }
  
  public String getPartialHtml() {
    buildAgreementModel();
    return buildHtml(PARTIAL_TEMPLATE);
  }
  
  public String getFundId(String applicationId){
	 return CLAgreementDataService.getFundId(applicationId);
  }
  
  private Calendar getCalendar(Date date) {
      if (date == null) {
          date = new Date();
      }
      
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      
      return calendar;
  }
  
  private String buildHtml(String templateFile) {
      Logger.get().info("CLAgreementModel model= : " + model);
    try (FileInputStream fin = new FileInputStream(templateFile)) {
      String htmlStr = CharStreams.toString(new InputStreamReader(fin, "UTF-8"));
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
      
      htmlStr = htmlStr.replace("./stamp.gif", "data:image/gif;base64, " + STAMP);

      htmlStr = htmlStr.replace("%Name%", model.getName());
      htmlStr = htmlStr.replace("%IdNumber%", model.getIdNumber());
      htmlStr = htmlStr.replace("%Address%", notNullString(model.getAddress()));
      htmlStr = htmlStr.replace("%BankName%", model.getBankName());
      htmlStr = htmlStr.replace("%BankAccount%", model.getBankAccount());
      htmlStr = htmlStr.replace("%InstallmentPurpose%", model.getInstallmentPurpose());
      
      htmlStr = htmlStr.replace("%MonthlyRepaymentString%", model.getMonthlyRepaymentString());
      htmlStr = htmlStr.replace("%techConsultFeeString%", model.getTechConsultFeeString());
      htmlStr = htmlStr.replace("%monthlyTotalFeeString%", model.getMonthlyTotalFeeString());
      htmlStr = htmlStr.replace("%monthlyRepaymentCapitalString%", model.getMonthlyRepaymentCapitalString());
      
      htmlStr = htmlStr.replace("%AppId%", model.getAppId().replace("-", ""));
      htmlStr = htmlStr.replace("%Repayments%", String.valueOf(model.getRepayments()));
      htmlStr = htmlStr.replace("%Principal%", model.getPrincipal().toString());
      htmlStr = htmlStr.replace("%PrincipalCapital%", model.getPrincipalCapital());
      htmlStr = htmlStr.replace("%MonthlyRepayment%", model.getMonthlyRepayment().toString());
      htmlStr = htmlStr.replace("%MonthlyRepaymentCapital%", model.getMonthlyRepaymentCapital());
      htmlStr = htmlStr.replace("%PrincipalInterestPerMonth%", model.getPrincipalInterestPerMonth().toString());
      htmlStr = htmlStr.replace("%FeePerMonth%", model.getFeePerMonth().toString());
      htmlStr = htmlStr.replace("%FeePerMonthCapital%", model.getFeePerMonthCapital());
      htmlStr = htmlStr.replace("%Fee%", model.getFee().toString());
      htmlStr = htmlStr.replace("%MonthlyFeeRate%", model.getMonthlyFeeRate().toString());
      htmlStr = htmlStr.replace("%MonthlyInterestRate%", model.getMonthlyInterestRate().toString());
      htmlStr = htmlStr.replace("%PenaltyPerDay%", model.getPenaltyPerDay().toString());
      htmlStr = htmlStr.replace("%PrePaymentFee%", model.getPrePaymentFee().toString());
      htmlStr = htmlStr.replace("%MonthlyPaybackDay%", String.valueOf(model.getMonthlyPaybackDay()));
      htmlStr = htmlStr.replace("%FirstPaybackDateYear%", String.valueOf(getCalendar(model.getFirstPaybackDate()).get(Calendar.YEAR)));
      htmlStr = htmlStr.replace("%FirstPaybackDateMonth%", String.valueOf(getCalendar(model.getFirstPaybackDate()).get(Calendar.MONTH) + 1));
      htmlStr = htmlStr.replace("%FirstPaybackDateDay%", String.valueOf(getCalendar(model.getFirstPaybackDate()).get(Calendar.DAY_OF_MONTH)));
      htmlStr = htmlStr.replace("%EndTime%", sdf.format(model.getEndTime()));
      htmlStr = htmlStr.replace("%MoneyTransferredOn%", sdf.format(model.getMoneyTransferredOn()));
      htmlStr = htmlStr.replace("%MoneyTransferredOnYear%", String.valueOf(getCalendar(model.getMoneyTransferredOn()).get(Calendar.YEAR)));
      htmlStr = htmlStr.replace("%MoneyTransferredOnMonth%", String.valueOf(getCalendar(model.getMoneyTransferredOn()).get(Calendar.MONTH) + 1));
      htmlStr = htmlStr.replace("%MoneyTransferredOnDay%", String.valueOf(getCalendar(model.getMoneyTransferredOn()).get(Calendar.DAY_OF_MONTH)));
      htmlStr = htmlStr.replace("%RepaymentSchedule%", model.getRepaymentSchedule());
      htmlStr = htmlStr.replace("%TechFeePerMonth%", model.getTechMaintenFee().toString());
      htmlStr = htmlStr.replace("%MonthlyTechMaintenFeeRate%", model.getMonthlyTechMaintenFeeRate().toString());
      htmlStr = htmlStr.replace("%ValueAddedPackageFlag%", model.getValueAddedPkgFlag());
      htmlStr = htmlStr.replace("%ValueAddedPackageFee%", model.getValueAddedPkgFee().toString());
      htmlStr = htmlStr.replace("%PackageAdvanceFee%", model.getPackageAdvanceFee().toString());
      htmlStr = htmlStr.replace("%PackageInsuranceFee%", model.getPackageInsuranceFee().toString());
      htmlStr = htmlStr.replace("%ConsultFee%", model.getConsultFee().toString());
      htmlStr = htmlStr.replace("%CustomAndTechFee%", model.getCustomAndTechFee().toString());
      htmlStr = htmlStr.replace("%AnnualRate%", model.getAnnualRate().toString());
      htmlStr = htmlStr.replace("%MonthlyTechMainFee%", model.getMonthlyTechMainFee().toString());
      htmlStr = htmlStr.replace("%formerDiversePaybackPackageFee%", model.getFormerDiversePaybackPackageFee().toPlainString());
      htmlStr = htmlStr.replace("%latterDiversePaybackPackageFee%", model.getLatterDiversePaybackPackageFee().toPlainString());
      htmlStr = htmlStr.replace("%formerPeriodsTotalMonthlyPay%", model.getFormerPeriodsTotalMonthlyPay().toPlainString());
      htmlStr = htmlStr.replace("%latterPeriodsTotalMonthlyPay%", model.getLatterPeriodsTotalMonthlyPay().toPlainString());
      htmlStr = htmlStr.replace("%formerPeriodsTotalMonthlyFee%", model.getFormerPeriodsTotalMonthlyFee().toPlainString());
      htmlStr = htmlStr.replace("%latterPeriodsTotalMonthlyFee%", model.getLatterPeriodsTotalMonthlyFee().toPlainString());
      htmlStr = htmlStr.replace("%formerPeriodsTotalMonthlyPayCapital%", CLUtil.changeNumber2Chinese(model.getFormerPeriodsTotalMonthlyPay().doubleValue()));
      htmlStr = htmlStr.replace("%latterPeriodsTotalMonthlyPayCapital%", CLUtil.changeNumber2Chinese(model.getLatterPeriodsTotalMonthlyPay().doubleValue()));
      
      return htmlStr;
    } catch (UnsupportedEncodingException e) {
        Logger.get().error(e);
    } catch (IOException e) {
        Logger.get().error(e);
    }
    return null;
  }

  private void buildAgreementModel() {
    model = new CLAgreementModel();
    fillApplicationInfo();
    fillUserInfo();
//    fillRepaymentInfo();
    fillRepaymentInfoByPhoebus();
    fillExtraInfo();
  }
  
  private void buildAssetAgreementModel(AssetThirdAgreementModel assetThirdAgreementModel) {
	    model = new CLAgreementModel();
	    fillAssetApplicationInfo(assetThirdAgreementModel);
	    fillUserInfo();
	    fillRepaymentInfoByAsset(assetThirdAgreementModel);
	  }

  private void fillApplicationInfo() {
    ApplicationModel appModel = CLAgreementDataService.getApplication(appId);
    List<EventModel> events = CLAgreementDataService.getApplicationEvent(appId);
    Map<String, Object>  applicationExtendInfo = CLAgreementDataService.getApplicationExtendInfo(appId);  
    
    Date moneyTransferredOn =null;
    if(events!=null){
    	moneyTransferredOn= getDateByEvent(events, ApplicationEvent.MONEY_TRANSFERRED);
    }

    model.setAppId(appId);
    model.setUserId(appModel.userId);
    model.setPrincipal(appModel.principal);
    model.setPrincipalCapital(CLUtil.changeNumber2Chinese(model.getPrincipal().doubleValue()));
    model.setRepayments(appModel.repayments);
    model.setMoneyTransferredOn(moneyTransferredOn == null ? new Date() : moneyTransferredOn);

    model.setConsultFee(this.getConsultFeePercentByApplication(appModel));
    if (applicationExtendInfo != null) {
    	if(applicationExtendInfo.containsKey("isDiversePaybackApp") && (boolean)applicationExtendInfo.get("isDiversePaybackApp")) {
    		model.setDiverseApp(true);
    		if(applicationExtendInfo.containsKey("diversePaybackLatterPeriods")
        			&& ((Double)applicationExtendInfo.get("diversePaybackLatterPeriods")).intValue() > 0) {
    			// 含有前三后九的多样还款增值包，每月的增值服务费由后续的账务数据进行替换
    			model.setTechConsultFeeString(
    				String.format("前【%s】月支付【%s】元/月，后【%s】月支付【%s】元/月", 
    						((Double)applicationExtendInfo.get("diversePaybackFormerPeriods")).intValue() + "", "%formerDiversePaybackPackageFee%",
    						((Double)applicationExtendInfo.get("diversePaybackLatterPeriods")).intValue() + "", "%latterDiversePaybackPackageFee%"
    					)
    				);
    			model.setFormerDiversePaybackPackagePeriods(((Double)applicationExtendInfo.get("diversePaybackFormerPeriods")).intValue());
    			model.setLatterDiversePaybackPackagePeriods(((Double)applicationExtendInfo.get("diversePaybackLatterPeriods")).intValue());
    			model.setMonthlyRepaymentString(
    					String.format("前【%s】月付款金额：【%%formerPeriodsTotalMonthlyPay%%】元<br />剩余期数每月付款金额：【%%latterPeriodsTotalMonthlyPay%%】元", 
    							((Double)applicationExtendInfo.get("diversePaybackFormerPeriods")).intValue() + ""));
    		
    			model.setMonthlyTotalFeeString(
    					String.format("前[%s]期【%%formerPeriodsTotalMonthlyFee%%】元/月，后[%s]期【%%latterPeriodsTotalMonthlyFee%%】元/月", 
    							((Double)applicationExtendInfo.get("diversePaybackFormerPeriods")).intValue() + "",
    							((Double)applicationExtendInfo.get("diversePaybackLatterPeriods")).intValue() + ""));
    			model.setMonthlyRepaymentCapitalString(
    					String.format("您的前【%s】期月付款额为人民币<u>&nbsp;（大写） %%formerPeriodsTotalMonthlyPayCapital%%&nbsp;</u>（￥），"
    							+ "剩余期数月付款额为人民币<u>&nbsp;（大写）%%latterPeriodsTotalMonthlyPayCapital%%&nbsp;</u>（￥）。", 
    							((Double)applicationExtendInfo.get("diversePaybackFormerPeriods")).intValue() + ""));
    		} else {
    			// 是多样还款增值包，但不是前三后九，每期相同的正常还产品
    			model.setFormerDiversePaybackPackagePeriods(((Double)applicationExtendInfo.get("diversePaybackFormerPeriods")).intValue());
    			model.setLatterDiversePaybackPackagePeriods(((Double)applicationExtendInfo.get("diversePaybackLatterPeriods")).intValue());
        		model.setTechConsultFeeString("【%formerDiversePaybackPackageFee%】元/月。");
        		model.setMonthlyRepaymentString("每月付款金额：【%MonthlyRepayment%】元");
        		model.setMonthlyTotalFeeString("【%CustomAndTechFee%】元/月。");
        		model.setMonthlyRepaymentCapitalString("您的【每月】付款额为人民币<u>&nbsp;（大写）%MonthlyRepaymentCapital%&nbsp;</u>(￥)。");
    		}
    	} else {
    		// 不含前三后九的多样还款增值包，每月还款金额由后续的账务数据进行替换
    		model.setTechConsultFeeString("【0】元/月。");
    		model.setMonthlyRepaymentString("每月付款金额：【%MonthlyRepayment%】元");
    		model.setMonthlyTotalFeeString("【%CustomAndTechFee%】元/月。");
    		model.setMonthlyRepaymentCapitalString("您的【每月】付款额为人民币<u>&nbsp;（大写）%MonthlyRepaymentCapital%&nbsp;</u>(￥)。");
    	}
    }
  }
  
  private void fillAssetApplicationInfo(AssetThirdAgreementModel assetThirdAgreementModel) {
	    ApplicationModel appModel = CLAgreementDataService.getApplication(appId);
	    model.setAppId(assetThirdAgreementModel.getAppId());
	    model.setUserId(appModel.userId);
	    model.setPrincipal(assetThirdAgreementModel.getPrincipal());
	    model.setPrincipalCapital(CLUtil.changeNumber2Chinese(assetThirdAgreementModel.getPrincipal().doubleValue()));
	    model.setRepayments(assetThirdAgreementModel.getRepayments());
	    model.setMoneyTransferredOn(new Date(assetThirdAgreementModel.getMoneyTransferredOn()));

	    model.setConsultFee(assetThirdAgreementModel.getConsultFee());
	  }

  /**
   * function: get consult fee percent by application.<p>
   * date: 2017-04-24
   * @author jiaoh
   * @param app
   * @return
   */
  private BigDecimal getConsultFeePercentByApplication(ApplicationModel app) {
	  BigDecimal consultFeePercent = this.getConsultFeePercentByRepayments(app);
	  BigDecimal consultFee = app.getPrincipal()
			  .divide(new BigDecimal(1).subtract(consultFeePercent), 2, BigDecimal.ROUND_HALF_UP)
			  .subtract(app.getPrincipal());
	  
	  return consultFee;
  }
  
	private BigDecimal getDefaultConsultFeePercentByRepayments(int repayments) {
		int index = 0;
		switch (repayments) {
		case 1:
			index = 0;
			break;
		case 3:
			index = 1;
			break;
		case 6:
			index = 2;
			break;
		case 9:
			index = 3;
			break;
		case 12:
			index = 4;
			break;
		case 15:
			index = 5;
			break;
		case 24:
			index = 6;
			break;
		case 36:
			index = 7;
			break;
		}
		return new BigDecimal(CONSULT_FEE_PERCENT.get(index));
	}
  
  /**
   * function: get consult fee percent by repayments.<p>
   * date: 2017-04-04
   * @author jiaoh
   * @param repayments
   * @return
   */
	private BigDecimal getConsultFeePercentByRepayments(ApplicationModel app) {
		//get consulet Fee from cowfish-service by appId
		BigDecimal consultFee=CLAgreementDataService.getConsultFeeRateByAppId(app.getId());
		if (consultFee == null) {
			Logger.get().warn("fundInfo is null ,will use default consultFee for appId="+app.getId()+" .");
			return getDefaultConsultFeePercentByRepayments(app.getRepayments());
		}
		return consultFee;
	}
  
	private String getProductId(String appId) {
		return CLAgreementDataService.getProductId(appId);
	}

private void fillUserInfo() {
    String userId = model.getUserId();
    User userInfo = CLAgreementDataService.getUserInfo(userId);
    UserInfoModel clUserInfo = CLAgreementDataService.getCLUserInfoByAppId(appId);
    String livingAddress = clUserInfo.getLivingAddress() == null
        ? userInfo.getLivingAddress()
        : clUserInfo.getLivingAddress();

    model.setName(userInfo.getIdName());
    model.setIdNumber(userInfo.getIdNumber());
    model.setAddress(livingAddress);
    model.setBankName(userInfo.getBankName());
    model.setBankAccount(userInfo.getBankCardAccount());
//    model.setInstallmentPurpose(clUserInfo.getPurpose());  // 注释原因：法务要求借款目的修改为消费
    model.setInstallmentPurpose("消费");
  }

  private void fillRepaymentInfo() {
    String appId = model.getAppId();
    BigDecimal repayments = new BigDecimal(model.getRepayments());
    String productId = CLAgreementDataService.getApplicationProductId(appId).getId();
    ProductModel productParam = CLAgreementDataService.getApplicationProduct(productId);
    RepaymentInfo repaymentInfo = CLAgreementDataService.getRepaymentSchedule(appId);
    RepaymentItem firstRepayment = repaymentInfo.items.stream()
        .filter(item -> item.index == 1)
        .findFirst()
        .get();
    Date firstPaybackDate = firstRepayment.dateDue;
    Date endTime = repaymentInfo.items.stream()
        .filter(item -> item.index == model.getRepayments())
        .findFirst()
        .get().dateDue;
    BigDecimal totalFee = BigDecimal.ZERO;
    BigDecimal techMaintenFee = repaymentInfo.items.get(0).getTechMaintainFee();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    StringBuilder repaymentScheduleSB = new StringBuilder();
    for (RepaymentItem item : repaymentInfo.items) {
      BigDecimal payback = item.principal
          .add(item.interest)
          .add(item.fee)
          .add(notNullBigDecimal(item.techMaintainFee))
          .setScale(2, BigDecimal.ROUND_HALF_UP);
      repaymentScheduleSB
        .append("<tr>")
        .append(String.format("<td>%s</td>", item.index))
        .append(String.format("<td>%s</td>", sdf.format(item.dateDue)))
        .append(String.format("<td>本息服务费共【%s】元</td>", payback.toString()))
        .append("</tr>");

        totalFee.add(item.fee).add(notNullBigDecimal(item.techMaintainFee));
    }

    model.setMonthlyRepayment(firstRepayment.principal
        .add(firstRepayment.interest)
        .add(firstRepayment.fee)
        .add(notNullBigDecimal(firstRepayment.techMaintainFee))
        .setScale(2, BigDecimal.ROUND_HALF_UP));
    model.setMonthlyRepaymentCapital(CLUtil.changeNumber2Chinese(model.getMonthlyRepayment().doubleValue()));
    model.setPrincipalInterestPerMonth(firstRepayment.principal.add(firstRepayment.interest));
    model.setFeePerMonth(firstRepayment.fee.setScale(2, BigDecimal.ROUND_HALF_UP));
    model.setFeePerMonthCapital(CLUtil.changeNumber2Chinese(model.getFeePerMonth().doubleValue()));
    model.setFee(totalFee.setScale(2, BigDecimal.ROUND_HALF_UP));
    model.setMonthlyFeeRate(MONTHLY_FEE_RATE.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
    model.setMonthlyInterestRate(productParam.getAnnualInterestRate().multiply(new BigDecimal(100)).divide(repayments, 2, BigDecimal.ROUND_HALF_UP));
    model.setPenaltyPerDay(new BigDecimal(3).setScale(2, BigDecimal.ROUND_HALF_UP));
    model.setPrePaymentFee(new BigDecimal(200).setScale(2, BigDecimal.ROUND_HALF_UP));
    model.setFirstPaybackDate(firstPaybackDate);
    model.setMonthlyPaybackDay(new DateTime(firstPaybackDate).getDayOfMonth());
    model.setEndTime(endTime);
    model.setRepaymentSchedule(repaymentScheduleSB.toString());
    model.setTechMaintenFee(techMaintenFee.setScale(2, BigDecimal.ROUND_HALF_UP));
    model.setMonthlyTechMaintenFeeRate(MONTHLY_TECH_MAINTEN_FEE_RATE.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
  }
  
  /**
   * 通过新账务系统获取申请还款计划表信息
   */
  private void fillRepaymentInfoByPhoebus() {

      String appId = model.getAppId();
      
      AllInfo repaymentInfo = CLAgreementDataService.getRepaymentsFromPhoebus(appId);
      model.setRepayments(repaymentInfo.getRepayment());

      PayDetail firstRepayment = repaymentInfo.getPayDetails().stream()
          .filter(item -> item.getInstalmentNum() == 1)
          .findFirst()
          .get();
      Date firstPaybackDate = firstRepayment.getDueDate();
      Date endTime = repaymentInfo.getPayDetails().stream()
          .filter(item -> item.getInstalmentNum() == model.getRepayments())
          .findFirst()
          .get().getDueDate();
      
      BigDecimal totalFee = BigDecimal.ZERO;
      
      // {"extendFees":{"client_fee":"","tech_fee":""}}
      BigDecimal techMaintenFee = repaymentInfo.getPayDetails().get(0).getExtendFees().get("tech_fee");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
      StringBuilder repaymentScheduleSB = new StringBuilder();
      
      BigDecimal totalFormerDiversePackageFee = BigDecimal.ZERO;
      BigDecimal totalLatterDiversePackageFee = BigDecimal.ZERO;
      BigDecimal totalFormerMonthlyPay = BigDecimal.ZERO;
      BigDecimal totalLatterMonthlyPay = BigDecimal.ZERO;

      for (int i = 0; i < repaymentInfo.getPayDetails().size(); i++) {
    	  PayDetail item = repaymentInfo.getPayDetails().get(i);

    	  BigDecimal payback = item.getPrincipal()
            .add(item.getInterest())
            .add(notNullBigDecimal(item.getExtendFees().get("client_fee")))
            .add(notNullBigDecimal(item.getExtendFees().get("tech_fee")))
            .add(notNullBigDecimal(item.getExtendFees().get("package_advance_fee")))
            .add(notNullBigDecimal(item.getExtendFees().get("package_insureance_fee")))
            .add(notNullBigDecimal(item.getExtendFees().get("package_diverse_PayBackPackage_fee")))
            .setScale(2, BigDecimal.ROUND_HALF_UP);
    	  repaymentScheduleSB
          .append("<tr>")
          .append(String.format("<td>%s</td>", item.getInstalmentNum()))
          .append(String.format("<td>%s</td>", sdf.format(item.getDueDate())))
          .append(String.format("<td>本息服务费共【%s】元</td>", payback.toString()))
          .append("</tr>");
        
    	  totalFee.add(notNullBigDecimal(item.getExtendFees().get("client_fee")))
            .add(notNullBigDecimal(item.getExtendFees().get("tech_fee")));
        	

    	  if(i < model.getFormerDiversePaybackPackagePeriods()) {
        	  // 计算前三后九的前x期各费用明细
    		  totalFormerDiversePackageFee = totalFormerDiversePackageFee.add(
    				  notNullBigDecimal(item.getExtendFees().get("package_diverse_PayBackPackage_fee")));
    		  totalFormerMonthlyPay = totalFormerMonthlyPay.add(payback);
    	  } else {
        	  // 计算前三后九的后y期各费用明细
    		  totalLatterDiversePackageFee = totalLatterDiversePackageFee.add(
    				  notNullBigDecimal(item.getExtendFees().get("package_diverse_PayBackPackage_fee")));
    		  totalLatterMonthlyPay = totalLatterMonthlyPay.add(payback);
    	  }
      }


      setValueAddedPackageFee(repaymentInfo.getPayDetails().get(0).getExtendFees());
      
      model.setMonthlyTechMainFee(BigDecimal.ZERO.add(notNullBigDecimal(firstRepayment.getExtendFees().get("client_fee")))
    		  .add(notNullBigDecimal(firstRepayment.getExtendFees().get("tech_fee")))
    		  .setScale(2, BigDecimal.ROUND_HALF_UP));
      
      model.setMonthlyRepayment(firstRepayment.getPrincipal()
          .add(firstRepayment.getInterest())
          .add(notNullBigDecimal(firstRepayment.getExtendFees().get("client_fee")))
          .add(notNullBigDecimal(firstRepayment.getExtendFees().get("tech_fee")))
          .add(notNullBigDecimal(firstRepayment.getExtendFees().get("package_advance_fee")))
          .add(notNullBigDecimal(firstRepayment.getExtendFees().get("package_insureance_fee")))
          .setScale(2, BigDecimal.ROUND_HALF_UP));
      
      if(model.isDiverseApp()) {
    	  model.setMonthlyRepayment(model.getMonthlyRepayment()
    			  .add(notNullBigDecimal(firstRepayment.getExtendFees().get("package_diverse_PayBackPackage_fee")))
    	  			.setScale(2, BigDecimal.ROUND_HALF_UP));
      }
      
      model.setMonthlyRepaymentCapital(CLUtil.changeNumber2Chinese(model.getMonthlyRepayment().doubleValue()));
      model.setPrincipalInterestPerMonth(firstRepayment.getPrincipal().add(firstRepayment.getInterest()));
      model.setFeePerMonth(notNullBigDecimal(firstRepayment.getExtendFees().get("client_fee")).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setFeePerMonthCapital(CLUtil.changeNumber2Chinese(model.getFeePerMonth().doubleValue()));
      model.setFee(totalFee.setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setMonthlyFeeRate(repaymentInfo.getProductDetail().getFee().getClientFee().getRate()
          .multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setMonthlyInterestRate(repaymentInfo.getProductDetail().getInterest().getRate()
          .multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setPenaltyPerDay(repaymentInfo.getProductDetail().getFee().getPenaltyFee());
      model.setPrePaymentFee(repaymentInfo.getProductDetail().getFee().getAdvancedFee().getValue());
      model.setFirstPaybackDate(firstPaybackDate);
      model.setMonthlyPaybackDay(new DateTime(firstPaybackDate).getDayOfMonth());
      model.setEndTime(endTime);
      model.setRepaymentSchedule(repaymentScheduleSB.toString());
      model.setTechMaintenFee(notNullBigDecimal(techMaintenFee).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setMonthlyTechMaintenFeeRate(repaymentInfo.getProductDetail().getFee().getTechFeeRate()
          .multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setCustomAndTechFee(model.getFeePerMonth().add(model.getTechMaintenFee()));
    
      model.setAnnualRate(this.getAnnualRate());
      
      if(model.getFormerDiversePaybackPackagePeriods() > 0) {
    	  model.setFormerDiversePaybackPackageFee(totalFormerDiversePackageFee
    			  .divide(new BigDecimal(model.getFormerDiversePaybackPackagePeriods()), 2, BigDecimal.ROUND_HALF_UP));
    	  model.setFormerPeriodsTotalMonthlyPay(totalFormerMonthlyPay
    			  .divide(new BigDecimal(model.getFormerDiversePaybackPackagePeriods()), 2, BigDecimal.ROUND_HALF_UP));
    	  
    	  if ((repaymentInfo.getPayDetails().size() - model.getFormerDiversePaybackPackagePeriods()) > 0) {
        	  model.setLatterDiversePaybackPackageFee(totalLatterDiversePackageFee
        			  .divide(new BigDecimal(repaymentInfo.getPayDetails().size() - model.getFormerDiversePaybackPackagePeriods()), 2, BigDecimal.ROUND_HALF_UP));
        	  model.setLatterPeriodsTotalMonthlyPay(totalLatterMonthlyPay
        			  .divide(new BigDecimal(repaymentInfo.getPayDetails().size() - model.getFormerDiversePaybackPackagePeriods()), 2, BigDecimal.ROUND_HALF_UP));
    	  }

    	  model.setFormerPeriodsTotalMonthlyFee(model.getCustomAndTechFee().add(model.getFormerDiversePaybackPackageFee()));
    	  model.setLatterPeriodsTotalMonthlyFee(model.getCustomAndTechFee().add(model.getLatterDiversePaybackPackageFee()));
      }
  }
  
  private BigDecimal getAnnualRate() {
	  //default AnnualRate of shouchuang. If u use this as other fund,
	  //you should adapter for the function.
	  return new BigDecimal(0.11).setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  private void setValueAddedPackageFee(Map<String, BigDecimal> extendFee) {
	  BigDecimal packageAdvanceFee = extendFee.get("package_advance_fee");
	  BigDecimal packageInsuranceFee = extendFee.get("package_insureance_fee");
	  
	  if (packageAdvanceFee == null && packageInsuranceFee == null) {
		  model.setValueAddedPkgFlag("否");
		  model.setValueAddedPkgFee(new BigDecimal(0));
		  model.setPackageAdvanceFee(new BigDecimal(0));
		  model.setPackageInsuranceFee(new BigDecimal(0));
		  return;
	  }
	  
	  model.setValueAddedPkgFlag("是");
	  model.setValueAddedPkgFee(notNullBigDecimal(packageAdvanceFee).
			  add(notNullBigDecimal(packageInsuranceFee).setScale(2, BigDecimal.ROUND_HALF_UP)));
	  model.setPackageAdvanceFee(notNullBigDecimal(packageAdvanceFee).setScale(2, BigDecimal.ROUND_HALF_UP));
	  model.setPackageInsuranceFee(notNullBigDecimal(packageInsuranceFee).setScale(2, BigDecimal.ROUND_HALF_UP));
  }
  
  private void setAssetValueAddedPackageFee(FundFinanceProduct fundFinanceProduct,BigDecimal packageInsureanceFee) {
	  BigDecimal packageAdvanceFee = fundFinanceProduct.getPrepayment();
	  BigDecimal packageInsuranceFee =packageInsureanceFee;
	  
	  if (packageAdvanceFee == null && packageInsuranceFee == null) {
		  model.setValueAddedPkgFlag("否");
		  model.setValueAddedPkgFee(new BigDecimal(0));
		  model.setPackageAdvanceFee(new BigDecimal(0));
		  model.setPackageInsuranceFee(new BigDecimal(0));
		  return;
	  }
	  
	  model.setValueAddedPkgFlag("是");
	  model.setValueAddedPkgFee(notNullBigDecimal(packageAdvanceFee).
			  add(notNullBigDecimal(packageInsuranceFee).setScale(2, BigDecimal.ROUND_HALF_UP)));
	  model.setPackageAdvanceFee(notNullBigDecimal(packageAdvanceFee).setScale(2, BigDecimal.ROUND_HALF_UP));
	  model.setPackageInsuranceFee(notNullBigDecimal(packageInsuranceFee).setScale(2, BigDecimal.ROUND_HALF_UP));
  }
  
  private void fillExtraInfo() {
  }

  private Date getDateByEvent(List<EventModel> events, String eventName) {
    EventModel event = events.stream()
        .filter(e -> e.name.equals(eventName))
        .sorted((e1, e2) -> e2.date.compareTo(e1.date))    // find latest
        .findFirst()
        .orElse(null);

    return event == null ? null : event.date;
  }

  private String notNullString(String in) {
    return in == null ? "" : in;
  }

  private BigDecimal notNullBigDecimal(BigDecimal amount) {
    return amount == null ? BigDecimal.ZERO : amount;
  }
  
  /**
   * 通过asset系统获取申请还款计划表信息
   */
  private void fillRepaymentInfoByAsset(AssetThirdAgreementModel assetThirdAgreementModel) {

      String appId = model.getAppId();
      
      List<RepaymentSchedule> repaymentInfo = CLAgreementDataService.getRepaymentsFromAsset(assetThirdAgreementModel.getAppId());
      FundFinanceProduct fundFinanceProduct = CLAgreementDataService.getFundFinanceProductFromAsset(assetThirdAgreementModel.getRepayments());
      AllInfo allInfo = CLAgreementDataService.getRepaymentsFromPhoebus(assetThirdAgreementModel.getOldAppId());
      BigDecimal packageInsureanceFee = allInfo.getPayDetails().get(0).getExtendFees().get("package_insureance_fee");
      RepaymentSchedule firstRepayment = repaymentInfo.stream()
          .filter(item -> item.getInstallmentNum() == 1)
          .findFirst()
          .get();
      Date firstPaybackDate = firstRepayment.getDateDue();
      Date endTime = repaymentInfo.stream()
          .filter(item -> item.getInstallmentNum() == model.getRepayments())
          .findFirst()
          .get().getDateDue();
      
      BigDecimal totalFee = BigDecimal.ZERO;
      
      // {"extendFees":{"client_fee":"","tech_fee":""}}
      BigDecimal techMaintenFee = firstRepayment.getTechFee();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
      StringBuilder repaymentScheduleSB = new StringBuilder();
      for (RepaymentSchedule repaymentSchedule : repaymentInfo) {
        BigDecimal payback = repaymentSchedule.getPrincipal()
            .add(repaymentSchedule.getInterest())
            .add(notNullBigDecimal(repaymentSchedule.getClientFee()))
            .add(notNullBigDecimal(repaymentSchedule.getTechFee()))
            .add(notNullBigDecimal(fundFinanceProduct.getPrepayment()))
            .add(notNullBigDecimal(packageInsureanceFee))
            .setScale(2, BigDecimal.ROUND_HALF_UP);
        repaymentScheduleSB
          .append("<tr>")
          .append(String.format("<td>%s</td>", repaymentSchedule.getInstallmentNum()))
          .append(String.format("<td>%s</td>", sdf.format(repaymentSchedule.getDateDue())))
          .append(String.format("<td>本息服务费共【%s】元</td>", payback.toString()))
          .append("</tr>");
        
        totalFee.add(notNullBigDecimal(repaymentSchedule.getClientFee()))
            .add(notNullBigDecimal(repaymentSchedule.getTechFee()));
      }

      setAssetValueAddedPackageFee(fundFinanceProduct,packageInsureanceFee);
      
      model.setMonthlyTechMainFee(BigDecimal.ZERO);
      model.setMonthlyRepayment(firstRepayment.getPrincipal()
          .add(firstRepayment.getInterest())
          .add(notNullBigDecimal(firstRepayment.getClientFee()))
          .add(notNullBigDecimal(firstRepayment.getTechFee()))
          .add(notNullBigDecimal(fundFinanceProduct.getPrepayment()))
          .add(notNullBigDecimal(packageInsureanceFee))
          .setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setMonthlyRepaymentCapital(CLUtil.changeNumber2Chinese(model.getMonthlyRepayment().doubleValue()));
      model.setPrincipalInterestPerMonth(firstRepayment.getPrincipal().add(firstRepayment.getInterest()));
      model.setFeePerMonth(notNullBigDecimal(firstRepayment.getClientFee()).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setFeePerMonthCapital(CLUtil.changeNumber2Chinese(model.getFeePerMonth().doubleValue()));
      model.setFee(totalFee.setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setMonthlyFeeRate(fundFinanceProduct.getCustomFee()
          .multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setMonthlyInterestRate(fundFinanceProduct.getInterestRate()
          .multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setPenaltyPerDay(fundFinanceProduct.getFine());
      model.setPrePaymentFee(fundFinanceProduct.getPrepayment());
      model.setFirstPaybackDate(firstPaybackDate);
      model.setMonthlyPaybackDay(new DateTime(firstPaybackDate).getDayOfMonth());
      model.setEndTime(endTime);
      model.setRepaymentSchedule(repaymentScheduleSB.toString());
      model.setTechMaintenFee(notNullBigDecimal(techMaintenFee).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setMonthlyTechMaintenFeeRate(fundFinanceProduct.getTechFee()
          .multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
      model.setCustomAndTechFee(model.getFeePerMonth().add(model.getTechMaintenFee()));
      model.setAnnualRate(BigDecimal.ZERO);
  }

}
