package catfish.plugins.finance.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.dao.AbstractDao;
import catfish.base.business.object.UserAccountObject;
import catfish.base.database.DatabaseApi;

public class DataService {

  public static UserAccountObject getUserAccountByAppId(String appId) {
    String sql = "select * from UserAccountObjects as ua "
        + "join InstallmentApplicationObjects as ia on ua.UserId = ia.UserId "
        + "where ia.Id = :AppId";
    try {
      return DatabaseApi.querySingleResultOrDefault(
          sql,
          CollectionUtils.mapOf("AppId", appId),
          getObjectRowMapper(UserAccountObject.class),
          null);
    } catch (SQLException e) {
      Logger.get().error(String.format("get user account by appId(%s) failed!", appId), e);
      return null;
    }
  }

  public static <T> RowMapper<T> getObjectRowMapper(final Class<T> clazz)
      throws SQLException {
    return new RowMapper<T>() {
      @Override
      public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AbstractDao.fillObject(clazz, rs);
      }
    };
  }

  public static <T> ResultSetExtractor<List<T>> getListResultSetExtractor(final Class<T> clazz)
      throws SQLException, DataAccessException {
    return new ResultSetExtractor<List<T>>() {
      @Override
      public List<T> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<T> tList = new ArrayList<T>();
        while(resultSet.next()) {
            T obj = AbstractDao.fillObject(clazz, resultSet);
            if(obj != null) {
                tList.add(obj);
            }
        }
        return tList;
      }
    };
  }

}
