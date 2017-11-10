package catfish.plugins.finance;

import java.util.Date;
import java.util.List;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;

public class RepaymentSchedule {
///
  // 生成还款计划表 （不写入数据库）
  ///
  public static List<RepaymentItem> generateRepaymentSchedule(String appId)
  {
    InstallmentApplicationObject application = InstallmentApplicationDao.getInstallmentApplicationById(appId);
    if (application == null)
    {
      Logger.get().info("Query result is null where appId = " + appId);
      return null;
    }

    String fundTag = InstallmentApplicationDao.getFundTagByAppId(application.Id);
    return generateRepaymentSchedule(application, fundTag);
  }

  ///
  // 生成还款计划表 （不写入数据库）
  ///
  private static List<RepaymentItem> generateRepaymentSchedule(InstallmentApplicationObject application, String fundTag)
  {
    LoanInformation loanInfo = new LoanInformation();
    loanInfo.periods =application.Repayments;
    loanInfo.princpal = application.Principal.doubleValue();
    loanInfo.firstPaybackDay = application.FirstPaybackDate;
    List<RepaymentItem> repayments = null;
    if (application.InstalmentChannel == InstalmentChannel.PayDayLoanApp.getValue())// PDL特殊考虑
    {
      Date start = application.MoneyTransferredOn;
      if (start == null)
      {
        start = application.ProductSelectedTime;
      }

      // PDL了期数为放款日与首次还款日之间的天数 ？？？？？？
      loanInfo.periods = RepaymentUtil.compareDiffDay(start, application.FirstPaybackDate);
      Logger.get().info(String.format("PDL: Princpal is %s and Periods is %s", loanInfo.princpal, loanInfo.periods));

      repayments=RepaymentScheduleGenerator.generateRepaymentsOfPDL(loanInfo);
    }
//    else
//    {
//        RSGenerator rs = RSGenerator.getGenerator(application.Id);
//        repayments = rs.generateRepaymentsOfPMT();
//    }
    return repayments;
  }
//	private static String OverDueStatusString = ">>>>>>>>>>>>>>>>>>>>>>>>";
//
//	private static double rate = Double.valueOf(StartupConfig.get("catfish.finance.rate")).doubleValue();
//
//	private static String SuccessFlag = "SUCCESS";
//
//	private static final String ProductServiceHost = StartupConfig.get("catfish.service.product.host");
  
	///
	// 提供不同principal下的还款额，供App调用，用于申请时显示
	///
//	public static List<RepaymentInfo> generateAllRepaymentInfo(String merchantStoreId, String productId)
//	{
//		List<RepaymentInfo> result = new ArrayList<RepaymentInfo>();
//
//		// 获取产品参数
//		String uri = String.format("%s/product/%s?type=MobileCredit", ProductServiceHost, productId);
//		if (merchantStoreId != null) {
//		    uri = uri + "&merchantStoreId=" + merchantStoreId;
//		}
//		ProductParamModel productParamModel = HttpClientApi.getGson(uri, ProductParamModel.class);
//
//		// 得到的是Generator的模板 !!!
//		RSGenerator rs = new RSGenerator(
//				new Date(),
//				BigDecimal.ZERO,
//				1,
//				productParamModel.apr,
//				productParamModel.interestRate,
//				productParamModel.penaltyFee,
//				productParamModel.monthlyFeeRate,
//				productParamModel.monthlyFeeDigit,
//				productParamModel.monthlyFeeNper
//		);
//
//		for (int period : productParamModel.repaymentMonths) {
//			for (BigDecimal p = productParamModel.minPrincipal;
//			    p.compareTo(productParamModel.maxPrincipal) <= 0;
//			    p = p.add(new BigDecimal(productParamModel.step))) {
//
//			    // RSGenerator rs = RSGenerator.getGeneratorTemplate(productId, merchantStoreId);
//				rs.setPeriods(period);
//				rs.setPrincipal(p.doubleValue());
//
//			  List<RepaymentItem> items =  rs.generateRepaymentsOfPMT();
//			  if (items == null || items.size() == 0) {
//				Logger.get().error(String.format(
//				    "Repayment is null or empty when principal = %s periods = %s", p.toString(), period));
//				continue;
//			  }
//
//			  RepaymentInfo repaymentInfo = new RepaymentInfo();
//			  repaymentInfo.totalPeriods = period;
//			  repaymentInfo.principal = p;
//			  repaymentInfo.rate = productParamModel.apr;
//			  repaymentInfo.baseMonthPay = items.get(0).principal.add(items.get(0).interest).add(items.get(0).accountFee);
//			  repaymentInfo.fee = items.get(0).fee;
//			  repaymentInfo.feePayTimes = productParamModel.monthlyFeeNper;
//
//			  result.add(repaymentInfo);
//			}
//		}
//		return result;
//	}

	///
	// 生成还款计划表（写入数据库）
	///
//	public static boolean generateAndUpdateDB(String appId, String adminUserId){
//
//		InstallmentApplicationObject application = InstallmentApplicationDao.getInstallmentApplicationById(appId);
//		if (application == null)
//		{
//			Logger.get().info("Query result is null where appId = " + appId);
//			return false;
//		}
//		ApplicationStatus appStatus = EnumUtils.parse(ApplicationStatus.class, application.Status);
//		if (InstallmentApplicationDao.isInImmutableStatus(appStatus))
//		{
//			Logger.get().info(String.format("application status is %s, not meet the condition", appStatus.getValue()));
//			return false;
//		}
//
//		application.Status = ApplicationStatus.Completed.getValue();
//		application.LastModifiedBy = adminUserId;
//		adjustPaybackDay(application);   //modify by xp
//
//		boolean result = updateDB(application);
//
//		if (result)
//		{
//			try{
//				MessageNotificationUtil.sendMessageAsynchronously("MoneyTransferred", application.Id, application.ContextId);
//			}
//			catch (Exception e)
//			{
//				Logger.get().error("send Notification error:" + e.getMessage());
//			}
//		}
//		return result;
//	}

//	public static BigDecimal generateMonthlyPay(String appId)
//	{
//		List<RepaymentItem> repayments = generateRepaymentSchedule(appId);
//		if (repayments.size() == 0)
//		{
//			Logger.get().warn("Size of repayments is zero!");
//			return BigDecimal.ZERO;
//		}
//		BigDecimal sum = BigDecimal.ZERO;
//		for (int i = 0;i<repayments.size();i++)
//		{
//			sum.add(repayments.get(i).principal).add(repayments.get(i).interest).add(repayments.get(i).fee).add(repayments.get(i).accountFee);
//		}
//		return sum.divide(new BigDecimal(repayments.size()));
//	}

	///
	// 29、30、31号的放款的申请统一 1号还款 （该逻辑暂时注释掉，由前端保证）
	// PDL 隔日放款的特殊情况，需要更新FirstPaybackDate和MonthlyPaybackDay
	///
//	private static void adjustPaybackDay(InstallmentApplicationObject application)
//	{
//		if (application.InstalmentChannel == InstalmentChannel.PayDayLoanApp.getValue())
//		{
//			if (application.MoneyTransferredOn == null)
//			{
//				application.MoneyTransferredOn = new Date();
//				int period = RepaymentUtil.compareDiffDay(application.ProductSelectedTime, application.FirstPaybackDate);
//
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTime(application.MoneyTransferredOn);
//				calendar.add(Calendar.DAY_OF_MONTH, period);
//				application.FirstPaybackDate = calendar.getTime();
//				application.MonthlyPaybackDay = calendar.get(Calendar.DAY_OF_MONTH);
//			}
//		}
//		else
//		{
//			application.MoneyTransferredOn = new Date();
//		}

//		Date firstPaybackDay = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(firstPaybackDay);
//
//		if (calendar.get(Calendar.DAY_OF_MONTH) ==29
//				|| calendar.get(Calendar.DAY_OF_MONTH) ==30
//				|| calendar.get(Calendar.DAY_OF_MONTH) ==31)
//		{
//			Logger.get().debug(String.format("Today is %s", calendar.get(Calendar.DAY_OF_MONTH)));
//			while (calendar.get(Calendar.DAY_OF_MONTH) !=1)
//			{
//				calendar.add(Calendar.DAY_OF_MONTH, 1);
//			}
//			Logger.get().debug(String.format("First PayBack day set as %s", calendar.get(Calendar.DAY_OF_MONTH)));
//		}
//
//		calendar.add(Calendar.MONTH, 1);
//		firstPaybackDay = calendar.getTime();
//		int monthlyPaybackDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//		application.Status = ApplicationStatus.Completed.getValue();
//		application.FirstPaybackDate = firstPaybackDay;
//		application.MonthlyPaybackDay = monthlyPaybackDay;
//	}

	///
	// 还款计划表所涉及的数据库写入操作，统一放在一个事务中处理
	// InstallmentApplication
	// UserAccount
	// Transaction
	// Instalment
	///
//	@SuppressWarnings("unchecked")
//	private static boolean updateDB(InstallmentApplicationObject application)
//	{
//		String fundTag = InstallmentApplicationDao.getFundTagByAppId(application.Id);
//		Object result = null;
//
//		try{
//			result = TransactionTemplateHelper.newTransactionTemplate().execute(new TransactionCallback(){
//			@Override
//      public Object doInTransaction(TransactionStatus status)
//			{
//				List<RepaymentItem> repayments = generateRepaymentSchedule(application, fundTag);
//				if (repayments == null || repayments.size() == 0)
//				{
//					RepaymentUtil.throwExecption(String.format("[AppId:%s] repayment is null or empty!", application.Id));
//				}
//				BigDecimal baseMonthlyPay =
//						repayments.get(0).principal.add(repayments.get(0).interest).add(repayments.get(0).accountFee);
//				InstallmentApplicationDao.updateApplicationStatus(application.Id, application.Status, OverDueStatusString, baseMonthlyPay,
//						application.MoneyTransferredOn, application.FirstPaybackDate, application.MonthlyPaybackDay, application.LastModifiedBy);
//
//				//判断 UserAccount 是否存在，不存在则创建一个 值为0的记录
//				if (0==UserAccountDao.getCount(application.UserId))
//				{
//					InstallmentApplicationObject lastApplication = InstallmentApplicationDao.getLatestApplicationInfoByUserId(application.UserId);
//					UserAccountDao.insertUserAccount(application.UserId, lastApplication.Id);
//				}
//
//				//更新 transaction 表
//				TransactionDao.insertTransaction(createTransaction(application.Principal, application.Id, null, null, TransactionType.DISBURSAL.getValue()));
//
//				//插入 installment 表
//				if (InstalmentDao.getAllInstalments(application.Id).size() == 0)
//				{
//					List<InstalmentObject> instalments = createInstalments(application, repayments);
//					InstalmentDao.batchInsertInstalments(instalments);
//
//                    // 插入 FinanceCheckObjects   36条记录:   dateDue,  numInstalment, appId
//                    List<FinanceCheck> items = new ArrayList<>();
//
//					// 1 - 12 期 finance-check
//                    int i = 0;
//					Iterator<InstalmentObject> it = instalments.iterator();
//					while (it.hasNext()) {
//						InstalmentObject inst = it.next();
//						FinanceCheck fc  = new FinanceCheck();
//                        fc.id            = UUID.randomUUID().toString();
//						fc.appId         = application.Id;
//						fc.datedue       = inst.DateDue;
//						fc.numInstalment = inst.NumInstalment+i;
//						items.add(fc);
//						i++;
//					}  // now i = 12 / 9 / 1   : 目前我们有1, 9 11多种期数
//
//					// 24 - 36 期
//					while( i < 36) {
//                      FinanceCheck fc  = new FinanceCheck();
//                      fc.id            = UUID.randomUUID().toString();
//					  fc.appId         = application.Id;
//                      fc.numInstalment = i + 1;   // 13期
//					  fc.datedue = RepaymentUtil.getDateDue(application.FirstPaybackDate, Calendar.MONTH, i);  // 首次还款日 + n个月!!
//					  items.add(fc);
//					  ++i;
//					}
//
//					// 批量插入
//					batchInsertFinanceCheck(items);
//
//                    // 插入初始overdue字符串
//                    insertInitOverDue(application.Id);
//				}
//				else
//				{
//					RepaymentUtil.throwExecption(String.format("[AppId:%s] instalments is exist!", application.Id));
//				}
//				return SuccessFlag;
//			}
//		});
//		}catch (Exception e)
//		{
//			Logger.get().error(e);
//			return false;
//		}
//		return SuccessFlag.equals(result);
//	}

	///
	// 根据还款信息，生成instalment记录
	// 一期 生成三条记录（本金、利息、服务费）,目前利息+管理费并成利息
	// 拆成四项，先修改这里！
	///
//	private static List<InstalmentObject> createInstalments(InstallmentApplicationObject application, List<RepaymentItem> repayments)
//	{
//		List<InstalmentObject> instalments = new ArrayList<InstalmentObject>();
//		for (int i=0;i<repayments.size();i++)
//		{
//			instalments.add(createInstalment(application, repayments.get(i).repaymentNumber -1,
//					InstalmentPriority.PRINCIPAL.getValue(), InstalmentType.PRINCIPAL.getValue(),  repayments.get(i).principal));
//			instalments.add(createInstalment(application, repayments.get(i).repaymentNumber -1,
//					InstalmentPriority.INTERESTS.getValue(), InstalmentType.INTERESTS.getValue(),  repayments.get(i).interest.add(repayments.get(i).accountFee)));
//			instalments.add(createInstalment(application, repayments.get(i).repaymentNumber -1,
//					InstalmentPriority.FEE.getValue(), InstalmentType.FEE.getValue(),  repayments.get(i).fee));
//		}
//		return instalments;
//	}
//
//	private static TransactionObject createTransaction(BigDecimal amount, String appId, String payInId, String instalmentId, int type)
//	{
//		TransactionObject transaction = new TransactionObject();
//		transaction.Amount = amount;
//		transaction.TransactionType = type;
//		transaction.DateAdded = new Date();
//		transaction.LastModified = new Date();
//		transaction.AppId = appId;
//		transaction.PayInId = payInId;
//		transaction.InstalmentId = instalmentId;
//		return transaction;
//	}

//	private static InstalmentObject createInstalment(InstallmentApplicationObject app, int numInstalment, int prioity, int instalmentType, BigDecimal instalmentValue)
//	{
//		InstalmentObject instalment = new InstalmentObject();
//	    instalment.AppId = app.Id;
//	    instalment.DateDue = RepaymentUtil.getDateDue(app.FirstPaybackDate, Calendar.MONTH, numInstalment);
//	    instalment.NumInstalment = numInstalment + 1;
//
//	    instalment.Priority = prioity;
//	    instalment.InstalmentValue = instalmentValue;
//	    instalment.InstalmentType = instalmentType;
//
//        instalment.DateAdded = new Date();
//        instalment.LastModified = new Date();
//        instalment.ValuePay = BigDecimal.valueOf(0.0);
//        instalment.Outstanding = instalment.InstalmentValue.subtract(instalment.ValuePay);
//
//        return instalment;
//	}
//
//	// 批量插入FinanceCheck
//	private static int[] batchInsertFinanceCheck(List<FinanceCheck> objs)
//	{
//                // 一批36条记录
//		Map<String, ?>[] paramsArray = (Map<String, ?>[]) Array.newInstance(Map.class, objs.size());
//		for(int i = 0; i < objs.size(); i++)
//		{
//			FinanceCheck obj = objs.get(i);
//			Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder()
//                                        .add("Id", obj.id)
//					.add("AppId", obj.appId)
//					.add("DateDue", obj.datedue)
//					.add("NumInstalment", obj.numInstalment)
//					.add("Status", obj.status)
//					.build();
//			paramsArray[i] = params;
//		}
//
//		String insertSql =
//                    "insert into [FinanceCheckObjects]( " +
//                        "Id, AppId, DateDue, NumInstalment, Status, DateAdded, LastModified) " +
//                    "values(:Id, :AppId, :DateDue, :NumInstalment, 0, GETDATE(), GETDATE())";
//
//		return DatabaseApi.batchUpdateTable(insertSql, paramsArray);
//	}
//
//        // 插入初始的overdue字符串!!!
//	private static int insertInitOverDue(String appId)
//	{
//		Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder()
//                                        .add("Id", appId)
//					.add("OverDueStatusString", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
//					.build();
//		String insertSql = "insert into [ApplicationOverDueObjects](Id, OverDueStatusString, DateAdded, LastModified) values(:Id, :OverDueStatusString, GETDATE(), GETDATE())";
//
//		return DatabaseApi.updateTable(insertSql, params);
//	}
}

