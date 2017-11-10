package catfish.plugins.finance.handler;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.InstalmentType;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.InstalmentDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.InstalmentObject;
import catfish.base.business.object.UserAccountObject;
import catfish.plugins.finance.object.JsonDataBuilder;
import catfish.plugins.finance.object.RepaymentByDateData;
import catfish.plugins.finance.object.Status;
import catfish.plugins.finance.service.DataService;

public class RepaymentByDateHandler implements IHandler {

  private String appId;
  private Date date;

  public RepaymentByDateHandler(String appId, Date date) {
    super();
    this.appId = appId;
    this.date = date;
  }

  @Override
  public String handle() {
    InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
    if (app == null || app.MoneyTransferredOn == null) {
      return JsonDataBuilder
          .build(Status.ERROR, "App is null or MoneyTransferredOn is null.", null)
          .toString();
    }

    Calendar moneyTransferredOnDate = getDate(app.MoneyTransferredOn);
    if (date.before(moneyTransferredOnDate.getTime())) {
      return JsonDataBuilder
          .build(Status.ERROR, "query date is earlier than MoneyTransferredOn.", null)
          .toString();
    }

    RepaymentByDateData data = getData(app);
    if (data == null) {
      return JsonDataBuilder.build(Status.ERROR, "get RepaymentByDateData failed.", null).toString();
    }

    return JsonDataBuilder.build(Status.SUCCESS, "success", data).toString();
  }

  private RepaymentByDateData getData(InstallmentApplicationObject app) {
    List<InstalmentObject> instalmentList = InstalmentDao.getAllInstalments(appId);
    if (instalmentList == null || instalmentList.size() == 0) {
      return null;
    }

    UserAccountObject userAccount = DataService.getUserAccountByAppId(appId);
    BigDecimal deposit = userAccount == null ? BigDecimal.ZERO : userAccount.Amount;
    Calendar queryDate = getDate(date);

    RepaymentByDateData data = new RepaymentByDateData();
    data.overdueAmount = calculateExtraRepayment(
        deposit, calculateOverdueAmount(instalmentList, queryDate.getTime()));
    data.currentAmount = calculateExtraRepayment(
        deposit, calculateCurrentAmount(instalmentList, queryDate.getTime(), data.overdueAmount));
    data.payOffAmount = calculateExtraRepayment(
        deposit, calculatePayOffAmount(instalmentList, queryDate.getTime(), data.overdueAmount, app.InstalmentChannel));
    return data;
  }

  // 回正金额
  private BigDecimal calculateOverdueAmount(List<InstalmentObject> instalments, Date queryDate) {
    // 逾期金额，不包含罚金
    BigDecimal overdue = instalments.stream()
        .filter(f -> f.DateDue.before(queryDate) && f.InstalmentType != InstalmentType.PENALT.getValue())
        .map(m -> m.Outstanding)
        .reduce(BigDecimal.ZERO, (a,b) -> a.add(b));
    BigDecimal penalty = calculatePenalty(instalments, queryDate);
    return overdue.add(penalty);
  }

  // 当期金额
  private BigDecimal calculateCurrentAmount(
      List<InstalmentObject> instalments, Date queryDate, final BigDecimal overdue) {
    BigDecimal currentInstalment = calculateCurrentInstalmentAmount(instalments, queryDate);
    return overdue.add(currentInstalment);
  }

  // 一次性还清
  private BigDecimal calculatePayOffAmount(
      List<InstalmentObject> instalments, Date queryDate, final BigDecimal overdue, int channel) {
    BigDecimal futurePrincipal = calculateFuturePrincipal(instalments, queryDate);
    BigDecimal preRepaymentFee = new PreRepaymentFeeHandler(appId, date).getPrePaymentFee();
    BigDecimal result = overdue.add(futurePrincipal).add(preRepaymentFee);
    if (channel == InstalmentChannel.PayDayLoanApp.getValue()) {
      result = result.add(calculateFutureInterestAndFee(instalments, queryDate));
    }
    return result;
  }

  // 当前期本金+利息+手续费
  private BigDecimal calculateCurrentInstalmentAmount(List<InstalmentObject> instalments, Date queryDate) {
    int currentInstalNum = instalments.stream()
        .filter(f -> (f.DateDue.equals(queryDate) || f.DateDue.after(queryDate))
            && f.InstalmentType == InstalmentType.PRINCIPAL.getValue())
        .sorted((i1,i2) -> Integer.compare(i1.NumInstalment, i2.NumInstalment))
        .map(m -> m.NumInstalment)
        .findFirst().orElse(0);
    if (currentInstalNum <= 0) {
      return BigDecimal.ZERO;
    }
    return instalments.stream()
        .filter(f -> f.NumInstalment == currentInstalNum &&
            (f.InstalmentType == InstalmentType.PRINCIPAL.getValue()
            || f.InstalmentType == InstalmentType.INTERESTS.getValue()
            || f.InstalmentType == InstalmentType.FEE.getValue()))
        .map(m -> m.InstalmentValue)
        .reduce(BigDecimal.ZERO, (a,b) -> a.add(b));
  }

  // 未来的本金
  private BigDecimal calculateFuturePrincipal(List<InstalmentObject> instalments, Date queryDate) {
    return instalments.stream()
        .filter(f -> (f.DateDue.equals(queryDate) || f.DateDue.after(queryDate))
            && f.InstalmentType == InstalmentType.PRINCIPAL.getValue())
        .map(m -> m.InstalmentValue)
        .reduce(BigDecimal.ZERO, (a,b) -> a.add(b));
  }

  // 未来的利息+手续费
  private BigDecimal calculateFutureInterestAndFee(List<InstalmentObject> instalments, Date queryDate) {
    return instalments.stream()
        .filter(f -> (f.DateDue.equals(queryDate) || f.DateDue.after(queryDate))
            && (f.InstalmentType == InstalmentType.INTERESTS.getValue()
            || f.InstalmentType == InstalmentType.FEE.getValue()))
        .map(m -> m.InstalmentValue)
        .reduce(BigDecimal.ZERO, (a,b) -> a.add(b));
  }

  // 计算总罚金
  private BigDecimal calculatePenalty(List<InstalmentObject> instalments, Date queryDate) {
    // 获取DueDate<queryDate且Outstanding>0中，按DueDate排，最早的记录
    InstalmentObject instalment = instalments.stream()
        .filter(f -> f.DateDue.before(queryDate) && f.Outstanding.compareTo(BigDecimal.ZERO) > 0)
        .sorted((i1,i2) -> i1.DateDue.compareTo(i2.DateDue))
        .findFirst().orElse(null);
    if (instalment == null) {
      return BigDecimal.ZERO;
    }

    int dayDiff = Days.daysBetween(new DateTime(instalment.DateDue), new DateTime(queryDate)).getDays();
    BigDecimal penalty = new PenaltyPerDayHandler(appId).getPenaltyPerDay();
    return penalty.multiply(new BigDecimal(dayDiff));
  }

  private BigDecimal calculateExtraRepayment(BigDecimal deposit, BigDecimal repayment) {
    return deposit.compareTo(repayment) >= 0
        ? BigDecimal.ZERO
        : repayment.subtract(deposit);
  }

  private Calendar getDate(Date time) {
    if (time == null) {
      return null;
    }

    Calendar cal = Calendar.getInstance();
    cal.setTime(time);
    return getDate(cal);
  }

  private Calendar getDate(Calendar time) {
    if (time == null) {
      return null;
    }

    time.set(Calendar.HOUR_OF_DAY, 0);
    time.set(Calendar.MINUTE, 0);
    time.set(Calendar.SECOND, 0);
    time.set(Calendar.MILLISECOND, 0);
    return time;
  }

}
