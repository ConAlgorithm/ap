package thirdparty.jxl.response.reportdata;

import com.google.gson.annotations.SerializedName;

public class ContactRegionItem {

	/*
		地区名称	region_loc	string	联系人的号码归属地
		号码数量	region_uniq_num_cnt	int	去重后的联系人号码数量
		电话呼入	region_call_in_cnt	int	电话呼入次数
		电话呼出	region_call_out_cnt	int	电话呼出次数
		电话呼入时间	region_call_in_time	int	电话呼入总时间（秒）
		电话呼出时间	region_call_out_time	int	电话呼出总时间（秒）
		平均电话呼入时间	region_avg_call_in_time	float	平均电话呼入时间（秒）
		平均电话呼出时间	region_avg_call_out_time	float	平均电话呼出时间（秒）
		电话呼入次数百分比	region_call_in_cnt_pct	float	电话呼入次数百分比
		电话呼出次数百分比	region_call_out_cnt_pct	float	电话呼出次数百分比
		电话呼入时间百分比	region_call_in_time_pct	float	电话呼入时间百分比
		电话呼出时间百分比	region_call_out_time_pct	float	电话呼出时间百分比
	*/
	//eg: 北京
	@SerializedName("region_loc")
	private String regionLocation;
	
	@SerializedName("region_uniq_num_cnt")
	private int regionUniqNumCount;
	
	@SerializedName("region_call_in_cnt")
	private int regionCallinCount;
	
	@SerializedName("region_call_out_cnt")
	private int regionCalloutCount;
	
	@SerializedName("region_call_in_time")
	private int regionCallinTime;
	
	@SerializedName("region_call_out_time")
	private int regionCalloutTime;
	
	@SerializedName("region_avg_call_in_time")
	private float regionAvgCallinTime;
	
	@SerializedName("region_avg_call_out_time")
	private float regionAvgCalloutTime;
	
	@SerializedName("region_call_in_cnt_pct")
	private float regionCallinCountPercent;
	
	@SerializedName("region_call_out_cnt_pct")
	private float regionCalloutCountPercent;
	
	@SerializedName("region_call_in_time_pct")
	private float regionCallinTimePercent;
	
	@SerializedName("region_call_out_time_pct")
	private float regionCalloutTimePercent;

	public String getRegionLocation() {
		return regionLocation;
	}

	public void setRegionLocation(String regionLocation) {
		this.regionLocation = regionLocation;
	}

	public int getRegionUniqNumCount() {
		return regionUniqNumCount;
	}

	public void setRegionUniqNumCount(int regionUniqNumCount) {
		this.regionUniqNumCount = regionUniqNumCount;
	}

	public int getRegionCallinCount() {
		return regionCallinCount;
	}

	public void setRegionCallinCount(int regionCallinCount) {
		this.regionCallinCount = regionCallinCount;
	}

	public int getRegionCalloutCount() {
		return regionCalloutCount;
	}

	public void setRegionCalloutCount(int regionCalloutCount) {
		this.regionCalloutCount = regionCalloutCount;
	}

	public int getRegionCallinTime() {
		return regionCallinTime;
	}

	public void setRegionCallinTime(int regionCallinTime) {
		this.regionCallinTime = regionCallinTime;
	}

	public int getRegionCalloutTime() {
		return regionCalloutTime;
	}

	public void setRegionCalloutTime(int regionCalloutTime) {
		this.regionCalloutTime = regionCalloutTime;
	}

	public float getRegionAvgCallinTime() {
		return regionAvgCallinTime;
	}

	public void setRegionAvgCallinTime(float regionAvgCallinTime) {
		this.regionAvgCallinTime = regionAvgCallinTime;
	}

	public float getRegionAvgCalloutTime() {
		return regionAvgCalloutTime;
	}

	public void setRegionAvgCalloutTime(float regionAvgCalloutTime) {
		this.regionAvgCalloutTime = regionAvgCalloutTime;
	}

	public float getRegionCallinCountPercent() {
		return regionCallinCountPercent;
	}

	public void setRegionCallinCountPercent(float regionCallinCountPercent) {
		this.regionCallinCountPercent = regionCallinCountPercent;
	}

	public float getRegionCalloutCountPercent() {
		return regionCalloutCountPercent;
	}

	public void setRegionCalloutCountPercent(float regionCalloutCountPercent) {
		this.regionCalloutCountPercent = regionCalloutCountPercent;
	}

	public float getRegionCallinTimePercent() {
		return regionCallinTimePercent;
	}

	public void setRegionCallinTimePercent(float regionCallinTimePercent) {
		this.regionCallinTimePercent = regionCallinTimePercent;
	}

	public float getRegionCalloutTimePercent() {
		return regionCalloutTimePercent;
	}

	public void setRegionCalloutTimePercent(float regionCalloutTimePercent) {
		this.regionCalloutTimePercent = regionCalloutTimePercent;
	}
}
