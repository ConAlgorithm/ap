package catfish.sales.objects;

import java.math.BigDecimal;

import catfish.sales.enums.MerchantUserRole;
import catfish.sales.enums.MerchantUserTransactionType;

public class MerchantUserTransactionObject extends BaseDataObject {

	public int MerchantUserTransactionType ;

    public BigDecimal Amount ;

    public int Role ;

    public String SellerId ;
  
    public String UserId ;
    
    public String InstalmentAppId ;
    
    public String WithdrawAppId ;
    
    public String MerchantUserId ;
}
