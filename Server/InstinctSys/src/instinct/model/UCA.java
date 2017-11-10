package instinct.model;

import util.InstinctizeUtil;

public class UCA 
{
	public final String category = "W";	//	Text	1	Must be “W”.
	public String id_Number1;	//	Text	60	PDL-客户电话审核_单位信息核实(checkUserCompanyPaydayForPDL)
	public String id_Number2;	//	Text	60	PDL-工作证检查_是否是工作证(checkWhetherImageIsWorkPermitForPDL)
	public String id_Number3;		//	Text	300	PDL-第三联系人电话审核V3_关系核身单位名称($(Relation))(checkThirdContactCompanyNameResultPDL)
	public String surname;	//	Text	300	PDL-合影检查_是否是合影(checkWhetherImageIsGroupPhotoForPDL)
	public String first_Name;	//	Text	200	PDL-第三联系人电话审核V3_关系核身婚姻状况($(Relation))(checkThirdContactMarriageStatusResultForPDL)
	public String middle_Name;	//	Text	60	PDL产品F1号（f1Id)
//	public String Sex;		//	Text	2	
//	public String Date_Of_Birth;	//	Date	8	
	public String home_Address1;	//	Text	140	PDL历史平均借款天数pdlhistoricalLoanAverageDays	
	public String home_Address2;	//	Text	140	PDL历史借款日离发薪日平均天数pdlhistoricalLoanDayAwayFromPayDayAverageDays
	public String home_Address3;	//	Text	140	PDL历史GPS定位距离申请地大于5km的总次数pdlhistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount
	public String home_Address4;	//	Text	140	PDL历史GPS定位距离申请时的最大距离(km)pdlhistoricalGPSAwayFromApplicationPostionMaxDistance
	public String home_Address5;	//	Text	140	PDL历史GPS定位距离申请时的平均距离(km)pdlhistoricalGPSAwayFromApplicationPostionAverageDistance
//	public String Home_Address6;	//	Text	140	
//	public String Home_Postcode;	//	Text	20	
//	public String Home_Phone_Number;	//	Text	64	
//	public String Mobile_Phone_Number;	//	Text	64	
//	public String Company_Name;		//	Text	300	
	public String company_Address1;	//	Text	140	PDL当前GPS定位距离申请时的距离(km)pdlcurrentGPSAwayFromApplicationPostionDistance
	public String company_Address2;	//	Text	140	PDL身份信息录入证件地址(PdlCertificateAdress)
//	public String company_Address3;	//	Text	140	
//	public String company_Address4;	//	Text	140	
//	public String company_Address5;	//	Text	140	
//	public String Company_Address6;	//	Text	140	
//	public String Company_Postcode;	//	Text	20	
//	public String Company_Phone_Number;	//	Text	64	
	public String user_Field1;		//	Text	60	PDL-第二联系人电话审核V3_关系核身婚姻状况($(Relation))(checkSecondContactMarriageStatusResultForPDL)
	public String user_Field2;		//	Text	60	PDL-第二联系人电话审核V3_关系核身单位名称($(Relation))(checkSecondContactCompanyNameResultPDL)
	public String user_Field3;		//	Numeric	8	PDL-客户电话审核_单位名称确认(checkUserCompanyNameForPDL)
	public String user_Field4;		//	Numeric	8	PDL-第二联系人电话审核V3_申请人关系审核(checkSecondContactIdentificationResultForPDL)
//	public String User_Field5;		//	Date	8	
	public String user_Field6;		//	Text	100	PDL-客户电话审核_身份证住址确认(checkUserIDCardAddressForPDL)
	public String user_Field7;		//	Text	100	PDL-第一联系人电话审核V3_关系核身婚姻状况($(Relation))(checkFirstContactMarriageStatusResultForPDL)
	public String user_Field8;		//	Text	100	PDL-代扣协议审核V3_客户代扣协议照片中客户填写信息与申请人信息是否一致(checkBuckleDoesSignatureAndIdNameMatchForPDL)
	public String user_Field9;		//	Text	100	PDL-工作证检查_公章(checkWhetherWorkPermitOfficialSealConsistentForPDL)
	public String user_Field10;	//	Text	100	PDL-工作证检查_公司名/部门信息比对(checkWhetherCompanyNameAndDepartmentIsConsistentForPDL)
	public String user_Field11;	//	Numeric	8	PDL历史平均额度pdlhistoricalLoanAverageLimitAmount
//	public double User_Field12;	//	Numeric	8	
//	public double User_Field13;	//	Numeric	8	
//	public double User_Field14;	//	Numeric	8	
//	public double User_Field15;	//	Numeric	8	
//	public String User_Field16;	//	Text	max	
//	public String User_Field17;	//	Text	max	
//	public String User_Field18;	//	Text	max	
//	public String User_Field19;	//	Text	max	
//	public String User_Field20;	//	Text	max	
	
	public UCA(omni.model.UCA posuca)
	{
		this.id_Number1 = InstinctizeUtil.string(posuca.getX_CheckUserCompanyPaydayForPDL());
		this.id_Number2 = InstinctizeUtil.string(posuca.getX_CheckWhetherImageIsWorkPermitForPDL());
		this.id_Number3 = InstinctizeUtil.string(posuca.getX_CheckThirdContactCompanyNameResultPDL());
		this.surname = InstinctizeUtil.string(posuca.getX_CheckWhetherImageIsGroupPhotoForPDL());
		this.first_Name = InstinctizeUtil.string(posuca.getX_CheckThirdContactMarriageStatusResultForPDL());
		this.middle_Name = InstinctizeUtil.string(posuca.getF1Id());
		this.home_Address1 = InstinctizeUtil.string(posuca.getX_HistoricalLoanAverageDays());		
		this.home_Address2 = InstinctizeUtil.string(posuca.getX_HistoricalLoanDayAwayFromPayDayAverageDays());	
		this.home_Address3 = InstinctizeUtil.string(posuca.getX_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount());	
		this.home_Address4 = InstinctizeUtil.string(posuca.getX_HistoricalGPSAwayFromApplicationPostionMaxDistance());	
		this.home_Address5 = InstinctizeUtil.string(posuca.getX_HistoricalGPSAwayFromApplicationPostionAverageDistance());	
		this.company_Address1 = InstinctizeUtil.string(posuca.getX_CurrentGPSAwayFromApplicationPostionDistance());	
		this.company_Address2 = InstinctizeUtil.string(posuca.getX_InputIdCardInfoAddressForPDL());	
		this.user_Field1 = InstinctizeUtil.string(posuca.getX_CheckSecondContactMarriageStatusResultForPDL());
		this.user_Field2 = InstinctizeUtil.string(posuca.getX_CheckSecondContactCompanyNameResultPDL());
		this.user_Field3 = InstinctizeUtil.string(posuca.getX_CheckUserCompanyNameForPDL());
		this.user_Field4 = InstinctizeUtil.string(posuca.getX_CheckSecondContactIdentificationResultForPDL());
		this.user_Field6 = InstinctizeUtil.string(posuca.getX_CheckUserIDCardAddressForPDL());
		this.user_Field7 = InstinctizeUtil.string(posuca.getX_CheckFirstContactMarriageStatusResultForPDL());
		this.user_Field8 = InstinctizeUtil.string(posuca.getX_CheckBuckleDoesSignatureAndIdNameMatchForPDL());
		this.user_Field9 = InstinctizeUtil.string(posuca.getX_CheckWhetherWorkPermitOfficialSealConsistentForPDL());
		this.user_Field10 = InstinctizeUtil.string(posuca.getX_CheckWhetherCompanyNameAndDepartmentIsConsistentForPDL());
		this.user_Field11 = InstinctizeUtil.string(posuca.getX_HistoricalLoanAverageLimitAmount());	
	}

	public UCA() 
	{
		// TODO Auto-generated constructor stub
	}
}
