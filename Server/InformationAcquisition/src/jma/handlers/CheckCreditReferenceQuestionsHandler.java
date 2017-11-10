package jma.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jma.CrawlUtils;
import jma.DatabaseEnumValues;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;

import com.google.gson.Gson;

public class CheckCreditReferenceQuestionsHandler extends JobHandler {

  public static class CreditQuestion {
    public String question;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public String answer5;
  }
  @Override
  public void execute(String appId) throws RetryRequiredException {
    List<AppDerivativeVariable> questionResults = getQuestions(appId);
    if(questionResults==null){
      AppDerivativeVariableManager.addVariables(
          new AppDerivativeVariable(
              appId,
              AppDerivativeVariableNames.CHECK_BANK_REFERENCE_RESULT,
              DatabaseEnumValues.BankReferenceResult.QueryException));
      return;
    }
      for(AppDerivativeVariable result : questionResults){
        AppDerivativeVariableManager.addVariables(
            result );
    }
  }
  @SuppressWarnings("finally")
  private List<AppDerivativeVariable> getQuestions(String appId) {
    List<AppDerivativeVariable>result=null;
    try{
    List<String> idNameAndNumber = DatabaseUtils.getIdCardNameAndNumber(appId);
    String cookies = getCookies();
    String token = getToken(cookies);
    String questionHtml = getQuestionPage(
        cookies, token, idNameAndNumber.get(0), idNameAndNumber.get(1).toUpperCase());
    result=getQuestionJsonString(appId,questionHtml);
    }
    catch(Exception e){
      Logger.get().warn(String.format("Query Exception : appid=%s", appId),e);
    }
    finally{
      return result;
    }
  }
  private String getCookies() {
    HttpPost request = new HttpPost("https://ipcrs.pbccrc.org.cn/page/register/servearticle.jsp");
    return CrawlUtils.getResponseHandler(request).getCookies();
  }

  private String getToken(String cookies) {
    HttpPost request = new HttpPost("https://ipcrs.pbccrc.org.cn/userReg.do");
    request.setHeader("Cookie", cookies);
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("method", "initReg"));
    request.setEntity(CrawlUtils.getFormEntity(params, "GBK"));
    String htmlString = CrawlUtils.getResponseHandler(request).getHtml();
    String token = Jsoup.parse(htmlString)
        .getElementsByAttributeValue("name", "org.apache.struts.taglib.html.TOKEN")
        .attr("value");
    if (token == null) {
      throw new RuntimeException("get token failed!");
    }
    return token;
  }
  private String getQuestionPage(
      String cookies, String token, String userName, String userIdCard) {
    HttpPost request = new HttpPost("https://ipcrs.pbccrc.org.cn/userReg.do");
    request.setHeader("Cookie", cookies);
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("org.apache.struts.taglib.html.TOKEN", token));
    params.add(new BasicNameValuePair("method", "checkIdentity"));
    params.add(new BasicNameValuePair("page", "1"));
    params.add(new BasicNameValuePair("userInfoVO.name", userName));
    params.add(new BasicNameValuePair("userInfoVO.certType", "0"));
    params.add(new BasicNameValuePair("userInfoVO.certNo", userIdCard));
    params.add(new BasicNameValuePair("userInfoVO.usertype", "2"));
    request.setEntity(CrawlUtils.getFormEntity(params, "GBK"));

    return CrawlUtils.getResponseHandler(request).getHtml();
  }

  private static final Map<String, Integer> STATUS_VALUE_MAPPING =
      CollectionUtils.<String, Integer>newMapBuilder()
          .add("系统尚未收录您的个人信息", DatabaseEnumValues.BankReferenceResult.NoRecords)
          .add("查询异常", DatabaseEnumValues.BankReferenceResult.QueryException)
          .add("系统尚未收录足够的信息", DatabaseEnumValues.BankReferenceResult.NotEnoughInfo)
          .add("证件号码是无效的", DatabaseEnumValues.BankReferenceResult.NotEnoughInfo)
          .add("您提交的注册申请未通过身份验证",
              DatabaseEnumValues.BankReferenceResult.RegisteredOrRegistering)
          .add("您已注册过用户", DatabaseEnumValues.BankReferenceResult.RegisteredOrRegistering)
          .add("您之前已经提交过注册申请", DatabaseEnumValues.BankReferenceResult.RegisteredOrRegistering)
          .add("平台正处于验证试用阶段", DatabaseEnumValues.BankReferenceResult.NotSupported)
          .build();
private List<AppDerivativeVariable> getQuestionJsonString(String appId, String questionHtml) {
    List<AppDerivativeVariable>questionResults = new ArrayList<AppDerivativeVariable>();
    Document doc = Jsoup.parse(questionHtml);
    // failed to get questions
    Element creditInfo = doc.getElementById("_error_field_");
    if (creditInfo != null) {
      String creditInfoString=creditInfo.text();
      for (Map.Entry<String, Integer> entry : STATUS_VALUE_MAPPING.entrySet()) {
        if (creditInfoString.contains(entry.getKey())) {
          questionResults.add(
              new AppDerivativeVariable(
                  appId, AppDerivativeVariableNames.CHECK_BANK_REFERENCE_RESULT, entry.getValue()));
          questionResults.add(
              new AppDerivativeVariable(
                  appId, AppDerivativeVariableNames.CREDIT_REFERENCE_QUESTIONS, String.format("{\"%s\":\"%s\"}", "creditInfo", creditInfo.text())));
          return questionResults;
        }
      }
      questionResults.add(
          new AppDerivativeVariable(
              appId, AppDerivativeVariableNames.CHECK_BANK_REFERENCE_RESULT, DatabaseEnumValues.BankReferenceResult.QueryException));
      return questionResults;
    }

    // succeed in getting questions
    Gson gson = new Gson();
    List<CreditQuestion> cqList = new ArrayList<>();
    Elements questions = doc.select(".p");
    int loadNumbers=0,creditcardNumbers=0,personalNumbers=0;
    for (int i = 0; i < questions.size(); i += 6) {
      CreditQuestion cq = new CreditQuestion();
      cq.question = questions.get(i).text().replaceAll("\u00a0","").trim();    // remove &nbsp
      if(cq.question.contains("信用"))
        creditcardNumbers++;
      else
        if(cq.question.contains("贷款"))
          loadNumbers++;
        else
          personalNumbers++;
      cq.answer1 = questions.get(i+1).text().replaceAll("\u00a0","").trim();
      cq.answer2 = questions.get(i+2).text().replaceAll("\u00a0","").trim();
      cq.answer3 = questions.get(i+3).text().replaceAll("\u00a0","").trim();
      cq.answer4 = questions.get(i+4).text().replaceAll("\u00a0","").trim();
      cq.answer5 = questions.get(i+5).text().replaceAll("\u00a0","").trim();
      cqList.add(cq);
    }
    questionResults.add(
        new AppDerivativeVariable(
            appId, AppDerivativeVariableNames.CHECK_BANK_REFERENCE_RESULT, DatabaseEnumValues.BankReferenceResult.HasCreditRecord));
    questionResults.add(
        new AppDerivativeVariable(
            appId, AppDerivativeVariableNames.QUESTION_COUNT_ABOUT_LOAN, loadNumbers));
    questionResults.add(
        new AppDerivativeVariable(
            appId, AppDerivativeVariableNames.QUESTION_COUNT_ABOUT_CREDIT_CARD, creditcardNumbers));
    questionResults.add(
        new AppDerivativeVariable(
            appId, AppDerivativeVariableNames.QUESTIONS_COUNT_ABOUT_PERSONAL, personalNumbers));
    String jsonQuestions = gson.toJson(cqList);
    questionResults.add(
        new AppDerivativeVariable(
            appId, AppDerivativeVariableNames.CREDIT_REFERENCE_QUESTIONS,jsonQuestions));
    
    List<AppDerivativeVariable> resultsV2 = CreditReferenceQuestionsAnalysisV2.Analyze(appId, jsonQuestions);
    questionResults.addAll(resultsV2);
    
    return questionResults;
  }
}
