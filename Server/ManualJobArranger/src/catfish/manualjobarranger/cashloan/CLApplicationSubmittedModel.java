package catfish.manualjobarranger.cashloan;

import java.util.Date;

public class CLApplicationSubmittedModel {

  private String appId;

  private Date submitDate;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public Date getSubmitDate() {
    return submitDate;
  }

  public void setSubmitDate(Date submitDate) {
    this.submitDate = submitDate;
  }

}
