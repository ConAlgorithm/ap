package jma.handlers;

import static jma.DatabaseEnumValues.ContactRelationShip.BROTHERS_AND_SISTERS;
import static jma.DatabaseEnumValues.ContactRelationShip.COLLEAGUE;
import static jma.DatabaseEnumValues.ContactRelationShip.FRIEND;
import static jma.DatabaseEnumValues.ContactRelationShip.HUSBAND;
import static jma.DatabaseEnumValues.ContactRelationShip.OTHER;
import static jma.DatabaseEnumValues.ContactRelationShip.PARENT;
import static jma.DatabaseEnumValues.ContactRelationShip.RELATIVE;
import static jma.DatabaseEnumValues.ContactRelationShip.SPOUSE;
import static jma.DatabaseEnumValues.ContactRelationShip.WIFE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.handlers.ContactRelationshipUtils.ContactStructure;
import jma.handlers.ContactRelationshipUtils.OtherUserStructure;

import org.springframework.jdbc.core.RowCallbackHandler;

import catfish.base.CollectionUtils;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.dao.FraudRuleResultDao;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.CommonUtil;
import catfish.base.database.DatabaseApi;

public class CheckContactRelationshipHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String thisUserId = DatabaseUtils.getUserIdBy(appId);
    String thisUserName = DatabaseUtils.getIdCardNameAndNumber(appId).get(0);
    String thisUserMobile = PhoneUtils.getUserMobile(appId);

    Map<String, ContactStructure> thisContacts = ContactRelationshipUtils.getMobileContacts(appId);
    Map<String, OtherUserStructure> otherUsers =
        ContactRelationshipUtils.getOtherUsers(thisUserId, thisContacts.keySet());
    Map<String, ContactStructure> inverseContacts = otherUsers.size() > 0
        ? getInverseContacts(thisUserMobile, otherUsers.keySet())
        : new HashMap<String, ContactStructure>();

    Boolean variableValue = !isConsistent(thisUserName, thisContacts, otherUsers, inverseContacts);
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
        appId,
        AppDerivativeVariableNames.IS_CONTACT_RELATIONSHIP_INCONSISTENT,
        variableValue));
    
    //int score = variableValue ? 100 : 0;
    FraudRuleResultDao.saveFraudDetailResult(appId, "A009", "联系人关系校验", variableValue ? FraudRuleResultStatus.Fail.getValue() : FraudRuleResultStatus.Pass.getValue());
  }

  private static boolean isConsistent(
      String thisUserName,
      Map<String, ContactStructure> thisContacts,
      Map<String, OtherUserStructure> otherUsers,
      Map<String, ContactStructure> inverseContacts) {
    for (OtherUserStructure otherUser : otherUsers.values()) {
      if (!CommonUtil.isSimilarUserName(thisContacts.get(otherUser.mobile).name, otherUser.name)) {
        return false;
      }
    }
    for (Entry<String, ContactStructure> inverseContact : inverseContacts.entrySet()) {
      if (!CommonUtil.isSimilarUserName(thisUserName, inverseContact.getValue().name)) {
        return false;
      }
      int thisRelation = thisContacts.get(otherUsers.get(inverseContact.getKey()).mobile).relation;
      int inverseRelation = inverseContact.getValue().relation;
      if (INVALID_RELATIONSHIP.get(thisRelation).contains(inverseRelation)) {
        return false;
      }
    }
    return true;
  }

  private static Map<String, ContactStructure> getInverseContacts(
      final String thisUserMobile, Set<String> otherUserIds) {
    String sql =
        "SELECT I.UserId, CP.Name, CP.Relationship " +
        "FROM InstallmentApplicationObjects AS I, ContactPersonObjects AS CP, " +
        "    ContactObjects AS C " +
        "WHERE CP.AppId = I.Id " +
        "    AND CP.MobileContactId = C.Id " +
        "    AND I.UserId IN (:OtherUserIds) " +
        "    AND C.Content = :ThisUserMobile " +
        "    AND NOT EXISTS ( " +
        "        SELECT CP2.Id " +
        "        FROM InstallmentApplicationObjects AS I2, ContactPersonObjects AS CP2, " +
        "            ContactObjects AS C2 " +
        "        WHERE CP2.AppId = I2.Id " +
        "            AND CP2.MobileContactId = C2.Id " +
        "            AND I2.UserId = I.UserId " +
        "            AND C2.Content = C.Content " +
        "            AND C2.LastModified > C.LastModified )";
    Map<String, ?> params = CollectionUtils.mapOf(
        "OtherUserIds", otherUserIds,
        "ThisUserMobile", thisUserMobile);

    final Map<String, ContactStructure> contacts = new HashMap<>();
    RowCallbackHandler rowProcessor = new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet row) throws SQLException {
        contacts.put(
            row.getString("UserId"),
            new ContactStructure(
                ContactRelationshipUtils.aggregateRelation(row.getInt("Relationship")),
                thisUserMobile,
                row.getString("Name")));
      }
    };

    DatabaseApi.query(sql, params, rowProcessor);
    return contacts;
  }

  private static final Map<Integer, Set<Integer>> INVALID_RELATIONSHIP =
      CollectionUtils.<Integer, Set<Integer>>newMapBuilder()
          .add(PARENT,
              CollectionUtils.<Integer>newSetBuilder()
                  .add(PARENT)
                  .add(HUSBAND)
                  .add(WIFE)
                  .add(SPOUSE)
                  .add(FRIEND)
                  .add(BROTHERS_AND_SISTERS)
                  .add(COLLEAGUE)
                  .build())
          .add(HUSBAND,
              CollectionUtils.<Integer>newSetBuilder()
                  .add(PARENT)
                  .add(HUSBAND)
                  .add(FRIEND)
                  .add(BROTHERS_AND_SISTERS)
                  .add(COLLEAGUE)
                  .build())
          .add(WIFE,
              CollectionUtils.<Integer>newSetBuilder()
                  .add(PARENT)
                  .add(WIFE)
                  .add(FRIEND)
                  .add(BROTHERS_AND_SISTERS)
                  .add(COLLEAGUE)
                  .build())
          .add(SPOUSE,
              CollectionUtils.<Integer>newSetBuilder()
                  .add(PARENT)
                  .add(FRIEND)
                  .add(BROTHERS_AND_SISTERS)
                  .add(COLLEAGUE)
                  .build())
          .add(FRIEND,
              CollectionUtils.<Integer>newSetBuilder()
                  .add(PARENT)
                  .add(HUSBAND)
                  .add(WIFE)
                  .add(SPOUSE)
                  .add(RELATIVE)
                  .add(BROTHERS_AND_SISTERS)
                  .build())
          .add(RELATIVE,
              CollectionUtils.<Integer>newSetBuilder()
                  .add(FRIEND)
                  .build())
          .add(BROTHERS_AND_SISTERS,
              CollectionUtils.<Integer>newSetBuilder()
                  .add(PARENT)
                  .add(HUSBAND)
                  .add(WIFE)
                  .add(SPOUSE)
                  .add(FRIEND)
                  .add(COLLEAGUE)
                  .build())
          .add(COLLEAGUE,
              CollectionUtils.<Integer>newSetBuilder()
                  .add(SPOUSE)
                  .add(BROTHERS_AND_SISTERS)
                  .build())
          .add(OTHER, new HashSet<Integer>())
          .build();

}
