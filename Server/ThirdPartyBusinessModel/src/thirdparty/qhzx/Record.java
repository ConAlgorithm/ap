package thirdparty.qhzx;

public class Record {

  private String idNo;
  private String idType;
  private String reasonCode;
  private String name;
  private String seqNo;

  private String erCode;
  private String erMsg;
  private String gradeQuery;
  private String moneyBound;
  private String dataBuildTime;

  private String money;
  private String phoneNo;
  private String currency;
  private String gradeReport;
  private String updatedDate;
  private String cardNo;        // optional
  private String qqNo;          // optional
  
  //被查询信息主体的授权代码
  //appId will be set in our case
  private String entityAuthCode;
  //信息主体授权时间
  //format should be yyyy-MM-dd HH:mm:ss
  private String entityAuthDate;
  
  //风险信息来源
  private String sourceId;
  
  //风险得分
  private String rskScore;
  
  //风险标记
  private String rskMark;
  
  //数据状态
  private String dataStatus;

  public String getIdNo() {
    return idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public String getIdType() {
    return idType;
  }

  public void setIdType(String idType) {
    this.idType = idType;
  }

  public String getReasonCode() {
    return reasonCode;
  }

  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSeqNo() {
    return seqNo;
  }

  public void setSeqNo(String seqNo) {
    this.seqNo = seqNo;
  }

  public String getErCode() {
    return erCode;
  }

  public void setErCode(String erCode) {
    this.erCode = erCode;
  }

  public String getErMsg() {
    return erMsg;
  }

  public void setErMsg(String erMsg) {
    this.erMsg = erMsg;
  }

  public String getGradeQuery() {
    return gradeQuery;
  }

  public void setGradeQuery(String gradeQuery) {
    this.gradeQuery = gradeQuery;
  }

  public String getMoneyBound() {
    return moneyBound;
  }

  public void setMoneyBound(String moneyBound) {
    this.moneyBound = moneyBound;
  }

  public String getDataBuildTime() {
    return dataBuildTime;
  }

  public void setDataBuildTime(String dataBuildTime) {
    this.dataBuildTime = dataBuildTime;
  }

  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getGradeReport() {
    return gradeReport;
  }

  public void setGradeReport(String gradeReport) {
    this.gradeReport = gradeReport;
  }

  public String getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(String updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public String getQqNo() {
    return qqNo;
  }

  public void setQqNo(String qqNo) {
    this.qqNo = qqNo;
  }

	public String getEntityAuthCode() {
		return entityAuthCode;
	}
	
	public void setEntityAuthCode(String entityAuthCode) {
		this.entityAuthCode = entityAuthCode;
	}
	
	public String getEntityAuthDate() {
		return entityAuthDate;
	}
	
	public void setEntityAuthDate(String entityAuthDate) {
		this.entityAuthDate = entityAuthDate;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getRskScore() {
		return rskScore;
	}

	public void setRskScore(String rskScore) {
		this.rskScore = rskScore;
	}

	public String getRskMark() {
		return rskMark;
	}

	public void setRskMark(String rskMark) {
		this.rskMark = rskMark;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
  
	
  
}
