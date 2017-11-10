package jma.models;

/**
 * 泰融小额信贷接口返回
 * @author yeyb
 * @date 2017年8月10日
 */
public class SmallCreditResponseModel  {

    private int resultCode;
    
    private boolean success;
    
    private String resultMsg;
    
    private SmallCreditResultData resultData;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public SmallCreditResultData getResultData() {
        return resultData;
    }

    public void setResultData(SmallCreditResultData resultData) {
        this.resultData = resultData;
    }
    
}
