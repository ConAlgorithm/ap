package jma.handlers;

import grasscarp.contact.model.ContactPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jma.AppDerivativeVariablesBuilder;
import jma.Configuration;
import jma.JobHandlerSwitch;
import jma.DatabaseEnumValues.ContactPersonType;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import thirdparty.td.DetailResponse;
import thirdparty.td.Response;
import thirdparty.td.Rule;
import thirdparty.td.RuleDetailModel;
import thirdparty.td.TdApi;
import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.ThreadUtils;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.httpclient.HttpClientApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CheckContactCreditOnTdHandler extends NonBlockingJobHandler {

  private static final int QUERY_INTERVAL_IN_SECONDS = 2;
  private static final String CONTACT_URL_FORMAT = "%s/contact?appId=%s";
  private static final Map<Integer, String> CONTACT_MAP = CollectionUtils.mapOf(
      ContactPersonType.FIRST_CONTACT_PERSON, "FirstContact",
      ContactPersonType.SECOND_CONTACT_PERSON, "SecondContact");
  private static final Map<String, String> DETAIL_NAMES = CollectionUtils.mapOf(
      "3个月内手机在本平台外的借款申请次数", "LoanAmount",
      "3个月内手机多平台借款平台数", "PlatformAmount");

  private AppDerivativeVariablesBuilder variableBuilder;
  private List<RawData> rawDataList = new ArrayList<>();
  private ContactPerson currentContent;
  private String currentContactLiteral;

  @Override
  public void execute(String appId) throws RetryRequiredException {
	  if(DynamicConfig.read("TDSwitch",JobHandlerSwitch.Off.getValue())
				.equals(JobHandlerSwitch.Off.getValue())) 
	  {
		  return;
	  }
    variableBuilder = new AppDerivativeVariablesBuilder(appId);
    try {
      for (ContactPerson contact : getContacts()) {
        if (contact.getContactPersonType() >= ContactPersonType.THIRD_CONTACT_PERSON) {
          continue;   // only two contacts supported in the first version
        }
        currentContent = contact;
        currentContactLiteral = CONTACT_MAP.get(contact.getContactPersonType());
        String detailSequenceId = queryUnited();
        if (detailSequenceId != null) {
          ThreadUtils.sleepInSeconds(QUERY_INTERVAL_IN_SECONDS);
          queryDetail(detailSequenceId);
        }
      }
    } finally {
      if (!rawDataList.isEmpty()) {
        RawDataStorageManager.addRawDatas(rawDataList);
      }
      if (!variableBuilder.build().isEmpty()) {
        AppDerivativeVariableManager.addVariables(variableBuilder.build());
      }
    }
  }

  private List<ContactPerson> getContacts() {
    return new Gson().fromJson(
        HttpClientApi.get(String.format(
            CONTACT_URL_FORMAT,
            Configuration.getApplicationServiceUrl(),
            requestMessager.getAppId())),
        new TypeToken<List<ContactPerson>>() {}.getType());
  }

  private String queryUnited() {
    Response response = TdApi.query(
        currentContent.getName(),
        "" /* id number */,
        currentContent.getContent());
    rawDataList.add(new RawData(
        requestMessager.getAppId(),
        String.format(
            RawDataVariableNames.TD_CONTACT_UNITED_RAW_DATA_FORMAT,
            currentContactLiteral),
        response.getRawResponse()));
    for (Rule rule : response.getHit_rules()) {
      variableBuilder.add(
          String.format(
              AppDerivativeVariableNames.TD_CONTACT_RULE_FORMAT,
              currentContactLiteral,
              rule.getId()),
          true);
    }
    return response.getHit_rules().isEmpty() ? null : response.getSeq_id();
  }

  private void queryDetail(String sequenceId) {
    DetailResponse response = TdApi.queryDetailsV2(sequenceId);
    rawDataList.add(new RawData(
        requestMessager.getAppId(),
        String.format(
            RawDataVariableNames.TD_CONTACT_DETAIL_RAW_DATA_FORMAT,
            currentContactLiteral),
        response.getRawResponse()));
    for (RuleDetailModel item : TdApi.analyzePolicy(response)) {
      String itemName = DETAIL_NAMES.get(item.getDescription());
      if (itemName != null) {
        variableBuilder.add(
            String.format(
                AppDerivativeVariableNames.TD_CONTACT_RULE_DETAIL_FORMAT,
                currentContactLiteral,
                "33674" /* mobile cross platform rule id */,
                itemName),
            item.getCalculateResult() + item.getCount());
      }
    }
  }
}
