package jma.dataservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.database.DatabaseApi;

public class SalesDataService {
	
	public static String getMerchantCompanyId(String merchantStoreId){
		String sql = "SELECT MerchantCompanyId FROM MerchantStoreObjects WHERE Id=:merchantStoreId";
		Map<String, ?> params = CollectionUtils.mapOf("merchantStoreId", merchantStoreId);

		return DatabaseApi.querySingleString(sql, params);
	}
	
	public static List<String> getMerchantStoreIds(String merchantCompanyId){
		String sql = "SELECT Id FROM MerchantStoreObjects WHERE MerchantCompanyId=:merchantCompanyId";
		Map<String, ?> params = CollectionUtils.mapOf("merchantCompanyId", merchantCompanyId);

		RowMapper<String> extractor = new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet resultSet, int rowIndex)
					throws SQLException {
				return resultSet.getString("Id");
			}
		};
		
		return DatabaseApi.queryMultipleResults(sql, params, extractor);
	}
}
