package engine.rule.test.tester;

import java.util.Map;

import engine.rule.model.creator.AbstractModelCreator;
import engine.rule.test.creator.CompositeModelCreator;
import engine.rule.test.creator.pdl.*;
import engine.rule.test.data.TestCaseData;
import engine.rule.test.validator.ResultValidator;

public class PDLTester extends Tester{

	public PDLTester(TestCaseData testcaseData, ResultValidator validator) {
		super(testcaseData, validator);
	}

	@Override
	protected CompositeModelCreator getInModelCreator(Map<String, String> line) {
		CompositeModelCreator creator = new CompositeModelCreator();
		creator.addCreator(new InvestigationModelCreator(line));
		creator.addCreator(new FraudCheckModelCreator(line));
		creator.addCreator(new ApplicationModelCreator(line));
		creator.addCreator(new PersonalInfoModelCreator(line));
		creator.addCreator(new ConsistencyCheckModelCreator(line));
		creator.addCreator(new CreditReferenceModelCreator(line));
		creator.addCreator(new SocialRelationModelCreator(line));
		creator.addCreator(new ThirdpartyDataModelCreator(line));
		creator.addCreator(new BehaviorModelCreator(line));
		creator.addCreator(new DynamicApplicationModelCreator(line));
		creator.addCreator(new FInfoModelCreator(line));
		creator.addCreator(new HistoricalPerformanceModelCreator(line));
		return creator;
	}

	@Override
	protected AbstractModelCreator getOutModelCreator(Map<String, String> line) {
		int reuploadCount = 0;
		if(line.containsKey(REUPLOADCOUNT)){
			reuploadCount = new Integer(line.get(REUPLOADCOUNT));
		}
		return  new OutModelCreator(reuploadCount, line);
	}

}
