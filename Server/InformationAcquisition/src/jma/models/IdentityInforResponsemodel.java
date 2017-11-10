package jma.models;

import java.util.List;

public class IdentityInforResponsemodel {
	private String result;
	private String message;
	private String idCardName; // 身份证姓名
	private String idCardCode; // 身份证号码
	private String timeInterval; // 回查时间范围
	private String toTalTransNum; // 被查询次数
	private String lastTransTime; // 最后一次查询时间
	private String lastIndustry; // 最后一次查询企业所属行业
	private String lastPrdGrpName;// 最后一次查询产品类别
	private List<StatisticsList> statisticsList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public String getIdCardName() {
		return idCardName;
	}

	public void setIdCardName(String idCardName) {
		this.idCardName = idCardName;
	}

	public String getIdCardCode() {
		return idCardCode;
	}

	public void setIdCardCode(String idCardCode) {
		this.idCardCode = idCardCode;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getToTalTransNum() {
		return toTalTransNum;
	}

	public void setToTalTransNum(String toTalTransNum) {
		this.toTalTransNum = toTalTransNum;
	}

	public String getLastTransTime() {
		return lastTransTime;
	}

	public void setLastTransTime(String lastTransTime) {
		this.lastTransTime = lastTransTime;
	}

	public String getLastIndustry() {
		return lastIndustry;
	}

	public void setLastIndustry(String lastIndustry) {
		this.lastIndustry = lastIndustry;
	}

	public String getLastPrdGrpName() {
		return lastPrdGrpName;
	}

	public void setLastPrdGrpName(String lastPrdGrpName) {
		this.lastPrdGrpName = lastPrdGrpName;
	}

	public List<StatisticsList> getStatisticsList() {
		return statisticsList;
	}

	public void setStatisticsList(List<StatisticsList> statisticsList) {
		this.statisticsList = statisticsList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IdentityInforResponsemodel [result=");
		builder.append(result);
		builder.append(", message=");
		builder.append(message);
		builder.append(", idCardName=");
		builder.append(idCardName);
		builder.append(", idCardCode=");
		builder.append(idCardCode);
		builder.append(", timeInterval=");
		builder.append(timeInterval);
		builder.append(", toTalTransNum=");
		builder.append(toTalTransNum);
		builder.append(", lastTransTime=");
		builder.append(lastTransTime);
		builder.append(", lastIndustry=");
		builder.append(lastIndustry);
		builder.append(", lastPrdGrpName=");
		builder.append(lastPrdGrpName);
		builder.append(", statisticsList=");
		builder.append(statisticsList);
		builder.append("]");
		return builder.toString();
	}

}
