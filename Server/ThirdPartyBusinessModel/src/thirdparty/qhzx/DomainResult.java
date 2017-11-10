package thirdparty.qhzx;

import java.util.Date;

import catfish.base.business.util.DateTimeUtils;

public class DomainResult {
  private String rawResult;
  private boolean hasBlacklist;
  private Date latestBuildTime;
  private boolean hasBeenExecuted;
  private boolean hasBeenBrokenPromise;
  private boolean hasBeenBroeknPromiseAndExecuted;
  private int maxOverdueGrade;
  private int maxMoneyBound;
  
  //v2.0接口返回中衍生
  //是否信贷逾期风险
  private boolean hasCreditOverdueRisk;
  //是否行政负面风险
  private boolean hasAdministrationNegativeRisk;
  //是否欺诈风险
  private boolean hasFraudRisk;
  //风险得分(10-45)
  private int riskScore;
  //是否交通严重违章
  private boolean hasSeriousTrafficViolationRisk;
  //是否手机号存在欺诈风险
  private boolean hasMobileFraudRisk;
  //是否卡号存在欺诈风险
  private boolean hasBankCardFraudRisk;
  //是否身份证号存在欺诈风险
  private boolean hasIdCardFraudRisk;
  //是否IP存在欺诈风险
  private boolean hasIPAddressFraudRisk;
  //数据状态（99-权限不足，1-已验证，2-待验证，3-异议中）
  private String dataStatus;

  public String getRawResult() {
    return rawResult;
  }

  public void setRawResult(String rawResult) {
    this.rawResult = rawResult;
  }

  public boolean isHasBlacklist() {
    return hasBlacklist;
  }

  public void setHasBlacklist(boolean hasBlacklist) {
    this.hasBlacklist = hasBlacklist;
  }

  public Date getLatestBuildTime() {
    return latestBuildTime;
  }

  public void setLatestBuildTime(Date latestBuildTime) {
    this.latestBuildTime = latestBuildTime;
  }

  public boolean isHasBeenExecuted() {
    return hasBeenExecuted;
  }

  public void setHasBeenExecuted(boolean hasBeenExecuted) {
    this.hasBeenExecuted = hasBeenExecuted;
  }

  public boolean isHasBeenBrokenPromise() {
    return hasBeenBrokenPromise;
  }

  public void setHasBeenBrokenPromise(boolean hasBeenBrokenPromise) {
    this.hasBeenBrokenPromise = hasBeenBrokenPromise;
  }

  public boolean isHasBeenBroeknPromiseAndExecuted() {
    return hasBeenBroeknPromiseAndExecuted;
  }

  public void setHasBeenBroeknPromiseAndExecuted(
      boolean hasBeenBroeknPromiseAndExecuted) {
    this.hasBeenBroeknPromiseAndExecuted = hasBeenBroeknPromiseAndExecuted;
  }

  public int getMaxOverdueGrade() {
    return maxOverdueGrade;
  }

  public void setMaxOverdueGrade(int maxOverdueGrade) {
    this.maxOverdueGrade = maxOverdueGrade;
  }

  public int getMaxMoneyBound() {
    return maxMoneyBound;
  }

  public void setMaxMoneyBound(int maxMoneyBound) {
    this.maxMoneyBound = maxMoneyBound;
  }

  
	public boolean isHasCreditOverdueRisk() {
		return hasCreditOverdueRisk;
	}
	
	public void setHasCreditOverdueRisk(boolean hasCreditOverdueRisk) {
		this.hasCreditOverdueRisk = hasCreditOverdueRisk;
	}
	
	public boolean isHasAdministrationNegativeRisk() {
		return hasAdministrationNegativeRisk;
	}
	
	public void setHasAdministrationNegativeRisk(
			boolean hasAdministrationNegativeRisk) {
		this.hasAdministrationNegativeRisk = hasAdministrationNegativeRisk;
	}
	
	public boolean isHasFraudRisk() {
		return hasFraudRisk;
	}
	
	public void setHasFraudRisk(boolean hasFraudRisk) {
		this.hasFraudRisk = hasFraudRisk;
	}
	
	public boolean isHasSeriousTrafficViolationRisk() {
		return hasSeriousTrafficViolationRisk;
	}
	
	public void setHasSeriousTrafficViolationRisk(
			boolean hasSeriousTrafficViolationRisk) {
		this.hasSeriousTrafficViolationRisk = hasSeriousTrafficViolationRisk;
	}
	
	public boolean isHasMobileFraudRisk() {
		return hasMobileFraudRisk;
	}
	
	public void setHasMobileFraudRisk(boolean hasMobileFraudRisk) {
		this.hasMobileFraudRisk = hasMobileFraudRisk;
	}
	
	public boolean isHasBankCardFraudRisk() {
		return hasBankCardFraudRisk;
	}
	
	public void setHasBankCardFraudRisk(boolean hasBankCardFraudRisk) {
		this.hasBankCardFraudRisk = hasBankCardFraudRisk;
	}
	
	public boolean isHasIdCardFraudRisk() {
		return hasIdCardFraudRisk;
	}
	
	public void setHasIdCardFraudRisk(boolean hasIdCardFraudRisk) {
		this.hasIdCardFraudRisk = hasIdCardFraudRisk;
	}
	
	public boolean isHasIPAddressFraudRisk() {
		return hasIPAddressFraudRisk;
	}
	
	public void setHasIPAddressFraudRisk(boolean hasIPAddressFraudRisk) {
		this.hasIPAddressFraudRisk = hasIPAddressFraudRisk;
	}
	
	public int getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(int riskScore) {
		this.riskScore = riskScore;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

@Override
  public String toString() {
    return  hasBlacklist + ","
        + (latestBuildTime == null ? "" : DateTimeUtils.format(latestBuildTime)) + ","
        + hasBeenExecuted + ","
        + hasBeenBrokenPromise + ","
        + hasBeenBroeknPromiseAndExecuted + ","
        + maxOverdueGrade + ","
        + maxMoneyBound;
  }
}
