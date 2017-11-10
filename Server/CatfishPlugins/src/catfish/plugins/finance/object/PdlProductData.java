package catfish.plugins.finance.object;

import java.math.BigDecimal;
import java.util.List;

public class PdlProductData {

  public BigDecimal minPrincipal;

  public BigDecimal maxPrincipal;

  public BigDecimal step;

  public int minLoanDays;

  public int maxLoanDays;

  public List<BigDecimal> monthlyPay;

}
