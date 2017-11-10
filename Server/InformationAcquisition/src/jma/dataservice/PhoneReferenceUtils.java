package jma.dataservice;

import java.util.Map;

import jma.DatabaseEnumValues;
import catfish.base.CollectionUtils;
import catfish.base.database.DatabaseApi;

public class PhoneReferenceUtils {

  //手机号被其他身份证号用户作为申请手机号的引用次数
  public static int getOtherUserMobileReference(String currentUserIdNumber, String phoneNumber) {
    String sql =
        "SELECT COUNT (DISTINCT ee.IdNumber) " +
        "FROM InstallmentApplicationObjects AS ia, UserObjects AS uo, " +
        "    EndUserExtensionObjects AS ee, ContactObjects AS co " +
        "WHERE ia.UserId = uo.Id " +
        "    AND uo.Id = ee.Id " +
        "    AND uo.MobileContactId = co.Id " +
        "    AND co.ContactType = :Mobile " +
        "    AND co.Content = :PhoneNumber " +
        "    AND ee.IdNumber != :CurrentUserIdNumber";

    Map<String, ?> params = CollectionUtils.mapOf(
        "Mobile", DatabaseEnumValues.ContactType.MOBILE,
        "PhoneNumber", phoneNumber,
        "CurrentUserIdNumber", currentUserIdNumber);

    return DatabaseApi.querySingleInteger(sql, params);
  }

  public static int getOtherCompanyTelReference(String currentUserIdNumber, String phoneNumber) {
    String sql =
        "SELECT COUNT (DISTINCT EE.IdNumber) " +
        "FROM InstallmentApplicationObjects A1, EndUserExtensionObjects EE, JobInfoObjects J1, " +
        "    ContactObjects C1 " +
        "WHERE A1.UserId = EE.Id " +
        "    AND A1.Id = J1.ApplicationId " +
        "    AND J1.CompanyTelId = C1.Id " +
        "    AND C1.Content = :PhoneNumber " +
        "    AND EE.IdNumber != :CurrentUserIdNumber " +
        "    AND NOT EXISTS ( " +
        "        SELECT C2.Id " +
        "        FROM InstallmentApplicationObjects A2, JobInfoObjects J2, ContactObjects C2 " +
        "        WHERE A2.Id = J2.ApplicationId " +
        "            AND J2.CompanyTelId = C2.Id " +
        "            AND A2.UserId = A1.UserId " +
        "            AND C2.Id != C1.Id " +
        "            AND DATEDIFF (SS, C1.LastModified, C2.LastModified) > 0)";  // C2 after C1

    Map<String, ?> params = CollectionUtils.mapOf(
        "PhoneNumber", phoneNumber,
        "CurrentUserIdNumber", currentUserIdNumber);

    return DatabaseApi.querySingleInteger(sql, params);
  }

  public static int getOtherFirstContactMobileReference(String currentUserIdNumber, String phoneNumber) {
    return getOtherContactMobileReference(
        currentUserIdNumber, phoneNumber, DatabaseEnumValues.ContactPersonType.FIRST_CONTACT_PERSON);
  }

  public static int getOtherSecondContactMobileReference(String currentUserIdNumber, String phoneNumber) {
    return getOtherContactMobileReference(
       currentUserIdNumber, phoneNumber, DatabaseEnumValues.ContactPersonType.SECOND_CONTACT_PERSON);
  }

  public static int getOtherThirdContactMobileReference(String currentUserIdNumber, String phoneNumber) {
    return getOtherContactMobileReference(
        currentUserIdNumber, phoneNumber, DatabaseEnumValues.ContactPersonType.THIRD_CONTACT_PERSON);
  }

  public static int getOtherAdditionalContactMobileReference(
      String currentUserIdNumber, String phoneNumber) {
      String sql =
          "SELECT COUNT (DISTINCT EE.IdNumber) " +
          "FROM InstallmentApplicationObjects A1, EndUserExtensionObjects EE, " +
          "    ContactPersonObjects CP1, ContactObjects C1 " +
          "WHERE A1.Id = CP1.AppId " +
          "    AND A1.UserId = EE.Id " +
          "    AND CP1.MobileContactId = C1.Id " +
          "    AND CP1.ContactPersonType >= :ContactPersonTypeBase " +
          "    AND CP1.ContactPersonType <= :ContactPersonUp " +
          "    AND C1.Content = :PhoneNumber " +
          "    AND EE.IdNumber != :CurrentUserIdNumber " +
          "    AND NOT EXISTS ( " +
          "        SELECT C2.Id " +
          "        FROM InstallmentApplicationObjects A2, ContactPersonObjects CP2," +
          "            ContactObjects C2 " +
          "        WHERE A2.Id = CP2.AppId " +
          "            AND CP2.MobileContactId = C2.Id " +
          "            AND CP2.ContactPersonType = CP1.ContactPersonType " +
          "            AND A2.UserId = A1.UserId " +
          "            AND C2.Id != C1.Id " +
          "            AND DATEDIFF (SS, C1.LastModified, C2.LastModified) > 0)";  // C2 after C1

      Map<String, ?> params = CollectionUtils.mapOf(
          "ContactPersonTypeBase", DatabaseEnumValues.ContactPersonType.ADDITIONAL_CONTACT_PERSON_BASE,
          "ContactPersonUp", DatabaseEnumValues.ContactPersonType.ADDITIONAL_CONTACT_PERSON_UP,
          "PhoneNumber", phoneNumber,
          "CurrentUserIdNumber", currentUserIdNumber);

      return DatabaseApi.querySingleInteger(sql, params);
  }

  public static int getOtherParentPhoneReference(String currentUserIdNumber, String phoneNumber) {
    String sql =
        "SELECT COUNT (DISTINCT ee.IdNumber) " +
        "FROM InstallmentApplicationObjects AS ia1, EndUserExtensionObjects AS ee, " +
        "    ContactPersonObjects AS cp1, ContactObjects AS co1 " +
        "WHERE ia1.Id = cp1.AppId " +
        "    AND ia1.UserId = ee.Id " +
        "    AND ee.IdNumber != :CurrentUserIdNumber " +
        "    AND cp1.MobileContactId = co1.Id " +
        "    AND co1.Content = :PhoneNumber " +
        "    AND (cp1.Relationship = :Father OR cp1.Relationship = :Mother " +
        "        OR cp1.Relationship = :Parent) " +
        "    AND NOT EXISTS ( " +
        "        SELECT co2.Id " +
        "        FROM InstallmentApplicationObjects AS ia2, ContactPersonObjects AS cp2, " +
        "            ContactObjects AS co2 " +
        "        WHERE ia2.Id = cp2.AppId " +
        "            AND ia2.UserId = ia1.UserId " +
        "            AND cp2.MobileContactId = co2.Id " +
        "            AND co2.Content = co1.Content " +
        "            AND cp2.Relationship = cp1.Relationship " +
        "            AND DATEDIFF (SS, co1.LastModified, co2.LastModified) > 0)";

    Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder()
        .add("CurrentUserIdNumber", currentUserIdNumber)
        .add("PhoneNumber", phoneNumber)
        .add("Father", DatabaseEnumValues.ContactRelationShip.FATHER)
        .add("Mother", DatabaseEnumValues.ContactRelationShip.MOTHER)
        .add("Parent", DatabaseEnumValues.ContactRelationShip.PARENT)
        .build();

    return DatabaseApi.querySingleInteger(sql, params);
  }

  private static int getOtherContactMobileReference(
      String currentUserIdNumber, String phoneNumber, int contactPersonType) {
    String sql =
        "SELECT COUNT (DISTINCT EE.IdNumber) " +
        "FROM InstallmentApplicationObjects A1, EndUserExtensionObjects EE, " +
        "    ContactPersonObjects CP1, ContactObjects C1 " +
        "WHERE A1.UserId = EE.Id " +
        "    AND A1.Id = CP1.AppId " +
        "    AND CP1.MobileContactId = C1.Id " +
        "    AND CP1.ContactPersonType = :ContactPersonType " +
        "    AND C1.Content = :PhoneNumber " +
        "    AND EE.IdNumber != :CurrentUserIdNumber " +
        "    AND NOT EXISTS ( " +
        "        SELECT C2.Id " +
        "        FROM InstallmentApplicationObjects A2, ContactPersonObjects CP2," +
        "            ContactObjects C2 " +
        "        WHERE A2.Id = CP2.AppId " +
        "            AND CP2.MobileContactId = C2.Id " +
        "            AND CP2.ContactPersonType = CP1.ContactPersonType " +
        "            AND A2.UserId = A1.UserId " +
        "            AND C2.Id != C1.Id " +
        "            AND DATEDIFF (SS, C1.LastModified, C2.LastModified) > 0)";  // C2 after C1

    Map<String, Object> params = CollectionUtils.<String, Object>mapOf(
        "ContactPersonType", contactPersonType,
        "PhoneNumber", phoneNumber,
        "CurrentUserIdNumber", currentUserIdNumber);

    return DatabaseApi.querySingleInteger(sql, params);
  }
}
