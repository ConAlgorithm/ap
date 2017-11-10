package catfish.notification.messagegeneration;


import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.apache.http.client.utils.URIBuilder;


import catfish.base.JsonParseUtil;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.Configuration;

public class PDLBusinessServiceApi {
	public static NotificationInfoModel getNotificationInfoModel(String userId) {
		NotificationInfoModel response = new NotificationInfoModel();
		try {
	      URIBuilder builder = new URIBuilder().setPath(Configuration.getPDLBusinessUrlPrefix()
	        + String.format("/application/bills/%s/notificationinfo", userId));

	      String literal = HttpClientApi.get(builder.toString());
	      Logger.get().info("Call " + builder.toString());

	      response = JsonParseUtil.parseToObject(literal, NotificationInfoModel.class);

	    } catch (Exception e) {
	      Logger.get().warn("Error happens: " + userId, e);
	    }

		return response;
	}
	
	
	public static class NotificationInfoModel {
		public static class BillModel {
			private Long id;
			
			private int period;
			
			private UUID accountId;
			
			private BigDecimal principal;
			
			private BigDecimal interest;
			
			private BigDecimal fee;
			
			private BigDecimal minAmount;
			
			private BigDecimal totalAmount;
			
			private Date billStartDate;
			
			private Date billDueDate;
			
			private Date billPenalityStartDate;

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public int getPeriod() {
				return period;
			}

			public void setPeriod(int period) {
				this.period = period;
			}

			public UUID getAccountId() {
				return accountId;
			}

			public void setAccountId(UUID accountId) {
				this.accountId = accountId;
			}

			public BigDecimal getPrincipal() {
				return principal;
			}

			public void setPrincipal(BigDecimal principal) {
				this.principal = principal;
			}

			public BigDecimal getInterest() {
				return interest;
			}

			public void setInterest(BigDecimal interest) {
				this.interest = interest;
			}

			public BigDecimal getFee() {
				return fee;
			}

			public void setFee(BigDecimal fee) {
				this.fee = fee;
			}

			public BigDecimal getMinAmount() {
				return minAmount;
			}

			public void setMinAmount(BigDecimal minAmount) {
				this.minAmount = minAmount;
			}

			public BigDecimal getTotalAmount() {
				return totalAmount;
			}

			public void setTotalAmount(BigDecimal totalAmount) {
				this.totalAmount = totalAmount;
			}

			public Date getBillStartDate() {
				return billStartDate;
			}

			public void setBillStartDate(Date billStartDate) {
				this.billStartDate = billStartDate;
			}

			public Date getBillDueDate() {
				return billDueDate;
			}

			public void setBillDueDate(Date billDueDate) {
				this.billDueDate = billDueDate;
			}

			public Date getBillPenalityStartDate() {
				return billPenalityStartDate;
			}

			public void setBillPenalityStartDate(Date billPenalityStartDate) {
				this.billPenalityStartDate = billPenalityStartDate;
			}
			
			
		}
		
		public static class PlanAmountRecordModel {
			private Date dateAdded;
			
			private Date lastModified;
			
			private BigDecimal principal;
			
			private BigDecimal interest;
			
			private BigDecimal fee;
			
			private BigDecimal planAmount;
			
			private BigDecimal totalAmount;
			
			private BigDecimal minAmount;
			
			private WithholdResultModel withholdResult;

			public Date getDateAdded() {
				return dateAdded;
			}

			public void setDateAdded(Date dateAdded) {
				this.dateAdded = dateAdded;
			}

			public Date getLastModified() {
				return lastModified;
			}

			public void setLastModified(Date lastModified) {
				this.lastModified = lastModified;
			}

			public BigDecimal getPrincipal() {
				return principal;
			}

			public void setPrincipal(BigDecimal principal) {
				this.principal = principal;
			}

			public BigDecimal getInterest() {
				return interest;
			}

			public void setInterest(BigDecimal interest) {
				this.interest = interest;
			}

			public BigDecimal getFee() {
				return fee;
			}

			public void setFee(BigDecimal fee) {
				this.fee = fee;
			}

			public BigDecimal getPlanAmount() {
				return planAmount;
			}

			public void setPlanAmount(BigDecimal planAmount) {
				this.planAmount = planAmount;
			}

			public BigDecimal getTotalAmount() {
				return totalAmount;
			}

			public void setTotalAmount(BigDecimal totalAmount) {
				this.totalAmount = totalAmount;
			}

			public BigDecimal getMinAmount() {
				return minAmount;
			}

			public void setMinAmount(BigDecimal minAmount) {
				this.minAmount = minAmount;
			}

			public WithholdResultModel getWithholdResult() {
				return withholdResult;
			}

			public void setWithholdResult(WithholdResultModel withholdResult) {
				this.withholdResult = withholdResult;
			}
			
			
		}
		
		public static class WithholdResultModel {
			private Date withholdDate;
			
			private BigDecimal withholdAmount;
			
			private Long planAmountRecordId;
			
			private int status;

			public Date getWithholdDate() {
				return withholdDate;
			}

			public void setWithholdDate(Date withholdDate) {
				this.withholdDate = withholdDate;
			}

			public BigDecimal getWithholdAmount() {
				return withholdAmount;
			}

			public void setWithholdAmount(BigDecimal withholdAmount) {
				this.withholdAmount = withholdAmount;
			}

			public Long getPlanAmountRecordId() {
				return planAmountRecordId;
			}

			public void setPlanAmountRecordId(Long planAmountRecordId) {
				this.planAmountRecordId = planAmountRecordId;
			}

			public int getStatus() {
				return status;
			}

			public void setStatus(int status) {
				this.status = status;
			}	
		}
		public static class BankInfo {
			private UUID id;
			
			private UUID userId;
			
			private String bankName; 
			
			private String bankCardName;
			
			private String bankCardAccount;
			
			private Date dateAdded;
			
			private Date lastModified;

			public UUID getId() {
				return id;
			}

			public void setId(UUID id) {
				this.id = id;
			}

			public UUID getUserId() {
				return userId;
			}

			public void setUserId(UUID userId) {
				this.userId = userId;
			}

			public String getBankName() {
				return bankName;
			}

			public void setBankName(String bankName) {
				this.bankName = bankName;
			}

			public String getBankCardName() {
				return bankCardName;
			}

			public void setBankCardName(String bankCardName) {
				this.bankCardName = bankCardName;
			}

			public String getBankCardAccount() {
				return bankCardAccount;
			}

			public void setBankCardAccount(String bankCardAccount) {
				this.bankCardAccount = bankCardAccount;
			}

			public Date getDateAdded() {
				return dateAdded;
			}

			public void setDateAdded(Date dateAdded) {
				this.dateAdded = dateAdded;
			}

			public Date getLastModified() {
				return lastModified;
			}

			public void setLastModified(Date lastModified) {
				this.lastModified = lastModified;
			}
		}
		
		private BillModel bill;
		
		private PlanAmountRecordModel planAmount;

		private BankInfo bankInfo;


		public BillModel getBill() {
			return bill;
		}

		public void setBill(BillModel bill) {
			this.bill = bill;
		}

		public PlanAmountRecordModel getPlanAmount() {
			return planAmount;
		}

		public void setPlanAmount(PlanAmountRecordModel planAmount) {
			this.planAmount = planAmount;
		}

		public BankInfo getBankInfo() {
			return bankInfo;
		}

		public void setBankInfo(BankInfo bankInfo) {
			this.bankInfo = bankInfo;
		}
		
		
		
	}
}