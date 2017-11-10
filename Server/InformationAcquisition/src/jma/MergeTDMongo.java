package jma;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import thirdparty.td.DetailResponse;
import thirdparty.td.Response;
import thirdparty.td.Rule;
import thirdparty.td.RuleDetailModel;
import thirdparty.td.TdApi;
import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.business.common.ContactPersonType;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;

public class MergeTDMongo {

  public static final int PER_PAGE = 1000;
  private static final int QUERY_INTERVAL_IN_SECONDS = 2;
  private static final Map<Integer, String> CONTACT_MAP = Collections.emptyMap();
  private static final Map<String, String> DETAIL_NAMES = Collections.emptyMap();
  private static final File FILE = new File("errorappids");
  private static FileWriter WRITER = null;
  
  static {
    ContactPersonType[] contactPersonTypes = ContactPersonType.values();
    for (ContactPersonType contactPersonType : contactPersonTypes) {
      CONTACT_MAP.put(contactPersonType.getValue(), contactPersonType.toString());
    }
    CONTACT_MAP.put(jma.DatabaseEnumValues.ContactPersonType.FIRST_CONTACT_PERSON, "FirstContact");
    CONTACT_MAP.put(jma.DatabaseEnumValues.ContactPersonType.SECOND_CONTACT_PERSON, "SecondContact");
    CONTACT_MAP.put(jma.DatabaseEnumValues.ContactPersonType.THIRD_CONTACT_PERSON, "ThirdContact");

    DETAIL_NAMES.put("3个月内手机在本平台外的借款申请次数", "LoanAmount");
    DETAIL_NAMES.put("3个月内手机多平台借款平台数", "PlatformAmount");
    DETAIL_NAMES.put("3个月内身份证在本平台外的借款申请次数", "LoanAmount");
    DETAIL_NAMES.put("3个月内身份证多平台借款平台数", "PlatformAmount");
    
    try {
      if (FILE.exists()) {
        FILE.delete();
      }
      FILE.createNewFile();
      WRITER = new FileWriter(FILE);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    int startAt = 1;

    while (true) {
      List<User> list = MergeTDMongo.getTDUser(startAt, startAt + PER_PAGE);
      startAt += PER_PAGE + 1;

      if (CollectionUtils.isEmpty(list)) {
        break;
      }
      for (User user : list) {
        mergeUserTD(user);
      }

      for (User user : list) {
        mergeUserContactPerson(user);
      }

      if (list.size() < PER_PAGE) {
        break;
      }
    }

  }

  public static void mergeUserTD(User user) {
    boolean pass = true;

    String mobile = user.getUsermobile();
    Response response = TdApi.query(user.getIdname(), user.getIdnumber(), mobile);

    RawDataStorageManager.addRawDatas(new RawData(user.getAppid(), RawDataVariableNames.TD_UNITED_RAW_DATA, response
      .getRawResponse()));

    if (!response.getHit_rules().isEmpty()) {
      AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(user.getAppid());
      try {
        for (Rule rule : response.getHit_rules()) {
          if("33674".equals(rule.getId()) || "33676".equals(rule.getId())){
            builder.add(AppDerivativeVariableNames.TD_RULE_PREFIX + rule.getId(), true);
          }
        }
        String seqId = response.getSeq_id();
        // wait td generating data for seqId
        if (seqId != null && !seqId.isEmpty()) {
          ThreadUtils.sleepInSeconds(QUERY_INTERVAL_IN_SECONDS);
          DetailResponse detailResponse = TdApi.queryDetailsV2(seqId);
          List<RuleDetailModel> details = TdApi.analyzePolicy(detailResponse);

          RawDataStorageManager.addRawDatas(new RawData(user.getAppid(), RawDataVariableNames.TD_DETAIL_RAW_DATA,
            detailResponse.getRawResponse()));
          for (RuleDetailModel detail : details) {
            String varName = TdApi.TdVariableMap.get(detail.getDescription());
            if (varName == null) {
              continue;
            }
            builder.add(varName, detail.getCalculateResult() + detail.getCount());
          }
        }

        // update
        AppDerivativeVariableManager.addVariables(builder.build());
      } catch (Exception e) {
        pass = false;
        Logger.get().error(String.format("mergeUserTD faild ,userId=%s", user.getUserid()),e);
      } finally {
        if (!pass) {
          logErrorAppId(user.getAppid());
        }
      }
    }
  }

  public static void mergeUserContactPerson(User user) {
    boolean pass = true;

    try {
      for (ContactPerson contact : getContactPerson(user.getAppid(), user.getUserid())) {
        queryUnited(contact);
      }
    } catch (NullPointerException nullPointerException) {
      if (nullPointerException.getMessage().equals("1")) {
        // addRawDatas failed
    	  
      }
      if (nullPointerException.getMessage().equals("2")) {
        // addVariables failed

      }
      pass = false;
    } catch (Exception e) {
      Logger.get().error(String.format("mergeUserContactPerson faild ,appId=%s,userId=%s", user.appid,user.userid),e);
      pass = false;
    } finally {
      if (!pass) {
        logErrorAppId(user.getAppid());
      }
    }
  }

  public static void queryUnited(ContactPerson contact) {
    AppDerivativeVariablesBuilder variableBuilder = new AppDerivativeVariablesBuilder(contact.getAppid());
    String currentContactLiteral = CONTACT_MAP.get(contact.getType());
    List<RawData> rawDataList = new ArrayList<RawData>();
    Response response = TdApi.query(contact.getName(), "" /* id number */, contact.getMobile());
    rawDataList.add(new RawData(contact.getAppid(), String.format(
      RawDataVariableNames.TD_CONTACT_UNITED_RAW_DATA_FORMAT, currentContactLiteral), response.getRawResponse()));
    for (Rule rule : response.getHit_rules()) {
      variableBuilder.add(
        String.format(AppDerivativeVariableNames.TD_CONTACT_RULE_FORMAT, currentContactLiteral, rule.getId()), true);
    }

    String sequenceId = response.getHit_rules().isEmpty() ? null : response.getSeq_id();

    if (sequenceId != null) {
      ThreadUtils.sleepInSeconds(QUERY_INTERVAL_IN_SECONDS);
      DetailResponse detailResponse = TdApi.queryDetailsV2(sequenceId);
      rawDataList.add(new RawData(contact.getAppid(), String.format(
        RawDataVariableNames.TD_CONTACT_DETAIL_RAW_DATA_FORMAT, currentContactLiteral), response.getRawResponse()));
      for (RuleDetailModel item : TdApi.analyzePolicy(detailResponse)) {
        String itemName = DETAIL_NAMES.get(item.getDescription());
        if (itemName != null) {
          variableBuilder.add(String.format(AppDerivativeVariableNames.TD_CONTACT_RULE_DETAIL_FORMAT,
            currentContactLiteral, "33674" /* mobile cross platform rule id */, itemName), item.getCalculateResult()
            + item.getCount());
        }
      }
      try {
        if (!rawDataList.isEmpty()) {
          RawDataStorageManager.addRawDatas(rawDataList);
        }
      } catch (Exception e) {
        throw new NullPointerException("1");
      }

      try {
        if (!variableBuilder.build().isEmpty()) {
          AppDerivativeVariableManager.addVariables(variableBuilder.build());
        }
      } catch (Exception e) {
        throw new NullPointerException("2");
      }
    }
  }

  public static List<User> getTDUser(int startAt, int end) {

    String sql = "select appid,userid,idname,idnumber,usermobile from "
      + "(select *,TID=row_number()over(order by appid) from TDUSER) a where a.TID between :from and :end";

    Dao<User> userDao = Dao.create(User.class, DatabaseApi.database);

    return userDao.getMultiple(sql, catfish.base.CollectionUtils.mapOf("from", startAt, "end", end));
  }

  public static List<ContactPerson> getContactPerson(String appid, String userid) {
    String sql = "SELECT :userid as userid, :appid as appid,m.contactPersonType as type, m.content as mobile, m.name as name FROM ( select n = ROW_NUMBER() over (partition by t.contactPersonType order by t.DateAdded desc),*  FROM (SELECT [ContactPersonObjects].Name AS name, [ContactObjects].Content AS content, [ContactPersonObjects].ContactPersonType AS contactPersonType, [ContactPersonObjects].DateAdded FROM [ContactObjects], [ContactPersonObjects] WHERE [ContactPersonObjects].MobileContactId = [ContactObjects].Id  AND [ContactPersonObjects].AppId = :appid ) t ) m WHERE n = 1";
    Dao<ContactPerson> userDao = Dao.create(ContactPerson.class, DatabaseApi.database);

    return userDao.getMultiple(sql, catfish.base.CollectionUtils.mapOf("userid", userid, "appid", appid));
  }

  public List<ContactPerson> getTDContactPerson(String appid) {

    String sql = "SELECT appid ,userid ,mobile ,name,type  FROM TDCONTACTPERSON where appid = :appid";

    Dao<ContactPerson> userDao = Dao.create(ContactPerson.class, DatabaseApi.database);

    return userDao.getMultiple(sql, catfish.base.CollectionUtils.mapOf("appid", appid));
  }

  public static void logErrorAppId(String appId) {
    try {
      WRITER.write(appId);
      WRITER.write("\r\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void deleteContactPerson(String appid) {

    String sql = "delete FROM TDCONTACTPERSON where appid = :appid";

    DatabaseApi.updateTable(sql, catfish.base.CollectionUtils.mapOf("appid", appid));
  }

  public static void deleteUser(String appid) {

    String sql = "delete FROM TDUSER where appid = :appid";

    DatabaseApi.updateTable(sql, catfish.base.CollectionUtils.mapOf("appid", appid));
  }

  public static class User {
    String appid;
    String userid;
    String idname;
    String idnumber;
    String usermobile;

    public String getAppid() {
      return appid;
    }

    public void setAppid(String appid) {
      this.appid = appid;
    }

    public String getUserid() {
      return userid;
    }

    public void setUserid(String userid) {
      this.userid = userid;
    }

    public String getIdname() {
      return idname;
    }

    public void setIdname(String idname) {
      this.idname = idname;
    }

    public String getIdnumber() {
      return idnumber;
    }

    public void setIdnumber(String idnumber) {
      this.idnumber = idnumber;
    }

    public String getUsermobile() {
      return usermobile;
    }

    public void setUsermobile(String usermobile) {
      this.usermobile = usermobile;
    }
  }

  public static class ContactPerson {
    String appid;
    String userid;
    String name;
    String mobile;
    int type;

    public String getAppid() {
      return appid;
    }

    public void setAppid(String appid) {
      this.appid = appid;
    }

    public String getUserid() {
      return userid;
    }

    public void setUserid(String userid) {
      this.userid = userid;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getMobile() {
      return mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public int getType() {
      return type;
    }

    public void setType(int type) {
      this.type = type;
    }
  }
}
