package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseConfig;

public class CheckIdCardBasicInfoHandler extends JobHandler {

  private static final String ID_CARD_NUMBER_REGEX = "\\d{17}[0-9xX]";
  private static final int[] DIGIT_WEIGHTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
  private static final char[] VERIFIERS = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

  @Override
  public void execute(String appId) throws RetryRequiredException {
    String idCardNumber = DatabaseUtils.getIdCardNumberBy(appId);
    boolean isChecksumValid = isChecksumValid(idCardNumber);
    String gender = "M";
    int age = 0;
    List<String> areaNames = Arrays.asList("", "", "");   // province, city, district

    if (isChecksumValid) {
      gender = Integer.parseInt(String.valueOf(idCardNumber.charAt(16))) % 2 == 0 ? "F" : "M";
      areaNames = getAreaNames(idCardNumber.substring(0, 6));

      Calendar now = Calendar.getInstance();
      int thisYear = now.get(Calendar.YEAR);
      int birthdayYear = Integer.parseInt(idCardNumber.substring(6, 10));
      String thisMonthDay = new SimpleDateFormat("MMdd").format(now.getTime());
      String birthdayMonthDay = idCardNumber.substring(10, 14);
      age = thisMonthDay.compareTo(birthdayMonthDay) < 0
          ? thisYear - birthdayYear - 1
          : thisYear - birthdayYear;
    }

    AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        .add(AppDerivativeVariableNames.ID_CARD_IS_CHECKSUM_VALID, isChecksumValid)
        .add(AppDerivativeVariableNames.ID_CARD_GENDER, gender)
        .add(AppDerivativeVariableNames.ID_CARD_AGE, age)
        .add(AppDerivativeVariableNames.ID_CARD_PROVINCE, areaNames.get(0))
        .add(AppDerivativeVariableNames.ID_CARD_CITY, areaNames.get(1))
        .add(AppDerivativeVariableNames.ID_CARD_DISTRICT, areaNames.get(2))
        .build());
  }

  private static boolean isChecksumValid(String idCardNumber) {
    if (!idCardNumber.matches(ID_CARD_NUMBER_REGEX)) {
      return false;
    }

    int sum = 0;
    for (int i = 0; i < 17; i++) {
      sum += Integer.parseInt(String.valueOf(idCardNumber.charAt(i))) * DIGIT_WEIGHTS[i];
    }
    return VERIFIERS[sum % 11] == idCardNumber.toUpperCase().charAt(17);
  }

  private static List<String> getAreaNames(String areaCode) {
    String sql =
        "SELECT Province, City, District " +
        "FROM IdCardAreaObjects " +
        "WHERE AreaCode = :AreaCode";

    Map<String, ?> params = CollectionUtils.mapOf("AreaCode", areaCode);

    RowMapper<List<String>> rowMapper = new RowMapper<List<String>>() {
      @Override
      public List<String> mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
        return Arrays.asList(
            resultSet.getString("Province"),
            resultSet.getString("City"),
            resultSet.getString("District"));
      }
    };

    List<List<String>> results = new NamedParameterJdbcTemplate(DatabaseConfig.getDataSource())
        .query(sql, params, rowMapper);
    return results.isEmpty() ? Arrays.asList("", "", "") : results.get(0);
  }
}
