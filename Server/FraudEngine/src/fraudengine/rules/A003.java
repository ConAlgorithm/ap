package fraudengine.rules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jma.dataservice.PhoneUtils;
import catfish.base.CollectionUtils;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class A003 extends FraudRule {

  public A003(String id, String name, int score, String description) {
    super(id, name, score, description);
  }
  
  @Override
  public CatfishEnum<Integer> run(String appID) {
    
    List<String> contactMobs = getContactMobils(appID);
    Set<String> mobSet = new HashSet<>();
    mobSet.add(PhoneUtils.getUserMobile(appID).substring(0, 7));
    mobSet.add(PhoneUtils.getCompanyTel(appID).substring(0, 7));
    for (String mob : contactMobs) {
      mobSet.add(mob.substring(0, 7));
    }
    
    return parse(mobSet.size() == 1);
  }

  private List<String> getContactMobils(String appId) {
    String sql =
        "SELECT co.Content " +
        "FROM ContactPersonObjects AS cp, ContactObjects AS co " +
        "WHERE cp.AppId = :AppId " +
        "    AND cp.MobileContactId = co.Id";

    return DatabaseApi.queryMultipleResults(
        sql, CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.STRING_EXTRACTOR);
  }
}
