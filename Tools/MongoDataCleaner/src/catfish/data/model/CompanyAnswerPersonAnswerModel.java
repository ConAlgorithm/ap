package catfish.data.model;

public class CompanyAnswerPersonAnswerModel {
    
    private String appId;
    
    private int status;
    
    private Integer X_CheckCompanyAnswerPersonAnswerResult;
    
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getX_CheckCompanyAnswerPersonAnswerResult() {
        return X_CheckCompanyAnswerPersonAnswerResult;
    }

    public void setX_CheckCompanyAnswerPersonAnswerResult(
            Integer x_CheckCompanyAnswerPersonAnswerResult) {
        X_CheckCompanyAnswerPersonAnswerResult = x_CheckCompanyAnswerPersonAnswerResult;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
