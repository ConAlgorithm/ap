package catfish.notification.messagegeneration;

import grasscarp.account.model.FrozenDetail;
import grasscarp.application.model.Application;
import grasscarp.application.model.PDLApplication;
import grasscarp.application.model.POSApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstalmentApplicationSnapDao;
import catfish.base.business.object.InstalmentApplicationSnapObject;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;
import catfish.notification.Configuration;
import catfish.notification.clientModel.CommonResultModel;
import catfish.notification.object.SmsaResponseData;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class MessageDataRequestApi {

	public final static String D3WEIXINID = "D3WEIXINID";
	public final static String D1UNAME = "D1UNAME";
	public final static String MERCHANTSTORENAME = "MERCHANTSTORENAME";

	private static final String getD1IdByAppIdSql = "SELECT D1Id FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId =:appId";
	private static final String SMSA_USERINFO_URL = "http://%s:%s/public/common/user/getUserInfo";//smsa获取用户信息接口
	private static final String SMSA_OLDUSER_EXTINFO_URL = "http://%s:%s/public/common/olduserextinfo/queryOldUserExtInfo";
	public static final String SMSA_BUSILINE_CODE = "PSL"; //smsa接口业务线代码
	

	// TODO 最终需要call 销售部的service
	/**
	 * 
	 * @param appId
	 * @return d3weixin_id, d1_uname, merchant_store_name
	 */
	public static Map<String, String> getAppSalesInfo(String appId) {
		Map<String, String> returnMap = new HashMap<String, String>();

		InstalmentApplicationSnapDao applicationSnapDao = new InstalmentApplicationSnapDao(appId);
		InstalmentApplicationSnapObject applicationSnapObject = applicationSnapDao.getSingle();
		String d3Id = applicationSnapObject.D3Id;
		String d1Id = applicationSnapObject.D1Id;
		String s1Id = applicationSnapObject.S1Id;
		
		boolean salesAdminServiceSwitchOn = Boolean.FALSE;
		salesAdminServiceSwitchOn = DynamicConfig.readAsBool("salesAdminServiceSwitchOn");// 获取smsa开关
		boolean isposApp = MessageDatabaseApi.isPOSApplication(appId);//app申请是否是pos用户

		// 根据d3Id查询d3的微信OpenId
		if (!StringUtils.isNullOrWhiteSpaces(d3Id)) {
			String WeiXinUserId = null;
			if (salesAdminServiceSwitchOn && isposApp) {
				// 2.调用smsa接口，根据d3人员的id查询d3的微信openId
				Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().build();
				List<String> userIdList = new ArrayList<String>();
				userIdList.add(d3Id);
				params.put("userIdList", userIdList);
				
				Map<String, Object>[] data = getSmsaUserExtendInfo(params);
				if (data == null || data.length==0 || !data[0].containsKey("weixinUserId")) {
					Logger.get().warn("call smsa-service -- no weixinUserId for d3 , d3Id : " + d3Id + " , appId : " + appId);
				} else {
					WeiXinUserId = (String) data[0].get("weixinUserId");
					Logger.get().info("call smsa-service -- weixinUserId for d3 ,weixinUserId : "+ WeiXinUserId +" d3Id : " + d3Id + " , appId : " + appId);
				}
				
			} else {
				String sql = "SELECT WeiXinUserId FROM UserObjects WHERE Id = (SELECT UserId FROM DealerUserObjects WHERE id = :d3id)";
				WeiXinUserId = DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("d3id", d3Id));
			}
			returnMap.put(D3WEIXINID, WeiXinUserId);
		}
		
		// 根据d1Id查询身份证姓名
		if (!StringUtils.isNullOrWhiteSpaces(d1Id)) {
			String d1Uname = "";
			if (salesAdminServiceSwitchOn && isposApp) {//pos申请 && smsa开关打开
				// TODO 调用SMSA接口 根据d1Id查询d人员的信息
				Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().build();
				params.put("userId", d1Id);
				//params.put("isDeletedOn", isDeletedOn);//TODO 确定到底要不要delete
				List<String> busilineCodeList = new ArrayList<String>();
				busilineCodeList.add(SMSA_BUSILINE_CODE);
				params.put("busilineCodeList", busilineCodeList); //业务条线必传字段
				
				Map<String, Object>[] data = getSmsaUserInfo(params);
				if (data == null || data.length==0 || !data[0].containsKey("idname")) {
					Logger.get().warn("call smsa-service -- no idname for d1 , d1Id : " + d1Id + " , appId : " + appId);
				} else {
					d1Uname = (String) data[0].get("idname");
					Logger.get().info("call smsa-service -- d1Uname for d1 , d1Id : " + d1Id + "  d1Uname : " + d1Uname + " , appId : " + appId);
				}
			} else {
				String sql = "SELECT idName FROM DealerUserObjects WHERE Id = :d1id";
				d1Uname = DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("d1id", d1Id));
			}
			returnMap.put(D1UNAME, d1Uname);

		}
		// 根据appid查询做单的s1所在pos门店名称
		if (!StringUtils.isNullOrWhiteSpaces(s1Id)) {
			String sql = "SELECT name FROM MerchantStoreObjects WHERE Id = (SELECT MerchantStoreId FROM InstallmentApplicationObjects WHERE Id = :AppId)";
			returnMap.put(MERCHANTSTORENAME, DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId)));
		}

		return returnMap;
	}

	/**
	 * 根据appid获取做单d1人员的手机号
	 * 
	 * @param appId
	 * @return
	 */
	public static String getD1MobileByAppId(String appId) {
		Logger.get().info("call getD1MobileByAppId with appId : " + appId);
		String mobile = null;

		// 1.先根据appid查询快照表得出D1的id
		String d1Id = DatabaseApi.querySingleString(getD1IdByAppIdSql, CollectionUtils.mapOf("appId", appId));

		if (StringUtils.isNullOrWhiteSpaces(d1Id)) {
			Logger.get().warn("no d1Id from InstalmentApplicationSnapObjects , for appId : " + appId);
		} else {
			// 2.调用smsa接口，根据d人员的id查询d的手机号 :
			// 根据merchantUserId查询到最近一次修改的ContactId，根据ContactId到ContactObjects表查询手机号码
			Logger.get().info("-----begin smsa-service to get mobile for d1 , d1Id : " + d1Id);
			Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().build();
			params.put("userId", d1Id);
			//params.put("isDeletedOn", isDeletedOn);//TODO 确定到底要不要delete
			List<String> busilineCodeList = new ArrayList<String>();
			busilineCodeList.add(SMSA_BUSILINE_CODE);
			params.put("busilineCodeList", busilineCodeList); //业务条线必传字段
			
			Map<String, Object>[] data = getSmsaUserInfo(params);
			if (data == null || data.length==0 || !data[0].containsKey("mobile")) {
				Logger.get().warn("call smsa-service -- no mobile for d1 , d1Id : " + d1Id + " , appId : " + appId);
			} else {
				mobile = (String) data[0].get("mobile");
				Logger.get().info("call smsa-service -- mobile for d1 , d1Id : " + d1Id + "  mobile : " + mobile + " , appId : " + appId);
			}
		}
		return mobile;
	}

	/**
	 * 根据params参数查询smsa接口，userinfo
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object>[] getSmsaUserInfo(Map<String, Object> params) {
		SmsaResponseData<Object> responseData = null;
		Map<String, Object>[] userInfoDatas = null;
		
		String SMSA_URL = String.format(SMSA_USERINFO_URL, StartupConfig.get("sales.adminService.host"),StartupConfig.get("sales.adminService.port"));

		Logger.get().info("-----begin call smsa-service to getUserInfo -----  \n url : " + SMSA_URL +" \n params : " + new Gson().toJson(params));
		//responseData = HttpClientApi.postJson(SMSA_URL, params, new TypeToken<SmsaResponseData<Object>>() {}.getType());
		String postResult = HttpClientApi.postJson(SMSA_URL, params, "string");
		if (StringUtils.isNullOrWhiteSpaces(postResult)) {
			Logger.get().warn("call smsa-service to getUserInfo error  ! postResult is null  ");
			return null;
		}
		else {
			responseData = (SmsaResponseData<Object>)new Gson().fromJson(postResult, SmsaResponseData.class);
			if ( responseData !=null && responseData.getCode() ==0) {//操作成功
				userInfoDatas = responseData.getValue();
				if (userInfoDatas != null) {
					Logger.get().info("call smsa-service to getUserInfo success ! response value :  " + (new Gson()).toJson(userInfoDatas));
				} 
			}else {
				Logger.get().warn("call smsa-service to getUserInfo error  ! message :  " + responseData.getMessage());
			}
		}
		return userInfoDatas;
	}

	/**
	 * 根据appid查询D1的微信openid
	 * 
	 * @param appId
	 * @return
	 */
	public static String getD1WeChatOpenIdByAppId(String appId) {
		Logger.get().info("call getD1WeChatOpenIdByAppId with appId : " + appId);
		String WeiXinUserId = null;
		// 1.先根据appid查询快照表得出D1的id
		String d1Id = DatabaseApi.querySingleString(getD1IdByAppIdSql, CollectionUtils.mapOf("appId", appId));

		if (StringUtils.isNullOrWhiteSpaces(d1Id)) {
			Logger.get().warn("no d1Id from InstalmentApplicationSnapObjects , for appId : " + appId);
		} else {
			// 2.调用smsa接口，根据d人员的id查询d的微信openId
			Logger.get().info("-----begin smsa-service to get weixinUserId for d1 , d1Id : " + d1Id);
			Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().build();
			List<String> userIdList = new ArrayList<String>();
			userIdList.add(d1Id);
			params.put("userIdList", userIdList);
			
			Map<String, Object>[] data = getSmsaUserExtendInfo(params);
			if (data == null || data.length==0 || !data[0].containsKey("weixinUserId")) {
				Logger.get().warn("call smsa-service -- no weixinUserId for d1 , d1Id : " + d1Id + " , appId : " + appId);
			} else {
				WeiXinUserId = (String) data[0].get("weixinUserId");
				Logger.get().info("call smsa-service -- weixinUserId for d1 , WeiXinUserId : " + WeiXinUserId +" d1Id : " + d1Id + " , appId : " + appId);
			}
		}
		return WeiXinUserId;
	}

	/**
	 * 根据appid查询bd2的微信openId
	 * 
	 * @param appId
	 * @return
	 */
	public static String getBD2WeChatOpenIdByAppId(String appId) {
		Logger.get().info("call getBD2WeChatOpenIdByAppId with appId : " + appId);
		String WeiXinUserId = null;

		// 1.先根据appid查询快照表得出BD2的id
		String sql = "SELECT BD2Id FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId =:appId";
		String bd2Id = DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("appId", appId));

		if (StringUtils.isNullOrWhiteSpaces(bd2Id)) {
			Logger.get().warn("no bd2Id from InstalmentApplicationSnapObjects , for appId : " + appId);
		} else {
			Logger.get().info("-----begin smsa-service to get weixinUserId for BD2 , bd2Id : " + bd2Id);
			// 2.调用smsa接口，根据bd2人员的id查询bd2的微信openId
			Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder().build();
			List<String> userIdList = new ArrayList<String>();
			userIdList.add(bd2Id);
			params.put("userIdList", userIdList);
			
			Map<String, Object>[] data = getSmsaUserExtendInfo(params);
			if (data == null || data.length==0 || !data[0].containsKey("weixinUserId")) {
				Logger.get().warn("call smsa-service -- no weixinUserId for BD2 , BD2Id : " + bd2Id + " , appId : " + appId);
			} else {
				WeiXinUserId = (String) data[0].get("weixinUserId");
				Logger.get().info("call smsa-service -- weixinUserId for BD2 , WeiXinUserId : " + WeiXinUserId +" BD2Id : " + bd2Id + " , appId : " + appId);
			}
		}
		return WeiXinUserId;
	}

	/**
	 * 调用SMSA接口,根据bd或d的id查询对应的微信openId
	 * 
	 * @param did
	 * @return
	 */
	public static Map<String, Object>[] getSmsaUserExtendInfo(Map<String, Object> params) {
		SmsaResponseData<Object> responseData = null;
		Map<String, Object>[] userExtInfoDatas = null;
		
		String SMSA_URL =  String.format(SMSA_OLDUSER_EXTINFO_URL, StartupConfig.get("sales.adminService.host"),StartupConfig.get("sales.adminService.port"));
		Logger.get().info("-----begin call smsa-service to getUserExtendInfo -----  \n url : " + SMSA_URL +" \n params : " + new Gson().toJson(params));
		//responseData = HttpClientApi.postJson(SMSA_URL, params, new TypeToken<SmsaResponseData<Object>>() {}.getType());
		String postResult = HttpClientApi.postJson(SMSA_URL, params, "string");
		if (StringUtils.isNullOrWhiteSpaces(postResult)) {
			Logger.get().warn("call smsa-service to getUserExtendInfo error  ! postResult is null  ");
			return null;
		}
		else {
			responseData = (SmsaResponseData<Object>)new Gson().fromJson(postResult, SmsaResponseData.class);
			if ( responseData !=null && responseData.getCode() ==0) {//操作成功
				userExtInfoDatas = responseData.getValue();
				if (userExtInfoDatas != null) {
					Logger.get().info("call smsa-service to getUserExtendInfo success , response value :  " + (new Gson()).toJson(userExtInfoDatas));
				} 
			}else {
				Logger.get().warn("call smsa-service to getUserExtendInfo error  ! message :  " + responseData.getMessage());
			}
		}
		return userExtInfoDatas;
	}

	public static Application getApp(String appId, Integer channel) {

		Logger.get().info("Call getApp with app id: " + appId);

		String requestString = "http://%s:%s/application/%s";

		requestString = String.format(requestString, StartupConfig.get("grasscarp.restful.host"),
				StartupConfig.get("grasscarp.restful.port"), appId);

		try {
			if (channel == InstalmentChannel.PayDayLoanApp.getValue()) {
				return HttpClientApi.getGson(requestString, PDLApplication.class);
			} else {
				return HttpClientApi.getGson(requestString, POSApplication.class);
			}

		} catch (RuntimeException e) {

			Logger.get().warn("requst url:" + requestString + " with error: " + e.getMessage());
			return null;
		}
	}

	public static FrozenDetail getAvailableFrozenDetail(String userId) {
		Logger.get().info("Call get frozen with userId: " + userId);
		String requestString = "http://%s:%s/account/%s/frozen";
		requestString = String.format(requestString, StartupConfig.get("grasscarp.restful.host"),
				StartupConfig.get("grasscarp.restful.port"), userId);

		try {
			// String data = HttpClientApi.get(requestString);
			return HttpClientApi.getGson(requestString, FrozenDetail.class);

		} catch (RuntimeException e) {

			Logger.get().warn("requst url:" + requestString + " with error: " + e.getMessage());
			return null;
		}
	}

	public static String getProdNameById(final String productId) {
		return HttpClientApi.CallService(3, new ServiceHandler<String, String>() {
			@Override
			public String createUrl() {
				return String.format("%s/product/%s/name", Configuration.getSaturnProductUrlPrefix(), productId);
			}

			@Override
			public String OnRequest(String url) {
				Logger.get().info("request url: " + url);
				return HttpClientApi.get(url);
			}

			@SuppressWarnings("unchecked")
			@Override
			public String OnSuccess(String result) {
				Map<String, Object> ret = (Map<String, Object>) new Gson().fromJson(result, CommonResultModel.class)
						.getData();
				return (String) ret.get("name");
			}

			@Override
			public String OnError(String result) {
				return null;
			}
		});
	}
}