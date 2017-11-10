package engine.rule.model.creator.app;

import java.math.BigDecimal;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.BuyPurposeType;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.JobInfoDao;
import catfish.base.business.dao.PersonalInfoDao;
import catfish.base.business.dao.ProductDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.JobInfoObject;
import catfish.base.business.object.PersonInfoObject;
import catfish.base.business.object.ProductObject;
import engine.exception.DBFieldAdapterException;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.app.ApplicationInForm;
import engine.util.ApplicationHelper;
import engine.util.SettlementService;

public class ApplicationModelCreator extends AbstractApplicationModelCreator {

    public ApplicationModelCreator(String appId) {
        super(appId);
    }

    @Override
    public Map<String, Object> createModelForm() {
        try {

            form = new ModelBuilder<ApplicationInForm>(new ApplicationInForm()).buidDerivativeVariables(appId).buildModelFieldsByAdapter("engine.rule.model.adapter.PrincipalAdapter", appId).getForm();

            ApplicationInForm appForm = (ApplicationInForm) form;
            // 教育程度
            Integer education = EndUserExtentionDao.getEducationByAppId(appId);
            if (education != null)
                appForm.setEducation(education);

            PersonInfoObject personObj = new PersonalInfoDao(appId).getSingle();
            // 婚姻
            if (personObj != null && personObj.MarriageStatus != null)
                appForm.setMarriage(personObj.MarriageStatus);
            // 居住情况
            if (personObj != null && personObj.LivingCondition != null)
                appForm.setLivingCondition(personObj.LivingCondition);

            JobInfoObject jobObj = new JobInfoDao(appId).getSingle();
            if (jobObj != null) {
                // 第几份工作
                appForm.setNthJob(jobObj.NthJob);
            }

            InstallmentApplicationObject installmentApplicationObject = InstallmentApplicationDao.getInstallmentApplicationById(appId);
            BigDecimal principal = installmentApplicationObject.getPrincipal();
            //String productName= installmentApplicationObject.getProductName(); //手机名称
            String productId = ApplicationHelper.GetProductId(appId);
            ProductObject productObject = ProductDao.getProductById(productId);

            String productName = (productObject == null ? "" : productObject.getName());

            appForm.setPrincipal(principal);
            appForm.setProductName(productName);
            appForm.setProduct(installmentApplicationObject.getProductName());
            BigDecimal downPayment = installmentApplicationObject.getDownpayment().setScale(2, BigDecimal.ROUND_HALF_UP);
            appForm.setDownPayment(downPayment.doubleValue());
            String buyPurpose = InstallmentApplicationDao.getBuyPurpose(appId);
            BigDecimal firstRepayment = null;
            try {
                firstRepayment = SettlementService.maxRepayment(appId);
//                firstRepayment = SettlementService.getMaxRepayment(appId);
            } catch (Exception e) {
                Logger.get().error("MaxRepayment Error.appId:" + appId, e);
            }
            BigDecimal productProfitability = InstallmentApplicationDao.getProductProfitability(appId);
            String productTypeName = InstallmentApplicationDao.getProductTypeName(appId);

            if (buyPurpose != null) {
                buyPurpose=buyPurpose.trim();
                if (buyPurpose.equals("傲娇")) {
                    appForm.setPurpose(BuyPurposeType.AoJiao.getValue());
                } else if (buyPurpose.equals("杯具")) {
                    appForm.setPurpose(BuyPurposeType.BeiJu.getValue());
                } else if (buyPurpose.equals("任性")) {
                    appForm.setPurpose(BuyPurposeType.RengXing.getValue());
                } else if (buyPurpose.equals("土豪")) {
                    appForm.setPurpose(BuyPurposeType.TuHao.getValue());
                } else if (buyPurpose.equals("羞涩")) {
                    appForm.setPurpose(BuyPurposeType.XiuSe.getValue());
                } else if (buyPurpose.equals("移动")){
                	appForm.setPurpose(BuyPurposeType.YiDong.getValue());
                }else {
                    Logger.get().error("没有得到可用的buyPurpose:" + buyPurpose);
                }
            }

            if (firstRepayment != null) {
                appForm.setFirstpay(firstRepayment);
            }
            if (productProfitability != null) {
                appForm.setApr(productProfitability);
            }

            appForm.setProducttype(productTypeName == null ? "" : productTypeName);

            Map<String, Object> in = CollectionUtils.mapOf("in_Application", (Object) appForm);
            return in;
        } catch (DBFieldAdapterException e) {
            Logger.get().error(e);
        }
        return null;
    }

    @Override
    public String createBusinessNo() {
        // TODO Auto-generated method stub 

        return null;
    }

}
