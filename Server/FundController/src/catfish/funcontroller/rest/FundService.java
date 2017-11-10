package catfish.funcontroller.rest;


import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientApi;
import catfish.framework.IListener;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;
import catfish.fundcontroller.Configuration;
import catfish.fundcontroller.axdl.AXDLFund;
import catfish.fundcontroller.zrb.ZRBFund;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import grasscarp.application.model.POSApplication;
import grasscarp.product.model.ProductFund;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class FundService implements IListener<HttpData> {

    private static final String POST = "post";
    private static final String GET = "get";
    private static final String APPLICATION_SERVICE = "/application/";
    private static final String PRODUCT_SERVICE = "/product/%s/fund";
    @SuppressWarnings("serial")
    private static final Map<String, String> fundTagMap = new HashMap<String, String>() {
        {
            put("JMBOX", StartupConfig.get("catfish.jimubox.fundTag"));
            put("FOTIC", StartupConfig.get("catfish.fotic.fundTag"));
            put("LTZ", StartupConfig.get("catfish.ltz.fundTag"));
            put("AXDL", StartupConfig.get("catfish.axdl.fundTag"));  // 增加安心得利
        }
    };

    // P2P-yiji付  支持银行
    private static final List<String> bankList = Arrays.asList(
            "农业银行",
            "中国银行",
            "建设银行",
            "工商银行",
            "光大银行",
            "兴业银行",
            "邮政储蓄银行"
    );
    private IServiceProvider serviceProvider;

    public FundService(IServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void onMessage(String representation, HttpData data) {

        String method = data.getMethod().toLowerCase();

        if (POST.equals(method)) {

            String json = data.getRequestData();
            if (!StringUtils.isNullOrWhiteSpaces(json)) {
                Map<String, String> requestData = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
                String appId = requestData.get("AppId");

                if (!StringUtils.isNullOrWhiteSpaces(appId)) {
                    try {
                        String responseData = getFundTagByAppId(appId);
                        if (responseData == null) {
                            responseData = "";
                        }
                        data.setResponseData(String.format("{ \"Tag\" : \"%s\" }", responseData));
                        Logger.get().info(String.format("appId: %s , get FundTag %s", appId, responseData));
                    } catch (Exception ex) {
                        data.setResponseData(String.format("{ \"Tag\" : \"%s\" }", ""));
                        Logger.get().warn(String.format("appId: %s get FundTag Error!", appId), ex);
                    }
                } else {
                    Logger.get().warn("[FundService] Post data format Error ");
                    data.setResponseData("");
                }

            } else {
                Logger.get().warn("[FundService] Post data is null ");
                data.setResponseData("");
            }
        } else {
            Logger.get().warn("[FundService] Not supported method in status " + method);
            data.setResponseData("");
        }
    }

    // 产品与funds之间的关系是: ?
    //
    public ProductFund[] getProductFundsByProductId(String productId) {
        String url = String.format(Configuration.getGS_URL() + PRODUCT_SERVICE, productId);
        Logger.get().info("Calling product service url: " + url);
        try {
            ProductFund[] object = HttpClientApi.getGson(url, ProductFund[].class);
            return object;
        } catch (Exception e) {
            Logger.get().info("Exception when invoking product service:" + e);
        }
        return null;
    }

    // POSApplication  --> InstallmentApplicationObject ????
    public POSApplication getApplicationById(String appId) {
        String url = Configuration.getGS_URL() + APPLICATION_SERVICE + appId;
        Logger.get().info("Calling application service url: " + url);

        try {
            POSApplication application = HttpClientApi.getGson(url, POSApplication.class);
            return application;
        } catch (Exception e) {
            Logger.get().info("Exception when invoking application service:" + e);
            return null;
        }
    }

    public String getFundTagByAppId(String appId) {
        InstallmentApplicationObject application = InstallmentApplicationDao.getApplicationInfoById(appId);
        if (application == null) {
            Logger.get().error("Application info is error!");
            return null;
        }

        // ? App渠道
        if (application.InstalmentChannel == InstalmentChannel.App.getValue()) {
            return setPOSFund(appId);
        }
        // ??
        else {
            return setPDLFund(application.Id);
        }
    }

    public String setPOSFund(String appId) {

        List<?> dsfInfo = getDSFBankInfoByAppId(appId);
        if (dsfInfo == null) {
            Logger.get().warn("DSFBank info is error!");
            return null;
        }

        POSApplication posApp = getApplicationById(appId);
        if (posApp == null) {
            Logger.get().warn("Application info is error!");
            return null;
        }

        // 依据产品ID获取产品支持的Fund列表, 列表按优先级排练好了!!!
        ProductFund[] pfList = getProductFundsByProductId(posApp.getProductId());

        ZRBFund zrbFund = new ZRBFund();
        AXDLFund axdlFund = new AXDLFund();

        for (int i = 0; i < pfList.length; i++) {

            ProductFund pf = pfList[i];
            String fundId = null;
            String fundTag = pf.getFundTag();

            if (pf.getFundTag().equals(fundTagMap.get("FOTIC"))) {
                fundId = getFundIdByTag(fundTag);
            }

            //
            else if (isP2PPlatform(posApp.getRepayments(), dsfInfo)) {

                if (pf.getFundTag().equals(fundTagMap.get("LTZ"))) {
                    fundId = isLTZFund(posApp.getPrincipal());
                }
                else if (pf.getFundTag().equals(axdlFund.getFundTag()) &&
                        axdlFund.isAvailable(posApp.getPrincipal())){
                    fundId = axdlFund.getFund().Id;
                }
                else if (pf.getFundTag().equals(fundTagMap.get("JMBOX"))) {
                    fundId = isJMBoxFund(posApp.getProductName(), posApp.getPrincipal());
                }
                else if (pf.getFundTag().equals(zrbFund.getFundTag())
                        && zrbFund.isAvailable(posApp.getPrincipal())) {
                    fundId = zrbFund.getFund().Id;
                }
            }

            //
            if (fundId != null) {
                setFundTagToApplication(posApp.getId(), fundId);
                return pf.getFundTag();
            }
            continue;
        }
        return null;
    }

    // PDL只能走外贸信托
    public String setPDLFund(String appId) {
        String fundTag = fundTagMap.get("FOTIC");
        String fundId = getFundIdByTag(fundTag);
        if (fundId != null) {
            setFundTagToApplication(appId, fundId);
            return fundTag;
        }
        return null;
    }

    public Boolean isP2PPlatform(int repayments, List<?> dsfInfo) {
        if (repayments == StartupConfig.getAsInt("catifhs.p2p.Repayments")     // p2p只支持12期
//				&& Integer.parseInt(dsfInfo.get(2).toString()) == PaymentPlatformType.Cpcn.getValue()
                && !dsfInfo.get(3).toString().equals("0")    // 支持代收付,
                && bankList.contains(dsfInfo.get(0))) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    public String isJMBoxFund(String productName, BigDecimal principal) {
        if (StartupConfig.getAsBoolean("catfish.jimubox.enable") && !productName.equals("其他")) {
            String fundId = getFundIdByTag(StartupConfig.get("catfish.jimubox.fundTag"));
            if (fundId == null) {
                Logger.get().warn("Can't find JMBOX FundTag!");
                return null;
            }

            Date end = new java.util.Date();
            Date begin = new java.util.Date(end.getYear(), end.getMonth(), end.getDate());

            if (isWorkTimeForJMBox(begin)) {
                if (principal.intValue()
                        <= StartupConfig.getAsInt("catfish.jimubox.PrincipalLimit")) {

                    Integer loannedMoney = getFundAccountBalance(fundId, begin, end);
                    if (principal.intValue() + loannedMoney
                            <= Integer.parseInt(StartupConfig.get("catfish.jimubox.Amount"))) {
                        return fundId;
                    }
                }
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public String isLTZFund(BigDecimal principal) {

        if (StartupConfig.getAsBoolean("catfish.ltz.enable")) {
            String fundId = getFundIdByTag(StartupConfig.get("catfish.ltz.fundTag"));
            if (fundId == null) {
                Logger.get().warn("Can't find LTZ FundTag!");
                return null;
            }

            Date end = new java.util.Date();
            int diffDay;

            if (end.getDay() == 0) {
                diffDay = 7 - StartupConfig.getAsInt("catfish.ltz.SettlementDayOfWeek");
            } else {
                diffDay = end.getDay() - StartupConfig.getAsInt("catfish.ltz.SettlementDayOfWeek");
            }

            if (diffDay < 0) {
                diffDay += 7;
            }

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, -diffDay);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date begin = cal.getTime();

            Integer loanedMoney = getFundAccountBalance(fundId, begin, end);
            if ((loanedMoney + principal.intValue())
                    <= StartupConfig.getAsInt("catfish.ltz.Amount")) {
                return fundId;
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    private Boolean isWorkTimeForJMBox(Date date) {
        String[] dayStr = StartupConfig.get("catfish.jimubox.WorkDay").split(",");
        for (String day : dayStr) {
            if (date.getDay() == Integer.parseInt(day)) {
                return true;
            }
        }
        return false;
    }

    // ??
    private List<?> getDSFBankInfoByAppId(String appId) {
        String sql = "select dsf.* from PaymentApplicationObjects pa "
                + "join PaymentObjects p on pa.PaymentId = p.Id "
                + "join DetailedBankObjects db on p.BankName = db.BankName "
                + "join DSFBankObjects dsf on db.BankCategoryName = dsf.BankName "
                + "where pa.ApplicationId = :appId "
                + "order by pa.LastModified desc ";
        return DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("appId", appId), new RowMapper<List<?>>() {
            @Override
            public List<?> mapRow(ResultSet resultSet, int arg1)
                    throws SQLException {
                if (resultSet != null)
                    return Arrays.asList(
                            resultSet.getString("BankName"),
                            resultSet.getString("WithholdingBankID"),
                            resultSet.getString("WithholdingPreferredPlatform"),
                            resultSet.getString("CpcnPayingBankID"));
                return null;
            }
        });
    }

    // 这里代价有点高?
    private Integer getFundAccountBalance(String fundId, Date begin, Date end) {
        String sql = "select sum(a.Principal) from InstallmentApplicationObjects a "
                + "where a.MoneyTransferredOn is not null "
                + "and a.DateAdded > :begin and a.DateAdded < :end "
                + "and a.FundId = :fundId ";
        return DatabaseApi.querySingleInteger(sql, CollectionUtils.mapOf("begin", begin, "end", end, "fundId", fundId));
    }

    private String getFundIdByTag(String fundTag) {
        String sql = "select * from FundObjects where FundTag = :tag ";
        return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("tag", fundTag));
    }

    private Integer setFundTagToApplication(String appId, String fundId) {
        String sql = "UPDATE InstallmentApplicationObjects SET FundId = :fundId, LastModified = :LastModified where Id = :appId";
        return DatabaseApi.updateTable(sql, CollectionUtils.mapOf(
                "appId", appId, "fundId", fundId, "LastModified", new java.util.Date()));
    }
}
