package thirdparty.td;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RuleModel{

    @SerializedName("规则名称")
    private String ruleName;

    @SerializedName("命中分数")
    private int hitScore;

    @SerializedName("规则详情")
    private List<RuleDetailModel> ruleDetails;

    public int getHitScore() {
        return hitScore;
    }

    public void setHitScore(int hitScore) {
        this.hitScore = hitScore;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<RuleDetailModel> getRuleDetails() {
        return ruleDetails;
    }

    public void setRuleDetails(List<RuleDetailModel> ruleDetails) {
        this.ruleDetails = ruleDetails;
    }

}
