package catfish.data.metadata.domain;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class NominalType extends AbstractMetaData{

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nominalTypeId")
    private Collection<NominalValue> values;

    public Collection<NominalValue> getValues() {
        return values;
    }

    public void setValues(Collection<NominalValue> values) {
        this.values = values;
    }
}
