package thirdparty.jxl.response.reportdata;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class CallInfoItem {

	/*
	 *  电话号码	phone_num	string	联系人的电话号码
		号码归属地	phone_num_loc	string	号码的归属地
		呼叫次数	call_cnt	int	呼叫次数
		呼叫时长	call_len	float	呼叫时间，单位是分钟
		呼出次数	call_out_cnt	int	主叫次数
		呼入次数	call_in_cnt	int	被叫次数
		短信条数	sms_cnt	int	发送和接收短信次数
		最早沟通时间	trans_start	datetime	在呼叫记录里面最早出现的时间
		最早沟通时间	trans_end	datetime	在呼叫记录里面最晚出现的时间
	 * */
	
	@SerializedName("phone_num")
	private String phoneNumber;
	
	@SerializedName("phone_num_loc")
	private String phoneNumberLocation;
	
	@SerializedName("call_cnt")
	private int callCount;
	
	@SerializedName("call_len")
	private float callLength;
	
	@SerializedName("call_out_cnt")
	private int calloutCount;
	
	@SerializedName("call_int_cnt")
	private int callintCount;
	
	@SerializedName("sms_cnt")
	private int smsCount;
	
	@SerializedName("trans_start")
	private Date transStartTime;
	
	@SerializedName("trans_end")
	private Date transEndTime;
	
}
