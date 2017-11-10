package jma.handlers;

import static jma.DatabaseEnumValues.ContactRelationShip.HUSBAND;
import static jma.DatabaseEnumValues.ContactRelationShip.SPOUSE;
import static jma.DatabaseEnumValues.ContactRelationShip.WIFE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.handlers.ContactRelationshipUtils.ContactStructure;
import jma.handlers.ContactRelationshipUtils.OtherUserStructure;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.dao.FraudRuleResultDao;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.CommonUtil;
import catfish.base.database.DatabaseApi;

public class CheckSpouseRelationshipHandler extends JobHandler {

  static class SpouseStructure {
    int relationship;
    String phone;
    String name;

    public SpouseStructure(int relationship, String phone, String name) {
      this.relationship = relationship;
      this.phone = phone;
      this.name = name;
    }
  }

  @Override
  public void execute(String appId) throws RetryRequiredException {
    Boolean variableValue = !isConsistent(appId); 
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
        appId,
        AppDerivativeVariableNames.IS_SPOUSE_RELATIONSHIP_INCONSISTENT,
        variableValue));
    
    //int score = variableValue ? 100 : 0;
    FraudRuleResultDao.saveFraudDetailResult(appId, "A008", "配偶关系校验", variableValue ? FraudRuleResultStatus.Fail.getValue() : FraudRuleResultStatus.Pass.getValue());
  }

  private static boolean isConsistent(String appId) {
    SpouseStructure forwardSpouse = getSpouseByAppId(appId);
    if (forwardSpouse == null || forwardSpouse.phone.startsWith("0")) {
      return true;
    }

    OtherUserStructure otherUser =
        getOtherUser(DatabaseUtils.getUserIdBy(appId), forwardSpouse.phone);
    if (otherUser == null) {
      return true;
    }
    if (!CommonUtil.isSimilarUserName(forwardSpouse.name,otherUser.name)) {
      return false;
    }

    SpouseStructure inverseSpouse = getSpouseByUserId(otherUser.id);
    if (inverseSpouse == null) {
      return getContactCount(otherUser.id) == 0;
    } else {
      return isRelationshipConsistent(forwardSpouse.relationship, inverseSpouse.relationship)
          && PhoneUtils.getUserMobile(appId).equals(inverseSpouse.phone)
          && DatabaseUtils.getIdCardNameAndNumber(appId).get(0).equals(inverseSpouse.name);
    }
  }

  private static boolean isRelationshipConsistent(int forward, int inverse) {
    return forward == SPOUSE || forward != inverse;
  }

  private static SpouseStructure getSpouseByAppId(String appId) {
    for (ContactStructure contact : ContactRelationshipUtils.getMobileContacts(appId).values()) {
      if (contact.relation == SPOUSE || contact.relation == HUSBAND || contact.relation == WIFE) {
        return new SpouseStructure(contact.relation, contact.phone, contact.name);
      }
    }
    return null;
  }

  private static OtherUserStructure getOtherUser(String thisUserId, String spouseMobile) {
    Map<String, OtherUserStructure> otherUsers =
        ContactRelationshipUtils.getOtherUsers(thisUserId, spouseMobile);
    return otherUsers.isEmpty() ? null : otherUsers.values().iterator().next();
  }

  private static SpouseStructure getSpouseByUserId(String userId) {
    String sql =
        "SELECT TOP 1 CP.Relationship, C.Content, CP.Name " +
        "FROM InstallmentApplicationObjects AS I, ContactPersonObjects AS CP, " +
        "    ContactObjects AS C " +
        "WHERE I.Id = CP.AppId " +
        "    AND CP.MobileContactId = C.Id " +
        "    AND I.UserId = :UserId " +
        "    AND CP.Relationship IN (:SpouseTypes) " +
        "ORDER BY CP.LastModified DESC";
    Map<String, ?> params = CollectionUtils.mapOf(
        "UserId", userId,
        "SpouseTypes", Arrays.asList(SPOUSE, HUSBAND, WIFE));
    RowMapper<SpouseStructure> mapper = new RowMapper<SpouseStructure>() {
      @Override
      public SpouseStructure mapRow(ResultSet row, int index) throws SQLException {
        return new SpouseStructure(row.getInt(1), row.getString(2), row.getString(3));
      }
    };
    return DatabaseApi.querySingleResultOrDefault(sql, params, mapper, null);
  }

  private static int getContactCount(String userId) {
    String sql =
        "SELECT COUNT(CP.Id) " +
        "FROM InstallmentApplicationObjects AS I, ContactPersonObjects AS CP " +
        "WHERE I.Id = CP.AppId " +
        "    AND I.UserId = :UserId";
    return DatabaseApi.querySingleInteger(sql, CollectionUtils.mapOf("UserId", userId));
  }
}
