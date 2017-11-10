package catfish.notification.messagegeneration;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.URIBuilder;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SettlementApi {

    private static final String URL_PAY_INFO = "/settlement/pay-info";
    private static final String URL_PRODUCT  = "/settlement/product";

    public static PayInfoResponse getPayInfo(Map<String, Object> params) {
        URIBuilder builder = new URIBuilder()
            .setPath(Configuration.getSettlementUrlPrefix() + URL_PAY_INFO);
        for (Entry<String, Object> param : params.entrySet()) {
            builder.setParameter(param.getKey(), param.getValue().toString());
        }

        String literal = HttpClientApi.get(builder.toString());
        Logger.get().info("Got repayment response: " + literal);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        PayInfoResponse response = gson.fromJson(literal, PayInfoResponse.class);
        if (response.error != null) {
            throw new RuntimeException(String.format("RepaymentApi error: %d - %s",
                response.error.get("code"), response.error.get("message")));
        }
        return response;
    }

    public static ProductResponse getProductInfo(Map<String, Object> params) {
        URIBuilder builder = new URIBuilder()
            .setPath(Configuration.getSettlementUrlPrefix() + URL_PRODUCT);
        for (Entry<String, Object> param : params.entrySet()) {
            builder.setParameter(param.getKey(), param.getValue().toString());
        }

        String literal = HttpClientApi.get(builder.toString());
        Logger.get().info("getProductInfo response: " + literal);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ProductResponse response = gson.fromJson(literal, ProductResponse.class);
        if (response.error != null) {
            throw new RuntimeException(String.format("RepaymentApi error: %d - %s",
                response.error.get("code"), response.error.get("message")));
        }
        return response;
    }

    public static class PayInfoResponse {
        private BigDecimal          overDueAmount;
        private BigDecimal          advancedPayAmount;
        private BigDecimal          curRepaymentPayAmount;
        private BigDecimal          overdueDays;
        private Map<String, String> error;

        public BigDecimal getOverDueAmount() {
            return overDueAmount;
        }

        public void setOverDueAmount(BigDecimal overDueAmount) {
            this.overDueAmount = overDueAmount;
        }

        public BigDecimal getAdvancedPayAmount() {
            return advancedPayAmount;
        }

        public void setAdvancedPayAmount(BigDecimal advancedPayAmount) {
            this.advancedPayAmount = advancedPayAmount;
        }

        public BigDecimal getCurRepaymentPayAmount() {
            return curRepaymentPayAmount;
        }

        public void setCurRepaymentPayAmount(BigDecimal curRepaymentPayAmount) {
            this.curRepaymentPayAmount = curRepaymentPayAmount;
        }

        public BigDecimal getOverdueDays() {
            return overdueDays;
        }

        public void setOverdueDays(BigDecimal overdueDays) {
            this.overdueDays = overdueDays;
        }

        public Map<String, String> getError() {
            return error;
        }

        public void setError(Map<String, String> error) {
            this.error = error;
        }
    }

    public static class ProductResponse {

        private Interest            interest;

        private Fee                 fee;

        private int                 hesitateDays;

        private Map<String, String> error;

        public Interest getInterest() {
            return interest;
        }

        public void setInterest(Interest interest) {
            this.interest = interest;
        }

        public Fee getFee() {
            return fee;
        }

        public void setFee(Fee fee) {
            this.fee = fee;
        }

        public int getHesitateDays() {
            return hesitateDays;
        }

        public void setHesitateDays(int hesitateDays) {
            this.hesitateDays = hesitateDays;
        }

        public Map<String, String> getError() {
            return error;
        }

        public void setError(Map<String, String> error) {
            this.error = error;
        }

        public static class Interest {

            private BigDecimal rate;

            private String     bearingType;

            public BigDecimal getRate() {
                return rate;
            }

            public void setRate(BigDecimal rate) {
                this.rate = rate;
            }

            public String getBearingType() {
                return bearingType;
            }

            public void setBearingType(String bearingType) {
                this.bearingType = bearingType;
            }
        }

        public static class Fee {

            private ClientFee   clientFee;

            private BigDecimal  techFeeRate;

            private BigDecimal  valueFeeRate;

            private AdvancedFee advancedFee;

            private BigDecimal  penaltyFee;

            private int         scale;

            public ClientFee getClientFee() {
                return clientFee;
            }

            public void setClientFee(ClientFee clientFee) {
                this.clientFee = clientFee;
            }

            public BigDecimal getTechFeeRate() {
                return techFeeRate;
            }

            public void setTechFeeRate(BigDecimal techFeeRate) {
                this.techFeeRate = techFeeRate;
            }

            public BigDecimal getValueFeeRate() {
                return valueFeeRate;
            }

            public void setValueFeeRate(BigDecimal valueFeeRate) {
                this.valueFeeRate = valueFeeRate;
            }

            public AdvancedFee getAdvancedFee() {
                return advancedFee;
            }

            public void setAdvancedFee(AdvancedFee advancedFee) {
                this.advancedFee = advancedFee;
            }

            public BigDecimal getPenaltyFee() {
                return penaltyFee;
            }

            public void setPenaltyFee(BigDecimal penaltyFee) {
                this.penaltyFee = penaltyFee;
            }

            public int getScale() {
                return scale;
            }

            public void setScale(int scale) {
                this.scale = scale;
            }

            public static class ClientFee {

                private BigDecimal rate;

                private int        nper;

                public BigDecimal getRate() {
                    return rate;
                }

                public void setRate(BigDecimal rate) {
                    this.rate = rate;
                }

                public int getNper() {
                    return nper;
                }

                public void setNper(int nper) {
                    this.nper = nper;
                }
            }

            public static class AdvancedFee {
                /** 提前还款服务费按固定值计算 */
                private BigDecimal value;
                /** 提前还款服务费按比例计算 */
                private BigDecimal rate;
                /** 提前还款服务费的收取规则 */
                private String     strategy;

                public BigDecimal getValue() {
                    return value;
                }

                public void setValue(BigDecimal value) {
                    this.value = value;
                }

                public BigDecimal getRate() {
                    return rate;
                }

                public void setRate(BigDecimal rate) {
                    this.rate = rate;
                }

                public String getStrategy() {
                    return strategy;
                }

                public void setStrategy(String strategy) {
                    this.strategy = strategy;
                }
            }
        }
    }
}
