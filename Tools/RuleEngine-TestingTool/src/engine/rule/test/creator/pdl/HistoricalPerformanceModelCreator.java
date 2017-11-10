package engine.rule.test.creator.pdl;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.HistoricalPerformanceInForm;
import engine.rule.test.creator.TestModelCreator;

public class HistoricalPerformanceModelCreator extends TestModelCreator {

	public HistoricalPerformanceModelCreator(Map<String, String> columnNameValueMappings) {
		super(HistoricalPerformanceInForm.class, HistoricalPerformanceInForm.Key, columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
