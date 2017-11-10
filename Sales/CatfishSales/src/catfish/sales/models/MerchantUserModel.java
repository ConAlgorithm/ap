package catfish.sales.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MerchantUserModel {

    public String id ;

    public String idname ;
    
    public Date realNameFilledOn;

    public int completedApplicationCount ;

    public int rejectedApplicationCount ;

    public Date startTime ;

    public Date endTime ;
    
    public Date deletedOn ;

    public BigDecimal passRate ;

    public String userId ;

    public String idNumber ;

    public String mobile ;

    public String QQNumber ;

    public String companyName ;

    public String companyAddress ;

    public String companyTel ;

    public String bankName ;

    public String bankAccount ;

    public String bankAccountName ;

    public AttachmentModel headPhotoAttachment ;

    public AttachmentModel idPhotoAttachment ;

    public AttachmentModel bankCardPhotoAttachment ;

    //当前代理的商户列表
    public List<SellerModel> currentAgentSellerList ;

    //历史代理商户列表
    public List<SellerModel> historyAgentSellerList ;

    public int role ;

    public int status ;

    public BigDecimal balance ;

    public String comments ;

    public String merchantStoreId ;

    public PosModel merchantStore ;

    public List<PosModel> historyPOSListForS2 ;

    public List<PosModel> currentPOSListForS2 ;

//    public List<POSForS1Model> HistoryPOSForS1 ;
//
//    public List<POSForS1Model> CurrentPOSForS1 ;

    public String roles ;

    public int weiXinMessageCount ;

    public String weiXinNickName ;

    public Date storeManagerStartTime ;

    public Date storeManagerEndTime ;


//    public List<SellerS3RelationModel> CurrentOwnSellerList ;
//
//    public List<SellerS3RelationModel> HistoryOwnSellerList ;

    public Date POSD1RelationStartTime ;

    public Date POSD1RelationEndTime ;

    public Date POSS1RelationStartTime ;

    public Date POSS1RelationEndTime ;

    public List<MerchantUserTransactionModel> affiliateTransactionList ;
    
    public UserModel user ;
    
    public WeiXinUserModel weiXinUser ;
}
