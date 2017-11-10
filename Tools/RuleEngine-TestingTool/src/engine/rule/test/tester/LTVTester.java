package engine.rule.test.tester;

import java.util.Map;

import engine.rule.model.creator.AbstractModelCreator;
import engine.rule.test.creator.CompositeModelCreator;
import engine.rule.test.creator.cashloan.ConsistencyCheckModelCreator;
import engine.rule.test.creator.cashloan.CreditCheckModelCreator;
import engine.rule.test.creator.cashloan.CreditCheckPercentModelCreator;
import engine.rule.test.creator.cashloan.CreditReferenceModelCreator;
import engine.rule.test.creator.cashloan.CurrentWhiteListModelCreator;
import engine.rule.test.creator.cashloan.InvestigationModelCreator;
import engine.rule.test.creator.cashloan.OutModelCreator;
import engine.rule.test.creator.cashloan.SHZXModelCreator;
import engine.rule.test.creator.cashloan.ThirdpartyDataModelCreator;
import engine.rule.test.data.TestCaseData;
import engine.rule.test.validator.ResultValidator;

public class LTVTester extends Tester {

	public LTVTester(TestCaseData testcaseData, ResultValidator validator) {
		super(testcaseData, validator);
	}

	@Override
	protected CompositeModelCreator getInModelCreator(Map<String, String> line) {
		CompositeModelCreator creator = new CompositeModelCreator();
		creator.addCreator(new InvestigationModelCreator(line));
		creator.addCreator(new CreditReferenceModelCreator(line));
		creator.addCreator(new ThirdpartyDataModelCreator(line));
		creator.addCreator(new CurrentWhiteListModelCreator(line));
		creator.addCreator(new CreditCheckModelCreator(line));
		creator.addCreator(new SHZXModelCreator(line));
		creator.addCreator(new ConsistencyCheckModelCreator(line));
		creator.addCreator(new CreditCheckPercentModelCreator(line));
		return creator;
	}

	@Override
	protected AbstractModelCreator getOutModelCreator(Map<String, String> line) {
		int reuploadCount = 0;
		if(line.containsKey(REUPLOADCOUNT)){
			reuploadCount = new Integer(line.get(REUPLOADCOUNT));
		}
		return new OutModelCreator(reuploadCount, line);
	}
}
