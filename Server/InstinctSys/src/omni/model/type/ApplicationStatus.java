package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum ApplicationStatus implements CatfishEnum<Integer>
{

	@Description(text = "无")
	None(-99),
	
	@Description(text =  "申请已取消") 
    Canceled(-1),

    //Default 
    @Description(text = "开始申请") 
    Started(0),

    //User submitted but not approved
    @Description(text = "申请已提交") 
    Submited(10),

    @Description(text = "预批准") 
    PreApproved(23),

    @Description(text = "所有文件已上传") 
    AllFileSubmited(29),

    //Approved
    @Description(text = "正式批准") 
    Approved(30),

    //For Paydayloan
    @Description(text = "现金贷产品已提交") 
    PaydayLoanProductSubmitted(33), 

    //For Paydayloan    
    @Description(text = "放款申请已提交") 
    MoneyTransferSubmitted(34), 
    
    //添加商户批准放款的状态
    @Description(text = "商户批准")
    MerchantApproved(35),
    
    //Rejected
    @Description(text = "已拒绝") 
    Rejected(40),
    
    //User has submitted all the neccessary materials. application completed.
    @Description(text = "还款中") 
    Completed(100),

    //delayed.
    @Description(text = "已逾期") 
    Delayed(200), 

    //user replay finished. the case is closed.
    @Description(text = "分期已结束") 
    Closed(500),

    @Description(text = "提前还款") 
    ClosedInAdvanced(600);
    
    private int value;
    
    private static final Map<Integer, ApplicationStatus> typesByValue = new HashMap<Integer, ApplicationStatus>();

    static {
        for (ApplicationStatus type : ApplicationStatus.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static ApplicationStatus forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	ApplicationStatus(int val)
	{
		this.value = val;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
