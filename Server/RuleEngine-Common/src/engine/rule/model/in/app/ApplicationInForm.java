package engine.rule.model.in.app;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.BuyPurposeType;
import catfish.base.business.util.AppDerivativeVariableNames;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.DerivativeVariableNames;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "业务申请材料")
public class ApplicationInForm extends BaseForm {

	@ModelField(name = "教育程度", bindDomain = "engine.rule.domain.EducationalBackground")
	private Integer education = -99;

	// finished
	// 在creator中手动填写
	@ModelField(name = "婚姻状况", bindDomain = "engine.rule.domain.MarriageStatus")
	private Integer marriage = -99;

	// finished
	// 在creator中手动填写
	@ModelField(name = "居住性质", bindDomain = "engine.rule.domain.LivingCondition")
	private Integer livingCondition = -99;

	// finished
	// 在creator中手动填写
	@ModelField(name = "第几份工作", bindDomain = "engine.rule.domain.NthJob")
	private int nthJob = -99;

	@ModelField(name = "贷款额")
	private BigDecimal principal = new BigDecimal(-99);

	/************ for Precheck **********************/
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.IdCardNumberRepayingCount)
	@ModelField(name = "客户当前所有申请中处于‘还款中’状态的数目")
	private Integer repayingCount = 0;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.IdCardNumberDelayedCount)
	@ModelField(name = "客户当前所有申请中处于‘已逾期’状态的数目")
	private Integer delayedCount = 0;

	@DBField(fieldName = DerivativeVariableApi.DateTimeValue, variableType = AppDerivativeVariableConsts.IdCardNumberLastRejectedDate)
	@ModelField(name = "身份证号最近被拒绝时间")
	private String idCardNumberLastRejectedDate = "";

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.TransactionMonitorJobCount)
	@ModelField(name = "交易监控在线数量(默认值-1)")
	private int transactionMonitorJobCount = -1;
	
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.ApplicationMaxCredit)
    @ModelField(name = "最大借款金额(默认值-1.0)")
    private Double applicationMaxCredit = -1.0;
    
    @ModelField(name = "金融产品")
    private String productName =""; 
    
    @ModelField(name = "商品名称")
    private String product ="";
    
    @ModelField(name = "首付金额")
    private Double downPayment = 0.00;
    
    @ModelField(name="金融产品利率(默认值-1)")
    private BigDecimal apr=new BigDecimal(-1);
    
    @ModelField(name="购买产品类别")
    private String producttype= "" ;
    
    @ModelField(name="第一期还款额(默认值-1)")
    private BigDecimal Firstpay=new BigDecimal(-1);
    
    @ModelField(name="购机目的", bindDomain = "engine.rule.domain.BuyPurposeType")
    private Integer purpose = BuyPurposeType.AoJiao.getValue(); 
  
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.REPAYMENTS)
    @ModelField(name = "还款期数")
    private Integer repayments = -1;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLY_NUMBER)
    @ModelField(name = "申请次数")
    private Integer applyTimes = -1;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = DerivativeVariableNames.INITPROD_RATE)
    @ModelField(name = "名义利率")
    private Double initprodRate = 0.0;
    
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.PRODUCT_CATEGORY)
    @ModelField(name = "商品类型标识")
    private String productCategory = "";
    
	public Double getInitprodRate() {
		return initprodRate;
	}

	public void setInitprodRate(Double initprodRate) {
		this.initprodRate = initprodRate;
	}

	public Integer getRepayments() {
		return repayments;
	}

	public void setRepayments(Integer repayments) {
		this.repayments = repayments;
	}

	public Integer getApplyTimes() {
		return applyTimes;
	}

	public void setApplyTimes(Integer applyTimes) {
		this.applyTimes = applyTimes;
	}	

	public String getProductName() {  
		return productName;
	}

	public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public BigDecimal getFirstpay() {
        return Firstpay;
    }

    public void setFirstpay(BigDecimal firstpay) {
        Firstpay = firstpay;
    }   

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getApplicationMaxCredit() {
        return applicationMaxCredit;
    }

    public void setApplicationMaxCredit(Double applicationMaxCredit) {
        this.applicationMaxCredit = applicationMaxCredit;
    }

	public String getIdCardNumberLastRejectedDate() {
		return idCardNumberLastRejectedDate;
	}

	public void setIdCardNumberLastRejectedDate(String idCardNumberLastRejectedDate) {
		this.idCardNumberLastRejectedDate = idCardNumberLastRejectedDate;
	}

	@ModelMethod(name = "(this)的客户在(#1,<天数>)内被拒绝过")
	public boolean isIdCardNumberBeenRejectedInSixMonth(int dayCount) {
		if (idCardNumberLastRejectedDate == null || "".equals(idCardNumberLastRejectedDate) )
			return false;
		DateTime now = DateTime.now();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date rejectedDate = format.parse(idCardNumberLastRejectedDate);
			DateTime rejectedDateTime = new DateTime(rejectedDate);
			long duration = new Duration(rejectedDateTime, now).getStandardDays();
			return (duration < dayCount);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*********************************************/

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	public Integer getLivingCondition() {
		return livingCondition;
	}

	public void setLivingCondition(Integer livingCondition) {
		this.livingCondition = livingCondition;
	}

	public int getNthJob() {
		return nthJob;
	}

	public void setNthJob(int nthJob) {
		this.nthJob = nthJob;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public Integer getRepayingCount() {
		return repayingCount;
	}

	public void setRepayingCount(Integer repayingCount) {
		this.repayingCount = repayingCount;
	}

	public Integer getDelayedCount() {
		return delayedCount;
	}

	public void setDelayedCount(Integer delayedCount) {
		this.delayedCount = delayedCount;
	}

	public int getTransactionMonitorJobCount() {
		return transactionMonitorJobCount;
	}

	public void setTransactionMonitorJobCount(int transactionMonitorJobCount) {
		this.transactionMonitorJobCount = transactionMonitorJobCount;
	}

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
}
