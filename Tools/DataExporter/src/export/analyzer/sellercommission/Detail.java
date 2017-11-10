package export.analyzer.sellercommission;

public class Detail{
	public String Id;
	public String ProductId;
	public String CommissionId;
	public String Name;
	public String Description;
	public int max;
	public int min;
	public double rate;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	public String getCommissionId() {
		return CommissionId;
	}
	public void setCommissionId(String commissionId) {
		CommissionId = commissionId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
}