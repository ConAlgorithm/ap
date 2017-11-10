package engine.rule.test.validator;

public interface ValidatorBase {

	//默认空
	public static final String NULL = "NULL";
	//最终决策结果
	public static final String DECISION = "DECISION";
	//分层策略代号
	public static final String SEGMENTATIONCODE = "SEGMENTATIONCODE";
	//拒绝原因列表
	public static final String REJREASONSET = "REGREASONSET";
	//触发欺诈标识列表
	public static final String FRAUDFLAGLIST = "FRAUDFLAGLIST";
	//重传标志位的和
	public static final String REUPLOAD = "REUPLOAD";
	//执行的随机数序列
	public static final String RANDOMNUMLIST = "RANDOMNUMLIST";
	//是否需要交易监控
    public static final String TMREQUIRED = "TMREQUIRED";
    //策略代号
    public static final String STRATEGYCODE = "STRATEGYCODE";
    //冷冻天数
    public static final String FROZENDAYS = "FROZENDAYS";
    //冷冻原因
    public static final String FROZENREASON = "FROZENREASON";
    //是否强签
    public static final String COMPULSORYSIGNING = "COMPULSORYSIGNING";
}
