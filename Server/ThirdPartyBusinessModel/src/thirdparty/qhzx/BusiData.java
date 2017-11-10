package thirdparty.qhzx;

import java.util.List;

public class BusiData {

  private  String batchNo;
  private List<Record> records;

  public String getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }

  public List<Record> getRecords() {
    return records;
  }

  public void setRecords(List<Record> records) {
    this.records = records;
  }
}
