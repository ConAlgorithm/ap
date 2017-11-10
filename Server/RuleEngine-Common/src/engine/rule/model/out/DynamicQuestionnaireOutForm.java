package engine.rule.model.out;

import java.util.ArrayList;
import java.util.List;

import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.rule.model.BaseForm;

@ModelInstance(description = "动态问卷结果")
public class DynamicQuestionnaireOutForm extends BaseForm {

	private List<Integer> questionnaireResultsList = new ArrayList<Integer>();

	@ModelMethod(name = "向(this)加入(#1,<联系人类型>)选项", paramDomains = "engine.rule.domain.QuestionnaireStrategy")
	public void addToResultList(int contactPersonType) {
		questionnaireResultsList.add(contactPersonType);
	}

	public List<Integer> getQuestionnaireResultsList() {
		return questionnaireResultsList;
	}

	public void setQuestionnaireResultsList(
			List<Integer> questionnaireResultsList) {
		this.questionnaireResultsList = questionnaireResultsList;
	}
}
