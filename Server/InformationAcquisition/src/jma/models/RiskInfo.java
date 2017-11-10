package jma.models;

/**
 * Created by ruilx
 * Date: 2017/4/18
 * Time: 17:34
 */
public class RiskInfo {
	private Integer type;//风险类型
	private Integer level;
	private String happenTime;//风险发生时间格式：YYYY-MM-DD
	private String riskNo;//风险项编号
	private String riskDesc;//风险项描述
	private Integer hitType;//命中类型

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}

	public String getRiskNo() {
		return riskNo;
	}

	public void setRiskNo(String riskNo) {
		this.riskNo = riskNo;
	}

	public String getRiskDesc() {
		return riskDesc;
	}

	public void setRiskDesc(String riskDesc) {
		this.riskDesc = riskDesc;
	}

	public Integer getHitType() {
		return hitType;
	}

	public void setHitType(Integer hitType) {
		this.hitType = hitType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RiskInfo{");
		sb.append("type='").append(type).append('\'');
		sb.append(", level='").append(level).append('\'');
		sb.append(", happenTime='").append(happenTime).append('\'');
		sb.append(", riskNo='").append(riskNo).append('\'');
		sb.append(", riskDesc='").append(riskDesc).append('\'');
		sb.append(", hitType='").append(hitType).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
