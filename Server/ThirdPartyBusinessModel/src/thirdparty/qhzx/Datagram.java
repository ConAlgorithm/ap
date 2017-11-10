package thirdparty.qhzx;

public class Datagram {
  private Header header;
  private BusiData rawBusiData;
  private String busiData;
  private SecurityInfo securityInfo;

  public Header getHeader() {
    return header;
  }

  public void setHeader(Header header) {
    this.header = header;
  }

  public BusiData getRawBusiData() {
    return rawBusiData;
  }

  public void setRawBusiData(BusiData rawBusiData) {
    this.rawBusiData = rawBusiData;
  }

  public String getBusiData() {
    return busiData;
  }

  public void setBusiData(String busiData) {
    this.busiData = busiData;
  }

  public SecurityInfo getSecurityInfo() {
    return securityInfo;
  }

  public void setSecurityInfo(SecurityInfo securityInfo) {
    this.securityInfo = securityInfo;
  }
}
