package instinct.model;

import util.InstinctizeUtil;

public class U2A 
{
    /** Text    1   Must be “F” */
	public final String category = "F";	
	/** Text   50 客户电核首付金额(X_CheckUserDownPayment) */
	public String user_Field1;
	/** Text   50 客户电核购机价格(X_CheckUserProductPrice) */
	public String user_Field2;
	/** Text   50 外包个人信息审核是否信贷类公司(X_CheckPersonalInfoCompanyTypeResult) */
	public String user_Field3;
	/** Text   50 外包收集身份证地址(X_InputIdCardInfoAddress) */
	public String user_Field4;
	/** Text   50 D1合影审核客户照片判断是否同一人(X_CheckWhetherCustomerPhotoIsTheSamePerson) */
	public String user_Field5;
	/** Text   50 D1合影审核D1照片判断是否同一人(X_CheckWhetherDPhotoIsTheSamePerson) */
	public String user_Field6;
	/** Text   50 外包头像对比三张照片是否一致(X_CheckImageHeadPhotoPersonalPic) */
	public String user_Field7;
	/** Text   50 依图比对相似度(X_YT_Similarity) */
	public String user_Field8;
	/** Text   50 查询照对证件照（网纹照）的相似度(X_YT_SimilarityQueryDatabase) */
	public String user_Field9;
	/** Text   50 查询照对翻拍身份证照的相似度(X_YT_SimilarityQueryIdcard) */
	public String user_Field10;
	/** Text   50 翻拍身份证照对证件照（网纹照）的相似度(X_YT_SimilarityIdcardDatabase) */
    public String user_Field11;
    /** Text   50 依图比对是否通过(X_YT_IsPass) */
    public String user_Field12;
    
    /** Text   50 用户总申请次数(X_GroupInfoUserTotalCount) */
    public String user_Field13;
    /** Text   50 用户总申请批准次数(X_GroupInfoUserApprovedCount) */
    public String user_Field14;
    /** Text   50 用户总申请拒绝次数(X_GroupInfoUserRejectedCount) */
    public String user_Field15;
    /** Text   50 团内批核率[status>=40](X_GroupInfoUserApprovedRate) */
    public String user_Field16;
    /** Text   50 拒绝的黑名单人数(X_GroupInfoBlackListCount) */
    public String user_Field17;
    /** Text   50 拒绝的黑名单率(X_GroupInfoBlackListCountRate) */
    public String user_Field18;
    /** Text   50 拒绝的拉警报人数(X_GroupInfoReportedRejectedCount) */
    public String user_Field19;
    /** Text   50 拒绝的拉警报率(X_GroupInfoReportedRejectedCountRate) */
    public String user_Field20;
    /** Text   50 批核件fpd7已表现人数(X_GroupInfoUserFPD7Count) */
    public String user_Field21;
    /** Text   50 批核件fpd7%(X_GroupInfoUserFPD7Rate) */
    public String user_Field22;
    /** Text   50 批核件fpd30已表现人数(X_GroupInfoUserFPD30Count) */
    public String user_Field23;
    /** Text   50 批核件fpd30%(X_GroupInfoUserFPD30Rate) */
    public String user_Field24;
    /** Text   50 批核件fs30已表现人数(X_GroupInfoUserFS30Count) */
    public String user_Field25;
    /** Text   50 批核件fs30%(X_GroupInfoUserFS30Rate) */
    public String user_Field26;
    /** Text   50 批核件fst30已表现人数(X_GroupInfoUserFST30Count) */
    public String user_Field27;
    /** Text   50 批核件fst30%(X_GroupInfoUserFST30Rate) */
    public String user_Field28;
    /** Text   50 批核件fstq30已表现人数(X_GroupInfoUserFSTQ30Count) */
    public String user_Field29;
    /** Text   50 批核件fstq30%(X_GroupInfoUserFSTQ30Rate) */
    public String user_Field30;
    /** Text   100 批核件m3+%(X_GroupInfoUserM3PlusRate) */
    public String user_Field31;
    /** Text   100 团最近3天申请人数(X_GroupInfoUserAppRecent3daysCount) */
    public String user_Field32;
    /** Text   100 团最近7天申请人数(X_GroupInfoUserAppRecent7daysCount) */
    public String user_Field33;
    /** Text   100 团最近一个月申请人数(X_GroupInfoUserAppRecent1monthCount) */
    public String user_Field34;
    /** Text   100 团最近三个月申请人数(X_GroupInfoUserAppRecent3monthsCount) */
    public String user_Field35;
    /** Numeric   8 团欺诈拒绝人数(X_GroupInfoFausd) */
    public String user_Field36;
    /** Text   max 团内申请地最大集中省份(X_GroupInfoUserMainProvince) */
    public String user_Field56;
    /** Text   max 团内申请地最大集中城市(X_GroupInfoUserMainCity) */
    public String user_Field57;
	
	public U2A(omni.model.U2A u2a)
	{
		this.user_Field1 = InstinctizeUtil.string(u2a.getX_CheckUserDownPayment());
		this.user_Field2 = InstinctizeUtil.string(u2a.getX_CheckUserProductPrice());
		this.user_Field3 = InstinctizeUtil.string(u2a.getX_CheckPersonalInfoCompanyTypeResult());
		this.user_Field4 = InstinctizeUtil.string(u2a.getX_InputIdCardInfoAddress());
		this.user_Field5 = InstinctizeUtil.string(u2a.getX_CheckWhetherCustomerPhotoIsTheSamePerson());
		this.user_Field6 = InstinctizeUtil.string(u2a.getX_CheckWhetherDPhotoIsTheSamePerson());
		this.user_Field7 = InstinctizeUtil.string(u2a.getX_CheckImageComparision());
	    this.user_Field8 = InstinctizeUtil.string(u2a.getX_YT_Similarity());
        this.user_Field9 = InstinctizeUtil.string(u2a.getX_YT_SimilarityQueryDatabase());
        this.user_Field10 = InstinctizeUtil.string(u2a.getX_YT_SimilarityQueryIdcard());
        this.user_Field11 = InstinctizeUtil.string(u2a.getX_YT_SimilarityIdcardDatabase());
        this.user_Field12 = InstinctizeUtil.string(u2a.getX_YT_IsPass());
        // v20160830 add start
        this.user_Field13 = InstinctizeUtil.string(u2a.getX_GroupInfoUserTotalCount());
        this.user_Field14 = InstinctizeUtil.string(u2a.getX_GroupInfoUserApprovedCount());
        this.user_Field15 = InstinctizeUtil.string(u2a.getX_GroupInfoUserRejectedCount());
        this.user_Field16 = InstinctizeUtil.string(u2a.getX_GroupInfoUserApprovedRate());
        this.user_Field17 = InstinctizeUtil.string(u2a.getX_GroupInfoBlackListCount());
        this.user_Field18 = InstinctizeUtil.string(u2a.getX_GroupInfoBlackListCountRate());
        this.user_Field19 = InstinctizeUtil.string(u2a.getX_GroupInfoReportedRejectedCount());
        this.user_Field20 = InstinctizeUtil.string(u2a.getX_GroupInfoReportedRejectedCountRate());
        this.user_Field21 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFPD7Count());
        this.user_Field22 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFPD7Rate());
        this.user_Field23 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFPD30Count());
        this.user_Field24 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFPD30Rate());
        this.user_Field25 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFS30Count());
        this.user_Field26 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFS30Rate());
        this.user_Field27 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFST30Count());
        this.user_Field28 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFST30Rate());
        this.user_Field29 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFSTQ30Count());
        this.user_Field30 = InstinctizeUtil.string(u2a.getX_GroupInfoUserFSTQ30Rate());
        this.user_Field31 = InstinctizeUtil.string(u2a.getX_GroupInfoUserM3PlusRate());
        this.user_Field32 = InstinctizeUtil.string(u2a.getX_GroupInfoUserAppRecent3daysCount());
        this.user_Field33 = InstinctizeUtil.string(u2a.getX_GroupInfoUserAppRecent7daysCount());
        this.user_Field34 = InstinctizeUtil.string(u2a.getX_GroupInfoUserAppRecent1monthCount());
        this.user_Field35 = InstinctizeUtil.string(u2a.getX_GroupInfoUserAppRecent3monthsCount());
        this.user_Field36 = InstinctizeUtil.string(u2a.getX_GroupInfoFausd());
        this.user_Field56 = InstinctizeUtil.string(u2a.getX_GroupInfoUserMainProvince());
        this.user_Field57 = InstinctizeUtil.string(u2a.getX_GroupInfoUserMainCity());
        // v20160830 add end
	}

	public U2A() 
	{
		// TODO Auto-generated constructor stub
	}
}
