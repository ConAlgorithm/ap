package omni.database.catfish.object;

import java.util.Date;

import catfish.base.ForeignKey;
import catfish.base.business.object.BaseObject;

public class MerchantStoreObject extends BaseObject
{

	public String code;

    public String name;

    public String address;

    public String mobile;

    public String telephone;

    public String ipAddress;

    public String longitude;

    public String latitude;

    public String legalPersonName;

    public String legalPersonIdNumber;

    public String description;

    public Integer estimatedSalesPerDay;

    public Integer estimatedPersonTraficPerDay;

    public String qrCodeTicket;

    public Integer qrCodeExpireSeconds;

    public Integer qrCodeSceneId;

    public Integer status;

    public Integer type;

    public Integer storeCategory;

    public Integer commodityCategories;

    public Integer commodityBrands;

    public String commercialDistrict;

    public String openningTime;

    public String closingTime;

    public Date cooperationApplyTime;

    public Date cooperationStartTime;

    public Date cooperationEndTime;

    public Integer quality;

    public Integer extraCode;

    public String cNAreaId;
    
    @ForeignKey("catfish.base.business.object.MerchantCompanyObject")
    public String merchantCompanyId;
    
    //@ForeignKey("catfish.base.business.object.MerchantUserObject")
    public String ownerUserId;

}
