package catfish.plugins.finance.object;

import java.util.Date;
import java.math.BigDecimal;

public class ApplicationPosModel extends ApplicationBaseModel {

  public String iouIdentifier;

  public String instalmentPurposeId;

  public String productId;

  public int repayments;

  public String productName;

  public String merchantStoreId;

  public String merchantUserId;

  public Date firstPaybackDate;

  public BigDecimal itemPrice;

}
