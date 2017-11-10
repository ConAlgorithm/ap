package catfish.data.assembler;

import java.util.LinkedList;
import java.util.List;

import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.data.model.CompanyAnswerPersonAnswerModel;

public class AssemblerUtils {
    
    public static List<CompanyAnswerPersonAnswerModel> getAllModels() {
        List<CompanyAnswerPersonAnswerModel> modelList = new LinkedList<CompanyAnswerPersonAnswerModel>();
        
        List<InstallmentApplicationObject> apps = InstallmentApplicationDao.getAppsFromApp();
        
        for(InstallmentApplicationObject app : apps) {
            CompanyAnswerPersonAnswerModel model = new CompanyAnswerPersonAnswerModel();
            
            model.setAppId(app.Id);
            model.setStatus(app.Status);
            
            AppDerivativeVariables variables = AppDerivativeVariableManager
                    .getVariables(app.Id.toUpperCase());
            
            if (variables != null) {
                int tmp = variables.getAsInt("X_CheckCompanyAnswerPersonAnswerResult", -1);
                model.setX_CheckCompanyAnswerPersonAnswerResult(tmp == -1 ? null : tmp);
            } else {
                model.setX_CheckCompanyAnswerPersonAnswerResult(null);
            }
            
            modelList.add(model);
        }
        
        return modelList;
    }
    
}
