package catfish.bonuspoints.rules;

import java.util.*;
import java.util.stream.Collectors;

import catfish.base.CollectionUtils;
import catfish.base.business.dao.BonusPointsRuleDao;
import catfish.base.business.object.BonusPointsRuleObject;
import catfish.base.queue.QueueMessages;

public class RuleProvider implements IRuleProvider {

  private static final Map<String, List<String>> queueMessageToRuleKeysMap = CollectionUtils.mapOf(
      QueueMessages.APPLICATION_SUBMITTED, Arrays.asList("ApplicationSubmit"),
      QueueMessages.APPLICATION_COMPLETED, Arrays.asList("ApplicationComplete"));

  @Override
  public List<BonusPointsRuleObject> getRules(String message) {
    List<String> ruleKeys = queueMessageToRuleKeysMap.get(message);
    if (ruleKeys == null) {
      return null;
    }
    return getAllRules().stream()
        .filter(f -> ruleKeys.contains(f.Key))
        .collect(Collectors.toList());
  }

  private List<BonusPointsRuleObject> getAllRules() {
    return new BonusPointsRuleDao().getMultiple().stream()
        .filter(f -> f.IsInUse)
        .collect(Collectors.toList());
  }
}
