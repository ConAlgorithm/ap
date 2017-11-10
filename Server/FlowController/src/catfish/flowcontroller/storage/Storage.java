package catfish.flowcontroller.storage;

import java.util.List;

import catfish.flowcontroller.models.Application;

public interface Storage {
    int save(Application data);
    Application load(String appId);
    List<Application> load(List<String> appIds, List<String> appfields);
    
    List<Application> queryPendingApps(String workflow, List<String> appfields);
}
