package catfish.plugins.redpack;

public class RedPackArea {
	private String ProvinceId;
	
	private String CityId;
	
	private String BlockId;
	
	public RedPackArea() {
		ProvinceId = null;
		CityId = null;
		BlockId = null;
	}

	public String getProvinceId() {
		return ProvinceId;
	}

	public void setProvinceId(String provinceId) {
		ProvinceId = provinceId;
	}

	public String getCityId() {
		return CityId;
	}

	public void setCityId(String cityId) {
		CityId = cityId;
	}

	public String getBlockId() {
		return BlockId;
	}

	public void setBlockId(String blockId) {
		BlockId = blockId;
	}

}
