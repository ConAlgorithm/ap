package jma;

public class QQDerivativeVariable {
  private String QQNumber;
  private int qZone = -1;
  private int intimacyScore = -1;
  private String realName = null;
  private String smartName = null;
  private String bitmap = null;
  private String AvataUrl = null;
  private boolean isExist = false;
  private String nickName = null;

  public QQDerivativeVariable(String _QQNumber) {
    this.QQNumber = _QQNumber;
  }

  public String getQQNumber() {
    return QQNumber;
  }

  public void setQQNumber(String qQNumber) {
    QQNumber = qQNumber;
  }

  public int getqZone() {
    return qZone;
  }

  public void setqZone(int qZone) {
    this.qZone = qZone;
  }

  public int getIntimacyScore() {
    return intimacyScore;
  }

  public void setIntimacyScore(int intimacyScore) {
    this.intimacyScore = intimacyScore;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getSmartName() {
    return smartName;
  }

  public void setSmartName(String smartName) {
    this.smartName = smartName;
  }

  public String getBitmap() {
    return bitmap;
  }

  public void setBitmap(String bitmap) {
    this.bitmap = bitmap;
  }

  public String getAvataUrl() {
    return AvataUrl;
  }

  public void setAvataUrl(String avataUrl) {
    AvataUrl = avataUrl;
  }

  public boolean isExist() {
    return isExist;
  }

  public void setExist(boolean isExist) {
    this.isExist = isExist;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
}
