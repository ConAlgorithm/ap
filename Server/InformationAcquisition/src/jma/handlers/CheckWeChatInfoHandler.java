package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DerivativeVariableNames;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.database.DatabaseApi;


/**
 * 微信头像    昵称    还款期数
 * @author zhangll
 *
 */
public class CheckWeChatInfoHandler extends NonBlockingJobHandler {

	@Override
	public void execute(String appId) throws RetryRequiredException {
		Map<String,Object> data = this.getData(appId);
		
		this.saveData(data);
		Logger.get().info("success execute CheckWeChatInfoHandler.appId="+appId);
	}
	protected void saveData(Map<String,Object> map){
		AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
		if(map.get("NickName") != null ){
			builder.add(DerivativeVariableNames.NICK_NAME, map.get("NickName").toString());
		}
		
		Object headImage = map.get("HeadImageUrl");
		String wechatHead= this.getWechatHead(headImage, map.get("NickName"));
		builder.add(DerivativeVariableNames.WECHAT_HEAD, wechatHead);
		
		if(map.get("Repayments") != null ){
			builder.add(DerivativeVariableNames.REPAYMENTS, Integer.parseInt(map.get("Repayments").toString()));
		}
		AppDerivativeVariableManager.addVariables(builder.build());
	}
	
	private String getWechatHead(Object wechatHead, Object nickName){
		String result = "有" ;
		if(wechatHead== null || wechatHead.toString().length()<=0) {
			result = "无";
		}else if(nickName !=null && nickName.toString().contains("买单侠APP")){
			result = "无数据";
		}
		return result;
	}
	
	protected Map<String,Object> getData(String appId){
		String sql = "select w.NickName, w.HeadImageUrl, i.Repayments from WeiXinUserObjects w " +
						" left join UserObjects u on w.id=u.WeiXinUserId" +
						" left join InstallmentApplicationObjects i on u.Id  = i.UserId"	+
						" where i.id  = :appId";
		
		Map<String, ?> params = CollectionUtils.mapOf(
		        "appId", appId);
		
		RowMapper<Map<String,Object>> mapper = new RowMapper<Map<String,Object>>() {
		      @Override
		      public Map<String,Object> mapRow(ResultSet row, int index) throws SQLException {
		    	  Map<String, Object> params = CollectionUtils.mapOf(
		  		        "NickName", row.getString(1), "HeadImageUrl", row.getString(2), "Repayments", row.getInt(3));
		        return params;
		      }
		    };
		
		return DatabaseApi.querySingleResult(sql, params, mapper);
	}
}
