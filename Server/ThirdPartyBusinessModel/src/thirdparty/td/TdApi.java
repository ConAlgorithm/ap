package thirdparty.td;

import java.util.*;

import catfish.base.business.util.AppDerivativeVariableNames;

import thirdparty.config.TdConfiguration;
import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;

import com.google.gson.Gson;

public class TdApi {
  public static Response query(String name, String idNumber, String mobileNumber) {
    return handleResponse(HttpClientApi.postForm(
            TdConfiguration.getQueryUrl(),
            prepareContent(name, idNumber, mobileNumber)));
  }

  static Map<String, String> prepareContent(
      String name, String idNumber, String mobileNumber) {
    return CollectionUtils.<String, String>newMapBuilder()
        .add("partner_code", TdConfiguration.getPartnerCode())
        .add("secret_key", TdConfiguration.getSecretKey())
        .add("event_id", TdConfiguration.getEventId())
        .add("account_name", name)
        .add("id_number", idNumber)
        .add("account_mobile", mobileNumber)
        .build();
  }

  static Response handleResponse(String literal) {
    Response response = new Gson().fromJson(literal, Response.class);
    response.setRawResponse(literal);
    if (!response.isSuccess()) {
      Logger.get().warn("TD Api error " + response.getReason_code());
    }
    return response;
  }

  // {"reason_code":"001:用户认证失败","success":false}
  public static DetailResponse queryDetailsV2(String sequenceId) {
    return handleDetailResponse(HttpClientApi.get(String.format(
            "%s?partner_code=%s&partner_key=%s&sequence_id=%s",
      TdConfiguration.getDetailUrl(),
              TdConfiguration.getPartnerCode(),
              TdConfiguration.getPartnerKey(),
              sequenceId)));
  }

    static DetailResponse handleDetailResponse(String literal) {
        DetailResponse detailResponse = new Gson().fromJson(literal, DetailResponse.class);
        detailResponse.setRawResponse(literal);
        if (!detailResponse.isSuccess()) {
            Logger.get().warn("TD Api error " + detailResponse.getReasonCode());
        }
        return detailResponse;
    }

    public static List<RuleDetailModel> analyzePolicy(DetailResponse response) {

        List<PolicyModel> policyList = response.getPolicyList();
        List<RuleDetailModel> details = new ArrayList<>();
        if (policyList != null && policyList.size() > 0) {
            for (PolicyModel policy : policyList) {
                List<RuleModel> ruleList = policy.getRuleList();
                if (ruleList != null && ruleList.size() > 0) {
                    for (RuleModel rule : ruleList) {
                        details.addAll(rule.getRuleDetails());
                    }
                }
            }
        }

        return details;
    }

    public static Map<String, String> TdVariableMap = new HashMap<String, String>() {
        {
            put("3个月内手机在本平台外的借款申请次数", AppDerivativeVariableNames.TD_RULE_33674_OUTER_PLATFORM_LOAN_AMOUNT);
            put("3个月内手机多平台借款平台数", AppDerivativeVariableNames.TD_RULE_33674_LOAN_AMOUNT);
            put("3个月内身份证在本平台外的借款申请次数", AppDerivativeVariableNames.TD_RULE_33676_OUTER_PLATFORM_LOAN_AMOUNT);
            put("3个月内身份证多平台借款平台数", AppDerivativeVariableNames.TD_RULE_33676_LOAN_AMOUNT);
        }
    };


}
