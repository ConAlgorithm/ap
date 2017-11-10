package jma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import catfish.base.CollectionUtils;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseConfig;

public class QQDerivativeVariableManager {

  private static void insertQQVariables(QQDerivativeVariable variable) {
    String timeString = DateTimeUtils.getCurrentTimeString();
    String sql = "INSERT INTO QQObjects( "
        + "QQNumber, NickName, QZone, IntimacyScore, RealName, "
        + "SmartName, Bitmap, AvataUrl, IsExist, DateAdded, LastModified) "
        + "VALUES(:QQNumber, :NickName, :QZone, "
        + ":IntimacyScore, :RealName, :SmartName, "
        + ":Bitmap, :AvataUrl, :IsExist, :DateAdded, :LastModified) ";
    Map<String, ?> params = CollectionUtils.<String, Object>newMapBuilder()
        .add("QQNumber", variable.getQQNumber())
        .add("NickName", variable.getNickName())
        .add("QZone", variable.getqZone())
        .add("IntimacyScore", variable.getIntimacyScore())
        .add("RealName", variable.getRealName())
        .add("SmartName", variable.getSmartName())
        .add("Bitmap", variable.getBitmap())
        .add("AvataUrl", variable.getAvataUrl())
        .add("IsExist", variable.isExist()).add("DateAdded", timeString)
        .add("LastModified", timeString).build();
    new NamedParameterJdbcTemplate(DatabaseConfig.getDataSource()).update(sql,
        params);
  }

  private static void updateVariables(String QQNumber, String property,
      Object value) {
    String sql = " UPDATE QQObjects " + " SET " + property + " = :" + property
        + ", LastModified = :LastModified " + " Where QQNumber = :QQNumber ";
    Map<String, ?> params = CollectionUtils.mapOf("QQNumber", QQNumber, property, value,
        "LastModified", DateTimeUtils.getCurrentTimeString());
    new NamedParameterJdbcTemplate(DatabaseConfig.getDataSource()).update(sql,
        params);
  }

  public static void addQQVariables(QQDerivativeVariable variable) {
    QQDerivativeVariable var = getQQVariables(variable.getQQNumber());
    if (var == null) {
      insertQQVariables(variable);
    } else {
      if (variable.getAvataUrl() != null
          && !var.getAvataUrl().equals(variable.getAvataUrl())) {
        updateVariables(variable.getQQNumber(), "AvataUrl",
            variable.getAvataUrl());
      }
      if (variable.getBitmap() != null
          && !var.getBitmap().equals(variable.getBitmap())) {
        updateVariables(variable.getQQNumber(), "Bitmap", variable.getBitmap());
      }
      if (var.getIntimacyScore() != variable.getIntimacyScore()) {
        updateVariables(variable.getQQNumber(), "IntimacyScore",
            variable.getIntimacyScore());
      }
      if (variable.getNickName() != null
          && !var.getNickName().equals(variable.getNickName())) {
        updateVariables(variable.getQQNumber(), "NickName",
            variable.getNickName());
      }
      if (var.getqZone() != variable.getqZone()) {
        updateVariables(variable.getQQNumber(), "QZone", variable.getqZone());
      }
      if (variable.getRealName() != null
          && !var.getRealName().equals(variable.getRealName())) {
        updateVariables(variable.getQQNumber(), "RealName",
            variable.getRealName());
      }
      if (variable.getSmartName() != null
          && !var.getSmartName().equals(variable.getSmartName())) {
        updateVariables(variable.getQQNumber(), "SmartName",
            variable.getSmartName());
      }
      if (var.isExist() == false && variable.isExist() == true) {
        updateVariables(variable.getQQNumber(), "IsExist", true);
      }
    }
  }

  private static RowMapper<QQDerivativeVariable> QQDERIVATIVE_EXTRACKTER = new RowMapper<QQDerivativeVariable>() {
    @Override
    public QQDerivativeVariable mapRow(ResultSet result, int rowIndex)
        throws SQLException {
      QQDerivativeVariable variable = null;
      variable = new QQDerivativeVariable(result.getString(1));
      variable.setNickName(result.getString(2));
      variable.setqZone(result.getInt(3));
      variable.setIntimacyScore(result.getInt(4));
      variable.setRealName(result.getString(5));
      variable.setSmartName(result.getString(6));
      variable.setBitmap(result.getString(7));
      variable.setAvataUrl(result.getString(8));
      variable.setExist(result.getBoolean(9));
      return variable;
    }
  };

  private static QQDerivativeVariable getQQVariables(String QQNumber) {
    String sql = "SELECT QQNumber, NickName, QZone, IntimacyScore, RealName,  SmartName, Bitmap, AvataUrl, IsExist "
        + "FROM QQObjects " + "WHERE QQObjects.QQNumber = :QQNumber ";
    Map<String, String> params = CollectionUtils.mapOf("QQNumber", QQNumber);
    return DatabaseApi.querySingleResultOrDefault(sql, params, QQDERIVATIVE_EXTRACKTER, null);
  }
}
