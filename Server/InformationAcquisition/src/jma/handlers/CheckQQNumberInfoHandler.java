package jma.handlers;

import java.io.IOException;

import jma.DatabaseUtils;
import jma.JobHandler;
import jma.QQDerivativeVariable;
import jma.QQDerivativeVariableManager;
import jma.RetryRequiredException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CheckQQNumberInfoHandler extends JobHandler {
  private static String QUERY_QQ_URL = "http://redstones.sinaapp.com/apis/qqinfo-service.php?qq=%s";

  private final static String UIN = "uin";
  private final static String QZONE = "qzone";
  private final static String INTIMACYSCORE = "intimacyScore";
  private final static String NICKNAME = "nickname";
  private final static String REALNAME = "realname";
  private final static String SMARTNAME = "smartname";
  private final static String BITMAP = "bitmap";
  private final static String AVATARURL = "avatarUrl";

	@Override
	public void execute(String appId) throws RetryRequiredException {
    addQQNumber(DatabaseUtils.getUserQQ(appId));
	}

	private static QQDerivativeVariable getQQDerivativeVariableFromNet(String QQNumber) {
    QQDerivativeVariable variable = new QQDerivativeVariable(QQNumber);
     try {
       Document doc = Jsoup.connect(String.format(QUERY_QQ_URL, QQNumber)).get();
       Elements els = doc.getElementsByTag("body");

       String jstring = els.text();
       jstring = jstring.substring(jstring.indexOf("(") + 2, jstring.lastIndexOf(")"));
       JsonObject jsonObject = new JsonParser().parse(jstring).getAsJsonObject();
         if(jsonObject.has(UIN) && QQNumber.equals(jsonObject.get(UIN).getAsString())) {
           variable.setExist(true);
           if(jsonObject.has(QZONE)) {
             variable.setqZone(jsonObject.get(QZONE).getAsInt());
           }
           if(jsonObject.has(INTIMACYSCORE)) {
             variable.setIntimacyScore(jsonObject.get(INTIMACYSCORE).getAsInt());
           }
           if(jsonObject.has(REALNAME)) {
             variable.setRealName(jsonObject.get(REALNAME).getAsString());
           }
           if(jsonObject.has(NICKNAME)) {
             variable.setNickName(jsonObject.get(NICKNAME).getAsString());
           }
           if(jsonObject.has(SMARTNAME)) {
             variable.setSmartName(jsonObject.get(SMARTNAME).getAsString());
           }
           if(jsonObject.has(BITMAP)) {
             variable.setBitmap(jsonObject.get(BITMAP).getAsString());
           }
           if(jsonObject.has(AVATARURL)) {
             variable.setAvataUrl(jsonObject.get(AVATARURL).getAsString());
           }
         } else {
           variable.setExist(false);
         }

      } catch (IOException e) {
        variable.setExist(true);
    }
    return variable;
  }

	private static void addQQNumber(String QQNumber) {
    QQDerivativeVariable variable = getQQDerivativeVariableFromNet(QQNumber);
    QQDerivativeVariableManager.addQQVariables(variable);
  }
}
