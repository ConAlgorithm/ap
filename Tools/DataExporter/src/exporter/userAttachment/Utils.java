package exporter.userAttachment;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import catfish.base.StartupConfig;
import catfish.base.business.util.DateTimeUtils;

public class Utils {

  private static final String ID_PHOTO_FILE_NAME = "身份证.png";
  private static final String BANK_PHOTO_FILE_NAME = "银行卡.png";
  private static final String BUCKLE_PHOTO_FILE_NAME = "代扣协议.png";

  private static final String COMPLETED_FILE_NAME = "还款中";
  private static final String DELAYED_FILE_NAME = "已逾期";
  private static final String CLOSED_FILE_NAME = "分期已结束";
  private static final String CLOSE_IN_ADVANCE_FILE_NAME = "提前还款";

  public static final String COMPRESSED_FILE_TAG = ".zip";
  public static final int BUFFER_SIZE = 1024;

  public static final String FILE_NAME_SEPARATOR = "-";
  public static final String FILE_DATE_FORMAT = "yyyy-MM-dd";

  private static final String FUND_TAG = "catfish.dataExporter.userAttachment.FundTag";
  private static final String START_DATE = "catfish.dataExporter.userAttachment.starDate";
  private static final String END_DATE = "catfish.dataExporter.userAttachment.endDate";

  public static final List<String> BankList = Arrays.asList(new String[] { "交通银行", "邮政储蓄银行" });

  public static String getIdPhotoFileName() {

    return ID_PHOTO_FILE_NAME;
  }

  public static String getBankPhotoFileName() {

    return BANK_PHOTO_FILE_NAME;
  }

  public static String getBuclePhotoFileName() {

    return BUCKLE_PHOTO_FILE_NAME;
  }

  public static String getCompletedFileName() {

    return getFullFileName(COMPLETED_FILE_NAME);
  }

  public static String getDelayedFileName() {

    return getFullFileName(DELAYED_FILE_NAME);
  }

  public static String getClosedFileName() {

    return getFullFileName(CLOSED_FILE_NAME);
  }

  public static String getClosedInAdvanceFileName() {
    return getFullFileName(CLOSE_IN_ADVANCE_FILE_NAME);
  }

  private static String getFullFileName(String name) {

    return new DateTime().toString(FILE_DATE_FORMAT) + File.separator + name;
  }

  public static String getFundTag() {

    return StartupConfig.get(FUND_TAG);
  }

  public static Date getStartDate() {
    
    return DateTimeUtils.parseYMD(StartupConfig.get(START_DATE));
  }
  
  public static Date getEndDate() {
    
    return DateTimeUtils.parseYMD(StartupConfig.get(END_DATE));
  }
}
