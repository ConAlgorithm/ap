package catfish.bonuspoints.rules;

import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.BonusPointsRuleObject;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.queue.QueueMapMessager;

public class RuleHandler implements IRuleHandler {

  @Override
  public int getPoint(BonusPointsRuleObject rule, QueueMapMessager dataMap) {
    if (rule == null || !rule.IsInUse) {
      return 0;
    }

    if (rule.Key.equals("ApplicationSubmit") || rule.Key.equals("ApplicationComplete")) {
      String appId = dataMap.getAsString("appId");
      if (appId == null) {
        return 0;
      }
      return (int) (getPrincipal(appId) * rule.PointModifier);
    }

    return 0;
  }

  private double getPrincipal(String appId) {
    InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
    if (app == null) {
      return 0.0;
    }
    return app.Principal.doubleValue();
  }

}
