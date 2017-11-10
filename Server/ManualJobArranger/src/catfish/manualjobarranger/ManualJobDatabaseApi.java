package catfish.manualjobarranger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.util.DateTimeUtils;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;

public class ManualJobDatabaseApi {

  private static final String QUERY_ALL_QUEUES_SQL =
      "SELECT Name FROM ManualJobQueueConfigObjects";

  private static final String INSERT_SQL =
      "INSERT INTO ManualJobBookingObjects (AppId, JobName, JobGeneratedDate, " +
      "    MessageHandle, MessageBody, QueueName, DateAdded, LastModified) " +
      "VALUES (:AppId, :JobName, :JobGeneratedDate, :MessageHandle, :MessageBody, " +
      "    :QueueName, GETDATE(), GETDATE())";

  private static final String DELETE_SQL =
      "DELETE FROM ManualJobBookingObjects WHERE Id = :Id";

  private static final String QUERY_SQL =
      "SELECT B.Id, B.AppId, B.JobName, A.LicenseAgreedOn, B.JobGeneratedDate, " +
      "       C.JobDuration, A.InstalmentChannel, B.MessageHandle, B.MessageBody, B.QueueName " +
      "FROM ManualJobBookingObjects B " +
      "LEFT JOIN InstallmentApplicationObjects A ON B.AppId = A.Id " +
      "JOIN ManualJobQueueConfigObjects C ON B.QueueName = C.Name " +
      "JOIN ExaminationSkillObjects S ON C.Skill = S.SkillName " +
      "JOIN UserExaminationSkillObjects US ON S.Id = US.SkillId " +
      "WHERE C.IsRealtime = :IsRealtime " +
      "  AND US.UserId = :UserId " +
      "  AND C.Product IN ( :Products ) ";

  private static final String QUERY_COUNT_SQL =
      "SELECT COUNT (*) " +
      "FROM ManualJobBookingObjects B " +
      "LEFT JOIN InstallmentApplicationObjects A ON B.AppId = A.Id " +
      "JOIN ManualJobQueueConfigObjects C ON B.QueueName = C.Name " +
      "JOIN ExaminationSkillObjects S ON C.Skill = S.SkillName " +
      "JOIN UserExaminationSkillObjects US ON S.Id = US.SkillId " +
      "WHERE US.UserId = :UserId " +
      "  AND C.Product IN ( :Products ) ";

  private static final String QUERY_EXAMINER_SQL_FORMAT =
      "SELECT COUNT(*) " +
      "FROM AccountOnlineStatObjects " +
      "WHERE DateAdded >= :Date " +
      "    AND (%s) ";

  private static final String QUERY_EXAMING_APP_COUNT =
      "SELECT COUNT(*) " +
      "FROM InstallmentApplicationObjects " +
      "WHERE InstallmentStartedOn >= :Date " +
      "  AND Status > :LowerBound " +
      "  AND Status <= :UpperBound ";

  public static List<String> getAllQueues() {
    return DatabaseApi.queryMultipleResults(
        QUERY_ALL_QUEUES_SQL, new HashMap<String, String>(), DatabaseExtractors.STRING_EXTRACTOR);
  }

  public static void recordMessage(MessageEntity message) {
    Map<String, ?> params = CollectionUtils.<String, Object>newMapBuilder()
        .add("AppId", message.getAppId())
        .add("JobName", message.getJobName())
        .add("JobGeneratedDate", message.getJobGeneratedDate())
        .add("MessageHandle", message.getMessageHandle())
        .add("MessageBody", message.getMessageBody())
        .add("QueueName", message.getQueueName())
        .build();
    DatabaseApi.updateTable(INSERT_SQL, params);
  }

  public static boolean deleteMessage(String id) {
    return DatabaseApi.updateTable(DELETE_SQL, CollectionUtils.mapOf("Id", id)) > 0;
  }

  public static List<MessageEntity> retrieveMessages(
      List<String> products, String userId, boolean isRealtime) {
    Map<String, ?> params = CollectionUtils.mapOf(
        "IsRealtime", isRealtime ? 1 : 0,
        "UserId", userId,
        "Products", products);
    RowMapper<MessageEntity> extractor = new RowMapper<MessageEntity>() {
      @Override
      public MessageEntity mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
        MessageEntity entity = new MessageEntity();
        entity.setId(resultSet.getString("Id"));
        entity.setAppId(resultSet.getString("AppId"));
        entity.setJobName(resultSet.getString("JobName"));
        entity.setJobGeneratedDate(DateTimeUtils.parse(resultSet.getString("JobGeneratedDate")));
        entity.setJobDuration(resultSet.getInt("JobDuration"));
        entity.setChannel(resultSet.getObject("InstalmentChannel") == null
            ? -1
            : resultSet.getInt("InstalmentChannel"));
        entity.setMessageHandle(resultSet.getString("MessageHandle"));
        entity.setMessageBody(resultSet.getString("MessageBody"));
        entity.setQueueName(resultSet.getString("QueueName"));
        if (StringUtils.isNullOrWhiteSpaces(resultSet.getString("LicenseAgreedOn"))) {
          Logger.get().warn("Unknown app submitted date, assume 10 minutes ago");
          entity.setAppSubmittedDate(new Date(new Date().getTime() - 10 * 60 * 1000));
        } else {
          entity.setAppSubmittedDate(DateTimeUtils.parse(resultSet.getString("LicenseAgreedOn")));
        }
        return entity;
      }
    };
    return DatabaseApi.queryMultipleResults(QUERY_SQL, params, extractor);
  }

  public static int retrieveMessageCount(List<String> products, String userId) {
    return DatabaseApi.querySingleInteger(QUERY_COUNT_SQL, CollectionUtils.mapOf(
        "UserId", userId,
        "Products", products));
  }

  public static int queryOnlineExaminerCount(List<String> prefixes) {
    StringBuilder builder = new StringBuilder();
    boolean isFirst = true;
    for (String prefix : prefixes) {
      if (!isFirst) {
        builder.append(" OR ");
      }
      isFirst = false;
      builder.append(String.format("UserName like '%s%%'", prefix));;
    }
    return DatabaseApi.querySingleInteger(
        String.format(QUERY_EXAMINER_SQL_FORMAT, builder.toString()),
        CollectionUtils.mapOf("Date", formatDateNow()));
  }

  public static int queryExamingAppCount() {
    return DatabaseApi.querySingleInteger(
        QUERY_EXAMING_APP_COUNT,
        CollectionUtils.mapOf(
            "Date", formatDateNow(),
            "LowerBound", ApplicationStatus.Started.getValue(),
            "UpperBound", ApplicationStatus.MerchantApproved.getValue()));
  }

  private static String formatDateNow() {
    return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
  }
}
