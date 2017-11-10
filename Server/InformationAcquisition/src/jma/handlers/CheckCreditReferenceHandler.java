package jma.handlers;

import im.catfish.captcha.CaptchaConstant;
import im.catfish.captcha.CaptchaRecognitionFailException;
import im.catfish.captcha.algorithm.Classifier;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import catfish.base.Logger;
import catfish.base.business.common.BankReferenceResult;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import jma.CrawlUtils;
import jma.DatabaseUtils;
import jma.JobHandler;
import jma.RetryRequiredException;

public class CheckCreditReferenceHandler extends JobHandler {

  private static final String BASE_URI = "https://ipcrs.pbccrc.org.cn";
  private int retryTimes = 0;
  private static final int MAX_RETRY_TIMES = 5;
  private int result = 0;

  @Override
  public void execute(String appId) throws RetryRequiredException {
    Document doc = null;
    try {
      doc = Jsoup.parse(getResponse(appId));
    }
    catch (CaptchaRecognitionFailException e) {
      retryExecute(appId);
      return;
    }
    Element errorField = doc.getElementById("_error_field_");

    if (errorField == null && doc.getElementById("getCode").attr("value").equals("获取动态码")) {
      result = BankReferenceResult.Registrable.getValue();
    }
    else if (errorField.text().equals("目前系统尚未收录您的个人信息，无法进行注册。")) {
      result = BankReferenceResult.NoRecords.getValue();
    }
    else if (errorField.text().equals("您已注册过用户，请返回首页直接登录。")) {
      result = BankReferenceResult.RegisteredOrRegistering.getValue();
    }
    else if (errorField.text().equals("验证码输入错误，请重新输入。")) {
      retryExecute(appId);
      return;
    }
    else {
      result = BankReferenceResult.QueryException.getValue();
      Logger.get().warn(String.format("%s:%s: unknown result or exception: %s",
          appId, this.getClass().getName(), doc.html()));
    }

    AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
        appId, AppDerivativeVariableNames.CHECK_BANK_REFERENCE_RESULT, result));
  }

  private void retryExecute(String appId) throws RetryRequiredException {
    retryTimes++;
    Logger.get().warn(String.format("%s:%s: captcha not match! retry: %d,next request uri : %s",
        appId, this.getClass().getName() , retryTimes,BASE_URI));
    if (retryTimes >= MAX_RETRY_TIMES) {
      return;
    }
    execute(appId);
  }

  private String getResponse(String appId) throws CaptchaRecognitionFailException {
    List<String> idNameAndNumber = DatabaseUtils.getIdCardNameAndNumber(appId);
    String cookies = getCookies();
    String regHtml = getRegHtml(cookies);
    String token = Jsoup.parse(regHtml)
        .getElementsByAttributeValue("name", "org.apache.struts.taglib.html.TOKEN")
        .attr("value");
    String captchaUri = Jsoup.parse(regHtml)
        .getElementById("imgrc")
        .attr("src");
    BufferedImage captcha = getCaptchaImage(cookies, captchaUri);
    String decaptcha = new Classifier(captcha, CaptchaConstant.CHAR_NUMBER_OF_CAPTCHA)
        .initialize()
        .getCaptchaByKnn();
    Logger.get().info("captcha: " + decaptcha);
    return checkIdentity(idNameAndNumber, cookies, token, decaptcha);
  }

  private String getCookies() {
    HttpGet request = new HttpGet(BASE_URI);
    return CrawlUtils.getResponseHandler(request).getCookies();
  }

  private String getRegHtml(String cookies) {
    HttpGet request = new HttpGet(BASE_URI + "/userReg.do?method=initReg");
    request.setHeader("cookie", cookies);
    return CrawlUtils.getResponseHandler(request).getHtml();
  }

  private BufferedImage getCaptchaImage(String cookies, String captchaUri) {
    BufferedImage captchaImg = null;
    HttpGet request = new HttpGet(BASE_URI + captchaUri);
    request.setHeader("Cookie", cookies);
    HttpResponse response = CrawlUtils.getResponseHandler(request).getResponse();
    try {
      InputStream is = response.getEntity().getContent();
      captchaImg = ImageIO.read(is);
      is.close();
    } catch (IllegalStateException | IOException e) {
      throw new RuntimeException("get captcha image failed!");
    }
    return captchaImg;
  }

  private String checkIdentity(
      List<String> idNameAndNumber,String cookies, String token, String captcha) {
    HttpPost request = new HttpPost(BASE_URI + "/userReg.do");
    request.setHeader("Cookie", cookies);
    List<NameValuePair> params = new ArrayList<>();
    params.addAll(Arrays.asList(
        new BasicNameValuePair("org.apache.struts.taglib.html.TOKEN", token),
        new BasicNameValuePair("method", "checkIdentity"),
        new BasicNameValuePair("userInfoVO.name", idNameAndNumber.get(0)),
        new BasicNameValuePair("userInfoVO.certNo", idNameAndNumber.get(1)),
        new BasicNameValuePair("userInfoVO.certType", "0"),
        new BasicNameValuePair("_@IMGRC@_", captcha),
        new BasicNameValuePair("1", "on")));
    request.setEntity(CrawlUtils.getFormEntity(params, "GBK"));
    return CrawlUtils.getResponseHandler(request).getHtml();
  }

}
