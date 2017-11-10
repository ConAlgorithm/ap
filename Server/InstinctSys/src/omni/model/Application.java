package omni.model;

import catfish.base.business.object.InstallmentApplicationObject;
import omni.database.DataContainer;
import omni.database.catfish.object.InstalmentPurposeObject;
import omni.database.catfish.object.hybrid.AppProductObject;
import omni.database.mongo.DerivativeVariables;
import omni.model.type.ApplicationStatus;
import omni.model.type.RejectedType;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author guoqing
 * @version 1.1.0
 */
public class Application {

    private String appId; //	[dw].[dbo].[Application]
    private String appType; //	AppType申请类型
    private Date installmentStartedOn; //	[dw].[dbo].[Application]	(Application_Date)	
    //	private String Expire_Date;			//	same as InstallmentStartedOn	(Expiry_Date)
    private Double principal; //	[dw].[dbo].[Application]	(Amount_Limit)
    private Integer rejectedType; //	[dw].[dbo].[Application]	(Decision) (User_Field11)
    private String rejectedReason; //	[dw].[dbo].[Application]	(Decision_Reason) (User_Field12)
    private Date approvedOn; //	[dw].[dbo].[Application]	(Decision_Date) 
    private String purpose; //	[catfish].[dbo].[InstalmentApplication]->purposeId->[catfish].[dbo].[InstalmentPurpose]	(User_Field1)
    private String productType; //	(User_Field2)
    private String productName; //	[dw].[dbo].[Application]	(User_Field3)
    private String x_UbtPhoneType_Latest; //	mongodb	(User_Field4)			
    private final Integer userInputTime = null; //	[dw].[dbo].[Application]	(User_Field5)
    private Integer x_TransactionMonitorJobCount; //	(默认值-1)	(User_Field6)
    private String x_UbtTotallySpentSeconds_Summation; //	mongodb	(User_Field7)
    private Integer status; //	[dw].[dbo].[Application] (User_Field8)

    private String instinctCall; //	Customized (User_Field9)(preCheck;finalCheck;update)

    private String pdlInstalmentChannel = null; //	(User_Field13)
    private String pdlIndustryClassification = null; //	(User_Field4)

    private String IsSecondTimeUser;
    private String LastAppMaxDelayedDays;
    private String LastApplicationInterval;
    private String LastLoanStatus;
    private String LastLoanPrepaymentdays;

    /**
     * 贷款期数
     */
    private int repayments;
    /**
     * 产品价格
     */
    private BigDecimal itemPrice;
    /**
     * 首付
     */
    private BigDecimal downpayment;
    /**
     * 金融产品名称
     */
    private String financialProductName;

    /**
     * This constructor generates information for preCheck phase.
     * 
     * @param id application ID.
     * @param instCall supports "preCheck", "finalCheck" and "update".
     * @since 1.1.0
     * 
     */
    public Application(String id, String instCall) {
        if ("Precheck".equalsIgnoreCase(instCall)) {
            this.instinctCall = "Precheck";
            InstallmentApplicationObject app = DataContainer.appObj.get(id);
            this.appId = id;
            this.appType = (app.InstalmentChannel == 3) ? "PDL" : (((app.InstalmentChannel == 0) || (app.InstalmentChannel == 1)) ? "PSL" : "CL");
            this.productType = (this.appType == "PSL") ? "MobileCredit" : ((this.appType == "PDL") ? "PaydayLoan" : "CashLoan");
            this.installmentStartedOn = (Date) app.InstallmentStartedOn;
            this.approvedOn = app.ApprovedOn;
         // v20161230 add start
//            AppSecondCreditObject secondCreditObject = DataContainer.secondObj.get(id);
//            if (secondCreditObject != null) {
//                this.IsSecondTimeUser = String.valueOf(secondCreditObject.IsSecondTimeUser);
//                this.LastAppMaxDelayedDays = String.valueOf(secondCreditObject.LastAppMaxDelayedDays);
//                this.LastApplicationInterval = String.valueOf(secondCreditObject.LastApplicationInterval);
//                this.LastLoanStatus = String.valueOf(secondCreditObject.LastLoanStatus);
//                this.LastLoanPrepaymentdays = String.valueOf(secondCreditObject.LastLoanPrepaymentdays);
//
//            }
            // v20161230 add end
            // v20170606 add start
            DerivativeVariables mongodv = DataContainer.mongodv.get(id);
            if (mongodv != null) {
                this.IsSecondTimeUser = String.valueOf(mongodv.X_Loan_IsSecondTimeUser);
                this.LastAppMaxDelayedDays = String.valueOf(mongodv.X_Loan_LastAppMaxDelayedDays);
                this.LastApplicationInterval = String.valueOf(mongodv.X_Loan_LastApplicationInterval);
                this.LastLoanStatus = String.valueOf(mongodv.X_Loan_LastLoanStatus);
                this.LastLoanPrepaymentdays = String.valueOf(mongodv.X_Loan_LastLoanPrepaymentdays);
            }
            // v20170606 add end
        } else if (instCall.equalsIgnoreCase("Finalcheck")) {
            this.initialize(id);
            this.instinctCall = "Finalcheck";
        } else if (instCall.equalsIgnoreCase("Update")) {
            this.initialize(id);
            this.instinctCall = "Update";
        } else {
            this.initialize(id);
            this.instinctCall = "";
        }
    }

    /**
     * This constructor generates massive application.
     * 
     * @param id application ID.
     * 
     */
    public Application(String id) {
        this(id, "");
    }

    /**
     * @param id application id.
     */
    public final void initialize(String id) {
        InstallmentApplicationObject app = DataContainer.appObj.get(id);
        InstalmentPurposeObject purposeObj = DataContainer.purposeObj.get(id);
        AppProductObject productObj = DataContainer.productObj.get(id);
        DerivativeVariables mongodv = DataContainer.mongodv.get(id);
//        AppSecondCreditObject secondCreditObject = DataContainer.secondObj.get(id);
        this.appId = id;
        this.appType = (app.InstalmentChannel == 3) ? "PDL" : (((app.InstalmentChannel == 0) || (app.InstalmentChannel == 1)) ? "PSL" : "CL");
        this.productType = (this.appType == "PSL") ? "MobileCredit" : ((this.appType == "PDL") ? "PaydayLoan" : "CashLoan");
        this.installmentStartedOn = (Date) app.InstallmentStartedOn;
        this.principal = app.Principal.doubleValue();
        this.rejectedType = app.RejectedType;
        this.rejectedReason = app.RejectedReason;
        this.approvedOn = app.ApprovedOn;
        this.purpose = purposeObj == null ? null : purposeObj.message;

        this.productName = app.ProductName;
        if (mongodv != null) {
            this.x_UbtPhoneType_Latest = mongodv.X_UbtPhoneType_Latest;
            //		this.userInputTime = app.userInputTime;
            this.x_TransactionMonitorJobCount = mongodv.X_TransactionMonitorJobCount;
            this.x_UbtTotallySpentSeconds_Summation = mongodv.X_UbtTotallySpentSeconds_Summation;
            // v20170606 add start
            this.IsSecondTimeUser = String.valueOf(mongodv.X_Loan_IsSecondTimeUser);    
            this.LastAppMaxDelayedDays = String.valueOf(mongodv.X_Loan_LastAppMaxDelayedDays);
            this.LastApplicationInterval = String.valueOf(mongodv.X_Loan_LastApplicationInterval);
            this.LastLoanStatus = String.valueOf(mongodv.X_Loan_LastLoanStatus);
            this.LastLoanPrepaymentdays = String.valueOf(mongodv.X_Loan_LastLoanPrepaymentdays);
            // v20170606 add end
        }
        this.status = app.Status;

        // v20160802 add start
        this.repayments = app.Repayments;
        this.itemPrice = app.ItemPrice;
        this.downpayment = app.Downpayment;
        if (productObj != null) {
            this.financialProductName = productObj.name;
        }
        // v20160802 add end
        // v20161230 add start
//        if (secondCreditObject != null) {
//            this.IsSecondTimeUser = String.valueOf(secondCreditObject.IsSecondTimeUser);
//            this.LastAppMaxDelayedDays = String.valueOf(secondCreditObject.LastAppMaxDelayedDays);
//            this.LastApplicationInterval = String.valueOf(secondCreditObject.LastApplicationInterval);
//            this.LastLoanStatus = String.valueOf(secondCreditObject.LastLoanStatus);
//            this.LastLoanPrepaymentdays = String.valueOf(secondCreditObject.LastLoanPrepaymentdays);
//
//        }
        // v20161230 add end
    }

    public final String getAppId() {
        return this.appId;
    }

    public final String getAppType() {
        return this.appType;
    }

    public final Date getInstallmentStartedOn() {
        return this.installmentStartedOn;
    }

    public final Date getExpireDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getInstallmentStartedOn());
        cal.add(Calendar.DAY_OF_YEAR, +1);
        Date expireDate = cal.getTime();
        return expireDate;
    }

    public final Double getPrincipal() {
        return this.principal;
    }

    public final RejectedType getRejectedType() {
        return this.rejectedType == null ? null : RejectedType.forValue(this.rejectedType);
    }

    public final String getRejectedReason() {
        return this.rejectedReason;
    }

    public final Date getApprovedOn() {
        return this.approvedOn;
    }

    public final String getPurpose() {
        return this.purpose;
    }

    public final String getProductType() {
        return this.productType;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final String getX_UbtPhoneType_Latest() {
        return this.x_UbtPhoneType_Latest;
    }

    public final Integer getUserInputTime() {
        return this.userInputTime;
    }

    public final Integer getX_TransactionMonitorJobCount() {
        return this.x_TransactionMonitorJobCount;
    }

    public final String getX_UbtTotallySpentSeconds_Summation() {
        return this.x_UbtTotallySpentSeconds_Summation;
    }

    public final ApplicationStatus getStatus() {
        return status == null ? null : ApplicationStatus.forValue(this.status);
    }

    public final String getInstinctCall() {
        return this.instinctCall;
    }

    public final String getPDLInstalmentChannel() {
        return this.pdlInstalmentChannel;
    }

    public final String getPDLIndustryClassification() {
        return this.pdlIndustryClassification;
    }

    public int getRepayments() {
        return repayments;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public BigDecimal getDownpayment() {
        return downpayment;
    }

    public String getFinancialProductName() {
        return financialProductName;
    }

    public String getIsSecondTimeUser() {
        return IsSecondTimeUser;
    }

    public void setIsSecondTimeUser(String isSecondTimeUser) {
        IsSecondTimeUser = isSecondTimeUser;
    }

    public String getLastAppMaxDelayedDays() {
        return LastAppMaxDelayedDays;
    }

    public void setLastAppMaxDelayedDays(String lastAppMaxDelayedDays) {
        LastAppMaxDelayedDays = lastAppMaxDelayedDays;
    }

    public String getLastApplicationInterval() {
        return LastApplicationInterval;
    }

    public void setLastApplicationInterval(String lastApplicationInterval) {
        LastApplicationInterval = lastApplicationInterval;
    }

    public String getLastLoanStatus() {
        return LastLoanStatus;
    }

    public void setLastLoanStatus(String lastLoanStatus) {
        LastLoanStatus = lastLoanStatus;
    }

    public String getLastLoanPrepaymentdays() {
        return LastLoanPrepaymentdays;
    }

    public void setLastLoanPrepaymentdays(String lastLoanPrepaymentdays) {
        LastLoanPrepaymentdays = lastLoanPrepaymentdays;
    }
}
