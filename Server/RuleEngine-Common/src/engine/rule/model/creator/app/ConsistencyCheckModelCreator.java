package engine.rule.model.creator.app;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.CheckNameIDCardResult;
import catfish.base.business.common.MobileserverType;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.dao.MobilePhoneCNAreaDao;
import catfish.base.business.object.CNAreaObject;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.MerchantStoreObject;
import catfish.base.business.util.EnumUtils;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.creator.app.domain.PaymentDetail;
import engine.rule.model.creator.app.domain.RepaymentTableResult;
import engine.rule.model.in.app.ConsistencyCheckInForm;
import engine.rule.model.in.app.PersonalInfoInForm;

public class ConsistencyCheckModelCreator extends AbstractApplicationModelCreator {

    private PersonalInfoModelCreator personModelCreator;

    public ConsistencyCheckModelCreator(String appId,
                                        PersonalInfoModelCreator personalInfoModelCreator) {
        super(appId);

        this.personModelCreator = personalInfoModelCreator;
    }

    @Override
    public Map<String, Object> createModelForm() {

        ContactDao contactDao = new ContactDao(appId);
        form = new ModelBuilder<ConsistencyCheckInForm>(new ConsistencyCheckInForm())
            .buidDerivativeVariables(appId).getForm();

        ConsistencyCheckInForm consisForm = (ConsistencyCheckInForm) form;
        // 联系人不同城市数量
        consisForm.setContactorCitiesNumber(ContactDao.getContactPersonCitiesNumberById(appId));

        Map<String, Object> params = CollectionUtils.mapOf("in_Derived", (Object) consisForm);

        // 是否本地人
        MerchantStoreObject storeObj = MerchantStoreDao
            .getMerchantStoreById(MerchantStoreDao.getMerchantStoreId(appId));
        if (personModelCreator != null && storeObj != null) {
            params.putAll(personModelCreator.createModelForm());
            try {
                String birthProvince = ((PersonalInfoInForm) personModelCreator.getForm())
                    .getIdCardProvince();
                String birthCity = ((PersonalInfoInForm) personModelCreator.getForm())
                    .getIdCardCity();
                CNAreaObject area = new CNAreaDao(storeObj.CNAreaId).getSingle();
                String storeLocatedCity = MerchantStoreDao.getLocatedCityByAreaCode(area.Code);
                // 如果没有值，默认为true
                boolean result = true;
                if (!birthCity.equals("")) {
                    result = birthCity.contains(storeLocatedCity);
                } else {
                    result = birthProvince.contains(storeLocatedCity);
                }
                consisForm.setNativePerson(result);
            } catch (Exception e) {
                Logger.get().error(e);
            }
        }

        InstallmentApplicationObject installmentApplicationObject = InstallmentApplicationDao
            .getInstallmentApplicationById(appId);

        BigDecimal principal = installmentApplicationObject.getPrincipal();
        BigDecimal downPayment = installmentApplicationObject.getDownpayment();

        //BigDecimal downPaymentRate = downPayment.divide(downPayment.add(principal), 5, BigDecimal.ROUND_UP).add(new BigDecimal("0.00005"));
        Double downPaymentRateDouble = downPayment.doubleValue()
                                       / (principal.doubleValue() + downPayment.doubleValue());
        BigDecimal downPaymentRate = new BigDecimal(downPaymentRateDouble);

        consisForm.setDownPaymentRate(downPaymentRate);

        //使身份证一致性检查结果标准化
        consisForm.setIdCardIdentificationResult(
            filterIdCardIdentificationResult(consisForm.getIdCardIdentificationResult()));

        Date installmentStartedOn = installmentApplicationObject.getInstallmentStartedOn();

        if (installmentStartedOn != null) {
            consisForm.setOpenweekday(getWeekOfDayByDate(installmentStartedOn));
            consisForm.setOpentime(getHourOfDayByDate(installmentStartedOn));
        } else {
            consisForm.setOpentime("");
            consisForm.setOpenweekday("");

        }
        //得到申请时的手机号
        String personMobileNo = ContactDao.getPersonMobileNoForApplication(appId);
        String serviceProvider = "";

        if (personMobileNo != null) {
            consisForm.setMobile_o(personMobileNo);
            try {
                consisForm.setMobile_osec(personMobileNo.substring(0, 3));
                serviceProvider = MobilePhoneCNAreaDao
                    .getTelecomOperatorbyMobile1to7(personMobileNo.substring(0, 7));
                if(serviceProvider != null){
                    
                    if(serviceProvider.equals("移动")){
                        consisForm.setServiceProvider(MobileserverType.ChinaMobile.getValue());
                    }else  if(serviceProvider.equals("联通")){
                        consisForm.setServiceProvider(MobileserverType.ChinaUnicom.getValue());
                    }
                    else  if(serviceProvider.equals("电信")){
                        consisForm.setServiceProvider(MobileserverType.CHINATELECOM.getValue());
                    }
                    else  if(serviceProvider.equals("虚拟运营商")){
                        consisForm.setServiceProvider(MobileserverType.MobileVirtualNetworkOperator.getValue());
                    }
                } 
            } catch (Exception e) {
                consisForm.setMobile_osec("");
                consisForm.setServiceProvider(MobileserverType.NONE.getValue());
                Logger.get().error("getTelecomOperatorbyMobile1to7 手机号处理异常", e);
            }
        } else {
            consisForm.setMobile_o("");
            consisForm.setMobile_osec("");
            consisForm.setServiceProvider(MobileserverType.NONE.getValue());
        }
        //得到用户最新手机号
        ContactObject single = contactDao.getSingle();
        String personMobileNow = single.Content;
        if (personMobileNow != null) {
            consisForm.setMobile(personMobileNow);
            try {
                consisForm.setMobilesec(personMobileNow.substring(0, 3));

            } catch (Exception e) {
                consisForm.setMobilesec("");
                Logger.get().error("setMobilesec 手机号处理异常", e);
            }

        } else {
            consisForm.setMobile("");
            consisForm.setMobilesec("");
        }
    /*    //二次贷
        InstallmentApplicationObject lastInstallmentApplicationObjects = InstallmentApplicationDao
            .getLastInstallmentApplicationObjectsById(appId);
        //还款结清日期
        Date paymentDate = null;
        //上一次借款状态
        Integer lastLoanStatus = -99;
        //上一次借款申请日期
        Date lastInstallmentStartedOn = null;
        //上一次借款提前还款天数
        int lastLoanPrepaymentdays = -1;
        //上一次已完成借款最大逾期天数
        int lastAppMaxDelayedDays = -1;
        //距离上一次已完成借款最后还款日期时间
        int lastApplicationInterval = -1;
        if (lastInstallmentApplicationObjects == null) {
            consisForm.setSecondTimeUser(false);
        } else {
            lastInstallmentStartedOn = lastInstallmentApplicationObjects.getInstallmentStartedOn();
            String id = lastInstallmentApplicationObjects.getId();
            if (id != null) {
                Map<Integer, RepaymentTableResult> repaymentTable = SettlementService
                    .getRepaymentTable(id);
                if (repaymentTable == null || repaymentTable.size() == 0) {
                    Logger.get().error("得到空的还款计划");

                }
                try {
                    lastAppMaxDelayedDays = getLastAppMaxDelayedDays(repaymentTable);
                } catch (Exception e) {
                    Logger.get().error("getLastAppMaxDelayedDays function error", e);
                }

                //设置上一次已完成借款最大逾期天数
                consisForm.setLastAppMaxDelayedDays(lastAppMaxDelayedDays);
                //设置是否是二次贷
                consisForm.setSecondTimeUser(true);

                InstallmentStatus status = null;
                try {
                    status = SettlementService.getStatus(id);
                } catch (Exception e) {
                    Logger.get().error("getStatus function error", e);
                }

                if (status != null) {
                    String status2 = status.getStatus();
                    if (status2.equals(PaymentStatus.aheadPayment.getValue())
                        || status2.equals(PaymentStatus.hesitationPeriodPayment.getValue())) {
                        Date updateDate = status.getUpdateDate();
                        try {
                            lastLoanPrepaymentdays = daysBetween(lastInstallmentStartedOn,
                                updateDate);
                            //set上一次借款提前还款天数
                            consisForm.setLastLoanPrepaymentdays(lastLoanPrepaymentdays);
                        } catch (ParseException e) {
                            Logger.get().error("daysBetween function error", e);
                        }
                        paymentDate = updateDate;
                        lastLoanStatus = ApplicationStatus.ClosedInAdvanced.getValue();
                    } else if (status2.equals(PaymentStatus.normalPayment.getValue())) {
                        paymentDate = status.getUpdateDate();
                        lastLoanStatus = ApplicationStatus.Closed.getValue();
                    } else {
                        Logger.get().error("上一次还款状态逾期或者正在还款中,状态异常");
                    }
                }

                //set上一次借款状态
                consisForm.setLastLoanIsPrepaid(lastLoanStatus);
                if (paymentDate == null) {
                    Logger.get().error("上一次还款状态逾期或者正在还款中,状态异常");
                } else {
                    try {
                        lastApplicationInterval = daysBetween(paymentDate, new Date());
                    } catch (ParseException e) {
                        Logger.get().error("daysBetween function error", e);
                    }
                }
                consisForm.setLastApplicationInterval(lastApplicationInterval);
            }
        }*/

        return params;
    }

    @Override
    public String createBusinessNo() {
        // TODO Auto-generated method stub
        return null;
    }

    private String filterIdCardIdentificationResult(String rawData) {
        if (rawData.contains(CheckNameIDCardResult.Match.getValue())
            && !rawData.contains(CheckNameIDCardResult.NotMatch.getValue())) {
            return CheckNameIDCardResult.Match.getValue();
        } else if (rawData.contains(CheckNameIDCardResult.NotMatch.getValue())) {
            return CheckNameIDCardResult.NotMatch.getValue();
        }
        List<Object> values = EnumUtils.getValues(CheckNameIDCardResult.class);
        if (values != null) {
            String value = null;
            for (Object item : values) {
                value = item.toString();
                if (rawData.contains(value))
                    return value;
            }
        }
        return rawData;
    }

    private String getWeekOfDayByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        if (Calendar.MONDAY == weekDay) {
            return "1";
        }
        if (Calendar.TUESDAY == weekDay) {
            return "2";
        }
        if (Calendar.WEDNESDAY == weekDay) {
            return "3";
        }
        if (Calendar.THURSDAY == weekDay) {
            return "4";
        }
        if (Calendar.FRIDAY == weekDay) {
            return "5";
        }
        if (Calendar.SATURDAY == weekDay) {
            return "6";
        }

        return "7";
    }

    private String getHourOfDayByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY) + "";
    }

    //根据Map类型还款记录求最大预期天数
    private int getLastAppMaxDelayedDays(Map<Integer, RepaymentTableResult> map) {
        if (map == null || map.size() == 0) {
            Logger.get().error("得到空的还款计划");
            return -1;
        }

        Iterator<RepaymentTableResult> iterator = map.values().iterator();
        int max = 0;

        while (iterator.hasNext()) {
            RepaymentTableResult next = iterator.next();
            PaymentDetail principal = next.getPrincipal();
            Date dueDate = principal.getDueDate();
            Date payDate = principal.getPayDate();
            int temp = 0;
            try {
                if (dueDate == null || payDate == null) {
                    continue;
                }
                temp = daysBetween(dueDate, payDate);
            } catch (ParseException e) {
                Logger.get().error("daysBetween function error");
            }
            if (temp > max) {
                max = temp;
            }
        }

        return max;

    }

    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
