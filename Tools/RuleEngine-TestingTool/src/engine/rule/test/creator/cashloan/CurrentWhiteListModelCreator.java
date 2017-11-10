package engine.rule.test.creator.cashloan;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.cashloan.CreditReferenceInForm;
import engine.rule.model.in.cashloan.CurrentWhiteListInForm;
import engine.rule.test.creator.TestModelCreator;

public class CurrentWhiteListModelCreator extends TestModelCreator {
    public CurrentWhiteListModelCreator(Map<String, String> columnNameValueMappings) {
        super(CurrentWhiteListInForm.class, "in_Whitelist", columnNameValueMappings);
    }


    @Override
    protected boolean setSpecificFormValue(BaseForm form) {
        // TODO Auto-generated method stub
        return false;
    } 

}
