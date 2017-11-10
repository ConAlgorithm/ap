package catfish.bonuspoints.rankupdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowCallbackHandler;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.BonusPointRankAreaType;
import catfish.base.business.common.BonusPointRankPeriodType;
import catfish.base.business.common.MerchantUserRole;
import catfish.base.business.dao.BonusPointsDao;
import catfish.base.business.dao.BonusPointsRankDao;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.object.BonusPointsObject;
import catfish.base.business.object.BonusPointsRankObject;
import catfish.base.business.object.CNAreaObject;
import catfish.base.database.DatabaseApi;

public class RankUpdateTask extends TimerTask {

  @Override
  public void run() {
    Logger.get().info("bonus points: rank updating ...");
    List<BonusPointsRankObject> ranks = new ArrayList<BonusPointsRankObject>();
    Map<String, List<String>> s1IdToAreaIdsMap = getS1IdAndAreaIdsMap();
    boolean isFirstDay = isTodayTheFirstDayOfWeek();
    updatePoints(s1IdToAreaIdsMap.keySet(), ranks, isFirstDay);
    updateLastWeekRanks(s1IdToAreaIdsMap, ranks, isFirstDay);
    updateDatabase(ranks, isFirstDay);
    Logger.get().info("bonus points: rank updated.");
  }

  private void updatePoints(Set<String> s1Ids, List<BonusPointsRankObject> ranks, boolean isFirstDay) {
    Date firstDayOfThisWeek = getFirstDayOfThisWeek();
    Date firstDayOfLastWeek = getFirstDayOfLastWeek();
    List<BonusPointsObject> bps = new BonusPointsDao().getMultiple();

    s1Ids.forEach(id -> {
      BonusPointsRankObject rank = new BonusPointsRankObject();
      List<BonusPointsObject> userPoints = bps.stream()
          .filter(f -> f.UserId.equals(id))
          .collect(Collectors.toList());
      rank.UserId = id;
      rank.TotalPoint = userPoints.stream()
          .mapToInt(m -> m.Point)
          .sum();
      rank.ThisWeekPoint = userPoints.stream()
          .filter(f -> f.DateAdded.after(firstDayOfThisWeek))
          .mapToInt(m -> m.Point)
          .sum();
      if (isFirstDay) {
        rank.TotalPointEndOfLastWeek = userPoints.stream()
            .filter(f -> f.DateAdded.before(firstDayOfThisWeek))
            .mapToInt(m -> m.Point)
            .sum();
        rank.LastWeekPoint = userPoints.stream()
            .filter(f -> f.DateAdded.after(firstDayOfLastWeek) && f.DateAdded.before(firstDayOfThisWeek))
            .mapToInt(m -> m.Point)
            .sum();
      }
      ranks.add(rank);
    });
  }

  private void updateLastWeekRanks(
      Map<String, List<String>> s1IdToAreaIdsMap, List<BonusPointsRankObject> ranks, boolean isFirstDay) {
    if (!isFirstDay) {
      return;
    }

    Map<String, List<String>> districtIdToS1IdsMap = new HashMap<>();
    Map<String, List<String>> cityIdToS1IdsMap = new HashMap<>();
    Map<String, List<String>> provinceIdToS1IdsMap = new HashMap<>();
    mapS1IdsToAreaId(s1IdToAreaIdsMap, districtIdToS1IdsMap, cityIdToS1IdsMap, provinceIdToS1IdsMap);

    ranks.sort(new BonusPointsRankObject.TotalPointLastWeekComparator());
    updateRankBy(districtIdToS1IdsMap, BonusPointRankPeriodType.TOTAL_LAST_WEEK, BonusPointRankAreaType.DISTRICT, ranks);
    updateRankBy(cityIdToS1IdsMap, BonusPointRankPeriodType.TOTAL_LAST_WEEK, BonusPointRankAreaType.CITY, ranks);
    updateRankBy(provinceIdToS1IdsMap, BonusPointRankPeriodType.TOTAL_LAST_WEEK, BonusPointRankAreaType.PROVINCE, ranks);

    ranks.sort(new BonusPointsRankObject.LastWeekPointComparator());
    updateRankBy(districtIdToS1IdsMap, BonusPointRankPeriodType.LAST_WEEK, BonusPointRankAreaType.DISTRICT, ranks);
    updateRankBy(cityIdToS1IdsMap, BonusPointRankPeriodType.LAST_WEEK, BonusPointRankAreaType.CITY, ranks);
    updateRankBy(provinceIdToS1IdsMap, BonusPointRankPeriodType.LAST_WEEK, BonusPointRankAreaType.PROVINCE, ranks);
  }

  private void updateDatabase(List<BonusPointsRankObject> ranks, boolean isFirstDay) {
    ranks.forEach(rank -> {
      if (new BonusPointsRankDao(rank.UserId).getSingle() != null) {
        if (isFirstDay) {
          BonusPointsRankDao.update(rank);
        }
        else {
          BonusPointsRankDao.updatePoints(rank);
        }
      }
      else {
        BonusPointsRankDao.insert(rank);
      }
    });
  }

  private void mapS1IdsToAreaId(
      Map<String, List<String>> s1IdToAreaIdsMap,
      Map<String, List<String>> districtIdToS1IdsMap,
      Map<String, List<String>> cityIdToS1IdsMap,
      Map<String, List<String>> provinceIdToS1IdsMap) {
    s1IdToAreaIdsMap.forEach((s1Id, areaIds) -> {
      String district = areaIds.get(0);
      String city = areaIds.get(1);
      String province = areaIds.get(2);

      if (!districtIdToS1IdsMap.containsKey(district)) {
        districtIdToS1IdsMap.put(district, new ArrayList<>());
      }
      districtIdToS1IdsMap.get(district).add(s1Id);

      if (!cityIdToS1IdsMap.containsKey(city)) {
        cityIdToS1IdsMap.put(city, new ArrayList<>());
      }
      cityIdToS1IdsMap.get(city).add(s1Id);

      if (!provinceIdToS1IdsMap.containsKey(province)) {
        provinceIdToS1IdsMap.put(province, new ArrayList<>());
      }
      provinceIdToS1IdsMap.get(province).add(s1Id);
    });
  }

  private void updateRankBy(
      Map<String, List<String>> areaIdToS1IdsMap,
      int periodType,
      int areaType,
      List<BonusPointsRankObject> ranks) {
    areaIdToS1IdsMap.forEach((area, s1Ids) -> {
      int order = 0;
      int sameOrder = 0;
      int previousPoint = -1;
      for (BonusPointsRankObject rank
          : ranks.stream().filter(f -> s1Ids.contains(f.UserId)).collect(Collectors.toList())) {
        if (rank.getPoint(periodType) == previousPoint) {
          sameOrder++;
        }
        else {
          order += sameOrder + 1;
          sameOrder = 0;
        }
        rank.setRank(periodType, areaType, order);
        previousPoint = rank.getPoint(periodType);
      }
    });
  }

  // first day: Monday
  private Date getFirstDayOfThisWeek() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.add(Calendar.DATE, - cal.get(Calendar.DAY_OF_WEEK) + 2);
    return cal.getTime();
  }

  private Date getFirstDayOfLastWeek() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.add(Calendar.DATE, - cal.get(Calendar.DAY_OF_WEEK) - 5);
    return cal.getTime();
  }

  private boolean isTodayTheFirstDayOfWeek() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
  }

  private Map<String, List<String>> getS1IdAndAreaIdsMap() {
    final Map<String, String> S1IdToAreaIdMap = new HashMap<>();
    String sql =
        "SELECT mu.Id, ms.CNAreaId from MerchantUserObjects AS mu, MerchantStoreObjects AS ms " +
        "WHERE mu.MerchantStoreId = ms.Id " +
        "    and mu.Role & :S1 > 0 " +
        "    and mu.Status = :Status";
    RowCallbackHandler rowProcessor = new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet row) throws SQLException {
        S1IdToAreaIdMap.put(row.getString("Id"), row.getString("CNAreaId"));
      }
    };
    DatabaseApi.query(
        sql, CollectionUtils.mapOf("S1", MerchantUserRole.S1.getValue(), "Status", 0), rowProcessor);

    Map<String, List<String>> result = new HashMap<>();
    List<CNAreaObject> areas = new CNAreaDao().getMultiple();
    S1IdToAreaIdMap.forEach((s1Id, areaId) -> {
      CNAreaObject district = areas.stream().filter(f -> f.Id.equals(areaId)).findFirst().orElse(null);
      if (district == null) {
        return;
      }
      CNAreaObject city = areas.stream().filter(f -> f.Code.equals(district.parentCode)).findFirst().orElse(null);
      if (city == null) {
        return;
      }
      CNAreaObject province = areas.stream().filter(f -> f.Code.equals(city.parentCode)).findFirst().orElse(null);
      if (province == null) {
        return;
      }
      result.put(s1Id, Arrays.asList(district.Id, city.Id, province.Id));
    });

    return result;
  }

}
