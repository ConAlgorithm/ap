/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.thirdpartyservices.jxl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.business.dao.ContactDao2;
import catfish.base.business.dao.EndUserExtentionDao2;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;
import jma.AppDerivativeVariablesBuilder;
import jma.Configuration;
import jma.JobStatus;
import jma.models.DataSourceResponseBase;
import jma.models.jxl.JXLV4ResponseModel;
import jma.util.DSPApiUtils;
import thirdparty.config.JXLConfiguration;


/**
 * @description CL申请抓取聚信立信息用作提额
 * 
 * @author wangkun
 * @time 2017年2月23日
 */
public class InfoCrawlerThreadForCL implements Runnable {

	// jobName
	private static final String JOB_NAME = "JxlCredit";
	// 线程初始等待时间,首次查询时间为WAITING_SECONDS+RETRY_INTERVAL_SECONDS,这么写是为了与POS的保持统一，以便使用同一配置
	private static final int WAITING_SECONDS = JXLConfiguration.getJxlThreadWaitingSecondsForCL();
	// 重试时间间隔
	private static final int RETRY_INTERVAL_SECONDS = JXLConfiguration.getJxlRetryIntervalSecondsForCL();
	// 数据源指定的jxl接口版本号
	private static final String JXL_VERSION = JXLConfiguration.getJxlVersion();;
	// 重试次数
	private int retryCount = JXLConfiguration.getJxlMaxRetries();
	
	//jxl credit type: 0,active; 1, negative
	private static final String JXL_CREDIT_TYPE = "X_JXL_Credit_Type";
	
	//jxl credit product tag: CL means Cashloan product
	private static final String JXL_CREDIT_PRODUCT_TAG = "X_JXL_Credit_Product_Tag";

	private QueueMessager requestQueueMessager;
	private String appId;

	public InfoCrawlerThreadForCL(QueueMessager requestQueueMessager) {
		this.requestQueueMessager = requestQueueMessager;
		this.appId = requestQueueMessager.getAppId();
	}

    @Override
    public void run() {
		QueueMessager messager = new QueueMessager(appId, JOB_NAME, JobStatus.SUCCEESS);
		messager.setUserId(requestQueueMessager.getUserId());
		messager.setMessageType(requestQueueMessager.getMessageType());

        try {

			/* 线程等待JXL准备数据，增加查询成功率 */
			Logger.get().info(
					String.format("Get JXL info for CL appId = %s after %s seconds! request messager= %s", appId,
							WAITING_SECONDS + RETRY_INTERVAL_SECONDS,
							new Gson().toJson(requestQueueMessager)));
			ThreadUtils.sleepInSeconds(WAITING_SECONDS + RETRY_INTERVAL_SECONDS);


			/* 获取dsp接口请求信息，准备请求数据，不指定版本就使用默认的V4.0版本api */
			Map<String, Object> requestParam = getUserInfo(requestQueueMessager.getUserId());

			if (requestParam == null || requestParam.isEmpty()) {
				messager.setJobStatus(JobStatus.ERROR);
				Logger.get().warn(String.format("Get JXL info for CL appId = %s, request params is empty", appId));
				return;
			}
			requestParam.put("interfaceVersion", JXL_VERSION);
			Logger.get().info(
					String.format("Get JXL info for CL appId = %s, request params = %s ,retry times = %s ", appId, new Gson().toJson(requestParam),
							retryCount));


			/* 请求接口 */
            JXLV4ResponseModel model = null;
			do {
				model = getJXLData(requestParam);
				if (model == null) {
					retryCount--;
					Logger.get().warn(String.format("Get JXL info for CL appId = %s fail, left retry times = %s ", appId, retryCount));
				} else {
					break;
				}
				ThreadUtils.sleepInSeconds(RETRY_INTERVAL_SECONDS);
			} while (retryCount > 0);

            if (model == null) {
				messager.setJobStatus(JobStatus.ERROR);
				AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPROTDATA_SUCCESS_FLAG, false)
						.isNotNullAdd(JXL_CREDIT_TYPE, requestQueueMessager.getMessageType())
						.isNotNullAdd(JXL_CREDIT_PRODUCT_TAG, "CL")
						.build());
            } else {
				Logger.get().debug(String.format("Get JXL info for CL appId = %s fail, result = %s ", appId, new Gson().toJson(model)));

                AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPROTDATA_SUCCESS_FLAG, true)
						.isNotNullAdd(JXL_CREDIT_TYPE, requestQueueMessager.getMessageType())
						.isNotNullAdd(JXL_CREDIT_PRODUCT_TAG, "CL")
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_REGION_COUNT, model.getX_JXL_ReportData_ContactRegion_Count())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_COUNT, model.getX_JXL_ReportData_Contact_Count())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_LENGTH, model.getX_JXL_ReportData_CallOut_Length())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_COUNT, model.getX_JXL_ReportData_CallOut_Count())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_LENGTH, model.getX_JXL_ReportData_CallIn_Length())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_COUNT, model.getX_JXL_ReportData_CallIn_Count())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_DATA_EXIST_MONTH_COUNT,
								model.getX_JXL_ReportData_DataExistMonth_Count())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_NUMBER_COUNT, model.getX_JXL_ReportData_ContactNumber_Count())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_IS_REAL_AUTHENTICATED,
								model.getX_JXL_ReportData_IsRealAuthenticated())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_IS_PROVIDER_INFO_MATCH,
								model.getX_JXL_ReportData_IsProviderInfoMatch())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_IS_ALWAYS_POWEROFF, model.getX_JXL_ReportData_IsAlwaysPowerOff())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALLlOANPHONE, model.getX_JXL_ReportData_CallLoanPhone())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALLFINANCEPHONE, model.getX_JXL_ReportData_CallFinancePhone())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALLJIEXINPHONE, model.getX_JXL_ReportData_CallJieXinPhone())
						.isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_REG_CONTACTPHONE_IN_JXL_NUM,
								model.getX_JXL_ReportData_regContactPhoneInJXLNum())
						.build());

            }
        } catch (Exception e) {
			messager.setJobStatus(JobStatus.ERROR);
			Logger.get().error(String.format("Get JXL info for CL appId = %s exception exception!", appId), e);
        } finally {
			PersistenceQueueApi.writeMessager(requestQueueMessager.getCallbackQueue(), messager, MessageSource.InformationAcquisition);
        }
    }

	/**
	 * 获取用户信息：姓名，身份证，手机号 , dsp api字段必填
	 * 
	 * @param userId
	 * @return
	 */
	protected Map<String, Object> getUserInfo(String userId) {

		Map<String, Object> param = null;

		try {

			EndUserExtensionObject userObj = new EndUserExtentionDao2(userId).getSingle();
			ContactObject contactObj = new ContactDao2(userId).getSingle();

			Logger.get().debug("userObj = " + new Gson().toJson(userObj));
			Logger.get().debug("contactObj = " + new Gson().toJson(contactObj));

			if (userObj == null || contactObj == null) {
				throw new RuntimeException("User info is not exists");
			}

			if (StringUtils.isBlank(userObj.IdName) || StringUtils.isBlank(userObj.IdNumber) || StringUtils.isBlank(contactObj.Content)) {
				throw new RuntimeException("User info is not integral");
			}

			param = new HashMap<String, Object>();
			param.put("name", userObj.IdName);
			param.put("idNo", userObj.IdNumber);
			param.put("mobile", contactObj.Content);

		} catch (Exception e) {
			Logger.get().error("Query user info exception !", e);
		}

        return param;
    }

    /**
     * <p>〈调用聚信立dsp数据源获取数据〉</p>
     * 
     * @return
     */
    protected JXLV4ResponseModel getJXLData(Map<String, Object> map) {
        JXLV4ResponseModel model = null;
        String url = Configuration.getJxlNewUrl();
        try {
            //调用dsp
            DataSourceResponseBase<JXLV4ResponseModel> res = DSPApiUtils.invokeDspApi(appId, url, map,
                new TypeToken<DataSourceResponseBase<JXLV4ResponseModel>>() {
                }.getType());
            RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.JXL_REPORTDATA_RAW_DATA, new Gson().toJson(res)));
            if (res != null && res.getCode() != 200) {
                Logger.get().warn(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                return null;
            }
            List<JXLV4ResponseModel> data = res.getData();
            if (data == null || data.size() == 0) {
                Logger.get().warn("response data is null.");
                return null;
            }
            model = data.get(0);
        } catch (Exception e) {
            Logger.get().warn(String.format("CL CrawlingNew getJXLData of AppId: %s error!", appId), e);
        }
        return model;
    }

    
}
