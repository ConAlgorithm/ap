package instinct.model;

import util.InstinctizeUtil;

public class CBA 
{
    /** Text    1   Must be “B” */
	public final String category = "B";	//	Text	1	Must be “B”.
	/** Text   60  店员QQ(S1 QQ) */
	public String id_Number1;		//	Text	60	店员QQ（S1 QQ）
	/** Text   60  销售QQ(D1 QQ) */
	public String id_Number2;		//	Text	60	销售QQ（D1QQ）
	/** Text   300 店员姓名(S1 Name) */
	public String surname;			//	Text	300	店员姓名（S1 Name）
	/** Text   150 销售姓名(D1 Name) */
	public String first_Name;		//	Text	300	销售姓名（D1Name）
	/** Text   100 代理商姓名(BD2 Name) */
	public String middle_Name;		//	Text	200	代理商姓名（BD2Name）
	/** Text   150 销售姓名(D2 Name) */
    public String short_Name;
	/** Text   140 门店所在省(Provine) */
	public String home_Address1;	//	Text	140	门店所在省（Provine）
	/** Text   140 门店所在市(City) */
	public String home_Address2;	//	Text	140	门店所在市（City）
	/** Text   140 门店所在区(Area) */
	public String home_Address3;	//	Text	140	门店所在区（Area）
	/** Text   100 店员手机号(S1 Mobile) */
	public String mobile_Phone_Number;
	/** Text   100 D1手机号(D1 Mobile) */
    public String other_Phone_Number;
    /** Text   10 门店种类(StoreCategory) */
    public String user_Field1;
    /** Text   30 D2fpd7% */
    public String user_Field2;
    /** Text   30 POS批核数 */
    public String user_Field3;
    /** Text   50 POS拒绝数 */
	public String user_Field4;
	/** Text   50  门店评级标签(storeLevelResult) */
	public String user_Field5;		//	Text	50	门店评级标签(storeLevelResult)	
	/** Text   50  门店批核率(APR) */
	public String user_Field6;		//	Text	50	门店批核率(APR)
	/** Text   50  店员银行卡号(S1 BankCard) */
	public String user_Field7;		//	Text	50	店员银行卡号（S1 BankCard）
	/** Text   50  销售银行卡号(D1 BankCard) */
	public String user_Field8;		//	Text	50	销售银行卡号（D1 BankCard）
	/** Text   60 POS坏账率(POSoverduerate) */
	public String user_Field9;		//	Text	60	POS坏账率（POSoverduerate）
	/** Text   60 S3坏账率(D1overduerate) */
	public String user_Field10;		//	Text	60	S3坏账率（D1overduerate）
	/** Text   60 S1坏账率(S1overduerate) */
	public String user_Field11;		//	Text	60	S1坏账率（S1overduerate）
	/** Text   80 店员ID(S1 ID) */
	public String user_Field12;		//	Text	80	店员ID（S1ID）
	/** Text   80 销售ID(D1 ID) */
	public String user_Field13;		//	Text	80	销售ID（D1ID）
	/** Text   80 代理商ID(BD2 ID) */
	public String user_Field14;		//	Text	80	代理商ID（BD2ID）
	/** Text   40 S1批核率 */
    public String user_Field15;
    /** Text   40 D1批核数 */
    public String user_Field16;
    /** Numeric   8 S1批核数 */
    public String user_Field17;
    /** Numeric   8 S1拒绝数 */
    public String user_Field18;
    /** Numeric   8 D1批核数 */
    public String user_Field19;
    /** Numeric   8 D1拒绝数 */
    public String user_Field20;
	/** Text   MAX 进件门店(POSName) */
	public String user_Field29;		//	Text	max	进件门店（POSName）
	/** Text   MAX 用户扫码记录1 */
    public String user_Field30;
    /** Text   MAX 用户扫码记录2 */
    public String user_Field31;
    /** Text   MAX 用户扫码记录3 */
    public String user_Field32;
    
	public CBA(omni.model.CBA ia)
	{
		this.id_Number1 = InstinctizeUtil.string(ia.getS1QQ());
		this.id_Number2 = InstinctizeUtil.string(ia.getD1QQ());
		this.surname = InstinctizeUtil.string(ia.getS1Name());
		this.first_Name = InstinctizeUtil.string(ia.getD1Name());
		this.middle_Name = InstinctizeUtil.string(ia.getBD2Name());
		// v20160809 add start
		this.short_Name = InstinctizeUtil.string(ia.getD2Name());
		// v20160809 add end
		this.home_Address1 = InstinctizeUtil.string(ia.getX_MerchantProvince());
		this.home_Address2 = InstinctizeUtil.string(ia.getX_MerchantCity());
		this.home_Address3 = InstinctizeUtil.string(ia.getX_MerchantDistrict());
		this.mobile_Phone_Number = InstinctizeUtil.string(ia.getS1Mobile());
		// v20160809 add start
		this.other_Phone_Number = InstinctizeUtil.string(ia.getD1Mobile());
		this.user_Field1 = InstinctizeUtil.string(ia.getStoreCategory());
		// v20160809 add end
		// v20160830 add start
		this.user_Field2 = InstinctizeUtil.string(ia.getD2FPD7Rate());
		this.user_Field3 = InstinctizeUtil.string(ia.getPosApprovedCount());
		this.user_Field4 = InstinctizeUtil.string(ia.getPosRejectedCount());
		// v20160830 add end
		this.user_Field5 = InstinctizeUtil.string(ia.getStoreLevelResult());	
		this.user_Field6 = InstinctizeUtil.string(ia.getAPR());
		this.user_Field7 = InstinctizeUtil.string(ia.getS1BankAccount());
		this.user_Field8 = InstinctizeUtil.string(ia.getD1BankAccount());
		this.user_Field9 = InstinctizeUtil.string(ia.getPOSOverduerate());
		this.user_Field10 = InstinctizeUtil.string(ia.getS3Overduerate());
		this.user_Field11 = InstinctizeUtil.string(ia.getS1Overduerate());
		this.user_Field12 = InstinctizeUtil.string(ia.getS1Id());
		this.user_Field13 = InstinctizeUtil.string(ia.getD1Id());
		this.user_Field14 = InstinctizeUtil.string(ia.getBD2Id());
		// v20160830 add start
	    this.user_Field15 = InstinctizeUtil.string(ia.getS1ApprovedRate());
	    this.user_Field16 = InstinctizeUtil.string(ia.getD1ApprovedRate());
	    this.user_Field17 = InstinctizeUtil.string(ia.getS1ApprovedCount());
	    this.user_Field18 = InstinctizeUtil.string(ia.getS1RejectedCount());
	    this.user_Field19 = InstinctizeUtil.string(ia.getD1ApprovedCount());
	    this.user_Field20 = InstinctizeUtil.string(ia.getD1RejectedCount());
		// v20160830 add end
		this.user_Field29 = InstinctizeUtil.string(ia.getPOSName());
		// v20160809 add start
		this.user_Field30 = InstinctizeUtil.string(ia.getUserScanRecord1());
		this.user_Field31 = InstinctizeUtil.string(ia.getUserScanRecord2());
		this.user_Field32 = InstinctizeUtil.string(ia.getUserScanRecord3());
		// v20160809 add end
	}

	public CBA() 
	{
		// TODO Auto-generated constructor stub
	}
}
