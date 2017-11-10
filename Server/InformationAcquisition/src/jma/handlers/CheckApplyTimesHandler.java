package jma.handlers;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.database.DatabaseApi;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;

/**
 * 申请次数
 * @author zhangll
 *
 */
public class CheckApplyTimesHandler extends NonBlockingJobHandler {

	@Override
	public void execute(String appId) throws RetryRequiredException {
		int applyTimes = this.getApplyTimesByAppId(appId);
		
		// 写数据至mongo
		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
		builder.add(DerivativeVariableNames.APPLY_NUMBER, applyTimes);
		AppDerivativeVariableManager.addVariables(builder.build());
		Logger.get().info("success execute CheckApplyTimesHandler.appId="+appId);
	}
	
	private int getApplyTimesByAppId(String appId){
		
		String sql = "select count(id) from InstallmentApplicationObjects ins where ins.UserId = ( "+
				" select u.id from InstallmentApplicationObjects i left join UserObjects u on i.UserId = u.Id "+
				" where i.Id = :appId)";
		Map<String, ?> params = CollectionUtils.mapOf(
		        "appId", appId);
		return DatabaseApi.querySingleInteger(sql, params);
	}
}
