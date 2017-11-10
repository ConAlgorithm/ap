package jma.handlers;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.database.DatabaseApi;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;
import jma.util.GrasscarpClient;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by feit on 2017/7/7.
 * 临时方案,根据公司名称列表筛选有问题的申请并直接拒绝之.
 */
public class CheckBlackCompanyAndRejectTempHandler extends JobHandler {

    private static final String FileName = "blackCompany.properties";
    private static final String getCompanyNameSql = "SELECT CompanyName " +
            "FROM JobInfoObjects " +
            "WHERE ApplicationId = :AppId " +
            "ORDER BY LastModified DESC";
    //private static final String rejectApplicationSql = "UPDATE InstallmentApplicationObjects SET Status = :Status, RejectedReason='P016' WHERE id = :AppId";
    @Override
    public void execute(String appId) throws RetryRequiredException {
        if(GrasscarpClient.isCMCC(appId)){
            String rawCompanyName = getCompanyName(appId);
            if(rawCompanyName != null){
                boolean isMatch = matchBlackList(readBlackCompany(), rawCompanyName.replaceAll(" ", ""));
                //if(isMatch) rejectApplication(appId);
                // 写数据至mongo
        		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
        		builder.isNotNullAdd(DerivativeVariableNames.IS_BLACK_COMPANY_NAME, isMatch);
        		AppDerivativeVariableManager.addVariables(builder.build());
        		Logger.get().info("success execute CheckBlackCompanyAndRejectTempHandler,appId=" + appId);
            }
        }
    }

    private String getCompanyName(String appId) {
        return DatabaseApi.querySingleString(getCompanyNameSql, CollectionUtils.mapOf("AppId", appId));
    }

//    private void rejectApplication(String appId){
//        DatabaseApi.updateTable(rejectApplicationSql, CollectionUtils.mapOf("Status", ApplicationStatus.Rejected.getValue(), "AppId", appId));
//    }

    private List<String> readBlackCompany() {
        try(Stream<String> stream = Files.lines(Paths.get(FileName))){
            return stream.map(item -> item.toString().replaceAll(" ", "")).collect(Collectors.toList());
        }catch (Exception e){
            Logger.get().warn("Can not read " + FileName, e);
            return Collections.emptyList();
        }
    }

    private boolean matchBlackList(List<String> blackList, String src){
        return blackList.stream().anyMatch(src::contains);
    }
}
