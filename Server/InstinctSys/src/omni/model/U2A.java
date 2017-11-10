package omni.model;

import catfish.base.business.common.CompanyTypeResult;
import omni.database.DataContainer;
import omni.database.mongo.DerivativeVariables;
import omni.model.type.CheckImageComparisionResult;
import omni.model.type.CheckWhetherCustomerPhotoIsTheSamePersonResult;
import omni.model.type.CheckWhetherDPhotoIsTheSamePersonResult;
import util.EnumUtil;

/**
 * 
 * @author baowzh
 * @version 1.0.0
 *
 */
public class U2A 
{
	/** 客户电核首付金额 */
	private String x_CheckUserDownPayment;
	/** 客户电核购机价格 */
    private String x_CheckUserProductPrice;
    /** 外包个人信息审核是否信贷类公司 */
    private String x_CheckPersonalInfoCompanyTypeResult;
    /** 外包收集身份证地址 */
    private String x_InputIdCardInfoAddress;
    /** D1合影审核客户照片判断是否同一人 */
    private String x_CheckWhetherCustomerPhotoIsTheSamePerson;
    /** D1合影审核D1照片判断是否同一人 */
    private String x_CheckWhetherDPhotoIsTheSamePerson;
    /** 外包头像对比三张照片是否一致 */
    private String x_CheckImageComparision;
    /** 依图比对相似度 */
    private String x_YT_Similarity;
    /** 查询照对证件照（网纹照）的相似度 */
    private String x_YT_SimilarityQueryDatabase;
    /** 查询照对翻拍身份证照的相似度 */
    private String x_YT_SimilarityQueryIdcard;
    /** 翻拍身份证照对证件照（网纹照）的相似度 */
    private String x_YT_SimilarityIdcardDatabase;
    /** 依图比对是否通过 */
    private String x_YT_IsPass;
    
    /** 用户总申请次数 */
    private Integer x_GroupInfoUserTotalCount;
    /** 用户总申请批准次数 */
    private Integer x_GroupInfoUserApprovedCount;
    /** 用户总申请拒绝次数 */
    private Integer x_GroupInfoUserRejectedCount;
    /** 团内批核率[status>=40] */
    private String x_GroupInfoUserApprovedRate;
    /** 拒绝的黑名单人数 */
    private String x_GroupInfoBlackListCount;
    /** 拒绝的黑名单率 */
    private String x_GroupInfoBlackListCountRate;
    /** 拒绝的拉警报人数 */
    private String x_GroupInfoReportedRejectedCount;
    /** 拒绝的拉警报率 */
    private String x_GroupInfoReportedRejectedCountRate;
    /** 批核件fpd7已表现人数 */
    private String x_GroupInfoUserFPD7Count;
    /** 批核件fpd7% */
    private String x_GroupInfoUserFPD7Rate;
    /** 批核件fpd30已表现人数 */
    private Integer x_GroupInfoUserFPD30Count;
    /** 批核件fpd30% */
    private String x_GroupInfoUserFPD30Rate;
    /** 批核件fs30已表现人数 */
    private String x_GroupInfoUserFS30Count;
    /** 批核件fs30% */
    private String x_GroupInfoUserFS30Rate;
    /** 批核件fst30已表现人数 */
    private String x_GroupInfoUserFST30Count;
    /** 批核件fst30% */
    private String x_GroupInfoUserFST30Rate;
    /** 批核件fstq30已表现人数 */
    private String x_GroupInfoUserFSTQ30Count;
    /** 批核件fstq30% */
    private String x_GroupInfoUserFSTQ30Rate;
    /** 批核件m3+% */
    private String x_GroupInfoUserM3PlusRate;
    /** 团最近3天申请人数 */
    private String x_GroupInfoUserAppRecent3daysCount;
    /** 团最近7天申请人数 */
    private String x_GroupInfoUserAppRecent7daysCount;
    /** 团最近一个月申请人数 */
    private String x_GroupInfoUserAppRecent1monthCount;
    /** 团最近三个月申请人数 */
    private String x_GroupInfoUserAppRecent3monthsCount;
    /** 团内申请地最大集中省份 */
    private String x_GroupInfoUserMainProvince;
    /** 团内申请地最大集中城市 */
    private String x_GroupInfoUserMainCity;
    /** 团欺诈拒绝人数 */
    private String x_GroupInfoFausd;

    public U2A(String appId)
	{
		this(appId, "");
	}
	
	public U2A(String appId, String instinctCall) 
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
		    this.x_CheckUserDownPayment = mongodv.X_CheckUserDownPayment;
		    this.x_CheckUserProductPrice = mongodv.X_CheckUserProductPrice;
		    this.x_CheckPersonalInfoCompanyTypeResult = EnumUtil.getText(CompanyTypeResult.class, mongodv.X_CheckPersonalInfoCompanyTypeResult);
		    this.x_InputIdCardInfoAddress = mongodv.X_InputIdCardInfoAddress == null ? mongodv.X_InputIdCardInfoAddressForPDL : mongodv.X_InputIdCardInfoAddress;
		    this.x_CheckWhetherCustomerPhotoIsTheSamePerson = EnumUtil.getText(CheckWhetherCustomerPhotoIsTheSamePersonResult.class, mongodv.X_CheckWhetherCustomerPhotoIsTheSamePerson);
		    this.x_CheckWhetherDPhotoIsTheSamePerson = EnumUtil.getText(CheckImageComparisionResult.class, mongodv.X_CheckWhetherDPhotoIsTheSamePerson);
		    this.x_CheckImageComparision = EnumUtil.getText(CheckWhetherDPhotoIsTheSamePersonResult.class, mongodv.X_CheckImageComparision);;
		    this.x_YT_Similarity = mongodv.X_YT_Similarity;
		    this.x_YT_SimilarityQueryDatabase = mongodv.X_YT_SimilarityQueryDatabase;
	        this.x_YT_SimilarityQueryIdcard = mongodv.X_YT_SimilarityQueryIdcard;
	        this.x_YT_SimilarityIdcardDatabase = mongodv.X_YT_SimilarityIdcardDatabase;
	        this.x_YT_IsPass = mongodv.X_YT_IsPass;
	        // v20160830 add start
	        this.x_GroupInfoUserTotalCount = mongodv.X_GroupInfoUserTotalCount;
	        this.x_GroupInfoUserApprovedCount = mongodv.X_GroupInfoUserApprovedCount;
	        this.x_GroupInfoUserRejectedCount = mongodv.X_GroupInfoUserRejectedCount;
	        this.x_GroupInfoUserApprovedRate = mongodv.X_GroupInfoUserApprovedRate;
	        this.x_GroupInfoBlackListCount = mongodv.X_GroupInfoBlackListCount;
	        this.x_GroupInfoBlackListCountRate = mongodv.X_GroupInfoBlackListCountRate;
	        this.x_GroupInfoReportedRejectedCount = mongodv.X_GroupInfoReportedRejectedCount;
	        this.x_GroupInfoReportedRejectedCountRate = mongodv.X_GroupInfoReportedRejectedCountRate;
	        this.x_GroupInfoUserFPD7Count = mongodv.X_GroupInfoUserFPD7Count;
	        this.x_GroupInfoUserFPD7Rate = mongodv.X_GroupInfoUserFPD7Rate;
	        this.x_GroupInfoUserFPD30Count = mongodv.X_GroupInfoUserFPD30Count;
	        this.x_GroupInfoUserFPD30Rate = mongodv.X_GroupInfoUserFPD30Rate;
	        this.x_GroupInfoUserFS30Count = mongodv.X_GroupInfoUserFS30Count;
	        this.x_GroupInfoUserFS30Rate = mongodv.X_GroupInfoUserFS30Rate;
	        this.x_GroupInfoUserFST30Count = mongodv.X_GroupInfoUserFST30Count;
	        this.x_GroupInfoUserFST30Rate = mongodv.X_GroupInfoUserFST30Rate;
	        this.x_GroupInfoUserFSTQ30Count = mongodv.X_GroupInfoUserFSTQ30Count;
	        this.x_GroupInfoUserFSTQ30Rate = mongodv.X_GroupInfoUserFSTQ30Rate;
	        this.x_GroupInfoUserM3PlusRate = mongodv.X_GroupInfoUserM3PlusRate;
	        this.x_GroupInfoUserAppRecent3daysCount = mongodv.X_GroupInfoUserAppRecent3daysCount;
	        this.x_GroupInfoUserAppRecent7daysCount = mongodv.X_GroupInfoUserAppRecent7daysCount;
	        this.x_GroupInfoUserAppRecent1monthCount = mongodv.X_GroupInfoUserAppRecent1monthCount;
	        this.x_GroupInfoUserAppRecent3monthsCount = mongodv.X_GroupInfoUserAppRecent3monthsCount;
	        this.x_GroupInfoUserMainProvince = mongodv.X_GroupInfoUserMainProvince;
	        this.x_GroupInfoUserMainCity = mongodv.X_GroupInfoUserMainCity;
	        this.x_GroupInfoFausd = mongodv.X_GroupInfoFausd;
	        // v20160830 add end
		}		
	}

    public String getX_CheckUserDownPayment() {
        return x_CheckUserDownPayment;
    }

    public String getX_CheckUserProductPrice() {
        return x_CheckUserProductPrice;
    }

    public String getX_CheckPersonalInfoCompanyTypeResult() {
        return x_CheckPersonalInfoCompanyTypeResult;
    }

    public String getX_InputIdCardInfoAddress() {
        return x_InputIdCardInfoAddress;
    }

    public String getX_CheckWhetherCustomerPhotoIsTheSamePerson() {
        return x_CheckWhetherCustomerPhotoIsTheSamePerson;
    }

    public String getX_CheckWhetherDPhotoIsTheSamePerson() {
        return x_CheckWhetherDPhotoIsTheSamePerson;
    }

    public String getX_YT_Similarity() {
        return x_YT_Similarity;
    }

    public String getX_YT_SimilarityQueryDatabase() {
        return x_YT_SimilarityQueryDatabase;
    }

    public String getX_YT_SimilarityQueryIdcard() {
        return x_YT_SimilarityQueryIdcard;
    }

    public String getX_YT_SimilarityIdcardDatabase() {
        return x_YT_SimilarityIdcardDatabase;
    }

    public String getX_YT_IsPass() {
        return x_YT_IsPass;
    }

    public String getX_CheckImageComparision() {
        return x_CheckImageComparision;
    }

    public Integer getX_GroupInfoUserTotalCount() {
        return x_GroupInfoUserTotalCount;
    }

    public Integer getX_GroupInfoUserApprovedCount() {
        return x_GroupInfoUserApprovedCount;
    }

    public Integer getX_GroupInfoUserRejectedCount() {
        return x_GroupInfoUserRejectedCount;
    }

    public String getX_GroupInfoBlackListCount() {
        return x_GroupInfoBlackListCount;
    }

    public String getX_GroupInfoBlackListCountRate() {
        return x_GroupInfoBlackListCountRate;
    }

    public String getX_GroupInfoReportedRejectedCount() {
        return x_GroupInfoReportedRejectedCount;
    }

    public String getX_GroupInfoReportedRejectedCountRate() {
        return x_GroupInfoReportedRejectedCountRate;
    }

    public String getX_GroupInfoUserFPD7Count() {
        return x_GroupInfoUserFPD7Count;
    }

    public String getX_GroupInfoUserFPD7Rate() {
        return x_GroupInfoUserFPD7Rate;
    }

    public Integer getX_GroupInfoUserFPD30Count() {
        return x_GroupInfoUserFPD30Count;
    }

    public String getX_GroupInfoUserFPD30Rate() {
        return x_GroupInfoUserFPD30Rate;
    }

    public String getX_GroupInfoUserFS30Count() {
        return x_GroupInfoUserFS30Count;
    }

    public String getX_GroupInfoUserFS30Rate() {
        return x_GroupInfoUserFS30Rate;
    }

    public String getX_GroupInfoUserFST30Count() {
        return x_GroupInfoUserFST30Count;
    }

    public String getX_GroupInfoUserFST30Rate() {
        return x_GroupInfoUserFST30Rate;
    }

    public String getX_GroupInfoUserFSTQ30Count() {
        return x_GroupInfoUserFSTQ30Count;
    }

    public String getX_GroupInfoUserFSTQ30Rate() {
        return x_GroupInfoUserFSTQ30Rate;
    }

    public String getX_GroupInfoUserAppRecent3daysCount() {
        return x_GroupInfoUserAppRecent3daysCount;
    }

    public String getX_GroupInfoUserAppRecent7daysCount() {
        return x_GroupInfoUserAppRecent7daysCount;
    }

    public String getX_GroupInfoUserAppRecent1monthCount() {
        return x_GroupInfoUserAppRecent1monthCount;
    }

    public String getX_GroupInfoUserAppRecent3monthsCount() {
        return x_GroupInfoUserAppRecent3monthsCount;
    }

    public String getX_GroupInfoUserMainProvince() {
        return x_GroupInfoUserMainProvince;
    }

    public String getX_GroupInfoUserMainCity() {
        return x_GroupInfoUserMainCity;
    }
    
    public String getX_GroupInfoFausd() {
        return x_GroupInfoFausd;
    }

    public String getX_GroupInfoUserApprovedRate() {
        return x_GroupInfoUserApprovedRate;
    }

    public String getX_GroupInfoUserM3PlusRate() {
        return x_GroupInfoUserM3PlusRate;
    }
}
