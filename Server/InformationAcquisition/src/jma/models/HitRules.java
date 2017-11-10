package jma.models;

/**
 * Created by ruilx
 * Date: 2017/4/10
 * Time: 15:40
 */
public class HitRules {
	private String ruleName;
	private String ruleId;
	private Integer score;//规则风险系数，只有权重策略模式下有效
	private String decision;//风险名单比对规则决策结果
	private String memo;//名单匹配规则击中分类明细

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("HitRules{");
		sb.append("ruleName='").append(ruleName).append('\'');
		sb.append(", ruleId='").append(ruleId).append('\'');
		sb.append(", score='").append(score).append('\'');
		sb.append(", decision='").append(decision).append('\'');
		sb.append(", memo='").append(memo).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
