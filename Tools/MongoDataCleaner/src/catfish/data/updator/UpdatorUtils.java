package catfish.data.updator;

import java.util.List;

import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.data.model.CompanyAnswerPersonAnswerModel;

public class UpdatorUtils {

    private static int _fixedCompanyAnswerPersonAnswerResult = 1;
    
    public static void fixCheckCompanyAnswerPersonAnswerResult(List<CompanyAnswerPersonAnswerModel> modelList) {
        for(CompanyAnswerPersonAnswerModel model : modelList) {
            AppDerivativeVariableManager.addVariables( new AppDerivativeVariable(
                    model.getAppId().toUpperCase(),
                    "X_CheckCompanyAnswerPersonAnswerResult",
                    _fixedCompanyAnswerPersonAnswerResult));
            Logger.get().info(
                    "set X_CheckCompanyAnswerPersonAnswerResult for " 
                    + model.getAppId() 
                    + " from " + model.getX_CheckCompanyAnswerPersonAnswerResult()
                    + " to " + _fixedCompanyAnswerPersonAnswerResult);
        }
    }
}
