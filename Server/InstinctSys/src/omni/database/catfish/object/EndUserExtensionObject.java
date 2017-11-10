package omni.database.catfish.object;

import java.util.Date;

import catfish.base.ForeignKey;
import catfish.base.annotation.dao.DefaultColumnValue;
import catfish.base.annotation.dao.Id;

//因为不包含LastModifiedBy, 所以不继承自BaseObject
public class EndUserExtensionObject
{
	@Id
	public String id;

	public Integer userType;

    public Integer userAccountStatus;

    public Boolean isStudent;

    public Integer education;

    public Date personalInfoFilledOn;

    @ForeignKey("omni.database.catfish.object.MerchantStoreObject")
    public String merchantStoreId;

    public String idResultId;

    @DefaultColumnValue("GETDATE()")
	public Date dateAdded;
	
	@DefaultColumnValue("GETDATE()")
	public Date lastModified;
	
    public String idName;

    public Date realNameFilledOn;

    public String idNumber;
    
	@ForeignKey("catfish.base.business.object.MerchantUserObjects")
    public String merchantUserId;

    public Integer userCreditLevel;

    public Boolean isAvailable;
}
