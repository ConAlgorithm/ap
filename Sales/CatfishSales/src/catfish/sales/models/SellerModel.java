package catfish.sales.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SellerModel {

    public String Id ;

    public String Code ;

    public String Name ;

    public String AreaCode ;

    public String AreaName ;

    public int CompletedApplicationCount ;

    public int RejectedApplicationCount ;

    public BigDecimal PassRate ;

    public int Type ;

    public int Status ;

    public String Address ;

    public String CooperationAgreementPhotoIds ;

    public String LicensePhotoIds ;

    public String MerchantCompanyManagerId ;

    public MerchantUserModel MerchantCompanyManager ;

    public String AgentCompanyManagerId ;

    public String CNAreaCode ;

    public String CNAreaDescription ;

    public Date CooperationApplyTime ;

    public Date CooperationStartTime ;

    public Date CooperationEndTime ;

    public Boolean IsTemplateProduct ;

    public String CustomProductId ;

    public String TemplateProductId ;

//    public ProductModel Product ;

    public MerchantUserModel AgentCompanyManager ;

    public String Comments ;

    public List<AttachmentModel> CooperationAgreementPhotos ;

    public List<AttachmentModel> LicensePhotos ;

    public List<MerchantUserModel> MerchantCompanyManagerList ;

    public List<MerchantUserModel> CurrentS3List ;

    public List<MerchantUserModel> HistoryS3List ;

    public List<MerchantUserModel>  AgentCompanyManagerList ;

    public List<PosModel> CurrentMerchantStores ;

    public List<PosModel> HistoryMerchantStores ;

    //public List<ProductModel> ProductList ;

    public List<CommissionListModel> CommissionList ;

    public String CommissionId ;
    
    public String CommissionName ;

    public String SalesDistricts ;
}
