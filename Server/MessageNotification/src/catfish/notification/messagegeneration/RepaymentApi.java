package catfish.notification.messagegeneration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.URIBuilder;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RepaymentApi {

  public static class RepaymentResponse {
    private int status;
    private String msg;
    private Data data;

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }

    public Data getData() {
      return data;
    }

    public void setData(Data data) {
      this.data = data;
    }
  }

  public static class Data {
    private BigDecimal currentAmount;
    private BigDecimal penaltyPerDay;
    private BigDecimal principalTotal;
    private BigDecimal interestTotal;
    private BigDecimal penaltyTotal;
    private BigDecimal serviceFeeTotal;
    private Date datedue; // 本期还款日期

    public Date getDatedue() {
		return datedue;
	}

	public void setDatedue(Date datedue) {
		this.datedue = datedue;
	}

	public BigDecimal getPrincipalTotal() {
		return principalTotal;
	}

	public void setPrincipalTotal(BigDecimal principalTotal) {
		this.principalTotal = principalTotal;
	}

	public BigDecimal getInterestTotal() {
		return interestTotal;
	}

	public void setInterestTotal(BigDecimal interestTotal) {
		this.interestTotal = interestTotal;
	}

	public BigDecimal getPenaltyTotal() {
		return penaltyTotal;
	}

	public void setPenaltyTotal(BigDecimal penaltyTotal) {
		this.penaltyTotal = penaltyTotal;
	}

	public BigDecimal getServiceFeeTotal() {
		return serviceFeeTotal;
	}

	public void setServiceFeeTotal(BigDecimal serviceFeeTotal) {
		this.serviceFeeTotal = serviceFeeTotal;
	}

	public BigDecimal getCurrentAmount() {
      return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
      this.currentAmount = currentAmount;
    }

    public BigDecimal getPenaltyPerDay() {
      return penaltyPerDay;
    }

    public void setPenaltyPerDay(BigDecimal penaltyPerDay) {
      this.penaltyPerDay = penaltyPerDay;
    }
    
    public BigDecimal getTotal(){
    	BigDecimal principalTotalTemp = this.principalTotal == null?new BigDecimal(0):this.principalTotal;
    	BigDecimal interestTotalTemp = this.interestTotal == null?new BigDecimal(0):this.interestTotal;
    	BigDecimal penaltyTotalTemp = this.penaltyTotal == null?new BigDecimal(0):this.penaltyTotal;
    	BigDecimal serviceFeeTotalTemp = this.serviceFeeTotal == null?new BigDecimal(0):this.serviceFeeTotal;
    	return principalTotalTemp.add(interestTotalTemp).add(penaltyTotalTemp).add(serviceFeeTotalTemp);
    }
  }

  public static Data invoke(String relativePath, Map<String, String> params) {
    URIBuilder builder = new URIBuilder()
        .setPath(Configuration.getRepaymentUrlPrefix() + relativePath);
    for (Entry<String, String> param : params.entrySet()) {
      builder.setParameter(param.getKey(), param.getValue());
    }

    String literal = HttpClientApi.get(builder.toString());
    Logger.get().info("Got repayment response: " + literal);

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    RepaymentResponse response = gson.fromJson(literal, RepaymentResponse.class);
    if (response.status != 0) {
      throw new RuntimeException(
          String.format("RepaymentApi error: %d - %s", response.status, response.msg));
    }

    return response.getData();
  }
  
  public static Data invoke(String relativePath, String params) {
	    URIBuilder builder = new URIBuilder()
	        .setPath(Configuration.getRepaymentUrlPrefix() + relativePath + "/" + params);
	    String literal = HttpClientApi.get(builder.toString());
	    Logger.get().info("Got repayment response: " + literal);

	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	    Data data = gson.fromJson(literal, Data.class);

	    return data;
	  }
}
