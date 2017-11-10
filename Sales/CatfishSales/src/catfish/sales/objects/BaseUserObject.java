package catfish.sales.objects;

import java.util.Date;

import catfish.base.business.common.MerchantStatus;
import catfish.sales.enums.MerchantUserRole;

public class BaseUserObject extends BaseDataObject {
	public String IdName;

    public Date RealNameFilledOn ;

    public String IdNumber ;

    public int Role ;

    public int Status ;

    public Date DeletedOn;

    public String Comments;

}
