package jma.models;

import java.util.List;

/**
 * Created by ruilx
 * Date: 2017/4/18
 * Time: 17:33
 */
public class ShuWeiData {
	private Integer specialLevel;//特殊名单等级取值范围：1~9; 1~8：灰名单 ;9：黑名单数值越大，风险越高

	List<RiskInfo> riskInfo;//风险信息

	public Integer getSpecialLevel() {
		return specialLevel;
	}

	public void setSpecialLevel(Integer specialLevel) {
		this.specialLevel = specialLevel;
	}

	public List<RiskInfo> getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(List<RiskInfo> riskInfo) {
		this.riskInfo = riskInfo;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ShuWeiData{");
		sb.append("specialLevel='").append(specialLevel).append('\'');
		sb.append(", riskInfo=").append(riskInfo);
		sb.append('}');
		return sb.toString();
	}
}
