package jma.thirdpartyservices.pengyuan;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

import jma.Configuration;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.xpath.DefaultXPath;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientConfig;

public class PengyuanApi {

  private static final String GBK = "GBK";
  private static final String SOAP_FORMAT =
      "<?xml version=\"1.0\" encoding=\"%s\"?>\n" +
      "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
      "  <soapenv:Body>\n" +
      "    <ns2:queryReport xmlns:ns2=\"http://batoffline.report.szpcs.scrc.com\">\n" +
      "      <userID xmlns=\"\">%s</userID>\n" +
      "      <password xmlns=\"\">%s</password>\n" +
      "      <queryCondition xmlns=\"\">\n" +
      "        %s\n" +
      "      </queryCondition>\n" +
      "    </ns2:queryReport>\n" +
      "  </soapenv:Body>\n" +
      "</soapenv:Envelope>\n";
  private static final String RISK_QUERY_FORMAT =
      "<conditions>\n" +
      "  <condition queryType=\"25136\">\n" +
      "    <item>\n" +
      "      <name>name</name>\n" +
      "      <value>%s</value>\n" +
      "    </item>\n" +
      "    <item>\n" +
      "      <name>documentNo</name>\n" +
      "      <value>%s</value>\n" +
      "    </item>\n" +
      "    <item>\n" +
      "      <name>subreportIDs</name>\n" +
      "      <value>14200</value>\n" +
      "    </item>\n" +
      "    <item>\n" +
      "      <name>refID</name>\n" +
      "      <value></value>\n" +
      "    </item>\n" +
      "  </condition>\n" +
      "</conditions>\n";

  public interface RiskCallback {
    void onNoResults();
    void onResults(
        int alCount, int zxCount, int sxCount, int swCount, int cqggCount, int wdyqCount);
  }

  public static void queryRisk(String name, String idNumber, RiskCallback callback) {
    String result = doQuery(String.format(RISK_QUERY_FORMAT, name, idNumber.toUpperCase()));
    Element riskSummary = null;
    try {
      riskSummary = (Element) DocumentHelper
          .parseText(result).getRootElement().selectSingleNode("//personRiskStatInfo");
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }
    if (!riskSummary.attributeValue("treatResult").equals("1")) {
      callback.onNoResults();
      return;
    }
    Element riskState = (Element) riskSummary.selectSingleNode("stat");
    callback.onResults(
        getValue(riskState, "alCount"),
        getValue(riskState, "zxCount"),
        getValue(riskState, "sxCount"),
        getValue(riskState, "swCount"),
        getValue(riskState, "cqggCount"),
        getValue(riskState, "wdyqCount"));
  }

  private static String doQuery(String queryCondition) {
    HttpPost post = new HttpPost(Configuration.getPengyuanUrl());
    post.addHeader("SOAPAction", String.format(
        "\"%s/WebServiceSingleQuery/queryReportRequest\"", Configuration.getPengyuanUrl()));
    post.setEntity(new StringEntity(
        String.format(
            SOAP_FORMAT,
            GBK,
            Configuration.getPengyuanUsername(),
            Configuration.getPengyuanPassword(),
            StringEscapeUtils.escapeXml11(queryCondition)),
        ContentType.create("text/xml", GBK)));

    Logger.get().info("Sending Pengyuan request ...");
    try (CloseableHttpResponse response =
        (CloseableHttpResponse) HttpClientConfig.get().execute(post)) {
      // The response entity exists, even for response with "500 - Internal Error"
      String responseContent = EntityUtils.toString(response.getEntity(), GBK);
      Logger.get().info("Got Pengyuan response: " + responseContent);
      return unzip(parse(responseContent));
    } catch (IOException | DocumentException e) {
      throw new RuntimeException(e);
    } finally {
      post.abort();
    }
  }

  private static String parse(String soapContent) throws DocumentException, IOException {
    DefaultXPath outerPath = new DefaultXPath("//ns1:queryReportReturn");
    outerPath.setNamespaceURIs(CollectionUtils.mapOf(
        "ns1", "http://batoffline.report.szpcs.scrc.com"));
    String outerResult = outerPath
        .selectSingleNode(DocumentHelper.parseText(soapContent).getRootElement())
        .getText();

    Element innerElement = DocumentHelper.parseText(outerResult).getRootElement();
    if (!innerElement.elementTextTrim("status").equals("1")) {
      throw new RuntimeException(String.format(
          "Pengyuan error: %s - %s",
          innerElement.elementTextTrim("errorCode"),
          innerElement.elementTextTrim("errorMessage")));
    }
    return innerElement.elementTextTrim("returnValue");
  }

  private static String unzip(String content) throws IOException {
    try (ZipInputStream zin =
        new ZipInputStream(new ByteArrayInputStream(Base64.decodeBase64(content)))) {
      zin.getNextEntry();
      return IOUtils.toString(zin, Charset.forName(GBK));
    }
  }

  private static int getValue(Element node, String key) {
    return Integer.parseInt(node.elementTextTrim(key));
  }
}
