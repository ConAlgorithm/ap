package engine.rule.model.creator.app;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.CompanyContactType;
import catfish.base.business.common.ContactType;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.JobInfoDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.JobInfoObject;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import engine.rule.domain.app.AddressBook;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.creator.app.domain.Mobile;
import engine.rule.model.creator.app.domain.MobileArray;
import engine.rule.model.in.app.SocialRelationInForm;
import engine.util.RiskPersistenceService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialRelationModelCreator extends AbstractApplicationModelCreator {

    public SocialRelationModelCreator(String appId) {
        super(appId);
    }

    @Override
    public Map<String, Object> createModelForm() {
        form = new ModelBuilder<SocialRelationInForm>(new SocialRelationInForm())
            .buidDerivativeVariables(appId).getForm();

        JobInfoObject jobObj = new JobInfoDao(appId).getSingle();

        SocialRelationInForm socialForm = (SocialRelationInForm) form;
        // 公司固定电话或老板电话
        String companyContact = null;
        try {
            companyContact = ContactDao.getCompanyTelNumberByJobInfoIdAndType(jobObj.Id,
                ContactType.CompanyTel);
        } catch (Exception e) {
            Logger.get().error("", e);
        }

        // 如果为空
        if (companyContact == null || companyContact.equals(""))
            socialForm.setCompanyTelNumberType(CompanyContactType.Empty.getValue());
        // 如果为老板电话
        else if (companyContact.substring(0, 1).equals("1") && companyContact.length() == 11)
            socialForm.setCompanyTelNumberType(CompanyContactType.BossMobilePhone.getValue());
        else
            socialForm.setCompanyTelNumberType(CompanyContactType.CompanyTelePhone.getValue());

        String secondRelationship = ContactDao.getSecondRelationship(appId);
        //通过appid得到申请对象
        InstallmentApplicationObject installmentApplicationById = InstallmentApplicationDao
            .getInstallmentApplicationById(appId);
        installmentApplicationById = (installmentApplicationById == null
            ? new InstallmentApplicationObject() : installmentApplicationById);
        socialForm.setSecondRelationship(secondRelationship == null ? "" : secondRelationship);
        String userId = installmentApplicationById.getUserId();
        List<AddressBook> addressBooks = RiskPersistenceService.getAddressBook(userId);
        if(addressBooks != null && !addressBooks.isEmpty()) {
            socialForm.setAddressBookContacts(addressBooks.size());
            Logger.get().info("Get AddressBook number for contact : " + addressBooks.size());
        } 
        Map<String, Object> params = CollectionUtils.mapOf("in_Social", (Object) socialForm);
        return params;
    }

    @Override
    public String createBusinessNo() {
        // TODO Auto-generated method stub
        return null;
    }

    //查询通讯录联系人个数
    private int getContactPeopleNum(DBCollection currentCollection, String userid) {
        int size = -1;
        Gson gson = new Gson();
        try {

            BasicDBObject key = new BasicDBObject("contents", -1);
            key.append("_id", 0);
            BasicDBObject basicDBObject = new BasicDBObject("userId", userid);//"userId","C55A71EC-9206-E511-87D5-80DB2F14945F"
            DBCursor find = currentCollection.find(basicDBObject, key);
            if (find == null || find.count() == 0) {
                return -1;
            }

            List<DBObject> array = find.toArray();
            if (array == null) {
                return -1;
            }

            ArrayList<String> numbers = new ArrayList<String>();
            HashSet<String> numbersSet = new HashSet<String>();
            for (DBObject a : array) {
                MobileArray fromJson = null;
                try {
                    fromJson = gson.fromJson(a.toString(), MobileArray.class);
                } catch (Exception e) {
                    fromJson=new MobileArray();
                    Logger.get().error("反序列化错误", e);
                }
                if (fromJson == null) {
                    continue;
                }
                List<Mobile> contents = fromJson.getContents();
                if (contents == null) {
                    continue;
                }

                for (Mobile e : contents) {
                    if(e.getMobile()!=null){
                        numbers.add(e.getMobile());
                    } 
                }
            }
            for (String s : numbers) {
                numbersSet.add(handleMobliePhone(s));
            }

            size = numbersSet.size();
           

        } catch (Exception e) {
            Logger.get().error("getContactPeopleNum : ", e);
        }

        if(size==0){
            return -1;
        }
        return size;

    }

    private String handleMobliePhone(String number) {
        if (startCheck("\\d{11}", number))
            return number;
        else if (startCheck("\\*86\\d{11};", number)) {
            return handleNumber(number);
        } else if (startCheck("\\d{3}\\-\\d{4}\\-\\d{4}", number)) {
            return handleNumber2(number);
        } else if (startCheck("\\+\\d{11}", number)) {
            return handleNumber3(number);
        } else if (startCheck("\\(\\+86\\)(\\s|\\t|\\r)*\\d{11};", number)) {
            return handleNumber4(number);
        } else if (startCheck("\\(86\\)\\d{11}", number)) {
            return handleNumber5(number);
        } else if (startCheck("\\d{3}(\\s|\\t|\\r){1}\\d{4}(\\s|\\t|\\r){1}\\d{4}", number)) {
            return handleNumber6(number);
        } else if (startCheck(
            "\\+\\d{2}(\\s|\\t|\\r){1}\\(0\\)\\d{1}(\\s|\\t|\\r){1}\\d{4}(\\s|\\t|\\r){1}\\d{4};",
            number)) {
            return handleNumber7(number);
        }

        return number;
    }

    public static boolean startCheck(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }

    //*8615298857382;
    private static String handleNumber(String str) {
        String trim = str.trim();
        String replace = trim.replaceAll(";", "");
        String substring = replace.substring(3);
        return substring;
    }

    //137-7073-8773
    private static String handleNumber2(String str) {
        String trim = str.trim();
        String replaceAll = trim.replaceAll("-", "");
        return replaceAll;
    }

    //+17188887752
    private static String handleNumber3(String str) {
        String trim = str.trim();
        String replaceAll = trim.replace("+", "");
        return replaceAll;
    }

    //(+86) 18950052558  
    private static String handleNumber4(String str) {
        String trim = str.trim();
        String substring = trim.substring(5);
        String trim2 = substring.trim();
        return trim2;
    }

    //(86)13661861692
    private static String handleNumber5(String str) {
        String trim = str.trim();
        String substring = trim.substring(4);
        String trim2 = substring.trim();
        return trim2;
    }

    //136 6186 1692
    private static String handleNumber6(String str) {
        String replaceAll = str.replaceAll("(\\s|\\t|\\r){1}", "");
        return replaceAll;
    }

    //+31 (0)6 1151 3441

    private static String handleNumber7(String str) {

        String trim = str.trim();
        String replace = trim.replaceFirst("(\\s|\\t|\\r){1}", "");
        String substring = replace.substring(1);

        return substring;
    }
}
