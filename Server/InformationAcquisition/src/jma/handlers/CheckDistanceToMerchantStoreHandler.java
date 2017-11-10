package jma.handlers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jma.JobHandler;
import jma.RetryRequiredException;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;

public class CheckDistanceToMerchantStoreHandler extends JobHandler {

  static class Location {
    double lng;
    double lat;

    public Location(double lng, double lat) {
      this.lng = lng;
      this.lat = lat;
    }
  }

  public static final int HOME = 10;
  public static final int WORK = 20;
  public static final int MERCHANT = 30;

  private static RowMapper<Location> LOCATION_EXTRACTOR = new RowMapper<Location>() {
    @Override
    public Location mapRow(ResultSet result, int rowIndex) throws SQLException {
      if (result.getDouble(1) != 0) {
        return new Location(result.getDouble(1), result.getDouble(2));
      } else {
        return null;
      }
    }
  };

  @Override
  public void execute(String appId) throws RetryRequiredException {
    Map<Integer, Location> AddressMapping = getAddressData(appId);
    if (AddressMapping == null) {
      return;
    }

    if (AddressMapping.containsKey(HOME)) {
      AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
          appId,
          AppDerivativeVariableNames.DISTANCE_BETWEEN_RESIDENCE_AND_STORE,
          getDistance(AddressMapping.get(HOME), AddressMapping.get(MERCHANT))));
    }

    if (AddressMapping.containsKey(WORK)) {
      AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
          appId,
          AppDerivativeVariableNames.DISTANCE_BETWEEN_WORK_PLACE_AND_STORE,
          getDistance(AddressMapping.get(WORK), AddressMapping.get(MERCHANT))));
    }
  }

  private Map<Integer, Location> getAddressData(String appId) {
    Map<Integer, Location> addressData = new HashMap<>();
    String sql =
        "SELECT LngBaidu, LatBaidu " +
        "FROM AddressInfoObjects " +
        "WHERE AppId = :AppId " +
        "    AND Type = :Type " +
        "ORDER BY DateAdded DESC";

    Location merchant = DatabaseApi.querySingleResultOrDefault(sql, CollectionUtils.mapOf("AppId", appId, "Type", MERCHANT), LOCATION_EXTRACTOR, null);
    if (merchant != null) {
      addressData.put(MERCHANT, merchant);
    } else {
      return null;
    }

    Location home = DatabaseApi.querySingleResultOrDefault(sql, CollectionUtils.mapOf("AppId", appId, "Type", HOME), LOCATION_EXTRACTOR, null);
    if (home != null) {
      addressData.put(HOME, home);
    }

    Location work = DatabaseApi.querySingleResultOrDefault(sql, CollectionUtils.mapOf("AppId", appId, "Type", WORK), LOCATION_EXTRACTOR, null);
    if (work != null) {
      addressData.put(WORK, work);
    }

    return addressData;
  }

  private static BigDecimal getDistance(Location loc1, Location loc2) {
    final double EARTH_RADIUS = 6378000;    // average earth radius /meter
    final double PI = 3.1415926;

    double c = Math.cos(loc1.lat)
             * Math.cos(loc2.lat)
             * Math.cos(loc1.lng - loc2.lng)
             + Math.sin(loc1.lat)
             * Math.sin(loc2.lat);

    return new BigDecimal(Double.toString(EARTH_RADIUS * Math.acos(c) * PI / 180));
  }
}
