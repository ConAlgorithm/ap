package jma.models;

import java.util.List;

/**
 * Created by ruilx
 * Date: 2017/4/10
 * Time: 15:39
 */
public class StrategySet {

	private String strategyName;//策略名称
	private String strategyId;//策略 ID
	private String strategyDecision;//策略决策结果
	private String strategyMode;//策略匹配模式
	private Integer strategyScore;//策略风险系数， 只有权重策略模式下有效
	private String riskType;//策略风险类型
	private String tips;//策略击中话术提示
	private List<HitRules> hitRules;

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	public String getStrategyDecision() {
		return strategyDecision;
	}

	public void setStrategyDecision(String strategyDecision) {
		this.strategyDecision = strategyDecision;
	}

	public String getStrategyMode() {
		return strategyMode;
	}

	public void setStrategyMode(String strategyMode) {
		this.strategyMode = strategyMode;
	}

	public Integer getStrategyScore() {
		return strategyScore;
	}

	public void setStrategyScore(Integer strategyScore) {
		this.strategyScore = strategyScore;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<HitRules> getHitRules() {
		return hitRules;
	}

	public void setHitRules(List<HitRules> hitRules) {
		this.hitRules = hitRules;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("StrategySet{");
		sb.append("strategyName='").append(strategyName).append('\'');
		sb.append(", strategyId='").append(strategyId).append('\'');
		sb.append(", strategyDecision='").append(strategyDecision).append('\'');
		sb.append(", strategyMode='").append(strategyMode).append('\'');
		sb.append(", strategyScore='").append(strategyScore).append('\'');
		sb.append(", riskType='").append(riskType).append('\'');
		sb.append(", tips='").append(tips).append('\'');
		sb.append(", hitRules=").append(hitRules);
		sb.append('}');
		return sb.toString();
	}
}
