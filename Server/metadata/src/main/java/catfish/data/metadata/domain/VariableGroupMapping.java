package catfish.data.metadata.domain;

import javax.persistence.Entity;

@Entity
public class VariableGroupMapping extends AbstractLifecycleEntity{

    private Long variableId;
    
    private Long groupId;

    public Long getVariableId() {
        return variableId;
    }

    public void setVariableId(Long variableId) {
        this.variableId = variableId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
