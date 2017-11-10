package catfish.notification.sender.wechat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseConfig;
import catfish.base.database.DatabaseExtractors;

public class TokenManager extends ReusableResourceManager {

  private static final int EXPIRATION_IN_MINUTES = 115;   // 5 minutes to 2 hours
  private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  private String weChatId;
  private String weChatSecret;

  public TokenManager(String name, String weChatId, String weChatSecret) {
    super(name);

    this.weChatId = weChatId;
    this.weChatSecret = weChatSecret;
  }

  @Override
  public synchronized void update() {
    String oldIdentifier = identifier;
    readExistingToken();
    if (identifier.equals(oldIdentifier) || isIdentifierExpired()) {
      generateToken();
      saveToken();
    }
  }

  @Override
  protected int getExpirationInMinutes() {
    return EXPIRATION_IN_MINUTES;
  }

  private void readExistingToken() {
    String sql =
        "SELECT AccessToken, LastModified " +
        "FROM WeiXinAccessTokenObjects " +
        "WHERE AppId = :AppId and AppSecret = :AppSecret";
    Map<String, ?> params = CollectionUtils.mapOf(
        "AppId", weChatId,
        "AppSecret", weChatSecret);
    List<String> result = DatabaseApi.querySingleResult(
        sql, params, DatabaseExtractors.TWO_STRING_EXTRACTOR);

    identifier = result.get(0);
    updateExpirationDate(parseDate(result.get(1)));
    Logger.get().info(String.format("TokenDatabaseApi: Read token %s for %s", identifier, name));
  }

  private void saveToken() {
    String sql =
        "UPDATE WeiXinAccessTokenObjects " +
        "SET AccessToken = :AccessToken, LastModified = :LastModified " +
        "WHERE AppId = :AppId and AppSecret = :AppSecret";
    Map<String, ?> params = CollectionUtils.mapOf(
        "AccessToken", identifier,
        "LastModified", formatDate(addMinutes(expiration, -EXPIRATION_IN_MINUTES)),
        "AppId", weChatId,
        "AppSecret", weChatSecret);
    new NamedParameterJdbcTemplate(DatabaseConfig.getDataSource()).update(sql, params);
    Logger.get().info(String.format("TokenDatabaseApi: Saved token %s for %s", identifier, name));
  }

  private void generateToken() {
    identifier = WeChatApi.generateNewToken(weChatId, weChatSecret);
    updateExpirationDate();
  }

  private static Date parseDate(String literal) {
    try {
      return new SimpleDateFormat(DATE_FORMAT).parse(literal);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  private static String formatDate(Date date) {
    return new SimpleDateFormat(DATE_FORMAT).format(date);
  }
}
