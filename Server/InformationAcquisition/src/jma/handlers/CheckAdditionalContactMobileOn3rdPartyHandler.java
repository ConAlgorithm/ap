package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseEnumValues;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckAdditionalContactMobileOn3rdPartyHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String sql =
        "SELECT Province, City, ServiceProvider " +
        "FROM MobilePhoneAreaObjects " +
        "WHERE Prefix = :Prefix";

    RowMapper<List<String>> extractor = new RowMapper<List<String>>() {
      @Override
      public List<String> mapRow(ResultSet resultSet, int index) throws SQLException {
        return Arrays.asList(
            resultSet.getString("Province"),
            resultSet.getString("City"),
            resultSet.getString("ServiceProvider"));
      }
    };

    for(int i = DatabaseEnumValues.ContactPersonType.ADDITIONAL_CONTACT_PERSON_BASE;
        i <= DatabaseEnumValues.ContactPersonType.ADDITIONAL_CONTACT_PERSON_UP;
        i++) {
      String mobileNumber = PhoneUtils.getAdditionalContactMobile(appId, i);
      if (mobileNumber == null) {
        return;
      }

      List<String> result = DatabaseApi.querySingleResultOrDefault(sql, CollectionUtils.mapOf("Prefix", mobileNumber.substring(0, 7)), extractor, Arrays.asList("", "", ""));

      AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_PROVINCE + "_" + i, result.get(0))
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_CITY + "_" + i, result.get(1))
          .add(AppDerivativeVariableNames.ADDITIONAL_CONTACT_MOBILE_SUPPLIER + "_" + i, result.get(2))
          .build());
    }
  }
}
