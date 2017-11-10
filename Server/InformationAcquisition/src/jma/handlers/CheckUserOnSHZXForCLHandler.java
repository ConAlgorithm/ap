/**
 * 
 */
package jma.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.business.dao.EndUserExtentionDao2;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.httpclient.HttpClientApi;
import jma.AppDerivativeVariablesBuilder;
import jma.Configuration;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DataSourceResponseBase;
import jma.models.shzx.LoanGuarantRecord;
import jma.models.shzx.LoanSpecialRecordBean;
import jma.models.shzx.ShzxNfcsResponseModel;
import jma.util.DSPApiUtils;
import net.sf.json.JSONObject;

/**
 * 〈上海资信〉
 *
 * @author dengw
 * @version CheckUserOnSHZXForCLHandler.java, V1.0 2017年3月24日 下午3:05:49
 */
public class CheckUserOnSHZXForCLHandler extends NonBlockingJobHandler {
    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().serializeNulls().disableHtmlEscaping().create();
    @Override
    public void execute(String appId) throws RetryRequiredException {
        if (DynamicConfig.read("SHZXSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue())) {
            return;
        }
        Logger.get().info("CheckUserOnSHZXHandler execute appId:" + appId);
        String url = Configuration.getShzxUrl();
        try {
            Map<String, Object> param = getUserInfo(appId);
            if (param == null) {
                Logger.get().info("getUserInfo param is null");
                return;
            }
            Logger.get().info("request dsp param is " + new Gson().toJson(param));
            //调用dsp
            DataSourceResponseBase<ShzxNfcsResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<ShzxNfcsResponseModel>>(){}.getType());
            RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.SHZX_RAW_DATA, gson.toJson(res)));
            if (res != null && res.getCode() != 200) {
                Logger.get().warn(String.format("request doesnot success,retry. url=%s, result=%s", url, gson.toJson(res)));
                return;
            }
            List<ShzxNfcsResponseModel> data = res.getData();
            if (data == null || data.size() == 0) {
                Logger.get().warn("response data is null.");
                return;
            }
            ShzxNfcsResponseModel model = data.get(0);
            //相关数据入mongo
            saveData(appId,model);
        } catch (Exception e) {
            Logger.get().error(String.format("exception occurred!appId=%s, url=%s", appId, url), e);
            throw new RetryRequiredException();
        }
    }
    
    /**
     * <p>〈获取请求的用户相关信息姓名，身份证号,〉</p>
     * 
     * @param appId
     * @return
     */
    protected Map<String, Object> getUserInfo(String appId) {
        Map<String, Object> param = new HashMap<String, Object>();       
        String cowfishHost = Configuration.getCowfishUrl(); 
        String appInfoUrl = cowfishHost + "/application/" + appId; 
        
        String appInfoResponse = HttpClientApi.get(appInfoUrl);
        JSONObject appJson = JSONObject.fromObject(appInfoResponse);
        
        String userId = appJson.getString("userId");
        EndUserExtensionObject userObj = new EndUserExtentionDao2(userId).getSingle();
        
        if (userObj != null) {
            param.put("name", userObj.IdName);
            param.put("idNo", userObj.IdNumber);
        }
        return param;
    }
    
    /**
     * <p>〈保持数据入mongo〉</p>
     * 
     * @param model
     */
    protected void saveData(String appId, ShzxNfcsResponseModel model) {
        if(model!=null){
            AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
            if(model.getShzxNfcsHeadBean()!=null){
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_QueryReason, model.getShzxNfcsHeadBean().getQueryReason());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_ReportId, model.getShzxNfcsHeadBean().getReportId());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_ReportDateTime, model.getShzxNfcsHeadBean().getReportDateTime());
            }
            if(model.getUserInfoBean()!=null){
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_Name, model.getUserInfoBean().getName());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_IdType, model.getUserInfoBean().getIdType());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_IdNo, model.getUserInfoBean().getIdNo());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_Gender, model.getUserInfoBean().getGender());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_Birthday, model.getUserInfoBean().getBirthday());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateName, model.getUserInfoBean().getMateName());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateIdType, model.getUserInfoBean().getMateIdType());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateIdNo, model.getUserInfoBean().getMateIdNo());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateGender, model.getUserInfoBean().getMateGender());
                builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateBirthday, model.getUserInfoBean().getMateBirthday());
                if(model.getUserInfoBean().getMaritalInfoBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MaritalStatus, model.getUserInfoBean().getMaritalInfoBean().getMaritalStatus());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MaritalInfoDateTime, model.getUserInfoBean().getMaritalInfoBean().getMaritalInfoDateTime());
                }
                if(model.getUserInfoBean().getEduDegreeInfoBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_EducationDegree, model.getUserInfoBean().getEduDegreeInfoBean().getEducationDegree());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_EducationInfoDateTime, model.getUserInfoBean().getEduDegreeInfoBean().getEducationInfoDateTime());
                }
                if(model.getUserInfoBean().getTitleInfoBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_TitleDetails, model.getUserInfoBean().getTitleInfoBean().getTitleDetails());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_TitleInfoDateTime, model.getUserInfoBean().getTitleInfoBean().getTitleInfoDateTime());
                }
                if(model.getUserInfoBean().getHomePhonInfoBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_HomePhone, model.getUserInfoBean().getHomePhonInfoBean().getHomePhone());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_HomePhoneInfoDateTIme, model.getUserInfoBean().getHomePhonInfoBean().getHomePhoneInfoDateTIme());
                }
                if(model.getUserInfoBean().getMobileInfoBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_Mobile, model.getUserInfoBean().getMobileInfoBean().getMobile());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MobileInfoDateTime, model.getUserInfoBean().getMobileInfoBean().getMobileInfoDateTime());
                }
                if(model.getUserInfoBean().getEmailInfoBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_Email, model.getUserInfoBean().getEmailInfoBean().getEmail());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_EmailInfoDateTime, model.getUserInfoBean().getEmailInfoBean().getEmailInfoDateTime());
                }
                if(model.getUserInfoBean().getAddressInfoList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_AddressInfoList,gson.toJson(model.getUserInfoBean().getAddressInfoList()));
                }
                if(model.getUserInfoBean().getPersonWorkDetailList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_PersonWorkDetailList, gson.toJson(model.getUserInfoBean().getPersonWorkDetailList()));
                }
                if(model.getUserInfoBean().getMateWorkDetailBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateWorkDetail, model.getUserInfoBean().getMateWorkDetailBean().getMateWorkDetail());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateWorkInfoDateTime, model.getUserInfoBean().getMateWorkDetailBean().getMateWorkInfoDateTime());
                }
                if(model.getUserInfoBean().getMateContactInfoBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateContactPhone, model.getUserInfoBean().getMateContactInfoBean().getMateContactPhone());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MateContactInfoDateTime, model.getUserInfoBean().getMateContactInfoBean().getMateContactInfoDateTime());
                }
                if(model.getUserInfoBean().getContact1InfoBeanList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_Contact1InfoBeanList, gson.toJson(model.getUserInfoBean().getContact1InfoBeanList()));
                }
                if(model.getUserInfoBean().getContact2InfoBeanList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_Contact2InfoBeanList, gson.toJson(model.getUserInfoBean().getContact2InfoBeanList()));
                }
            }
            if(model.getLoanApplyRecordBean()!=null){
                if(model.getLoanApplyRecordBean().getLoanApplyRecordList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanApplyRecordList, gson.toJson(model.getLoanApplyRecordBean().getLoanApplyRecordList())); 
                }
            }
            if(model.getLoanTransactionBean()!=null){
                if(model.getLoanTransactionBean().getLoanRecordList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanRecordList, gson.toJson(model.getLoanTransactionBean().getLoanRecordList()));
                }
                if(model.getLoanTransactionBean().getLoanOutlineRecordBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanNumsTotal, model.getLoanTransactionBean().getLoanOutlineRecordBean().getLoanNumsTotal());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_FirstLoanDateTime, model.getLoanTransactionBean().getLoanOutlineRecordBean().getFirstLoanDateTime());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MaxCreditLineTotal, model.getLoanTransactionBean().getLoanOutlineRecordBean().getMaxCreditLineTotal());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanMoneyTotal, model.getLoanTransactionBean().getLoanOutlineRecordBean().getLoanMoneyTotal());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_RemainMoneyTotal, model.getLoanTransactionBean().getLoanOutlineRecordBean().getRemainMoneyTotal());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MounthRepayTotal, model.getLoanTransactionBean().getLoanOutlineRecordBean().getMounthRepayTotal());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_RepayOverdueMoneyTotal, model.getLoanTransactionBean().getLoanOutlineRecordBean().getRepayOverdueMoneyTotal());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_RepayOverdueMaxMoney, model.getLoanTransactionBean().getLoanOutlineRecordBean().getRepayOverdueMaxMoney());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_RepayOverdueMaxNums, model.getLoanTransactionBean().getLoanOutlineRecordBean().getRepayOverdueMaxNums());
                }
            }
            //担保信息
            if(model.getLoanGuarantRecordBean()!=null){
                if(model.getLoanGuarantRecordBean().getLoanGuarantRecordList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanGuarantRecordList, gson.toJson(model.getLoanGuarantRecordBean().getLoanGuarantRecordList()));
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanGuarantRecordCount, model.getLoanGuarantRecordBean().getLoanGuarantRecordList().size());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MaxMoneyLoanGuarantRecord, getMaxMoneyLoanGuarantRecord(model.getLoanGuarantRecordBean().getLoanGuarantRecordList()));
                }
            }
            
            if(model.getSpecialTransactionBean()!=null){
                if(model.getSpecialTransactionBean().getLoanSpecialRecordList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanSpecialRecordList, gson.toJson(model.getSpecialTransactionBean().getLoanSpecialRecordList()));
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanSpecialRecordCount, String.valueOf(model.getSpecialTransactionBean().getLoanSpecialRecordList().size())); 
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_MaxMoneyLoanspecialRecord, getMaxMoneyLoanspecialRecord(model.getSpecialTransactionBean().getLoanSpecialRecordList()));
                }
            }
            if(model.getShzxNfcsTipInfoBean()!=null){
                if(model.getShzxNfcsTipInfoBean().getZxTipInfoBean()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_Project, model.getShzxNfcsTipInfoBean().getZxTipInfoBean().getProject());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_TipContent, model.getShzxNfcsTipInfoBean().getZxTipInfoBean().getTipContent());
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_TipTime, model.getShzxNfcsTipInfoBean().getZxTipInfoBean().getTipTime());
                }
            }
            if(model.getLoanReportQueryBean()!=null){
                if(model.getLoanReportQueryBean().getReportQueryRecordList()!=null){
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_ReportQueryRecordList, gson.toJson(model.getLoanReportQueryBean().getReportQueryRecordList()));
                    builder.isNotNullAdd(AppDerivativeVariableNames.SHZX_LoanReportQueryCount, model.getLoanReportQueryBean().getReportQueryRecordList().size());
                }
            }
            AppDerivativeVariableManager.addVariables(builder.build());
        }
    }

    
    /**
     * <p>〈获取历史特殊交易最大交易金额〉</p>
     * 
     * @return
     */
    protected String getMaxMoneyLoanspecialRecord(List<LoanSpecialRecordBean> list){
        if(list!=null&&list.size()>0){
            double max=0;
            for (LoanSpecialRecordBean loanSpecialRecordBean : list) {
                if(Double.valueOf(loanSpecialRecordBean.getChangeMoney())>max){
                    max=Double.valueOf(loanSpecialRecordBean.getChangeMoney());
                }
            }
            return String.valueOf(max);
        }
        return null;
    }
    
    /**
     * <p>〈获取历史最大担保金额〉</p>
     * 
     * @return
     */
    protected String getMaxMoneyLoanGuarantRecord(List<LoanGuarantRecord> list){
        if(list!=null&&list.size()>0){
            double max=0;
            for (LoanGuarantRecord loanGuarantRecord : list) {
                if(Double.valueOf(loanGuarantRecord.getGuarantMoney())>max){
                    max=Double.valueOf(loanGuarantRecord.getGuarantMoney());
                }
            }
            return String.valueOf(max);
        }
        return null;
    }
    
}
