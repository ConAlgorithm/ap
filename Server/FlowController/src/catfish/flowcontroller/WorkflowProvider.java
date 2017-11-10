package catfish.flowcontroller;

public interface WorkflowProvider {
    WorkFlow get(String flowName);
    WorkFlow getByAppId(String appId);
}
