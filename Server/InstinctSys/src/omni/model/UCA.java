package omni.model;

import omni.database.DataContainer;
import omni.database.mongo.DerivativeVariables;
import omni.model.type.CheckContactIdentificationResult;
import omni.model.type.CheckContactResultViaPhone;
import omni.model.type.PDLCheckWhetherCompanyNameAndDepartmentIsConsistent;
import omni.model.type.PDLCheckWhetherImageIsWorkPermit;
import omni.model.type.RepeatedConsistencyCheckResult;
import omni.model.type.YesNoResult;

public class UCA 
{
	private Integer x_CheckUserCompanyPaydayForPDL;	//	(Id_Number1)
	private Integer x_CheckWhetherImageIsWorkPermitForPDL;	//	(Id_Number2)
	private Integer x_CheckThirdContactCompanyNameResultPDL;	//	(Id_Number3)	TODO: add unittest
	private Integer x_CheckWhetherImageIsGroupPhotoForPDL;	//	(Surname)
	private Integer x_CheckThirdContactMarriageStatusResultForPDL;	//	(First_Name)
	private String f1Id = null;	//	(Middle_Name)
	private Integer x_CheckSecondContactMarriageStatusResultForPDL;	//	(User_Field1)
	private Integer x_CheckSecondContactCompanyNameResultPDL;	//	(User_Field2)
	private Integer x_CheckUserCompanyNameForPDL;	//	(User_Field3)
	private Integer x_CheckSecondContactIdentificationResultForPDL;	//	(User_Field4)
	private Integer x_CheckUserIDCardAddressForPDL;	//	(User_Field6)
	private Integer x_CheckFirstContactMarriageStatusResultForPDL;	//	(User_Field7)
	private Integer x_CheckBuckleDoesSignatureAndIdNameMatchForPDL;	//	(User_Field8)
	private Integer x_CheckWhetherWorkPermitOfficialSealConsistentForPDL;	//	(User_Field9)
	private Integer x_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL;	//	(User_Field10)

	private String x_HistoricalLoanAverageDays;	//	(home_Address1)	
	private String x_HistoricalLoanDayAwayFromPayDayAverageDays;	//	(home_Address2)	
	private Integer x_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount;	//	(home_Address3)	
	private String x_HistoricalGPSAwayFromApplicationPostionMaxDistance;	//	(home_Address4)	
	private String x_HistoricalGPSAwayFromApplicationPostionAverageDistance;	//	(home_Address5)	
	private String x_CurrentGPSAwayFromApplicationPostionDistance;	//	(company_Address1)
	private String x_InputIdCardInfoAddressForPDL;	//	(company_Address2)	
	private String x_HistoricalLoanAverageLimitAmount;	//	(user_Field11)
	
	public UCA(String appId)
	{
		this(appId, "");
	}
	
	public UCA(String appId, String instinctCall) 
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
			this.x_CheckUserCompanyPaydayForPDL = mongodv.X_CheckUserCompanyPaydayForPDL;	
			this.x_CheckWhetherImageIsWorkPermitForPDL = mongodv.X_CheckWhetherImageIsWorkPermitForPDL;	
			this.x_CheckThirdContactCompanyNameResultPDL = mongodv.X_CheckThirdContactCompanyNameResultPDL;	
			this.x_CheckWhetherImageIsGroupPhotoForPDL = mongodv.X_CheckWhetherImageIsGroupPhotoForPDL;
			this.x_CheckThirdContactMarriageStatusResultForPDL = mongodv.X_CheckThirdContactMarriageStatusResultForPDL;	
			this.x_CheckSecondContactMarriageStatusResultForPDL = mongodv.X_CheckSecondContactMarriageStatusResultForPDL;
			this.x_CheckSecondContactCompanyNameResultPDL = mongodv.X_CheckSecondContactCompanyNameResultPDL;	
			this.x_CheckUserCompanyNameForPDL = mongodv.X_CheckUserCompanyNameForPDL;	
			this.x_CheckSecondContactIdentificationResultForPDL = mongodv.X_CheckSecondContactIdentificationResultForPDL;	
			this.x_CheckUserIDCardAddressForPDL = mongodv.X_CheckUserIDCardAddressForPDL;	
			this.x_CheckFirstContactMarriageStatusResultForPDL = mongodv.X_CheckFirstContactMarriageStatusResultForPDL;	
			this.x_CheckBuckleDoesSignatureAndIdNameMatchForPDL = mongodv.X_CheckBuckleDoesSignatureAndIdNameMatchForPDL;	
			this.x_CheckWhetherWorkPermitOfficialSealConsistentForPDL = mongodv.X_CheckWhetherWorkPermitOfficialSealConsistentForPDL;	
			this.x_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL = mongodv.X_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL;	
			this.x_HistoricalLoanAverageDays = mongodv.X_HistoricalLoanAverageDays;	
			this.x_HistoricalLoanDayAwayFromPayDayAverageDays = mongodv.X_HistoricalLoanDayAwayFromPayDayAverageDays;	
			this.x_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount = mongodv.X_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount;
			this.x_HistoricalGPSAwayFromApplicationPostionMaxDistance = mongodv.X_HistoricalGPSAwayFromApplicationPostionMaxDistance;
			this.x_HistoricalGPSAwayFromApplicationPostionAverageDistance = mongodv.X_HistoricalGPSAwayFromApplicationPostionAverageDistance;
			this.x_CurrentGPSAwayFromApplicationPostionDistance = mongodv.X_CurrentGPSAwayFromApplicationPostionDistance;
			this.x_InputIdCardInfoAddressForPDL = mongodv.X_InputIdCardInfoAddressForPDL;
			this.x_HistoricalLoanAverageLimitAmount = mongodv.X_HistoricalLoanAverageLimitAmount;
		}
	}

	public final String getF1Id()
	{
		return this.f1Id;
	}
	
	public final RepeatedConsistencyCheckResult getX_CheckUserCompanyPaydayForPDL()
	{
		return this.x_CheckUserCompanyPaydayForPDL == null ? null
				: RepeatedConsistencyCheckResult.forValue(this.x_CheckUserCompanyPaydayForPDL);
	}
	
	public final PDLCheckWhetherImageIsWorkPermit getX_CheckWhetherImageIsWorkPermitForPDL()
	{
		return this.x_CheckWhetherImageIsWorkPermitForPDL == null ? null
				: PDLCheckWhetherImageIsWorkPermit.forValue(this.x_CheckWhetherImageIsWorkPermitForPDL);
	}
	
	public final CheckContactResultViaPhone getX_CheckThirdContactCompanyNameResultPDL()
	{
		return this.x_CheckThirdContactCompanyNameResultPDL == null ? null
				: CheckContactResultViaPhone.forValue(this.x_CheckThirdContactCompanyNameResultPDL);
	}

	public final YesNoResult getX_CheckWhetherImageIsGroupPhotoForPDL()
	{
		return this.x_CheckWhetherImageIsGroupPhotoForPDL == null ? null
				: YesNoResult.forValue(this.x_CheckWhetherImageIsGroupPhotoForPDL);
	}
	
	public final CheckContactResultViaPhone getX_CheckThirdContactMarriageStatusResultForPDL()
	{
		return this.x_CheckThirdContactMarriageStatusResultForPDL == null ? null
				: CheckContactResultViaPhone.forValue(this.x_CheckThirdContactMarriageStatusResultForPDL);
	}
	
	public final CheckContactResultViaPhone getX_CheckSecondContactMarriageStatusResultForPDL()
	{
		return this.x_CheckSecondContactMarriageStatusResultForPDL == null ? null 
				: CheckContactResultViaPhone.forValue(this.x_CheckSecondContactMarriageStatusResultForPDL);
	}
	
	public final CheckContactResultViaPhone getX_CheckSecondContactCompanyNameResultPDL()
	{
		return this.x_CheckSecondContactCompanyNameResultPDL == null ? null
				: CheckContactResultViaPhone.forValue(this.x_CheckSecondContactCompanyNameResultPDL);
	}
	
	public final RepeatedConsistencyCheckResult getX_CheckUserCompanyNameForPDL()
	{
		return this.x_CheckUserCompanyNameForPDL == null ? null
				: RepeatedConsistencyCheckResult.forValue(this.x_CheckUserCompanyNameForPDL);
	}
	
	public final CheckContactIdentificationResult getX_CheckSecondContactIdentificationResultForPDL()
	{
		return this.x_CheckSecondContactIdentificationResultForPDL == null ? null
				: CheckContactIdentificationResult.forValue(this.x_CheckSecondContactIdentificationResultForPDL);
	}

	public final RepeatedConsistencyCheckResult getX_CheckUserIDCardAddressForPDL()
	{
		return this.x_CheckUserIDCardAddressForPDL == null ? null
				: RepeatedConsistencyCheckResult.forValue(this.x_CheckUserIDCardAddressForPDL);
	}
	
	public final CheckContactResultViaPhone getX_CheckFirstContactMarriageStatusResultForPDL()
	{
		return this.x_CheckFirstContactMarriageStatusResultForPDL == null ? null
				: CheckContactResultViaPhone.forValue(this.x_CheckFirstContactMarriageStatusResultForPDL);
	}
	
	public final RepeatedConsistencyCheckResult getX_CheckBuckleDoesSignatureAndIdNameMatchForPDL()
	{
		return this.x_CheckBuckleDoesSignatureAndIdNameMatchForPDL == null ? null
				: RepeatedConsistencyCheckResult.forValue(this.x_CheckBuckleDoesSignatureAndIdNameMatchForPDL);
	}
	
	public final RepeatedConsistencyCheckResult getX_CheckWhetherWorkPermitOfficialSealConsistentForPDL()
	{
		return this.x_CheckWhetherWorkPermitOfficialSealConsistentForPDL == null ? null
				: RepeatedConsistencyCheckResult.forValue(this.x_CheckWhetherWorkPermitOfficialSealConsistentForPDL);
	}
	
	public final PDLCheckWhetherCompanyNameAndDepartmentIsConsistent getX_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL()
	{
		return this.x_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL == null ? null
				: PDLCheckWhetherCompanyNameAndDepartmentIsConsistent.forValue(this.x_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL);
	}

	public final String getX_HistoricalLoanAverageDays() 
	{
		return this.x_HistoricalLoanAverageDays;
	}

	public final String getX_HistoricalLoanDayAwayFromPayDayAverageDays() 
	{
		return this.x_HistoricalLoanDayAwayFromPayDayAverageDays;
	}

	public final Integer getX_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount() 
	{
		return this.x_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount;
	}

	public final String getX_HistoricalGPSAwayFromApplicationPostionMaxDistance() 
	{
		return this.x_HistoricalGPSAwayFromApplicationPostionMaxDistance;
	}

	public final String getX_HistoricalGPSAwayFromApplicationPostionAverageDistance() 
	{
		return this.x_HistoricalGPSAwayFromApplicationPostionAverageDistance;
	}

	public final String getX_CurrentGPSAwayFromApplicationPostionDistance() 
	{
		return this.x_CurrentGPSAwayFromApplicationPostionDistance;
	}

	public final String getX_InputIdCardInfoAddressForPDL() 
	{
		return this.x_InputIdCardInfoAddressForPDL;
	}

	public final String getX_HistoricalLoanAverageLimitAmount() 
	{
		return this.x_HistoricalLoanAverageLimitAmount;
	}
	
}
