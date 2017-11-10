package thirdparty.td;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailResponse {

    private String reasonCode;

    @SerializedName("策略列表")
    private List<PolicyModel> policyList;

    private boolean success;

    private String rawResponse;

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public List<PolicyModel> getPolicyList() {
        return policyList;
    }

    public void setPolicyList(List<PolicyModel> policyList) {
        this.policyList = policyList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
