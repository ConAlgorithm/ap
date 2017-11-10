package jma.handlers;

import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import jma.AppDerivativeVariablesBuilder;
import jma.Configuration;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.models.ContactInfo;
import jma.models.DataSourceResponseBase;
import jma.models.ECL;
import jma.models.JOModel;
import jma.models.RSL;
import jma.util.DSPApiUtils;

public class JOInfoCrawlHandler extends NonBlockingJobHandler {
    public static final String JOAuthenticated3 = "B7";
    public static final String JOAuthenticated2 = "A2";
    public static final String JOLenthOfNerwork = "A3";
    public static final String ExceptionCode = "NoResult";

    @Override
    public void execute(String appId) throws RetryRequiredException {
        try {
            this.appId = appId;
            //get idname idno mobile
            EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
            ContactObject contactObj = new ContactDao(appId).getSingle();

            //get jo data from dsp
            if (userObj == null || contactObj == null) {
                Logger.get().warn("can not get userObj or contactObj for " + appId);
                return;
            }
            Map<String, Object> param = CollectionUtils.mapOf("name", userObj.IdName, "idNo", userObj.IdNumber, "mobile", contactObj.Content);
            JOModel joModel = fetchJOData(getJOUrl(), param);
            if (null == joModel) {
                Logger.get().warn("can not get joModel for " + appId);
                return;
            }

            AppDerivativeVariablesBuilder adBuilder = new AppDerivativeVariablesBuilder(appId);
            //query first contact person get username and usermobile
            ContactInfo firstContactInfo = PhoneUtils.getFirstContactInfo(appId);
            ContactInfo secondContactInfo = PhoneUtils.getSecondContactInfo(appId);
            ContactInfo selfInfo = new ContactInfo();
            selfInfo.setName(userObj.IdName);
            selfInfo.setMobile(contactObj.Content);
            //save jo data to mongo
            saveJOData(appId, joModel, adBuilder);

            //save jo contact data to mongo
            saveJOContactData(firstContactInfo, secondContactInfo, selfInfo, adBuilder);

            if (firstContactInfo != null) {
                //save IsFirstContactSameSunName  to mongo 
                saveSunName(userObj.IdName, firstContactInfo.getName(), adBuilder);
                //save first contact relation to mongo    
                saveFirstRelation(firstContactInfo.getRelationShip(), adBuilder);
            }
            AppDerivativeVariableManager.addVariables(adBuilder.build());
        } catch (Exception e) {
            Logger.get().error("crawl jo data error for" + appId, e);
        }

    }

    private void saveFirstRelation(Integer relationShip, AppDerivativeVariablesBuilder adBuilder) {
        adBuilder.add(AppDerivativeVariableNames.FirstContactType, relationShip);
    }

    private void saveRSLData(List<RSL> x_JO_rsl, AppDerivativeVariablesBuilder adBuilder) {
        if (x_JO_rsl == null || x_JO_rsl.isEmpty()) {
            return;
        }
        x_JO_rsl.forEach(rsl -> saveDVByIFT(rsl.getIft(), rsl.getRs().getCode(), rsl.getRs().getDesc(), adBuilder));
    }

    private void saveECLData(List<ECL> x_JO_ecl, AppDerivativeVariablesBuilder adBuilder) {
        if (x_JO_ecl == null || x_JO_ecl.isEmpty()) {
            return;
        }
        x_JO_ecl.forEach(ecl -> saveDVByIFT(ecl.getIft(), ExceptionCode, ecl.getCode(), adBuilder));
    }

    private JOModel fetchJOData(String url, Map<String, Object> param) {
        int tryNums = 0;
        while (tryNums < Configuration.getJoMaxRetries()) {
            try {
                DataSourceResponseBase<JOModel> res = DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<JOModel>>() {
                }.getType());
                if (res.getCode() == 200) {
                    if (res.getData().isEmpty()) {
                        Logger.get().warn("fetch jo data fail for " + tryNums + "times," + "null data for user " + param.get("mobile"));
                        tryNums++;
                        break;
                    }
                    return res.getData().get(0);
                } else {
                    Logger.get().warn("try to fetch jo data fail for " + tryNums + "times" + "for user " + param.get("mobile"));
                    tryNums++;
                }
            } catch (Exception e) {
                Logger.get().warn("try to fetch jo data fail for " + tryNums + "for user " + param.get("mobile") + "times", e);
                tryNums++;
            }
        }

        return null;
    }

    private String getJOUrl() {
        return "/dsp/api/resource/jo";
    }

    private String getJONoIdNoUrl() {
        return "/dsp/api/resource/jo/retain";
    }

    protected JOModel getJoData(ContactInfo contactInfo) {
        Map<String, Object> Param = CollectionUtils.mapOf("name", contactInfo.getName(), "mobile", contactInfo.getMobile());

        return fetchJOData(getJONoIdNoUrl(), Param);

    }

    private void saveSunName(String idName, String firstContactName, AppDerivativeVariablesBuilder adBuilder) {
        if (idName != null && firstContactName != null) {
            adBuilder.add(AppDerivativeVariableNames.IsFirstContactSameSunName, idName.charAt(0) == firstContactName.charAt(0));
        }
    }

    private void saveJOContactData(ContactInfo firstContactInfo, ContactInfo secondContactInfo, ContactInfo selfInfo, AppDerivativeVariablesBuilder adBuilder) {
        if (firstContactInfo != null) {
            saveContactIFT(AppDerivativeVariableNames.JO_IsFirstContactRealAuthenticated2, AppDerivativeVariableNames.JO_IsFirstContactRealAuthenticated2Desc, getJoData(firstContactInfo), adBuilder);
        }
        if (secondContactInfo != null) {
            saveContactIFT(AppDerivativeVariableNames.JO_IsSecondContactRealAuthenticated2, AppDerivativeVariableNames.JO_IsSecondContactRealAuthenticated2Desc, getJoData(secondContactInfo),
                adBuilder);
        }
//        if (selfInfo != null) {
//            saveContactIFT(AppDerivativeVariableNames.JO_IsRealAuthenticated2, AppDerivativeVariableNames.JO_IsRealAuthenticated2Desc, getJoData(selfInfo), adBuilder);
//        }

    }

    private void saveJOData(String appId, JOModel joModel, AppDerivativeVariablesBuilder adBuilder) {
        saveECLData(joModel.getX_JO_ecl(), adBuilder);
        saveRSLData(joModel.getX_JO_rsl(), adBuilder);

    }

    private void saveDVByIFT(String ift, String code, String desc, AppDerivativeVariablesBuilder builder) {
        if (JOLenthOfNerwork.equals(ift)) {
            builder.add(AppDerivativeVariableNames.JO_LengthOfNetwork, code);
            builder.add(AppDerivativeVariableNames.JO_LengthOfNetworkDesc, desc);
        } else if (JOAuthenticated3.equals(ift)) {
            builder.add(AppDerivativeVariableNames.JO_IsRealAuthenticated3, code);
            builder.add(AppDerivativeVariableNames.JO_IsRealAuthenticated3Desc, desc);
        } else if (JOAuthenticated2.equals(ift)) {
            builder.add(AppDerivativeVariableNames.JO_IsRealAuthenticated2, code);
            builder.add(AppDerivativeVariableNames.JO_IsRealAuthenticated2Desc, desc);
        }
    }

    protected void saveContactIFT(String appVariableName, String appVariableDescName, JOModel model, AppDerivativeVariablesBuilder adBuilder) {
        if (null == model) {
            return;
        }
        for (ECL ecl : model.getX_JO_ecl()) {
            if (JOAuthenticated2.equals(ecl.getIft())) {
                adBuilder.add(appVariableName, ExceptionCode);
                adBuilder.add(appVariableDescName, ecl.getCode());
            }
        }
        for (RSL rsl : model.getX_JO_rsl()) {
            if (JOAuthenticated2.equals(rsl.getIft())) {
                adBuilder.add(appVariableName, rsl.getRs().getCode());
                adBuilder.add(appVariableDescName, rsl.getRs().getDesc());
            }
        }
    }
}
