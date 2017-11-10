package jma.thirdpartyservices.junyu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jma.Configuration;
import jma.OssManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.xpath.DefaultXPath;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.AttachmentType;
import catfish.base.business.dao.AttachmentDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.dao.UserAttachmentDao;
import catfish.base.business.object.AttachmentObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.object.UserAttachmentObject;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.HttpClientConfig;

public class JYChecker {

	public static Set<Integer> CallErrorSet = new HashSet<>();

	private static ScheduledExecutorService interrupter =
	    Executors.newSingleThreadScheduledExecutor();

	private static final String CONTENT_FORMAT =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
    + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
    + "  <soapenv:Body>"
    + "    <ns1:ToIngestInvoke xmlns:ns1=\"http://tempuri.org/\">"
    + "      <ns1:strXmlIn>"
    + "        %s"
    + "      </ns1:strXmlIn>"
    + "    </ns1:ToIngestInvoke>"
    + "  </soapenv:Body>"
    + "</soapenv:Envelope>";


	static{
		CallErrorSet.add(-1300);//服务器内部错误
		CallErrorSet.add(-1301);//服务器连接超时
		CallErrorSet.add(-2300);//公安部服务器内部错误
		CallErrorSet.add(-2301);//服务器连接超时
		CallErrorSet.add(-2103);//超出限制数量
		CallErrorSet.add(-2104);//超出使用期限
		CallErrorSet.add(2107);//账户充值余额不足
		//对比身份证和公安部照片出错
		CallErrorSet.add(-2001);//姓名为空
		CallErrorSet.add(-2002);//身份证号为空
		CallErrorSet.add(-2003);//证件翻拍照为空
		CallErrorSet.add(-2007);//姓名格式不正确
		CallErrorSet.add(-2008);//身份证格式不正确
		CallErrorSet.add(-2009);//照片格式不正确
		//对比身份证和现场照片出错
		CallErrorSet.add(-1001);//姓名为空
		CallErrorSet.add(-1002);//身份证号为空
		CallErrorSet.add(-1003);//抓拍照片为空
		CallErrorSet.add(-1007);//姓名格式不正确
		CallErrorSet.add(-1008);//身份证格式不正确
		CallErrorSet.add(-1009);//照片格式不正确
		CallErrorSet.add(-1011);//证件翻拍照为空
	}
	public static byte[] getPhoto(String userId, AttachmentType type)
	{
		ByteArrayOutputStream outStream = null;
		try{
			UserAttachmentObject ut = new UserAttachmentDao(userId, type).getSingle();
			AttachmentObject at = new AttachmentDao(ut.AttachmentId).getSingle();

			outStream = new ByteArrayOutputStream();
			InputStream  stream = OssManager.get(at.FilePath);
			//InputStream stream = new FileInputStream("test.jpg");
			byte[] photo = new byte[stream.available()];
			int realSize = 0;

			while((realSize = stream.read(photo)) != -1)
			{
				outStream.write(photo, 0, realSize);
			}
			stream.close();
			return outStream.toByteArray();
		}catch(Exception e)
		{
			Logger.get().warn(String.format("Cannot get byte array of image: %d, userId: %s", type.getValue(), userId),e);
			return null;
		}finally{
			if(outStream != null)
				try {
					outStream.close();
				} catch (IOException e) {
					Logger.get().warn("Cannot close ByteArrayOutputStream, userId: " + userId);
				}
		}
	}

	public static String prepareData(String idNumber, String idName, byte[] copyPicture, byte[] capturePicture, int type) {
	    String dateTimeLiteral = DateTimeUtils.format(new Date());
	    String ciperText = DigestUtils.md5Hex(String.format(
	        "%s%d%d%s",
	        Configuration.getJunyuKey(),
	        copyPicture.length,
	        capturePicture.length,
	        dateTimeLiteral));
	    return new StringBuilder()
	        .append("<Request>")
	            .append("<MsgType>0</MsgType>")
	            .append("<Name>").append(idName).append("</Name>")
	            .append("<Id>").append(idNumber).append("</Id>")
	            .append("<IdPhotos_Base64>")
	                .append(Base64.encodeBase64String(copyPicture))
	            .append("</IdPhotos_Base64>")
	            .append("<CapturePhotos_Base64>")
	                .append(Base64.encodeBase64String(capturePicture))
	            .append("</CapturePhotos_Base64>")
	             .append("<type>")
	             .append(type)
	             .append("</type>")
	            .append("<DateTime>").append(dateTimeLiteral).append("</DateTime>")
	            .append("<CipherText>").append(ciperText).append("</CipherText>")
	        .append("</Request>")
	        .toString();
	  }

	  public static String query(String requestBody, String url) {
	    final HttpPost post = new HttpPost(url);
	    post.setHeader("Content-Type", "text/xml; charset=UTF-8");
	    post.setHeader("SOAPAction", "\"http://tempuri.org/ToIngestInvoke\"");
	    post.setEntity(new StringEntity(
	        String.format(CONTENT_FORMAT, requestBody), catfish.base.httpclient.Configuration.UTF_8));

	    interrupter.schedule(new Runnable() {
	      @Override
	      public void run() {
	        post.abort();
	      }
	    }, HttpClientConfig.TIMEOUT, TimeUnit.MILLISECONDS);

	    try {
	      Element root = DocumentHelper.parseText(HttpClientApi.execute(post)).getRootElement();
	      DefaultXPath path = new DefaultXPath("//ns1:ToIngestInvokeResult");
	      path.setNamespaceURIs(CollectionUtils.mapOf("ns1", "http://tempuri.org/"));
	      return path.selectSingleNode(root).getText();
	    } catch (DocumentException e) {
	      throw new RuntimeException(e);
	    }
	  }

	  public static List<Integer> prepareResponse(String responseBody) {
		    try {
		      Element root = DocumentHelper.parseText(responseBody).getRootElement();
		      return Arrays.asList(
		          tryParse(root.elementTextTrim("Return"), -1),
		          tryParse(root.elementTextTrim("IDSimilarity"), -1),
		          tryParse(root.elementTextTrim("CaptureSimilarity"), -1));
		    } catch (DocumentException e) {
		      throw new RuntimeException(e);
		    }
	  }

	  public static int tryParse(String value, int defaultValue) {
		    try {
		      return Integer.parseInt(value);
		    } catch (NumberFormatException e) {
		      return defaultValue;
		    }
	  }

	  public static List<Integer> callJunYu(String appId, AlternativeUrlsContainer urlsContainer, int type)
	  {
		  int retryCount = Configuration.getJunyuMaxRetries();
		  String url = urlsContainer.popUrl();
		  if(url == null)
			  return null;
		  int count;
		  for(count = 0; count < retryCount; count ++)
		  {
			  try{
				    EndUserExtensionObject user = new EndUserExtentionDao(appId).getSingle();
					byte[] headPhoto = getPhoto(user.Id, AttachmentType.UserHeadPhoto);
					byte[] idPhoto = getPhoto(user.Id, AttachmentType.UserIdPhoto);

					if(headPhoto != null && idPhoto != null)
					{
						String responseStr = query(prepareData(user.IdNumber, user.IdName, idPhoto, headPhoto, type), url);
						RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.JUNYU_RAW_DATA+"_" + type, responseStr));
						return JYChecker.prepareResponse(responseStr);
					}
			  }catch(Exception e)
			  {
				  //修改error的信息级别为warn
				  Logger.get().warn(String.format("CallJunYun warning, appId: %s , retryCount: %d ,next request url : %s", appId, count,url), e);
			  }
		  }
		  if(count>=retryCount){
			  Logger.get().error(String.format("Retrytimes exhausted ! CallJunYun error, appId: %s", appId));
		  }
		  return callJunYu(appId, urlsContainer, type);
	  }
}
