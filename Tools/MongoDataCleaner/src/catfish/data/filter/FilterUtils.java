package catfish.data.filter;

import java.util.LinkedList;
import java.util.List;

import catfish.data.model.CompanyAnswerPersonAnswerModel;

public class FilterUtils {

    public static List<CompanyAnswerPersonAnswerModel> getInvalid(List<CompanyAnswerPersonAnswerModel> modelList) {
        List<CompanyAnswerPersonAnswerModel> invalidList = new LinkedList<CompanyAnswerPersonAnswerModel>();
        if(modelList == null || modelList.isEmpty()) {
            return invalidList;
        }
        
        for(CompanyAnswerPersonAnswerModel model : modelList) {
            if (!_isValid(model)) {
                invalidList.add(model);
            }
        }
        
        return invalidList;
    }
    
    private static boolean _isValid(CompanyAnswerPersonAnswerModel model) {
        Integer answerResult = model.getX_CheckCompanyAnswerPersonAnswerResult();
        if (answerResult == null
                || answerResult == 1
                || answerResult == 2
                || answerResult == 4
                || answerResult == 8
                || answerResult == 16
                ) {
            return true;
        }
        
        return false;
    }
}
