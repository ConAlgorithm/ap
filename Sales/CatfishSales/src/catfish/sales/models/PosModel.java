package catfish.sales.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class PosModel {
	
	public String id;
	
	public String code ;

    public String name ;

    public String address ;

    public String mobile ;

    public String telephone ;

    public String ipAddress ;

    public String longitude ;

    public String latitude ;

    public String legalPersonName ;

    public String legalPersonIdNumber ;

    public String description ;

    public int estimatedSalesPerDay ;

    public int estimatedPersonTraficPerDay ;

    public String qrCodeTicket ;

    public int qrCodeExpireSeconds ;

    public int qrCodeSceneId ;

    public int status ;

    public int type ;

    public int storeCategory ;

    public int commodityCategories ;

    public int commodityBrands ;

    public String commercialDistrict ;

    public String openningTime ;

    public String closingTime ;

    public String sp_OpenningTime ;

    public String sp_ClosingTime ;

    public Date cooperationApplyTime ;

    public Date cooperationStartTime ;

    public Date cooperationEndTime ;

    public int quality ;

    public int extraCode ;

    public String cNAreaId ;
    
    public String cNAreaCode;

    public String sellerId ;

    public String ownerUserId ;
    
    public String s2Id ;

    public String bDOrgId ;

    public String dOrgId ;

    public String comments ;

    public Date activateTime ;

    public double area ;

    public int businessmanNumber ;

    public int merchantsWithin500m ;

    public int peakSales ;

    public int hasSmuggledGoods ;

    public int hasSecondHandGoods ;

    public int hasRepairedGoods ;

    public Boolean hasVisualEvidence ;

    public String pOSTagRelationList ;

    public String orgId ;
    
    public  SellerModel merchantCompany;
    public  String merchantCompanyId;
    
    public String d1Name;
    public String s2Name;
    
    public List<AttachmentModel> licensePhotos ;
    public List<AttachmentModel> scenePhotos ;
    public List<AttachmentModel> sceneVideos ;
    public List<AttachmentModel> cooperationAgreementPhotos ;
    
    public int enumCommodityCategories ;
    public int enumCommodityBrands;
    
    public String licensePhotoIds;
    public String scenePhotoIds;
    public String sceneVideoIds;
    public String cooperationAgreementPhotoIds;
    public String salesDistricts;
    public int completedApplicationCount;
    public int rejectedApplicationCount;
    public BigDecimal passRate;

    public String initialBusinessDeveloperName;
    public String currentBusinessDeveloperName;
    
    public MerchantUserModel d1;
    public List<MerchantUserModel> s1List ;
    public List<POSTagModel> pOSTagList ;
    public Date startTime;
    public Date endTime;
    
	public String s1Id;
	public String s1IdName;
	public String s1Mobile;
	public String d1Id;
	public String d1IdName;
	public String d1Moblie;
	public String d1UserName;
    
}
