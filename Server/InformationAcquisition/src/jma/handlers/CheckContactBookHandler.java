/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.httpclient.HttpClientApi;
import jma.DatabaseEnumValues.BlacklistType;
import jma.AppDerivativeVariablesBuilder;
import jma.DatabaseUtils;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.models.BlackListInfo;
import jma.models.DerivativeVariableNames;
import jma.models.Result;
import jma.models.mongo.AddressBook;
import jma.models.mongo.ContactInfo;
import jma.models.mongo.ResponseModel;
import jma.models.sensitive.SensitiveResult;
import jma.models.sensitive.SensitiveWordResponse;

/**
 * 〈检查用户通讯录job〉
 *
 * @author hwei
 * @version CheckContactBookHandler.java, V1.0 2017年7月11日 下午2:18:57
 */
public class CheckContactBookHandler extends NonBlockingJobHandler{
    public static String blackListUrl=StartupConfig.get("risk.blackbase.url");
    public static String mongoUrl = StartupConfig.get("risk.persistence.mongoUrl");
    public static String sensitiveUrl = StartupConfig.get("as.sensitive.url");
    @Override
    public void execute(String appId) throws RetryRequiredException {
        AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
        try {
            AddressBook addressBook = getAddressBookInfo(appId);
            if (addressBook != null && addressBook.getContents() != null) {
                List<ContactInfo> list = addressBook.getContents();
                //通讯录是否在黑中介名单中
                builder.isNotNullAdd(DerivativeVariableNames.ISINAGENCY_BLACKLISTOBJECTS, checkIsInAgencyblacklistobjects(list));               
                jma.models.ContactInfo firstContactInfo = PhoneUtils.getFirstContactInfo(appId);
                Map<String,Object> contact1Map = checkIsContactInList(list, firstContactInfo, 1);
                if(contact1Map!=null){
                    //第一联系人是否存在于用户通讯录
                    builder.isNotNullAdd(DerivativeVariableNames.ISCONTACT1_INLIST, contact1Map.get("iscontact1inlist")==null?null:Boolean.valueOf(String.valueOf(contact1Map.get("iscontact1inlist"))));
                    //第一联系人在通讯录中的称呼
                    builder.isNotNullAdd(DerivativeVariableNames.CONTACT1NAME_INLIST, String.valueOf(contact1Map.get("Contact1name")));
                    //第1联系人在通讯录里的称呼中是否包含姓名中的某一个字
                    builder.isNotNullAdd(DerivativeVariableNames.ISCONTACT1TRUE, contact1Map.get("IsContact1True")==null?null:Boolean.valueOf(String.valueOf(contact1Map.get("IsContact1True"))));
                }
                jma.models.ContactInfo secondContactInfo = PhoneUtils.getSecondContactInfo(appId);
                Map<String,Object> contact2Map = checkIsContactInList(list, secondContactInfo, 2);
                if(contact2Map!=null){
                    //第二联系人是否存在于用户通讯录
                    builder.isNotNullAdd(DerivativeVariableNames.ISCONTACT2_INLIST, contact2Map.get("iscontact2inlist")==null?null:Boolean.valueOf(String.valueOf(contact2Map.get("iscontact2inlist"))));
                    //第二联系人在通讯录中的称呼
                    builder.isNotNullAdd(DerivativeVariableNames.CONTACT2NAME_INLIST, String.valueOf(contact2Map.get("Contact2name")));
                    //第2联系人在通讯录里的称呼中是否包含姓名中的某一个字
                    builder.isNotNullAdd(DerivativeVariableNames.ISCONTACT2TRUE, contact2Map.get("IsContact2True")==null?null:Boolean.valueOf(String.valueOf(contact2Map.get("IsContact2True"))));
                }
                //黑词个数-----敏感词库
                builder.isNotNullAdd(DerivativeVariableNames.CONTACTNUMBEROFBLACK, getBlackSensitiveNumber(list));
               //通讯录厚度----计算通讯录电话非重复个数，包括固话和短号
                builder.isNotNullAdd(DerivativeVariableNames.CONTACTNUMBEROFMOBILE, getBookCounts(list));
                //含买单侠字眼个数，有“买单侠”
                builder.isNotNullAdd(DerivativeVariableNames.CONTACTNUMBEROFMDX, getMDXCount(list));
            } else {
                Logger.get().warn("addressbook is not exists ! appId is " + appId);
            }
            if (!builder.build().isEmpty()){
                AppDerivativeVariableManager.addVariables(builder.build());                
            }
        } catch (Exception e) {
            Logger.get().error("CheckContactBookHandler has error !appId is " + appId, e);
        }
    }
    
    
    /**
     * <p>〈获取用户通讯录信息〉</p>
     * 
     * @param appId
     */
    public AddressBook getAddressBookInfo(String appId) {
        if("".equals(mongoUrl) || mongoUrl==null){
            Logger.get().warn("blackListUrl is null,please add blackListUrl in configuration file ! ");
            return null;
        }
        try {
            String userId = DatabaseUtils.getUserIdBy(appId);
            if(userId!=null){
                String json = HttpClientApi.get(mongoUrl + "/addressbook/" + userId);
                ResponseModel<List<AddressBook>> response = new Gson().fromJson(json, new TypeToken<ResponseModel<List<AddressBook>>>() {
                }.getType());
                if(response!=null&&response.getCode()==1) {
                    List<AddressBook> list = response.getData();
                    if (list != null && list.size() > 0) {
                        AddressBook book = list.get(0);
                        return book;
                    }
                }                
            }
        } catch (Exception e) {
            Logger.get().error("getAddressBookInfo has error !appId is "+appId,e);
        }
        return null;
    }
    
    /**
     * <p>〈检查是否在中介黑名单中〉</p>
     * 
     * @param list
     * @return
     */
    public int checkIsInAgencyblacklistobjects(List<ContactInfo> list){
        if("".equals(blackListUrl) || blackListUrl==null){
            Logger.get().warn("blackListUrl is null,please add blackListUrl in configuration file ! ");
            return -1;
        }
        if(list!=null&&list.size()>0){
            Map<String,Object> map = new HashMap<>();
            map.put("category", "agency");
            map.put("type", BlacklistType.PHONE);  
            Set<String> set = new HashSet<>();
            String regex ="^1\\d{10}$";
            for (ContactInfo contactInfo : list) {
              if(contactInfo.getMobile().matches(regex)){
                  set.add(contactInfo.getMobile());
              }
            }
            map.put("list", new ArrayList<>(set));
            Gson gson = new Gson();
            Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
            try {
                String json = HttpClientApi.postString(blackListUrl + "/black/checkUserIsBlackListBatch", gson.toJson(map), header);
                Result<Boolean> result = gson.fromJson(json, new TypeToken<Result<Boolean>>() {
                }.getType());
                if(result!=null&&result.getCode()==1){
                    Logger.get().info("checkUserIsBlackListBatch success,result is "+gson.toJson(result));
                    return result.getData()==true?1:0;
                }else{
                    Logger.get().info("checkUserIsBlackListBatch failed");
                }
            } catch (Exception e) {
                Logger.get().error("checkUserIsBlackListBatch  has error ",e);
            }            
        }
        return -1;
    }
    
    /**
     * <p>〈检查联系人是否在用户通讯录中〉</p>
     * 
     * @return
     */
    public Map<String,Object> checkIsContactInList(List<ContactInfo> list, jma.models.ContactInfo contact,int index) {
        Map<String,Object> map =new HashMap<String, Object>();
        Boolean iscontactinlist = false;
        String name = "-1";
        Boolean  isContactTrue = false; 
        if (list != null && list.size() > 0 && contact != null && contact.mobile != null) {
            for (ContactInfo contactInfo : list) {
                if (contact.mobile.equals(contactInfo.getMobile())) {
                    iscontactinlist = true;
                    name = contactInfo.getName();
                    if(contact.name!=null&&name!=null){
                        for(int i = 0;i<contact.name.length();i++){
                            if(name.contains(String.valueOf(contact.name.charAt(i)))){
                                isContactTrue = true;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        } else {
            iscontactinlist = null;
            isContactTrue = null;
        }
        map.put("iscontact"+index+"inlist",iscontactinlist);
        map.put("Contact"+index+"name", name);
        map.put("IsContact"+index+"True", isContactTrue);
        Logger.get().info("checkIsContactInList result is: "+new Gson().toJson(map));
        return map;      
    }
    
    
    /**
     * <p>〈讯录中的所有称呼里面包含敏感词库中的敏感词的个数〉</p>
     * 
     * @return
     */
    public int getBlackSensitiveNumber(List<ContactInfo> list) {
        int count = 0;
        if ("".equals(sensitiveUrl) || sensitiveUrl == null) {
            Logger.get().warn("sensitiveUrl is null,please add sensitiveUrl in configuration file ! ");
            return count;
        }
        try {
            Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
            Map<String, String> params = new HashMap<>();
            params.put("type", "");
            params.put("degree", "1");
            String json = HttpClientApi.postString(sensitiveUrl + "/api/sensitive/queryDataByThirdParty", new Gson().toJson(params), header);
            SensitiveResult result = new Gson().fromJson(json, new TypeToken<SensitiveResult>() {
            }.getType());
            if (result != null && "200".equals(result.getCode())) {
                List<SensitiveWordResponse> data = result.getData();
                if (data != null && list != null && data.size()>0 && list.size()>0) {
                    for (SensitiveWordResponse sensitiveWordResponse : data) {
                        for (ContactInfo contactInfo : list) {
                            if(contactInfo.getName()!=null&&sensitiveWordResponse.getSensitiveWord()!=null){
                                if (contactInfo.getName().contains(sensitiveWordResponse.getSensitiveWord()) || sensitiveWordResponse.getSensitiveWord().contains(contactInfo.getName())) {
                                    count++;
                                }                                
                            }
                        }
                    }
                }
            }            
        } catch (Exception e) {
            Logger.get().error("getBlackSensitiveNumber  has error ", e);
        }
        return count;
    }
    
    /**
     * <p>〈通讯录厚度----计算通讯录电话非重复个数，包括固话和短号〉</p>
     * 
     * @param list
     * @return
     */
    public int getBookCounts(List<ContactInfo> list){
        Set<String> set = new HashSet<>();
        if(list!=null){
            for (ContactInfo contactInfo : list) {
                set.add(contactInfo.getMobile());
            }            
        }
        return set.size();        
    }
    
    /**
     * <p>〈含买单侠字眼个数，有“买单侠”〉</p>
     * 
     * @param list
     * @return
     */
    public int getMDXCount(List<ContactInfo> list){
        int count = 0;
        if(list!=null){
            for (ContactInfo contactInfo : list){
                if(contactInfo.getName()!=null){
                    if(contactInfo.getName().contains("买单侠")){
                       count++; 
                    }
                }
            }
        }
        return count;        
    }

}
