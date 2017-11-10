package catfish.fundcontroller.jimubox;

import java.math.BigDecimal;
import java.util.Date;

public class ApplicationModel {
	//基本信息
	public String IdentityNumber ;
	public String ChineseName;
	public String Sex;
	public String Phone;
	public String MaritalStatus;
	//教育/工作信息
	public String CompanyName;
	public String CompanyNature;
	public String CompanyTel;
	//关系人
	public String RelaName;
	public String RelaPhone;
	public String Relationship;
	//诉讼记录
	public String HasBadRecord;
	//融资产品信息
	public String ProductType;
	public int Batch;
	public String UserName;
	public BigDecimal FinancingAmount;
	public BigDecimal LenderAmount;
	public BigDecimal HJXD_RepaymentByMonth;
	public int RepaymentDay;
	public String RepaymentStartDate;
	public String RepaymentEndDate;
	public BigDecimal HJXD_LastMonthAmount;
	public String ApplyCity;
	public String ProductName;
	public BigDecimal SalePrice;
	public BigDecimal DownPaymentAmount;
	//代收款人
	public String AltName;
	public String AltID;
	public String AltRelation;
	//开户银行账号
	public String BankName;
	public String AccountNo;
	//商户信息
	public String MerchantName;
	public String MerchantProvince;
	public String MerchantCity;
	public String ProjectNo;
	//public String ProjectID;
	//public String Status;
	//public String Message;
	
	
}
