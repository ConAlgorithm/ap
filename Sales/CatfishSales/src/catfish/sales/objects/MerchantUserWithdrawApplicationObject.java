package catfish.sales.objects;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantUserWithdrawApplicationObject extends BaseDataObject {

	public BigDecimal Amount ;

    public BigDecimal BalanceBeforeApprove ;

    public BigDecimal BalanceAfterApprove ;

    public String MerchantUserId ;

    public Date WithdrawTime ;

    public String TransferVoucherPhotoAttachmentId ;

    public Boolean IsWithdraw ;
}
