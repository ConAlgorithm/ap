package catfish.bonuspoints.rules;

import java.util.List;

import catfish.base.business.object.BonusPointsRuleObject;

public interface IRuleProvider {

  List<BonusPointsRuleObject> getRules(String message);
}
