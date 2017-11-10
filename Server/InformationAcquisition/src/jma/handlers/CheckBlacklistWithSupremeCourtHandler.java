package jma.handlers;

import java.util.ArrayList;
import java.util.List;

import jma.CrawlUtils;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.JobHandlerSwitch;
import jma.RetryRequiredException;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;

import catfish.base.DynamicConfig;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CheckBlacklistWithSupremeCourtHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    if(DynamicConfig.read("SupremeCourtSwitch", JobHandlerSwitch.Off.getValue())
        .equals(JobHandlerSwitch.Off.getValue())) {
      return;
    }

    AppDerivativeVariableManager.addVariables(
        new AppDerivativeVariable(
            appId,
            AppDerivativeVariableNames.IS_ID_IN_SUPREME_COURT_BLACKLIST,
            isInBlacklist(appId)));
  }

  private boolean isInBlacklist(String appId) {
    List<String> idNameAndNumber = DatabaseUtils.getIdCardNameAndNumber(appId);

    HttpPost request = new HttpPost("http://shixin.court.gov.cn/search");
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("pName", idNameAndNumber.get(0)));
    params.add(new BasicNameValuePair("pCardNum", idNameAndNumber.get(1)));
    params.add(new BasicNameValuePair("pProvince", "0"));    // search all provinces
    request.setEntity(CrawlUtils.getFormEntity(params, "UTF-8"));
    String html = CrawlUtils.getResponseHandler(request).getHtml();

    return Jsoup.parse(html).select("#Resultlist tr").size() > 1;    // if no record, return table head = 1
  }
}
