package catfish.bonuspoints.rules;

import catfish.base.business.object.BonusPointsRuleObject;
import catfish.base.queue.QueueMapMessager;

public interface IRuleHandler {

  int getPoint(BonusPointsRuleObject rule, QueueMapMessager dataMap);
}
