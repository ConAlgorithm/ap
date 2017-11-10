//package catfish.plugins.finance;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import com.google.gson.Gson;
//
//import catfish.base.StartupConfig;
//import catfish.base.httpclient.HttpClientApi;
//import catfish.plugins.finance.object.ApplicationPosModel;
//import catfish.plugins.finance.object.ProductParamModel;
//
///**
// * Created by hary on 15/8/31.
// */
//public class RSGenerator {
//
//    private static final String ProductServiceHost =  StartupConfig.get("catfish.service.product.host");
//    private static final String ApplicationServiceHost = StartupConfig.get("catfish.service.application.host");
//
//    public enum RepaymentMode {
//        PMT, PPT;
//    };
//
//    private RepaymentMode mode;          // 还款方式         等额本息/等额本金
//    private Date firstRepaymentDate;     //
//    private double principal;            // 本金             1200
//    private int periods;                 // 分期期数          12
//    private double yearAllRate;          // 总年化利率        0.78
//    private double yearInterestRate;     // 总利息年化利率     0.51
//    private int  serviceFeePeriods;      // 服务费收取期数     3
//
//    private FeeCalculator serviceFeeCalculator;          // 服务费计算模型
//    private FeeCalculator penaltyFeeCalculator;          // 罚金计算模型
//    private FeeCalculator prepaymentFeeCalculator;       // 提前还款费计算模型
//
//    /**
//     * 目前需求中只有等额本息:  所以mode写死
//     * @param mode
//     * @param firstRepaymentDate
//     * @param principal
//     * @param periods
//     * @param yearAllRate
//     * @param yearInterestRate
//     * @param serviceFeePeriods
//     */
//    public RSGenerator(
//            Date firstRepaymentDate,
//            BigDecimal principal,
//            int periods,
//            BigDecimal yearAllRate,
//            BigDecimal yearInterestRate,
//
//            BigDecimal penaltyFee,
//
//            BigDecimal monthlyFeeRate,
//            int monthlyFeeDigit,
//            int serviceFeePeriods) {
//
//        this.mode = RepaymentMode.PMT;
//
//        this.firstRepaymentDate = firstRepaymentDate;
//        this.principal = principal.doubleValue();
//        this.periods = periods;
//        this.yearAllRate = yearAllRate.doubleValue();
//        this.yearInterestRate = yearInterestRate.doubleValue();
//
//        // 服务费期数
//        this.serviceFeePeriods = serviceFeePeriods;
//
//        // 目前服务费规则是在配置模式上是死的！！
//        String serviceFeeStr = "ratio:" + monthlyFeeRate.toString() + ":0:100000@rounddown=" + monthlyFeeDigit;
//        this.serviceFeeCalculator = new FeeCalculator(serviceFeeStr, principal.doubleValue());    // 服务费计算器
//
//        // 罚金也是死5块的
//        this.penaltyFeeCalculator = new FeeCalculator("fixed:" + penaltyFee.toString(), principal.doubleValue());    // 罚金计算器
//
//        // 提前还款费也是死的!!!
//        this.prepaymentFeeCalculator = new FeeCalculator("fixed:200",principal.doubleValue()); // 提前还款计算器
//    }
//
//    /**
//     *
//     * @param mode
//     * @param principal
//     * @param periods
//     * @param yearAllRate
//     * @param yearInterestRate
//     * @param serviceFeePeriods
//     */
//    public RSGenerator(
//            Date firstRepaymentDate,
//            RepaymentMode mode,
//            BigDecimal principal,            // 已经保证了精度？？？
//            int periods,
//            BigDecimal yearAllRate,         // 已经保证了精度？？？
//            BigDecimal yearInterestRate,    // 已经保证了精度？？？
//            int serviceFeePeriods,
//            String serviceFeeStr,
//            String penaltyFeeStr,
//            String prepaymentFeeStr
//    ) {
//        this.firstRepaymentDate = firstRepaymentDate;
//        this.mode = mode;
//        this.principal = principal.doubleValue();
//        this.periods = periods;
//        this.yearAllRate = yearAllRate.doubleValue();
//        this.yearInterestRate = yearInterestRate.doubleValue();
//        this.serviceFeePeriods = serviceFeePeriods;
//
//        this.serviceFeeCalculator = new FeeCalculator(serviceFeeStr, this.principal);
//        this.penaltyFeeCalculator = new FeeCalculator(penaltyFeeStr, this.principal);
//        this.prepaymentFeeCalculator = new FeeCalculator(prepaymentFeeStr,this.principal);
//
//        // System.out.println(this.serviceFeeCalculator);
//        // System.out.println(this.penaltyFeeCalculator);
//        // System.out.println(this.prepaymentFeeCalculator);   //
//
//    }
//
//    /**
//     * 设置calculator模板的principal
//     * @param principal
//     */
//    public void setPrincipal(double principal) {
//        this.principal = principal;
//        this.serviceFeeCalculator.setPrincipal(principal);
//        this.prepaymentFeeCalculator.setPrincipal(principal);
//        this.penaltyFeeCalculator.setPrincipal(principal);
//    }
//
//    /**
//     * 设置calculator模板的periods
//     * @param periods
//     */
//    public void setPeriods(int periods) {
//        this.periods = periods;
//    }
//
//    /**
//     *
//     * @param yearRate   : 年化利率
//     * @param periods    : 期数
//     * @return : 返回每期的还款比例
//     */
//    private double getPmtRate(double yearRate, int periods) {
//
//        double monthRate = yearRate / 12;
//        double snowBall = Math.pow(monthRate + 1, periods);
//
//        double pmt = monthRate * snowBall / (snowBall - 1);
//
//        return pmt;
//    }
//
//
//    /**
//     * 生成还款计划表
//     * @return
//     */
//    public List<RepaymentItem> generateRepaymentsOfPMT() {
//
//        // Step 0: 年利率 -> pmt利率,
//        //         年利息利率 -> pmt利率
//        //         年利息利率 -> 每期平均利息
//        // 利率不作round!!!!
//        double yearAllPmtRate = getPmtRate(yearAllRate, periods);
//        double yearInterestPmtRate = getPmtRate(yearInterestRate, periods);
//        double avgInterestRate = yearInterestRate / 12;
//
//        // Step 1: 计算每期服务费, 目前是按10元取整
//        BigDecimal monthServiceFee = new BigDecimal(serviceFeeCalculator.getFee());
//        double monthServiceFeeDouble = monthServiceFee.doubleValue();
//
//        // Step 2: 每期还款总额(本金+利息+服务费+账户管理费)
//        BigDecimal monthPay =  new BigDecimal(principal *  yearAllPmtRate).setScale(2, BigDecimal.ROUND_HALF_UP);
//        double monthPayDouble = monthPay.doubleValue();
//
//        // Step 3: 每期本金 + 利息之和
//        BigDecimal monthPrincpalAndInterest = new BigDecimal(principal * yearInterestPmtRate).setScale(2,BigDecimal.ROUND_HALF_UP);
//        double monthPrincpalAndInterestDouble = monthPrincpalAndInterest.doubleValue();
//
//        // Step 4: 计算服务费总额 = 每月服务费 * 服务费期数
//        BigDecimal serviceFeeAll =  new BigDecimal(monthServiceFeeDouble *  serviceFeePeriods).setScale(-1, BigDecimal.ROUND_DOWN);
//
//        // Step 5: 计算每期基本还款额(期本金 + 利息 + 账户管理费) = (monthPayDouble * periods - serviceFeeAll) / periods
//        double monthPayBase = (monthPayDouble * periods - serviceFeeAll.doubleValue()) / periods;
//
//        // Step 6: 每期账户管理费 = monthPayBase - monthPrincpalAndInterestDouble;
//        // 填充每期账户管理费
//        double monthAccountFeeDouble = monthPayBase - monthPrincpalAndInterestDouble;
//        BigDecimal monthAccountFee = new BigDecimal(monthAccountFeeDouble).setScale(2, BigDecimal.ROUND_HALF_UP);
//
//        // Step 4: 拆分每期利息 与 本金
//        List<RepaymentItem> repaymentItems =new ArrayList<RepaymentItem>();
//
//        double sumOfPrincpalExceptLast = 0.0;  // 计算除最后一期本金之外的所有本金之和HALF_
//        double remain = this.principal;
//        for( int i = 0; i < periods; ++i) {
//
//            RepaymentItem item = new RepaymentItem();
//            item.repaymentNumber = i + 1;
//
//            // 利息部分
//            item.interest = new BigDecimal(remain * avgInterestRate).setScale(2, BigDecimal.ROUND_HALF_UP);
//            double interestDouble = item.interest.doubleValue();
//
//            // 本金部分
//            item.principal = new BigDecimal(monthPrincpalAndInterestDouble - interestDouble).setScale(2, BigDecimal.ROUND_HALF_UP);
//            double principalDouble = item.principal.doubleValue();
//
//            // 调整利息部分
//            item.interest = new BigDecimal(interestDouble + monthAccountFee.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
//
//            // 服务费部分， 只在服务费收取期数内有
//            if ( i < serviceFeePeriods ) {
//                item.fee = monthServiceFee;   // 如50块
//            } else {
//                item.fee = BigDecimal.ZERO;
//            }
//
//            // 除最后一期的本金之和
//            if ( i < periods-1) {
//                sumOfPrincpalExceptLast += principalDouble;
//            }
//            // 最后一期本金特殊处理，以保证Sum（principal） = 总本金
//            if (i == periods -1) {
//                item.principal = new BigDecimal(principal - sumOfPrincpalExceptLast).setScale(2, BigDecimal.ROUND_HALF_UP);
//            }
//
//
//            // 剩余本金
//            remain = remain - principalDouble;
//
//            // 账户管理费为0
//            item.accountFee = BigDecimal.ZERO;
//
//            // item.dateDue; // 还款日
//            item.dateDue = RepaymentUtil.getDateDue(this.firstRepaymentDate, i+1);
//
//
//            repaymentItems.add(item);
//        }
//
//
//
//
//        return repaymentItems;
//
//    }
//
//    @Override
//    public String toString() {
//        return "IRepaymentModelImpl{" +
//                "mode=" + mode +
//                ", firstRepaymentDate=" + firstRepaymentDate +
//                ", principal=" + principal +
//                ", periods=" + periods +
//                ", yearAllRate=" + yearAllRate +
//                ", yearInterestRate=" + yearInterestRate +
//                ", serviceFeePeriods=" + serviceFeePeriods +
//                ", serviceFeeCalculator=" + serviceFeeCalculator +
//                ", penaltyFeeCalculator=" + penaltyFeeCalculator +
//                ", prepaymentFeeCalculator=" + prepaymentFeeCalculator +
//                '}';
//    }
//
//    //  依据appId获取 还款计划表生成器
//    public static RSGenerator getGenerator(String appId) {
//        // 1> 调用User组appliction信息接口, 获取产品ID, 分期数等相关产品信息
//        String appURL = ApplicationServiceHost + "/application/" + appId;
//        ApplicationPosModel appInfo = HttpClientApi.getGson(appURL, ApplicationPosModel.class);
//
//        // 2> 调用User, 依据产品ID获取产品参数
//        String ppURL = String.format("%s/product/%s?type=MobileCredit&merchantStoreId=%s",
//                ProductServiceHost, appInfo.productId, appInfo.merchantStoreId);
//        ProductParamModel productParamModel = HttpClientApi.getGson(ppURL, ProductParamModel.class);
//
//        // 还款计划表计算器
//        RSGenerator rsGenerator = new RSGenerator(
//                appInfo.firstPaybackDate,
//                appInfo.principal,
//                appInfo.repayments,    // 分期数
//                productParamModel.apr,  //  new BigDecimal(0.78),
//                productParamModel.interestRate,
//                productParamModel.penaltyFee,
//                productParamModel.monthlyFeeRate,
//                productParamModel.monthlyFeeDigit,
//                productParamModel.monthlyFeeNper
//        );
//
//        return rsGenerator;
//    }
//
//}
