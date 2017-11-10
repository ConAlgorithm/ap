package catfish.data.metadata.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Variable extends AbstractMetaData{

    @Column(nullable = false)
    private String defaultValue;
    
    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    private VariableType variableType;
    
    @ManyToOne
    @JoinColumn(name = "nominalTypeId", referencedColumnName = "id")
    private NominalType nominalType;
    
    @ManyToMany(mappedBy = "variables")
    private Collection<VariableGroup> groups;

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    public NominalType getNominalType() {
        return nominalType;
    }

    public void setNominalType(NominalType nominalType) {
        this.nominalType = nominalType;
    }

    public Collection<VariableGroup> getGroups() {
        return groups;
    }

    public void setGroups(Collection<VariableGroup> groups) {
        this.groups = groups;
    }
}
