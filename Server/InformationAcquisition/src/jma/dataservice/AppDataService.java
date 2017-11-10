package jma.dataservice;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jma.DatabaseEnumValues.ApplicationStatus;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class AppDataService {

	public static String getMerchantStoreIdBy(String appId) {
		String sql = "SELECT MerchantStoreId "
				+ "FROM InstallmentApplicationObjects " 
				+ "WHERE Id = :AppId";

		return DatabaseApi.querySingleString(sql,
				CollectionUtils.mapOf("AppId", appId));
	}

	public static String getUserId(String appId) {
		String sql = "SELECT UserId "
				+ "FROM InstallmentApplicationObjects " 
				+ "WHERE Id = :AppId";

		return DatabaseApi.querySingleString(sql,
				CollectionUtils.mapOf("AppId", appId));
	}
	
	public static InstallmentApplicationObject getInstallmentAppObject(String appId) {
		return new InstallmentApplicationDao(appId).getSingle();
	}

	public static Date getInstallmentStartedOnBy(String appId) {
		String sql = "SELECT InstallmentStartedOn "
				+ "FROM InstallmentApplicationObjects " 
				+ "WHERE Id = :AppId";

		return DateTimeUtils.parse(DatabaseApi.querySingleString(sql,
				CollectionUtils.mapOf("AppId", appId)));
	}

	public static int getHistoryApproved(List<String> merchantStoreIds) {
	
	    String sql =
	        "SELECT COUNT(*) " +
	        "FROM InstallmentApplicationObjects A " +
	        "WHERE A.MerchantStoreId IN (:merchantStoreIds) " +
	        "    AND A.Status >= :StatusApproved " +
	        "    AND A.Status != :StatusRejected";

	    Map<String, ?> params = CollectionUtils.mapOf(
	        "merchantStoreIds", merchantStoreIds,
	        "StatusApproved", ApplicationStatus.APPROVED,
	        "StatusRejected", ApplicationStatus.REJECTED);

	    return DatabaseApi.querySingleInteger(sql, params);
	}
	
	public static int getHistoryTotal(List<String> merchantStoreIds) {
		    String sql =
		        "SELECT COUNT(*) " +
		        "FROM InstallmentApplicationObjects A " +
		        "WHERE A.MerchantStoreId IN (:merchantStoreIds) " +
		        "    AND A.Status >= :StatusSubmitted";

		    Map<String, ?> params = CollectionUtils.mapOf(
		        "merchantStoreIds", merchantStoreIds,
		        "StatusSubmitted", ApplicationStatus.SUBMITTED);

		    return DatabaseApi.querySingleInteger(sql, params);
		  }

	public static int getHistoryRejected(List<String> merchantStoreIds) {
		    String sql =
		        "SELECT COUNT(*) " +
		        "FROM InstallmentApplicationObjects A " +
		        "WHERE A.MerchantStoreId IN (:merchantStoreIds) " +
		        "    AND A.Status = :StatusRejected";

		    Map<String, ?> params = CollectionUtils.mapOf(
		        "merchantStoreIds", merchantStoreIds,
		        "StatusRejected", ApplicationStatus.REJECTED);

		    return DatabaseApi.querySingleInteger(sql, params);
		  }

		  public static List<String> getHistoryDelayedAppIdList(List<String> merchantStoreIds) {

		    String sql =
		        "SELECT A.id " +
		        "FROM InstallmentApplicationObjects A " +
		        "WHERE A.MerchantStoreId IN (:merchantStoreIds) " +
		        "    AND A.Status = :Delayed ";

		    Map<String, ?> params = CollectionUtils.mapOf(
		        "Delayed", ApplicationStatus.DELAYED,
		        "merchantStoreIds", merchantStoreIds);

		    return DatabaseApi.queryMultipleResults(sql, params, DatabaseExtractors.STRING_EXTRACTOR);
		  }
		  
		  public static List<String> getHistoryDelayedAppIdListByMerchantStoreId(String merchantStoreId) {

			    String sql =
			        "SELECT A.id " +
			        "FROM InstallmentApplicationObjects A " +
			        "WHERE A.MerchantStoreId = :MerchantStoreId " +
			        "    AND A.Status = :Delayed ";

			    Map<String, ?> params = CollectionUtils.mapOf(
			        "Delayed", ApplicationStatus.DELAYED,
			        "MerchantStoreId", merchantStoreId);

			    return DatabaseApi.queryMultipleResults(sql, params, DatabaseExtractors.STRING_EXTRACTOR);
			  }
		  
		  public static List<String> getHistoryDelayedAppIdListByMerchantUserId(String merchantUserId) {

		    String sql =
		        "SELECT A.id " +
		        "FROM InstallmentApplicationObjects A " +
		        "WHERE A.MerchantUserId = :MerchantUserId " +
		        "    AND A.Status = :Delayed ";

		    Map<String, ?> params = CollectionUtils.mapOf(
		        "Delayed", ApplicationStatus.DELAYED,
		        "MerchantUserId", merchantUserId);

		    return DatabaseApi.queryMultipleResults(sql, params, DatabaseExtractors.STRING_EXTRACTOR);
		  }

		  public static int getTodayTotal(List<String> merchantStoreIds, Date endDate) {
		    String sql =
		        "SELECT COUNT(*) " +
		        "FROM InstallmentApplicationObjects A " +
		        "WHERE A.MerchantStoreId IN (:merchantStoreIds) " +
		        "    AND A.Status >= :StatusSubmitted " +
		        "    AND A.InstallmentStartedOn >= :StartDate " +
		        "    AND A.InstallmentStartedOn < :EndDate";

		    Map<String, ?> params = CollectionUtils.mapOf(
		        "merchantStoreIds", merchantStoreIds,
		        "StatusSubmitted", ApplicationStatus.SUBMITTED,
		        "StartDate", DateTimeUtils.format(trimTimeFields(endDate)),
		        "EndDate", DateTimeUtils.format(endDate));

		    return DatabaseApi.querySingleInteger(sql, params);
		  }

		  public static int getTodayApproved(List<String> merchantStoreIds, Date endDate) {
		    String sql =
		        "SELECT COUNT(*) " +
		        "FROM InstallmentApplicationObjects A " +
		        "WHERE A.MerchantStoreId IN (:merchantStoreIds) " +
		        "    AND A.Status >= :StatusApproved " +
		        "    AND A.Status != :StatusRejected " +
		        "    AND A.InstallmentStartedOn >= :StartDate " +
		        "    AND A.InstallmentStartedOn < :EndDate";

		    Map<String, ?> params = CollectionUtils.<String, Object>newMapBuilder()
		        .add("merchantStoreIds", merchantStoreIds)
		        .add("StatusApproved", ApplicationStatus.APPROVED)
		        .add("StatusRejected", ApplicationStatus.REJECTED)
		        .add("StartDate", DateTimeUtils.format(trimTimeFields(endDate)))
		        .add("EndDate", DateTimeUtils.format(endDate))
		        .build();

		    return DatabaseApi.querySingleInteger(sql, params);
		  }

		  public static int getTodayRejected(List<String> merchantStoreIds, Date endDate) {
		    String sql =
		        "SELECT COUNT(*) " +
		        "FROM InstallmentApplicationObjects A " +
		        "WHERE A.MerchantStoreId IN (:merchantStoreIds) " +
		        "    AND A.Status = :StatusRejected " +
		        "    AND A.InstallmentStartedOn >= :StartDate " +
		        "    AND A.InstallmentStartedOn < :EndDate";

		    Map<String, ?> params = CollectionUtils.mapOf(
		        "merchantStoreIds", merchantStoreIds,
		        "StatusRejected", ApplicationStatus.REJECTED,
		        "StartDate", DateTimeUtils.format(trimTimeFields(endDate)),
		        "EndDate", DateTimeUtils.format(endDate));

		    return DatabaseApi.querySingleInteger(sql, params);
		  }

		  private static Date trimTimeFields(Date date) {
		    Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    c.set(Calendar.HOUR_OF_DAY, 0);
		    c.set(Calendar.MINUTE, 0);
		    c.set(Calendar.SECOND, 0);
		    c.set(Calendar.MILLISECOND, 0);

		    return c.getTime();
		  }

		  public static <T> List<T> getApprovedAppDataForToday(String merchantStoreId, RowMapper<T> extractor){
			  String sql ="SELECT * FROM InstallmentApplicationObjects " +
			  		"where MerchantStoreId=:MerchantStoreId " +
			  		"and Status>=100 " +
			  		"and DateAdded>:StartDate " +
			  		"and DateAdded<:EndDate " +
			  		"order by DateAdded";

				Map<String, ?> params = CollectionUtils.mapOf(
						"MerchantStoreId", merchantStoreId,
						"StartDate", DatabaseApiUtil.getCurrentDate(),
						"EndDate", DatabaseApiUtil.getDate(1));

				return (List<T>) DatabaseApi.queryMultipleResults(
					sql, params, extractor);
		  }
}
