package jma.thirdpartyservices.zcxy;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.NONE)
public class ZCXYResult {
  @XmlAccessorType(XmlAccessType.NONE)
  public static class Message {
    @XmlElement
    private int status;

    @XmlElement
    private String value;

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }

  @XmlAccessorType(XmlAccessType.NONE)
  public static class PoliceCheckInfo {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String id;

    @XmlElement
    private Message message;

    @XmlElement
    private int compStatus;

    @XmlElement
    private String compResult;

    @XmlElement
    private String checkPhoto;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public Message getMessage() {
      return message;
    }

    public void setMessage(Message message) {
      this.message = message;
    }

    public int getCompStatus() {
      return compStatus;
    }

    public void setCompStatus(int compStatus) {
      this.compStatus = compStatus;
    }

    public String getCompResult() {
      return compResult;
    }

    public void setCompResult(String compResult) {
      this.compResult = compResult;
    }

    public String getCheckPhoto() {
      return checkPhoto;
    }

    public void setCheckPhoto(String checkPhoto) {
      this.checkPhoto = checkPhoto;
    }
  }

  @XmlAccessorType(XmlAccessType.NONE)
  public static class PoliceCheckInfoList {
    @XmlElement
    private List<PoliceCheckInfo> policeCheckInfo;

    public List<PoliceCheckInfo> getPoliceCheckInfo() {
      return policeCheckInfo;
    }

    public void setPoliceCheckInfo(List<PoliceCheckInfo> policeCheckInfo) {
      this.policeCheckInfo = policeCheckInfo;
    }
  }

  @XmlElement
  private Message message;

  @XmlElement
  private PoliceCheckInfoList policeCheckInfos;

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public PoliceCheckInfoList getPoliceCheckInfos() {
    return policeCheckInfos;
  }

  public void setPoliceCheckInfos(PoliceCheckInfoList policeCheckInfos) {
    this.policeCheckInfos = policeCheckInfos;
  }

}
