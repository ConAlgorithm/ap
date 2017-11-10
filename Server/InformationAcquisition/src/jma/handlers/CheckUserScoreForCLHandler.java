package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import catfish.cowfish.application.model.ApplicationModel;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;
import jma.models.UserInfoModelLTV;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

public class CheckUserScoreForCLHandler extends NonBlockingJobHandler{

    @Override
    public void execute(String appId) throws RetryRequiredException {
        Logger.get().info("CheckUserScoreForCLHandler execute appId:" + appId);
        AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
        ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
        if(clApp!=null){
            builder.isNotNullAdd(DerivativeVariableNames.LTV_PRINCIPAL, clApp.principal);
            builder.isNotNullAdd(DerivativeVariableNames.LTV_REPAYMENTS, clApp.repayments);
            User user = UserResourceFactory.getUser(clApp.userId);
            Logger.get().info("getUser form grassCarp result is "+ new Gson().toJson(user));
            if(user!=null){
                String IdCardNum=user.getIdNumber();
                if(IdCardNum!=null){
                    String gender=Integer.parseInt(String.valueOf(IdCardNum.charAt(16))) % 2 == 0 ? "F" : "M";
                    builder.isNotNullAdd(DerivativeVariableNames.LTV_GENDER, gender);
                    String birthday = IdCardNum.substring(6, 14); 
                    try {
                        Date  date = new SimpleDateFormat("yyyyMMdd").parse(birthday);
                        builder.isNotNullAdd(DerivativeVariableNames.LTV_AGE, getAge(date));
                    } catch (Exception e) {
                        Logger.get().error("parse date has error ",e);
                    }
                }
            }
            Account userAccount = UserResourceFactory.getUserAccount(clApp.userId);
            if(userAccount!=null){
                String mobile = userAccount.getMobile();
                List<String> result = getMobileArea(mobile);
                if(result!=null){
                    builder.isNotNullAdd(DerivativeVariableNames.LTV_PROVIDER, result.get(2)) ; 
                }
            }
        }
        UserInfoModelLTV userInfo = getApplicationUserInfo(appId);
        if(userInfo!=null){
            builder.isNotNullAdd(DerivativeVariableNames.LTV_INCOME, userInfo.getIncome());
        }
        if (!builder.build().isEmpty()){
            AppDerivativeVariableManager.addVariables(builder.build());                
        }        
    }
    
    protected UserInfoModelLTV getApplicationUserInfo(String appId){
        String url = StartupConfig.get("catfish.cowfish.host");
        try {
            UserInfoModelLTV model = HttpClientApi.getGson(
                String.format("%s/application/%s/userInfo", url, appId), UserInfoModelLTV.class);   
            return model;
        } catch (Exception e) {
            Logger.get().error("getApplicationUserInfo has error , appId is "+ appId);
        }
        return null;     
    }

    /**
     * 计算年龄
     * @param birth
     * @return
     */
    private int getAge(Date birth) {
        int age = -1;
        if (birth != null) {
            Calendar c = Calendar.getInstance();
            Calendar cBirth = Calendar.getInstance();
            cBirth.setTime(birth);
            if (c.after(cBirth)) {
                int nowYear = c.get(Calendar.YEAR);
                int nowMonth = c.get(Calendar.MONTH);
                int nowDay = c.get(Calendar.DAY_OF_MONTH);

                int birthYear = cBirth.get(Calendar.YEAR);
                int birthMonth = cBirth.get(Calendar.MONTH);
                int birthDay = cBirth.get(Calendar.DAY_OF_MONTH);

                int diffYear = nowYear - birthYear;
                int diffMonth = nowMonth - birthMonth;
                int diffDay = nowDay - birthDay;

                if (diffMonth > 0) {
                    age = diffYear;
                } else if (diffMonth == 0) {
                    if (diffDay >= 0) {
                        age = diffYear;
                    } else {
                        age = diffYear - 1;
                    }
                } else {
                    age = diffYear - 1;
                }

            }
        }
        return age;
    }
    
    private List<String> getMobileArea(String number) {
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

        return DatabaseApi.querySingleResultOrDefault(
            sql,
            CollectionUtils.mapOf("Prefix", number.substring(0, 7)),
            extractor,
            Arrays.asList("", "", ""));
      }
}
