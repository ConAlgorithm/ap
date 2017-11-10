package thirdparty.jxl.response.reportdata;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CollectionContactItem {

	/*
	 *  联系人姓名	contact_name	string	联系人姓名
		最早出现时间	begin_date	datetime	和联系人最早联系的时间
		最晚出现时间	end_date	datetime	和联系人最晚联系的时间
		电商送货总数	total_count	int
		电商送货总金额	total_amount float
		呼叫信息统计	call_info	list	【呼叫信息】表
	 * */
	
	@SerializedName("contact_name")
	private String contactName;
	
	@SerializedName("begin_date")
	private Date beginDate;
	
	@SerializedName("end_date")
	private Date endDate;
	
	@SerializedName("total_count")
	private int totalCount;
	
	@SerializedName("total_amount")
	private float totalAmount;
	
	@SerializedName("call_info")
	private List<CallInfoItem> callInfoList;
}
