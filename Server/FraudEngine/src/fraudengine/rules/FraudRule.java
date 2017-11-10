package fraudengine.rules;

import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;

public abstract class FraudRule {

  private String id;
  private String name;
  private int    score;
  private String description;

  public FraudRule(String id, String name, int score, String description) {
    this.setId(id);
    this.setDescription(description);
    this.setName(name);
    this.setScore(score);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public abstract CatfishEnum<Integer> run(String appID);
  
  protected FraudRuleResultStatus parse(boolean result)
  {
	  return result ? FraudRuleResultStatus.Fail : FraudRuleResultStatus.Pass;
  }
}
