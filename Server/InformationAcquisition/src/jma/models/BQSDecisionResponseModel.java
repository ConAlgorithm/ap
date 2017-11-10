package jma.models;

import java.util.List;

/**
 * 白骑士model
 * @author yeyb
 * @date 2017年7月26日
 */
public class BQSDecisionResponseModel{

	private String resultCode;
	private String resultDesc;
	private String finalDecision;
	private String finalScore;
	private List<StrategySet> strategySet;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}

	public String getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	public List<StrategySet> getStrategySet() {
		return strategySet;
	}

	public void setStrategySet(List<StrategySet> strategySet) {
		this.strategySet = strategySet;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("BQSDecisionResponseModel{");
		sb.append("resultCode='").append(resultCode).append('\'');
		sb.append(", resultDesc='").append(resultDesc).append('\'');
		sb.append(", finalDecision='").append(finalDecision).append('\'');
		sb.append(", finalScore='").append(finalScore).append('\'');
		sb.append(", strategySet=").append(strategySet);
		sb.append('}');
		return sb.toString();
	}
}
