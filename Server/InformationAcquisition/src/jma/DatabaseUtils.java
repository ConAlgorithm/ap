package jma;

import java.util.Date;
import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class DatabaseUtils {

  public static String getUserIdBy(String appId) {
    String sql =
        "SELECT UserId " +
        "FROM InstallmentApplicationObjects " +
        "WHERE Id = :AppId";

    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }

  public static String getIdCardNumberBy(String appId) {
    String sql =
        "SELECT EndUserExtensionObjects.IdNumber " +
        "FROM EndUserExtensionObjects, InstallmentApplicationObjects " +
        "WHERE EndUserExtensionObjects.Id = InstallmentApplicationObjects.UserId " +
        "    AND InstallmentApplicationObjects.Id = :AppId";

    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }

  public static Date getInstallmentStartedOnBy(String appId) {
    String sql =
        "SELECT InstallmentStartedOn " +
        "FROM InstallmentApplicationObjects " +
        "WHERE Id = :AppId";

    return DateTimeUtils.parse(
        DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId)));
  }

  public static List<String> getIdCardNameAndNumber(String appId) {
    String sql =
        "SELECT E.IdName, E.IdNumber " +
        "FROM EndUserExtensionObjects AS E, InstallmentApplicationObjects AS I " +
        "WHERE E.Id = I.UserID " +
        "    AND I.Id = :AppId";

    return DatabaseApi.querySingleResult(
        sql, CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.TWO_STRING_EXTRACTOR);
  }

  public static String getUserQQ(String appId) {
    String sql =
        "SELECT ContactObjects.Content " +
        "FROM InstallmentApplicationObjects, PersonalInfoObjects, " +
        "     ContactObjects " +
        "WHERE InstallmentApplicationObjects.Id = PersonalInfoObjects.AppId " +
        "    AND PersonalInfoObjects.QQContactId = ContactObjects.Id " +
        "    AND ContactObjects.ContactType = :ContactType " +
        "    AND InstallmentApplicationObjects.Id = :AppId " +
        "ORDER BY ContactObjects.LastModified DESC";

    Map<String, ?> params = CollectionUtils.mapOf(
        "ContactType", DatabaseEnumValues.ContactType.QQ,
        "AppId", appId);

    return DatabaseApi.querySingleString(sql, params);
  }
}
