//package catfish.plugins.finance;
//
//import java.math.BigDecimal;
//
///**
// * Created by hary on 15/8/27.
// */
//public class FeeCalculator {
//
//    public static final String FEE_MODEL_FIXED = "fixed";
//    public static final String FEE_MODEL_RATIO = "ratio";
//
//    private double principal; // 本金
//    private Double min;      // 保底
//    private Double max;      // 封顶
//    private double ratio;    // 按比例
//    private double fixed;    // 定额
//    private String mode;     // "ratio|fixed"   按比例保底封顶 | 定额
//    private String round;    // 取整方式:
//    private int    digits;   // 保留几位:hary
//
//    /**
//     * 设置本金值
//     * @param principal
//     */
//    public void setPrincipal(double principal) {
//        if ( FEE_MODEL_RATIO.equals(mode)) {
//            this.principal = principal;
//        }
//    }
//
//
//    /**
//     * 按比例保底封顶
//     * @param principal
//     * @param min
//     * @param max
//     * @param ratio
//     * @param round
//     * @param digits
//     */
//    public FeeCalculator(double principal, Double min, Double max, double ratio, String round, int digits) {
//        this.principal = principal;
//        this.min = min;
//        this.max = max;
//        this.ratio = ratio;
//        this.round = round;
//        this.digits = digits;
//
//        this.mode = "ratio";
//    }
//
//    /**
//     * 定额
//     * @param fixed
//     */
//    public FeeCalculator(double fixed) {
//        this.fixed = fixed;
//        this.mode = "fixed";
//    }
//
//    /**
//     *
//     * 依据费率字符串与本金， 生成费用计算模型
//     * @param feeStr      ratio:0.05:10:100@rounddown=-1  : 向下取整,保留-1位
//     *                    fixed:200
//     *                    fixed:5
//     *                    ratio:0.05::@=
//     * @param principal
//     * @return
//     */
//    public FeeCalculator(String feeStr, double principal) {
//        this.principal = principal;
//        String[] arr = feeStr.split("@");
//
//
//        String[] items = arr[0].split(":");
//
//        // 定额
//        if (items[0].equals(FEE_MODEL_FIXED)) {
//            this.mode = FEE_MODEL_FIXED;
//            this.fixed = Double.parseDouble(items[1]);
//        }
//
//        // 按比例封顶保底
//        else if (items[0].equals(FEE_MODEL_RATIO)) {
//
//            this.mode = FEE_MODEL_RATIO;
//            this.ratio = Double.parseDouble(items[1]);
//
//            if (items[2] != null && !items[2].equals("")) {
//                this.min = Double.parseDouble(items[2]);
//            }
//            if (items[3] != null && !items[3].equals("")) {
//
//                this.max = Double.parseDouble(items[3]);
//            }
//
//            // 按比例封顶保底, 必须要有取整规则，与保留位
//            if (arr.length != 2) {
//                throw new RuntimeException();
//            }
//            String[] roundAndDigits = arr[1].split("=");
//            this.round = roundAndDigits[0];
//            this.digits = Integer.parseInt(roundAndDigits[1]);
//
//            //  ratio:0.05:min:max
//            //  ratio:0.05::max
//            //  ratio:0.05::
//            //  或者规定必须配置
//        }
//    }
//
//    /**
//     * 计算手续费
//     * @return
//     */
//    public double getFee() {
//        if (mode.equals(FEE_MODEL_FIXED)) {
//            return fixed;
//        }
//        if (mode.equals(FEE_MODEL_RATIO)) {
//            double amt = principal * ratio;
//
//            BigDecimal result;
//            if ( this.round.equals("roundup")) {
//                result = new BigDecimal(amt).setScale(this.digits, BigDecimal.ROUND_UP);
//            }
//            else if ( this.round.equals("rounddown")) {
//                result = new BigDecimal(amt).setScale(this.digits, BigDecimal.ROUND_DOWN);
//            }
//            else if (this.round.equals("roundhalfup")) {
//                result = new BigDecimal(amt).setScale(this.digits, BigDecimal.ROUND_HALF_UP);
//
//            }
//            else {
//                throw new RuntimeException("invalid");
//            }
//
//            // 转换为double
//            amt = result.doubleValue();
//
//            // 如果保底
//            if (min != null) {
//                amt = Math.max(amt, min);
//            }
//
//            // 如果封顶
//            if (max != null) {
//                amt = Math.min(amt, max);
//            }
//            return amt;
//        }
//        return 0;
//    }
//
//    @Override
//    public String toString() {
//        return "FeeCalculator{" +
//                "principal=" + principal +
//                ", min=" + min +
//                ", max=" + max +
//                ", ratio=" + ratio +
//                ", fixed=" + fixed +
//                ", mode='" + mode + '\'' +
//                ", round='" + round + '\'' +
//                ", digits=" + digits +
//                '}';
//    }
//}
