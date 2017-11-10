package engine.rule.test.validator;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.test.validator.ValidatorBase;

public interface ResultValidator extends ValidatorBase{
    public boolean validate(BaseForm form, Map<String, String> columnValueMappings);
}
