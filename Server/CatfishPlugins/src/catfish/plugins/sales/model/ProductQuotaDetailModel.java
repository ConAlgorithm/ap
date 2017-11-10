package catfish.plugins.sales.model;

public class ProductQuotaDetailModel {

  public String productId;

  public String d3Id;

  public int quota;

  public ProductQuotaDetailModel() {};

  public ProductQuotaDetailModel(String productId, String d3Id, int quota) {
    super();
    this.productId = productId;
    this.d3Id = d3Id;
    this.quota = quota;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getD3Id() {
    return d3Id;
  }

  public void setD3Id(String d3Id) {
    this.d3Id = d3Id;
  }

  public int getQuota() {
    return quota;
  }

  public void setQuota(int quota) {
    this.quota = quota;
  }

}
