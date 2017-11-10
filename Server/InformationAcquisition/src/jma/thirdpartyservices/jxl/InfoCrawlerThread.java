package jma.thirdpartyservices.jxl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jma.Configuration;
import jma.JobStatus;
import thirdparty.config.JXLConfiguration;
import thirdparty.jxl.ReportStatus;
import thirdparty.jxl.request.GetReportStatusRequest;
import thirdparty.jxl.request.ReportDataRequest;
import thirdparty.jxl.response.GetReportStatusResponse;
import thirdparty.jxl.response.ReportDataResponse;
import thirdparty.jxl.response.reportdata.BehaviorItem;
import thirdparty.jxl.response.reportdata.BindDataSourceItem;
import thirdparty.jxl.response.reportdata.CellPhoneBehaviorItem;
import thirdparty.jxl.response.reportdata.CheckPointItem;
import thirdparty.jxl.response.reportdata.ContactListItem;
import thirdparty.jxl.response.reportdata.ContactRegionItem;
import thirdparty.jxl.response.reportdata.Person;
import thirdparty.util.JXLUtil;
import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.business.common.jxl.CheckPointResult;
import catfish.base.business.common.jxl.RequestStatus;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.httpclient.DefaultClient;
import catfish.base.httpclient.DefaultRequestBuilder;
import catfish.base.httpclient.object.BaseObject;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;

public class InfoCrawlerThread implements Runnable{

	private static final String JobFinishedName = "JXLInfoCrawlFinished";

	private static final int sleepSeconds = 3*60;

	private Date startTime = new Date();

	private String appId;
	private EndUserExtensionObject userObj;
	private ContactObject contactObj;

	public InfoCrawlerThread(String appId)
	{
		this.appId = appId;
	}

	private GetReportStatusRequest createGetReportStatusRequest()
	{
		GetReportStatusRequest request = new GetReportStatusRequest();
		request.setIdCardNumber(userObj.IdNumber);
		request.setName(userObj.IdName);
		request.setPhone(contactObj.Content);
		return request;
	}

	private ReportDataRequest createReportDataRequest()
	{
		ReportDataRequest request = new ReportDataRequest();
		request.setIdCardNumber(userObj.IdNumber);
		request.setName(userObj.IdName);
		request.setPhone(contactObj.Content);
		return request;
	}

   	private void initialDataForTest(String userName, String idCard, String mobile)
	{
		userObj = new EndUserExtensionObject();
		contactObj = new ContactObject();
		userObj.IdNumber = idCard;
		userObj.IdName = userName;
		contactObj.Content = mobile;
	}

	@Override
	public void run() {
		QueueMessager messager = new QueueMessager(appId, JobFinishedName, JobStatus.SUCCEESS);
		try{
	        userObj = new EndUserExtentionDao(appId).getSingle();
			contactObj = new ContactDao(appId).getSingle();

			//initialDataForTest("覃龙", "45222619950612271X", "15778202860");

			RequestStatus statusResult = getReportStatus(createGetReportStatusRequest());
			AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_GETREPORTSTATUS_RESULT, statusResult.getValue()));

			if(statusResult != RequestStatus.Success) return;

			//休息3分钟开始收集report
			ThreadUtils.sleepInSeconds(sleepSeconds);

			ReportDataResponse reportData = getReportData(createReportDataRequest());

			if(reportData == null) return;

			saveReportData(reportData);

		}catch(Exception e)
		{
			messager.setJobStatus(JobStatus.ERROR);
			Logger.get().error(String.format("Crawling 聚信力 Info of AppId: %s error!", appId), e);
		}finally{
			PersistenceQueueApi.writeMessager(Configuration.getJobResponseQueue(), messager, MessageSource.InformationAcquisition);
		}
	}

	public ReportDataResponse getReportData(ReportDataRequest request)
	{
		DefaultClient client = new DefaultClient(JXLConfiguration.getJxlReportDataUrl(), new DefaultRequestBuilder(request));
		int retryCount = JXLConfiguration.getJxlMaxRetries();
		while(retryCount > 0)
		{
			try {
				String result = client.httpGet();
				RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.JXL_REPORTDATA_RAW_DATA, result));
				ReportDataResponse response = BaseObject.fromString(result, ReportDataResponse.class);
				return response;
			} catch (Exception e) {
				Logger.get().warn(String.format("Get 聚信力  ReportData of %s warning, retry %d times , next request url : %s", request.toJson(), retryCount,JXLConfiguration.getJxlReportDataUrl()), e);
				retryCount--;
			}
		}
		if(retryCount<=0){
			Logger.get().error(String.format("Retrytimes exhaust ! Get 聚信力  ReportData of %s error", request.toJson()));
		}
		return null;
	}

	private RequestStatus getReportStatus(GetReportStatusRequest request)
	{
		DefaultClient client = new DefaultClient(JXLConfiguration.getJxlReportStatusUrl(), new DefaultRequestBuilder(request));
		int retryCount = JXLConfiguration.getJxlMaxRetries();
		int requestInterval = JXLConfiguration.getJxlRequestInterval();
		while(true)
		{
			if(retryCount <= 0)
			{
				Logger.get().error(String.format("Get 聚信力 ReportStatus of %s Error!", request.toJson()));
				return RequestStatus.Error;
			}
			//获取数据超时
			if((new Date().getTime() - startTime.getTime()) > JXLConfiguration.getJxlTimeoutSec()*1000)
			{
				Logger.get().error(String.format("Get 聚信力 ReportStatus of %s time out!", request.toJson()));
				return RequestStatus.TimeOut;
			}
	        try {
				String result = client.httpGet();
				RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.JXL_GETREPROTSTATUS_RAW_DATA, result));
				GetReportStatusResponse response = BaseObject.fromString(result, GetReportStatusResponse.class);
				if(! response.isSuccessFlag())
				{
					ThreadUtils.sleepInSeconds(requestInterval);
					retryCount --;
					continue;
				}
				else if(response.getParserInfo() == null)
				{
					Logger.get().error(String.format("Get 聚信力 ReportStatus of %s Error, reason: %s", request.toJson(), response.getNote()));
					return RequestStatus.Error;
				}
				else if(! response.getParserInfo().getMember().getStatus().equals(ReportStatus.Finished.getValue()))
				{
//					ThreadUtils.sleepInMilliseconds(requestInterval);
				    ThreadUtils.sleepInSeconds(requestInterval);
					continue;
				}
				return RequestStatus.Success;
			} catch (Exception e) {
				retryCount --;
				Logger.get().warn(String.format("Get 聚信力 ReportStatus of %s throws exception, retryCount: %d, next request url : %s", request.toJson(), retryCount,JXLConfiguration.getJxlReportStatusUrl()), e);
				continue;
			}
		}
	}

	public void saveReportData(ReportDataResponse reportData)
	{
		List<AppDerivativeVariable> variablesList = new ArrayList<>();
		variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPROTDATA_SUCCESS_FLAG, reportData.isSuccessFlag()));
		if(reportData.getReportData() != null)
		{
			List<CheckPointItem> appCheckPointList = reportData.getReportData().getApplicationCheckPointList();
			List<CheckPointItem> behaviorPointList = reportData.getReportData().getBehaviorCheckPointList();
			List<CellPhoneBehaviorItem> cellBehaviorList = reportData.getReportData().getCellPhoneBehaviorList();
			List<BindDataSourceItem> dataSourceList = reportData.getReportData().getDataSourceList();
			List<ContactRegionItem> contactRegionList = reportData.getReportData().getContactRegionList();
			List<ContactListItem> contactList = reportData.getReportData().getContactList();

			Person person = reportData.getReportData().getPerson();
			if(person != null)
			{
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_PERSON, person.toJson()));
			}
			if(appCheckPointList != null)
			{
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_APPLICATION_CHECK,
						BaseObject.toJson(appCheckPointList)));

				//是否实名认证
				CheckPointResult isRealAuthencated = this.getCheckPointResult(appCheckPointList, "身份验证", "是否本人实名认证");
				if(isRealAuthencated != CheckPointResult.NoData)
					variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_IS_REAL_AUTHENTICATED,
							isRealAuthencated.getValue()));
				//运营商信息是否匹配
				CheckPointResult isProviderMatch = this.getCheckPointResult(appCheckPointList, "身份验证", "运营商登记人身份证号码和姓名是否匹配");
				if(isProviderMatch != CheckPointResult.NoData)
					variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_IS_PROVIDER_INFO_MATCH,
							isProviderMatch.getValue()));
				//因为这两个字段在appcheck和behaviorcheck中都有出现，因此出现在两个地方
				//是否长时间关机
				CheckPointResult isAlwaysPowerOff = this.getCheckPointResult(appCheckPointList, "呼叫行为", "长时间关机");
				if(isAlwaysPowerOff != CheckPointResult.NoData)
					variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_IS_ALWAYS_POWEROFF,
							isAlwaysPowerOff.getValue()));
				//绑定号码是否是新号
				CheckPointResult isNewNumber = this.getCheckPointResult(appCheckPointList, "呼叫行为", "绑定号码是新号码");
				if(isNewNumber != CheckPointResult.NoData)
					variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_IS_NEW_NUMBER,
							isNewNumber.getValue()));
			}
			if(behaviorPointList != null)
			{
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_BEHAVIOR_CHECK,
						BaseObject.toJson(behaviorPointList)));
				//是否长时间关机
				CheckPointResult isAlwaysPowerOff = this.getCheckPointResult(behaviorPointList, "呼叫行为", "长时间关机");
				if(isAlwaysPowerOff != CheckPointResult.NoData)
					variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_IS_ALWAYS_POWEROFF,
							isAlwaysPowerOff.getValue()));
				//绑定号码是否是新号
				CheckPointResult isNewNumber = this.getCheckPointResult(behaviorPointList, "呼叫行为", "绑定号码是新号码");
				if(isNewNumber != CheckPointResult.NoData)
					variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_IS_NEW_NUMBER,
							isNewNumber.getValue()));
			}
			if(cellBehaviorList != null)
			{
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CELL_BEHAVIOR,
						BaseObject.toJson(cellBehaviorList)));
				if(cellBehaviorList.size() > 0)
				{
					variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_DATA_EXIST_MONTH_COUNT,
							getDataExistMonthCount(cellBehaviorList.get(0).getBehaviorList())));
				}

			}
			if(dataSourceList != null)
			{
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_DATA_SOURCE,
						BaseObject.toJson(dataSourceList)));
			}
			if(contactRegionList != null)
			{
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_REGION,
						BaseObject.toJson(contactRegionList)));

				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_REGION_COUNT,
						getContactRegionCount(contactRegionList)));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_NUMBER_COUNT,
						getContactNumberCount(contactRegionList)));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_LENGTH,
						getCallOutLength(contactRegionList)));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_COUNT,
						getCallOutCount(contactRegionList)));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_LENGTH,
						getCallInLength(contactRegionList)));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_COUNT,
						getCallInCount(contactRegionList)));
			}
			if(contactList != null)
			{
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_LIST,
						BaseObject.toJson(contactList)));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_COUNT,
						getContactCount(contactList)));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CALLlOANPHONE,
						this.hasCalledTargetPhone(contactList, "贷款")));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CALLFINANCEPHONE,
						this.hasCalledTargetPhone(contactList, "金融")));
				variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CALLJIEXINPHONE,
						this.hasCalledTargetPhone(contactList, "捷信")));
			}
		}
		AppDerivativeVariableManager.addVariables(variablesList);
	}

	//获取联系地区数量
	private int getContactRegionCount(List<ContactRegionItem> contactRegionList)
	{
		Set<String> regionSet = new HashSet<>();
		for(ContactRegionItem item : contactRegionList)
		{
			regionSet.add(item.getRegionLocation());
		}
		return regionSet.size();
	}
	//获取运营商联系人数量
	private int getContactCount(List<ContactListItem> contactList)
	{
		Set<String> contactSet = new HashSet<>();
		for(ContactListItem item : contactList)
		{
			contactSet.add(item.getPhoneNumber());
		}
		return contactSet.size();
	}
	//获取呼出通话总时长
	private int getCallOutLength(List<ContactRegionItem> contactRegionList)
	{
		int sum = 0;
		for(ContactRegionItem item : contactRegionList)
		{
			sum += item.getRegionCalloutTime();
		}
		return sum;
	}
	//获取呼出通话总数量
	private int getCallOutCount(List<ContactRegionItem> contactRegionList)
	{
		int sum = 0;
		for(ContactRegionItem item : contactRegionList)
		{
			sum += item.getRegionCalloutCount();
		}
		return sum;
	}
	//获取呼入通话总时长
	private int getCallInLength(List<ContactRegionItem> contactRegionList)
	{
		int sum = 0;
		for(ContactRegionItem item : contactRegionList)
		{
			sum += item.getRegionCallinTime();
		}
		return sum;
	}
	//获取呼出通话总数量
	private int getCallInCount(List<ContactRegionItem> contactRegionList)
	{
		int sum = 0;
		for(ContactRegionItem item : contactRegionList)
		{
			sum += item.getRegionCallinCount();
		}
		return sum;
	}
	//获取有数据的月份数量
	private int getDataExistMonthCount(List<BehaviorItem> behaviorList)
	{
		Set<String> behaviorSet = new HashSet<>();
		for(BehaviorItem item : behaviorList)
		{
			behaviorSet.add(item.getCellMonth());
		}
		return behaviorSet.size();
	}

	//联系号码数量
	private int getContactNumberCount(List<ContactRegionItem> regionList)
	{
		int sum = 0;
		for(ContactRegionItem item : regionList)
		{
			sum += item.getRegionUniqNumCount();
		}
		return sum;
	}

	//获取boolean类型的app检查点结果
	private CheckPointResult getCheckPointResult(List<CheckPointItem> checkList, String category, String checkPoint)
	{
		for(CheckPointItem item : checkList)
		{
			if(item.getCategory().equals(category) && item.getCheckPoint().contains(checkPoint))
			{
				return JXLUtil.GetCheckPointResult(item.getResult());
			}
		}
		return CheckPointResult.NoData;
	}

	//是否拨打过含目标名称的电话
	private boolean hasCalledTargetPhone(List<ContactListItem> contactList, String targetSrc)
	{
		for(ContactListItem item : contactList)
		{
			if(item.getPossibleContactName() != null && item.getPossibleContactName().contains(targetSrc))
				return true;
		}
		return false;
	}

	public Date getStartTime() {
		return startTime;
	}

	public String getAppId() {
		return appId;
	}

}
