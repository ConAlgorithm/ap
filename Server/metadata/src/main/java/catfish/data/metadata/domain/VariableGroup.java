package catfish.data.metadata.domain;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class VariableGroup extends AbstractMetaData{

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "VariableGroupMapping", 
    joinColumns = { @JoinColumn(name = "groupId", referencedColumnName = "id") }, 
    inverseJoinColumns = { @JoinColumn(name = "variableId", referencedColumnName = "id") })
    private Collection<Variable> variables;

    public Collection<Variable> getVariables() {
        return variables;
    }

    public void setVariables(Collection<Variable> variables) {
        this.variables = variables;
    }
}
