package omni.database.catfish.object;

public class OverDueInfoModel {
    private String appId;
    private String userId;
    private int fpd1;
    private int fpd7;
    private int fpd30;
    private int spd30;
    private int tpd30;
    private int qpd30;
    private int m3;

    public String getAppId()
    {
      return this.appId;
    }

    public void setAppId(String appId) {
      this.appId = appId;
    }

    public String getUserId() {
      return this.userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }

    public int getFpd1() {
      return this.fpd1;
    }

    public void setFpd1(int fpd1) {
      this.fpd1 = fpd1;
    }

    public int getFpd7() {
      return this.fpd7;
    }

    public void setFpd7(int fpd7) {
      this.fpd7 = fpd7;
    }

    public int getFpd30() {
      return this.fpd30;
    }

    public void setFpd30(int fpd30) {
      this.fpd30 = fpd30;
    }

    public int getSpd30() {
      return this.spd30;
    }

    public void setSpd30(int spd30) {
      this.spd30 = spd30;
    }

    public int getTpd30() {
      return this.tpd30;
    }

    public void setTpd30(int tpd30) {
      this.tpd30 = tpd30;
    }

    public int getQpd30() {
      return this.qpd30;
    }

    public void setQpd30(int qpd30) {
      this.qpd30 = qpd30;
    }

    public int getM3() {
      return this.m3;
    }

    public void setM3(int m3) {
      this.m3 = m3;
    }
}
