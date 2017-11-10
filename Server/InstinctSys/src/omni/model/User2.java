package omni.model;

import omni.database.DataContainer;
import omni.database.mongo.DerivativeVariables;
//import omni.database.mongo.ProdMongoClient;
import omni.model.type.AddressComparisonResult;
import omni.model.type.CheckCompanyApplicantCheckResult;
import omni.model.type.CheckCompanyPhoneRelationshipResult;
import omni.model.type.CheckIOUIsSmile;
import omni.model.type.CheckIOUMobileColor;
import omni.model.type.CheckIOURingFinger;
import omni.model.type.CheckImageHeadPhotoFaceExpression;
import omni.model.type.CheckMerchantCustomerOnSpot;
import omni.model.type.CheckMerchantPhoneCallResult;
import omni.model.type.CheckPhoneCallResult;
import omni.model.type.CheckUserIdentification;
import omni.model.type.CheckUserPhoneAnswererResult;
import omni.model.type.CheckUserSoundJudgeResult;
import omni.model.type.ConsumeRegionNative;
import omni.model.type.CheckUserJXLInforCrawlStatus;
import omni.model.type.CourtExecutionResult;
import omni.model.type.JXL_GetReportStatusResult;
import omni.model.type.NoYesResult;
import omni.model.type.PDLCheckPhoneCallResult;
import omni.model.type.RepeatedConsistencyCheckResult;
import omni.model.type.ShortcutRejectReasonType;
import omni.model.type.TransactionMonitorResult;
import omni.model.type.YLZH_BankCardMobileMatch;

/**
 * Please note that x_CheckUserPhoneCallResult and x_CheckUserPhoneCallResultForPDL have different type definitions as defined in 
 * <tt>Catfish</tt> project.<p>
 * Please see {@link CheckPhoneCallResult} and {@link PDLCheckPhoneCallResult} for reference.
 * 
 * @author guoqing
 * @version 1.1.0
 *
 */
public class User2 
{
	private Integer x_CheckCompanyApplicantCheckResult;	//	(User_Field1)
	private Integer x_CheckCompanyPhoneRelationshipResult;	//	(User_Field2)
	private Integer x_CheckCourtExecuted;	//	(User_Field3) PDL same type
	
	private Integer x_CheckImageHeadPhotoFaceExpression;	//	(User_Field4)
	private Integer x_CheckIOUIsSmile;	//	(User_Field5)
	private Integer x_CheckIOUMobileColor;	//	(User_Field6)
	private Integer x_CheckIOURingFinger;	//	(User_Field7)
	private Integer x_CheckMerchantCustomerOnSpot;	//	(User_Field8)
	private Integer x_CheckMerchantPhoneCallResult;	//	(User_Field9)
	private Integer x_CheckUserIDCardAddress;	//	(User_Field10)
	private Integer x_CheckUserIdentification;	//	(User_Field11)
	private Integer x_CheckUserIsCancelApplication;	//	(User_Field12)
	
	private Integer x_CheckUserJXLInforCrawlStatus;	//	Null? (User_Field13)
	private Integer x_CheckUserPhoneAnswererResult;	//	(User_Field14)
	private Integer x_CheckUserPhoneCallResult;	//	(User_Field15)
	private Integer x_CheckUserPhoneCallResultForPDL;	//	(User_Field15) !!Note: type is different from x_CheckUserPhoneCallResult!!
	private Integer x_CheckUserSound;	//	(User_Field16)
	
	private Integer x_CheckUserSymbolicAnimal;	//	(User_Field17)
	private Integer x_CheckPersonalInfoResidenceVSCompanyAddressResult;	//	(User_Field18) 
	private Integer x_TransactionMonitorRejectReason;	//	(User_Field19)
	private Integer x_TransactionMonitorResult;	//	(User_Field20)
	
	private Integer x_JXL_GetReportStatusResult;	//	(User_Field21)
	private Boolean x_JXL_ReportData_Success;	//	(User_Field22)

	private String x_YLZH_BankCardMobileMatch;	//	(User_Field23)
	private Integer x_YLZH_IsMaxConsumptionAmountNative;	//	(User_Field24)
	private Integer x_YLZH_IsMaxConsumptionCountNative;	//	(User_Field25)
	private String x_YLZH_ConsumptionTotalAmount;	//	(User_Field26)
	private Integer x_YLZH_ConsumptionTotalCount;	//	(User_Field27)
	private Integer x_YLZH_ConsumptionTotalRegionCount;	//	(User_Field28)
	
	private Boolean x_TD_Rule_33638;	//	(User_Field29)
	private Boolean x_TD_Rule_33640;	//	(User_Field30)
	private Boolean x_TD_Rule_33642;	//	(User_Field31)
	private Boolean x_TD_Rule_33652;	//	(User_Field32)
	private Boolean x_TD_Rule_33654;	//	(User_Field33)
	
	private final Integer jyHeadPhotoCheckSimilarity = null;	//	(User_Field34)
	private final Integer jyIDCardPhotoCheckSimilarity = null;	//	(User_Field35)
	
	private final String xsHeadPhotoCheckSimilarity = null;	// (User_Field36)
	private final String xsIDCardPhotoCheckSimilarity = null;	//	(User_Field37)
	
	/** 同盾3个月内手机在本平台外的借款申请次数 */
	private Integer x_TD_Rule_33674_OuterPlatformLoanAmount;
	/** 同盾3个月内身份证在本平台外的借款申请次数 */
    private Integer x_TD_Rule_33676_OuterPlatformLoanAmount;
    /** 同盾3个月内第一联系人身份证在本平台外的借款申请次数 */
    private Integer x_TD_FirstContact_Rule33674Hit_PlatformAmount;
    /** 同盾3个月内第二联系人身份证在本平台外的借款申请次数 */
    private Integer x_TD_SecondContact_Rule33674Hit_PlatformAmount;
	
	public User2(String appId)
	{
		this(appId, "");
	}
	
	public User2(String appId, String instinctCall) 
	{
		if (!"precheck".equalsIgnoreCase(instinctCall))
		{
			this.initialize(appId);
		}
	}

	private void initialize(String appId) 
	{
		DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
		
		if (mongodv != null)
		{
			this.x_CheckCompanyApplicantCheckResult = mongodv.X_CheckCompanyApplicantCheckResult;
			this.x_CheckCourtExecuted = mongodv.X_CheckCourtExecuted == null ? mongodv.X_CheckCourtExecutedForPDL : mongodv.X_CheckCourtExecuted;
			this.x_CheckCompanyPhoneRelationshipResult = mongodv.X_CheckCompanyPhoneRelationshipResult; 
	
			this.x_CheckImageHeadPhotoFaceExpression = mongodv.X_CheckImageHeadPhotoFaceExpression == null
					? mongodv.X_CheckImageHeadPhotoFaceExpressionForPDL : mongodv.X_CheckImageHeadPhotoFaceExpression;
			this.x_CheckIOUIsSmile = mongodv.X_CheckIOUIsSmile;
			this.x_CheckIOUMobileColor = mongodv.X_CheckIOUMobileColor;
			this.x_CheckIOURingFinger = mongodv.X_CheckIOURingFinger;
			this.x_CheckMerchantCustomerOnSpot = mongodv.X_CheckMerchantCustomerOnSpot;
			this.x_CheckMerchantPhoneCallResult = mongodv.X_CheckMerchantPhoneCallResult;
			this.x_CheckUserIDCardAddress = mongodv.X_CheckUserIDCardAddress == null
					? mongodv.X_CheckUserIDCardAddressForPDL : mongodv.X_CheckUserIDCardAddress;
			this.x_CheckUserIdentification = mongodv.X_CheckUserIdentification == null
					? mongodv.X_CheckUserIdentificationForPDL : mongodv.X_CheckUserIdentification;
			this.x_CheckUserIsCancelApplication = mongodv.X_CheckUserIsCancelApplication;
			this.x_CheckUserJXLInforCrawlStatus = mongodv.X_CheckUserJXLInforCrawlStatus;	//	Null?
			this.x_CheckUserPhoneAnswererResult = mongodv.X_CheckUserPhoneAnswererResult == null
					? mongodv.X_CheckUserPhoneAnswererResultForPDL : mongodv.X_CheckUserPhoneAnswererResult;
			this.x_CheckUserPhoneCallResult = mongodv.X_CheckUserPhoneCallResult;	
			this.x_CheckUserPhoneCallResultForPDL = mongodv.X_CheckUserPhoneCallResultForPDL;	// Type is different from X_CheckUserPhoneCallResult!!
			this.x_CheckUserSound = mongodv.X_CheckUserSound == null
					? mongodv.X_CheckUserSoundForPDL : mongodv.X_CheckUserSound;
			this.x_CheckUserSymbolicAnimal = mongodv.X_CheckUserSymbolicAnimal == null
					? mongodv.X_CheckUserSymbolicAnimalForPDL : mongodv.X_CheckUserSymbolicAnimal;	
			this.x_CheckPersonalInfoResidenceVSCompanyAddressResult = mongodv.X_CheckPersonalInfoResidenceVSCompanyAddressResult;
			this.x_TransactionMonitorRejectReason = mongodv.X_TransactionMonitorRejectReason == null
					? mongodv.X_TransactionMonitorRejectReasonForPDL : mongodv.X_TransactionMonitorRejectReason;
			this.x_TransactionMonitorResult = mongodv.X_TransactionMonitorResult == null
					? mongodv.X_TransactionMonitorResultForPDL : mongodv.X_TransactionMonitorResult;
	
			this.x_YLZH_BankCardMobileMatch = mongodv.X_YLZH_BankCardMobileMatch;
			this.x_YLZH_IsMaxConsumptionAmountNative = mongodv.X_YLZH_IsMaxConsumptionAmountNative;
			this.x_YLZH_IsMaxConsumptionCountNative = mongodv.X_YLZH_IsMaxConsumptionCountNative;
			this.x_YLZH_ConsumptionTotalAmount = mongodv.X_YLZH_ConsumptionTotalAmount;
			this.x_YLZH_ConsumptionTotalCount = mongodv.X_YLZH_ConsumptionTotalCount;
			this.x_YLZH_ConsumptionTotalRegionCount = mongodv.X_YLZH_ConsumptionTotalRegionCount;
			
			this.x_JXL_GetReportStatusResult = mongodv.X_JXL_GetReportStatusResult;
			this.x_JXL_ReportData_Success = mongodv.X_JXL_ReportData_Success;
	
			this.x_TD_Rule_33638 = mongodv.X_TD_Rule_33638;
			this.x_TD_Rule_33640 = mongodv.X_TD_Rule_33640;
			this.x_TD_Rule_33642 = mongodv.X_TD_Rule_33642;
			this.x_TD_Rule_33652 = mongodv.X_TD_Rule_33652;
			this.x_TD_Rule_33654 = mongodv.X_TD_Rule_33654;
			
			// v20160809 add start
			this.x_TD_Rule_33674_OuterPlatformLoanAmount = mongodv.X_TD_Rule_33674_OuterPlatformLoanAmount;
			this.x_TD_Rule_33676_OuterPlatformLoanAmount = mongodv.X_TD_Rule_33676_OuterPlatformLoanAmount;
			this.x_TD_FirstContact_Rule33674Hit_PlatformAmount = mongodv.X_TD_FirstContact_Rule33674Hit_PlatformAmount;
			this.x_TD_SecondContact_Rule33674Hit_PlatformAmount = mongodv.X_TD_SecondContact_Rule33674Hit_PlatformAmount;
			// v20160809 add end
		}		
	}

	public final CheckCompanyApplicantCheckResult getX_CheckCompanyApplicantCheckResult()
	{
		return this.x_CheckCompanyApplicantCheckResult == null ? null
				: CheckCompanyApplicantCheckResult.forValue(this.x_CheckCompanyApplicantCheckResult);
	}
	
	public final CheckCompanyPhoneRelationshipResult getX_CheckCompanyPhoneRelationshipResult()
	{
		return this.x_CheckCompanyPhoneRelationshipResult == null ? null
				: CheckCompanyPhoneRelationshipResult.forValue(this.x_CheckCompanyPhoneRelationshipResult);
	}
	
	public final CourtExecutionResult getX_CheckCourtExecuted()
	{
		return this.x_CheckCourtExecuted == null ? null
				: CourtExecutionResult.forValue(this.x_CheckCourtExecuted);
	}
	
	public final CheckImageHeadPhotoFaceExpression getX_CheckImageHeadPhotoFaceExpression()
	{
		return this.x_CheckImageHeadPhotoFaceExpression == null ? null
				: CheckImageHeadPhotoFaceExpression.forValue(this.x_CheckImageHeadPhotoFaceExpression);
	}

	public final CheckIOUIsSmile getX_CheckIOUIsSmile()
	{
		return this.x_CheckIOUIsSmile == null ? null
				: CheckIOUIsSmile.forValue(this.x_CheckIOUIsSmile);
	}
	
	public final CheckIOUMobileColor getX_CheckIOUMobileColor()
	{
		return this.x_CheckIOUMobileColor == null ? null
				: CheckIOUMobileColor.forValue(this.x_CheckIOUMobileColor);
	}
	
	public final CheckIOURingFinger getX_CheckIOURingFinger()
	{
		return this.x_CheckIOURingFinger == null ? null 
				: CheckIOURingFinger.forValue(this.x_CheckIOURingFinger);
	}
	
	public final CheckMerchantCustomerOnSpot getX_CheckMerchantCustomerOnSpot()
	{
		return this.x_CheckMerchantCustomerOnSpot == null ? null
				: CheckMerchantCustomerOnSpot.forValue(this.x_CheckMerchantCustomerOnSpot);
	}
	
	public final CheckMerchantPhoneCallResult getX_CheckMerchantPhoneCallResult()
	{
		return this.x_CheckMerchantPhoneCallResult == null ? null
				: CheckMerchantPhoneCallResult.forValue(this.x_CheckMerchantPhoneCallResult);
	}
	
	public final RepeatedConsistencyCheckResult getX_CheckUserIDCardAddress()
	{
		return this.x_CheckUserIDCardAddress == null ? null
				: RepeatedConsistencyCheckResult.forValue(this.x_CheckUserIDCardAddress);
	}

	public final CheckUserIdentification getX_CheckUserIdentification()
	{
		return this.x_CheckUserIdentification == null ? null
				: CheckUserIdentification.forValue(this.x_CheckUserIdentification);
	}
	
	public final NoYesResult getX_CheckUserIsCancelApplication()
	{
		return this.x_CheckUserIsCancelApplication == null ? null
				: NoYesResult.forValue(this.x_CheckUserIsCancelApplication);
	}
	
	public final CheckUserJXLInforCrawlStatus getX_CheckUserJXLInforCrawlStatus()
	{
		return this.x_CheckUserJXLInforCrawlStatus == null ? null
				: CheckUserJXLInforCrawlStatus.forValue(this.x_CheckUserJXLInforCrawlStatus);
	}
	
	public final CheckUserPhoneAnswererResult getX_CheckUserPhoneAnswererResult()
	{
		return this.x_CheckUserPhoneAnswererResult == null ? null
				: CheckUserPhoneAnswererResult.forValue(this.x_CheckUserPhoneAnswererResult);
	}
	
	public final CheckPhoneCallResult getX_CheckUserPhoneCallResult()
	{
		return this.x_CheckUserPhoneCallResult == null ? null
				: CheckPhoneCallResult.forValue(this.x_CheckUserPhoneCallResult);
	}
	
	public final PDLCheckPhoneCallResult getX_CheckUserPhoneCallResultForPDL()
	{
		return this.x_CheckUserPhoneCallResultForPDL == null ? null
				: PDLCheckPhoneCallResult.forValue(this.x_CheckUserPhoneCallResultForPDL);
	}
	
	public final CheckUserSoundJudgeResult getX_CheckUserSound()
	{
		return this.x_CheckUserSound == null ? null
				: CheckUserSoundJudgeResult.forValue(this.x_CheckUserSound);
	}
	
	public final RepeatedConsistencyCheckResult getX_CheckUserSymbolicAnimal()
	{
		return this.x_CheckUserSymbolicAnimal == null ? null 
				: RepeatedConsistencyCheckResult.forValue(this.x_CheckUserSymbolicAnimal);
	}
	
	public final AddressComparisonResult getX_CompletenessCheckResidenceAndCompanyAddressComparisonResult()
	{
		return this.x_CheckPersonalInfoResidenceVSCompanyAddressResult == null ? null
				: AddressComparisonResult.forValue(this.x_CheckPersonalInfoResidenceVSCompanyAddressResult);
	}
	
	public final ShortcutRejectReasonType getX_TransactionMonitorRejectReason()
	{
		return this.x_TransactionMonitorRejectReason == null ? null
				: ShortcutRejectReasonType.forValue(this.x_TransactionMonitorRejectReason);
	}

	public final TransactionMonitorResult getX_TransactionMonitorResult()
	{
		return this.x_TransactionMonitorResult == null ? null
				: TransactionMonitorResult.forValue(this.x_TransactionMonitorResult);
	}
	
	public final JXL_GetReportStatusResult getx_JXL_GetReportStatusResult()
	{
		return this.x_JXL_GetReportStatusResult == null ? null
				: JXL_GetReportStatusResult.forValue(this.x_JXL_GetReportStatusResult);
	}
	
	public final Boolean getX_JXL_ReportData_Success()
	{
		return this.x_JXL_ReportData_Success;
	}
	
	public final YLZH_BankCardMobileMatch getX_YLZH_BankCardMobileMatch()
	{
		return this.x_YLZH_BankCardMobileMatch == null ? null
				: YLZH_BankCardMobileMatch.forValue(this.x_YLZH_BankCardMobileMatch);
	}

	public final ConsumeRegionNative getX_YLZH_IsMaxConsumptionAmountNative()
	{
		return this.x_YLZH_IsMaxConsumptionAmountNative == null ? null
				: ConsumeRegionNative.forValue(this.x_YLZH_IsMaxConsumptionAmountNative);
	}
	
	public final ConsumeRegionNative getX_YLZH_IsMaxConsumptionCountNative()
	{
		return this.x_YLZH_IsMaxConsumptionCountNative == null ? null
				: ConsumeRegionNative.forValue(this.x_YLZH_IsMaxConsumptionCountNative);
	}
	
	public final String getX_YLZH_ConsumptionTotalAmount()
	{
		return this.x_YLZH_ConsumptionTotalAmount;
	}
	
	public final Integer getX_YLZH_ConsumptionTotalCount()
	{
		return this.x_YLZH_ConsumptionTotalCount;
	}
	
	public final Integer getX_YLZH_ConsumptionTotalRegionCount()
	{
		return this.x_YLZH_ConsumptionTotalRegionCount;
	}
	
	public final Boolean getX_TD_Rule_33638()
	{
		return this.x_TD_Rule_33638;
	}

	public final Boolean getX_TD_Rule_33640()
	{
		return this.x_TD_Rule_33640;
	}
	
	public final Boolean getX_TD_Rule_33642()
	{
		return this.x_TD_Rule_33642;
	}
	
	public final Boolean getX_TD_Rule_33652()
	{
		return this.x_TD_Rule_33652;
	}
	
	public final Boolean getX_TD_Rule_33654()
	{
		return this.x_TD_Rule_33654;
	}
	
	public final Integer getJyHeadPhotoCheckSimilarity()
	{
		return this.jyHeadPhotoCheckSimilarity;
	}
	
	public final Integer getJyIDCardPhotoCheckSimilarity()
	{
		return this.jyIDCardPhotoCheckSimilarity;
	}
	
	public final String getXsHeadPhotoCheckSimilarity()
	{
		return this.xsHeadPhotoCheckSimilarity;
	}
	
	public final String getXsIDCardPhotoCheckSimilarity()
	{
		return this.xsIDCardPhotoCheckSimilarity;
	}

    public Integer getX_TD_Rule_33674_OuterPlatformLoanAmount() {
        return x_TD_Rule_33674_OuterPlatformLoanAmount;
    }

    public Integer getX_TD_Rule_33676_OuterPlatformLoanAmount() {
        return x_TD_Rule_33676_OuterPlatformLoanAmount;
    }

    public Integer getX_TD_FirstContact_Rule33674Hit_PlatformAmount() {
        return x_TD_FirstContact_Rule33674Hit_PlatformAmount;
    }

    public Integer getX_TD_SecondContact_Rule33674Hit_PlatformAmount() {
        return x_TD_SecondContact_Rule33674Hit_PlatformAmount;
    }
}
