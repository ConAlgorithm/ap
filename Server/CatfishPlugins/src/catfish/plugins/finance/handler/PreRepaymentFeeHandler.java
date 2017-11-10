package catfish.plugins.finance.handler;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;

public class PreRepaymentFeeHandler {

  private String appId;
  private Date date;
  private static final BigDecimal DEFAULT_PRE_REPAYMENT_FEE = new BigDecimal(200);

  public PreRepaymentFeeHandler(String appId, Date date) {
    super();
    this.appId = appId;
    this.date = date;
  }

  public BigDecimal getPrePaymentFee() {
    if (appId == null) {
      Logger.get().error("AppId is null!");
      return null;
    }

    return calculate();
  }

  private BigDecimal calculate() {
    InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
    if (app == null) {
      Logger.get().error(String.format("App not exists. AppId:%s", appId));
      return null;
    }

    if (app.InstalmentChannel == InstalmentChannel.PayDayLoanApp.getValue()) {
      return BigDecimal.ZERO;
    }

    Date lastPaybackDate = getLastPaybackDate(app.FirstPaybackDate, app.Repayments);
    if (date.after(lastPaybackDate)) {
      return BigDecimal.ZERO;
    }

    return DEFAULT_PRE_REPAYMENT_FEE;
  }

  private Date getLastPaybackDate(Date firstPaybackDay, int instalmentNum) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(firstPaybackDay);
    cal.add(Calendar.MONTH, instalmentNum - 1);
    return cal.getTime();
  }

}
