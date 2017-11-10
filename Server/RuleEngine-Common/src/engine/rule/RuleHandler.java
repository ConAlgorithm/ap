package engine.rule;

import java.util.HashMap;
import java.util.Map;

import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.queue.QueueMessager;
import engine.rule.record.LogWriter;
import engine.rule.record.RecordWriter;

public abstract class RuleHandler<T> {
	
	protected RecordWriter writer = new LogWriter();
	protected String[] recordHeader;

	public abstract T handle(QueueMessager message);
	
	public static final Map<Integer, String> ResultStatusMap = new HashMap<>();
	
	static {
		ResultStatusMap.put(RuleEngineDecisionResult.Approved.getValue(), JobStatus.Approved);
		ResultStatusMap.put(RuleEngineDecisionResult.Rejected.getValue(), JobStatus.Rejected);
		ResultStatusMap.put(RuleEngineDecisionResult.Canceled.getValue(), JobStatus.Canceled);
		ResultStatusMap.put(RuleEngineDecisionResult.RecheckingRequired.getValue(), JobStatus.RecheckingRequired);
	}
}
