package catfish.data;

import java.util.List;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.database.DatabaseConfig;
import catfish.data.assembler.AssemblerUtils;
import catfish.data.export.ExportUtils;
import catfish.data.filter.FilterUtils;
import catfish.data.model.CompanyAnswerPersonAnswerModel;
import catfish.data.updator.UpdatorUtils;

public class Driver {

    public static void main(String[] args) {
        
        boolean isProfilerMode = _isProfilerMode(args);
        
        StartupConfig.initialize();
        Logger.initialize();
        DatabaseConfig.initialize();
        
        List<CompanyAnswerPersonAnswerModel> allApps = AssemblerUtils.getAllModels();
        List<CompanyAnswerPersonAnswerModel> invalidApps = FilterUtils.getInvalid(allApps);
        
        if (isProfilerMode)
        {
            Logger.get().info("Catfish Data Is Running In Profiler Mode ...");
            Logger.get().info("start exporting data ...");
            
            ExportUtils.export2Csv(allApps, "allAppsFromApp.csv");
            ExportUtils.export2Csv(invalidApps, "invalidAppsFromApp.csv");
            
            Logger.get().info("exporting data finished !");
        }
        else
        {
            Logger.get().info("Catfish Data Is Running In Updator Mode ...");
            Logger.get().info(String.format(
                    " %d invalid records to be updated", invalidApps.size()));
            
            UpdatorUtils.fixCheckCompanyAnswerPersonAnswerResult(invalidApps);
            
            Logger.get().info("updating data finished !");
        }
    }

    private static boolean _isProfilerMode(String[] args) {
        return args != null && args.length >= 1 && args[0].equals("p");
    }
    
}
