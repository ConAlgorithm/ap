package jma.handlers;

import static jma.DatabaseEnumValues.ContactRelationShip.BROTHER;
import static jma.DatabaseEnumValues.ContactRelationShip.BROTHERS_AND_SISTERS;
import static jma.DatabaseEnumValues.ContactRelationShip.DAUGHTER;
import static jma.DatabaseEnumValues.ContactRelationShip.FATHER;
import static jma.DatabaseEnumValues.ContactRelationShip.MOTHER;
import static jma.DatabaseEnumValues.ContactRelationShip.OTHER;
import static jma.DatabaseEnumValues.ContactRelationShip.PARENT;
import static jma.DatabaseEnumValues.ContactRelationShip.SISTER;
import static jma.DatabaseEnumValues.ContactRelationShip.SON;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;

import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class ContactRelationshipUtils {

  private static final int REGULAR_CONTACTS = 3;

  public static class OtherUserStructure {
    public String id;
    public String mobile;
    public String name;

    public OtherUserStructure(String id, String mobile, String name) {
      this.id = id;
      this.mobile = mobile;
      this.name = name;
    }
  }

  public static class ContactStructure {
    public int relation;
    public String phone;
    public String name;

    ContactStructure(int relation, String phone, String name) {
      this.relation = relation;
      this.phone = phone;
      this.name = name;
    }
  }

  public static Map<String, OtherUserStructure> getOtherUsers(
      String thisUserId, Object thisContactMobileNumbers) {
    String sql =
        "SELECT EU.Id, EU.IdName, C.Content AS Mobile " +
        "FROM EndUserExtensionObjects AS EU, UserObjects AS U, ContactObjects AS C " +
        "WHERE EU.Id = U.Id " +
        "    AND U.MobileContactId = C.Id " +
        "    AND EU.IdName IS NOT NULL " +
        "    AND C.Content IN (:MobileNumbers) " +
        "    AND U.Id != :ThisUserId";
    Map<String, ?> params = CollectionUtils.mapOf(
        "MobileNumbers", thisContactMobileNumbers,
        "ThisUserId", thisUserId);

    final Map<String, OtherUserStructure> users = new HashMap<>();
    RowCallbackHandler rowProcessor = new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet row) throws SQLException {
        users.put(
            row.getString("Id"),
            new OtherUserStructure(
                row.getString("Id"), row.getString("Mobile"), row.getString("IdName")));
      }
    };

    DatabaseApi.query(sql, params, rowProcessor);
    return users;
  }

  public static Map<String, ContactStructure> getMobileContacts(String appId) {
    String sql =
        "SELECT TOP (:MaxCount) C.Content AS Phone, CP.Relationship, CP.Name " +
        "FROM ContactPersonObjects AS CP, ContactObjects AS C " +
        "WHERE CP.MobileContactId = C.Id " +
        "    AND CP.AppId = :AppId " +
        "    AND NOT EXISTS ( " +
        "        SELECT CP2.Id " +
        "        FROM ContactPersonObjects AS CP2 " +
        "        WHERE CP2.AppId = CP.AppId " +
        "            AND CP2.ContactPersonType = CP.ContactPersonType " +
        "            AND CP2.LastModified > CP.LastModified )";
    Map<String, ?> params = CollectionUtils.mapOf(
        "AppId", appId,
        "MaxCount", REGULAR_CONTACTS + AppDerivativeVariableManager.getAsInt(
            appId, AppDerivativeVariableNames.CHECK_USER_ADDITIONAL_CONTACT_COUNT, 0));

    final Map<String, ContactStructure> contacts = new HashMap<>();
    RowCallbackHandler rowProcessor = new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet row) throws SQLException {
        if (row.getString("Phone").startsWith("1")) {   // only mobile number
          contacts.put(
              row.getString("Phone"),
              new ContactStructure(
                  aggregateRelation(row.getInt("Relationship")),
                  row.getString("Phone"),
                  row.getString("Name")));
        }
      }
    };

    DatabaseApi.query(sql, params, rowProcessor);
    return contacts;
  }

  private static final Map<Integer, Integer> RELATION_AGGREGATION =
      CollectionUtils.<Integer, Integer>newMapBuilder()
          .add(BROTHER, BROTHERS_AND_SISTERS)
          .add(SISTER, BROTHERS_AND_SISTERS)
          .add(FATHER, PARENT)
          .add(MOTHER, PARENT)
          .add(SON, OTHER)
          .add(DAUGHTER, OTHER)
          .build();

  public static int aggregateRelation(int relation) {
    return RELATION_AGGREGATION.containsKey(relation)
        ? RELATION_AGGREGATION.get(relation)
        : relation;
  }
}
