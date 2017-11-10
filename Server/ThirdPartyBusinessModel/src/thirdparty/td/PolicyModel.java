package thirdparty.td;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PolicyModel {

    @SerializedName("规则列表")
    private List<RuleModel> ruleList;

    private String policyName;

    public List<RuleModel> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<RuleModel> ruleList) {
        this.ruleList = ruleList;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

}
