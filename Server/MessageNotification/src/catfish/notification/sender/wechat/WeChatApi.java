package catfish.notification.sender.wechat;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.collections.MapBuilder;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.Configuration;

import com.google.gson.Gson;

public class WeChatApi {

  private static final Gson GSON = new Gson();

  private static final String NEW_TOKEN_URL_FORMAT =
      "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
  private static final String UPLOAD_MEDIA_URL_FORMAT =
      "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";
  private static final String SEND_MESSAGE_URL_FORMAT =
      "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
  private static final List<Integer> ACCESS_TOKEN_ERROR_CODES = Arrays.asList(40001, 40014, 42001);
  private static final int INVALID_MEDIA_ID_CODE = 40007;
  private static final int EXPECTED_SEND_MESSAGE_CODE = 0;
  private static final Map<String, String> MEDIA_TYPE_TO_MIME_MAPPING = CollectionUtils.mapOf(
      "image", "image/jpg");
  private static final Map<String, String> MEDIA_TYPE_TO_FILENAME_MAPPING = CollectionUtils.mapOf(
      "image", "image.jpg");

  public static String generateNewToken(String weChatId, String weChatSecret) {
    String token = handleResponse(
        HttpClientApi.getGson(String.format(NEW_TOKEN_URL_FORMAT, weChatId, weChatSecret)),
        false,
        "access_token");
    Logger.get().info("WeChatApi: Generated token " + token);
    return token;
  }

  public static void sendMessage(String accessToken, String openId, String content) {
    if (accessToken == null || openId == null || content == null) {
      Logger.get().warn(String.format("WeChatApi:sendMessage:parameters error. "
          + "AccessToken(%s), OpenId(%s), Content(%s)", accessToken, openId, content));
      return;
    }

    checkResponse(HttpClientApi.postJson(
        String.format(SEND_MESSAGE_URL_FORMAT, accessToken),
        new MapBuilder<String, Object>()
            .add("touser", openId)
            .add("msgtype", "text")
            .add("text", new MapBuilder<String, String>().add("content", content).build())
            .build()));
    Logger.get().info("WeChatApi: Sent message to " + openId);
  }

  public static void sendImage(String accessToken, String openId, String mediaId) {
    checkResponse(HttpClientApi.postJson(
        String.format(SEND_MESSAGE_URL_FORMAT, accessToken),
        new MapBuilder<String, Object>()
            .add("touser", openId)
            .add("msgtype", "image")
            .add("image", new MapBuilder<String, String>().add("media_id", mediaId).build())
            .build()));
    Logger.get().info("WeChatApi: Sent image to " + openId);
  }

  public static String uploadJpgImage(String accessToken, String jpgImagePath) {
    return uploadMedia(accessToken, "image", jpgImagePath);
  }

  @SuppressWarnings("unchecked")
  public static String uploadMedia(String accessToken, String mediaType, String mediaPath) {
    MultipartEntity entity = new MultipartEntity();
    entity.addPart("file", new FileBody(
        new File(mediaPath),
        MEDIA_TYPE_TO_FILENAME_MAPPING.get(mediaType),
        MEDIA_TYPE_TO_MIME_MAPPING.get(mediaType),
        Configuration.UTF_8));

    String mediaId = handleResponse(
        (Map<String, Object>) GSON.fromJson(
            HttpClientApi.post(
                String.format(UPLOAD_MEDIA_URL_FORMAT, accessToken, mediaType), entity),
            Map.class),
        true,
        "media_id");
    Logger.get().info("WeChatApi: Uploaded media " + mediaId);
    return mediaId;
  }

  private static void checkResponse(Map<String, Object> response) {
    handleResponse(response, true, null);
  }

  private static String handleResponse(
      Map<String, Object> response,
      boolean handleTokenException,
      String requestKey /* optional */) {
    if (requestKey != null) {
      String result = (String) response.get(requestKey);
      if (!StringUtils.isNullOrWhiteSpaces(result)) {
        return result;
      }
    }

    Double code = (Double) response.get("errcode");
    if (code != null && ACCESS_TOKEN_ERROR_CODES.contains(code.intValue())) {
      if (handleTokenException) {
        throw new AccessTokenException("We chat token exception with response " + response);
      } else {
        throw new RuntimeException("We chat exception with response " + response);
      }
    }
    if (code != null && code.intValue() == INVALID_MEDIA_ID_CODE) {
      throw new InvalidMediaIdException("We chat invalid media ID " + response);
    }
    if (requestKey != null || code == null || code.intValue() != EXPECTED_SEND_MESSAGE_CODE) {
      throw new RuntimeException("We chat exception with response " + response);
    }

    return null;
  }
}
