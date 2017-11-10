/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.database.DatabaseApi;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;

/**
 * 〈检查工厂区域信息〉
 *
 * @author hwei
 * @version CheckFactoryAreaHandler.java, V1.0 2017年4月24日 下午5:50:53
 */
public class CheckFactoryAreaHandler extends NonBlockingJobHandler{
    private static final RowMapper<List<String>> EXTRACTOR = new RowMapper<List<String>>() {
        @Override
        public List<String> mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
          return Arrays.asList(
              resultSet.getString("ParentCode"),
              resultSet.getString("Code"),
              resultSet.getString("Name"));
        }
      };

    @Override
    public void execute(String appId) throws RetryRequiredException {
        Logger.get().info("CheckFactoryAreaHandler execute appId:" + appId);
        try {
            AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
            List<String> districtInfo = queryDistrictInfo(appId);
            if(districtInfo!=null&&districtInfo.size()>=3){
                builder.add(AppDerivativeVariableNames.FACTORY_DISTRICT_CODE, districtInfo.get(1));
                builder.add(AppDerivativeVariableNames.FACTORY_DISTRICT, districtInfo.get(2));
                List<String> cityInfo = queryAreaInfo(districtInfo.get(0));
                if(cityInfo!=null&&cityInfo.size()>=3){
                    builder.add(AppDerivativeVariableNames.FACTORY_CITY_CODE, cityInfo.get(1));
                    builder.add(AppDerivativeVariableNames.FACTORY_CITY, cityInfo.get(2));
                    List<String> provinceInfo = queryAreaInfo(cityInfo.get(0));
                    if(provinceInfo!=null&&provinceInfo.size()>=3){
                        builder.add(AppDerivativeVariableNames.FACTORY_PROVINCE_CODE, provinceInfo.get(1));
                        builder.add(AppDerivativeVariableNames.FACTORY_PROVINCE, provinceInfo.get(2));
                    }
                }
            }
            Logger.get().info("CheckFactoryAreaHandler info is "+ new Gson().toJson(builder));
            AppDerivativeVariableManager.addVariables(builder.build());            
        } catch (Exception e) {
            Logger.get().error(String.format("exception occurred!appId=%s", appId), e);
            throw new RetryRequiredException();
        }
    }
    
    private static List<String> queryDistrictInfo(String appId) {
        String sql =
            "SELECT CNAreaObjects.ParentCode, CNAreaObjects.Code, CNAreaObjects.Name " +
            "FROM FApplicationRelationObjects, FactoryObjects, CNAreaObjects " +
            "WHERE FApplicationRelationObjects.FactoryId = FactoryObjects.Id " +
            "    AND FactoryObjects.CNAreaId = CNAreaObjects.Id " +
            "    AND FApplicationRelationObjects.AppId = :AppId";

        return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), EXTRACTOR);
      }

      public static List<String> queryAreaInfo(String code) {
        return DatabaseApi.querySingleResult(
            "SELECT ParentCode, Code, Name FROM CNAreaObjects WHERE Code = :Code",
            CollectionUtils.mapOf("Code", code), EXTRACTOR);
      }

}
