package catfish.bonuspoints.rank;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

import catfish.base.StartupConfig;
import catfish.base.business.dao.BonusPointsDao;
import catfish.base.business.dao.BonusPointsRankDao;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.dao.MerchantUserDao;
import catfish.base.business.object.BonusPointsObject;
import catfish.base.business.object.BonusPointsRankObject;
import catfish.base.business.object.CNAreaObject;
import catfish.base.business.object.MerchantStoreObject;
import catfish.base.business.object.MerchantUserObject;
import catfish.bonuspoints.rank.RankResponse.RankObject;
import catfish.bonuspoints.rankupdate.IScheduler;
import catfish.bonuspoints.rankupdate.RankUpdateScheduler;
import catfish.bonuspoints.rankupdate.RankUpdateTask;
import catfish.bonuspoints.rankupdate.ScheduleConfig;

public class RankService {

    private static Date lastUpdateDate = new Date();
    private IScheduler updateScheduler;

    public RankService() {
      boolean updateSchedulerOn = StartupConfig.getAsBoolean("catfish.bonuspoints.updateSchedulerOn");
      if (updateSchedulerOn) {
        initUpdateScheduler();
      }
    }

    public Integer getMyRankOfCityThisWeek(String userId) {
		List<RankObject> rankObjects = getRankObjectList(userId, "Week", "City");
		if (rankObjects == null || rankObjects.isEmpty()) {
		    return null;
		}
		else {
		    int rank = 0;
		    int sameRank = 0;
		    int previousPoint = -1;
		    Collections.sort(rankObjects);
		    for (RankObject rankObject : rankObjects) {
		        if (rankObject.Points == previousPoint) {
		            sameRank++;
		        }
		        else {
		            rank += sameRank + 1;
		            sameRank = 0;
		        }
		        rankObject.Rank = rank;
		        previousPoint = rankObject.Points;

		        if (rankObject.UserId.equals(userId.toUpperCase()))
		        	return rankObject.Rank;
            }
			return null;
		}
    }

	public RankResponse getRankResult(String userId, String timeRange, String territoryRange) {
		List<RankObject> rankObjects = getRankObjectList(userId, timeRange, territoryRange);
		if (rankObjects == null || rankObjects.isEmpty()) {
		    return new RankResponse();
		}
		else {
			RankResponse response = new RankResponse();
		    int rank = 0;
		    int sameRank = 0;
		    int previousPoint = -1;
		    Collections.sort(rankObjects);
		    for (RankObject rankObject : rankObjects) {
		        if (rankObject.Points == previousPoint) {
		            sameRank++;
		        }
		        else {
		            rank += sameRank + 1;
		            sameRank = 0;
		        }
		        rankObject.Rank = rank;
		        previousPoint = rankObject.Points;
            }

		    int topCount = 0;
		    topCount = Math.min(rankObjects.size(), RankResponse.getTopCountByArea(territoryRange));
		    response.myRank = rankObjects.stream()
		        .filter(f -> f.UserId.equals(userId.toUpperCase()))
		        .findFirst().orElse(null);
		    response.tops = new RankObject[topCount];
	        for (int i = 0; i < topCount; i++)
	            response.tops[i] = rankObjects.get(i);
			return response;
		}
	}

	private List<RankObject> getRankObjectList(String userId, String timeRange, String territoryRange) {
        lastUpdateDate = ((RankUpdateScheduler) updateScheduler).getLastUpdateDate();
		MerchantUserObject user = MerchantUserDao.getMerchantUserbyUserId(userId);
		if (user == null || user.MerchantStoreId == null) {
			return null;
		}
		MerchantStoreObject store = (new MerchantStoreDao(user.MerchantStoreId)).getSingle();
		CNAreaObject area = (new CNAreaDao(store.CNAreaId)).getSingle();
		CNAreaObject city = CNAreaDao.getCNAreaObjByAreaCode(area.parentCode);
		CNAreaObject province = CNAreaDao.getCNAreaObjByAreaCode(city.parentCode);

		//deal with 直辖市
		if (!checkCityName(city)) {
			if (territoryRange.equals("City"))
				territoryRange = "Province";
		}

		List<BonusPointsObject> bonusPointsList;
		List<BonusPointsRankObject> bonusPointsRankList;
		if (territoryRange.equals("Province")) {
			bonusPointsList = BonusPointsDao.getBonusPointsByProvinceCodeSinceLastUpdate(province.Code, lastUpdateDate);
			bonusPointsRankList = BonusPointsRankDao.getBonusPointsRankByProvinceCode(province.Code);
		}
		else if (territoryRange.equals("City")) {
			bonusPointsList = BonusPointsDao.getBonusPointsByCityCodeSinceLastUpdate(city.Code, lastUpdateDate);
			bonusPointsRankList = BonusPointsRankDao.getBonusPointsRankByCityCode(city.Code);
		}
		else {
			bonusPointsList = BonusPointsDao.getBonusPointsByAreaIdSinceLastUpdate(area.Id, lastUpdateDate);
			bonusPointsRankList = BonusPointsRankDao.getBonusPointsRankByAreaId(area.Id);
		}

		RankResponse response = new RankResponse();
		List<RankObject> rankObjects = new ArrayList<RankObject>();
		Map<String, Integer> idMap = new HashMap<String, Integer>();
		for (BonusPointsRankObject dbRankObject : safeList(bonusPointsRankList)) {
			RankObject rankObj;
			if (timeRange.equals("Week"))
				rankObj = response.createRankObject(dbRankObject.UserId.toUpperCase(), dbRankObject.ThisWeekPoint);
			else
				rankObj = response.createRankObject(dbRankObject.UserId.toUpperCase(), dbRankObject.TotalPoint);

			Integer lastRank = getLastRankBy(dbRankObject ,timeRange, territoryRange);
			rankObj.LastRank = lastRank == null ? 0 : lastRank.intValue();

			rankObjects.add(rankObj);
			idMap.put(dbRankObject.UserId.toUpperCase(), rankObjects.indexOf(rankObj));
		}

		for (BonusPointsObject pointsObject : safeList(bonusPointsList)) {
		    if (idMap.containsKey(pointsObject.UserId.toUpperCase())) {
		        int index = idMap.get(pointsObject.UserId.toUpperCase());
		        rankObjects.get(index).addPoints(pointsObject.Point);
		    }
			else {
				RankObject rankObj = response.createRankObject(pointsObject.UserId.toUpperCase(), pointsObject.Point);
				rankObjects.add(rankObj);
				idMap.put(rankObj.UserId.toUpperCase(), rankObjects.indexOf(rankObj));
			}
		}
		return rankObjects;
	}

	private boolean checkCityName(CNAreaObject city) {
		if (city.Name.equals("省直辖行政单位") || city.Name.equals("县") || city.Name.equals("市辖区") || city.Name.equals("市"))
			return false;
		return true;
	}

    private void initUpdateScheduler() {
      updateScheduler = new RankUpdateScheduler();
      updateScheduler.addTask(
          new RankUpdateTask(),
          new ScheduleConfig(getUpdateRankFirstTime(), ScheduleConfig.ONE_DAY));
      updateScheduler.start();
    }

    private Date getUpdateRankFirstTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    private Integer getLastRankBy(BonusPointsRankObject rank, String period, String area) {
      if (period.equals("Week") && area.equals("Province")) {
        return rank.RankProvinceLastWeek;
      }
      else if (period.equals("Week") && area.equals("City")) {
        return rank.RankCityLastWeek;
      }
      else if (period.equals("Week") && area.equals("Area")) {
        return rank.RankDistrictLastWeek;
      }
      else if (period.equals("History") && area.equals("Province")) {
        return rank.RankProvinceHistory;
      }
      else if (period.equals("History") && area.equals("City")) {
        return rank.RankCityHistory;
      }
      else if (period.equals("History") && area.equals("Area")) {
        return rank.RankDistrictHistory;
      }
      return 0;
    }

    private <T> List<T> safeList(List<T> list) {
      if (list == null) {
        return Collections.<T>emptyList();
      }
      return list;
    }
}
