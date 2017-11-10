package jma.dataservice;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.ContactPersonType;
import catfish.base.business.common.ContactType;
import catfish.base.business.dao.DBFieldParser;
import catfish.base.database.DatabaseApi;
import catfish.base.exception.DBFieldParseException;
import jma.models.ContactInfo;

public class PhoneUtils {

  public static String getUserMobile(String appId) {
    String sql =
        "SELECT ContactObjects.Content " +
        "FROM ContactObjects, UserObjects, InstallmentApplicationObjects " +
        "WHERE ContactObjects.Id = UserObjects.MobileContactId " +
        "  AND UserObjects.Id = InstallmentApplicationObjects.UserId " +
        "  AND ContactObjects.ContactType = :ContactType " +
        "  AND InstallmentApplicationObjects.Id = :AppId " +
        "ORDER BY ContactObjects.LastModified DESC";

    Map<String, ?> params = CollectionUtils.mapOf(
        "ContactType", ContactType.Mobile.getValue(),
        "AppId", appId);

    return DatabaseApi.querySingleString(sql, params);
  }

  public static String getCompanyTel(String appId) {
    String sql =
        "SELECT ContactObjects.Content " +
        "FROM ContactObjects, JobInfoObjects " +
        "WHERE ContactObjects.Id = JobInfoObjects.CompanyTelId " +
        "    AND JobInfoObjects.ApplicationId = :AppId " +
        "ORDER BY ContactObjects.LastModified DESC";

    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }

  public static String getFirstContactMobile(String appId) {
    return getContactMobile(appId, ContactPersonType.FirstContactPerson.getValue());
  }

  public static String getSecondContactMobile(String appId) {
    return getContactMobile(appId, ContactPersonType.SecondContactPerson.getValue());
  }

  public static String getThirdContactMobile(String appId) {
    return getContactMobile(appId, ContactPersonType.ThirdContactPerson.getValue());
  }

  public static String getAdditionalContactMobile(String appId, int additionalContactNumber) {
    return getContactMobile(appId, additionalContactNumber);
  }

  public static ContactInfo getFirstContactInfo(String appId) {
	  return getContactInfo(appId, ContactPersonType.FirstContactPerson.getValue());	  
  }
  
  public static ContactInfo getSecondContactInfo(String appId) {
	  return getContactInfo(appId, ContactPersonType.SecondContactPerson.getValue());	  
  }
  
  private static String getContactMobile(String appId, int contactType) {
    String sql =
        "SELECT ContactObjects.Content " +
        "FROM ContactObjects, ContactPersonObjects " +
        "WHERE ContactObjects.Id = ContactPersonObjects.MobileContactId " +
        "    AND ContactPersonObjects.AppId = :AppId " +
        "    AND ContactPersonObjects.ContactPersonType = :ContactPersonType " +
        "    AND ContactObjects.ContactType = :ContactType " +
        "ORDER BY ContactObjects.LastModified DESC";

    Map<String, ?> params = CollectionUtils.mapOf(
        "ContactPersonType", contactType,
        "ContactType", ContactType.Mobile.getValue(),
        "AppId", appId);

    return DatabaseApi.querySingleStringOrDefault(sql, params, null);
  }
  
  private static ContactInfo getContactInfo(String appId, int contactType) {
	    String sql =
	        "SELECT ContactObjects.Content as mobile, ContactPersonObjects.Name as name, ContactPersonObjects.Relationship as relationShip " +
	        "FROM ContactObjects, ContactPersonObjects " +
	        "WHERE ContactObjects.Id = ContactPersonObjects.MobileContactId " +
	        "    AND ContactPersonObjects.AppId = :AppId " +
	        "    AND ContactPersonObjects.ContactPersonType = :ContactPersonType " +
	        "    AND ContactObjects.ContactType = :ContactType " +
	        "ORDER BY ContactObjects.LastModified DESC";

	    Map<String, ?> params = CollectionUtils.mapOf(
	        "ContactPersonType", contactType,
	        "ContactType", ContactType.Mobile.getValue(),
	        "AppId", appId);

	    return DatabaseApi.querySingleResult(sql, params, new RowMapper<ContactInfo>()
	    {

			@Override
			public ContactInfo mapRow(ResultSet resultSet, int arg1)
					throws SQLException {
				if(resultSet != null)
					return fillObject(ContactInfo.class, resultSet);
				return null;
			}
		});
	  } 
	public static <T> T fillObject(Class<T> objClass, ResultSet resultSet)
	{
		String fieldName = null;
		try {
			T obj  = objClass.newInstance();
			Field[] fields = objClass.getFields();
			Object value = null;
			for(Field field : fields)
			{
				fieldName = field.getName();
				try{
					value = resultSet.getObject(fieldName);
					if(value != null)
					     DBFieldParser.fill(obj, field, value.toString());
				}catch (SQLException e)
				{
					Logger.get().warn(String.format("Database table %s did not has the field %s ", objClass.getSimpleName(), fieldName), e);
				}
			}
			return obj;
		} catch (DBFieldParseException e) {
          Logger.get().error(String.format("Parse database field into %s of Object %s error ", fieldName, objClass.getName()), e);
		} catch (Exception e)
		{
			Logger.get().error(String.format("Parse database field into %s of Object %s error ", fieldName, objClass.getName()), e);
		}
		return null;
	}  
}
